package project.iksandecade.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
    }

    public void toUser(View view) {
        Intent i = new Intent(SelectActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void toPanitia(View view){
        Intent i = new Intent(SelectActivity.this, PanitiaActivity.class);
        startActivity(i);
    }

    public void toSeller(View view){
        Intent i = new Intent(SelectActivity.this, SellerActivity.class);
        startActivity(i);
    }
}
