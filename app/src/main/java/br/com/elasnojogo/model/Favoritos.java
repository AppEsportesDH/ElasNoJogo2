package br.com.elasnojogo.Model;

import java.util.Date;

public class Favoritos {

    private String nomeEvento;
    private String localEvento;
    private Date dataEvento;
    private int imagemEvento;

    public Favoritos() {
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getLocalEvento() {
        return localEvento;
    }

    public void setLocalEvento(String localEvento) {
        this.localEvento = localEvento;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public int getImagemEvento() {
        return imagemEvento;
    }

    public void setImagemEvento(int imagemEvento) {
        this.imagemEvento = imagemEvento;
    }
}
