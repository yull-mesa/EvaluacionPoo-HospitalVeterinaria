package org.veterinaria;

public class Hospital {
    private Paciente[] pacientes;
    private int contadorPacientes;
    private Jaula[] jaulas;

    public Hospital() {
        pacientes = new Paciente[10];
        contadorPacientes = 0;
        jaulas = new Jaula[6];
        cargarDatosIniciales();
    }

    public void cargarDatosIniciales() {
        // Jaulas iniciales: 1 (A, S), 2 (A, M), 3 (A, M), 4 (A, L), 5 (B, S), 6 (B, M)
        jaulas[0] = new Jaula(1, 'S', 'A');
        jaulas[1] = new Jaula(2, 'M', 'A');
        jaulas[2] = new Jaula(3, 'M', 'A');
        jaulas[3] = new Jaula(4, 'L', 'A');
        jaulas[4] = new Jaula(5, 'S', 'B');
        jaulas[5] = new Jaula(6, 'M', 'B');

        // Datos iniciales sin jaula asignada
        Perro rocky = new Perro("Rocky", 30.0, 4, "labrador", true);
        Gato misifu = new Gato("Misifu", 4.0, 3, 0);
        AveExotica kiwi = new AveExotica("Kiwi", 0.8, 2, 70.0);

        pacientes[0] = rocky;
        pacientes[1] = misifu;
        pacientes[2] = kiwi;
        contadorPacientes = 3;
    }

