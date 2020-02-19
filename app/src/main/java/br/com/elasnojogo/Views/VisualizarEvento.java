package br.com.elasnojogo.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.elasnojogo2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisualizarEvento extends Fragment {


    public VisualizarEvento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visualizar_evento, container, false);
    }

}