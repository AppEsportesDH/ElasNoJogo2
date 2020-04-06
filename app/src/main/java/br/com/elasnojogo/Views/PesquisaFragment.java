package br.com.elasnojogo.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import br.com.elasnojogo2.R;

public class PesquisaFragment extends Fragment {

    public PesquisaFragment (){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_pesquisa, container, false);
    }


}
