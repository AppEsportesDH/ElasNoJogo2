package br.com.elasnojogo.Repository;

import android.content.Context;

import java.util.List;

import br.com.elasnojogo.Model.Evento;
import br.com.elasnojogo.data.EventosDataBase;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class EventoRepository {

    public void inserirEventos(Evento evento, Context context) {
        EventosDataBase.getDataBase(context).eventosDAO().inserirEventos(evento);
    }

    public void deletarEventos(Evento evento, Context context) {
        EventosDataBase.getDataBase(context).eventosDAO().deletarEventos(evento);
    }

    public void upateEvento(Evento evento, Context context) {
        EventosDataBase.getDataBase(context).eventosDAO().updateEventos(evento);
    }

    public Flowable<List<Evento>> retornarEventos(Context context) {
        return EventosDataBase.getDataBase(context).eventosDAO().retornaEventos();
    }
}
