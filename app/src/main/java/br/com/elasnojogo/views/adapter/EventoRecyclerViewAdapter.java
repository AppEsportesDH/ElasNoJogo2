package br.com.elasnojogo.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import br.com.elasnojogo.model.Evento;
import br.com.elasnojogo.views.interfaces.EventoListener;
import br.com.elasnojogo2.R;

import static br.com.elasnojogo.util.AppUtil.loadImageEventoFromFirebase;

public class EventoRecyclerViewAdapter extends RecyclerView.Adapter<EventoRecyclerViewAdapter.ViewHolder> {
    private List<Evento> eventos;
    private EventoListener listener;

    public EventoRecyclerViewAdapter(List<Evento> listaEventos, EventoListener listener) {
        this.eventos = listaEventos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_eventos_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Evento dadosEvento = eventos.get(position);
        holder.onBind(dadosEvento);
        holder.itemView.setOnClickListener(v -> listener.enviarEvento(dadosEvento));
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public void atualizaListaEvento(List<Evento> listaEventos) {
        this.eventos.clear();
        this.eventos = listaEventos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView nomeEvento;
        private TextView localEvento;
        private TextView dataEvento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageView_evento);
            nomeEvento = itemView.findViewById(R.id.nome_evento_visualizarfragment);
            localEvento = itemView.findViewById(R.id.local_visualizarfragment);
            dataEvento = itemView.findViewById(R.id.data_visualizarfragment);
        }

        public void onBind(Evento evento) {
            loadImageEventoFromFirebase(itemView.getContext(),image);
            nomeEvento.setText(evento.getNomeEvento());
            localEvento.setText(evento.getLocal());
            dataEvento.setText(evento.getData());
        }
    }
}