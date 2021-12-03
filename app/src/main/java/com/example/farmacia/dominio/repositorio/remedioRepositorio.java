package com.example.farmacia.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.farmacia.dominio.entidade.remedio;

import java.util.ArrayList;
import java.util.List;

public class remedioRepositorio {

    private SQLiteDatabase conexao;

    public remedioRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }


    public void inserir(remedio remedio){

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", remedio.nome);
        contentValues.put("sintomas",remedio.sintomas);
        contentValues.put("efeitos",remedio.efeitos);
        contentValues.put("preco",remedio.preco);

        conexao.insertOrThrow("remedio",null,contentValues);
    }

    public void alterar(remedio remedio){

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", remedio.nome);
        contentValues.put("sintomas",remedio.sintomas);
        contentValues.put("efeitos",remedio.efeitos);
        contentValues.put("preco",remedio.preco);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(remedio.id);

        conexao.update("remedio",contentValues,"id=?",parametros);
    }

    public void excluir(int id){
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id);

        conexao.delete("remedio","id=?",parametros);
    }

    public List<remedio> buscarTodos(){

        List<remedio> remedios = new ArrayList<remedio>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id,nome,sintomas,efeitos,preco");
        sql.append("FROM remedio");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();
            do {
                remedio rem = new remedio();

                rem.id = resultado.getInt(resultado.getColumnIndexOrThrow("id"));
                rem.nome = resultado.getString(resultado.getColumnIndexOrThrow("nome"));
                rem.sintomas = resultado.getString(resultado.getColumnIndexOrThrow("sintomas"));
                rem.efeitos = resultado.getString(resultado.getColumnIndexOrThrow("efeitos"));
                rem.preco = resultado.getFloat(resultado.getColumnIndexOrThrow("preco"));

                remedios.add(rem);
            }while (resultado.moveToNext());
        }
        return remedios;
    }

    public remedio buscarRemedio(int id){
        remedio remedio = new remedio();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id,nome,sintomas,efeitos,preco");
        sql.append("FROM remedio");
        sql.append("WHERE id= ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id);

        Cursor resultado = conexao.rawQuery(sql.toString(),parametros);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();

            remedio.id = resultado.getInt(resultado.getColumnIndexOrThrow("id"));
            remedio.nome = resultado.getString(resultado.getColumnIndexOrThrow("nome"));
            remedio.sintomas = resultado.getString(resultado.getColumnIndexOrThrow("sintomas"));
            remedio.efeitos = resultado.getString(resultado.getColumnIndexOrThrow("efeitos"));
            remedio.preco = resultado.getFloat(resultado.getColumnIndexOrThrow("preco"));

            return remedio;
        }
        return  null;
    }


}
