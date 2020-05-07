package br.com.elasnojogo.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.io.InputStream;

import br.com.elasnojogo.model.Usuario;
import br.com.elasnojogo.util.AppUtil;
import br.com.elasnojogo2.R;

import static br.com.elasnojogo.constantes.Constantes.NOME_USUARIO;
import static br.com.elasnojogo.constantes.Constantes.USUARIO;
import static br.com.elasnojogo2.R.string.preencha_campo;

public class CadastroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button cadastrar;
    private TextInputEditText nomeUsuario;
    private TextInputEditText emailUsuario;
    private TextInputEditText telefoneUsuario;
    private TextInputEditText senhaUsuario;
    private TextInputEditText confirmeSenhaUsuario;
    private ProgressBar progressBar;
    private InputStream stream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        initViews();

        String nome = nomeUsuario.getText().toString();
        String email = emailUsuario.getText().toString();
        String telefone = telefoneUsuario.getText().toString();
        String senha = senhaUsuario.getText().toString();
        String confirmeSenha = confirmeSenhaUsuario.getText().toString();

        validarCamposCadastro(nome, email, telefone, senha, confirmeSenha);

        cadastrar.setOnClickListener(v -> {

            if (validarCamposCadastro(nome, email, telefone, senha, confirmeSenha)){

                Bundle bundle = new Bundle ();
                bundle.putString(NOME_USUARIO,nome);
                Intent intent = new Intent(CadastroActivity.this, br.com.elasnojogo.views.HomeActivity.class);
                intent.putExtra(USUARIO, bundle);
                startActivity(intent);
            }
        });

        Spinner spinnerIdentificacao = findViewById(R.id.spinner_genero);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista_identificacao, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerIdentificacao.setAdapter(adapter);
        spinnerIdentificacao.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void initViews (){
        cadastrar = findViewById(R.id.btn_cadastrar);
        nomeUsuario = findViewById(R.id.nome_usuaria);
        emailUsuario = findViewById(R.id.email);
        telefoneUsuario = findViewById(R.id.telefone);
        senhaUsuario = findViewById(R.id.senha);
        confirmeSenhaUsuario = findViewById(R.id.confirmarSenha);
        progressBar = findViewById(R.id.progressBar);
    }

    private boolean validarCamposCadastro(String nomeInput, String emailInput, String telefoneInput, String senhaInput, String confirmeSenhaInput) {
        if (nomeInput.isEmpty() && emailInput.isEmpty() && telefoneInput.isEmpty() && senhaInput.isEmpty() && confirmeSenhaInput.isEmpty()) {
            nomeUsuario.setError(getString(preencha_campo));
            emailUsuario.setError(getString(preencha_campo));
            telefoneUsuario.setError(getString(preencha_campo));
            confirmeSenhaUsuario.setError(getString(preencha_campo));
            return false;
        } else {
            registrarUsuario(emailInput, senhaInput);
            return true;
        }
    }

        private void registrarUsuario(String email, String senha){
            progressBar.setVisibility(View.VISIBLE);

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {

                            AppUtil.salvarIdUsuario(this, FirebaseAuth.getInstance().getCurrentUser().getUid());
                            startActivity(new Intent(this, HomeActivity.class));
                            finish();

                        } else {
                            Snackbar.make(cadastrar, "Erro ao cadastrar usuário -> " + task.getException().getMessage(), Snackbar.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
        }
    }




