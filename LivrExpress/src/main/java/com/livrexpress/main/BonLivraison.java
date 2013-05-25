package com.livrexpress.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.livrexpress.R;

public class BonLivraison extends Activity {

    Spinner spinner;
    Spinner motif;
    EditText com;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bon_livraison);


        //Récupération de la combobox
        spinner = (Spinner) findViewById(R.id.spinner);
        String[] listeStrings = {"Remise du colis", "Colis Refuse", "Colis non remis"};
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listeStrings));

        motif = (Spinner) findViewById(R.id.spinner2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if(spinner.getSelectedItem().toString() == "Colis Refuse")
                {
                    motif.setVisibility(View.VISIBLE);
                    String[] listeStrings = {"Colis endommage", "Colis Indesirable"};
                    motif.setAdapter(new ArrayAdapter<>(BonLivraison.this, android.R.layout.simple_spinner_item, listeStrings));
                    motif.invalidate();
                }
                else if (spinner.getSelectedItem().toString() == "Colis non remis")
                {
                    motif.setVisibility(View.VISIBLE);
                    String[] listeStrings = {"Destinataire absent"};
                    motif.setAdapter(new ArrayAdapter<>(BonLivraison.this, android.R.layout.simple_spinner_item, listeStrings));
                    motif.invalidate();
                }
                else
                {
                    motif.setVisibility(View.INVISIBLE);
                    motif.invalidate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void confirmer(View v)
    {
        GestureOverlayView gov = (GestureOverlayView) findViewById(R.id.signaturePad);
        //TODO: Trouver la méthode retournant si quelque chose est déssiné
        if (gov.isGestureVisible())
        {
            Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
        //TODO: Remplacer ces paramètres par la génération partielle du fichier XML
        //intent.putExtra("EXTRA_MOTIF", ((ExpandableListView) findViewById(R.id.motif)).getSelectedItem().toString());
        //intent.putExtra("EXTRA_COMMANTAIRE", ((EditText) findViewById(R.id.commentaire)).getText());
            startActivity(new Intent(v.getContext(), MapActivity.class));
        }
        else
        {
            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
            alertDialog.setTitle("Signature");
            alertDialog.setMessage("Vous devez recueillir une signature sur le bon de livraison avant de poursuivre.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
            alertDialog.show();
        }
    }
}

