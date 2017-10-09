package com.ahmadlufiau.tekssuara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void TeksKeSuara(View view) {
        Intent intent = new Intent(this, TekssuaraActivity.class);
        startActivity(intent);
    }

    public void aboutUs(View view) {
        Intent intent = new Intent(this, AboutusActivity.class);
        startActivity(intent);
    }
}
