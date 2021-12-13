package com.example.farmacia;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RemedioAdapter extends BaseAdapter {
    private List<Remedio> remedios;
    private Activity activity;

    public RemedioAdapter(List<Remedio> remedios, Activity activity) {
        this.remedios = remedios;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return remedios.size();
    }

    @Override
    public Object getItem(int position) {
        return remedios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return remedios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = activity.getLayoutInflater().inflate(R.layout.item,parent,false);
        TextView nome = v.findViewById(R.id.txtNomeId);
        TextView preco = v.findViewById(R.id.txtPrecoId);
        TextView sintoma = v.findViewById(R.id.txtSintomaId);
        Remedio a = remedios.get(position);

        nome.setText(a.getNome());
        sintoma.setText(a.getSintoma());
        preco.setText(a.getPreco().toString());
        return v;
    }
}
