package com.lamlt.piggywithfoxie;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;


public class HomeFragment extends Fragment {
    SimpleDateFormat simpleDateFormat;
    Calendar calendarStart, calendarNow;
    String mFilePath;
    private static int RESULT_LOAD_IMG = 1;
    String imgpath, storedpath;
    SharedPreferences sp;
    ImageView myImage;
    LinearLayout linearLayout;
    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 1001;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        linearLayout = view.findViewById(R.id.options);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //size image

        TextView tvCountDay = view.findViewById(R.id.tvCountDay);
        myImage = view.findViewById(R.id.imvBg);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                String[] options = {"Edit start Date", "Edit top title", "Edit bottom title", "Select wallpaper", "Reset wallpaper", "Select color love"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: //Edit start Date

                                showDatePickerDialog();
//                                countDay();

                                break;
                            case 1: //Edit top title
                                break;
                            case 2: //Edit bottom title
                                break;

                            case 3: //Select wallpaper
                                loadImagefromGallery(myImage);
                                break;
                            case 4: //Reset wallpaper
                                resetBackGround();
                                break;
                            case 5://Select color love
                                break;

                        }
                        return;
                    }


                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }

    private void resetBackGround() {
        myImage.setImageResource(R.drawable.holdhands);
//        myImage.setImageDrawable(null);
//        Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.holdhands);
//        myImage.setImageBitmap(bImage);
    }

    public void loadImagefromGallery(View view) {
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
//            if (check)
//        }
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);

            }

        }
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContext().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgpath = cursor.getString(columnIndex);
                Log.d("path", imgpath);
                cursor.close();
//
//                SharedPreferences.Editor edit = sp.edit();
//                edit.putString("imagepath", imgpath);
//                edit.commit();


                Bitmap myBitmap = BitmapFactory.decodeFile(imgpath);
                Drawable drawable = new BitmapDrawable(myBitmap);
//                myImage.setImageBitmap(myBitmap);
                myImage.setBackground(drawable);
            } else {
                Toast.makeText(getContext(), "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! do the
                    // calendar task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'switch' lines to check for other
            // permissions this app might request
        }
    }

    public void showDatePickerDialog() {
        calendarStart = Calendar.getInstance();
        int startDay = calendarStart.get(Calendar.DATE);
        int startMonth = calendarStart.get(Calendar.MONTH);
        int startYear = calendarStart.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarStart.set(year, month, dayOfMonth);
                TextView tvCountDay = getView().findViewById(R.id.tvCountDay);
                simpleDateFormat.format(calendarStart.getTimeInMillis());
            }
        }, startYear, startMonth, startDay);
        datePickerDialog.show();
    }

    public void getDateInstance() {
        calendarNow = Calendar.getInstance();
        simpleDateFormat.format(calendarNow.getTimeInMillis());

    }

    public void countDay() {
        TextView tvCountDay = getView().findViewById(R.id.tvCountDay);
        int count = (int) (calendarNow.getTimeInMillis() - calendarStart.getTimeInMillis());
        tvCountDay.setText(count);
    }


}

