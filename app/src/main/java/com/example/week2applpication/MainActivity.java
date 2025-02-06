package com.example.week2applpication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

//    Explicit (internal code) vs implicit (external code) intent
//    Only the main activity started automatically, the rest have to be activated manually

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView iv_car = findViewById(R.id.iv_car);
        ImageView iv_plane = findViewById(R.id.iv_plane);
        ImageView iv_phone = findViewById(R.id.iv_phone);

        iv_car.setOnClickListener(v -> startSecondActivity("car"));
        iv_plane.setOnClickListener(v -> startSecondActivity("plane"));

        iv_phone.setOnClickListener(v -> startImplicitIntentActivity());
    }

        public void startSecondActivity(String from) {
            Intent intent;

            intent = new Intent(this, SecondActivity.class); // Explicit intent - create mission
            intent.putExtra("parameter", from); // add parameter to mission
            startActivity(intent); // launch mission
        }

        public void startImplicitIntentActivity() {
            Intent intent;

            intent = new Intent(Intent.ACTION_DIAL); // Since we just open phone app without modifying the number, we don't need permissions as this is "safe"
            intent.setData(Uri.parse("tel:0123433239"));
            startActivity(intent);
        }
}