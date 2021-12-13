package com.example.farmacia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarRemedioActivity extends AppCompatActivity {

    private EditText nome, preco, sintoma;
    private RemedioDAO dao;
    private Remedio remedio = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_remedio);

        nome = findViewById(R.id.editNome);
        preco = findViewById(R.id.editPreco);
        sintoma = findViewById(R.id.editSintoma);
        dao = new RemedioDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("remedio")){
            remedio = (Remedio) it.getSerializableExtra("remedio");
            nome.setText(remedio.getNome());
            preco.setText(remedio.getPreco().toString());
            sintoma.setText(remedio.getSintoma());
        }
    }

    public void Salvar(View view) {
        if (remedio == null) {
            remedio = new Remedio();
            remedio.setNome(nome.getText().toString());
            remedio.setSintoma(sintoma.getText().toString());
            remedio.setPreco(Double.parseDouble(preco.getText().toString()));
            long id = dao.inserir(remedio);
            Toast.makeText(this, "Remedio inserido ", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this, ListarRemediosActivity.class);
            startActivity(it);
        }else{
            remedio.setNome(nome.getText().toString());
            remedio.setSintoma(sintoma.getText().toString());
            remedio.setPreco(Double.parseDouble(preco.getText().toString()));
            dao.atualizar(remedio);
            Toast.makeText(this, "Alteração concluída", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this, ListarRemediosActivity.class);
            startActivity(it);

        }
    }
}