package com.livrexpress.parseur;

import org.simpleframework.xml.Element;

/**
 * Created by Anto on 21/05/13.
 */
public class Expediteur
{
    @Element
    private String nom;
    @Element
    private String rue;
    @Element
    private String cp;
    @Element
    private String ville;
    @Element
    private String telephone;

    public Expediteur()
    {
    }

    public String getCp()
    {
        return cp;
    }

    public void setCp(String cp)
    {
        this.cp = cp;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getRue()
    {
        return rue;
    }

    public void setRue(String rue)
    {
        this.rue = rue;
    }

    public String getVille()
    {
        return ville;
    }

    public void setVille(String ville)
    {
        this.ville = ville;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }
}