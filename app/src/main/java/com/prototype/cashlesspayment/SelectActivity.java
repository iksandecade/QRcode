package com.prototype.cashlesspayment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class SelectActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    Activity activity;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        activity = this;
        context = this;
        if (!checkPermission())
            requestPermission();

    }

    public void toUser(View view) {
        Intent i = new Intent(SelectActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void toPanitia(View view) {
        Intent i = new Intent(SelectActivity.this, PanitiaActivity.class);
        startActivity(i);
    }

    public void toSeller(View view) {
        Intent i = new Intent(SelectActivity.this, SellerActivity.class);
        startActivity(i);
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {

            return false;

        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {

            Toast.makeText(context, "Camera permission allows us to access barcode scanner. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
            finish();
        } else {

            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);

        }
    }
}
