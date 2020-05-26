package br.com.elasnojogo.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity(tableName = "evento")
public class Evento implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long id;

    @ColumnInfo(name = "nomeEvento")
    private String nomeEvento;

    @ColumnInfo(name = "dataEvento")
    private String data;

    @ColumnInfo(name = "horárioEvento")
    private String horario;

    @ColumnInfo(name = "localEvento")
    private String local;

    @ColumnInfo(name = "generoMulher")
    private String genero;

    @ColumnInfo(name = "categoriaEvento")
    private String categoria;

    public Evento(String nomeEvento, String data, String horario, String local, String genero, String categoria) {
        this.nomeEvento = nomeEvento;
        this.data = data;
        this.horario = horario;
        this.local = local;
        this.genero = genero;
        this.categoria = categoria;
    }

    public Evento(){
    }

    protected Evento(Parcel in) {
        nomeEvento = in.readString();
        horario = in.readString();
        local = in.readString();
        genero = in.readString();
        categoria = in.readString();
        data = in.readString();
    }
    public static final Creator<Evento> CREATOR = new Creator<Evento>() {
        @Override
        public Evento createFromParcel(Parcel in) {
            return new Evento(in);
        }
        @Override
        public Evento[] newArray(int size) {
            return new Evento[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public static Creator<Evento> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nomeEvento);
        parcel.writeString(horario);
        parcel.writeString(local);
        parcel.writeString(data);
        parcel.writeString(categoria);
        parcel.writeString(genero);
    }
}