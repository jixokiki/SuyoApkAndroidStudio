package com.example.suyo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midtrans.sdk.corekit.api.model.ItemDetails;
import com.midtrans.sdk.corekit.api.model.TransactionResult;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.TransactionRequest;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
//
//import com.midtrans.sdk.corekit.core.Snap;
//import com.midtrans.sdk.corekit.core.Transaction;
//import com.midtrans.sdk.corekit.core.TransactionRequest;
//import com.midtrans.sdk.corekit.models.CustomerDetails;
//import com.midtrans.sdk.corekit.models.ItemDetails;
//import com.midtrans.sdk.corekit.models.ShippingAddress;
//import com.midtrans.sdk.corekit.models.snap.TransactionResult;
//import com.midtrans.sdk.corekit.models.snap.TransactionCallback;


public class InvoiceActivity extends Activity {

    // Variabel untuk menyimpan pesanan sebelumnya
    private int previousMenu1Quantity = 0;
    private int previousMenu2Quantity = 0;
    private int previousMenu1Price = 0;
    private int previousMenu2Price = 0;

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        // Inisialisasi Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://suyo-3df59-default-rtdb.asia-southeast1.firebasedatabase.app/");

//        // Inisialisasi Midtrans Payment SDK
//        MidtransSDK.getInstance().setClientKey("SB-Mid-client-Hh7uCMvZdtRoGLNu");
//        MidtransSDK.getInstance().setTransactionRequest(initTransactionRequest());


        useEffect(); // Panggil useEffect


        // Memeriksa apakah ada intent sebelumnya
        Intent previousIntent = getIntent();
        if (previousIntent != null) {
            // Menyimpan nilai pesanan sebelumnya jika ada
            previousMenu1Quantity = previousIntent.getIntExtra("menu1_quantity", 0);
            previousMenu2Quantity = previousIntent.getIntExtra("menu2_quantity", 0);
            previousMenu1Price = previousIntent.getIntExtra("menu1_price", 0);
            previousMenu2Price = previousIntent.getIntExtra("menu2_price", 0);
        }

        // Dapatkan nilai yang disisipkan dari intent
        Intent intent = getIntent();
        int quantityMenu1 = intent.getIntExtra("menu1_quantity", 0);
        int quantityMenu2 = intent.getIntExtra("menu2_quantity", 0);
        int priceMenu1 = intent.getIntExtra("menu1_price", 0);
        int priceMenu2 = intent.getIntExtra("menu2_price", 0);

        // Tampilkan nilai di TextView pada layout baru
        TextView textViewMenu1 = findViewById(R.id.textViewMenu1);
        TextView textViewMenu2 = findViewById(R.id.textViewMenu2);
        TextView textViewPrice1 = findViewById(R.id.textViewPrice1);
        TextView textViewPrice2 = findViewById(R.id.textViewPrice2);

        textViewMenu1.setText("Mie Cocok Dua: " + quantityMenu1);
        textViewMenu2.setText("Nasi Goreng Katsu: " + quantityMenu2);
        textViewPrice1.setText("Total Harga: " + priceMenu1);
        textViewPrice2.setText("Total Harga: " + priceMenu2);

        // Dapatkan nilai yang disisipkan dari intent
        int quantityDrink1 = intent.getIntExtra("menu3_quantity", 0);
        int quantityDrink2 = intent.getIntExtra("menu4_quantity", 0);
        int priceDrink1 = intent.getIntExtra("menu3_price", 0);
        int priceDrink2 = intent.getIntExtra("menu4_price", 0);

        // Tampilkan nilai di TextView pada layout baru
        TextView textViewMenu3 = findViewById(R.id.textViewMenu3);
        TextView textViewMenu4 = findViewById(R.id.textViewMenu4);
        TextView textViewPrice3 = findViewById(R.id.textViewPrice3);
        TextView textViewPrice4 = findViewById(R.id.textViewPrice4);

        textViewMenu3.setText("Suyo Mocca: " + quantityDrink1);
        textViewMenu4.setText("Suyo Coklat: " + quantityDrink2);
        textViewPrice3.setText("Total Harga: " + priceDrink1);
        textViewPrice4.setText("Total Harga: " + priceDrink2);

        intent.putExtra("previous_menu1_quantity", previousMenu1Quantity);
        intent.putExtra("previous_menu2_quantity", previousMenu2Quantity);
        intent.putExtra("previous_menu1_price", previousMenu1Price);
        intent.putExtra("previous_menu2_price", previousMenu2Price);

