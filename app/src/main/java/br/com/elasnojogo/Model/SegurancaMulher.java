package br.com.elasnojogo.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity (tableName = "seguranaMulher")
public class SegurancaMulher {

    @ColumnInfo(name = "orientacaoSexual")
    private String orientacaoSexual;

    public SegurancaMulher(String orientacaoSexual) {
        this.orientacaoSexual = orientacaoSexual;
    }

    public String getOrientacaoSexual() { return orientacaoSexual; }

    public void setOrientacaoSexual(String orientacaoSexual) { this.orientacaoSexual = orientacaoSexual; }
}
