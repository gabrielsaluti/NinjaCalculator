package com.example.uber.ninjacalc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.view.View;
import java.util.ArrayList;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.WHITE;

public class TelaConfigLayout extends Activity {

    private RadioButton rbBlue;
    private RadioButton rbDark;
    private RadioButton rbWhite;
    private RadioGroup rdGroup;
    private ArrayList<Config> configs;
    private Toast toast;
    private Context ctx = this;
    private View view;

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_config_layout);

        rbBlue = (RadioButton) findViewById(R.id.rbBlue);
        rbDark = (RadioButton) findViewById(R.id.rbDark);
        rbWhite = (RadioButton) findViewById(R.id.rbWhite);
        rdGroup = (RadioGroup) findViewById(R.id.rdGroup);
        view = (View) findViewById(R.id.activity_tela_config_layout);

        final Ninja_DB banco= new Ninja_DB(this);

        configs = banco.listarConfig();

        if (configs.get(0).getLayout() == 1){
            rbBlue.setChecked(true);
        } else if (configs.get(0).getLayout() == 2){
            rbDark.setChecked(true);
        }else if (configs.get(0).getLayout() == 3){
            rbWhite.setChecked(true);
        }

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbBlue.isChecked()){
                    Config configuracoes = new Config(configs.get(0).getSenha().toString(),configs.get(0).getFakeSenha().toString(),1);
                    banco.update(configuracoes);
                    configs = banco.listarConfig();
                    toast = Toast.makeText(ctx,"Layout =" + configs.get(0).getLayout(),Toast.LENGTH_SHORT);
                    toast.show();
                    toast = Toast.makeText(ctx,"Layout alterado.",Toast.LENGTH_LONG);
                    toast.show();
                    Log.d(TAG,"AZUL");
                    view.setBackgroundColor(BLUE);

                }
                else if (rbDark.isChecked()){
                    Config configuracoes = new Config(configs.get(0).getSenha().toString(),configs.get(0).getFakeSenha().toString(),2);
                    banco.update(configuracoes);
                    configs = banco.listarConfig();
                    toast = Toast.makeText(ctx,"Layout =" + configs.get(0).getLayout(),Toast.LENGTH_SHORT);
                    toast.show();
                    toast = Toast.makeText(ctx,"Layout alterado.",Toast.LENGTH_LONG);
                    toast.show();
                    Log.d(TAG,"Dark");
                    view.setBackgroundColor(BLACK);
                }
                else if(rbWhite.isChecked()){
                    Config configuracoes = new Config(configs.get(0).getSenha().toString(),configs.get(0).getFakeSenha().toString(),3);
                    banco.update(configuracoes);
                    configs = banco.listarConfig();
                    toast = Toast.makeText(ctx,"Layout =" + configs.get(0).getLayout(),Toast.LENGTH_SHORT);
                    toast.show();
                    toast = Toast.makeText(ctx,"Layout alterado.",Toast.LENGTH_LONG);
                    toast.show();
                    Log.d(TAG,"White");
                    view.setBackgroundColor(WHITE);
                }
            }
        });


        /*public void onRadioButtonClicked (View view) {
            // Is the button now checked?
            boolean checked = ((RadioButton) view).isChecked();

            // Check which radio button was clicked
            switch(view.getId()) {
                case R.id.rbBlue:
                    if (checked){
                        Log.d(TAG,"Layout AZUL");}
                        break;
                case R.id.rbDark:
                    if (checked){
                        Log.d(TAG,"Layout DARK");}
                        break;
                case R.id.rbWhite:
                    if(checked){
                        Log.d(TAG,"Layout WHITE");}
                        break;
            }
        }*/

    }
}
