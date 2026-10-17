package org.veterinaria;

public class Jaula {
    private  int numero;
    private  char tamano;//s,m,l
    private char zona;//A o B
    private  Paciente ocupante; // Referencia o null

    public  Jaula(int numero , char tamano ,char zona){
        this.numero = numero;
        this.tamano = Character.toUpperCase(tamano);
        this.zona = Character.toUpperCase(zona);
        this.ocupante= null;

    }

    public  int getNumero(){
        return  numero;
    }

    public char getTamano(){
        return tamano;
    }

    public char getZona(){
        return zona;
    }

    public Paciente getOcupante(){
        return ocupante;
    }

    public  void setOcupante(){
        this.ocupante=ocupante;
    }

    public  boolean estadoLibre(){
        return ocupante == null;
    }
}
