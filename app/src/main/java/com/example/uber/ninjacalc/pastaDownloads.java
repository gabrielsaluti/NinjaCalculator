package com.example.uber.ninjacalc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class pastaDownloads extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listaDownloads;
    private File curFolder;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String[] files;
    private File[] detalhe;
    private Context ctx = this;
    private int pos;
    private ImageButton btnmenu;
    private File hideFolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasta_downloads);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        hideFolder = new File(Environment.getExternalStorageDirectory(),"Hide/.NinjaHide");
        Log.d(TAG,"Pasta referenciada: "+hideFolder.toString());
        if (hideFolder.exists()){
            File noMedia = new File(hideFolder.toString(), "/.nomedia");
            if (noMedia.exists()){
                Log.d(TAG, "Arquivo já existe");
            }
            /*else {
                try {
                    noMedia.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }
        else {
            hideFolder.mkdirs();
        }


        listaDownloads = (ListView) findViewById(R.id.lvDown);
        btnmenu = (ImageButton) findViewById(R.id.btnmenu);



        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        Log.d(TAG,path);
        curFolder = new File(path);
        files = curFolder.list();
        detalhe = curFolder.listFiles();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                files
        ){public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = inflater.inflate(R.layout.modelolayout, parent, false);
            String[] a = files;
            File[] b = detalhe;
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String data = df.format(b[position].lastModified());
            TextView t = (TextView) view.findViewById(R.id.tNome);
            TextView d = (TextView) view.findViewById(R.id.tDetalhe);
            LinearLayout lay = (LinearLayout) view.findViewById(R.id.lay);
            ImageButton btn = (ImageButton) view.findViewById(R.id.btnmenu);
            btn.setTag(position);
            if(position%2==0) {
                lay.setBackgroundColor(Color.parseColor("#CCCCCC"));
            }
            else {
                lay.setBackgroundColor(Color.parseColor("#97a7a7"));
            }
            t.setText(a[position]);
            d.setText("Última modificação: "+data);
            return view;


        }};
        listaDownloads.setChoiceMode(listaDownloads.CHOICE_MODE_MULTIPLE);
        listaDownloads.setAdapter(adapter);
        listaDownloads.setOnItemClickListener(this);

        listaDownloads.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                return false;
            }
        });
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menulistviewadd, popup.getMenu());
        pos = (Integer) v.getTag();
        //Toast.makeText(ctx,""+pos,Toast.LENGTH_LONG).show();
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d(TAG,Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() +"/" +files[pos].toString());
                Log.d(TAG,Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() +"/Teste/");
                Log.d(TAG,hideFolder.toString()+"/");
                // Definir arquivo origem e destino
                String src = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() +"/" +files[pos].toString();
                //String dst = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Teste/";
                String dst = hideFolder.toString()+"/";
                File fileSrc = new File(src);
                File fileDst = new File(dst);
                try {
                    // Copiar arquivo original para a pasta escondida
                    copyFile(fileSrc,fileDst, files[pos].toString());

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ctx,"Erro ao realizar a cópia do arquivo.",Toast.LENGTH_LONG).show();
                }

                // Fechar activity
                final Intent itDocs = new Intent(ctx, Documentos.class);
                startActivity(itDocs);
                finish();
                File fdelete = new File(src);
                fdelete.delete();
                return false;
            }
        });
    }

    private void copyFile(File src, File dst, String nomearquivo) throws IOException {
        FileInputStream inStream = new FileInputStream(src);

        if (!dst.exists()) {
            dst.mkdir();
        }

        File fCopy = new File(dst.getPath()+ File.separator + nomearquivo);

        FileOutputStream outStream = new FileOutputStream(fCopy);
        FileChannel inChannel = inStream.getChannel();
        FileChannel outChannel = outStream.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inStream.close();
        outStream.close();
    }
}
