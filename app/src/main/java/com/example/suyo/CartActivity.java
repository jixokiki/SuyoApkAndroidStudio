package com.example.suyo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartActivity extends Activity {

    // Variabel untuk menyimpan pesanan sebelumnya
    private int previousMenu1Quantity = 0;
    private int previousMenu2Quantity = 0;
    private int previousMenu1Price = 0;
    private int previousMenu2Price = 0;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);

        // Inisialisasi Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://suyo-3df59-default-rtdb.asia-southeast1.firebasedatabase.app/");

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

        // Inisialisasi button Invoice
        Button invoiceButton = findViewById(R.id.addButton2);
        invoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Panggil metode untuk menyimpan data ke Firebase saat tombol Invoice diklik
                saveDataToFirebase();

                // Inisialisasi intent ke InvoiceActivity
                Intent intent = new Intent(CartActivity.this, InvoiceActivity.class);
                startActivity(intent);
            }
        });

    }

    // Method untuk menambah data ke Firebase Database
    private void addItemToFirebase(String menu, int quantity, int price) {
        Item item = new Item(menu, quantity, price);
        databaseReference.child("items").push().setValue(item)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Berhasil menambahkan data
                        Toast.makeText(CartActivity.this, "Data berhasil ditambahkan ke Firebase", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Gagal menambahkan data
                        Toast.makeText(CartActivity.this, "Gagal menambahkan data ke Firebase: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Metode untuk menyimpan data ke Firebase saat tombol Invoice diklik
    private void saveDataToFirebase() {
        // Ambil nilai dari TextView
        TextView textViewMenu1 = findViewById(R.id.textViewMenu1);
        TextView textViewMenu2 = findViewById(R.id.textViewMenu2);
        TextView textViewMenu3 = findViewById(R.id.textViewMenu3);
        TextView textViewMenu4 = findViewById(R.id.textViewMenu4);
        TextView textViewPrice1 = findViewById(R.id.textViewPrice1);
        TextView textViewPrice2 = findViewById(R.id.textViewPrice2);
        TextView textViewPrice3 = findViewById(R.id.textViewPrice3);
        TextView textViewPrice4 = findViewById(R.id.textViewPrice4);

        // Ambil hanya angka dari teks dan ubah menjadi integer
        int quantityMenu1 = extractQuantity(textViewMenu1.getText().toString());
        int quantityMenu2 = extractQuantity(textViewMenu2.getText().toString());
        int quantityMenu3 = extractQuantity(textViewMenu3.getText().toString());
        int quantityMenu4 = extractQuantity(textViewMenu4.getText().toString());

        int priceMenu1 = extractPrice(textViewPrice1.getText().toString());
        int priceMenu2 = extractPrice(textViewPrice2.getText().toString());
        int priceMenu3 = extractPrice(textViewPrice3.getText().toString());
        int priceMenu4 = extractPrice(textViewPrice4.getText().toString());

        // Simpan data ke Firebase
        addItemToFirebase("Mie Cocok Dua", quantityMenu1, priceMenu1);
        addItemToFirebase("Nasi Goreng Katsu", quantityMenu2, priceMenu2);
        addItemToFirebase("Suyo Mocca", quantityMenu3, priceMenu3);
        addItemToFirebase("Suyo Coklat", quantityMenu4, priceMenu4);
    }

    // Metode untuk mengekstrak jumlah dari teks dan mengembalikannya sebagai integer
    private int extractQuantity(String text) {
        String[] parts = text.split(": ");
        if (parts.length > 1) {
            return Integer.parseInt(parts[1]);
        } else {
            return 0;
        }
    }

    // Metode untuk mengekstrak harga dari teks dan mengembalikannya sebagai integer
    private int extractPrice(String text) {
        String[] parts = text.split(": ");
        if (parts.length > 1) {
            return Integer.parseInt(parts[1]);
        } else {
            return 0;
        }
    }


    // Metode untuk menavigasi ke DrinkActivity saat pengguna mengklik "Your Drink"
    public void chooseDrink(View view) {
        Intent intent = new Intent(CartActivity.this, Drink.class);
        // Mengirim pesanan sebelumnya ke DrinkActivity
        intent.putExtra("previous_menu1_quantity", previousMenu1Quantity);
        intent.putExtra("previous_menu2_quantity", previousMenu2Quantity);
        intent.putExtra("previous_menu1_price", previousMenu1Price);
        intent.putExtra("previous_menu2_price", previousMenu2Price);
        startActivity(intent);
    }
}
