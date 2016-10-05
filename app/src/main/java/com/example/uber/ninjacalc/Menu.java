package com.example.uber.ninjacalc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class Menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        final Intent itDoc = new Intent(this, Documentos.class);
        final Intent itGal = new Intent(this, Galeria.class);
        final Intent itConf = new Intent(this, TelaConfiguracoes.class);

        int[] lista = new int[]{R.mipmap.ic_doc,R.mipmap.ic_galery, R.mipmap.ic_config};

        GridView gridMenu = (GridView) findViewById(R.id.gridMenu);
        gridMenu.setAdapter(new Adaptador(this,lista));

        gridMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                if (position == 0) {
                    startActivity(itDoc);
                }
                else if(position == 1) {
                    startActivity(itGal);
                }
                else if (position == 2){
                    startActivity(itConf);
                }
            }
        });


    }
}
