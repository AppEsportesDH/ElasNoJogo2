package br.com.elasnojogo.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;
import br.com.elasnojogo.Model.DadosEvento;
import br.com.elasnojogo.Interface.EventoView;
import br.com.elasnojogo.Model.Evento;
import br.com.elasnojogo.ViewModel.EventoViewModel;
import br.com.elasnojogo.Views.adapter.EventoRecyclerViewAdapter;
import br.com.elasnojogo2.R;

public class EventoFragment extends Fragment implements EventoView {

    private ImageView imageViewLogo;
    private EditText nomeEvento;
    private EditText textdata;
    private EditText textHorario;
    private EditText textLocal;
    private EditText textCategoria;
    private Button inserirImagem;
    private CheckBox mulherBi;
    private CheckBox  mulherTrans;
    private CheckBox mulherLes;
    private CheckBox pessoaNaoBinaria;
    private Button cadastrarEvento;
    private TextView criarEventoText;
    private TextView segurnacaEvento;
    private List<Evento> listaEvento = new ArrayList<>();
    private EventoViewModel viewModel;
    private EventoRecyclerViewAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_criarevento, container, false);

        initViews(view);

        return view;

    }

    @Override
    public void salvarEvento(DadosEvento comunicador) {
        // TODO: 15/02/2020 falta adicionar banco de dados;
    }

    private void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void initViews(View view) {
        imageViewLogo = view.findViewById(R.id.imageView5);
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
}