package com.example.uber.ninjacalc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TelaConfiguracoes extends AppCompatActivity {

    private ArrayList<Config> configs;
    private ListView listaconfig;
    private ArrayList<String> lista = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_configuracoes);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle("Settings");

        final Intent itSenha = new Intent(this, TelaConfigSenha.class);
        final Intent itLayout = new Intent(this, TelaConfigLayout.class);
        final Intent itFake = new Intent(this, TelaConfigFakeSenha.class);

        listaconfig = (ListView) findViewById(R.id.listaconfig);

        lista.add("Senha");
        lista.add("Layout");
        lista.add("Senha Fake");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                lista
        ){public View getView(int position, View convertView, ViewGroup parent) {

            View view = super.getView(position, convertView, parent);
            TextView text = (TextView) view.findViewById(android.R.id.text1);
            text.setTextColor(Color.BLACK);
            return view;

        }};

        listaconfig.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                if (position == 0) {
                    startActivity(itSenha);
                }
                else if (position == 1){
                    startActivity(itLayout);
                }
                else if (position == 2){
                    startActivity(itFake);
                }
            }
        });

        listaconfig.setAdapter(adapter);



    }
}
