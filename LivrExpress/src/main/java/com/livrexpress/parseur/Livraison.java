package com.livrexpress.parseur;

import org.simpleframework.xml.Element;

/**
 * Created by Anto on 21/05/13.
 */
public class Livraison
{

    @Element
    private String id;
    @Element
    private Expediteur expediteur;
    @Element
    private Destinataire destinataire;
    @Element
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