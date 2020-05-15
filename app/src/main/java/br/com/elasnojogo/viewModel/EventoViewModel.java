package br.com.elasnojogo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.elasnojogo.model.Evento;
import br.com.elasnojogo.repository.EventoRepository;
import br.com.elasnojogo.util.AppUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventoViewModel extends AndroidViewModel {
    public MutableLiveData<Throwable> resultLiveDataError = new MutableLiveData<>();

    private EventoRepository repository = new EventoRepository();
    private CompositeDisposable disposable = new CompositeDisposable();

    private MutableLiveData<List<Evento>> mutableLiveDataAEvento = new MutableLiveData<>();
    public LiveData<List<Evento>> listLiveData = mutableLiveDataAEvento;

    private MutableLiveData<String> mutableLiveDataErro = new MutableLiveData<>();
    public LiveData<String> liveDataErro = mutableLiveDataErro;

    private MutableLiveData<Boolean> mutableLiveDataLoading = new MutableLiveData<>();
    public LiveData<Boolean> liveDataLoading = mutableLiveDataLoading;

    public EventoViewModel(@NonNull Application application) {
        super(application);
    }

    public void carregaDadosBD() {
        disposable.add(
                repository.retornarEventos(getApplication())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(subscription -> mutableLiveDataLoading.setValue(true))
                        .doAfterTerminate(() -> mutableLiveDataLoading.setValue(false))
                        .subscribe(eventoList ->
                                        mutableLiveDataAEvento.setValue(eventoList),
                                throwable ->
                                        mutableLiveDataErro.setValue(throwable.getMessage() + "problema banco de dados"))
        );
    }

    public void insereDadosBd(Evento evento) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (evento != null) {
                    repository.inserirEventos(evento, getApplication());
                }
            }
        }).start();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    public void salvarEventoFirebase(Evento evento) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference(AppUtil.getIdUsuario(getApplication()) + "/evento");

        reference.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean existe = false;

                for (DataSnapshot resultSnapshot : dataSnapshot.getChildren()) {
                    Evento firebaseUsuario = resultSnapshot.getValue(Evento.class);

                    if (firebaseUsuario != null &&
                            firebaseUsuario.getId() != null &&
                            firebaseUsuario.getId().equals(evento.getId())) {
                        existe = true;
                    }
                }

                if (existe) {
                    resultLiveDataError.setValue(new Throwable(evento + ": Já existe no Firebase"));
                } else {
                    salvarInfoEvento(reference, evento);
                }
            }

            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void salvarInfoEvento(DatabaseReference reference, Evento evento) {
        String key = reference.push().getKey();
        reference.child(key).setValue(evento);
    }
}
