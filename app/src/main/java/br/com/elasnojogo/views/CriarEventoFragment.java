package br.com.elasnojogo.views;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import br.com.elasnojogo.model.Evento;
import br.com.elasnojogo.viewModel.EventoViewModel;
import br.com.elasnojogo.repository.data.EventosDAO;
import br.com.elasnojogo.repository.data.EventosDataBase;
import br.com.elasnojogo2.R;

import static br.com.elasnojogo.constantes.Constantes.MULHER_BI;
import static br.com.elasnojogo.constantes.Constantes.MULHER_LES;
import static br.com.elasnojogo.constantes.Constantes.MULHER_NAOBI;
import static br.com.elasnojogo.constantes.Constantes.MULHER_TRANS;
import static br.com.elasnojogo2.R.id.checkTrans;
import static br.com.elasnojogo2.R.id.checklbi;
import static br.com.elasnojogo2.R.id.checkles;
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
    private EventoViewModel viewModel;
    CheckBox ch, ch1, ch2, ch3;
    private String identificacao;

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

            onCheckboxClicked(view);
            Log.i("CheckBos", identificacao);

            Evento evento = new Evento(nomeEvento, local, data, horario, categoriaEsportes, identificacao);
            viewModel.insereDadosBd(evento);
            viewModel.salvarEventoFirebase(evento);
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
        dataInputEvento = view.findViewById(R.id.data_evento);
        localInputEvento = view.findViewById(R.id.local_do_evento);
        horarioInputEvento = view.findViewById(R.id.horario_evento);
        cadastrarEvento = view.findViewById(R.id.buttonCadastrarEvento);
        inserirImagem = view.findViewById(R.id.inserirImagem);
        criarEvento = view.findViewById(R.id.criareventoText);
        nomeInputEvento = view.findViewById(R.id.nome_do_evento);
        segurancaEvento = view.findViewById(R.id.segurancaEvento);
        ch = (CheckBox) view.findViewById(checklbi);
        ch1 = (CheckBox) view.findViewById(R.id.check_naobi);
        ch2 = (CheckBox) view.findViewById(R.id.checkles);
        ch3 = (CheckBox) view.findViewById(R.id.checkTrans);
    }

    private boolean validarCampos(String nomeEvento, String dataEvento, String horaEvento, String localEvento, String categoriaEvento) {
        if (nomeEvento.isEmpty() && dataEvento.isEmpty() && horaEvento.isEmpty() && localEvento.isEmpty() && categoriaEvento.isEmpty()) {
            nomeInputEvento.setError(getString(preencha_campo));
            dataInputEvento.setError(getString(preencha_campo));
            localInputEvento.setError(getString(preencha_campo));
            horarioInputEvento.setError(getString(preencha_campo));
            tipoInputEvento.setError(getString(preencha_campo));
            return false;

        } else {
            Fragment mudar = new MeusEventosFragment();
            replaceFragment(mudar);
            return true;
        }
    }

    private void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (view.getId() == checklbi && checked) {
            identificacao = MULHER_BI;
        } else if (view.getId() == checkles && checked) {
            identificacao = MULHER_LES;
        } else if (view.getId() == checkTrans && checked) {
            identificacao = MULHER_TRANS;
        } else if (view.getId() == R.id.check_naobi && checked) {
            identificacao = MULHER_NAOBI;
        }
    }
}