package br.com.elasnojogo.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.elasnojogo.Model.Sport;
import br.com.elasnojogo2.R;

import static br.com.elasnojogo.Constantes.Constantes.SPORT;


public class DetalheHomeFragment extends Fragment {

    private TextView textViewCategoria;
    private ImageView imagem;
    private TextView textViewDescription;

    public DetalheHomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhe_home, container, false);

        initViews(view);

        if (getArguments() != null) {

            Bundle bundle = getArguments();

            Sport result = bundle.getParcelable(SPORT);

            textViewCategoria.setText(result.getStrSport());
            textViewDescription.setText(result.getStrSportDescription());

            Picasso.get().load(result.getStrSportThumb()).into(imagem);
        }

        return view;
    }

    public void initViews(View view){
        textViewCategoria = view.findViewById(R.id.text_view_categoria);
        imagem = view.findViewById(R.id.imageView_detalhe);
        textViewDescription = view.findViewById(R.id.text_view_description);
    }
}


