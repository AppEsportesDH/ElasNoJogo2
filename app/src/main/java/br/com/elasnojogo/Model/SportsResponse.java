package br.com.elasnojogo.Model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SportsResponse {

    @SerializedName("sports")
    private List<Sport> mSports;
    @Expose
    private List<Sport> results;

    public List<Sport> getSports() {
        return mSports;
    }

    public void setSports(List<Sport> sports) {
        mSports = sports;
    }

    public List<Sport> getResults() {
        return results;
    }

    public void setResults(List<Sport> results) {
        this.results = results;
    }
}