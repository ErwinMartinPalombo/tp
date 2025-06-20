package tpTest;

import java.util.*;

public class SistemaCocheras {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Grafo grafo = new Grafo(10);
        DiccionarioCocheras diccionario = new DiccionarioCocheras();
        Pila<String> historial = new Pila<>();
        DiccionarioColas esperaPorCochera = new DiccionarioColas();

        
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");

        grafo.agregarArista("A", "B", 2);
        grafo.agregarArista("A", "C", 5);
        grafo.agregarArista("B", "D", 4);
        grafo.agregarArista("C", "D", 1);

        // todas las cocheras ocupadas
        diccionario.agregarCochera("B", "Calle B 123", false);
        diccionario.agregarCochera("C", "Calle C 456", false);
        diccionario.agregarCochera("D", "Calle D 789", false);
        /*
        // cocheras libres
        diccionario.agregarCochera("B", "Calle B 123", true);
        diccionario.agregarCochera("C", "Calle C 456", false);
        diccionario.agregarCochera("D", "Calle D 789", true);
         */
        boolean salir = false;
        
        while (!salir) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("1. Buscar cochera disponible");
            System.out.println("2. Ver historial de búsquedas");
            System.out.println("4. Ver cola de espera por cochera");
            System.out.println("5. Atender siguiente en la cola de una cochera");
            System.out.println("6. Mostrar todas las cocheras");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            System.out.println("\n---------------------------------------------------");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                /*case 1:
                    System.out.print("Ingrese su ubicación actual: ");
                    String origen = sc.nextLine();
                    String[] disponibles = diccionario.obtenerCocherasDisponibles();
                    if (disponibles.length == 0) {
                        System.out.println("No hay cocheras disponibles.");
                        break;
                    }
                    String destino = grafo.rutaMasCorta(origen, disponibles);
                    if (destino != null) {
                        System.out.println("Cochera más cercana disponible: " + destino);
                        System.out.println("Ubicación: " + diccionario.obtenerUbicacion(destino));
                        historial.push("Desde " + origen + " hasta " + destino);
                    } else {
                        System.out.println("No se encontró ruta a una cochera disponible.");
                    }
                    break;*/
	            case 1:
	                System.out.print("Ingrese su ubicación actual: ");
	                String origen = sc.nextLine();
	                String[] disponibles = diccionario.obtenerCocherasDisponibles();
	
	                if (disponibles.length == 0) {
	                    System.out.println("No hay cocheras disponibles.");
	                    System.out.print("¿Desea ver cocheras ocupadas y unirse a la cola de espera? (s/n): ");
	                    String respuesta = sc.nextLine();
	                    if (respuesta.equalsIgnoreCase("s")) {
	                        System.out.print("Ingrese ID de cochera ocupada (ej: C): ");
	                        String cocheraOcupada = sc.nextLine();
	                        System.out.print("Ingrese su ID de vehículo: ");
	                        String vehiculoId = sc.nextLine();
	                        System.out.print("Ingrese prioridad (menor = mayor prioridad): ");
	                        int prioridad = sc.nextInt();
	                        sc.nextLine();
	                        esperaPorCochera.insertar(cocheraOcupada, vehiculoId, prioridad);
	                        System.out.println("Vehículo en espera registrado para cochera " + cocheraOcupada);
	                    }
	                    break;
	                }
	
	                String destino = grafo.rutaMasCorta(origen, disponibles);
	                if (destino != null) {
	                    System.out.println("Cochera más cercana disponible: " + destino);
	                    System.out.println("Ubicación: " + diccionario.obtenerUbicacion(destino));
	                    historial.push("Desde " + origen + " hasta " + destino);
	                } else {
	                    System.out.println("No se encontró ruta a una cochera disponible.");
	                }
	                break;
                case 2:
                    System.out.println("Historial de búsquedas:");
                    while (!historial.estaVacia()) {
                        System.out.println(historial.pop());
                    }
                    break;
                /*
                case 3:
                    System.out.print("Ingrese cochera ocupada: ");
                    String cochera = sc.nextLine();
                    System.out.print("Ingrese ID del vehículo: ");
                    String vehiculo = sc.nextLine();
                    System.out.print("Ingrese prioridad (menor = mayor prioridad): ");
                    int prioridad = sc.nextInt();
                    sc.nextLine();
                    esperaPorCochera.insertar(cochera, vehiculo, prioridad);
                    System.out.println("Vehículo en espera registrado para cochera " + cochera);
                    break;
                */
                case 4:
                    System.out.print("Ingrese cochera a consultar: ");
                    String cocheraConsulta = sc.nextLine();
                    esperaPorCochera.mostrarCola(cocheraConsulta);
                    break;
                case 5:
                    System.out.print("Ingrese cochera a liberar: ");
                    String cocheraLiberar = sc.nextLine();
                    String siguiente = esperaPorCochera.eliminar(cocheraLiberar);
                    if (siguiente != null) {
                        System.out.println("Siguiente vehículo en la cola: " + siguiente);
                    } else {
                        System.out.println("No hay vehículos en espera para esta cochera.");
                    }
                    break;
                case 6:
                    diccionario.mostrarTodas();
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }
}
