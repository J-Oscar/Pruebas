package com.example.embebidos.photointent;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int TAKE_PICTURE = 1;
    private Button cameraButton;
    private ImageView image;

    private Uri path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraButton = (Button) findViewById(R.id.camera);
        image = (ImageView) findViewById(R.id.imageV);
    }

    protected void camera(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                path = createAppFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, path);
            startActivityForResult(takePictureIntent, TAKE_PICTURE);
        }
    }

    protected Uri createAppFile() throws IOException {
        String photoName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "VIEWDIT_" + photoName + ".JPG";

        String folder = Environment.DIRECTORY_PICTURES + File.separator + "ViewDit";

        File finalPhoto = new File(Environment.getExternalStoragePublicDirectory(folder), imageFileName);
        //File photoT = File.createTempFile(imageFileName, ".jpg");
        Uri photoUri = Uri.fromFile(finalPhoto);
        return photoUri;
    }
}
