package br.com.elasnojogo.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

import br.com.elasnojogo.util.AppUtil;
import br.com.elasnojogo2.R;

import static br.com.elasnojogo2.R.string.preencha_campo;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout email;
    private TextInputLayout senha;
    private Button botao_cadastro;
    private Button botao_entrar;
    private ImageButton google_btn;
    private ImageButton facebook_btn;
    private GoogleSignInClient googleSignInClient;
    public static final String GOOGLE_ACCOUNT = "google_account";
    private static final int RC_SIGN_IN = 1001;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        botao_entrar.setOnClickListener(v -> {

            String email1 = email.getEditText().toString();
            String senha1 = senha.getEditText().toString();

            loginEmail(email1, senha1);

        });

        callbackManager = CallbackManager.Factory.create();

        botao_cadastro.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, br.com.elasnojogo.views.CadastroActivity.class)));

        google_btn.setOnClickListener(v -> loginGoogle());

        facebook_btn.setOnClickListener(v -> loginFacebook());
    }

    public void loginGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(LoginActivity.this, connectionResult -> {
                    Toast.makeText(getApplicationContext(), "Falha na conexão", Toast.LENGTH_SHORT).show();
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signIntent, RC_SIGN_IN);
    }

    private void autenticacaoGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                irParaHome(FirebaseAuth.getInstance().getCurrentUser().getUid());
            } else {
                Toast.makeText(getApplicationContext(), "Tente logar novamente :)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void irParaHome(String uiid) {
        AppUtil.salvarIdUsuario(getApplication().getApplicationContext(), uiid);
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }

    private void loginEmail(String emailInput, String senhaInput) {
        if (emailInput.isEmpty() && senhaInput.isEmpty()) {
            email.setError(getString(preencha_campo));
            senha.setError(getString(preencha_campo));
            return;
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(emailInput, senhaInput)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        irParaHome(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    } else {
                        // Se deu algum erro mostramos para o usuário a mensagem
                        Snackbar.make(botao_entrar, "Erro ao tentar logar" + task.getException().getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    public void initViews() {
        email = findViewById(R.id.campo_email);
        senha = findViewById(R.id.campo_senha);
        botao_cadastro = findViewById(R.id.botao_cadastro);
        botao_entrar = findViewById(R.id.botao_entrar);
        facebook_btn = findViewById(R.id.facebook_btn);
        google_btn = findViewById(R.id.google_btn);

    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RC_SIGN_IN) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                if (result.isSuccess()) {
                    GoogleSignInAccount account = result.getSignInAccount();
                    autenticacaoGoogle(account);
                }
            }
        }
    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount alreadyLoggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyLoggedAccount != null) {
            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
            autenticacaoGoogle(alreadyLoggedAccount);
        } else {
        }
    }

    public void loginFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AuthCredential credential = FacebookAuthProvider
                        .getCredential(loginResult.getAccessToken().getToken());

                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(task -> {
                            irParaHome(loginResult.getAccessToken().getUserId());

                        });
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Cancelado!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}