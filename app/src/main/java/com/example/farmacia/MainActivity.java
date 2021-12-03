package com.example.farmacia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.example.farmacia.database.dadosOpenHelper;
import com.example.farmacia.dominio.entidade.remedio;
import com.example.farmacia.dominio.repositorio.remedioRepositorio;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase conexão;
    private dadosOpenHelper dadosOpenHelper;
    private remedioAdapter remedioAdapter;
    private remedioRepositorio remedioRepositorio;
    private ConstraintLayout pesquisa;
    private RecyclerView lstdados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstdados = (RecyclerView)findViewById(R.id.id_lista);
        pesquisa = (ConstraintLayout)findViewById(R.id.pesquisa);

        lstdados.setHasFixedSize(true);
        criarConexao();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstdados.setLayoutManager(linearLayoutManager);

        remedioRepositorio = new remedioRepositorio(conexão);
        List<remedio> dados = remedioRepositorio.buscarTodos();

        remedioAdapter = new remedioAdapter(dados);
        lstdados.setAdapter(remedioAdapter);
    }

    private void criarConexao(){
        try {

            dadosOpenHelper = new dadosOpenHelper(this);

            conexão = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(pesquisa,R.string.mensagem_conexao, Snackbar.LENGTH_SHORT).setAction(R.string.messagem_ok,null).show();
        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.messagem_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.messagem_ok,null);
            dlg.show();

        }

    }

}