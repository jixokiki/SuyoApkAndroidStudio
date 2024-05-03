package com.example.suyo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Food extends Activity {
    int quantityMenu1 = 0;

    int quantityMenu2 = 0;

    private TextView quantityMenu1TextView;
    private TextView quantityMenu2TextView;
    private TextView priceMenu1TextView;
    private TextView priceMenu2TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        quantityMenu1TextView = findViewById(R.id.quantityMenu1);
        quantityMenu2TextView = findViewById(R.id.quantityMenu2);
        priceMenu1TextView = findViewById(R.id.priceMenu1);
        priceMenu2TextView = findViewById(R.id.priceMenu2);
    }

    public void onPlusButtonClick(View view) {
        if (view.getId() == R.id.btnPlusMenu1) {
            quantityMenu1++;
            displayQuantityMenu1(quantityMenu1);
            quantityMenu1TextView.setText(String.valueOf(quantityMenu1));
            priceMenu1TextView.setText(String.valueOf(quantityMenu1 * 12000));
        }
    }


    public void onMinusButtonClick(View view) {
        if (view.getId() == R.id.btnMinusMenu1) {
            if (quantityMenu1 > 0) {
                quantityMenu1--;
                displayQuantityMenu1(quantityMenu1);
                quantityMenu1TextView.setText(String.valueOf(quantityMenu1));
                priceMenu1TextView.setText(String.valueOf(quantityMenu1 * 12000));
            }
        }
    }

    public void onAddToCartClick(View view) {
        if (view.getId() == R.id.btnAddToCartMenu1) {
            // Buat intent untuk memulai aktivitas baru
            Intent intent = new Intent(Food.this, CartActivity.class);

            // Sisipkan data ke intent
            intent.putExtra("menu1_name", "Mie Cocok Dua");
            intent.putExtra("menu1_quantity", quantityMenu1);
            intent.putExtra("menu1_price", quantityMenu1 * 12000);

            intent.putExtra("menu2_name", "Nasi Goreng Katsu");
            intent.putExtra("menu2_quantity", quantityMenu2);
            intent.putExtra("menu2_price", quantityMenu2 * 15000);

            // Mulai aktivitas baru dengan intent yang sudah disiapkan
            startActivity(intent);
        }
    }




    private void displayQuantityMenu1(int quantity) {
        TextView quantityTextView = findViewById(R.id.quantityMenu1);
        quantityTextView.setText(String.valueOf(quantity));
    }

    //
    public void onPlusButtonClick2(View view) {
        if (view.getId() == R.id.btnPlusMenu2) {
            quantityMenu2++;
            displayQuantityMenu2(quantityMenu2);
            quantityMenu2TextView.setText(String.valueOf(quantityMenu2));
            priceMenu2TextView.setText(String.valueOf(quantityMenu2 * 15000));
        }
    }


    public void onMinusButtonClick2(View view) {
        if (view.getId() == R.id.btnMinusMenu2) {
            if (quantityMenu2 > 0) {
                quantityMenu2--;
                displayQuantityMenu1(quantityMenu2);
                quantityMenu2TextView.setText(String.valueOf(quantityMenu2));
                priceMenu2TextView.setText(String.valueOf(quantityMenu2 * 15000));
            }
        }
    }


    private void displayQuantityMenu2(int quantity) {
        TextView quantityTextView = findViewById(R.id.quantityMenu2);
        quantityTextView.setText(String.valueOf(quantity));
    }
}
