package com.livrexpress.parseur;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Anto on 22/05/13.
 */
public class ParserXML
{
    public ParserXML()
    {
    }

    /**
     * Méthode permettant de parser un fichier XML : parcours du fichier xml balise par balise.
     * Renvoie une "Tournée" composée d'une date, d'un livreur et d'une liste de livraisons.
     * Les livraisons sont composées d'un expéditeur, d'un destinataire, et d'un colis lui-même composé d'une liste de paquets.
     *
     * @return Tournee
     */
    public static void parse()
    {
        Livreur livreur = null;
        ArrayList<Livraison> livraisons;
        Livraison livraison = null;
        Expediteur expediteur = null;
        Destinataire destinataire = null;
        Colis colis = null;
        ArrayList<Paquet> paquets = null;
        Paquet paquet = null;
        boolean done = false;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try
        {
            XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            //Téléchargement du fichier
            URL url = new URL("http://www.webosoft.info/exia/Export_ERP.xml");
            url.openConnection();
            InputStream in = url.openStream();

            parser.setInput(in, null);

            int eventType = parser.getEventType();
            livraisons = new ArrayList<>();

            //Tant qu'on est pas à la fin du document, on parcourt balise par balise
            while (eventType != XmlPullParser.END_DOCUMENT && !done)
            {
                String name;
                switch (eventType)
                {
                    //Début du document
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    //Debut d'un noeud
                    case XmlPullParser.START_TAG:
                        //Récup du nom du noeud
                        name = parser.getName();
                        //Balise livreur : création d'un livreur
                        if (name.equalsIgnoreCase("livreur"))
                            livreur = new Livreur();
                            //Balise date : recup de la date
                        else if (name.equalsIgnoreCase("date_tournee"))
                            Tournee.getInstance().setDateTournee(format.parse(parser.nextText()));
                            //Balise livraison : création d'une livraison
                        else if (name.equalsIgnoreCase("livraison"))
                            livraison = new Livraison();
                            //Si livreur est créé
                        else if (livreur != null)
                        {
                            //Récup de l'id du livreur
                            if (name.equalsIgnoreCase("id"))
                                livreur.setIdLivreur(parser.nextText());
                                //Récup du nom du livreur
                            else if (name.equalsIgnoreCase("nom"))
                                livreur.setNomLivreur(parser.nextText());
                        }
                        //Si livraison créée
                        else if (livraison != null)
                        {
                            //Recup de l'id livraison
                            if (name.equalsIgnoreCase("id"))
                                livraison.setId(parser.nextText());
                                //Balise expediteur : création d'un expéditeur
                            else if (name.equalsIgnoreCase("expediteur"))
                                expediteur = new Expediteur();
                                //Balise destinataire : création d'un destinataire
                            else if (name.equalsIgnoreCase("destinataire"))
                                destinataire = new Destinataire();
                                //Balise expediteur : création d'un colis et d'une liste de paquets
                            else if (name.equalsIgnoreCase("colis"))
                            {
                                colis = new Colis();
                                paquets = new ArrayList<>();
                            }
                            //Récup des infos de l'expediteur
                            else if (expediteur != null)
                            {
                                if (name.equalsIgnoreCase("nom"))
                                    expediteur.setNom(parser.nextText());
                                else if (name.equalsIgnoreCase("rue"))
                                    expediteur.setRue(parser.nextText());
                                else if (name.equalsIgnoreCase("cp"))
                                    expediteur.setCodePostal(parser.nextText());
                                else if (name.equalsIgnoreCase("ville"))
                                    expediteur.setVille(parser.nextText());
                                else if (name.equalsIgnoreCase("telephone"))
                                    expediteur.setTelephone(parser.nextText());
                            }
                            //Récup des infos du destinataire
                            else if (destinataire != null)
                            {
                                if (name.equalsIgnoreCase("nom"))
                                    destinataire.setNom(parser.nextText());
                                else if (name.equalsIgnoreCase("rue"))
                                    destinataire.setRue(parser.nextText());
                                else if (name.equalsIgnoreCase("cp"))
                                    destinataire.setCodePostal(parser.nextText());
                                else if (name.equalsIgnoreCase("ville"))
                                    destinataire.setVille(parser.nextText());
                                else if (name.equalsIgnoreCase("complement_adresse"))
                                    destinataire.setComplementAdresse(parser.nextText());
                                else if (name.equalsIgnoreCase("telephone"))
                                    destinataire.setTelephone(parser.nextText());
                                else if (name.equalsIgnoreCase("portable"))
                                    destinataire.setPortable(parser.nextText());
                            }
                            //Cas d'un colis
                            else if (colis != null && paquets != null)
                            {
                                //Récup du nombre de paquets
                                if (name.equalsIgnoreCase("nombre"))
                                    colis.setNombre(parser.nextText());
                                    //Création d'un paquet
                                else if (name.equalsIgnoreCase("paquet"))
                                    paquet = new Paquet();
                                    //Récup des infos du paquet
                                else if (paquet != null)
                                {
                                    if (name.equalsIgnoreCase("code_barre"))
                                        paquet.setCodeBarre(parser.nextText());
                                    else if (name.equalsIgnoreCase("taille"))
                                        paquet.setTaille(parser.nextText());
                                    else if (name.equalsIgnoreCase("poids"))
                                        paquet.setPoids(parser.nextText());
                                }
                            }
                        }
                        break;

                    //Fin d'un noeud
                    case XmlPullParser.END_TAG:
                        name = parser.getName();

                        //fin d'un noeud "paquet"
                        if (name.equalsIgnoreCase("paquet") && paquet != null)
                        {
                            //Ajout du paquet a la liste
                            paquets.add(paquet);
                            paquet = null;
                        }
                        //fin d'un noeud "colis"
                        else if (name.equalsIgnoreCase("colis") && colis != null)
                        {
                            //Ajout de la liste de paquets
                            colis.setPaquets(paquets);
                            //Ajout du colis a la livraison
                            livraison.setColis(colis);
                            colis = null;
                        }
                        //Fin d'un noeud "expediteur"
                        else if (name.equalsIgnoreCase("expediteur") && expediteur != null)
                        {
                            livraison.setExpediteur(expediteur);
                            expediteur = null;
                        }
                        //Fin d'un noeud "livraison"
                        else if (name.equalsIgnoreCase("livraison") && livraison != null)
                        {
                            livraison.setDestinataire(destinataire);
                            destinataire = null;
                            livraisons.add(livraison);
                        }
                        //Fin d'un noeud "livreur"
                        else if (name.equalsIgnoreCase("livreur") && livreur != null)
                        {
                            Tournee.getInstance().setLivreur(livreur);
                            livreur = null;
                        }
                        //Fin noeud principal "tournée"
                        else if (name.equalsIgnoreCase("tournee"))
                        {
                            Tournee.getInstance().setLivraisons(livraisons);
                            Tournee.getInstance().getPileLivraison().addAll(Tournee.getInstance().getLivraisons());
                            done = true;
                        }
                        break;
                }
                eventType = parser.next();
            }
            in.close();
        }
        catch (XmlPullParserException | IOException | ParseException e)
        {
            e.printStackTrace();
        }
    }
}