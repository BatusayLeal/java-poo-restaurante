public class Plato extends ProductoMenu {

    private String categoria;

    public Plato(int id, String nombre, double precio, boolean disponible, String categoria) {
        super(id, nombre, precio, disponible);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "[PLATO] " + super.toString() + " | Categoría: " + categoria;
    }
}