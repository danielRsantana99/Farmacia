package com.example.farmacia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmacia.dominio.entidade.remedio;

import java.util.ArrayList;
import java.util.List;

public class remedioAdapter extends RecyclerView.Adapter<remedioAdapter.ViewHolderRemedio> {

    List<remedio> dados;
    
    public remedioAdapter(List<remedio> dados){
        this.dados= dados;
    }

    @NonNull
    @Override
    public remedioAdapter.ViewHolderRemedio onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha_remedio,parent,false);

        ViewHolderRemedio holderRemedio = new ViewHolderRemedio(view);
        return holderRemedio;

    }

    @Override
    public void onBindViewHolder(@NonNull remedioAdapter.ViewHolderRemedio holder, int position) {
        if((dados != null) &&(dados.size() > 0)){
            remedio remedio = dados.get(position);

            holder.txtnome.setText(remedio.nome);
            holder.txtsintoma.setText(remedio.sintomas);
        }


    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderRemedio extends RecyclerView.ViewHolder{

        public TextView txtnome;
        public TextView txtsintoma;
        private  ViewHolderRemedio(View itemView) {
            super(itemView);

            txtnome = (TextView) itemView.findViewById(R.id.txtnome);
            txtsintoma = (TextView) itemView.findViewById(R.id.txtsintomas);
        }
    }


}
