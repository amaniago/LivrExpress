package com.livrexpress.parseur;

import org.simpleframework.xml.Element;

/**
 * Created by Anto on 21/05/13.
 */
public class Destinataire
{
    @Element
    private String nom;
    @Element
    private String rue;
    @Element
    private String cp;
    @Element
    private String ville;
    @Element(required = false)
    private String complement_adresse;
    @Element
    private String telephone;
    @Element
    private String portable;

    public String getAdresse()
    {
        return this.getRue() + " " + this.getCp() + " " + this.getVille();
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

    public String getCp()
    {
        return cp;
    }

    public void setCp(String cp)
    {
        this.cp = cp;
    }

    public String getVille()
    {
        return ville;
    }

    public void setVille(String ville)
    {
        this.ville = ville;
    }

    public String getComplement_adresse()
    {
        return complement_adresse;
    }

    public void setComplement_adresse(String complement_adresse)
    {
        this.complement_adresse = complement_adresse;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getPortable()
    {
        return portable;
    }

    public void setPortable(String portable)
    {
        this.portable = portable;
    }
}