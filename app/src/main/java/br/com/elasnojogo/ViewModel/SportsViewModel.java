package br.com.elasnojogo.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.elasnojogo.Model.Sport;
import br.com.elasnojogo.Repository.SportsRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SportsViewModel extends AndroidViewModel {

    private SportsRepository repository = new SportsRepository();

    private MutableLiveData<List<Sport>> listMutableLiveData = new MutableLiveData<>();
    public LiveData<List<Sport>> listLiveData = listMutableLiveData;

    private MutableLiveData<String> mutableLiveDataErro = new MutableLiveData<>();
    public LiveData<String> liveDataErro = mutableLiveDataErro;

    private CompositeDisposable disposable = new CompositeDisposable();
    private SportsRepository respository = new SportsRepository();

    public SportsViewModel(@NonNull Application application) {
        super(application);
    }

    public void getListSports(){
        disposable.add(
                respository.sportsResponseObservable()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(sportsResult ->
                                        listMutableLiveData.setValue(sportsResult.getSports()),
                                throwable -> {
                                    mutableLiveDataErro.setValue(throwable.getMessage());
                                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
