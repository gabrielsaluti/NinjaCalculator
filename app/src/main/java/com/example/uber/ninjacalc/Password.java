package com.example.uber.ninjacalc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;

public class Password extends Activity {

    SharedPreferences prefs = null;
    private ArrayList<Config> configs;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        prefs = getSharedPreferences("com.example.uber.ninjacalc", MODE_PRIVATE);



        final Ninja_DB banco = new Ninja_DB(this);
        //final Intent it = new Intent(this,Menu.class);
        final Intent it = new Intent(this,Documentos.class);
        final EditText password1;
        final EditText password2;
        final EditText password3;
        final EditText password4;

        password1 = (EditText) findViewById(R.id.password1);
        password2 = (EditText) findViewById(R.id.password2);
        password3 = (EditText) findViewById(R.id.password3);
        password4 = (EditText) findViewById(R.id.password4);


        configs = banco.listarConfig();

        if (configs.size() == 0){
            Config config = new Config ("1234", "12345",1 );
            banco.insert(config);
        }

        configs = banco.listarConfig();

        password1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 1) {
                    password2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });

        password2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 1) {
                    password3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 1) {
                    password4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 1) {
                    String senha = password1.getText().toString()+password2.getText().toString()+password3.getText().toString()+password4.getText().toString();
                    //Log.d(TAG,senha);
                    //Log.d(TAG, configs.get(0).getSenha());
                    if (senha.toString().equals(configs.get(0).getSenha().toString())){
                        startActivity(it);

                    };
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            Log.d(TAG,"First Run");
            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }
}
