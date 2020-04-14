package br.com.elasnojogo.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import br.com.elasnojogo.Model.Usuario;
import br.com.elasnojogo2.R;

public class CadastroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText nome;
    private EditText email;
    private EditText telefone;
    private EditText senha;
    private EditText confirmarSenha;
    Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cadastrar = findViewById(R.id.btn_cadastrar);

        cadastrar.setOnClickListener(clique);

        Spinner spinnerIdentificacao = findViewById(R.id.spinner_genero);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.lista_identificacao, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerIdentificacao.setAdapter(adapter);
        spinnerIdentificacao.setOnItemSelectedListener(this);


    }

    View.OnClickListener clique = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
            Usuario usuario = new Usuario("Sol", "silmarasol@hotmail.com", "11996887098", "RFD234", "RFD234");

            Bundle bundle = new Bundle();
            bundle.putParcelable("Usuario", usuario);

            intent.putExtras(bundle);

            startActivity(intent);
        }
    };


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}