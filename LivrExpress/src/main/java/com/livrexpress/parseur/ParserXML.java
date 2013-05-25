package com.livrexpress.parseur;

import android.content.Context;
import android.content.res.Resources;
import com.livrexpress.R;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
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
    public static Tournee parse(Context context)
    {
        Tournee tournee = Tournee.getInstance();

        try {
            //Téléchargement du fichier
            URL url = new URL(context.getResources().getString(R.string.url_fichier_xml));
            url.openConnection();
            InputStream in = url.openStream();

            //Désérialisation du fichier XML
            Serializer serializer = new Persister();
            tournee = serializer.read(Tournee.class, in);
            tournee.setPileLivraison(new Stack<Livraison>());
            tournee.getPileLivraison().addAll(Tournee.getInstance().getLivraisons());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tournee;
    }

    public static void write()
    {

    }
}