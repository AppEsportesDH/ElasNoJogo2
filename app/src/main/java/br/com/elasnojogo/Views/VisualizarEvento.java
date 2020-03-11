package br.com.elasnojogo.Views;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.elasnojogo.Model.DadosEvento;
import br.com.elasnojogo2.R;

import static br.com.elasnojogo.Views.HomeFragment.EVENTO_CHAVE;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisualizarEvento extends Fragment {

    private ImageView imageViewLogo;
    private ImageView imageViewEvento;
    private TextView textViewNomeEvento;
    private TextView textViewLocalEvento;
    private TextView textViewDataEvento;

    public VisualizarEvento() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visualizar_evento, container, false);

        initViews(view);

        //verificação se o arguments nao está nulo
        if (getArguments() != null){

            //passando os dados do arguments para o bundle
            Bundle bundle = getArguments();
            //passando o contato que está chegando para a variavel do tipo contato
            DadosEvento dadosEvento = bundle.getParcelable(EVENTO_CHAVE);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                Drawable drawable = getResources().getDrawable(dadosEvento.getAdicionarImagem());

                imageViewEvento.setImageDrawable(drawable);

            }

            textViewNomeEvento.setText(dadosEvento.getNomeEvento());
            textViewLocalEvento.setText(dadosEvento.getLocalEvento());
            textViewDataEvento.setText(dadosEvento.getDataEvento());
        }

        imageViewLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initViews(View view) {

        imageViewLogo = view.findViewById(R.id.imageView4);
        imageViewEvento = view.findViewById(R.id.imageView_evento);
        textViewNomeEvento = view.findViewById(R.id.nome_evento_visualizarfragment);
        textViewLocalEvento = view.findViewById(R.id.local_visualizarfragment);
        textViewDataEvento = view.findViewById(R.id.data_visualizarfragment);
    }
}