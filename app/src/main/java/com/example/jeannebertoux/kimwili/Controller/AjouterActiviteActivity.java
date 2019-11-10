package com.example.jeannebertoux.kimwili.Controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jeannebertoux.kimwili.Activite;
import com.example.jeannebertoux.kimwili.ActiviteException;
import com.example.jeannebertoux.kimwili.Dal;
import com.example.jeannebertoux.kimwili.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Le type Ajouter activite activity.
 * @author Jeanne Bertoux et Kilian Corbel
 */
public class AjouterActiviteActivity extends AppCompatActivity {

    private static  final int PERMISSION_REQUEST_CODE = 1000;
    private static  final int SELECTED_PICTURE =1;
    /**
     * l' Img.
     */
    ImageView  img;
    /**
     *  Nom activite.
     */
    EditText nomActivite;
    /**
     *  Filepath.
     */
    String filepath = "";

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grandResults){
        super.onRequestPermissionsResult(requestCode, permissions, grandResults);
        switch (requestCode){

            case PERMISSION_REQUEST_CODE:{
                if(grandResults.length > 0 && grandResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission accordée", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this,"Permission refusée", Toast.LENGTH_SHORT).show();
                }
            }
                break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Initialisation d'une instance de la bdd
        final Dal dal = new Dal(this);
        dal.open();

        setContentView(R.layout.activity_ajouter_activite);

        //---- initialisation des éléments ----
        img = findViewById(R.id.imgView);
        nomActivite = findViewById(R.id.et_nom_activite);
        Button btnAjouter = findViewById(R.id.btnAjouter);
        ImageButton btnHome = findViewById(R.id.btnHome);
        final EditText etNomActivite = findViewById(R.id.et_nom_activite);

        // Definition des boutons
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dal.close();
                Intent intent = new Intent(AjouterActiviteActivity.this, Accueil.class);
                startActivity(intent);
            }
        });

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    dal.insertActivite(new Activite(0, etNomActivite.getText().toString(), filepath));
                }
                catch (ActiviteException ae) {
                    ae.toString();
                }

                dal.close();
                Intent intent = new Intent(AjouterActiviteActivity.this, ActiviteActivity.class);
                startActivity(intent);
            }
        });

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions( new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, PERMISSION_REQUEST_CODE);
        }

    }

    /**
     * Btn click.
     *
     * @param v la view
     */
// action bouton image récupère l'image selectionnée
    public void btnClick (View v){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECTED_PICTURE);
    }

    //affiche l'image dans l'activité
    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        switch (requestCode){
            case SELECTED_PICTURE:
                if(resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    String[]projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(uri,projection,null,null,null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    filepath = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap votreImageSelect = BitmapFactory.decodeFile(filepath);
                    Drawable d = new BitmapDrawable( votreImageSelect);

                    img.setBackground(d);
                }
                break;
        }
    }

    // action bouton valider
  /*  public void passData(View view){

        String newItem = nomActivite.getText().toString();
        Intent passText_intent = new Intent(this, ActiviteActivity.class );
        passText_intent.putExtra("resultTx", newItem);
        startActivity(passText_intent);

        Intent passData_intent = new Intent(this, ActiviteActivity.class);
        passData_intent.putExtra("resultId", R.id.imgView);
        startActivity(passData_intent);

    }*/
}
