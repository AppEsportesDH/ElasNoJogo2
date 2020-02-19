package br.com.elasnojogo.Views;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.elasnojogo.R;

public class HomeActivity extends AppCompatActivity {
    private TextView sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("Email");

        sms = findViewById(R.id.textViewMensagem);

        sms.setText(getString(R.string.mensagem_home, email));


    }
}