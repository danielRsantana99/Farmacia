package com.example.farmacia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RemedioDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public RemedioDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();

    }

    public  long inserir(Remedio remedio){
        ContentValues values = new ContentValues();
        values.put("nome", remedio.getNome());
        values.put("sintoma", remedio.getSintoma());
        values.put("preco", remedio.getPreco());
        return banco.insert("remedio",null,values);
    }

    public List<Remedio> obterTodos(){
        List<Remedio> remedios = new ArrayList<>();
        Cursor cursor = banco.query("remedio",new String[]{"id","nome","sintoma","preco"},null,null,null,null,null);
        while (cursor.moveToNext()){
            Remedio a = new Remedio();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setSintoma(cursor.getString(2));
            a.setPreco(cursor.getDouble(3));
            remedios.add(a);
        }
        return remedios;
    }
    public void excluir (Remedio a){
        banco.delete("remedio", "id = ?", new String[]{a.getId().toString()});
    }

    public void atualizar(Remedio remedio){
        ContentValues values = new ContentValues();
        values.put("nome", remedio.getNome());
        values.put("sintoma", remedio.getSintoma());
        values.put("preco", remedio.getPreco());
        banco.update("remedio",values,
                "id = ?",
                new String[]{remedio.getId().toString()});
    }
}
