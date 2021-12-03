package com.example.farmacia.database;

public class scriptDLL {

    public static String getCreateTableCliente(){

        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE IF NOT EXISTS remedio( ");
        sql.append("    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("    nome VARCHAR(200) NOT NULL DEFAULT (''), ");
        sql.append("    sintomas VARCHAR(200) NOT NULL DEFAULT (''), ");
        sql.append("    efeitos VARCHAR(200) NOT NULL DEFAULT ('') ");
        sql.append("    preco INTEGER NOT NULL DEFAULT ('') )");

        return sql.toString();
    }



}
