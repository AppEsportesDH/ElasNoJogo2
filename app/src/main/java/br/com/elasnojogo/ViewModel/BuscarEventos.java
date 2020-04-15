package br.com.elasnojogo.ViewModel;

import android.util.Log;

import br.com.elasnojogo.Views.adapter.EventoRecyclerViewAdapter;
import br.com.elasnojogo.data.EventosDAO;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BuscarEventos {
    public EventosDAO eventosDAO;
    private EventoRecyclerViewAdapter adapter;

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
