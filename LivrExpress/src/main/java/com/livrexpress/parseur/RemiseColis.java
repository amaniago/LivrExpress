package com.livrexpress.parseur;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.Root;

/**
 * Created by Plantie on 26/05/13.
 */
@Root
public class RemiseColis
{
    @Element
    private String id;
    @Element(required = false)
    private String etat;
    @Element(required = false)
    private String commentaire;
    @ElementArray(required = false)
    private byte[] signature;
    @Element(required = false)
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

    public String getCommentaire()
    {
        return commentaire;
    }

    public void setCommentaire(String commentaire)
    {
        this.commentaire = commentaire;
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