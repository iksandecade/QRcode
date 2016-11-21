package project.iksandecade.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class TopupActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);
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
        int isBuy = saldo.indexOf("topup");
        if (isBuy == 0) {
            saldo = saldo.replace("topup", "");
            int topup = Integer.parseInt(saldo);
            topup = SPQrCode.getSaldo(this) + topup;
            SPQrCode.setSALDO(topup, this);
            Toast.makeText(this, "Topup Berhasil", Toast.LENGTH_SHORT).show();
            mScannerView.stopCamera();
            Intent intent = new Intent();
            intent.putExtra("MESSAGE", "HELO");
            setResult(2, intent);
            finish();
        } else {
            Toast.makeText(this, "Topup gagal", Toast.LENGTH_SHORT).show();
            mScannerView.stopCamera();
            finish();
        }
    }
}
