package com.livrexpress.parseur;

import java.util.ArrayList;

/**
 * Created by Anto on 21/05/13.
 */
public class Colis
{
    private String nombre;
    private ArrayList paquets;

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
}