package br.com.elasnojogo.Views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import br.com.elasnojogo.Interface.FavoritosView;
import br.com.elasnojogo.Model.DadosEvento;
import br.com.elasnojogo.Model.Evento;
import br.com.elasnojogo2.R;


public class PesquisaRecyclerViewAdapter extends RecyclerView.Adapter<PesquisaRecyclerViewAdapter.ViewHolder> {

    private List<DadosEvento> eventos;
    private List<DadosEvento> eventosFull;

    public PesquisaRecyclerViewAdapter(List<DadosEvento> listaEventos) {
        this.eventos = listaEventos;
        eventosFull = new ArrayList<>(eventos);
    }

    @NonNull
    @Override
    public PesquisaRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PesquisaRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.Bind(DadosEvento);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView nomeEvento;
        public TextView localEvento;
        public TextView dataEvento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageView_favoritos);
            nomeEvento = itemView.findViewById(R.id.text_view_nomeevento);
            localEvento = itemView.findViewById(R.id.text_view_local);
            dataEvento = itemView.findViewById(R.id.text_view_data);

        }
    }
}