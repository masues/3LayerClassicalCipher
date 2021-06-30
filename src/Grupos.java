/**
 * Clase que implementa el algoritmo de cifrado Transposición por grupos
 * @author Mario Alberto Suárez Espinoza
 */
public class Grupos{
    private int p; // Periodo del grupo
    private int [] PMcla; // Orden de la transposición
    
    /**
     * Método constructor
     * @param PMcla Arreglo de enteros que contiene el orden en el que se
     *              realiza la permutación de grupos
     */
    public Grupos(int[] PMcla){
        this.PMcla = PMcla;
        this.p = PMcla.length;
    }

    /**
     * Método para cifrar una cadena utilizando transposición por grupos
     * @param   Mcla    Cadena a cifrar  
     * @return          Cadena cifrada
     */
    public String encrypt(String Mcla){
        int k = (int) Mcla.length()/p; // Número de grupos que seran ordenados
        String cripto = ""; // Variable que almacena al mensaje cifrado

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

}