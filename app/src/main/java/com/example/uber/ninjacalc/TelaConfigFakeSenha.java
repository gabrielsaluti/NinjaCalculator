package com.example.uber.ninjacalc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaConfigFakeSenha extends AppCompatActivity {

    private EditText senha;
    private EditText confirmaSenha;
    private ArrayList<Config> configs;
    private Button btnsave;
    private Context ctx = this;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_config_fake_senha);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        //getSupportActionBar().setTitle("Fake Password");

        senha = (EditText) findViewById(R.id.edtSenhaFake);
        confirmaSenha = (EditText) findViewById(R.id.edtConfirmaSenhaFake);
        btnsave = (Button) findViewById(R.id.btnSaveFake);

        final Ninja_DB banco = new Ninja_DB(this);

        configs = banco.listarConfig();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,senha.getText().toString());
                Log.d(TAG, confirmaSenha.getText().toString());
                if (senha.getText().length() > 4){
                    toast = Toast.makeText(ctx,"A senha deve conter 4 digitos.",Toast.LENGTH_LONG);
                    toast.show();
                }
                else if (senha.getText().toString().equals(confirmaSenha.getText().toString())){
                    Config configuracoes = new Config(configs.get(0).getSenha().toString(),senha.getText().toString(),configs.get(0).getLayout());
                    banco.update(configuracoes);
                    configs = banco.listarConfig();
                    toast = Toast.makeText(ctx,"Senha Fake=" + configs.get(0).getFakeSenha().toString(),Toast.LENGTH_SHORT);
                    toast.show();
                    toast = Toast.makeText(ctx,"Senha alterada.",Toast.LENGTH_LONG);
                    toast.show();

                }
                else {
                    toast = Toast.makeText(ctx,"Senhas não conferem.",Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

    }
}