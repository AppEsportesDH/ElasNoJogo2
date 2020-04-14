package br.com.elasnojogo.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import br.com.elasnojogo.Model.Evento;
import br.com.elasnojogo.Repository.EventoRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class EventoViewModel extends AndroidViewModel {

    private EventoRepository repository = new EventoRepository();
    private CompositeDisposable disposable = new CompositeDisposable();

    private MutableLiveData<List<Evento>> mutableLiveDataAlbum = new MutableLiveData<>();
    public LiveData<List<Evento>> listLiveData= mutableLiveDataAlbum;

    private MutableLiveData<String> mutableLiveDataErro = new MutableLiveData<>();
    public LiveData<String> liveDataErro = mutableLiveDataErro;

    private MutableLiveData<Boolean> mutableLiveDataLoading = new MutableLiveData<>();
    public LiveData<Boolean> liveDataLoading = mutableLiveDataLoading;

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();


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
                                        mutableLiveDataAlbum.setValue(eventoList),
                                throwable ->
                                        mutableLiveDataErro.setValue(throwable.getMessage() + "problema banco de dados"))
        );
    }
    private void insereDadosBd(Evento evento) {
        repository.inserirEventos(evento, getApplication());
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
