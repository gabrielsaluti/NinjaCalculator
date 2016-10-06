package com.example.uber.ninjacalc;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Documentos extends Activity {

    private ListView listaDocs;

    private String[] itens = {
            "Angra dos reis", "Caldas Novas",
            "Campos de Jordao", "Costa do Sauipe",
            "Ilheus", "Porto Seguro", "Rio de Janeiro"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);

        listaDocs = (ListView) findViewById(R.id.lvDocs);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );

        listaDocs.setAdapter(adapter);
    }
}
