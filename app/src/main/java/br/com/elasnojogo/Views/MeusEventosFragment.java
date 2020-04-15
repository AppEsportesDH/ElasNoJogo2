package br.com.elasnojogo.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.elasnojogo.Interface.EventoListener;
import br.com.elasnojogo.Model.Evento;
import br.com.elasnojogo.Views.adapter.EventoRecyclerViewAdapter;
import br.com.elasnojogo.data.EventosDAO;
import br.com.elasnojogo.data.EventosDataBase;
import br.com.elasnojogo2.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MeusEventosFragment extends Fragment implements EventoListener {

    private List<Evento> listaEventos = new ArrayList<>();
    private EventoRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    public EventosDAO eventosDAO;

    public MeusEventosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meus_eventos, container, false);
        eventosDAO = EventosDataBase.getDataBase(getContext()).eventosDAO();

        buscarTodosEventos();
        recyclerView = view.findViewById(R.id.recycler_view_favoritos);
        adapter = new EventoRecyclerViewAdapter(listaEventos, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public void enviarEvento(Evento evento) {
    }

    private void buscarTodosEventos() {
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