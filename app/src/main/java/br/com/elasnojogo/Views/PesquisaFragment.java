package br.com.elasnojogo.Views;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;
import br.com.elasnojogo.Interface.EventoListener;
import br.com.elasnojogo.Model.Evento;
import br.com.elasnojogo.Views.adapter.EventoRecyclerViewAdapter;
import br.com.elasnojogo2.R;

public class PesquisaFragment extends Fragment implements EventoListener {
    private LinearLayout favorito;
    EventoListener mListener;
    public static final String EVENTO_CHAVE = "evento";
    private RecyclerView recyclerViewFavorito;
    private EventoRecyclerViewAdapter adapter;


    public PesquisaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pesquisa, container, false);

        recyclerViewFavorito = view.findViewById(R.id.recycler_view_favoritos);
        adapter = new EventoRecyclerViewAdapter(getListaEventos(), this);
        recyclerViewFavorito.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewFavorito.setLayoutManager(layoutManager);

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private List<Evento> getListaEventos() {
        List<Evento> eventos = new ArrayList<>();
        return eventos;
    }

    @Override
    public void enviarEvento(Evento evento) {
        Fragment fragment = new VisualizarEvento();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EVENTO_CHAVE, evento);
        fragment.setArguments(bundle);

        replaceFragment(fragment);
    }
}