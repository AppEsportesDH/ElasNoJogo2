package br.com.elasnojogo.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

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
    public EditText nomeImputEvento;
    public EditText tipoImputEvento;
    public EditText localImputEvento;
    public EditText dataImputEvento;
    public EditText horarioImputEvento;
    private EventosDAO eventosDAO;
    private Button cadastrarEvento;
    private ArrayList<String> opções = new ArrayList<>();
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
            String nomeEvento = nomeImputEvento.getText().toString();
            String local = localImputEvento.getText().toString();
            String data = dataImputEvento.getText().toString();
            String horario = horarioImputEvento.getText().toString();
            String categoriaEsportes = tipoImputEvento.getText().toString();

            validarCampos(nomeEvento, local, data, horario, categoriaEsportes);

            new Thread(() -> {
                Evento evento = new Evento(nomeEvento, data, horario, local, categoriaEsportes);
                if (evento != null) {
                    eventosDAO.inserirEventos(evento);
                }


            }).start();
        });

        return view;

    }

    private void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void initViews(View view) {
        imageViewLogo = view.findViewById(R.id.imagemLogo);
        viewModel = ViewModelProviders.of(this).get(EventoViewModel.class);
        tipoImputEvento = view.findViewById(R.id.textInputCategoria);
        dataImputEvento = view.findViewById(R.id.textDataDoEvento);
        localImputEvento = view.findViewById(R.id.textLocalDoEvento);
        horarioImputEvento = view.findViewById(R.id.textHorarioDoEvento);
        cadastrarEvento = view.findViewById(R.id.buttonCadastrarEvento);
        CheckBox checkNaoBi = view.findViewById(R.id.check_naobi);
        CheckBox checkbi = view.findViewById(R.id.checklbi);
        CheckBox checkTrans = view.findViewById(R.id.checkTrans);
        CheckBox checkLes = view.findViewById(R.id.checkles);
        inserirImagem = view.findViewById(R.id.inserirImagem);
        criarEvento = view.findViewById(R.id.criareventoText);
        nomeImputEvento = view.findViewById(R.id.textNomeEvento);
        segurancaEvento = view.findViewById(R.id.segurancaEvento);
    }

    private boolean validarCampos(String nomeEvento, String dataEvento, String horaEvento, String localEvento, String categoriaEvento) {
        if (nomeEvento.isEmpty() && dataEvento.isEmpty() && horaEvento.isEmpty() && localEvento.isEmpty() && categoriaEvento.isEmpty()) {
            nomeImputEvento.setError(getString(preencha_campo));
            dataImputEvento.setError(getString(preencha_campo));
            localImputEvento.setError(getString(preencha_campo));
            horarioImputEvento.setError(getString(preencha_campo));
            tipoImputEvento.setError(getString(preencha_campo));
            return false;
        } else {
            Fragment mudar = new MeusEventosFragment();
            replaceFragment(mudar);
            return true;
        }
    }
}