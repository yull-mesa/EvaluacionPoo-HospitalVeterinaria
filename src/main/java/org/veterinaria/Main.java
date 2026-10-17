package org.veterinaria;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (!scanner.hasNextLine()) break;
            String linea = scanner.nextLine().trim();
            if (linea.equalsIgnoreCase("salir")) {
                break;
            }
            if (linea.equalsIgnoreCase("reporte")) {
                hospital.reporte();
                continue;
            }

            String[] partes = linea.split("\\s+");
            if (partes.length == 0 || partes[0].isEmpty()) {
                continue;
            }

            String comando = partes[0].toLowerCase();

            if (comando.equals("ingresar")) {
                if (partes.length < 2) {
                    System.out.println("ERROR: comando desconocido.");
                    continue;
                }
                String tipo = partes[1].toLowerCase();
                String respuesta = "";

                if (tipo.equals("perro") && partes.length >= 7) {
                    try {
                        String nombre = partes[2];
                        double peso = Double.parseDouble(partes[3]);
                        int edad = Integer.parseInt(partes[4]);
                        String raza = partes[5];
                        String vacuna = partes[6];
                        respuesta = hospital.ingresarPerro(nombre, peso, edad, raza, vacuna);
                    } catch (NumberFormatException e) {
                        respuesta = "ERROR: datos invalidos";
                    }
                } else if (tipo.equals("gato") && partes.length >= 5) {
                    try {
                        String nombre = partes[2];
                        double peso = Double.parseDouble(partes[3]);
                        int edad = Integer.parseInt(partes[4]);
                        respuesta = hospital.ingresarGato(nombre, peso, edad);
                    } catch (NumberFormatException e) {
                        respuesta = "ERROR: datos invalidos";
                    }
                } else if (tipo.equals("ave") && partes.length >= 6) {
                    try {
                        String nombre = partes[2];
                        double peso = Double.parseDouble(partes[3]);
                        int edad = Integer.parseInt(partes[4]);
                        double envergadura = Double.parseDouble(partes[5]);
                        respuesta = hospital.ingresarAve(nombre, peso, edad, envergadura);
                    } catch (NumberFormatException e) {
                        respuesta = "ERROR: datos invalidos";
                    }
                } else {
                    respuesta = "ERROR: datos invalidos";
                }
                System.out.println(respuesta);

            } else if (comando.equals("vacunar") && partes.length >= 2) {
                System.out.println(hospital.vacunar(partes[1]));
            } else if (comando.equals("asignar") && partes.length >= 2) {
                System.out.println(hospital.asignar(partes[1]));
            } else if (comando.equals("medicar") && partes.length >= 3) {
                try {
                    double mg = Double.parseDouble(partes[2]);
                    System.out.println(hospital.medicar(partes[1], mg));
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: datos invalidos");
                }
            } else if (comando.equals("alta") && partes.length >= 2) {
                System.out.println(hospital.alta(partes[1]));
            } else {
                System.out.println("Comando desconocido: ERROR: comando desconocido.");
            }
        }
        scanner.close();
    }
}
