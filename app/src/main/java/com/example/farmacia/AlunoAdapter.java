package com.example.farmacia;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AlunoAdapter extends BaseAdapter {
    private List<Aluno> alunos;
    private Activity activity;

    public AlunoAdapter(List<Aluno> alunos, Activity activity) {
        this.alunos = alunos;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = activity.getLayoutInflater().inflate(R.layout.item,parent,false);
        TextView nome = v.findViewById(R.id.txtNomeId);
        TextView telefone = v.findViewById(R.id.txtTelefoneId);
        TextView cpf = v.findViewById(R.id.txtCpfId);
        Aluno a = alunos.get(position);

        nome.setText(a.getNome());
        cpf.setText(a.getCpf());
        telefone.setText(a.getTelefone());
        return v;
    }
}
