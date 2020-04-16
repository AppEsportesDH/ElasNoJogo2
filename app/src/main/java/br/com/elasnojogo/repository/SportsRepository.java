package br.com.elasnojogo.repository;

import br.com.elasnojogo.model.SportsResponse;
import io.reactivex.Observable;

import static br.com.elasnojogo.network.ServiceRetrofit.getApiService;

public class SportsRepository {
    public Observable<SportsResponse> sportsResponseObservable(){
        return getApiService().getSportsResponse();
    }
}