package com.livrexpress.parseur;

import org.simpleframework.xml.Element;

/**
 * Created by Plantie on 26/05/13.
 */
public class RemiseColis
{
    @Element
    private String id;
    @Element
    private String etat;
    @Element
    private String commantaire;
    @Element
    private byte[] signature;
    @Element
    private String date;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getEtat()
    {
        return etat;
    }

    public void setEtat(String etat)
    {
        this.etat = etat;
    }

    public String getCommantaire()
    {
        return commantaire;
    }

    public void setCommantaire(String commantaire)
    {
        this.commantaire = commantaire;
    }

    public byte[] getSignature()
    {
        return signature;
    }

    public void setSignature(byte[] signature)
    {
        this.signature = signature;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
}
