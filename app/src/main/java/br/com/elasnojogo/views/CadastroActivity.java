package br.com.elasnojogo.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.io.InputStream;
import br.com.elasnojogo.util.AppUtil;
import br.com.elasnojogo2.R;

public class CadastroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button cadastrar;
    private TextInputLayout nomeUsuario;
    private TextInputLayout emailUsuario;
    private TextInputLayout telefoneUsuario;
    private TextInputLayout senhaUsuario;
    private TextInputLayout confirmeSenhaUsuario;
    private ProgressBar progressBar;
    private InputStream stream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        initViews();

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer idadenumero;
                String nome = nomeUsuario.getEditText().getText().toString();
                String senha = senhaUsuario.getEditText().getText().toString();
                String email = emailUsuario.getEditText().getText().toString();
                String telefone = telefoneUsuario.getEditText().getText().toString();
                String confirmarSenha = confirmeSenhaUsuario.getEditText().getText().toString();

                try{idadenumero = Integer.valueOf(telefone);}catch(NumberFormatException ex){
                    idadenumero = -1;
                }

                if (validaCampos(nome, senha, email, telefone, confirmarSenha)){
                    registrarUsuario(nome, senha, email, telefone);
                }
            }
        });


    }

    private void registrarUsuario(String nome, String senha, String email, String idade) {

        progressBar.setVisibility(View.VISIBLE);

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(task -> {

                    progressBar.setVisibility(View.GONE);

                    if (task.isSuccessful()){
                        AppUtil.salvarIdUsuario(CadastroActivity.this, FirebaseAuth.getInstance()
                                .getCurrentUser().getUid());
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }else{

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

//        if (!telefone.isTelefone(telefone) {
//            telefoneUsuario.setError("Preencha corretamente (00) 00000-0000");
//            telefoneUsuario.setErrorEnabled(false);
//            telefoneUsuario.requestFocus();
//            Snackbar.make(telefoneUsuario, "O telefone não foi preenchido corretamente", Snackbar.LENGTH_LONG).show();
//            return false;}
        if (confirmarSenha.isEmpty()) {
            confirmeSenhaUsuario.setError("O campo confirmar senha não pode ser vazio");
            confirmeSenhaUsuario.setErrorEnabled(false);
            confirmeSenhaUsuario.requestFocus();
            Snackbar.make(confirmeSenhaUsuario, "O campo confirmar senha não pode ser vazio", Snackbar.LENGTH_LONG).show();
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
        cadastrar = findViewById(R.id.btn_cadastrar);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
    
}