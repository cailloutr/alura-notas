package com.example.ceep.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceep.R;
import com.example.ceep.model.Nota;

import java.util.List;

public class ListaNotasRecyclerAdapter
        extends RecyclerView.Adapter<ListaNotasRecyclerAdapter.NotaViewHolder> {

    private final List<Nota> lista;

    public ListaNotasRecyclerAdapter(List<Nota> lista) {
        this.lista = lista;
    }

    static class NotaViewHolder extends RecyclerView.ViewHolder {
        private final TextView titlle;
        private final TextView description;

        public NotaViewHolder(@NonNull View itemView) {
            super(itemView);
            titlle = itemView.findViewById(R.id.item_nota_title);
            description = itemView.findViewById(R.id.item_nota_description);
        }

        public void bind(Nota nota) {
            titlle.setText(nota.getTitulo());
            description.setText(nota.getDescricao());
        }
    }


    @NonNull
    @Override
    public ListaNotasRecyclerAdapter.NotaViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_nota,
                parent,
                false);
        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ListaNotasRecyclerAdapter.NotaViewHolder holder,
            int position
    ) {
        Nota nota = lista.get(position);
        holder.bind(nota);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void adiciona(Nota nota) {
        lista.add(nota);
        notifyDataSetChanged();
    }
}
