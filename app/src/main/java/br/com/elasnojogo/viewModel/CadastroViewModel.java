package br.com.elasnojogo.viewModel;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.elasnojogo.model.Usuario;
import br.com.elasnojogo.util.AppUtil;

import static android.content.Context.MODE_PRIVATE;


public class CadastroViewModel extends AndroidViewModel {
    public MutableLiveData<Throwable> resultLiveDataError = new MutableLiveData<>();

    public CadastroViewModel(@NonNull Application application) {
        super(application);
    }


    public void salvarInfoUsuario(Usuario usuario) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("/usuario" + AppUtil.getIdUsuario(getApplication()) + "/usuarios");

        reference.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean existe = false;

                for (DataSnapshot resultSnapshot : dataSnapshot.getChildren()) {
                    Usuario firebaseUsuario = resultSnapshot.getValue(Usuario.class);

                    if (firebaseUsuario != null &&
                            firebaseUsuario.getId() != null &&
                            firebaseUsuario.getId().equals(usuario.getId())) {
                        existe = true;
                    }
                }

                if (existe) {
                    resultLiveDataError.setValue(new Throwable(usuario + ": Já existe no Firebase"));
                } else {
                    salvarInfoUsuarioVerificado(reference, usuario);
                }
            }

            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void salvarInfoUsuarioVerificado(DatabaseReference reference, Usuario usuario) {
        String key = reference.push().getKey();

        reference.child(key).setValue(usuario);
    }
}
