package com.example.farmacia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListarRemediosActivity extends AppCompatActivity {
    private ListView listView;
    private RemedioDAO dao;
    private List<Remedio> remedios;
    private List<Remedio> remediosFiltrados = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_remedios);

        listView = findViewById(R.id.lista_remedios);
        dao = new RemedioDAO(this);
        remedios = dao.obterTodos();
        remediosFiltrados.addAll(remedios);
        RemedioAdapter adaptador = new RemedioAdapter(remediosFiltrados, this);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit (String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange (String s) {
                procuraRemedio(s);
                return false;
            }
        });


        return  true;
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);

    }

    public void procuraRemedio(String nome){
        remediosFiltrados.clear();
        for(Remedio a : remedios){
            if(a.getNome().toLowerCase().contains(nome.toLowerCase())){
                remediosFiltrados.add(a);
            }

        }
        listView.invalidateViews();
    }
    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Remedio remedioExcluir = remediosFiltrados.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o remedio?")
                .setNegativeButton("NÃO",null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                remediosFiltrados.remove(remedioExcluir);
                remedios.remove(remedioExcluir);
                dao.excluir(remedioExcluir);
                listView.invalidateViews();
            }
        }).create();
        dialog.show();

    }

    public void cadastrar(MenuItem menuItem){
        Intent it = new Intent(this, CadastrarRemedioActivity.class);
        startActivity(it);
    }
    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Remedio remedioAtualizar = remediosFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastrarRemedioActivity.class);
        it.putExtra("remedio", remedioAtualizar);
        startActivity(it);
    }

    @Override
    public void onResume(){
        super.onResume();
        remedios = dao.obterTodos();
        remediosFiltrados.clear();
        remediosFiltrados.addAll(remedios);
        listView.invalidateViews();
    }
}