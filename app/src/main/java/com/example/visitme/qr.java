package com.example.visitme;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class qr extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qr = new ZXingScannerView(this);
        setContentView(qr);

        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        qr.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void handleResult(Result rawResult) {
        //MainActivity.scantext.setText(rawResult.getText());

        Intent intent = new Intent(getBaseContext(), Extraction.class);
        intent.putExtra("ScanQR",rawResult.getText());
        startActivity(intent);
        //onBackPressed();
    }


    @Override
    protected void onResume() {
        super.onResume();
        qr.setResultHandler(this);
        qr.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qr.stopCamera();
    }
}