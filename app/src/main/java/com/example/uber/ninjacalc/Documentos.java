package com.example.uber.ninjacalc;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Documentos extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listaDocs;
    private File curFolder;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static Toast toast;
    private Context ctx = this;
    private int response;
    private String[] files;
    private File[] detalhe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        listaDocs = (ListView) findViewById(R.id.lvDocs);

        //if(ContextCompat.checkSelfPermission(this,
        //        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
        //ask for permission
        //    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, response);
        // }

        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        curFolder = new File(path);
        files = curFolder.list();
        detalhe = curFolder.listFiles();

        if (files == null){
            Log.d(TAG,"ta nulo");
        }

        //for (int i = 0; i < files.length; i++){
        //    Log.d(TAG,files[i].toString());
        //}




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                files
        ){public View getView(int position, View convertView, ViewGroup parent) {

            //View view = super.getView(position, convertView, parent);
            //TextView text = (TextView) view.findViewById(android.R.id.text1);
            //text.setTextColor(Color.BLACK);
            //return view;



            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = inflater.inflate(R.layout.modelolayout, parent, false);
            String[] a = files;
            File[] b = detalhe;
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String data = df.format(b[position].lastModified());
            TextView t = (TextView) view.findViewById(R.id.tNome);
            TextView d = (TextView) view.findViewById(R.id.tDetalhe);
           // ImageView img = (ImageView) view.findViewById(R.id.imgAluno);
            LinearLayout lay = (LinearLayout) view.findViewById(R.id.lay);
            if(position%2==0)
                lay.setBackgroundColor(Color.parseColor("#CCCCCC"));
            t.setText(a[position]);
            d.setText("Última modificação: "+data);
            return view;


        }};

        listaDocs.setAdapter(adapter);

        listaDocs.setOnItemClickListener(this);
        Log.d(TAG,"setei");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Intent itTexto = new Intent(this, ReadFileActivity.class);
        //Log.d(TAG,"clicado");
        //Toast.makeText(ctx,"cliquei",Toast.LENGTH_LONG).show();
        itTexto.putExtra("nome",files[position].toString());

        String nome = files[position].toString();
        String[] arquivo = nome.split("\\.");
        String tipoArquivo = arquivo[1];
        Log.d(TAG, tipoArquivo);

        if (tipoArquivo.equals("txt")){
            startActivity(itTexto);
        }
        else {
            Toast.makeText(this,"Não podemos abrir arquivos ."+ tipoArquivo,Toast.LENGTH_SHORT).show();
        }

    }
}