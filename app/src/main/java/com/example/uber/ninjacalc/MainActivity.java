package com.example.uber.ninjacalc;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.FloatProperty;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    static String Tag = "MainActivity";
    static String Operacao = "";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        Button btnAdd;
        Button btnSub;
        Button btnMult;
        Button btnDiv;
        Button btnEquals;
        Button btnDelete;
        final TextView displayCalc;
        Button btn1;
        Button btn2;
        Button btn3;
        Button btn4;
        Button btn5;
        Button btn6;
        Button btn7;
        Button btn8;
        Button btn9;
        Button btn0;
        final Intent itPassword = new Intent(this, Password.class);


        // Finding Elements on the view
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMult = (Button) findViewById(R.id.btnMult);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnEquals = (Button) findViewById(R.id.btnEquals);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        displayCalc = (TextView) findViewById(R.id.displayCalc);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText(displayCalc.getText()+"1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText(displayCalc.getText()+"2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText(displayCalc.getText()+"3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText(displayCalc.getText()+"4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText(displayCalc.getText()+"5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText(displayCalc.getText()+"6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText(displayCalc.getText()+"7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText(displayCalc.getText()+"8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText(displayCalc.getText()+"9");
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText(displayCalc.getText()+"0");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText(displayCalc.getText()+"+");
                Operacao = "+";
            }
        });

        btnAdd.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(itPassword);
                return false;
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText(displayCalc.getText()+"-");
                Operacao = "-";
            }
        });

        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText(displayCalc.getText()+"*");
                Operacao = "*";
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText(displayCalc.getText()+"/");
                Operacao = "/";
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalc.setText("");
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Fim, ini;
                float num1,num2,total;
                String[] bebe;
                switch(Operacao) {
                    case "-" :
                        ini = displayCalc.getText().toString();
                        bebe = ini.split("-");
                        num1 = Float.parseFloat(bebe[0]);
                        num2 = Float.parseFloat(bebe[1]);
                        total = num1 - num2;
                        Fim = Float.toString(total);
                        displayCalc.setText(Fim);
                        break;

                    case "*" :
                        ini = displayCalc.getText().toString();
                        bebe = ini.split("\\*");
                        num1 = Float.parseFloat(bebe[0]);
                        num2 = Float.parseFloat(bebe[1]);
                        total = num1 * num2;
                        Fim = Float.toString(total);
                        displayCalc.setText(Fim);
                        break;

                    case "/":
                        ini = displayCalc.getText().toString();
                        bebe = ini.split("/");
                        num1 = Float.parseFloat(bebe[0]);
                        num2 = Float.parseFloat(bebe[1]);
                        total = num1 / num2;
                        Fim = Float.toString(total);
                        displayCalc.setText(Fim);
                        break;

                    case "+" :
                        ini = displayCalc.getText().toString();
                        bebe = ini.split("\\+");
                        num1 = Float.parseFloat(bebe[0]);
                        num2 = Float.parseFloat(bebe[1]);
                        total = num1 + num2;
                        Fim = Float.toString(total);
                        displayCalc.setText(Fim);
                        break;


                    default:
                        break;
                }

            }
        });


        /* CODIGO INT TO STRING CALCULO STRING TO INT
         int foo = Integer.parseInt(bebe[0]);
                int foo2 = Integer.parseInt(bebe[1]);
                int fooFinal = foo + foo2;
                String teste = Integer.toString(fooFinal);
                displayCalc.setText(teste);

         */



        //Degug
        /*
        Button btnplus = (Button) findViewById(R.id.btnplus);
        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(Tag, "Hey its working!");
            }
        });

        Button btnminus = (Button) findViewById(R.id.btnminus);
        final Intent it = new Intent(this, Password.class);
        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(it);
            }
        });

*/
    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
