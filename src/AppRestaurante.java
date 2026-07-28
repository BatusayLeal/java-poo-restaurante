import java.util.ArrayList;
import java.util.Scanner;

public class AppRestaurante {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Restaurante restaurante = new Restaurante();

        // Se agrega un producto de prueba
        Plato plato = new Plato(1, "Hamburguesa", 8500, true, "Plato principal");
        restaurante.agregarProductoPrueba(plato);

        int opcion;

        do {
            System.out.println("\n========== ADMINISTRACIÓN DEL MENÚ ==========");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto");
            System.out.println("4. Modificar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Salir");
            System.out.print("Ingrese una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida. Debe ingresar un número.");
                opcion = -1;
                continue;
            }

            switch (opcion) {
                case 1:
                    registrarProducto(scanner, restaurante);
                    break;

                case 2:
                    listarProductos(restaurante);
                    break;

                case 3:
                    buscarProducto(scanner, restaurante);
                    break;

                case 4:
                    modificarProducto(scanner, restaurante);
                    break;

                case 5:
                    eliminarProducto(scanner, restaurante);
                    break;

                case 6:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 6);

        scanner.close();
    }

    // METODOS

    private static void registrarProducto(Scanner scanner, Restaurante restaurante) {
        System.out.println("\n--- Registrar producto ---");
        System.out.println("1. Plato");
        System.out.println("2. Bebida");
        System.out.print("Seleccione tipo de producto: ");

        int tipo;
        try {
            tipo = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Tipo no válido.");
            return;
        }

        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo no válido.");
            return;
        }

        try {
            System.out.print("Ingrese ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            if (restaurante.buscarProductoPorId(id) != null) {
                System.out.println("Error: Ya existe un producto con el ID " + id);
                return;
            }

            System.out.print("Ingrese nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese precio: ");
            double precio = Double.parseDouble(scanner.nextLine());

            // Disponibilidad SI/NO
            boolean disponible = false;
            while (true) {
                System.out.print("¿Está disponible? (SI/NO): ");
                String disponibleStr = scanner.nextLine().trim().toUpperCase();

                if (disponibleStr.equals("SI")) {
                    disponible = true;
                    break;
                } else if (disponibleStr.equals("NO")) {
                    disponible = false;
                    break;
                } else {
                    System.out.println("Por favor ingrese solo SI o NO.");
                }
            }

            ProductoMenu nuevoProducto;
                
            if (tipo == 1) {
                // Es comida
                System.out.print("Ingrese categoría (ej: Plato principal, Entrada, Postre): ");
                String categoria = scanner.nextLine();
                nuevoProducto = new Plato(id, nombre, precio, disponible, categoria);
            } else {
                // Es Bebida
                System.out.print("Ingrese tamaño en ml: ");
                int tamanioMl = Integer.parseInt(scanner.nextLine());
                nuevoProducto = new Bebida(id, nombre, precio, disponible, tamanioMl);
            }

            boolean registrado = restaurante.registrarProducto(nuevoProducto);

            if (registrado) {
                System.out.println("Producto registrado exitosamente:");
                System.out.println(nuevoProducto);
            } else {
                System.out.println("No se pudo registrar el producto.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingreso de datos inválido. Verifique que ID, precio y tamaño sean números.");
        }
    }

    private static void listarProductos(Restaurante restaurante) {
        System.out.println("\n--- Listado de productos ---");
        ArrayList<ProductoMenu> productos = restaurante.listarProductos();

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados en el menú.");
            return;
        }

        for (ProductoMenu p : productos) {
            System.out.println(p);
        }
        System.out.println("Total de productos: " + productos.size());
    }

    private static void buscarProducto(Scanner scanner, Restaurante restaurante) {
        System.out.println("\n--- Buscar producto ---");
        System.out.print("Ingrese el ID del producto: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());
            ProductoMenu producto = restaurante.buscarProductoPorId(id);

            if (producto == null) {
                System.out.println("Producto no encontrado.");
            } else {
                System.out.println("Producto encontrado:");
                System.out.println(producto);
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }

    private static void modificarProducto(Scanner scanner, Restaurante restaurante) {
        System.out.println("\n--- Modificar producto ---");
        System.out.print("Ingrese el ID del producto: ");

        try {
            int idModificar = Integer.parseInt(scanner.nextLine());

            ProductoMenu producto = restaurante.buscarProductoPorId(idModificar);

            if (producto == null) {
                System.out.println("Producto no encontrado.");
                return;
            }

            System.out.println("Producto actual:");
            System.out.println(producto);

            System.out.print("Ingrese el nuevo nombre: ");
            String nuevoNombre = scanner.nextLine();

            System.out.print("Ingrese el nuevo precio: ");
            double nuevoPrecio = Double.parseDouble(scanner.nextLine());

            // Disponibilidad SI/NO
            boolean nuevaDisponibilidad = false;
            while (true) {
                System.out.print("¿Está disponible? (SI/NO): ");
                String disponibleStr = scanner.nextLine().trim().toUpperCase();

                if (disponibleStr.equals("SI")) {
                    nuevaDisponibilidad = true;
                    break;
                } else if (disponibleStr.equals("NO")) {
                    nuevaDisponibilidad = false;
                    break;
                } else {
                    System.out.println("Por favor ingrese solo SI o NO.");
                }
            }

            boolean modificado = restaurante.modificarProducto(producto, nuevoNombre, nuevoPrecio, nuevaDisponibilidad);

            if (modificado) {
                System.out.println("Producto modificado:");
                System.out.println(producto);
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingreso de datos inválido.");
        }
    }

    private static void eliminarProducto(Scanner scanner, Restaurante restaurante) {
        System.out.println("\n--- Eliminar producto ---");
        System.out.print("Ingrese el ID del producto a eliminar: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());

            boolean eliminado = restaurante.eliminarProducto(id);

            if (eliminado) {
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }
}