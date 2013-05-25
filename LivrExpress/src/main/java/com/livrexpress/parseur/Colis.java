package com.livrexpress.parseur;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;

/**
 * Created by Anto on 21/05/13.
 */
public class Colis
{
    @Element
    private String nombre;
    @ElementList (inline=true)
    private ArrayList<Paquet> paquets;
    //Poid total du colis
    private Float poid;

    public Colis()
    {
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public ArrayList getPaquets()
    {
        return paquets;
    }

    public void setPaquets(ArrayList paquets)
    {
        this.paquets = paquets;
    }

    public Float getPoid() {
        return poid;
    }

    public void setPoid(Float poid) {
        this.poid = poid;
    }
}