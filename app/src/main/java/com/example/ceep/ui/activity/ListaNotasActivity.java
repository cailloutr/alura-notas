package com.example.ceep.ui.activity;

import static com.example.ceep.ui.ConstantsActivity.CHAVE_NOTA;
import static com.example.ceep.ui.ConstantsActivity.CODIGO_REQUISICAO_INSERE_NOTA;
import static com.example.ceep.ui.ConstantsActivity.CODIGO_RESULTADO_NOTA_CRIADA;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceep.R;
import com.example.ceep.dao.NotaDAO;
import com.example.ceep.model.Nota;
import com.example.ceep.ui.adapter.ListaNotasRecyclerAdapter;

import java.util.List;

public class ListaNotasActivity extends AppCompatActivity {


    private NotaDAO notaDAO;
    private ListaNotasRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        notaDAO = new NotaDAO();

        configuraRecyclerView(notaDAO);

        navegaParaFormularioNotaActivity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultContainsNota(requestCode, resultCode, data)) {
            Nota nota = (Nota) data.getSerializableExtra(CHAVE_NOTA);
            adicionaNota(nota);
        }
    }

    private void adicionaNota(Nota nota) {
        notaDAO.insere(nota);
        adapter.adiciona(nota);
    }

    private boolean resultContainsNota(int requestCode, int resultCode, @Nullable Intent data) {
        return requestCode == CODIGO_REQUISICAO_INSERE_NOTA && resultCode == CODIGO_RESULTADO_NOTA_CRIADA && data.hasExtra(CHAVE_NOTA);
    }

    private void navegaParaFormularioNotaActivity() {
        EditText inserirNotaEditText = findViewById(R.id.lista_notas_inserir_nota_edittext);
        inserirNotaEditText.setOnClickListener(v -> startActivityForResult(
                new Intent(
                        ListaNotasActivity.this,
                        FormularioNotaActivity.class),
                1));
    }

    private void configuraRecyclerView(NotaDAO notaDAO) {
        List<Nota> listaDeNotas = notaDAO.todos();
        RecyclerView recyclerView = findViewById(R.id.lista_notas_notas_recyclerView);
        adapter = new ListaNotasRecyclerAdapter(listaDeNotas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}