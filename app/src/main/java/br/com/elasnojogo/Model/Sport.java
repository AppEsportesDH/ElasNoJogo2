package br.com.elasnojogo.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Sport implements Parcelable {

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

    protected Sport(Parcel in) {
        mIdSport = in.readString();
        mStrFormat = in.readString();
        mStrSport = in.readString();
        mStrSportDescription = in.readString();
        mStrSportThumb = in.readString();
        mStrSportThumbGreen = in.readString();
    }

    public static final Creator<Sport> CREATOR = new Creator<Sport>() {
        @Override
        public Sport createFromParcel(Parcel in) {
            return new Sport(in);
        }

        @Override
        public Sport[] newArray(int size) {
            return new Sport[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mIdSport);
        parcel.writeString(mStrFormat);
        parcel.writeString(mStrSport);
        parcel.writeString(mStrSportDescription);
        parcel.writeString(mStrSportThumb);
        parcel.writeString(mStrSportThumbGreen);
    }
}
