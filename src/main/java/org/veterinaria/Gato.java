package org.veterinaria;

public class Gato extends Paciente{
    private int nivelEstres; // De 0 a 10

    public Gato(String nombre ,double peso, int edad, int nivelEstres){
        super(nombre,peso,edad,"Ingresado",6.0);
        this.nivelEstres = nivelEstres;
    }

    public  int getNivelEstres(){
        return nivelEstres;
    }

    public void setNivelEstres(int nivelEstres){
        if(nivelEstres>=0 && nivelEstres <= 10){
            this.nivelEstres = nivelEstres;
        }
    }

    @Override
    public  String getTipo(){
        return "Gato";
    }
}
