package br.com.elasnojogo.Network;

import br.com.elasnojogo.Model.SportsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface SportsApi {

    @GET("all_sports.php")
    Observable<SportsResponse> getSportsResponse();
}