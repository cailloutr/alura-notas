package com.example.ceep.ui.activity;

import static com.example.ceep.ui.ConstantsActivity.CHAVE_NOTA;
import static com.example.ceep.ui.ConstantsActivity.CODIGO_RESULTADO_NOTA_CRIADA;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ceep.R;
import com.example.ceep.dao.NotaDAO;
import com.example.ceep.model.Nota;

public class FormularioNotaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
        NotaDAO notaDAO = new NotaDAO();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (isMenuFormSalvaNOta(item)) {
            saveNota();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isMenuFormSalvaNOta(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_form_salvar_nota;
    }

    private void saveNota() {
        EditText title = findViewById(R.id.form_nota_title);
        EditText description = findViewById(R.id.form_nota_description);

        Nota nota = createNota(title, description);

        Intent data = new Intent();
        data.putExtra(CHAVE_NOTA, nota);
        setResult(CODIGO_RESULTADO_NOTA_CRIADA, data);
    }

    @NonNull
    private Nota createNota(EditText title, EditText description) {
        return new Nota(title.getText().toString(), description.getText().toString());
    }
}