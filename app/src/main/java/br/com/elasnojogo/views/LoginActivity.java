package br.com.elasnojogo.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import br.com.elasnojogo.util.AppUtil;
import br.com.elasnojogo2.R;

import static br.com.elasnojogo2.R.string.preencha_campo;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText email;
    private TextInputEditText senha;
    private Button botao_cadastro;
    private Button botao_entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        botao_entrar.setOnClickListener(v -> {
            loginEmail();
        });

        botao_cadastro.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, br.com.elasnojogo.views.CadastroActivity.class)));
    }


    public void initViews() {
        email = findViewById(R.id.campo_email);
        senha = findViewById(R.id.campo_senha);
        botao_cadastro = findViewById(R.id.botao_cadastro);
        botao_entrar = findViewById(R.id.botao_entrar);
    }


    public void loginEmail(){
        String email1 = email.getText().toString();
        String senha1 = senha.getText().toString();

        if (email1.isEmpty() || senha1.isEmpty()) {
            email.setError(getString(preencha_campo));
            senha.setError(getString(preencha_campo));
            return;
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email1,senha1).addOnCompleteListener(task -> {

            if (task.isSuccessful()){
                irParaHome(FirebaseAuth.getInstance().getCurrentUser().getUid());

            }else{
                Snackbar.make(botao_entrar, "Erro ao fazer login" + task.getException().getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }


        private void irParaHome(String uid) {
        AppUtil.salvarIdUsuario(this, uid);
        startActivity (new Intent (this, HomeActivity.class));
        finish();
    }
    }