package com.example.uber.ninjacalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;

public class ReadFileActivity extends AppCompatActivity {

    private EditText txtview;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        txtview = (EditText) findViewById(R.id.txtView);

        nome = getIntent().getStringExtra("nome");

        Log.d(TAG,nome);

        txtview.setText(FileHelper.ReadFile(this,"/"+nome));

        getSupportActionBar().setTitle(nome);
        myToolbar.setTitleTextColor(0xFFFFFFFF);


    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_settings){
            if (FileHelper.saveToFile(txtview.getText().toString(),"/"+nome)){
                Toast.makeText(this,"Saved to file",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Error save file!!!",Toast.LENGTH_SHORT).show();
            }
        }



        return super.onOptionsItemSelected(item);
    }
}
