package com.example.suyo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Drink extends Activity {
    int quantityDrink1 = 0;

    int quantityDrink2 = 0;

    private TextView quantityDrink1TextView;
    private TextView quantityDrink2TextView;
    private TextView priceDrink1TextView;
    private TextView priceDrink2TextView;
    private int previousMenu1Quantity;
    private int previousMenu2Quantity;
    private int previousMenu1Price;
    private int previousMenu2Price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Mendapatkan data pesanan sebelumnya dari Intent
        Intent previousIntent = getIntent();
        previousMenu1Quantity = previousIntent.getIntExtra("previous_menu1_quantity", 0);
        previousMenu2Quantity = previousIntent.getIntExtra("previous_menu2_quantity", 0);
        previousMenu1Price = previousIntent.getIntExtra("previous_menu1_price", 0);
        previousMenu2Price = previousIntent.getIntExtra("previous_menu2_price", 0);

        quantityDrink1TextView = findViewById(R.id.quantityDrink1);
        quantityDrink2TextView = findViewById(R.id.quantityDrink2);
        priceDrink1TextView = findViewById(R.id.priceDrink1);
        priceDrink2TextView = findViewById(R.id.priceDrink2);

//        // Menampilkan pesanan sebelumnya
//        displayQuantityMenu1(previousMenu1Quantity);
//        displayQuantityMenu2(previousMenu2Quantity);
//        priceDrink1TextView.setText(String.valueOf(previousMenu1Price));
//        priceDrink2TextView.setText(String.valueOf(previousMenu2Price));
    }

    public void onPlusButtonClick(View view) {
        if (view.getId() == R.id.btnPlusMenu1) {
            quantityDrink1++;
            displayQuantityMenu1(quantityDrink1);
            quantityDrink1TextView.setText(String.valueOf(quantityDrink1));
            priceDrink1TextView.setText(String.valueOf(quantityDrink1 * 10000));
        }
    }


    public void onMinusButtonClick(View view) {
        if (view.getId() == R.id.btnMinusMenu1) {
            if (quantityDrink1 > 0) {
                quantityDrink1--;
                displayQuantityMenu1(quantityDrink1);
                quantityDrink1TextView.setText(String.valueOf(quantityDrink1));
                priceDrink1TextView.setText(String.valueOf(quantityDrink1 * 10000));
            }
        }
    }

    public void onAddToCartClick(View view) {
        if (view.getId() == R.id.btnAddToCartMenu1) {
            // Buat intent untuk memulai aktivitas baru
            Intent intent = new Intent(this, CartActivity.class);

            // Sisipkan data ke intent
            intent.putExtra("menu3_name", "Suyo Mocca");
            intent.putExtra("menu3_quantity", quantityDrink1);
            intent.putExtra("menu3_price", quantityDrink1 * 10000);

            intent.putExtra("menu4_name", "Suyo Cokelat");
            intent.putExtra("menu4_quantity", quantityDrink2);
            intent.putExtra("menu4_price", quantityDrink2 * 10000);

            // Mengirim data pesanan minuman yang baru ditambahkan
            intent.putExtra("menu3_quantity", quantityDrink1);
            intent.putExtra("menu4_quantity", quantityDrink2);
            intent.putExtra("menu3_price", quantityDrink1 * 10000);
            intent.putExtra("menu4_price", quantityDrink2 * 10000);

            // Mengirim juga data pesanan sebelumnya
            intent.putExtra("menu1_quantity", previousMenu1Quantity);
            intent.putExtra("menu2_quantity", previousMenu2Quantity);
            intent.putExtra("menu1_price", previousMenu1Price);
            intent.putExtra("menu2_price", previousMenu2Price);

            // Mulai aktivitas baru dengan intent yang sudah disiapkan
            startActivity(intent);
        }
    }


    private void displayQuantityMenu1(int quantity) {
        TextView quantityTextView = findViewById(R.id.quantityDrink1);
        quantityTextView.setText(String.valueOf(quantity));
        quantityDrink1TextView.setText(String.valueOf(quantity));
    }

    //
    public void onPlusButtonClick2(View view) {
        if (view.getId() == R.id.btnPlusMenu2) {
            quantityDrink2++;
            displayQuantityMenu2(quantityDrink2);
            quantityDrink2TextView.setText(String.valueOf(quantityDrink2));
            priceDrink2TextView.setText(String.valueOf(quantityDrink2 * 10000));
        }
    }


    public void onMinusButtonClick2(View view) {
        if (view.getId() == R.id.btnMinusMenu2) {
            if (quantityDrink2 > 0) {
                quantityDrink2--;
                displayQuantityMenu1(quantityDrink2);
                quantityDrink2TextView.setText(String.valueOf(quantityDrink2));
                priceDrink2TextView.setText(String.valueOf(quantityDrink2 * 10000));
            }
        }
    }


    private void displayQuantityMenu2(int quantity) {
        TextView quantityTextView = findViewById(R.id.quantityDrink2);
        quantityTextView.setText(String.valueOf(quantity));
        quantityDrink2TextView.setText(String.valueOf(quantity));
    }
}
