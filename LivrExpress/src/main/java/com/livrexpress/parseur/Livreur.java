package com.livrexpress.parseur;

import org.simpleframework.xml.Element;

/**
 * Created by Anto on 21/05/13.
 */
public class Livreur
{
    @Element
    private String id;
    @Element
    private String nom;

    public Livreur()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}