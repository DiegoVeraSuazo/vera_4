import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UsoExpresionesRegulares {

    public static void main(String[] args) {
        menu();
    }

    /**
     * Metodo que crea los usuariois
     * @return Un string con el nombre completo el rut y la direccion
     */

    private static String creacionUsuario() {
        System.out.println("Ingrese su nombre");
        String nombre = ingresoNombreApellido();

        System.out.println("Ingrese su apellido");
        String apellido = ingresoNombreApellido();

        String RUT = validacionRut();

        String direccion = ingresoDireccion();

        String nombreCompleto = nombre +" "+apellido;
        return nombreCompleto+","+ RUT+","+direccion;
    }

    /**
     * Metodo que ingresa el nombre en general para ser validado y ver si sigue las pautas
     * @return Regresa el string nombre
     */
    private static String ingresoNombreApellido() {
        String nombre;
        do {
            nombre = teclado();
        } while (ingresoPalabras(nombre));
        return nombre;

    }

    /**
     * Metodo que valida el string nombre
     * @param nombre es la entrada realizada en ingresoNombreApellido()
     * @return regresa un boolean si es verdadero o falso
     */

    public static boolean ingresoPalabras(String nombre) {
        boolean noAceptable = true;

        if ((nombre).matches("([a-z]|[A-Z]|\\s)+")){
            noAceptable = false;
            System.out.println("La entrada "+nombre+" es valida");
        } else {
            System.out.println("No ingrese numeros, intente de nuevo");
            noAceptable = true;
            }

        return noAceptable;
    }


    /**
     * Metodo que ordena la direccion
     * @return devuelve ordenado segun las pautas la direccion
     */
    private static String ingresoDireccion() {
        System.out.println("Ingrese el nombre de la Calle en que vive");
        String calle = ingresoNombreApellido();

        System.out.println("Ingrese su Numero");
        String numeroCalle = stringNumeros();

        System.out.println("Ingrese la Ciudad en la que vive");
        String ciudad = ingresoNombreApellido();

        System.out.println("Ingrese la Region en que vive");
        String region = ingresoNombreApellido();

        return calle+","+numeroCalle+","+ciudad+","+region;
    }

    /**
     * Metodo que Tranforma int a String
     * @return devuelve el int trasformado
     */
    private static String stringNumeros() {
        int numero = validarNumero();
        String numeroEnString = String.valueOf(numero);
        return  numeroEnString;
    }

    /**
     * Metodo que llama el teclado para escribir
     * @return el string escrito en el teclado
     */
    public static String teclado() {
        Scanner teclado = new Scanner(System.in);
        String texto = teclado.nextLine();
        return texto;
    }

    /**
     * Metodo que escribe el rut para ser validado
     * @return regresa el String del rut
     */
    public static String validacionRut() {
        String Rut;
        do {
            System.out.println("Escriba el RUT del Empleado");
            Rut = teclado();
        } while (validarRut(Rut));
        return Rut;
    }

    /**
     * Metodo que ve si el rut escrito es correcto
     * @param rut es el string a corroborar
     * @return un booleano
     */
    public static boolean validarRut(String rut) {
        boolean validacion = true;
        try {
            rut =  rut.toUpperCase();       //12.123.123-K
            rut = rut.replace(".", "");     //12123123-K
            rut = rut.replace("-", "");     //12123123K

            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));  //12123123

            char dv = rut.charAt(rut.length() - 1);     //K

            int m = 0, s = 1;

            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }

            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = false;
            }
        } catch (java.lang.NumberFormatException e) {
            System.out.println("Error Numerico");
        } catch (Exception e) {
            System.out.println("Error Generico");
        }
        return validacion;
    }

    /**
     * Metodo que agrega texto al .csv
     */
    public static void agregarTexto(){
        String ruta = "usuarios.csv";
        Path archivo = Paths.get(ruta);
        String texto;
        System.out.println("Ingrese un nuevo usuario");
        String textoAgregado = creacionUsuario();
        try {
            String textoAnterior = new String(Files.readAllBytes(archivo));
            texto = textoAnterior+"\n"+textoAgregado;
            Files.write(archivo, texto.getBytes());
            System.out.println("Se ha guardado el archivo");
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser guardado");
        }
    }

    /**
     * Metodo que crea o reinicia el archivo "usuarios.csv"
     * @param texto es el texto con el que inicia el archivo
     */
    public static void crearArchivo(String texto){
        String ruta = "usuarios.csv";
        Path archivo = Paths.get(ruta);
        try {
            Files.write(archivo, texto.getBytes());
            System.out.println("Se ha guardado el archivo");
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser guardado");
        }
    }

    /**
     * Metodo que permite ver lo escrito en el archivo
     * @param ruta es el archivo donde se encuentra lo escrito
     */
    public static void leerArchivo(String ruta){
        Path archivo = Paths.get(ruta);
        String texto = "";
        try {
            texto = new String(Files.readAllBytes(archivo));
            System.out.println("El contenido del archivo es: \n"+texto);
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser leido");
        }
    }

    /**
     * Metodo que llama a un menu clasico
     */
    public static void menu(){
        boolean ejec = true;
        System.out.println("Bienvenido al menu principal, ¿Que desea hacer?");
        do {
            System.out.println("Selecciona la operacion a realizar");
            System.out.println("1 - Reinicio Archivo de Usuarios");
            System.out.println("2 - Ingreso de usuario nuevo");
            System.out.println("3 - Muestra usuarios");
            System.out.println("9 - Terminar");
            int opcion = validarNumero();
            if (opcion >= 1 && opcion <= 3 ) {
                seleccion(opcion, ejec);

            } else if (opcion == 9) {
                ejec = false;
            } else {
                System.out.println("Opcion no valida");
            }

        } while (ejec);
    }

    /**
     * Extension del menu que usa un Switch para ir a cada metodo
     * @param opcion ingresado para saber a que caso ir
     * @param ejec usado para saber cuando esta activo
     */
    private static void seleccion(int opcion, boolean ejec) {

        switch(opcion){
            case 1:
                crearArchivo("");
                break;
            case 2:
                agregarTexto();
                break;
            case 3:
                leerArchivo("usuarios.csv");
                break;
            default:
                break;
        }

    }

    /**
     * Metodo que valida los numeros ingresados de letra y/o caracteres
     * @return Retorna una Variable de tipo int.
     */
    public static int validarNumero() {
        Scanner teclado = new Scanner(System.in);

        Integer entrada = 0;
        do {
            try {
                entrada = teclado.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("No ingrese letras u oraciones");
                teclado.next();
                entrada = -1;
            }
            if (entrada < 0){
                System.out.println("Opcion no valida");
            }
        } while (entrada < 0);
        return entrada;
    }
}
