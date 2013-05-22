package com.livrexpress.parseur;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Anto on 21/05/13.
 */
public class Tournee
{
    private Livreur livreur;
    private Date dateTournee;
    private ArrayList livraisons;

    public Tournee()
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

    public ArrayList getLivraisons()
    {
        return livraisons;
    }

    public void setLivraisons(ArrayList livraisons)
    {
        this.livraisons = livraisons;
    }
}