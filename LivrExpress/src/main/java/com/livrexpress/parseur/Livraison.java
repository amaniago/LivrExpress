package com.livrexpress.parseur;

/**
 * Created by Anto on 21/05/13.
 */
public class Livraison
{
    private String id;
    private Expediteur expediteur;
    private Destinataire destinataire;
    private Colis colis;

    public Livraison()
    {

    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Expediteur getExpediteur()
    {
        return expediteur;
    }

    public void setExpediteur(Expediteur expediteur)
    {
        this.expediteur = expediteur;
    }

    public Destinataire getDestinataire()
    {
        return destinataire;
    }

    public void setDestinataire(Destinataire destinataire)
    {
        this.destinataire = destinataire;
    }

    public Colis getColis()
    {
        return colis;
    }

    public void setColis(Colis colis)
    {
        this.colis = colis;
    }
}