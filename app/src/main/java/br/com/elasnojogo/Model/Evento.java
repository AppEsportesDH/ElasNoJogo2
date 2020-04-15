package br.com.elasnojogo.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "evento")
public class Evento {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "nomeEvento")
    private String nomeEvento;

    @ColumnInfo(name = "dataEvento")
    private Date data;

    @ColumnInfo(name = "horárioEvento")
    private String horario;

    @ColumnInfo(name = "localEvento")
    private String local;

    @ColumnInfo(name = "categoriaEsportes")
    private String categoriaEsportes;

    public Evento(long id, String nomeEvento, Date data, String horario, String local,
                  String categoriaEsportes) {
        this.id = id;
        this.nomeEvento = nomeEvento;
        this.data = data;
        this.horario = horario;
        this.local = local;
        this.categoriaEsportes = categoriaEsportes;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getNomeEvento() {return nomeEvento; }

    public void setNomeEvento(String nomeEvento) { this.nomeEvento = nomeEvento; }

    public Date getData() { return data; }

    public void setData(Date data) { this.data = data; }

    public String getHorario() { return horario; }

    public void setHorario(String horario) { this.horario = horario; }

    public String getLocal() { return local; }

    public void setLocal(String local) { this.local = local;}

    public String getCategoriaEsportes() { return categoriaEsportes; }

    public void setCategoriaEsportes(String categoriaEsportes) { this.categoriaEsportes = categoriaEsportes; }
}
