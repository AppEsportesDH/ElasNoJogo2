package br.com.elasnojogo.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import br.com.elasnojogo.Model.DadosEvento;
import br.com.elasnojogo.ViewModel.EventoViewModel;
import br.com.elasnojogo.Views.interfaces.EventoListener;
import br.com.elasnojogo2.R;

public class EventoFragment extends Fragment implements EventoListener {

    private EditText nomeEvento;
    private EditText textdata;
    private EditText textHorario;
    private EditText textLocal;
    private EditText textCategoria;
    private Button inserirImagem;
    private CheckBox mulherBi;
    private CheckBox mulherTrans;
    private CheckBox mulherLes;
    private CheckBox pessoaNaoBinaria;
    private Button cadastrarEvento;
    private TextView criarEventoText;
    private TextView segurnacaEvento;
    private EventoViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_criarevento, container, false);
        initViews(view);
        return view;

    }

    private void initViews(View view) {
        nomeEvento = view.findViewById(R.id.edit_text_nomeEvento);
        textdata = view.findViewById(R.id.editText_data);
        textHorario = view.findViewById(R.id.editText_horário);
        textLocal = view.findViewById(R.id.editText_local);
        textCategoria = view.findViewById(R.id.editText_categoria);
        mulherLes = view.findViewById(R.id.checkBox_les);
        mulherBi = view.findViewById(R.id.checkBox_bi);
        mulherTrans= view.findViewById(R.id.checkBox_trans);
        pessoaNaoBinaria= view.findViewById(R.id.checkBox_naobi);
        cadastrarEvento = view.findViewById(R.id.botao_cadastro);
        criarEventoText = view.findViewById(R.id.criarevento);
        segurnacaEvento = view.findViewById(R.id.segurança_evento);
        inserirImagem = view.findViewById(R.id.inserir_imagem_btn);
        viewModel = ViewModelProviders.of(this).get(EventoViewModel.class);
    }

    @Override
    public void enviaEvento(DadosEvento dadosEvento) {

    }
}