package com.lamlt.piggywithfoxie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends BaseActivityFullScreen {

    ViewPager viewPager;
    ImageView imgbg;
    String mFilePath;
    private static int RESULT_LOAD_IMG = 1;
    String imgpath, storedpath;
    SharedPreferences sp;
    ImageView myImage;
Button btn;
    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        hideStatusBar();
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.viewpager);
        viewPager.setAdapter(new SliderAdapter(getSupportFragmentManager()));
        imgbg = findViewById(R.id.imvBg);
//        btn=findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadImagefromGallery(imgbg);
//            }
//        });
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
//            // Permission is not granted
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//            } else {
//                // No explanation needed; request the permission
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                        MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
//
//            }
//
//        }
//        try {
//            // When an Image is picked
//            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
//                    && null != data) {
//                // Get the Image from data
//
//                Uri selectedImage = data.getData();
//                String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//                // Get the cursor
//                Cursor cursor = this.getContentResolver().query(selectedImage,
//                        filePathColumn, null, null, null);
//                // Move to first row
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                imgpath = cursor.getString(columnIndex);
//                Log.d("path", imgpath);
//                cursor.close();
//
//                SharedPreferences.Editor edit=sp.edit();
//                edit.putString("imagepath",imgpath);
//                edit.commit();

//
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgpath);
//                Drawable drawable = new BitmapDrawable(myBitmap);
////                myImage.setImageBitmap(myBitmap);
//                myImage.setBackground(drawable);
//            }
//            else {
//                Toast.makeText(this, "You haven't picked Image",
//                        Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
//                    .show();
//        }
//    }
//    public void loadImagefromGallery(View view) {
////        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
////            if (check)
////        }
//        // Create intent to Open Image applications like Gallery, Google Photos
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        // Start the Intent
//        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE: {
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // permission was granted, yay! do the
//                    // calendar task you need to do.
//                } else {
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//            }
//            // other 'switch' lines to check for other
//            // permissions this app might request
//        }
//    }
}
}
