package com.example.uber.ninjacalc;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.GridView;

import java.io.BufferedReader;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class Galeria extends AppCompatActivity {

    private GridView listaimg;
    private File curFolder;
    private Context ctx;
    private File[] imagens;
    private String[] imagens2;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle("Gallery");



        listaimg = (GridView) findViewById(R.id.GridImg);

        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

        curFolder = new File(path);

        imagens = curFolder.listFiles();

        imagens2 = curFolder.list();

        Log.d(TAG,imagens[3].toString());

        int[] images = new int[]{
                R.mipmap.ic_galery,
                R.mipmap.ic_launcher
        };

        //Recriar classe adapter, não funciona esta merda
        // listaimg.setAdapter(new ImagensAdapter(ctx,images));



    }
}
