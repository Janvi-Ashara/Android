package com.example.camera_read;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import com.example.camera_read.R;
import com.campcode.maanav.digimate.helper.CropImageView;



import java.io.File;
public class CropActivity extends AppCompatActivity implements View.OnClickListener {
    public static Bitmap croppedImage;
    public static Uri uriImage;
    private CropImageView cropImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        initActivity();
    }

    private void initActivity() {
        // Access the image file and generate URI
        File filePath = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Digimate");
        File fileImage = new File(filePath, MainActivity.FILE_NAMES.get(MainActivity.CURRENT_PAGE));
        uriImage = Uri.fromFile(fileImage);

        // Set URI image to display
        cropImageView = findViewById(R.id.cropImageView);
        cropImageView.setImageUriAsync(uriImage);

        // Rotate image using function and angle (if needed)
        // cropImageView.rotateImage(angle);
        // For example: cropImageView.rotateImage(-90);

        FloatingActionButton mFab = findViewById(R.id.nextStep);
        mFab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nextStep) {
            cropImageView.getCroppedImageAsync();
            cropImageView.setOnCropImageCompleteListener(new CropImageView.OnCropImageCompleteListener() {
                @Override
                public void onCropImageComplete(CropImageView view, CropImageView.CropResult result) {
                    croppedImage = result.getBitmap();
                    nextStep();
                }
            });
        }
    }

    private void nextStep() {
        startActivity(new Intent(CropActivity.this, BinarizationActivity.class));
    }
}
