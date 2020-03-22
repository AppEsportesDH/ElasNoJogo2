package br.com.elasnojogo.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.elasnojogo.Interface.FavoritosView;
import br.com.elasnojogo.Model.DadosEvento;
import br.com.elasnojogo.Views.adapter.FavoritoRecyclerViewAdapter;
import br.com.elasnojogo2.R;


/**
 * A simple {@link Fragment} subclass.
 */

public class FavoritosFragment extends Fragment implements FavoritosView {

    private LinearLayout favorito;
    FavoritosView mListener;
    public static final String EVENTO_CHAVE = "evento";
    private RecyclerView recyclerViewFavorito;
    private FavoritoRecyclerViewAdapter adapter;
    public FavoritosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        recyclerViewFavorito = view.findViewById(R.id.recycler_view_favoritos);

        adapter = new FavoritoRecyclerViewAdapter(getListaEventos(), this);

        recyclerViewFavorito.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewFavorito.setLayoutManager(layoutManager);

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private List<DadosEvento> getListaEventos() {
        List<DadosEvento> eventos = new ArrayList<>();

        eventos.add(new DadosEvento(R.drawable.futebol,"Fut das Migas", "Digital House - São Paulo", "20/10/2021"));
        eventos.add(new DadosEvento(R.drawable.volei,"Liga de Volei Feminino", "Avenida Paulista, 123", "21/01/2022"));
        eventos.add(new DadosEvento(R.drawable.corrida,"Corrida na ZN", "Avenida do Estado", "22/02/2023"));

        return eventos;
    }

    @Override
    public void visualizarEvento(DadosEvento dadosEvento) {
        Fragment fragment = new EventoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EVENTO_CHAVE, dadosEvento);
        fragment.setArguments(bundle);

        replaceFragment(fragment);
    }
}