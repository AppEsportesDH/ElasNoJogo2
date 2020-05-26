package br.com.elasnojogo.views;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import br.com.elasnojogo.model.Usuario;
import br.com.elasnojogo.util.AppUtil;
import br.com.elasnojogo.viewModel.CadastroViewModel;
import br.com.elasnojogo2.R;
import de.hdodenhof.circleimageview.CircleImageView;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class CadastroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button cadastrar;
    private TextInputLayout nomeUsuario;
    private TextInputLayout emailUsuario;
    private TextInputLayout telefoneUsuario;
    private TextInputLayout senhaUsuario;
    private TextInputLayout confirmeSenhaUsuario;
    private ProgressBar progressBar;
    private Spinner spinnerIdentificacao;
    final String[] identificacao = new String[1];
    private CadastroViewModel cadastroViewModel;
    private CircleImageView imagemUsuario;
    private static final int PERMISSION_CODE = 100;
    private InputStream stream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        initViews();

        imagemUsuario.setOnClickListener(v -> {
            captureImage();
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = nomeUsuario.getEditText().getText().toString();
                String senha = senhaUsuario.getEditText().getText().toString();
                String email = emailUsuario.getEditText().getText().toString();
                String telefone = telefoneUsuario.getEditText().getText().toString();
                String confirmarSenha = confirmeSenhaUsuario.getEditText().getText().toString();

                if (validaCampos(nome, senha, email, telefone, confirmarSenha)) {
                    registrarUsuario(senha, email);
                    Log.i("Spinner", identificacao[0]);
                }

                Usuario user = new Usuario(identificacao[0], nome, email, telefone, senha, confirmarSenha);

                cadastroViewModel.salvarInfoUsuario(user);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista_identificacao, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerIdentificacao.setAdapter(adapter);
        spinnerIdentificacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                identificacao[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        loadImageFromFirebase();
    }

    private void registrarUsuario(String senha, String email) {
        progressBar.setVisibility(View.VISIBLE);
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        AppUtil.salvarIdUsuario(CadastroActivity.this, FirebaseAuth.getInstance()
                                .getCurrentUser().getUid());
                        salvarImagemUsuarioFirebase(stream, "nome_perfil");

                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    } else {
                        Snackbar.make(cadastrar, task.getException().getMessage(), Snackbar.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }


    private boolean validaCampos(String nome, String senha, String email, String telefone, String confirmarSenha) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailUsuario.setError("Email inválido");
            emailUsuario.setErrorEnabled(false);
            emailUsuario.requestFocus();
            Snackbar.make(emailUsuario, "Email inválido", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (email.isEmpty()) {
            emailUsuario.setError("O campo email não pode ser vazio");
            emailUsuario.setErrorEnabled(false);
            emailUsuario.requestFocus();
            Snackbar.make(emailUsuario, "O campo email não pode ser vazio", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (senha.isEmpty()) {
            senhaUsuario.setError("O campo senha não deve ser vazio");
            senhaUsuario.setErrorEnabled(false);
            senhaUsuario.requestFocus();
            Snackbar.make(senhaUsuario, "O campo senha não deve ser vazio", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (senha.length() < 6) {
            senhaUsuario.setError("A senha deve ter mais de 6 caracters");
            senhaUsuario.setErrorEnabled(false);
            senhaUsuario.requestFocus();
            Snackbar.make(senhaUsuario, "A senha deve ter mais de 6 caracters", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (nome.isEmpty()) {
            nomeUsuario.setError("O campo nome não pode ser vazio");
            nomeUsuario.setErrorEnabled(false);
            nomeUsuario.requestFocus();
            Snackbar.make(nomeUsuario, "O campo nome não pode ser vazio", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (telefone.isEmpty()) {
            telefoneUsuario.setError("O campo telefone não pode ser vazio");
            telefoneUsuario.setErrorEnabled(false);
            telefoneUsuario.requestFocus();
            Snackbar.make(telefoneUsuario, "O campo telefone não pode ser vazio", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (confirmarSenha.isEmpty()) {
            confirmeSenhaUsuario.setError("O campo confirmar senha não pode ser vazio");
            confirmeSenhaUsuario.setErrorEnabled(false);
            confirmeSenhaUsuario.requestFocus();
            Snackbar.make(confirmeSenhaUsuario, "O campo confirmar senha não pode ser vazio", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (senha == confirmarSenha) {
            Snackbar.make(confirmeSenhaUsuario, "As senhas devem ser iguais", Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void initViews() {
        nomeUsuario = findViewById(R.id.textInputLayoutUsuario);
        senhaUsuario = findViewById(R.id.textInputLayoutSenha);
        emailUsuario = findViewById(R.id.textInputLayoutEmail);
        telefoneUsuario = findViewById(R.id.textInputLayoutTelefone);
        confirmeSenhaUsuario = findViewById(R.id.textInputLayoutConfirmeSenha);
        imagemUsuario = findViewById(R.id.imageview_user_login);
        cadastrar = findViewById(R.id.btn_cadastrar);
        progressBar = findViewById(R.id.progressBar);
        spinnerIdentificacao = findViewById(R.id.spinner_genero);
        cadastroViewModel = ViewModelProviders.of(this).get(CadastroViewModel.class);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void captureImage() {
        int permissionCamera = ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.CAMERA);
        int permissionStorage = ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCamera == PackageManager.PERMISSION_GRANTED && permissionStorage == PackageManager.PERMISSION_GRANTED) {
//            EasyImage.openCameraForImage(this, MODE_PRIVATE);
            EasyImage.openChooserWithGallery(this, "escolher_imagem", MODE_PRIVATE);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        captureImage();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {

            @Override
            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {

                for (File file : imageFiles) {
                    try {
                        stream = new FileInputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void salvarImagemUsuarioFirebase(InputStream stream, String nomeFoto){

        StorageReference storage = FirebaseStorage
                .getInstance()
                .getReference()
                .child(AppUtil.getIdUsuario(getApplication()) + "/image/perfil/" + nomeFoto);

        if (stream == null) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            return;
        }

        UploadTask uploadTask = storage.putStream(stream);

        uploadTask.addOnSuccessListener(taskSnapshot -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));

        }).addOnFailureListener(e -> {

            Snackbar snackbar = Snackbar.make(imagemUsuario, e.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
        });
    }

    private void loadImageFromFirebase() {
        StorageReference storage = FirebaseStorage
                .getInstance()
                .getReference()
                .child(AppUtil.getIdUsuario(getApplication()) + "/image/perfil/nome_perfil");

        storage.getDownloadUrl()
                .addOnSuccessListener(uri -> {

                    Picasso.get()
                            .load(uri)
                            .rotate(90)
                            .into(imagemUsuario);
                });
    }
}