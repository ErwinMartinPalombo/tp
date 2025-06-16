package tpTest;

class Pila<T> {
    private Object[] elementos;
    private int tope;
    private int capacidad;

    public Pila() {
        capacidad = 100;
        elementos = new Object[capacidad];
        tope = -1;
    }

    public void push(T elemento) {
        if (tope + 1 < capacidad) {
            elementos[++tope] = elemento;
        }
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (!estaVacia()) {
            return (T) elementos[tope--];
        }
        return null;
    }

    public boolean estaVacia() {
        return tope == -1;
    }

    public T verTope() {
        if (!estaVacia()) {
            return (T) elementos[tope];
        }
        return null;
    }
}