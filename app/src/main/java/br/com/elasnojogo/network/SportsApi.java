package br.com.elasnojogo.network;

import br.com.elasnojogo.model.SportsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface SportsApi {

    @GET("all_sports.php")
    Observable<SportsResponse> getSportsResponse();
}