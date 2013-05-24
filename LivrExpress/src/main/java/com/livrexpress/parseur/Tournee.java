package com.livrexpress.parseur;

import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

/**
 * Created by Anto on 21/05/13.
 */
public class Tournee
{
    private Livreur livreur;
    private Date dateTournee;
    private ArrayList livraisons;
    Stack<Livraison> pileLivraison;
    private static Tournee instance;


    //Singleton
    public static Tournee getInstance() {
        if (null == instance) { // Premier appel
            instance = new Tournee();
        }
        return instance;
    }

    //Constructeur privé
    private Tournee() {}

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

    public ArrayList getLivraisons()
    {
        return livraisons;
    }

    public void setLivraisons(ArrayList livraisons)
    {
        this.livraisons = livraisons;
    }

    public Stack<Livraison> getPileLivraison() {
        return pileLivraison;
    }

    public void setPileLivraison(Stack<Livraison> pileLivraison) {
        this.pileLivraison = pileLivraison;
    }
}