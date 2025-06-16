package tpTest;

class ColaPrioridad {
    private static class Nodo {
        String valor;
        int prioridad;

        Nodo(String valor, int prioridad) {
            this.valor = valor;
            this.prioridad = prioridad;
        }
    }

    private Nodo[] elementos;
    private int tamaño;
    private int capacidad;

    public ColaPrioridad() {
        capacidad = 100;
        elementos = new Nodo[capacidad];
        tamaño = 0;
    }

    public void insertar(String valor, int prioridad) {
        if (tamaño >= capacidad) return;
        Nodo nuevo = new Nodo(valor, prioridad);
        int i = tamaño - 1;
        while (i >= 0 && elementos[i].prioridad > prioridad) {
            elementos[i + 1] = elementos[i];
            i--;
        }
        elementos[i + 1] = nuevo;
        tamaño++;
    }

    public String eliminar() {
        if (tamaño == 0) return null;
        return elementos[--tamaño].valor;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }
} 
