package br.com.elasnojogo.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.elasnojogo.model.Sport;
import br.com.elasnojogo.repository.SportsRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SportsViewModel extends ViewModel {

    private MutableLiveData<List<Sport>> listMutableLiveData = new MutableLiveData<>();
    public LiveData<List<Sport>> listLiveData = listMutableLiveData;

    private MutableLiveData<String> mutableLiveDataErro = new MutableLiveData<>();
    public LiveData<String> liveDataErro = mutableLiveDataErro;

    private CompositeDisposable disposable = new CompositeDisposable();
    private SportsRepository respository = new SportsRepository();

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
