package com.prototype.cashlesspayment;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class PanitiaTopupActivity extends AppCompatActivity {
    public final static int WIDTH = 500;
    ImageView ivTopup;
    EditText etTopup;
    ProgressBar pbLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panitia_topup);
        ivTopup = (ImageView) findViewById(R.id.ivTopup);
        etTopup = (EditText) findViewById(R.id.etTopup);
        pbLoad = (ProgressBar) findViewById(R.id.pbLoad);
        setLoading(false);
    }

    public void creates(View view) {
        final String harga = "topup" + etTopup.getText().toString();
        setLoading(true);
        Thread t = new Thread(new Runnable() {
            public void run() {

                try {
                    synchronized (this) {
                        wait(5000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Bitmap bitmap = null;

                                    bitmap = encodeAsBitmap(harga);
                                    ivTopup.setImageBitmap(bitmap);
                                    setLoading(false);
                                } catch (WriterException e) {
                                    e.printStackTrace();
                                } // end of catch block

                            } // end of run method
                        });

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });
        t.start();
    }

    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? getResources().getColor(R.color.black) : getResources().getColor(R.color.white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, 500, 0, 0, w, h);
        return bitmap;
    } /// end of this method

    public void setLoading(Boolean status) {
        if (status) {
            ivTopup.setVisibility(View.GONE);
            pbLoad.setVisibility(View.VISIBLE);
        } else {
            ivTopup.setVisibility(View.VISIBLE);
            pbLoad.setVisibility(View.GONE);
        }
    }
}
