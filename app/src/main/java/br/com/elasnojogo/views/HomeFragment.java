package br.com.elasnojogo.views;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import br.com.elasnojogo.model.Evento;
import br.com.elasnojogo.repository.data.EventosDAO;
import br.com.elasnojogo.repository.data.EventosDataBase;
import br.com.elasnojogo.views.interfaces.EventoListener;
import br.com.elasnojogo.model.Sport;
import br.com.elasnojogo.viewModel.SportsViewModel;
import br.com.elasnojogo.views.adapter.EventoRecyclerViewAdapter;
import br.com.elasnojogo.views.adapter.SportRecyclerViewAdapter;
import br.com.elasnojogo2.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static br.com.elasnojogo.constantes.Constantes.EVENTO_CHAVE;

public class HomeFragment extends Fragment implements EventoListener {

    private RecyclerView recyclerViewEventos;
    private RecyclerView recyclerViewSports;
    private SportsViewModel sportsViewModel;
    private List<Sport> results = new ArrayList<>();
    private SportRecyclerViewAdapter sportRecyclerViewAdapter;
    private Button buttonCriarEvento;
    private TextView saudacao;
    private List<Evento> listaEventos = new ArrayList<>();
    private EventoRecyclerViewAdapter adapter;
    public EventosDAO eventosDAO;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(view);

        eventosDAO = EventosDataBase.getDataBase(getContext()).eventosDAO();
        buscarTodosEventos();
        recyclerViewEventos = view.findViewById(R.id.recycler_view_eventos);
        adapter = new EventoRecyclerViewAdapter(listaEventos, this);
        recyclerViewEventos.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewEventos.setLayoutManager(layoutManager);
        deixaNomeBold();

        sportsViewModel.getListSports();
        sportsViewModel.listLiveData.observe(getViewLifecycleOwner(), results1 -> sportRecyclerViewAdapter.setUpdate(results1));

        recyclerViewSports.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewSports.setAdapter(sportRecyclerViewAdapter);
        buttonCriarEvento.setOnClickListener(view12 -> replaceFragment(new br.com.elasnojogo.views.CriarEventoFragment()));

        return view;
    }

    private void deixaNomeBold() {
        String normalText = getString(R.string.ola);
        String boldText = getString(R.string.nome);
        SpannableString str = new SpannableString(normalText + boldText);
        str.setSpan(new StyleSpan(Typeface.BOLD), 5, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        saudacao.setText(str);
    }

    private void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void initViews(View view) {
        buttonCriarEvento = view.findViewById(R.id.criarevento_btn);
        recyclerViewEventos = view.findViewById(R.id.recycler_view_eventos);
        saudacao = view.findViewById(R.id.textViewSaudacao);
        recyclerViewSports = view.findViewById(R.id.recycler_view_Sports);
        sportsViewModel = ViewModelProviders.of(this).get(SportsViewModel.class);
        sportRecyclerViewAdapter = new SportRecyclerViewAdapter(results);
    }

    @Override
    public void enviarEvento(Evento evento) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EVENTO_CHAVE, evento);

        Fragment detalheFragment = new br.com.elasnojogo.views.VisualizarEvento();
        detalheFragment.setArguments(bundle);
        replaceFragment(detalheFragment);
    }

    public void buscarTodosEventos() {
        eventosDAO.retornaEventos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(produtos -> {
                            adapter.atualizaListaEvento(produtos);
                        },
                        throwable -> {
                            Log.i("TAG", "método getAllEventos" + throwable.getMessage());
                        });
    }
}