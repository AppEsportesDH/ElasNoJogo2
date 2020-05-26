package br.com.elasnojogo.views;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.elasnojogo.model.Evento;
import br.com.elasnojogo.repository.data.EventosDAO;
import br.com.elasnojogo.repository.data.EventosDataBase;
import br.com.elasnojogo.util.AppUtil;
import br.com.elasnojogo.viewModel.EventoViewModel;
import br.com.elasnojogo.views.adapter.EventoRecyclerViewAdapter;
import br.com.elasnojogo2.R;
import pl.aprilapps.easyphotopicker.EasyImage;

import static android.content.Context.MODE_PRIVATE;
import static br.com.elasnojogo.constantes.Constantes.MULHER_BI;
import static br.com.elasnojogo.constantes.Constantes.MULHER_LES;
import static br.com.elasnojogo.constantes.Constantes.MULHER_NAOBI;
import static br.com.elasnojogo.constantes.Constantes.MULHER_TRANS;
import static br.com.elasnojogo2.R.id.checkTrans;
import static br.com.elasnojogo2.R.id.checklbi;
import static br.com.elasnojogo2.R.id.checkles;
import static br.com.elasnojogo2.R.string.preencha_campo;
import static com.facebook.FacebookSdk.getApplicationContext;

public class CriarEventoFragment extends Fragment {
    private static final int PERMISSION_CODE = 100;
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
    CheckBox ch, ch1, ch2, ch3;
    private String identificacao;
    private EventoRecyclerViewAdapter adapter;
    private InputStream stream = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_criar_evento, container, false);
        eventosDAO = EventosDataBase.getDataBase(getContext()).eventosDAO();
        initViews(view);

        inserirImagem.setOnClickListener(v -> {
            captureImage();
            salvarImagemFirebase(stream, "foto_evento");
        });

        cadastrarEvento.setOnClickListener(v -> {
            String nomeEvento = nomeInputEvento.getText().toString();
            String local = localInputEvento.getText().toString();
            String data = dataInputEvento.getText().toString();
            String horario = horarioInputEvento.getText().toString();
            String categoriaEsportes = tipoInputEvento.getText().toString();

            validarCampos(nomeEvento, local, data, horario, categoriaEsportes);

            onCheckboxClicked(ch);
            onCheckboxClicked(ch1);
            onCheckboxClicked(ch2);
            onCheckboxClicked(ch3);

            Evento evento = new Evento(nomeEvento, data, horario, local,identificacao, categoriaEsportes);
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

    private void captureImage() {
        int permissionCamera = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
        int permissionStorage = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCamera == PackageManager.PERMISSION_GRANTED && permissionStorage == PackageManager.PERMISSION_GRANTED) {
//            EasyImage.openCameraForImage(this, MODE_PRIVATE);
            EasyImage.openChooserWithGallery((Activity) getContext(), "Escolha a imagem", MODE_PRIVATE);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CODE);
        }
    }

    private void salvarImagemFirebase(InputStream stream, String nomeFotoEvento) {
        StorageReference storage = FirebaseStorage
                .getInstance()
                .getReference()
                .child(AppUtil.getIdUsuario(getContext()) + "/image/perfil/" + nomeFotoEvento);

        if (stream == null) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            return;
        }

        UploadTask uploadTask = storage.putStream(stream);

        uploadTask.addOnSuccessListener(taskSnapshot -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));

        }).addOnFailureListener(e -> {

            Snackbar snackbar = Snackbar.make(inserirImagem, e.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
        });
    }

}