package br.com.elasnojogo.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.appevents.suggestedevents.ViewOnClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import br.com.elasnojogo.util.AppUtil;
import br.com.elasnojogo2.R;

public class PerfilFragment extends Fragment {
    private ImageView imageView;
    private TextInputEditText nomePerfil;
    private TextInputEditText emailPerfil;
    private Button salvar;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        initViews(view);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromFirebase();
            }
        });

        loadImageFromFirebase();
        FirebaseUser usuaria = firebaseAuth.getCurrentUser();
        inserirInfosFirebase(usuaria);
    }

    private void inserirInfosFirebase(FirebaseUser usuaria){

        if (usuaria != null){
            Picasso.get().load(usuaria.getPhotoUrl()).into(imageView);
            nomePerfil.setText(usuaria.getDisplayName());
            emailPerfil.setText(usuaria.getEmail());
        } else{
            Toast.makeText(getContext(), "Erro ao carregar informações da usuária!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadImageFromFirebase() {
        // Pegamos a referencia do storage para pergar a foto do usuário
        StorageReference storage = FirebaseStorage
                .getInstance()
                .getReference()
                .child(AppUtil.getIdUsuario(getContext()) + "/image/profile/imagem-perfil");

        // Pegamos a url da imagem para o Picasso poder carregar a foto
        storage.getDownloadUrl()
                .addOnSuccessListener(uri -> {

                    // Mandamos o Picasso carregar a imagem com a url que veio d firebase
                    Picasso.get()
                            .load(uri)
                            .rotate(90) // Rotaciono a imagem em 90º
                            .into(imageView);
                });
    }

    private void initViews(View view) {
        imageView = view.findViewById(R.id.img_usuariaPerfil);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        nomePerfil = view.findViewById(R.id.edit_text_nome_perfil);
        emailPerfil = view.findViewById(R.id.edit_text_email_perfil);
        salvar = view.findViewById(R.id.salvar_btn);

    }
}