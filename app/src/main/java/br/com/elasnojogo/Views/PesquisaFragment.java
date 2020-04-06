package br.com.elasnojogo.Views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.elasnojogo.Model.DadosEvento;
import br.com.elasnojogo.Model.Evento;
import br.com.elasnojogo.Views.adapter.FavoritoRecyclerViewAdapter;
import br.com.elasnojogo2.R;


public class PesquisaFragment extends AppCompatActivity {
    private FavoritoRecyclerViewAdapter adapter;
    private List<Evento> eventoList;
    private List<Evento> eventoListFull;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_favoritos_recycler_view);
        getListaEventos();
        visualizarEvento();
    }

    private void getListaEventos() {
        List<DadosEvento> eventos = new ArrayList<>();
        eventos.add(new DadosEvento(R.drawable.futebol,"Fut das Migas", "Local: Digital House, SP", "Data: 20/10/2021"));
        eventos.add(new DadosEvento(R.drawable.volei,"Liga de Volei Feminino", "Local: Avenida Paulista, 123", "Data: 21/01/2022"));
        eventos.add(new DadosEvento(R.drawable.corrida,"Corrida na ZN", "Local: Avenida do Estado", "Data: 22/02/2023"));
    }

    @Override
    public void visualizarEvento() {
        RecyclerView recyclerView = findViewById(R.id.)// continuar vídeo principal-
        // necessário entender qual é o recyclerView

    }

}