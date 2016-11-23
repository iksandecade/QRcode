package com.prototype.cashlesspayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class PanitiaActivity extends AppCompatActivity {

    TextView tvPanitia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panitia);
        tvPanitia = (TextView) findViewById(R.id.tvPanitia);
    }

    public void toScan(View view) {
        Intent i = new Intent(PanitiaActivity.this, ScanActivity.class);
        startActivityForResult(i, 1);
    }

    public void toTopup(View view) {
        Intent i = new Intent(PanitiaActivity.this, PanitiaTopupActivity.class);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            tvPanitia.setText(data.getStringExtra("msg"));
        }
    }
}
