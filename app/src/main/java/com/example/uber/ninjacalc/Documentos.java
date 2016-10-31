package com.example.uber.ninjacalc;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.ImageView;
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


public class Documentos extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listaDocs;
    private File curFolder;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static Toast toast;
    private Context ctx = this;
    private int response;
    private String[] files;
    private File[] detalhe;
    private FloatingActionButton fbtnAdd;
    private String path;
    private ImageButton btnmenu;
    private int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        listaDocs = (ListView) findViewById(R.id.lvDocs);
        fbtnAdd = (FloatingActionButton) findViewById(R.id.fbtnAdicionar);
        fbtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent itDownload = new Intent(ctx, pastaDownloads.class);
                startActivity(itDownload);
                finish();
            }
        });



        //if(ContextCompat.checkSelfPermission(this,
        //        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
        //ask for permission
        //    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, response);
        // }

        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Teste";
        Log.d(TAG,path);
        curFolder = new File(path);
        files = curFolder.list();
        detalhe = curFolder.listFiles();

        if (files == null){
            Log.d(TAG,"ta nulo");
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
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
            if(position%2==0)
                lay.setBackgroundColor(Color.parseColor("#CCCCCC"));
            t.setText(a[position]);
            d.setText("Última modificação: "+data);
            return view;


        }};

        listaDocs.setAdapter(adapter);

        listaDocs.setOnItemClickListener(this);
        Log.d(TAG,"setei");

        listaDocs.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                final int position = pos;


                return true;
            }
        });
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

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menulistview, popup.getMenu());
        pos = (Integer) v.getTag();
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();
                alertDialog.setTitle("Tornar Público");
                alertDialog.setMessage("Você deseja tornar este arquivo público novamente?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Sim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                String dst = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() +"/";
                                String src = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Teste/"+files[pos].toString();
                                File fileSrc = new File(src);
                                File fileDst = new File(dst);
                                try {
                                    copyFile(fileSrc,fileDst, files[pos].toString());
                                    Log.d(TAG,"copiado de volta");
                                    File fdelete = new File(src);
                                    fdelete.delete();
                                    finish();
                                    final Intent itDocs = new Intent(ctx, Documentos.class);
                                    startActivity(itDocs);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Toast.makeText(ctx,"Erro ao tornar arquivo público.",Toast.LENGTH_LONG).show();
                                }
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Não",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return false;
            }
        });

    }
}