package br.com.elasnojogo.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.elasnojogo.model.Evento;
import br.com.elasnojogo.viewModel.EventoViewModel;
import br.com.elasnojogo.views.adapter.EventoRecyclerViewAdapter;
import br.com.elasnojogo.repository.data.EventosDAO;
import br.com.elasnojogo.repository.data.EventosDataBase;
import br.com.elasnojogo2.R;
import static br.com.elasnojogo2.R.string.preencha_campo;

public class CriarEventoFragment extends Fragment {
    private TextView criarEvento;
    private TextView segurancaEvento;
    private ImageView imageViewLogo;
    private Button inserirImagem;
    private TextInputEditText nomeInputEvento;
    private TextInputEditText tipoInputEvento;
    private TextInputEditText localInputEvento;
    private TextInputEditText dataInputEvento;
    private TextInputEditText horarioInputEvento;
    private EventosDAO eventosDAO;
    private Button cadastrarEvento;
    private List<Evento> listaEvento = new ArrayList<>();
    private EventoViewModel viewModel;
    private EventoRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_criar_evento, container, false);
        eventosDAO = EventosDataBase.getDataBase(getContext()).eventosDAO();
        initViews(view);

        cadastrarEvento.setOnClickListener(v -> {
            String nomeEvento = nomeInputEvento.getText().toString();
            String local = localInputEvento.getText().toString();
            String data = dataInputEvento.getText().toString();
            String horario = horarioInputEvento.getText().toString();
            String categoriaEsportes = tipoInputEvento.getText().toString();

            validarCampos(nomeEvento, local, data, horario, categoriaEsportes);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Evento evento = new Evento(nomeEvento, data, horario, local, categoriaEsportes);
                    if (evento != null) {
                        eventosDAO.inserirEventos(evento);
                    }
                }
            }).start();
        });

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void initViews(View view) {
        viewModel = ViewModelProviders.of(this).get(EventoViewModel.class);
        imageViewLogo = view.findViewById(R.id.imagemLogo);
        tipoInputEvento = view.findViewById(R.id.categoria_evento);
        dataInputEvento = view.findViewById(R.id.horario_evento);
        localInputEvento = view.findViewById(R.id.local_do_evento);
        horarioInputEvento = view.findViewById(R.id.horario_evento);
        cadastrarEvento = view.findViewById(R.id.buttonCadastrarEvento);
        inserirImagem = view.findViewById(R.id.inserirImagem);
        criarEvento = view.findViewById(R.id.criareventoText);
        nomeInputEvento = view.findViewById(R.id.nome_do_evento);
        segurancaEvento = view.findViewById(R.id.segurancaEvento);
    }

    private boolean validarCampos(String nomeEvento, String dataEvento, String horaEvento, String localEvento, String categoriaEvento) {
        if (nomeEvento.isEmpty() && dataEvento.isEmpty() && horaEvento.isEmpty() && localEvento.isEmpty() && categoriaEvento.isEmpty()) {
            nomeInputEvento.setError(getString(preencha_campo));
            dataInputEvento.setError(getString(preencha_campo));
            localInputEvento.setError(getString(preencha_campo));
            horarioInputEvento.setError(getString(preencha_campo));
            tipoInputEvento.setError(getString(preencha_campo));

            return false;
        }
            if (nomeEvento.length() <= 12) {
                nomeInputEvento.setError("Nome do evento deve conter até 12 caracteres");
                nomeInputEvento.requestFocus();
                return false;
        } else {
            Fragment mudar = new MeusEventosFragment();
            replaceFragment(mudar);
            return true;
        }
    }
}