package br.com.elasnojogo.Views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.elasnojogo.Views.interfaces.OnClick;
import br.com.elasnojogo.Model.Sport;
import br.com.elasnojogo2.R;

public class SportRecyclerViewAdapter extends RecyclerView.Adapter<SportRecyclerViewAdapter.ViewHolder> {

    private List<Sport> resultList;
    private OnClick listener;

  public SportRecyclerViewAdapter(List<Sport> resultList, OnClick listener) {
  this.resultList = resultList;
  this.listener = listener;
  }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sports_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Sport result = resultList.get(position);
        holder.onBind(result);

        holder.itemView.setOnClickListener(v -> listener.click(result));

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void setUpdate(List<Sport> results) {
        if (this.resultList.isEmpty()) {
            this.resultList = results;
        } else {
            this.resultList.addAll(results);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textViewSport);
            img = itemView.findViewById(R.id.imageViewSport);
        }

        public void onBind(Sport result) {
            nome.setText(result.getStrSport());
            Picasso.get().load(result.getStrSportThumb()).into(img);
        }
    }
}


