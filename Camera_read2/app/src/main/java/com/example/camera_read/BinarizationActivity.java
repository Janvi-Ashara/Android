package com.example.camera_read;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.camera_read.R;
import com.helper.OtsuThresholder;
import com.googlecode.leptonica.android.GrayQuant;
import com.googlecode.leptonica.android.Pix;
import com.googlecode.leptonica.android.ReadFile;
import com.googlecode.leptonica.android.WriteFile;

public class BinarizationActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    public static Bitmap umbralization;
    private Pix pix;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binarization);

        img = findViewById(R.id.croppedImage);
        FloatingActionButton mFab = findViewById(R.id.nextStep);
        mFab.setOnClickListener(this);

        // Replace CropActivity.croppedImage with the actual bitmap or file path you are using
        Bitmap croppedBitmap = CropActivity.getCroppedBitmap(); // Use a method to get your bitmap
        pix = ReadFile.readBitmap(croppedBitmap);

        OtsuThresholder otsuThresholder = new OtsuThresholder();
        int threshold = otsuThresholder.doThreshold(pix.getData());
        // Adjust the threshold value
        threshold += 20;
        umbralization = WriteFile.writeBitmap(GrayQuant.pixThresholdToBinary(pix, threshold));
        img.setImageBitmap(umbralization);

        AppCompatSeekBar seekBar = findViewById(R.id.umbralization);

        seekBar.setProgress((50 * threshold) / 254);
        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // Optional: Update live preview if needed
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Optional: Handle touch start event if needed
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int threshold = (254 * seekBar.getProgress()) / 50;
        umbralization = WriteFile.writeBitmap(GrayQuant.pixThresholdToBinary(pix, threshold));
        img.setImageBitmap(umbralization);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nextStep) {
            startActivity(new Intent(BinarizationActivity.this, RecognizerActivity.class));
        }
    }
}
