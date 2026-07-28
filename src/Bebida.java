public class Bebida extends ProductoMenu {

    private int tamanioMl;

    public Bebida(int id, String nombre, double precio, boolean disponible, int tamanioMl) {
        super(id, nombre, precio, disponible);
        this.tamanioMl = tamanioMl;
    }

    public int getTamanioMl() {
        return tamanioMl;
    }

    public void setTamanioMl(int tamanioMl) {
        this.tamanioMl = tamanioMl;
    }

    @Override
    public String toString() {
        return "[BEBIDA] " + super.toString() + " | Tamaño: " + tamanioMl + " ml";
    }
}