    private Paciente buscarPaciente(String nombre) {
        for (int i = 0; i < contadorPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                return pacientes[i];
            }
        }
        return null;
    }

    public String ingresarPerro(String nombre, double peso, int edad, String raza, String vacunaStr) {
        boolean vacunado = vacunaStr.equalsIgnoreCase("si") || vacunaStr.equalsIgnoreCase("sí");

        // R1: Validaciones datos
        if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 80 || edad < 0 || edad > 30) {
            return "ERROR: datos invalidos";
        }
        // R2: Duplicado y lleno
        if (buscarPaciente(nombre) != null) {
            return "ERROR: nombre duplicado";
        }
        if (contadorPacientes >= 10) {
            return "ERROR: hospital lleno";
        }

        Perro p = new Perro(nombre, peso, edad, raza, vacunado);
        pacientes[contadorPacientes++] = p;

        if (!vacunado) {
            return "OK: " + nombre + " ingresado en CUARENTENA";
        } else {
            return "OK: " + nombre + " ingresado";
        }
    }

    public String ingresarGato(String nombre, double peso, int edad) {
        if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 15 || edad < 0 || edad > 30) {
            return "ERROR: datos invalidos";
        }
        if (buscarPaciente(nombre) != null) {
            return "ERROR: nombre duplicado";
        }
        if (contadorPacientes >= 10) {
            return "ERROR: hospital lleno";
        }

        Gato g = new Gato(nombre, peso, edad, 0);
        pacientes[contadorPacientes++] = g;
        return "OK: " + nombre + " ingresado";
    }

    public String ingresarAve(String nombre, double peso, int edad, double envergadura) {
        if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 15 || edad < 0 || edad > 30 || envergadura <= 0) {
            return "ERROR: datos invalidos";
        }
        if (buscarPaciente(nombre) != null) {
            return "ERROR: nombre duplicado";
        }
        if (contadorPacientes >= 10) {
            return "ERROR: hospital lleno";
        }

        AveExotica a = new AveExotica(nombre, peso, edad, envergadura);
        pacientes[contadorPacientes++] = a;
        return "OK: " + nombre + " ingresado";
    }

    public String vacunar(String nombre) {
        Paciente pac = buscarPaciente(nombre);
        if (pac == null) {
            return "ERROR: paciente no encontrado";
        }
        if (!(pac instanceof Perro)) {
            return "ERROR: solo aplica a perros";
        }
        Perro perro = (Perro) pac;
        if (perro.isVacunado()) {
            return "ERROR: ya vacunado";
        }

        perro.setVacunado(true);
        return "OK: " + nombre + " vacunado (pasa a INGRESADO)";
    }

    public String asignar(String nombre) {
        Paciente pac = buscarPaciente(nombre);
        if (pac == null) {
            return "ERROR: paciente no encontrado";
        }
        if (pac.getEstado().equals("CUARENTENA")) {
            return "ERROR: paciente en cuarentena";
        }
        if (pac.getJaulaAsignada() != null) {
            return "ERROR: paciente ya tiene jaula";
        }

        // R4: Tamaño de jaula según peso y reglas especiales de aves
        char tamanoRequerido;
        double peso = pac.getPeso();
        if (peso < 5.0) {
            tamanoRequerido = 'S';
        } else if (peso <= 25.0) {
            tamanoRequerido = 'M';
        } else {
            tamanoRequerido = 'L';
        }

        if (pac instanceof AveExotica) {
            AveExotica ave = (AveExotica) pac;
            if (ave.getEnvergadura() > 60.0 && tamanoRequerido == 'S') {
                tamanoRequerido = 'M';
            }
        }

        // R5: Zona: perros y gatos en A; aves en B.
        char zonaRequerida = (pac instanceof AveExotica) ? 'B' : 'A';

        Jaula jaulaCompat = null;
        for (int i = 0; i < jaulas.length; i++) {
            Jaula j = jaulas[i];
            if (j.estaLibre() && j.getZona() == zonaRequerida) {
                // Verificar compatibilidad de tamaño (S < M < L)
                if (esTamanoCompatible(j.getTamano(), tamanoRequerido)) {
                    jaulaCompat = j;
                    break;
                }
            }
        }

        if (jaulaCompat == null) {
            return "ERROR: no hay jaula compatible";
        }

        jaulaCompat.setOcupante(pac);
        pac.setJaulaAsignada(jaulaCompat);
        return "OK: " + nombre + " asignado a jaula " + jaulaCompat.getNumero();
    }

    private boolean esTamanoCompatible(char tamanoJaula, char tamanoRequerido) {
        if (tamanoJaula == tamanoRequerido) return true;
        if (tamanoJaula == 'M' && tamanoRequerido == 'S') return true;
        if (tamanoJaula == 'L' && (tamanoRequerido == 'S' || tamanoRequerido == 'M')) return true;
        return false;
    }

    public String medicar(String nombre, double mg) {
        Paciente pac = buscarPaciente(nombre);
        if (pac == null) {
            return "ERROR: paciente no encontrado";
        }
        if (mg <= 0) {
            return "ERROR: datos invalidos";
        }
        if (pac.getEstado().equals("CUARENTENA")) {
            return "ERROR: paciente en cuarentena";
        }
        if (pac.getJaulaAsignada() == null) {
            return "ERROR: paciente sin jaula";
        }
        if (pac instanceof Gato) {
            Gato gato = (Gato) pac;
            if (gato.getNivelEstres() >= 8) {
                return "ERROR: gato con estres alto";
            }
        }

        double dosisMaxima = pac.getPeso() * pac.getFactorDosis();
        if (mg > dosisMaxima) {
            return "ERROR: dosis excede el maximo";
        }
        if (pac.getDosisAcumulada() + mg > 3 * dosisMaxima) {
            return "ERROR: tope acumulado superado";
        }

        pac.sumarDosisAcumulada(mg);
        pac.setEstado("EN_TRATAMIENTO");

        if (pac instanceof Gato) {
            Gato gato = (Gato) pac;
            int nuevoEstres = Math.min(10, gato.getNivelEstres() + 2);
            gato.setNivelEstres(nuevoEstres);
        }

        return "OK: " + nombre + " medicado con " + (int)mg + " mg" + (mg == (int)mg ? "" : ""); // Ajuste según formato
    }

    public String alta(String nombre) {
        Paciente pac = buscarPaciente(nombre);
        if (pac == null) {
            return "ERROR: paciente no encontrado";
        }
        if (!pac.getEstado().equals("EN_TRATAMIENTO")) {
            return "ERROR: paciente no apto para alta";
        }

        // Liberar jaula
        if (pac.getJaulaAsignada() != null) {
            pac.getJaulaAsignada().setOcupante(null);
        }

        // Eliminar del vector compactando
        int index = -1;
        for (int i = 0; i < contadorPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < contadorPacientes - 1; i++) {
                pacientes[i] = pacientes[i + 1];
            }
            pacientes[contadorPacientes - 1] = null;
            contadorPacientes--;
        }

        return "OK: " + nombre + " dado de alta";
    }

    public void reporte() {
        System.out.println("REPORTE");
        System.out.println("Pacientes: " + contadorPacientes + "/10");
        for (int i = 0; i < contadorPacientes; i++) {
            Paciente p = pacientes[i];
            String jaulaStr = (p.getJaulaAsignada() == null) ? "-" : String.valueOf(p.getJaulaAsignada().getNumero());
            System.out.printf("%s | %s | %.1f kg | %s | jaula: %s\n",
                    p.getNombre(), p.getTipo(), p.getPeso(), p.getEstado(), jaulaStr);
        }
        int libres = 0;
        for (int i = 0; i < jaulas.length; i++) {
            if (jaulas[i].estaLibre()) {
                libres++;
            }
        }
        System.out.println("Jaulas libres: " + libres);
    }
}