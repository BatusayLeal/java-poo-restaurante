import java.util.ArrayList;

public class Restaurante {

    // Crear Array de Productos
    private ArrayList<ProductoMenu> menu = new ArrayList<>();

    public boolean registrarProducto(ProductoMenu producto) {
        if (producto == null) {
            return false;
        }

        // Evitar productos con el mismo ID
        if (buscarProductoPorId(producto.getId()) != null) {
            return false;
        }

        menu.add(producto);
        return true;
    }

    public ArrayList<ProductoMenu> listarProductos() {
        return new ArrayList<>(menu);
    }

    public ProductoMenu buscarProductoPorId(int id) {
        for (ProductoMenu producto : menu) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    public boolean modificarProducto(ProductoMenu producto, String nuevoNombre, double nuevoPrecio, boolean nuevaDisponibilidad) {
        if (producto == null) {
            return false;
        }

        producto.setNombre(nuevoNombre);
        producto.setPrecio(nuevoPrecio);
        producto.setDisponible(nuevaDisponibilidad);

        return true;
    }

    public boolean eliminarProducto(int id) {
        ProductoMenu producto = buscarProductoPorId(id);

        if (producto == null) {
            return false;
        }

        return menu.remove(producto);
    }

    public void agregarProductoPrueba(ProductoMenu producto) {
        menu.add(producto);
    }
}