package br.com.elasnojogo.Views.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.com.elasnojogo.Interface.EventoListener;
import br.com.elasnojogo.Model.Evento;
import br.com.elasnojogo2.R;

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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.enviarEvento(dadosEvento);
            }
        });
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

            image = itemView.findViewById(R.id.circleImageView);
            nomeEvento = itemView.findViewById(R.id.text_view_nomeevento);
            localEvento = itemView.findViewById(R.id.text_view_local);
            dataEvento = itemView.findViewById(R.id.text_view_data);
        }

        public void onBind(Evento evento) {
            nomeEvento.setText(evento.getNomeEvento());
            localEvento.setText(evento.getLocal());
            dataEvento.setText(evento.getData());
        }
    }
}