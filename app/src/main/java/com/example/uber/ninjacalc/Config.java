package com.example.uber.ninjacalc;

/**
 * Created by André Vidal on 04/10/2016.
 */

public class Config {

    private String Senha;
    private String FakeSenha;
    private int layout;

    public Config(String senha, String fakeSenha, int layout) {
        Senha = senha;
        FakeSenha = fakeSenha;
        this.layout = layout;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getFakeSenha() {
        return FakeSenha;
    }

    public void setFakeSenha(String fakeSenha) {
        FakeSenha = fakeSenha;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Config config = (Config) o;

        if (!Senha.equals(config.Senha)) return false;
        return FakeSenha != null ? FakeSenha.equals(config.FakeSenha) : config.FakeSenha == null;

    }


}
