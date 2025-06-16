package tpTest;

class DiccionarioCocheras {
    private String[] claves;
    private String[] ubicaciones;
    private boolean[] disponibles;
    private int tamaño;
    private int capacidad;

    public DiccionarioCocheras() {
        capacidad = 20;
        claves = new String[capacidad];
        ubicaciones = new String[capacidad];
        disponibles = new boolean[capacidad];
        tamaño = 0;
    }

    public void agregarCochera(String clave, String ubicacion, boolean disponible) {
        if (tamaño >= capacidad) return;
        claves[tamaño] = clave;
        ubicaciones[tamaño] = ubicacion;
        disponibles[tamaño] = disponible;
        tamaño++;
    }

    public String[] obtenerCocherasDisponibles() {
        int conteo = 0;
        for (int i = 0; i < tamaño; i++) {
            if (disponibles[i]) conteo++;
        }
        String[] disponiblesTemp = new String[conteo];
        int j = 0;
        for (int i = 0; i < tamaño; i++) {
            if (disponibles[i]) disponiblesTemp[j++] = claves[i];
        }
        return disponiblesTemp;
    }

    public String obtenerUbicacion(String clave) {
        for (int i = 0; i < tamaño; i++) {
            if (claves[i].equals(clave)) return ubicaciones[i];
        }
        return "No encontrada";
    }

    public void actualizarEstado(String clave, boolean nuevoEstado) {
        for (int i = 0; i < tamaño; i++) {
            if (claves[i].equals(clave)) {
                disponibles[i] = nuevoEstado;
                return;
            }
        }
    }
}
