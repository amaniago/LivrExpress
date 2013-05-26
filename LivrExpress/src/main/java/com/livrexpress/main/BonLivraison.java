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
import com.livrexpress.parseur.Livraison;
import com.livrexpress.parseur.Tournee;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class BonLivraison extends Activity {

    Spinner spinner;
    Spinner motif;

    TextView destinataire;
    TextView expediteur;
    TextView nbPaquet;
    TextView poid;

    Livraison liv = Tournee.getInstance().getPileLivraison().pop();
    int paquetScan;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bon_livraison);

        paquetScan = 0;

        //Afichage des informations de livraison
        //TODO: Tester cet extrait de code
        destinataire = (TextView) findViewById(R.id.destinataire);
        expediteur = (TextView) findViewById(R.id.expediteur);
        nbPaquet = (TextView) findViewById(R.id.nbPaquet);
        poid = (TextView) findViewById(R.id.poid);

        destinataire.setText(destinataire.getText() + " " + liv.getDestinataire());
        expediteur.setText(expediteur.getText() + " " + liv.getExpediteur());
        nbPaquet.setText(nbPaquet.getText() + " " + liv.getColis().getNombre());
        poid.setText(poid.getText() + " " + liv.getColis().getPoid());

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
                }
                else if (spinner.getSelectedItem().toString() == "Colis non remis")
                {
                    motif.setVisibility(View.VISIBLE);
                    String[] listeStrings = {"Destinataire absent"};
                    motif.setAdapter(new ArrayAdapter<>(BonLivraison.this, android.R.layout.simple_spinner_item, listeStrings));
                }
                else
                {
                    motif.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume(){
        paquetScan++;
    }

    public void scanner(View v)
    {
        if (spinner.getSelectedItem().toString() != "Colis non remis"  && paquetScan < Integer.parseInt(liv.getColis().getNombre())){
            startActivity(new Intent(v.getContext(), Scanner.class));
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
            alertDialog.setTitle("Scanner");
            alertDialog.setMessage("Il n'est pas necessaire de scanner les articles si ceux-ci ne sont pas remis.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
            alertDialog.show();
        }
    }

    public void confirmer(View v)
    {
        if (spinner.getSelectedItem().toString() != "Colis non remis"  && paquetScan < Integer.parseInt(liv.getColis().getNombre())){
            AlertDialog alertDialogScan = new AlertDialog.Builder(v.getContext()).create();
            alertDialogScan.setTitle("Scan des paquets");
            alertDialogScan.setMessage("Vous devez scanner les paquets avant de poursuivre.");
            alertDialogScan.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
            alertDialogScan.show();

            GestureOverlayView gov = (GestureOverlayView) findViewById(R.id.signaturePad);
            //TODO: Trouver la méthode retournant si quelque chose est déssiné
            if (gov.isGestureVisible())
            {
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
        } else {
            //TODO: Exporter cette date avec le XML
            if (spinner.getSelectedItem().toString() == "Colis non remis"){
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            }
        }
    }
}

