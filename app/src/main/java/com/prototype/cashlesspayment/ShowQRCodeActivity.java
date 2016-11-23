package com.prototype.cashlesspayment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowQRCodeActivity extends AppCompatActivity {
    public final static int WIDTH = 500;
    ImageView ivBarCode;
    ProgressBar pbLoad;
    List<String> stringList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_qrcode);
        ivBarCode = (ImageView) findViewById(R.id.ivBarcode);
        pbLoad = (ProgressBar) findViewById(R.id.pbLoad);
        setLoading(true);
        Thread t = new Thread(new Runnable() {
            public void run() {
                stringList.add("VIP");
                stringList.add("Reguler");
                stringList.add("Premium");
                Collections.shuffle(stringList);

                try {
                    synchronized (this) {
                        wait(5000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Bitmap bitmap = null;

                                    bitmap = encodeAsBitmap(stringList.get(0));
                                    ivBarCode.setImageBitmap(bitmap);
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


    // this is method call from on create and return bitmap image of QRCode.
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
            ivBarCode.setVisibility(View.GONE);
            pbLoad.setVisibility(View.VISIBLE);
        } else {
            ivBarCode.setVisibility(View.VISIBLE);
            pbLoad.setVisibility(View.GONE);
        }
    }

}