        Button buttonPay = findViewById(R.id.buttonPay);
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePayment(); // Call payment initiation method
            }
        });

        // Dapatkan data dari Firebase Database
        databaseReference.child("items").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Item item = snapshot.getValue(Item.class);
                    if (item != null) {
                        // Tampilkan data pesanan di TextView pada layout InvoiceActivity
                        if (item.getMenuName().equals("Mie Cocok Dua")) {
                            TextView textViewMenu1 = findViewById(R.id.textViewMenu1);
                            textViewMenu1.setText("Mie Cocok Dua: " + item.getQuantity());
                            TextView textViewPrice1 = findViewById(R.id.textViewPrice1);
                            textViewPrice1.setText("Total Harga: " + item.getPrice());
                        } else if (item.getMenuName().equals("Nasi Goreng Katsu")) {
                            TextView textViewMenu2 = findViewById(R.id.textViewMenu2);
                            textViewMenu2.setText("Nasi Goreng Katsu: " + item.getQuantity());
                            TextView textViewPrice2 = findViewById(R.id.textViewPrice2);
                            textViewPrice2.setText("Total Harga: " + item.getPrice());
                        } else if (item.getMenuName().equals("Suyo Mocca")) {
                            TextView textViewMenu3 = findViewById(R.id.textViewMenu3);
                            textViewMenu3.setText("Suyo Mocca: " + item.getQuantity());
                            TextView textViewPrice3 = findViewById(R.id.textViewPrice3);
                            textViewPrice3.setText("Total Harga: " + item.getPrice());
                        } else if (item.getMenuName().equals("Suyo Coklat")) {
                            TextView textViewMenu4 = findViewById(R.id.textViewMenu4);
                            textViewMenu4.setText("Suyo Coklat: " + item.getQuantity());
                            TextView textViewPrice4 = findViewById(R.id.textViewPrice4);
                            textViewPrice4.setText("Total Harga: " + item.getPrice());
                        }
                    }
                }
            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Penanganan kesalahan jika ada
                Toast Toast = null;
                Toast.makeText(InvoiceActivity.this, "Gagal mendapatkan data dari Firebase: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        handlePayment(); // Panggil handlePayment

    }

//    private void useEffect() {
//        WebView webView = new WebView(this);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setDomStorageEnabled(true);
//
//        String snapScript = "https://app.sandbox.midtrans.com/snap/snap.js";
//        String clientKey = "SB-Mid-client-Hh7uCMvZdtRoGLNu"; // Ganti dengan kunci klien Anda
//        String script = "<script src=\"" + snapScript + "\" data-client-key=\"" + clientKey + "\"></script>";
//
//        webView.loadData(script, "text/html", "utf-8");
//
//        // Tambahkan WebView ke tata letak Anda jika diperlukan
//    }

    // WebView setup for loading Midtrans Snap script
    private void useEffect() {
        WebView webView = new WebView(this);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        String snapScript = "https://app.sandbox.midtrans.com/snap/snap.js";
        String clientKey = "SB-Mid-client-Hh7uCMvZdtRoGLNu"; // Replace with your client key
        String script = "<script src=\"" + snapScript + "\" data-client-key=\"" + clientKey + "\"></script>";

        webView.loadData(script, "text/html", "utf-8");
    }

    // Uncomment and implement payment initiation method
    private void initiatePayment() {
        // Code to initiate payment transaction using Midtrans SDK
    }

    // Method to set the SDK language
    private void setLocaleNew(String languageCode) {
        LocaleListCompat locales = LocaleListCompat.forLanguageTags(languageCode);
        AppCompatDelegate.setApplicationLocales(locales);
    }

    // Call setLocaleNew method with desired language code
//    setLocaleNew("en"); // for English





//    // Metode untuk menavigasi ke DrinkActivity saat pengguna mengklik "Your Drink"
//    public void chooseDrink(View view) {
//        Intent intent = new Intent(CartActivity.this, Drink.class);
//        // Mengirim pesanan sebelumnya ke DrinkActivity
//        intent.putExtra("previous_menu1_quantity", previousMenu1Quantity);
//        intent.putExtra("previous_menu2_quantity", previousMenu2Quantity);
//        intent.putExtra("previous_menu1_price", previousMenu1Price);
//        intent.putExtra("previous_menu2_price", previousMenu2Price);
//        startActivity(intent);
//    }
}
