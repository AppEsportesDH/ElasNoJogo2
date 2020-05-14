package br.com.elasnojogo.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.elasnojogo.model.DadosEvento;
import br.com.elasnojogo.model.Evento;
import br.com.elasnojogo2.R;

import static br.com.elasnojogo.constantes.Constantes.EVENTO_CHAVE;

public class VisualizarEvento extends Fragment {
    private ImageView imageViewEvento;
    private TextView textViewNomeEvento;
    private TextView textViewLocalEvento;
    private TextView textViewDataEvento;
    private TextView textViewHorarioEvento;
    private TextView textViewCategoriaEvento;
    private FloatingActionButton buttonCompartilhar;
    private DadosEvento dadosEventos;

    public VisualizarEvento() {
    }

    private static void onClick(View view1) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visualizar_evento, container, false);

        initViews(view);

        buttonCompartilhar.setOnClickListener(v -> compartilharEvento(dadosEventos));

        if (getArguments() != null){
            Bundle bundle = getArguments();
            Evento dadosEvento = bundle.getParcelable(EVENTO_CHAVE);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            }

            textViewNomeEvento.setText(dadosEvento.getNomeEvento());
            textViewLocalEvento.setText(getString(R.string.local) + dadosEvento.getLocal());
            textViewDataEvento.setText(getString(R.string.data) + dadosEvento.getData());
            textViewHorarioEvento.setText(getString(R.string.horario) + dadosEvento.getHorario());
            textViewCategoriaEvento.setText(getString(R.string.categoria) + dadosEvento.getCategoriaEsportes());
        };
        return view;
    }

    private void initViews(View view) {

        imageViewEvento = view.findViewById(R.id.imageView_evento);
        textViewNomeEvento = view.findViewById(R.id.nome_evento_visualizarfragment);
        textViewLocalEvento = view.findViewById(R.id.local_visualizarfragment);
        textViewDataEvento = view.findViewById(R.id.data_visualizarfragment);
        textViewHorarioEvento = view.findViewById(R.id.horario_visualizarfragment);
        textViewCategoriaEvento = view.findViewById(R.id.categoria_visualizarfragment);
        buttonCompartilhar = view.findViewById(R.id.compartilhar);
    }

    private void compartilharEvento(DadosEvento dadosEvento) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Participe do meu evento!");
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
}