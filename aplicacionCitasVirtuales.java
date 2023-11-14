import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class aplicacionCitasVirtuales {

        private static List<Usuario> usuarios = new ArrayList<>();
        private static Usuario usuarioActual;

        public static void main(String[] args) {
            cargarUsuariosDesdeArchivo("usuario.txt");

            Scanner scanner = new Scanner(System.in);

            while (true) {

                Scanner Scanner = null;
                if (usuarioActual == null) {
                    iniciarSesion(Scanner);
                } else {
                    mostrarMenu(Scanner);
                }
            }
        }
        private static void cargarUsuariosDesdeArchivo(String archivo) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userData = line.split(",");
                    if (userData.length == 4) {
                        String nombreUsuario = userData[0].trim();
                        String contraseña = userData[1].trim();
                        String descripcion = userData[2].trim();
                        int edad = Integer.parseInt(userData[3].trim());

                        Usuario usuario = new Usuario(nombreUsuario, contraseña, descripcion, edad);
                        usuario.add(usuario);
                    }
                }
                System.out.println("usuarios cargados correctamente desde el archivo. ");
            } catch (IOException | NumberFormatException e) {
                System.out.println(" Error al cargar usuarios desde el archivo. ");
            }


        }
        private static void iniciarSesion(Scanner scanner) {
            System.out.println("inicio de sesion:");
            System.out.print("nombre de usuario: ");
            String nombreUsuario = scanner.nextLine();

            System.out.print("contraseña: ");
            String contraseña = scanner.nextLine();

            Usuario usuario = validarCredenciales(nombreUsuario, contraseña);

            if (usuario != null) {
                usuarioActual = usuario;
                System.out.println("Inicio de sesion exitoso. ¡Bienvenido, "+ usuarioActual.getNombreUsuario() + "!");
            } else {
                System.out.println("credenciales incorrectas. Intentalo de nuevo. ");
            }
        }
        private static Usuario validarCredenciales(String nombreUsuario, String contraseña) {
            for (Usuario usuario : usuarios) {
                if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContraseña().equals(contraseña)) {
                    return  usuario;
                }
            }
            return null;
        }
        private static void mostrarMenu(Scanner scanner) {
            System.out.println("Menu de la aplicacion");
            System.out.println("1. Ajuste de Usuario");
            System.out.println("2. Mostrar todos los usuario");
            System.out.println("3.mostrar usuarios por edad");
            System.out.println("4. Solicitudes de emparejamiento");
            System.out.println("5. Cerrar sesion");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ajustesDeUsuario(scanner);
                    break;
                case 2:
                    mostrarUsuariosDisponibles();
                case 3:
                    mostrarUsuariosPorEdad();
                    break;
                case 4:
                    solicitudesEmparejamiento(scanner);
                case 5:
                    cerrarSesion();
                    break;
                default:
                    System.out.println("opcion no valida. Intentalo de nuevo. ");
            }
        }

        private static void ajustesDeUsuario(Scanner scanner) {
            System.out.println("Ajuste de usuario:");
            System.out.println("1. Cambiar descripcion");
            System.out.println("2. Cambiar contraseña");
            System.out.println("3. Cambiar edad");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    cambiarDescripcion(scanner);
                    break;
                case 2:
                    cambiarContraseña(scanner);
                    break;
                case 3:
                    cambiarEdad(scanner);
                    break;
                default:
                    System.out.println("opcion no valida. Intentalo de nuevo.");

            }
        }
        private static void cambiarDescripcion(Scanner scanner) {
            System.out.println("cambiando descripcion:");
            System.out.print(" Nueva descripcion: ");
            String nuevaDescripcion = scanner.nextLine();

            usuarioActual.setDescripcion(nuevaDescripcion);
            System.out.println("Descripcion cambiada correctamente.");
        }
        private static void cambiarContraseña(Scanner scanner) {
            System.out.println("Cambiando contraseña: ");
            System.out.print("Contraseña actual: ");
            String contraseñaActual = scanner.nextLine();

            if (!usuarioActual.getContraseña().equals(contraseñaActual)) {
                System.out.println("Contraseña actual incorrecta. cambio de contraseña cancelado.");
                return;
            }

            System.out.print("nueva contraseña: ");
            String nuevaContraseña = scanner.nextLine();

            usuarioActual.setContraseña(nuevaContraseña);

            usuarioActual = null;

            System.out.println("contraseña cambiada correctamente.por seguridad, se cerro la sesion.");
        }
        private static void cambiarEdad(Scanner scanner) {
            System.out.println("Cambiando edad:");

            int nuevaEdad = 0;
            boolean entradaValida = false;

            while (!entradaValida) {
                System.out.print("nueva edad: ");
                try {
                    nuevaEdad = Integer.parseInt(scanner.nextLine());
                    if (nuevaEdad > 0) {
                        entradaValida = true;
                    } else {
                        System.out.println("la edad debe der mayor que 0. intentalo de nuevo.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("por favor, introduce un numero valido.");
                }
            }
            usuarioActual.setEdad(nuevaEdad);

            System.out.println("Edad cambiada correctamente.");
        }
        private static void mostrarUsuariosDisponibles() {
            System.out.println("Usuario disponibles:");

            boolean usuariosDisponibles = false;

            for (Usuario usuario : usuarios) {
                if (!usuario.isEmparejado()) {
                    System.out.println("Nombre: "+ usuario.getNombreUsuario() +
                            ", Edad: " + usuario.getEdad() +
                            ", Descripcion: " + usuario.getDescripcion());
                    usuariosDisponibles = true;
                }
            }

            if (!usuariosDisponibles) {
                System.out.println("No hay usuarios disponibles en este momento.");
            }
        }
        private static void mostrarUsuariosPorEdad() {
            System.out.println("Usuarios ordenados por edad:");

            List<Usuario> usuariosOrdenados = new ArrayList<>(usuarios);

            usuariosOrdenados.sort(Comparator.comparingInt(Usuario::getEdad));

            for (Usuario usuario : usuariosOrdenados) {
                System.out.println("Nombre: " + usuario.getNombreUsuario() +
                        ", Edad: " + usuario.getEdad() +
                        ", Descripcion: " + usuario.getDescripcion());
            }
        }
        private static void solicitudesEmparejamiento(Scanner scanner) {
            System.out.println("solicitudes de emparejamiento:");
            System.out.println("1. Solicitudes enviadas");
            System.out.println("2. Solicitudes recibidas");
            System.out.println("3. Realizar solicitud de emparejamiento");
            System.out.println("4. Aceptar solicitud de emparejamiento");
            System.out.println("5. Rechazar solicitud de emparejamiento");
            System.out.println("6. Cancelar emparejamiento");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarSolicitudesEnviadas();
                    break;
                case 2:
                    mostrarSolicitudesRecibidas();
                    break;
                case 3:
                    realizarSolicitudesEmparejamientos(scanner);
                    break;
                case 4:
                    aceptarSolicitudEmparejamiento(scanner);
                    break;
                case 5:
                    rechazarSolicitudEmparejamiento(scanner);
                    break;
                case 6:
                    cancelarEmparejamiento(scanner);
                    break;
                default:
                    System.out.println("opcion no valida. Intentalo de nuevo");

            }
        }

        private static void mostrarSolicitudesEnviadas() {
            System.out.println(" Solicitudes de emparejamiento enviadas:");

            boolean solicitudesEnviadas = false;

            for (Usuario usuario : usuarios) {
                if (usuarioActual.equals(usuario) && usuario.getEmparejado()) {
                    System.out.println("Nombre: " + usuario.getNombreUsuario() +
                            ", Edad: " + usuario.getEdad() +
                            ", Estado de la solicitud: " + "Enviada");
                    solicitudesEnviadas = true;
                    break; // ya que un usuario solo puede enviar una solicitud a la vez
                }
            }
            if (!solicitudesEnviadas) {
                System.out.println("no has enviado ninguna solicitud de emparejamiento");
            }
        }
        private static void mostrarSolicitudesRecibidas() {
            System.out.println("Solicitudes de emparejamiento recibidas:");

            boolean solicitudesRecibidas = false;


        }
        private static void realizarSolicitudesEmparejamientos(Scanner scanner) {
            mostrarSolicitudesRecibidas();

            System.out.print("Ingresa el nombre de usuario del solicitante que deseas aceptar: ");
            String nombreSolicitante = scanner.nextLine();

        }
        private static void aceptarSolicitudEmparejamiento(Scanner scanner) {
            mostrarSolicitudesRecibidas();

            System.out.print("Ingresa el nombre de usuario del solicitante que deseas aceptar: ");
            String nombreEmisor = scanner.nextLine();
        }
        private static void cancelarEmparejamiento(Scanner scanner) {
            if (usuarioActual.isEmparejado()) {
                System.out.print("¿Estás seguro de que deseas cancelar el emparejamiento? (S/N): ");
                String respuesta = scanner.nextLine().toUpperCase();

                if (respuesta.equals("S")) {
                    usuarioActual.cancelarEmparejamiento();
                } else {
                    System.out.println("Emparejamiento no cancelado.");
                }
            } else {
                System.out.println("No estás emparejado actualmente.");
            }
        }
        private static void rechazarSolicitudEmparejamiento(Scanner scanner) {

            mostrarSolicitudesRecibidas();

            System.out.print("Ingresa el nombre de usuario del solicitante que deseas rechazar: ");
            String nombreEmisor = scanner.nextLine();

            usuarioActual.rechazarSolicitud(nombreEmisor);
        }

        private static void cerrarSesion() {

        }

    }


