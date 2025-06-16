package tpTest;

//Sistema de Búsqueda de Cocheras - Interfaz de Consola
import java.util.Scanner;

public class SistemaCocheras {
	 public static void main(String[] args) {
	     Scanner sc = new Scanner(System.in);
	
	     Grafo grafo = new Grafo(10);
	     DiccionarioCocheras diccionario = new DiccionarioCocheras();
	     Pila<String> historial = new Pila<>();
	     ColaPrioridad colaEspera = new ColaPrioridad();
	
	     // Cargar datos de ejemplo
	 
	 // (lugar)
	 grafo.agregarVertice("A");
	 grafo.agregarVertice("B");
	 grafo.agregarVertice("C");
	 grafo.agregarVertice("D");
	 
	 // (origen, destino, distancia)
	 grafo.agregarArista("A", "B", 2);
	 grafo.agregarArista("A", "C", 5);
	 grafo.agregarArista("B", "D", 4);
	 grafo.agregarArista("C", "D", 1);
	
	 // (clave, ubicacion, disponibilidad)
	 diccionario.agregarCochera("B", "Calle B 123", true);
	 diccionario.agregarCochera("C", "Calle C 456", false);
	 diccionario.agregarCochera("D", "Calle D 789", true);
	
	 boolean salir = false;
	 while (!salir) {
	     System.out.println("\n--- SISTEMA DE BUSQUEDA DE COCHERAS ---");
	 System.out.println("1. Buscar cochera disponible");
	 System.out.println("2. Ver historial de búsquedas");
	 System.out.println("3. Ver cola de espera");
	 System.out.println("4. Salir");
	 System.out.print("Seleccione una opción: ");
	 int opcion = sc.nextInt();
	 sc.nextLine();
	
	 switch (opcion) {
	     case 1:
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
	         break;
	     case 2:
	         System.out.println("Historial de búsquedas:");
	         while (!historial.estaVacia()) {
	             System.out.println(historial.pop());
	         }
	         break;
	     case 3:
	         System.out.print("Ingrese cochera ocupada: ");
	         String cocheraOcupada = sc.nextLine();
	         System.out.print("Ingrese ID del vehículo: ");
	         String vehiculo = sc.nextLine();
	         System.out.print("Ingrese prioridad (menor = mayor prioridad): ");
	         int prioridad = sc.nextInt();
	         sc.nextLine();
	         colaEspera.insertar(vehiculo, prioridad);
	         System.out.println("Vehículo en espera registrado.");
	         break;
	     case 4:
	         salir = true;
	         break;
	     default:
	         System.out.println("Opción inválida.");
	         }
	     }
	     sc.close();
	 }
}
