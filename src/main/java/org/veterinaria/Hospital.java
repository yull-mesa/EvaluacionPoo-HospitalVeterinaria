package org.veterinaria;

public class Hospital {
    private  Paciente[] pacientes;
    private int contadorPacientes;
    private Jaula[] jaulas;

    public Hospital (){
        pacientes = new Paciente[10];
        contadorPacientes = 0;
        jaulas = new Jaula[6];
        cargarDatosIniciales();
    }

    public void cargarDatosIniciales(){
        // jaulas iniciales 1( A , S ) , 2( A , M ) , 3( A , M ) 4( A , L ) , 5( B , S ) 6( B , M )
        jaulas[0] = new Jaula(1, 'S', 'A');
        jaulas[1] = new Jaula(2, 'M', 'A');
        jaulas[2] = new Jaula(3, 'M' , 'A');
        jaulas[3] = new Jaula(4, 'L','A');
        jaulas[4] = new Jaula(5, 'S','B');
        jaulas[5] = new Jaula(6,'M', 'B');
    }
}
