import java.util.*;

public class ConversionRomanoANumero {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un numero romano: ");
        String numeroRomano = scanner.nextLine();

        if (esNumeroRomanoValido(numeroRomano)) {
            int numero = convertirRomanoANumero(numeroRomano);
            if (numero != -1) {
                System.out.println("El numero romano " + numeroRomano + " es valido y su valor es " + numero);
            } else {
                System.out.println("El numero romano " + numeroRomano + " no es valido");
            }
        } else {
            System.out.println("El numero romano " + numeroRomano + " no es valido");
        }
    }

    private static int convertirRomanoANumero(String numeroRomano) {
        int resultado = 0;
        int longitud = numeroRomano.length();
        int repetidos = 0;

        for (int i = 0; i < longitud; i++) {
            int valorActual = obtenerValorRomano(numeroRomano.charAt(i));
            if (i + 1 < longitud) {
                int valorSiguiente = obtenerValorRomano(numeroRomano.charAt(i + 1));
                if (valorActual < valorSiguiente) {
                    resultado -= valorActual;
                    repetidos = 1;
                } else {
                    resultado += valorActual;
                    if (valorActual == valorSiguiente) {
                        repetidos++;
                        if (repetidos > 3) {
                            return -1; // Si hay más de 3 caracteres repetidos seguidos, no es válido (se permite hasta 3 repeticiones)
                        }
                        if (valorActual == 5 || valorActual == 50 || valorActual == 500) {
                            return -1; // Si el valor es 5, 50 o 500, no puede repetirse
                        }
                    } else {
                        repetidos = 1;
                    }
                }
            } else {
                resultado += valorActual;
            }

            if (i + 2 < longitud) {
                int valorSiguiente = obtenerValorRomano(numeroRomano.charAt(i + 2));
                if (valorActual < valorSiguiente) {
                    return -1; // Si el valor actual es menor que el siguiente, no es válido (ejemplo: IXI)
                }
            }
        }
        return resultado;
    }

    private static boolean esNumeroRomanoValido(String numeroRomano) {
        for (int i = 0; i < numeroRomano.length(); i++) {
            if (!esCaracterRomano(numeroRomano.charAt(i))) {
                return false; // Si un carácter no es romano, entonces no es un número romano válido
            }
        }
        return true; // Si todos los caracteres son romanos, entonces es un número romano válido
    }

    private static boolean esCaracterRomano(char caracter) {
        return obtenerValoresRomanos().containsKey(caracter); // Verificar si el carácter está presente en los valores romanos
    }

    private static int obtenerValorRomano(char caracter) {
        return obtenerValoresRomanos().get(caracter); // Obtener el valor asociado al carácter romano desde el mapa
    }

    private static Map<Character, Integer> obtenerValoresRomanos() {
        Map<Character, Integer> valoresRomanos = new HashMap<>();
        valoresRomanos.put('I', 1);
        valoresRomanos.put('V', 5);
        valoresRomanos.put('X', 10);
        valoresRomanos.put('L', 50);
        valoresRomanos.put('C', 100);
        valoresRomanos.put('D', 500);
        valoresRomanos.put('M', 1000);
        return valoresRomanos; // Devolver el mapa con los valores romanos
    }
}
