package com.livrexpress.parseur;

import org.simpleframework.xml.Element;

/**
 * Created by Anto on 21/05/13.
 */
public class Paquet
{
    @Element
    private String code_barre;
    @Element
    private String taille;
    @Element
    private String poids;

    public Paquet()
    {
    }

    public String getCode_barre() {
        return code_barre;
    }

    public void setCode_barre(String code_barre) {
        this.code_barre = code_barre;
    }

    public String getTaille()
    {
        return taille;
    }

    public void setTaille(String taille)
    {
        this.taille = taille;
    }

    public String getPoids()
    {
        return poids;
    }

    public void setPoids(String poids)
    {
        this.poids = poids;
    }
}