package com.example.uber.ninjacalc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class DocumentosFake extends AppCompatActivity implements  View.OnClickListener{

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
    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos_fake);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle("Documents");

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        fab.setOnClickListener( this);
        fab1.setOnClickListener( this);
        fab2.setOnClickListener(this);



        listaDocs = (ListView) findViewById(R.id.lvDocs);
        //fbtnAdd = (FloatingActionButton) findViewById(R.id.fbtnAdicionar);
        /*fbtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent itDownload = new Intent(ctx, pastaDownloads.class);
                startActivity(itDownload);
                finish();
            }
        });*/



        //if(ContextCompat.checkSelfPermission(this,
        //        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
        //ask for permission
        //    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, response);
        // }

        //path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Teste";
        path = Environment.getExternalStorageDirectory().toString() + "/Hide/.NinjaHideFake";
        Log.d(TAG,path);
        curFolder = new File(path);
        files = curFolder.list();
        detalhe = curFolder.listFiles();

        if (files == null){
            Log.d(TAG,"ta nulo");
            files = new String[1];
            files[0] = "Não existem documentos adicionados";
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
            //String data = df.format(b[position].lastModified());
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
            //d.setText("Última modificação: "+data);
            return view;


        }};

        listaDocs.setAdapter(adapter);

        Log.d(TAG,"setei");

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
                alertDialog.setTitle("Em Manutenção");
                alertDialog.setMessage("Em breve");

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
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


    public void animateFAB(){

        if(isFabOpen){


            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:

                animateFAB();
                break;
            case R.id.fab1:
                final Intent itConfig = new Intent(ctx, TelaConfiguracoes.class);
                startActivity(itConfig);
                Log.d("Raj", "Fab 1");
                break;
            case R.id.fab2:
                final Intent itDownload = new Intent(ctx, pastaDownloads.class);
                startActivity(itDownload);
                Log.d("Raj", "Fab 2");
                break;
        }
    }

}