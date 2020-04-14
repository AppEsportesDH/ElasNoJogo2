package br.com.elasnojogo.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Button;

public class DadosEvento implements Parcelable {
    private String nomeEvento;
    private String localEvento;
    private String categoriaEvento;
    private String horarioEvento;
    private String dataEvento;
    private String checksSegurança;
    private Button cadastrarEventos;
    private int adicionarImagem;

    public DadosEvento(int adicionarImagem, String nomeEvento, String localEvento, String dataEvento) {
        this.adicionarImagem = adicionarImagem;
        this.nomeEvento = nomeEvento;
        this.localEvento = localEvento;
        this.dataEvento = dataEvento;
    }

    public DadosEvento(String nomeEvento, String localEvento, String categoriaEvento, String horarioEvento,
                       String dataEvento, String checksSegurança, Button cadastrarEventos, int adicionarImagem) {
        this.nomeEvento       = nomeEvento;
        this.localEvento      = localEvento;
        this.categoriaEvento  = categoriaEvento;
        this.horarioEvento    = horarioEvento;
        this.dataEvento       = dataEvento;
        this.checksSegurança  = checksSegurança;
        this.cadastrarEventos = cadastrarEventos;
        this.adicionarImagem  = adicionarImagem;
    }

    protected DadosEvento(Parcel in) {
        nomeEvento      = in.readString();
        localEvento     = in.readString();
        categoriaEvento = in.readString();
        horarioEvento   = in.readString();
        dataEvento      = in.readString();
        checksSegurança = in.readString();
        categoriaEvento = in.readString();
        adicionarImagem = in.readInt();

    }

    public static final Creator<DadosEvento> CREATOR = new Creator<DadosEvento>() {
        @Override
        public DadosEvento createFromParcel(Parcel in) {
            return new DadosEvento(in);
        }

        @Override
        public DadosEvento[] newArray(int size) {
            return new DadosEvento[size];
        }
    };

    public String getNomeEvento() { return nomeEvento; }

    public void setNomeEvento(String nomeEvento) { this.nomeEvento = nomeEvento; }

    public String getLocalEvento() { return localEvento; }

    public void setLocalEvento(String localEvento) { this.localEvento = localEvento; }

    public String getCategoriaEvento() { return categoriaEvento; }

    public void setCategoriaEvento(String categoriaEvento) { this.categoriaEvento = categoriaEvento; }

    public String getHorarioEvento() { return horarioEvento; }

    public void setHorarioEvento(String horarioEvento) {this.horarioEvento = horarioEvento; }

    public String getDataEvento() { return dataEvento;}

    public void setDataEvento(String dataEvento) { this.dataEvento = dataEvento;}

    public String getChecksSegurança() { return checksSegurança;}

    public void setChecksSegurança(String checksSegurança) { this.checksSegurança = checksSegurança; }

    public Button getCadastrarEventos() { return cadastrarEventos; }

    public void setCadastrarEventos(Button cadastrarEventos) { this.cadastrarEventos = cadastrarEventos; }

    public int getAdicionarImagem() { return adicionarImagem; }

    public void setAdicionarImagem(int adicionarImagem) { this.adicionarImagem = adicionarImagem;}

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nomeEvento);
        dest.writeString(localEvento);
        dest.writeString(categoriaEvento);
        dest.writeString(dataEvento);
        dest.writeString(checksSegurança);
        dest.writeInt(adicionarImagem);

    }

    public String getText1() {
        return getText1();
    }
}