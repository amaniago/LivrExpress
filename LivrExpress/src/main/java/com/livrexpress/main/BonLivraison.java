package com.livrexpress.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.livrexpress.R;

public class BonLivraison extends Activity {

    Spinner spinner;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bon_livraison);


        //Récupération de la combobox
        spinner = (Spinner) findViewById(R.id.spinner);
        String[] listeStrings = {"Remise du colis", "Colis Refuse", "Colis non remis"};
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listeStrings));
    }

    public void confirmer(View v) {
        //TODO: Remplacer ces paramètres par la génération partielle du fichier XML
        //intent.putExtra("EXTRA_MOTIF", ((ExpandableListView) findViewById(R.id.motif)).getSelectedItem().toString());
        //intent.putExtra("EXTRA_COMMANTAIRE", ((EditText) findViewById(R.id.commentaire)).getText());
        startActivity(new Intent(v.getContext(), MapActivity.class));
    }
}

