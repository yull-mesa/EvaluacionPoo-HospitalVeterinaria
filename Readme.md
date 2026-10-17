yo hice dentro de la carpeta java package org.veterinaria 7 archivos para separar responsabilidades y facilitar el mantenimiento del mismo, también tome como ejemplo las directrices de la guía pdf adjunta en el wpp como guía para crear las clases y la logica del mismo programa creando clases constructores herencias encapsulamiento y tambien se le añadió @override que se encarga de sobrescribir 
también implemente un menú para que el usuario pueda saber como manejar la app
*como lo hice*: lo hice basándome en la guias y en los permisos que dio para este examen como el uso de ia consciente human in the loop yo di los prompts correctos para tener un código base y se fue manipulando a mi criterio y necesidades del proyecto mismo

# 🏥 Sistema de Gestión - Hospita
Veterinario (Java)

Proyecto de consola desarrollado en **Java (JDK 17+)** como evaluación práctica de **Programación Orientada a Objetos (POO)**. Este sistema gestiona pacientes (perros, gatos y aves exóticas), jaulas y reglas de negocio complejas utilizando exclusivamente **vectores de capacidad fija** y aplicando principios sólidos de arquitectura y encapsulamiento.

---

## 🚀 ¿Qué aprendí y qué apliqué en este proyecto?

Este proyecto me permitió consolidar y llevar a la práctica conceptos fundamentales del desarrollo de software:

### 1. Programación Orientada a Objetos (POO) y Herencia
* **Clase Abstracta (`Paciente`):** Creación de una clase base para centralizar atributos y comportamientos comunes (nombre, peso, edad, estado, dosis acumulada y referencia a jaula).
* **Herencia y Polimorfismo (`Perro`, `Gato`, `AveExotica`):** Extensión de la clase `Paciente` para implementar características y factores de dosis únicos por especie (`super(...)`), evitando la duplicación de código.

### 2. Encapsulamiento Estricto
* **Atributos `private`:** Protección del estado interno de los objetos.
* **Getters y Setters con validaciones:** Implementación de reglas de validación dentro de los setters (como rangos de edad, límites de peso y niveles de estrés) para rechazar datos inválidos de forma segura sin romper el objeto.

### 3. Manejo de Vectores de Capacidad Fija (Arrays)
* **Estructuras estáticas:** Gestión de datos utilizando únicamente vectores tradicionales (`Paciente[]` y `Jaula[]`) en lugar de colecciones de Java como `ArrayList`.
* **Algoritmos de búsqueda y compactación:** Implementación manual de lógica para buscar elementos por nombre (ignorando mayúsculas/minúsculas), validar cupos máximos ($10$ pacientes) y **compactar vectores** al dar de alta a un paciente (desplazando posiciones y decrementando contadores).

### 4. Reglas de Negocio y Lógica Condicional
* **Validaciones secuenciales:** Aplicación estricta del orden de validación para comandos (datos válidos, duplicados, límites de capacidad, estados de cuarentena).
* **Asignación inteligente de recursos:** Algoritmos para cruzar el peso y la envergadura del animal con el tamaño de jaula adecuado (`S`, `M`, `L`) y su zona correspondiente (`A` para perros/gatos, `B` para aves).
* **Control clínico:** Gestión de dosis máximas permitidas basadas en el peso y factor de cada especie, acumuladores de medicación y control de estrés para gatos.

### 5. Separación de Responsabilidades (Domain vs. UI)
* **Independencia del Dominio:** Las clases de lógica y negocio no conocen la consola (`Scanner` ni `System.out` están prohibidos en el dominio).
* **Capa de Interfaz (`Main`):** Centralización de la interacción con el usuario mediante un sistema de comandos de consola estructurados.

---

## 🛠️ Tecnologías y Herramientas utilizadas
* **Java (JDK 17 o superior)**
* **IntelliJ IDEA**
* **Git y GitHub** (para el control de versiones y gestión de commits)

---

## ⚙️ Compilación y Ejecución

Para compilar y ejecutar el proyecto desde la terminal:

```bash
# Compilar todas las clases
javac *.java

# Ejecutar el programa principal
java Main