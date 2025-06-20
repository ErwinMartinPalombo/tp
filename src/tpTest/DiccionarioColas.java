package tpTest;

public class DiccionarioColas {
    private String[] claves;
    private ColaPrioridad[] colas;
    private int tamaño;
    private int capacidad;

    public DiccionarioColas() {
        capacidad = 20;
        claves = new String[capacidad];
        colas = new ColaPrioridad[capacidad];
        tamaño = 0;
    }

    public void insertar(String clave, String valor, int prioridad) {
        int i = buscarClave(clave);
        if (i == -1) {
            if (tamaño >= capacidad) return;
            claves[tamaño] = clave;
            colas[tamaño] = new ColaPrioridad();
            i = tamaño++;
        }
        colas[i].insertar(valor, prioridad);
    }

    public String eliminar(String clave) {
        int i = buscarClave(clave);
        if (i != -1) {
            return colas[i].eliminar();
        }
        return null;
    }

    public void mostrarCola(String clave) {
        int i = buscarClave(clave);
        if (i != -1) {
            colas[i].mostrar();
        } else {
            System.out.println("No hay vehículos en espera para esta cochera.");
        }
    }

    private int buscarClave(String clave) {
        for (int i = 0; i < tamaño; i++) {
            if (claves[i].equals(clave)) return i;
        }
        return -1;
    }
}
