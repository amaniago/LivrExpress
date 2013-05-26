package com.livrexpress.parseur;

import android.content.Context;
import android.os.Environment;
import com.livrexpress.R;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Stack;

/**
 * Created by Anto on 22/05/13.
 */
public class ParserXML
{
    /**
     * Méthode permettant de parser un fichier XML : parcours du fichier xml balise par balise.
     * Renvoie une "Tournée" composée d'une date, d'un livreur et d'une liste de livraisons.
     * Les livraisons sont composées d'un expéditeur, d'un destinataire, et d'un colis lui-même composé d'une liste de paquets.
     *
     * @return Tournee
     */
    public static void parse(Context context)
    {
        try {
            //Téléchargement du fichier
            URL url = new URL(context.getResources().getString(R.string.url_fichier_xml));
            url.openConnection();
            InputStream in = url.openStream();

            //Désérialisation du fichier XML
            Serializer serializer = new Persister();
            Tournee tournee = serializer.read(Tournee.class, in);
            tournee.setPileLivraison(new Stack<Livraison>());
            tournee.getPileLivraison().addAll(tournee.getLivraisons());
            for (Livraison liv : tournee.getLivraisons())
            {
                float poid = 0;
                for (Paquet paquet : liv.getColis().getPaquets())
                    poid += Float.parseFloat(paquet.getPoids().replace(",", "."));
                liv.getColis().setPoid(poid);
            }

            Tournee.getInstance().setInstance(tournee);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void write(RemiseColis remiseColis, Context context)
    {
        Serializer serializer = new Persister();
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("Tournee.xml", Context.MODE_WORLD_WRITEABLE);
            serializer.write(remiseColis, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}