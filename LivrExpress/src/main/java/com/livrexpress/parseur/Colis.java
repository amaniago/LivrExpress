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
    private int nombre;
    @ElementList(inline = true)
    private ArrayList<Paquet> paquets;
    //Poid total du colis
    private Float poid;

    public int getNombre()
    {
        return nombre;
    }

    public void setNombre(int nombre)
    {
        this.nombre = nombre;
    }

    public ArrayList<Paquet> getPaquets()
    {
        return paquets;
    }

    public void setPaquets(ArrayList<Paquet> paquets)
    {
        this.paquets = paquets;
    }

    public Float getPoid()
    {
        return poid;
    }

    public void setPoid(Float poid)
    {
        this.poid = poid;
    }
}