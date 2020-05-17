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
import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilFragment extends Fragment {
    private CircleImageView imageView;
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
        StorageReference storage = FirebaseStorage
                .getInstance()
                .getReference()
                .child(AppUtil.getIdUsuario(getContext()) +  "/image/perfil/nome_perfil");

        storage.getDownloadUrl()
                .addOnSuccessListener(uri -> {

                    Picasso.get()
                            .load(uri)
                            .rotate(90)
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