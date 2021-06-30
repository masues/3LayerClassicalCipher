import java.util.List;
import java.util.stream.*;

/**
 * Clase que implementa el algoritmo de cifrado Transposición por grupos
 * @author Mario Alberto Suárez Espinoza
 */
public class Grupos{
    private int p; // Periodo del grupo
    private int [] PMcla; // Orden de la permutación
    private int [] invPMcla; // Orden de permutación inversa (para descifrado)
    
    /**
     * Método constructor
     * @param PMcla Arreglo de enteros que contiene el orden en el que se
     *              realiza la permutación de grupos
     */
    public Grupos(int[] PMcla){
        this.PMcla = PMcla;
        this.p = PMcla.length;
        this.setInvPMcla();
    }

    /**
     * Método para obtener el arreglo de permutación inversa
     */
    private void setInvPMcla(){
        // Inicializa un arreglo de permutación inversa de tamaño igual
        // al arreglo de permutación PMcla original
        int [] invPMcla = new int[p];
        // Genera una lista utilizando el arreglo PMcla original
        List<Integer> PMcla =
            IntStream.of(this.PMcla).boxed().collect(Collectors.toList());
        // Al arreglo de permutación inversa le asigna el índice del elemento
        // de la lista PMcla que contiene al índice de la iteración actual
        for (int i = 0; i < p; i++){
            invPMcla[i] = PMcla.indexOf(i+1) + 1;
        }

        // Almacena el arreglo de permutación inversa como propiedad del objeto
        this.invPMcla = invPMcla;
    }

    /**
     * Método para cifrar o descifrar una cadena utilizando transposición por
     * grupos
     * @param   Mcla        Cadena a cifrar o descifrar
     * @param   isDecrypt   Si es true, se realiza un descifrado, en caso
     *                      contrario cifra la cadena
     * @return              Cadena cifrada o descifrada
     */
    private String encrypt(String Mcla, boolean isDecrypt){
        int k = (int) Mcla.length()/p; // Número de grupos que seran ordenados
        // Variable que almacena al mensaje cifrado o descifrado
        String cripto = "";

        // Selecciona al arreglo de permutación dependiendo si cifra o descifra
        PMcla = isDecrypt ? this.invPMcla : this.PMcla;

        // Iteración sobre el número de grupos
        for (int i = 0; i < k; i++){
            // Iteración sobre el el arreglo de permutaciones PMcla
            for (int indiceCifrado: PMcla){
                cripto += Mcla.charAt(i*p + indiceCifrado - 1);
            }
        }

        // Agrega aquellos caracteres que no pudieron ser permutados debido
        // a la longitud de la cadena Mcla
        cripto += Mcla.substring(k*p);
        
        return cripto;
    }

    /**
     * Sobrecarga de métodos para encapsular el cifrado del mensaje
     * @param   Mcla    Mensaje a cifrar
     * @return          Mensaje cifrado
     */
    public String encrypt(String Mcla){
        return this.encrypt(Mcla, false);
    }

    /**
     * Sobrecarga de métodos para encapsular el descifrado del mensaje
     * @param   Mcla    Mensaje a descifrar
     * @return          Mensaje descifrado
     */
    public String decrypt(String Mcla){
        return this.encrypt(Mcla, true);
    }

}
