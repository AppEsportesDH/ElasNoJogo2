package br.com.elasnojogo.Views;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import br.com.elasnojogo.Interface.EventoListener;
import br.com.elasnojogo.Model.Evento;
import br.com.elasnojogo.Model.Sport;
import br.com.elasnojogo.ViewModel.BuscarEventos;
import br.com.elasnojogo.ViewModel.SportsViewModel;
import br.com.elasnojogo.Views.adapter.EventoRecyclerViewAdapter;
import br.com.elasnojogo.Views.adapter.SportRecyclerViewAdapter;
import br.com.elasnojogo.data.EventosDAO;
import br.com.elasnojogo.data.EventosDataBase;
import br.com.elasnojogo2.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment implements EventoListener {
    private RecyclerView recyclerViewEventos;
    private RecyclerView recyclerViewSports;
    private SportsViewModel sportsViewModel;
    private List<Sport> results = new ArrayList<>();
    private SportRecyclerViewAdapter sportRecyclerViewAdapter;
    private Button buttonCriarEvento;
    private TextView saudacao;
    public static final String EVENTO_CHAVE = "evento";
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
        buttonCriarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new CriarEventoFragment());
            }
        });
        return view;
    }

    private void deixaNomeBold() {
        String normalText = "Olá, ";
        String boldText = "Nome";
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

        Fragment detalheFragment = new VisualizarEvento();
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