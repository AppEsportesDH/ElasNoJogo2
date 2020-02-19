package br.com.elasnojogo.Views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.com.elasnojogo.R;


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

        botao_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//A Activity de Menu ainda será criada
                String email1 = email.getText().toString();
                String senha1 = senha.getText().toString();

                validarEmailSenha(email1, senha1);

            }
        });

        botao_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });
    }

    private boolean validarEmailSenha(String emailInput, String senhaInput) {
        if (emailInput.isEmpty() && senhaInput.isEmpty()) {
            email.setError("Preencha os campos!");
            senha.setError("Preencha os campos!");
            return false;
        } else {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra("Email", emailInput);
            startActivity(intent);
            return true;
        }
    }


    public void initViews() {
        email = findViewById(R.id.campo_email);
        senha = findViewById(R.id.campo_senha);
        botao_cadastro = findViewById(R.id.botao_cadastro);
        botao_entrar = findViewById(R.id.botao_entrar);
    }
}