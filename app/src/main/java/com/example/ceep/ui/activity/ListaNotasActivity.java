package com.example.ceep.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ceep.R;
import com.example.ceep.dao.NotaDAO;
import com.example.ceep.model.Nota;
import com.example.ceep.ui.adapter.ListaNotasAdapter;
import com.example.ceep.ui.adapter.ListaNotasRecyclerAdapter;

import java.util.List;

public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        RecyclerView recyclerView = findViewById(R.id.lista_notas_notas_recyclerView);
        NotaDAO notaDAO = new NotaDAO();

        for (int i = 0; i < 10000; i++) {
            notaDAO.insere(new Nota(i + "º Nota", "Descrição da nota"));
        }

        List<Nota> listaDeNotas = notaDAO.todos();

        recyclerView.setAdapter(new ListaNotasRecyclerAdapter(listaDeNotas));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}