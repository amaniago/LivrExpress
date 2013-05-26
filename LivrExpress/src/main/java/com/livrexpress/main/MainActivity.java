package com.livrexpress.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import com.livrexpress.R;
import com.livrexpress.barcode.CaptureActivity;
import com.livrexpress.parseur.ParserXML;
import com.livrexpress.parseur.Tournee;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fonction qui permet au téléchargement du fichier xml de s'exécuter
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Permet de stocker toutes les infos du fichier xml
        ParserXML.parse(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClickBtnParse(View view)
    {
        startActivity(new Intent(MainActivity.this, MapActivity.class));
    }

    public void onClickBtnBarcode(View view)
    {
        startActivity(new Intent(view.getContext(), CaptureActivity.class));
    }

    public void onClickBtnMap(View view)
    {
        startActivity(new Intent(view.getContext(), MapActivity.class));
    }

    public void onClickBtnBonLivraison(View view)
    {
        startActivity(new Intent(view.getContext(), BonLivraison.class));
    }
}