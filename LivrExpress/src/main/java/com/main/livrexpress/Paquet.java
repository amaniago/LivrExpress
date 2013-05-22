package com.main.livrexpress;

/**
 * Created by Anto on 21/05/13.
 */
public class Paquet {

    private String codeBarre;
    private String taille;
    private String poids;

    public Paquet(){}

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }
}
