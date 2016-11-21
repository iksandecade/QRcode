package project.iksandecade.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvSaldo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSaldo = (TextView) findViewById(R.id.tvSaldo);
        tvSaldo.setText(SPQrCode.getSaldo(this) + "");
    }

    public void toTopUp(View view) {
        Intent intent = new Intent(MainActivity.this, TopupActivity.class);
        startActivityForResult(intent, 1);
    }

    public void toBuy(View view) {
        Intent intent = new Intent(MainActivity.this, BuyActivity.class);
        startActivityForResult(intent, 1);
    }

    public void toShowQR(View view){
        Intent intent = new Intent(MainActivity.this, ShowQRCodeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 2){
            tvSaldo.setText(SPQrCode.getSaldo(this) + "");
        }
    }
}
