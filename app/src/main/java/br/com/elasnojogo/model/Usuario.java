package br.com.elasnojogo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {
    private String nome;
    private String email;
    private Long telefone;
    private String senha;
    private String confirmarSenha;

    public Usuario(String nome, String email, Long telefone, String senha, String confirmarSenha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.confirmarSenha = confirmarSenha;
    }

    protected Usuario(Parcel in) {
        nome = in.readString();
        email = in.readString();
        if (in.readByte() == 0) {
            telefone = null;
        } else {
            telefone = in.readLong();
        }
        senha = in.readString();
        confirmarSenha = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public Usuario(String sol, String email, String s, String rfd234, String rfd2341) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(email);
        if (telefone == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(telefone);
        }
        dest.writeString(senha);
        dest.writeString(confirmarSenha);
    }
}