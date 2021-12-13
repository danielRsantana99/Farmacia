package com.example.farmacia.dominio.entidade;

public class remedio {
    public int id;
    public String nome;
    public String sintomas;
    public String efeitos;
    public float preco;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSintomas() {
        return sintomas;
    }

    public String getEfeitos() {
        return efeitos;
    }

    public float getPreco() {
        return preco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public void setEfeitos(String efeitos) {
        this.efeitos = efeitos;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
