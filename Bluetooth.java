package com.arashad96.andoriddeveloperadvancedkit;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class Bluetooth extends AppCompatActivity {
    BluetoothAdapter ba;
    Button turnoff;
    Button turnon;
    Button discoverable;
    Button view;
    Button github;
    Button info;
    ListView listofdevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bluetooth);

        ba = BluetoothAdapter.getDefaultAdapter();
        turnoff = findViewById(R.id.turnoff);
        turnon = findViewById(R.id.turnon);
        discoverable = findViewById(R.id.discoverable);
        view = findViewById(R.id.view);
        listofdevices = findViewById(R.id.listofdevices);
        //check bluetooth is on
        if (ba.isEnabled()) {
            Toast.makeText(this, "Bluetooth is on", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bluetooth is off", Toast.LENGTH_SHORT).show();
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set<BluetoothDevice> paireddevices = ba.getBondedDevices();

                ArrayList paireddeviceslist = new ArrayList();

                for (BluetoothDevice bluetoothDevice : paireddevices){
                    paireddeviceslist.add(bluetoothDevice.getName());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(Bluetooth.this, android.R.layout.simple_list_item_1, paireddeviceslist);
                listofdevices.setAdapter(arrayAdapter);
            }
        });
        discoverable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivity(i);
            }
        });
        turnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ba.enable();
                Toast.makeText(Bluetooth.this, "Bluetooth is turned on", Toast.LENGTH_SHORT).show();
            }
        });
        turnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ba.disable();
                Toast.makeText(Bluetooth.this, "Bluetooth is turned off", Toast.LENGTH_SHORT).show();
            }
        });

        github = findViewById(R.id.github);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ARashad96/Bluetooth_All_API_Levels-including-pie"));
                startActivity(intent);
            }
        });
        info = findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new android.app.AlertDialog.Builder(Bluetooth.this)
                        .setIcon(R.drawable.profile)
                        .setTitle("App info")
                        .setMessage("This app is a simple introduction on using the bluetooth using listview, toast, button, bluetooth manager and linearlayout.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });
    }
}
