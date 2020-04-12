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

import static br.com.elasnojogo.constantes.Constantes.EVENTO_CHAVE;


public class MeusEventosFragment extends Fragment implements FavoritosView {

    private LinearLayout favorito;
    FavoritosView mListener;
    private RecyclerView recyclerViewFavorito;
    private FavoritoRecyclerViewAdapter adapter;


    public MeusEventosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meus_eventos, container, false);

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

        eventos.add(new DadosEvento(R.drawable.futebol,"Fut das Migas", "Local: Digital House, SP", "Data: 20/10/2021"));
        eventos.add(new DadosEvento(R.drawable.volei,"Liga de Volei Feminino", "Local: Avenida Paulista, 123", "Data: 21/01/2022"));
        eventos.add(new DadosEvento(R.drawable.corrida,"Corrida na ZN", "Local: Avenida do Estado", "Data: 22/02/2023"));

        return eventos;
    }

    @Override
    public void visualizarEvento(DadosEvento dadosEvento) {
        Fragment fragment = new VisualizarEvento();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EVENTO_CHAVE, dadosEvento);
        fragment.setArguments(bundle);

        replaceFragment(fragment);
    }

    private void initViews(View view) {

    }
}