package com.example.uber.ninjacalc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by André Vidal on 04/10/2016.
 */

public class Ninja_DB extends SQLiteOpenHelper {

    private static final String TAG = "sql";
    public static final  String NOME_BANCO = "Ninja_Android.sqlite";
    private static final int VERSAO_BANCO = 1;

    public Ninja_DB(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando tabela Ninja..");
        db.execSQL("create table if not exists Config (Senha text , layout integer,SenhaFake  text);");
        Log.d(TAG, "Tabela criada com sucesso");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int insert(Config config){
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();

            values.put("Senha", config.getSenha());
            values.put("layout", config.getLayout());
            values.put("SenhaFake", config.getFakeSenha());


            db.insert("Config","" ,values);
            return config.getLayout();
        }
        finally {
            db.close();
        }

    }

    public void update (Config config){
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();

            values.put("Senha", config.getSenha());
            values.put("layout", config.getLayout());
            values.put("SenhaFake", config.getFakeSenha());

            db.update("Config",values,null, null);

        }
        finally {
            db.close();
        }
    }

    public ArrayList<Config> listarConfig(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Config> configs = new ArrayList<Config>();
        try {
            Cursor c = db.query("Config", null, null, null, null, null, null);

            if(c.moveToFirst()){
                do {
                    String senha = c.getString(c.getColumnIndex("Senha"));
                    int layout = c.getInt(c.getColumnIndex("layout"));
                    String fakesenha = c.getString(c.getColumnIndex("SenhaFake"));
                    Config config = new Config(senha, fakesenha, layout);
                    configs.add(config);

                }while(c.moveToNext());
            }
        }
        finally {
            db.close();
        }
        return configs;
    }


    public int delete(){
        SQLiteDatabase db = getWritableDatabase();
        try{
            int count = db.delete("Config", "", null);
            return count;
        }
        finally {
            db.close();
        }
    }

}
