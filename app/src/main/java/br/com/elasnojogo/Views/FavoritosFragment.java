package br.com.elasnojogo.Views;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import br.com.elasnojogo.Interface.FavoritosView;
import br.com.elasnojogo.Model.DadosEvento;
import br.com.elasnojogo2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment implements FavoritosView {

    private LinearLayout favorito;
    FavoritosView mListener;

    @Override
    public void visualizarEvento(DadosEvento comunicador) {
    }

    public FavoritosFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onAttach (Context activity){
//        super.onAttach(activity);
//        if(!(activity instanceof FavoritosView)){
//            throw new RuntimeException();
//        }
//        mListener = (FavoritosView) activity;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false);

    }

}