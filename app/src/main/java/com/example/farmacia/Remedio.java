package com.example.farmacia;

import java.io.Serializable;

public class Remedio implements Serializable {
    private Integer id;
    private String nome;
    private String sintoma;
    private Double preco;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSintoma() {
        return sintoma;
    }

    public Double getPreco() {
        return preco;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString(){
        return nome;
    }
}
