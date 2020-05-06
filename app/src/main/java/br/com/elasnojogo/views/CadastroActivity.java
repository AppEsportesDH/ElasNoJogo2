package br.com.elasnojogo.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import br.com.elasnojogo.model.Usuario;
import br.com.elasnojogo2.R;

public class CadastroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cadastrar = findViewById(R.id.btn_cadastrar);

        cadastrar.setOnClickListener(clique);

        Spinner spinnerIdentificacao = findViewById(R.id.spinner_genero);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista_identificacao, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerIdentificacao.setAdapter(adapter);
        spinnerIdentificacao.setOnItemSelectedListener(this);
    }

    View.OnClickListener clique = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CadastroActivity.this, HomeActivity.class);
            Usuario usuario = new Usuario("Sol", "silmarasol@hotmail.com", "11996887598", "RFD234", "RFD234");

            Bundle bundle = new Bundle();
            bundle.putParcelable(CadastroActivity.this.getString(R.string.usuario), usuario);

            intent.putExtras(bundle);

            CadastroActivity.this.startActivity(intent);
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