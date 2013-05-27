package com.livrexpress.parseur;

import android.content.Context;
import com.livrexpress.R;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
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
     */
    public static void parse(Context context)
    {
        try
        {
            if (Tournee.getInstance().getPileLivraison() != null)
                return;

            //Téléchargement du fichier
            URL url = new URL(context.getResources().getString(R.string.url_fichier_xml));
            url.openConnection();
            InputStream in = url.openStream();

            //Désérialisation du fichier XML
            Serializer serializer = new Persister();
            Tournee tournee = serializer.read(Tournee.class, in);
            tournee.setPileLivraison(new Stack<Livraison>());
            Collections.reverse(tournee.getLivraisons());
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
        try
        {
            String PATH = context.getFilesDir() + "/Tournee.xml";
            File file = new File(PATH);
            FileWriter fileWritter = new FileWriter(file, true);
            serializer.write(remiseColis, fileWritter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}