package tpTest;

import java.util.*;

class Grafo {
    private class Nodo {
        String nombre;
        ListaAdyacencia adyacentes;

        Nodo(String nombre) {
            this.nombre = nombre;
            this.adyacentes = new ListaAdyacencia();
        }
    }

    private class Arista {
        Nodo destino;
        int peso;

        Arista(Nodo destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    private class ListaAdyacencia {
        private List<Arista> lista;

        ListaAdyacencia() {
            lista = new ArrayList<>();
        }

        void agregarArista(Nodo destino, int peso) {
            lista.add(new Arista(destino, peso));
        }

        List<Arista> obtenerAdyacentes() {
            return lista;
        }
    }

    private Nodo[] nodos;
    private int cantidad;

    public Grafo(int capacidadMaxima) {
        nodos = new Nodo[capacidadMaxima];
        cantidad = 0;
    }

    public void agregarVertice(String nombre) {
        if (buscarNodo(nombre) == null && cantidad < nodos.length) {
            nodos[cantidad++] = new Nodo(nombre);
        }
    }

    public void agregarArista(String origen, String destino, int peso) {
        Nodo nodoOrigen = buscarNodo(origen);
        Nodo nodoDestino = buscarNodo(destino);
        if (nodoOrigen != null && nodoDestino != null) {
            nodoOrigen.adyacentes.agregarArista(nodoDestino, peso);
        }
    }

    private Nodo buscarNodo(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (nodos[i].nombre.equals(nombre)) return nodos[i];
        }
        return null;
    }

    public String rutaMasCorta(String origen, String[] destinos) {
        Nodo nodoOrigen = buscarNodo(origen);
        if (nodoOrigen == null) return null;

        int[] distancias = new int[cantidad];
        boolean[] visitado = new boolean[cantidad];
        String[] anteriores = new String[cantidad];

        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[obtenerIndice(origen)] = 0;

        for (int i = 0; i < cantidad; i++) {
            int u = minimoDistancia(distancias, visitado);
            if (u == -1) break;
            visitado[u] = true;

            for (Arista arista : nodos[u].adyacentes.obtenerAdyacentes()) {
                int v = obtenerIndice(arista.destino.nombre);
                if (!visitado[v] && distancias[u] + arista.peso < distancias[v]) {
                    distancias[v] = distancias[u] + arista.peso;
                    anteriores[v] = nodos[u].nombre;
                }
            }
        }

        String mejorDestino = null;
        int menorDistancia = Integer.MAX_VALUE;
        for (String destino : destinos) {
            int idx = obtenerIndice(destino);
            if (idx != -1 && distancias[idx] < menorDistancia) {
                menorDistancia = distancias[idx];
                mejorDestino = destino;
            }
        }
        return mejorDestino;
    }

    private int obtenerIndice(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (nodos[i].nombre.equals(nombre)) return i;
        }
        return -1;
    }

    private int minimoDistancia(int[] distancias, boolean[] visitado) {
        int min = Integer.MAX_VALUE, indice = -1;
        for (int i = 0; i < cantidad; i++) {
            if (!visitado[i] && distancias[i] < min) {
                min = distancias[i];
                indice = i;
            }
        }
        return indice;
    }
}
