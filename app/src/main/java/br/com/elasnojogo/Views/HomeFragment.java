package br.com.elasnojogo.Views;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.elasnojogo.Interface.EventoListener;
import br.com.elasnojogo.Model.DadosEvento;
import br.com.elasnojogo.adapter.EventoRecyclerViewAdapter;
import br.com.elasnojogo2.R;

public class HomeFragment extends Fragment implements EventoListener {

    private RecyclerView recyclerViewEventos;
    private EventoRecyclerViewAdapter adapter;
    private Button buttonCriarEvento;

    public static final String EVENTO_CHAVE = "evento";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(view);

        adapter = new EventoRecyclerViewAdapter(getListaEventos(), this);
        recyclerViewEventos.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewEventos.setLayoutManager(layoutManager);

        buttonCriarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new EventoFragment());
            }
        });

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private List<DadosEvento> getListaEventos() {
        List<DadosEvento> eventos = new ArrayList<>();

        eventos.add(new DadosEvento(R.drawable.contraataque_post_mulheres_esporte_01_ls,"Empoderamento feminino no futebol", "SP", "20/10/2021"));
        eventos.add(new DadosEvento(R.drawable.contraataque_post_mulheres_esporte_01_ls,"Empoderamento feminino no basquete", "MG", "21/01/2022"));
        eventos.add(new DadosEvento(R.drawable.contraataque_post_mulheres_esporte_01_ls,"Empoderamento feminino no volei", "RJ", "22/02/2023"));
        eventos.add(new DadosEvento(R.drawable.contraataque_post_mulheres_esporte_01_ls,"Empoderamento feminino no nado", "BH", "23/03/2024"));
        eventos.add(new DadosEvento(R.drawable.contraataque_post_mulheres_esporte_01_ls,"Empoderamento feminino no automobilismo", "SC", "24/04/2025"));

        return eventos;
    }

    @Override
    public void enviaEvento(DadosEvento dadosEvento) {
        Fragment fragment = new VisualizarEvento();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EVENTO_CHAVE, dadosEvento);
        fragment.setArguments(bundle);

        replaceFragment(fragment);
    }

    private void initViews(View view) {
        buttonCriarEvento = view.findViewById(R.id.criarevento_btn);
        recyclerViewEventos = view.findViewById(R.id.recycler_view_eventos);
    }
}