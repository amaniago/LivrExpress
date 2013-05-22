package com.main.livrexpress;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends Activity {

    //TextView de test
    private TextView infoLivreur;
    //Permet de stocker toutes les infos du fichier xml
    private Tournee tournee;

    //Utilisé pour convertir un string en date
    private SimpleDateFormat format;
    {
        format = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bouton de test pour récupérer la tournée
        Button recupererTournee = (Button) findViewById(R.id.button_tournee);
        recupererTournee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tournee = new Tournee();
                //Appel à la fonction de parsing
                tournee = ParserXML.parse(getApplicationContext());
                infoLivreur = (TextView) findViewById(R.id.textView);
                infoLivreur.setText("ID : " + tournee.getLivreur().getIdLivreur() + " Nom : " + tournee.getLivreur().getNomLivreur() + " Date : " + format.format(tournee.getDateTournee()));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
