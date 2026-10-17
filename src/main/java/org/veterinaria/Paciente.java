package org.veterinaria;

public abstract class Paciente {
    private String nombre;
    private double peso;
    private int edad;
    private String estado; // INGRESADO, CUARENTENA, EN_TRATAMIENTO
    private double factorDosis;
    private double dosisAcumulada;
    private Jaula jaulaAsignada; // referencia o null

    public Paciente(String nombre, double peso, int edad, String estado, double factorDosis) {
        setNombre(nombre);
        setPeso(peso);
        setEdad(edad);
        this.estado = estado;
        this.factorDosis = factorDosis;
        this.dosisAcumulada = 0.0;
        this.jaulaAsignada = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getFactorDosis() {
        return factorDosis;
    }

    public double getDosisAcumulada() {
        return dosisAcumulada;
    }

    public void sumarDosisAcumulada(double dosis) {
        this.dosisAcumulada += dosis;
    }

    public Jaula getJaulaAsignada() {
        return jaulaAsignada;
    }

    public void setJaulaAsignada(Jaula jaulaAsignada) {
        this.jaulaAsignada = jaulaAsignada;
    }

    public abstract String getTipo();
}