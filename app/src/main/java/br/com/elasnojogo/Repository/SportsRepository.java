package br.com.elasnojogo.Repository;

import br.com.elasnojogo.Model.SportsResponse;
import io.reactivex.Observable;

import static br.com.elasnojogo.Network.ServiceRetrofit.getApiService;

public class SportsRepository {
    public Observable<SportsResponse> sportsResponseObservable(){
        return getApiService().getSportsResponse();
    }
}