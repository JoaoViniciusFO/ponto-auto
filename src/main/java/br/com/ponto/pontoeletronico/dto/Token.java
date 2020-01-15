package br.com.ponto.pontoeletronico.dto;

public class Token {
    public Token(Sub sub, long exp) {
        this.sub = sub;
        this.exp = exp;
    }

    public Token() {
    }

    private Sub sub;
    private long exp;

    public Sub getSub() {
        return sub;
    }

    public void setSub(Sub value) {
        this.sub = value;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long value) {
        this.exp = value;
    }
}