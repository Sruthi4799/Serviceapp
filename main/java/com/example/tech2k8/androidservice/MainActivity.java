package com.example.tech2k8.androidservice;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.image);
    }

    public void startService(View view) {
        Intent service =new Intent(MainActivity.this,BackgroundService.class);
        //

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(service);
        }
        else
        {
            startService(service);
        }

    }

    public void intentService(View view) {

        Intent intentService =new Intent(MainActivity.this,BackgroundIntentService.class);
        startService(intentService);
    }

    public void alertDialog(View view) {
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(" Caution");
        builder.setMessage("Are you really want to exit");
        builder.setPositiveButton("Yes, Exit me", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "user logged out", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No, Keep me here", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "not exited", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

    public void customDialog(View view) {

        Dialog dialog =new Dialog(MainActivity.this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_view,null,false);
        dialog.setContentView(dialogView);
        Button yes = dialogView.findViewById(R.id.yes);
        Button no = dialogView.findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "yes you are exited", Toast.LENGTH_SHORT).show();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    public void getImage(View view) {

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            checkForPermission();
        }
        else
        {
        Intent openCam =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCam,2001);
        }

    }


    private void checkForPermission()
    {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                1001
                );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==1001)
        {
            if (grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                Intent openCam =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(openCam,2001);
            }
            else
            {
                Toast.makeText(this, "permission dennied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("MainActivity","result obtained");
        if (requestCode==2001)
        {
            Log.i("MainActivity","request code is valid");
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }
}
