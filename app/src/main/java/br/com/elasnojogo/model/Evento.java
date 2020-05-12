package br.com.elasnojogo.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "evento")
public class Evento implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "nomeEvento")

    private String nomeEvento;
    @ColumnInfo(name = "dataEvento")

    private String data;
    @ColumnInfo(name = "horárioEvento")

    private String horario;
    @ColumnInfo(name = "localEvento")

    private String local;
    @ColumnInfo(name = "categoriaEsportes")

    private String genero;
    @ColumnInfo(name = "generoMulher")

    private String categoriaEsportes;
    public Evento(String nomeEvento, String data, String horario, String local,
                  String categoriaEsportes, String genero) {
        this.nomeEvento = nomeEvento;
        this.data = data;
        this.horario = horario;
        this.local = local;
        this.categoriaEsportes = categoriaEsportes;
        this.genero = genero;
    }

    protected Evento(Parcel in) {
        id = in.readLong();
        nomeEvento = in.readString();
        horario = in.readString();
        local = in.readString();
        genero = in.readString();
        categoriaEsportes = in.readString();
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

    public Evento(String nomeEvento, String data, String horario, String local, String categoriaEsportes) {
        this.nomeEvento = nomeEvento;
        this.data = data;
        this.horario = horario;
        this.local = local;
        this.categoriaEsportes = categoriaEsportes;
    }


    public String getNomeEvento() {return nomeEvento; }

    public void setNomeEvento(String nomeEvento) { this.nomeEvento = nomeEvento; }

    public String getData() { return data; }

    public void setData(String data) { this.data = data; }

    public String getHorario() { return horario; }

    public void setHorario(String horario) { this.horario = horario; }

    public String getLocal() { return local; }

    public void setLocal(String local) { this.local = local;}

    public String getCategoriaEsportes() { return categoriaEsportes; }

    public void setCategoriaEsportes(String categoriaEsportes) { this.categoriaEsportes = categoriaEsportes; }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getGenero() { return genero; }

    public void setGenero(String genero) { this.genero = genero; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(nomeEvento);
        parcel.writeString(horario);
        parcel.writeString(local);
        parcel.writeString(genero);
        parcel.writeString(categoriaEsportes);
    }
}