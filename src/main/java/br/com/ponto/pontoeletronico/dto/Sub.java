package br.com.ponto.pontoeletronico.dto;

public class Sub {

    public Sub(Object[] perfis, String nome, String usuario, long id, String email) {
        this.perfis = perfis;
        this.nome = nome;
        this.usuario = usuario;
        this.id = id;
        this.email = email;
    }

    public Sub() {
    }

    private Object[] perfis;
    private String nome;
    private String usuario;
    private long id;
    private String email;

    public Object[] getPerfis() {
        return perfis;
    }

    public void setPerfis(Object[] value) {
        this.perfis = value;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String value) {
        this.nome = value;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String value) {
        this.usuario = value;
    }

    public long getID() {
        return id;
    }

    public void setID(long value) {
        this.id = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }
}
