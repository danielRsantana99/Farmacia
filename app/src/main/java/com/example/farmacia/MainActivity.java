package com.example.farmacia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.farmacia.database.dadosOpenHelper;
import com.example.farmacia.dominio.entidade.remedio;
import com.example.farmacia.dominio.repositorio.remedioRepositorio;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private dadosOpenHelper dadosopenhelper;
    private remedioAdapter remedioAdapter;
    private remedioRepositorio remediorepositorio;
    private ConstraintLayout pesquisa;
    private RecyclerView lstdados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstdados = findViewById(R.id.id_lista);
        pesquisa = findViewById(R.id.pesquisa);

        lstdados.setHasFixedSize(true);
        criarConexao();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstdados.setLayoutManager(linearLayoutManager);

        remediorepositorio = new remedioRepositorio(conexao);
        //List<remedio> dados = remediorepositorio.buscarTodos();

        //remedioAdapter = new remedioAdapter(dados);
        //lstdados.setAdapter(remedioAdapter);
    }

    private void criarConexao(){
        try {

            dadosopenhelper = new dadosOpenHelper(this);

            conexao = dadosopenhelper.getWritableDatabase();

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