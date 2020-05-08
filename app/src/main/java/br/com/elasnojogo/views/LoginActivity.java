package br.com.elasnojogo.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import br.com.elasnojogo.util.AppUtil;
import br.com.elasnojogo2.R;

import static br.com.elasnojogo2.R.string.preencha_campo;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText email;
    private TextInputEditText senha;
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

        callbackManager = CallbackManager.Factory.create();

        initViews();

        botao_entrar.setOnClickListener(v -> {

            String email1 = email.getText().toString();
            String senha1 = senha.getText().toString();

            loginEmail(email1, senha1);

        });

        botao_cadastro.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, br.com.elasnojogo.views.CadastroActivity.class)));
        google_btn.setOnClickListener(v -> loginGoogle());
        facebook_btn.setOnClickListener(v -> loginFacebook());
    }

    public void loginGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
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
                        Snackbar.make(botao_entrar, "Erro ao tentar logar " + task.getException().getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            if (requestCode == RC_SIGN_IN) {
                try {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    onLoggedIn(account);
                } catch (ApiException e) {
                    Toast.makeText(getApplicationContext(), "Tente novamente", Toast.LENGTH_SHORT).show();
                }
            }
    }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(GOOGLE_ACCOUNT, googleSignInAccount);

        startActivity(intent);
        finish();
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

    public void initViews() {
        email = findViewById(R.id.campo_email);
        senha = findViewById(R.id.campo_senha);
        botao_cadastro = findViewById(R.id.botao_cadastro);
        botao_entrar = findViewById(R.id.botao_entrar);
        facebook_btn = findViewById(R.id.facebook_btn);
        google_btn = findViewById(R.id.google_btn);

    }
}
