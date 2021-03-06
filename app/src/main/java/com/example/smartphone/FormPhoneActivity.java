package com.example.smartphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartphone.model.Phone;
import com.google.android.material.textfield.TextInputLayout;

public class FormPhoneActivity extends AppCompatActivity {

    Button btnSimpan;
    TextInputLayout til_spek, til_hrg, til_des;
    Spinner spnbrand;
    final String[] tipeLis = {Phone.APPLE, Phone.SAMSUNG, Phone.VIVO, Phone.OPPO, Phone.XIOMI};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_phone);
        inisialisasiView();
    }

    private void inisialisasiView() {
        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(view -> simpan());
        til_spek = findViewById(R.id.til_speks);
        til_hrg = findViewById(R.id.til_harga);
        til_des = findViewById(R.id.til_des);
        spnbrand = findViewById(R.id.spn_brand);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                tipeLis
        );
        spnbrand.setAdapter(adapter);
        spnbrand.setSelection(0);
    }

    private void simpan() {
        if (isDataValid()) {
            Phone tr = new Phone();
            tr.setSpesifikasi(til_spek.getEditText().getText().toString());
            tr.setHarga(til_hrg.getEditText().getText().toString());
            tr.setModel(til_des.getEditText().getText().toString());
            tr.setBrand(spnbrand.getSelectedItem().toString());
            SharePereferenceUtility.addLis(this,tr);
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

            // Kembali ke layar sebelumnya setelah 500 ms (0.5 detik)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);


        }
    }

    private boolean isDataValid() {
        if (til_spek.getEditText().getText().toString().isEmpty()
                || til_hrg.getEditText().getText().toString().isEmpty()
                || til_des.getEditText().getText().toString().isEmpty()
                || spnbrand.getSelectedItem().toString().isEmpty()
        ) {
            Toast.makeText(this, "Data tidak bileh ada yang kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}