package br.com.elasnojogo.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.material.textfield.TextInputLayout;


@SuppressLint("ParcelCreator")
public class Usuario implements Parcelable {
    private Long id;
    private String identificacao;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String confirmarSenha;

    public Usuario(String identificacao, String nome, String email, String telefone, String senha, String confirmarSenha) {
        this.identificacao = identificacao;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.confirmarSenha = confirmarSenha;
    }

    public Usuario(){

    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getIdentificacao() { return identificacao; }

    public void setIdentificacao(String identificacao) { this.identificacao = identificacao; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public String getConfirmarSenha() { return confirmarSenha; }

    public void setConfirmarSenha(String confirmarSenha) { this.confirmarSenha = confirmarSenha; }

    protected Usuario(Parcel in) {
        identificacao = in.readString();
        nome = in.readString();
        email = in.readString();
        telefone = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(identificacao);
        dest.writeString(nome);
        dest.writeString(email);
        dest.writeString(telefone);
        dest.writeString(senha);
        dest.writeString(confirmarSenha);
    }
}