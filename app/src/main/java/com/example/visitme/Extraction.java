package com.example.visitme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extraction extends AppCompatActivity {

    FloatingActionButton save;
    EditText nom , entreprise , titre , adresse , mobile_1 , mobile_2 , fixe , fax , email , site_web , note ;
    ImageView mPreviewIv ;

    //mil main
    private static final  int CAMERA_REQUEST_CODE = 200;
    private static final  int STORAGE_REQUEST_CODE = 400;
    private static final  int IMAGE_PICK_GALLERY_CODE = 1000;
    private static final  int IMAGE_PICK_CAMERA_CODE = 1001;
    Uri AI;
    String scanResult;
    String QRresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extraction);
            // lier les variables a leurs id
            save = findViewById(R.id.save);
            nom = findViewById(R.id.nom_prénom);
            entreprise = findViewById(R.id.entreprise);
            titre = findViewById(R.id.titre);
            adresse = findViewById(R.id.adresse);
            mobile_1 = findViewById(R.id.mobile_1);
            mobile_2 = findViewById(R.id.mobile_2);
            fixe = findViewById(R.id.fix);
            fax = findViewById(R.id.fax);
            email = findViewById(R.id.email);
            site_web = findViewById(R.id.site_web);
            note = findViewById(R.id.note);
            mPreviewIv = findViewById(R.id.mPreviewIv);


        scanResult=getIntent().getExtras().getString("Scantext","defaultKey");
        if(scanResult!="defaultKey"){ extractdata(scanResult);}

        AI = getIntent().getParcelableExtra("uri");
        mPreviewIv.setImageURI(AI);

        QRresult=getIntent().getExtras().getString("ScanQR","defaultKey");
        if(QRresult!="defaultKey"){extractdata(QRresult);}

            
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void extractdata(String s) {
        //scantext.setText(visionText.getText());
        //String s=(visionText.getText());
        nom.setText(s);
        /////////////////////////////////////////////////extraction d'email////////////////////////////////////////////////
         Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+\\s?@\\s?[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(s);
         while (m.find()) {
         email.setText(m.group());
         }
        ////////////////////////////////////////////////fin extraction email //////////////////////////////////////////

        /////////////////////////////////////////////////extraction mobile ////////////////////////////////////////////////
        String mobiles = "";
        Matcher n = Pattern.compile("\\(?((\\+|00)?216)?\\)?\\s?\\.?(((40|41|42|44|46|56|50|51|52|53|54|55|58)\\s?\\.?[0-9]{3}\\s?\\.?[0-9]{3})|((9|2)[0-9]\\s?\\.?[0-9]{3}\\s?\\.?[0-9]{3})|((40|41|42|44|46|56|50|51|52|53|54|55|58)\\s?\\.?[0-9]{2}\\s?\\.?[0-9]{2}\\s?\\.?[0-9]{2})|((9|2)[0-9]\\s?\\.?[0-9]{2}\\s?\\.?[0-9]{2}\\s?\\.?[0-9]{2}))").matcher(s);
        while (n.find()) {
            mobiles = mobiles+"\n" + n.group();
        }
        mobile_1.setText(mobiles);
        ////////////////////////////////////////////////fin extraction mobile //////////////////////////////////////////

        /////////////////////////////////////////////////extraction fix ////////////////////////////////////////////////
        String fix = "";
        Matcher k = Pattern.compile("\\(?((\\+|00)216)?\\)?\\s?\\.?((30|31|32|36|39|80|82)\\s?\\.?[0-9]{2}\\s?\\.?[0-9]{2}\\s?\\.?[0-9]{2}|((7)[0-9]\\s?\\.?[0-9]{2}\\s?\\.?[0-9]{2}\\s?\\.?[0-9]{2})|(30|31|32|36|39|80|82)\\s?\\.?[0-9]{3}\\s?\\.?[0-9]{3}|((7)[0-9]\\s?\\.?[0-9]{3}\\s?\\.?[0-9]{3}))").matcher(s);
        while (k.find()) {
            fix = fix +"\n" + k.group();
        }
        fixe.setText(fix);
        ////////////////////////////////////////////////fin extraction fix //////////////////////////////////////////

        /////////////////////////////////////////////////extraction site web ////////////////////////////////////////////////
        String bb = "";
        Matcher v = Pattern.compile("((www\\.)|(WWW\\.))?[a-zA-Z0-9-_.]+\\.([a-zA-Z]{2,8})(\\.[a-zA-Z]{2,8})?").matcher(s);
        while (v.find()) {
            bb = bb +"\n" + v.group();

        }
        Matcher u = Pattern.compile("((www\\.)|(WWW\\.))[a-zA-Z0-9-_.]+\\.([a-zA-Z]{2,8})(\\.[a-zA-Z]{2,8})?").matcher(bb);
        if(u.find()) {
            site_web.setText(u.group());
        } else site_web.setText(bb);
        ////////////////////////////////////////////////fin extraction site web //////////////////////////////////////////

        /////////////////////////////////////////////////extraction profession ////////////////////////////////////////////////

        ////////////////////////////////////////////////fin extraction profession //////////////////////////////////////////

         try {
          PrintWriter pw = new PrintWriter("input.txt");
        } catch (FileNotFoundException e) {
         e.printStackTrace();
        }

    }


   // Uri u = null;

            //mPreviewIv.setImageURI(u.getResultat());


    //set image to image view


}

