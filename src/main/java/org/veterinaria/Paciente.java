package org.veterinaria;

public abstract class Paciente {
    private String nombre;
    private double peso;
    private int edad;
    private String estado; // ingresado, cuarentena, en tratamiento
    private double factorDosis;
    private double dosisAcumulada;
    private Jaula jaulaAsignada; // Corregido: "Jaula" debe ir con mayúscula si la clase se llama así

    // Corregido: Se cambiaron los puntos y comas (;) por comas (,) en los parámetros
    public Paciente(String nombre, double peso, int edad, String estado, double factorDosis) {
        setNombre(nombre);
        setPeso(peso);
        setEdad(edad);
        this.estado = estado;
        this.factorDosis = factorDosis; // Corregido: Java es sensible a mayúsculas (factorDosis, no FactorDosis)
        this.dosisAcumulada = 0.0;
        this.jaulaAsignada = null;
    }

    public String getNombre() {
        return nombre;
    }

    // Corregido: Se agregó el parámetro "String nombre" que faltaba en el método
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim(); // Corregido: Se limpió la asignación repetida
        }
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad >= 0 && edad <= 30) {
            this.edad = edad;
        }
    }

    public double getFactorDosis() {
        return factorDosis;
    }

    public double getDosisAcumulada() {
        return dosisAcumulada;
    }

    // Corregido: El tipo de retorno debe ser "Jaula" con mayúscula
    public Jaula getJaulaAsignada() {
        return jaulaAsignada;
    }

    public void setJaulaAsignada(Jaula jaulaAsignada) {
        this.jaulaAsignada = jaulaAsignada;
    }

    public abstract String getTipo();
}
