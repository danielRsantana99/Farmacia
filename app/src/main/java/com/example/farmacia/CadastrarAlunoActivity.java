package com.example.farmacia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarAlunoActivity extends AppCompatActivity {

    private EditText nome,cpf,telefone;
    private AlunoDAO dao;
    private Aluno aluno = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        nome = findViewById(R.id.editNome);
        cpf = findViewById(R.id.editCpf);
        telefone = findViewById(R.id.editTelefone);
        dao = new AlunoDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("aluno")){
            aluno = (Aluno) it.getSerializableExtra("aluno");
            nome.setText(aluno.getNome());
            cpf.setText(aluno.getCpf());
            telefone.setText(aluno.getTelefone());
        }
    }

    public void Salvar(View view) {
        if (aluno == null) {
            aluno = new Aluno();
            aluno.setNome(nome.getText().toString());
            aluno.setCpf(cpf.getText().toString());
            aluno.setTelefone(telefone.getText().toString());
            long id = dao.inserir(aluno);
            Toast.makeText(this, "Aluno inserido ", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this,ListarAlunosActivity.class);
            startActivity(it);
        }else{
            aluno.setNome(nome.getText().toString());
            aluno.setCpf(cpf.getText().toString());
            aluno.setTelefone(telefone.getText().toString());
            dao.atualizar(aluno);
            Toast.makeText(this, "Alteração concluída", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this,ListarAlunosActivity.class);
            startActivity(it);

        }
    }
}