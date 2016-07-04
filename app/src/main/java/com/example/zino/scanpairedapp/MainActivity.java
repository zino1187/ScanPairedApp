package com.example.zino.scanpairedapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    //NFC와 마찬가지로 안드로이드 개발자가 칩을 직접 제어할 수는 없으므로
    //어댑터 객체를 통해 간접 제어해야 한다...
    BluetoothAdapter bluetoothAdapter;

    //페어링된 기기목록
    Set<BluetoothDevice> paredDevices;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "this device is not supported bluethooth", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "블루투스 사용이 가능한 디바이스 입니다", Toast.LENGTH_LONG).show();
            enableBluetooth();
        }
    }

    //블루투스 활성화 요청 메서드!!
    public void enableBluetooth(){
        if(!bluetoothAdapter.isEnabled()){
            Toast.makeText(MainActivity.this, "앱 사용을 위해서는 블루투스를 활성화해야 합니다", Toast.LENGTH_SHORT).show();

            //개발자가 켜자!!
            Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 1);
        }
    }


    //페어링된 블루투스 검색하여 출력
    public void scanPaired() {
        paredDevices=bluetoothAdapter.getBondedDevices();

        for(BluetoothDevice device : paredDevices){

        }

        Toast.makeText(MainActivity.this, "페어링된 기기수는 "+paredDevices.size(), Toast.LENGTH_SHORT).show();
    }

    public void btnClick(View view) {
        scanPaired();
    }
}
