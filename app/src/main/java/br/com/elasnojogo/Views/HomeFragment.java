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

    public static final String EVENTO_CHAVE = "evento";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewEventos = view.findViewById(R.id.recycler_view_eventos);
        adapter = new EventoRecyclerViewAdapter(getListaEventos(), this);
        recyclerViewEventos.setAdapter(adapter);

        recyclerViewEventos.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private List<DadosEvento> getListaEventos() {
        List<DadosEvento> eventos = new ArrayList<>();

        eventos.add(new DadosEvento("Empoderamento feminino no futebol", "SP", "20/02/2020"));
        eventos.add(new DadosEvento("Empoderamento feminino no basquete", "SP", "20/02/2020"));
        eventos.add(new DadosEvento("Empoderamento feminino no volei", "SP", "20/02/2020"));
        eventos.add(new DadosEvento("Empoderamento feminino no nado", "SP", "20/02/2020"));
        eventos.add(new DadosEvento("Empoderamento feminino no automobilismo", "SP", "20/02/2020"));

        return eventos;
    }

    @Override
    public void enviaEvento(DadosEvento dadosEvento) {
        Fragment fragment = new EventoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EVENTO_CHAVE, dadosEvento);
        fragment.setArguments(bundle);

        replaceFragment(fragment);
    }
}