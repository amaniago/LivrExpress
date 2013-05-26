package com.livrexpress.parseur;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Anto on 21/05/13.
 */
@Root
public class Tournee
{
    private static Tournee instance;
    @Element
    private Livreur livreur;
    @Element
    private String date_tournee;
    @ElementList(inline = true)
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

    public void setInstance(Tournee tournee)
    {
        this.instance = tournee;
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

    public String getDate_tournee()
    {
        return date_tournee;
    }

    public void setDate_tournee(String date_tournee)
    {
        this.date_tournee = date_tournee;
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