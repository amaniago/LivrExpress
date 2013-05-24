package com.livrexpress.parseur;

import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

/**
 * Created by Anto on 21/05/13.
 */
public class Tournee
{
    private static Tournee instance;
    private Livreur livreur;
    private Date dateTournee;
    private ArrayList<Livraison> livraisons;

    public Stack<Livraison> pileLivraison;

    //Singleton
    public static Tournee getInstance()
    {
        // Premier appel
        if (null == instance)
            instance = new Tournee();
        return instance;
    }

    //Constructeur privé
    private Tournee()
    {
    }

    public Livreur getLivreur()
    {
        return livreur;
    }

    public void setLivreur(Livreur livreur)
    {
        this.livreur = livreur;
    }

    public Date getDateTournee()
    {
        return dateTournee;
    }

    public void setDateTournee(Date dateTournee)
    {
        this.dateTournee = dateTournee;
    }

    public ArrayList<Livraison> getLivraisons()
    {
        return livraisons;
    }

    public void setLivraisons(ArrayList<Livraison> livraisons)
    {
        this.livraisons = livraisons;
    }

    public Stack<Livraison> getPileLivraison()
    {
        return pileLivraison;
    }

    public void setPileLivraison(Stack<Livraison> pileLivraison)
    {
        this.pileLivraison = pileLivraison;
    }
}