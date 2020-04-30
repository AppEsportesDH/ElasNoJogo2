package br.com.elasnojogo.views;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.elasnojogo.model.Evento;
import br.com.elasnojogo2.R;

import static br.com.elasnojogo.constantes.Constantes.EVENTO_CHAVE;

public class VisualizarEvento extends Fragment {
    private ImageView imageViewLogo;
    private ImageView imageViewEvento;
    private TextView textViewNomeEvento;
    private TextView textViewLocalEvento;
    private TextView textViewDataEvento;
    private TextView textViewCategoriaEvento;

    public VisualizarEvento() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visualizar_evento, container, false);

        initViews(view);

        if (getArguments() != null){
            Bundle bundle = getArguments();
            Evento dadosEvento = bundle.getParcelable(EVENTO_CHAVE);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            }

            textViewNomeEvento.setText(dadosEvento.getNomeEvento());
            textViewLocalEvento.setText(getString(R.string.local) + dadosEvento.getLocal());
            textViewDataEvento.setText(getString(R.string.data) + dadosEvento.getData());
            textViewCategoriaEvento.setText("Categoria:"+ dadosEvento.getCategoriaEsportes());
        }

        imageViewLogo.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        });

        return view;
    }

    private void initViews(View view) {

        imageViewLogo = view.findViewById(R.id.imageView4);
        imageViewEvento = view.findViewById(R.id.imageView_evento);
        textViewNomeEvento = view.findViewById(R.id.nome_evento_visualizarfragment);
        textViewLocalEvento = view.findViewById(R.id.local_visualizarfragment);
        textViewDataEvento = view.findViewById(R.id.data_visualizarfragment);
        textViewCategoriaEvento = view.findViewById(R.id.categoria_visualizarfragment);
    }
}