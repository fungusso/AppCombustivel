package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import java.util.ArrayList;

public class ListarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar);

        ListView lista = (ListView) findViewById(R.id.lvEstradas);
        final ArrayList<String> ruas = preenchimento();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ruas);
        lista.setAdapter(arrayAdapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemChosen = (String) lista.getItemAtPosition(i);
                Intent intent = new Intent();
                intent.putExtra("codProd", itemChosen);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }

        });
    }

    private ArrayList<String> preenchimento() {
        ArrayList<String> dados = new ArrayList<String>();
        dados.add("Cidade");
        dados.add("Estrada");
        dados.add("Chão");
        dados.add("Barro");
        dados.add("Morro");
        dados.add("Pedregulho");
        return dados;
    }
}