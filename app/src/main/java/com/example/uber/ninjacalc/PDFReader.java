package com.example.uber.ninjacalc;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class PDFReader extends AppCompatActivity {
    private ImageView imageView;
    private Matrix matrix = new Matrix();
    private Float scale = 1f;
    public ScaleGestureDetector SGD;
    private int currentPage = 0;
    private Button next, previous;
    private static final String TAG = "Erro";
    private int response;
    private float mx,my = 0;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfreader);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        TextView textToolbar = (TextView) findViewById(R.id.toolbarTitle);
        textToolbar.setText(getIntent().getStringExtra("nome"));
        textToolbar.setTextSize(20);

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage++;
                render();
            }
        });
        previous = (Button) findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage--;
                render();
            }
        });
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, response);
        }
        render();
    }

    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void render() {
        try {
            imageView = (ImageView) findViewById(R.id.imageView);
            int REQ_WIDTH = 600;
            int REQ_HEIGHT = 900;
            String path;
            //           REQ_WIDTH = imageView.getWidth();
            //           REQ_HEIGHT = imageView.getHeight();
            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            Log.d(TAG,path);
            path = path+"/regulamento_wifikiller.pdf";
            Log.d(TAG,path);
            Bitmap bitmap = Bitmap.createBitmap(REQ_WIDTH, REQ_HEIGHT, Bitmap.Config.ARGB_4444);

            File file = new File(path);
            PdfRenderer renderer = new PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY));

            if (currentPage < 0) {
                currentPage = 0;
            } else if (currentPage > renderer.getPageCount()) {
                currentPage = renderer.getPageCount() - 1;
            }

            renderer.openPage(currentPage).render(bitmap, new Rect(0, 0, REQ_WIDTH, REQ_HEIGHT), new Matrix(), PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            imageView.setImageBitmap(bitmap);
            imageView.invalidate();

            SGD = new ScaleGestureDetector(this, new ScaleListener());

            /*Matrix m = imageView.getImageMatrix();
            Rect rect = new Rect(0, 0, REQ_WIDTH, REQ_HEIGHT);
            renderer.openPage(currentPage).render(bitmap, rect, m, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            imageView.setImageMatrix(m);
            imageView.setImageBitmap(bitmap);
            imageView.invalidate();
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale = scale * detector.getScaleFactor();
            scale = Math.max(0.1f,Math.min(scale,5f));
            matrix.setScale(scale, scale);
            imageView.setImageMatrix(matrix);
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float curX, curY;


        SGD.onTouchEvent(event);
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mx = event.getX();
                my = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                curX = event.getX();
                curY = event.getY();
                imageView.scrollBy((int) (mx - curX), (int) (my - curY));
                mx = curX;
                my = curY;
                break;
            case MotionEvent.ACTION_UP:
                curX = event.getX();
                curY = event.getY();
                imageView.scrollBy((int) (mx - curX), (int) (my - curY));
                break;
        }
        return true;
    }
}
