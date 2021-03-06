package br.com.elasnojogo.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.elasnojogo.views.interfaces.EventoListener;
import br.com.elasnojogo.model.Evento;
import br.com.elasnojogo2.R;


public class PesquisaRecyclerViewAdapter extends RecyclerView.Adapter<PesquisaRecyclerViewAdapter.ViewHolder> {

    private List<Evento> eventos;

    private EventoListener listener;

    public PesquisaRecyclerViewAdapter(List<Evento> listaEventos, EventoListener listener) {
        this.eventos = listaEventos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.fragment_item_favoritos_recycler_view, parent, false);
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
    public int getItemCount() { return eventos.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView nomeEvento;
        private TextView localEvento;
        private TextView dataEvento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageView_favoritos);
            nomeEvento = itemView.findViewById(R.id.text_view_nomeevento);
            localEvento = itemView.findViewById(R.id.text_view_local);
            dataEvento = itemView.findViewById(R.id.text_view_data);
        }


        public void onBind(Evento dadosEvento) {
            nomeEvento.setText(dadosEvento.getNomeEvento());
            localEvento.setText(dadosEvento.getLocal());
            dataEvento.setText(dadosEvento.getData());
        }
    }
}