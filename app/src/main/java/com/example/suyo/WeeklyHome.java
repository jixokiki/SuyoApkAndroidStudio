package com.example.suyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WeeklyHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);

        Button btnNextMonth = findViewById(R.id.btnNextMonth);
        btnNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pindah ke layout selanjutnya
                Intent intent = new Intent(WeeklyHome.this, Food.class);
                startActivity(intent);
            }
        });

        Button btnNextWeek = findViewById(R.id.btnNextWeek);
        btnNextWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pindah ke layout selanjutnya
                Intent intent = new Intent(WeeklyHome.this, Drink.class);
                startActivity(intent);
            }
        });

        Button btnNextDay = findViewById(R.id.btnNextDay);
        btnNextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pindah ke layout selanjutnya
                Intent intent = new Intent(WeeklyHome.this, Food.class);
                startActivity(intent);
            }
        });

        Button btnViewCard = findViewById(R.id.buttonViewCart);
        btnViewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeeklyHome.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    // Metode berikutnya dimulai di sini
    public void chooseDrink(View view) {
        Intent intent = new Intent(this, Drink.class);
        startActivity(intent);
    }
}
