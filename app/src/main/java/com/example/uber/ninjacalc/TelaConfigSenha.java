package com.example.uber.ninjacalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class TelaConfigSenha extends AppCompatActivity {

    private EditText senha;
    private ArrayList<Config> configs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_config_senha);

        senha = (EditText) findViewById(R.id.edtSenha);

        Ninja_DB banco = new Ninja_DB(this);


        banco.delete();
        Config teste =  new Config("1111","1234",1);
        banco.insert(teste);

        configs = banco.listarConfig();

        senha.setText(configs.get(0).getSenha().toString());


    }
}
