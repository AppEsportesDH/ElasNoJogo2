package br.com.elasnojogo.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "seguranaMulher")
public class SegurancaMulher {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "orientacaoSexual")
    private String orientacaoSexual;

    public SegurancaMulher(String orientacaoSexual) {
        this.orientacaoSexual = orientacaoSexual;
    }

    public String getOrientacaoSexual() { return orientacaoSexual; }

    public void setOrientacaoSexual(String orientacaoSexual) { this.orientacaoSexual = orientacaoSexual; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
