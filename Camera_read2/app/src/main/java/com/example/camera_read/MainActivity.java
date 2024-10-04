package com.example.camera_read;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.camera_read.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String TITLE;
    public static int TOTAL_PAGES;
    public static String CONTENT;
    public static int CURRENT_PAGE;
    public static String DIRECTORY_PATH = "/Android/data/com.campcode.janvi.digimate";
    public static ArrayList<String> FILE_NAMES = new ArrayList<>();
    private EditText editTitle, editPages;
    private Button buttonGenerate;

    // Permission request codes
    private static final int PERMISSION_REQUEST_CODE_CAMERA = 1;
    private static final int PERMISSION_REQUEST_CODE_EXTERNAL_STORAGE = 2;
    private static final int PERMISSION_REQUEST_CODE_CONTACTS = 3;

    // Permission result launcher
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission granted
                    handlePermissionGranted();
                } else {
                    // Permission denied
                    Toast.makeText(MainActivity.this, "Permission not granted.", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActivity();

        buttonGenerate.setOnClickListener(v -> {
            TITLE = editTitle.getText().toString();
            String pages = editPages.getText().toString();
            if (TITLE.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter the title of PDF", Toast.LENGTH_SHORT).show();
            } else if (pages.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter the total pages in PDF", Toast.LENGTH_SHORT).show();
            } else {
                TOTAL_PAGES = Integer.parseInt(pages);
                CURRENT_PAGE = 0;
                CONTENT = "";
                if (CURRENT_PAGE < TOTAL_PAGES) {
                    checkPermissionsAndStartCamera();
                }
            }
        });
    }

    private void initActivity() {
        // Initialize the view elements
        editTitle = findViewById(R.id.editTitle);
        editPages = findViewById(R.id.editPages);
        buttonGenerate = findViewById(R.id.buttonGenerate);
    }

    private void checkPermissionsAndStartCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA);
        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.WRITE_CONTACTS);
        } else {
            startCamera();
        }
    }

    private void handlePermissionGranted() {
        // Check which permission was granted and proceed accordingly
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    startCamera();
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.WRITE_CONTACTS);
                }
            } else {
                requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    private void startCamera() {
        long TIME_OUT = 100;
        new Handler().postDelayed(() -> startActivity(new Intent(MainActivity.this, CaptureActivity.class)), TIME_OUT);
    }
}
