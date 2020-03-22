package br.com.elasnojogo.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
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
import br.com.elasnojogo.Model.Sport;
import br.com.elasnojogo.ViewModel.SportsViewModel;
import br.com.elasnojogo.Views.adapter.EventoRecyclerViewAdapter;
import br.com.elasnojogo.Views.adapter.SportRecyclerViewAdapter;
import br.com.elasnojogo2.R;

public class HomeFragment extends Fragment implements EventoListener {

    private RecyclerView recyclerViewEventos;
    private RecyclerView recyclerViewSports;
    private EventoRecyclerViewAdapter adapter;

    private SportsViewModel sportsViewModel;
    private List<Sport> results = new ArrayList<>();
    private SportRecyclerViewAdapter sportRecyclerViewAdapter;
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


        sportsViewModel.getListSports();
        sportsViewModel.listLiveData.observe(getViewLifecycleOwner(), results1 -> sportRecyclerViewAdapter.setUpdate(results1));

        recyclerViewSports.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewSports.setAdapter(sportRecyclerViewAdapter);


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

        eventos.add(new DadosEvento(R.drawable.futebol,"Fut das Migas", "Digital House - São Paulo", "20/10/2021"));
        eventos.add(new DadosEvento(R.drawable.volei,"Liga de Volei Feminino", "Avenida Paulista, 123", "21/01/2022"));
        eventos.add(new DadosEvento(R.drawable.corrida,"Corrida na ZN", "Avenida do Estado", "22/02/2023"));

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

        recyclerViewSports = view.findViewById(R.id.recycler_view_Sports);
        sportsViewModel = ViewModelProviders.of(this).get(SportsViewModel.class);
        sportRecyclerViewAdapter = new SportRecyclerViewAdapter(results);
    }
}