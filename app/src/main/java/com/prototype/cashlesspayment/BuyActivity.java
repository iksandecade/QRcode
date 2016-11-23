package com.prototype.cashlesspayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BuyActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);

        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mScannerView.stopCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.e("handler", result.getText()); // Prints scan results
        Log.e("handler", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode)


        String saldo = result.getText();
        int isBuy = saldo.indexOf("buy");
        if (isBuy == 0) {
            saldo = saldo.replace("buy", "");
            int topup = Integer.parseInt(saldo);
            topup = SPQrCode.getSaldo(this) - topup;
            if (topup < 0) {
                Toast.makeText(this, "Saldo anda tidak mencukupi", Toast.LENGTH_SHORT).show();
            } else {
                SPQrCode.setSALDO(topup, this);
                Toast.makeText(this, "Buy Berhasil", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent();
            intent.putExtra("MESSAGE", "HELO");
            setResult(2, intent);
            mScannerView.stopCamera();
            finish();
        } else {
            Toast.makeText(this, "Buy gagal", Toast.LENGTH_SHORT).show();
            mScannerView.stopCamera();
            finish();
        }
    }
}
