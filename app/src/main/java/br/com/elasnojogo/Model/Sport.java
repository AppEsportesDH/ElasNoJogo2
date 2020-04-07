
package br.com.elasnojogo.Model;

import com.google.gson.annotations.SerializedName;

public class Sport {

    @SerializedName("idSport")
    private String mIdSport;
    @SerializedName("strFormat")
    private String mStrFormat;
    @SerializedName("strSport")
    private String mStrSport;
    @SerializedName("strSportDescription")
    private String mStrSportDescription;
    @SerializedName("strSportThumb")
    private String mStrSportThumb;
    @SerializedName("strSportThumbGreen")
    private String mStrSportThumbGreen;

    public String getIdSport() {
        return mIdSport;
    }

    public void setIdSport(String idSport) {
        mIdSport = idSport;
    }

    public String getStrFormat() {
        return mStrFormat;
    }

    public void setStrFormat(String strFormat) {
        mStrFormat = strFormat;
    }

    public String getStrSport() {
        return mStrSport;
    }

    public void setStrSport(String strSport) {
        mStrSport = strSport;
    }

    public String getStrSportDescription() {
        return mStrSportDescription;
    }

    public void setStrSportDescription(String strSportDescription) {
        mStrSportDescription = strSportDescription;
    }

    public String getStrSportThumb() {
        return mStrSportThumb;
    }

    public void setStrSportThumb(String strSportThumb) {
        mStrSportThumb = strSportThumb;
    }

    public String getStrSportThumbGreen() {
        return mStrSportThumbGreen;
    }

    public void setStrSportThumbGreen(String strSportThumbGreen) {
        mStrSportThumbGreen = strSportThumbGreen;
    }

}
