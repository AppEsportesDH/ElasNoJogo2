package br.com.elasnojogo.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import br.com.elasnojogo.Model.DadosEvento;
import br.com.elasnojogo.Interface.EventoView;
import br.com.elasnojogo2.R;

public class EventoFragment extends Fragment implements EventoView {

    private ImageView imageViewLogo;

    public EventoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_criarevento, container, false);

        initViews(view);

        imageViewLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void salvarEvento(DadosEvento comunicador) {
        // TODO: 15/02/2020 falta adicionar banco de dados;
    }

    private void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void initViews(View view) {
        imageViewLogo = view.findViewById(R.id.imageView5);
    }
}