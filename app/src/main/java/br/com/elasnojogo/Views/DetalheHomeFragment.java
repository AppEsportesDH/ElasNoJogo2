package br.com.elasnojogo.Views;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import br.com.elasnojogo.Model.Sport;
import br.com.elasnojogo2.R;

import static br.com.elasnojogo.Views.HomeFragment.SPORT;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalheHomeFragment extends Fragment {

    private TextView textViewCategoria;
    private ImageView imagem;
    private TextView textViewDescription;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalhe_home, container, false);

        initViews(view);

        if (getArguments() != null) {

            Bundle bundle = getArguments();

            Sport sport = bundle.getParcelable(SPORT);

            textViewCategoria.setText(sport.getStrSport());
            textViewDescription.setText(sport.getStrSportDescription());

            Picasso.get().load("https://www.thesportsdb.com/images/sports/").into(imagem);
        }

        return view;
    }

    public void initViews(View view){
        textViewCategoria = view.findViewById(R.id.text_view_categoria);
        imagem = view.findViewById(R.id.imageView_detalhe);
        textViewDescription = view.findViewById(R.id.text_view_description);
    }

    public void onBackPressed() {
    }
}


