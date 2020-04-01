package br.com.elasnojogo.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "seguranaMulher")
public class SegurancaMulher {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "orientacaoSexual")
    private String orientacaoSexual;

    public SegurancaMulher(String orientacaoSexual) {
        this.orientacaoSexual = orientacaoSexual;
    }

    public String getOrientacaoSexual() { return orientacaoSexual; }

    public void setOrientacaoSexual(String orientacaoSexual) { this.orientacaoSexual = orientacaoSexual; }


}
