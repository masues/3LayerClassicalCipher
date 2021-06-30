/**
 * Clase que implementa el funcionamiento de un algoritmo de cifrado afín
 * Este algoritmo sólo soporta letras mayúsculas. Soporta la letra 'Ñ' si se
 * selecciona el alfabeto castellano. En caso de que la cadena a cifrar contenga
 * un carácter que no está soportado, este es sustituido por una 'X'
 * @author Mario Alberto Suárez Espinoza
 */
public class Afin {
    // Constante de decimación
    int a;
    // Constante de decimación inversa
    int aInv;
    // Constante de desplazamiento
    int b;
    // Tamaño del alfabeto
    // Para alfabeto con ñ, n=27
    // Para alfabeto sin n, n=26
    int n;
    
    /**
     * Método constructor
     * @param   a                   Constante de decimación
     * @param   b                   Constante de desplazamiento
     * @param   englishAlphabet     Bandera que indica si se usa el alfabeto
     *                              inglés. Si se usa el alfabeto castellano
     *                              la bandera tendrá el valor false.
     */
    public Afin(int a,int b,boolean englishAlphabet){
        this.a = a;
        this.b = b;
        if (englishAlphabet)
            this.n = 26;
        else
            this.n = 27;
        this.getModMultInverse();
    }
    
    /**
     * Método para la obtención de la constante de decimación inversa
     */
    private void getModMultInverse(){
        // Método ingenuo para la obtneción de la constante de decimación
        // inversa.
        // Consiste en probar todos los números, de 1 a n-1 para probar cual
        // de ellos resulta en la ecuación: a * aInv = 1 (mod n)
        for (int i = 1; i < this.n; i++)
            if (((this.a%this.n) * (i%this.n)) % this.n == 1)
                this.aInv = i;
    }
    
    /**
     * Método para mapear entre caracteres del alfabeto y su correspondiente
     * número
     * El alfabeto inglés va de A = 65 hasta Z = 90. Estos números son
     * normalizados a A = 0 hasta Z = 25.
     * Para el alfabeto castellano se añade la letra Ñ, al cual le corresponde
     * Ñ = 209, pero es transformado a Ñ = 15, posteriormente O = 16 y así
     * sucesivamente.
     * 
     * @param   character   Carácter a transformar a su valor numérico
     * @return              Representación numérica del carácter ingresado
     */
    private int toNumeric(char character){
       int numericRep = (int) character;
       
       // Si el caracter está fuera del alfabeto, se le asigna la representación
       // de una X
       if (numericRep < 65 || (numericRep > 90 && character != 'Ñ'))
           numericRep = (int) 'X';
       
       // Si el alfabeto es castellano, se suma un 1 a las letras 'O' u mayores
       if (this.n==27 && numericRep >= (int) 'O'){
           numericRep++;
           // En caso de que se trate de la Ñ, se le asigna el valor ASCII
           // de la letra 'O'.
           if (character == 'Ñ')
               numericRep = (int) 'O';
       }
       else if (character == 'Ñ')
           numericRep = (int) 'X';
           
       // Se resta 65 para normalizar
       numericRep -= 65;
       
       return numericRep;
       
    }
    
    /**
     * Método que transforma un valor numérico a carácter
     * @param   numericRep  Valor numérico para transformar a su representación
     *                      en carácter.
     * @return              Carácter que representa al número ingresado
     */
    private char toChar(int numericRep){
        // Si el alfabeto es castellano y se tiene la representación de las
        // letras 'O', 'Ñ' o mayores
        if (this.n==27 && numericRep >= (int) 'O' - 65){
            // Si se trataba de la Ñ, se devuelve directamente
            if (numericRep == ((int) 'O') - 65)
               return 'Ñ';
            // Si se trataba de la letra 'O' u mayores se resta 1 a la
            // representación numérica
            numericRep--;
        }
        return (char) (numericRep + 65);
        
    }
    
    /**
     * Este método tiene como entrada al mensaje en claro y como salida devuelve
     * al mensaje cifrado mediante el algoritmo afín.
     * @param   Mcla    Cadena que contiene el mensaje en claro
     * @return          Cadena cifrada mediante el algoritmo afín
     */
    public String encrypt(String Mcla){
        String criptograma = ""; // variable que almacenará el mensaje cifrado
        char[] mcla = Mcla.toCharArray();
        
        // El cifrado se realiza carácter por carácter aplicando la fórmula
        // Ci = (a*Mi + b) mod n
        for (char character: mcla){
            criptograma += this.toChar((a * this.toNumeric(character) + b) % n);
        }
        return criptograma;
    }
    
    /**
     * Este método tiene como entrada una cadena cifrada mediante el algoritmo
     * afín y devuelve la cadena descifrada.
     * @param   Cripto  Cadena que contiene al mensaje cifrado
     * @return          Cadena que contiene al mensaje descifrado
     */
    public String decrypt(String Cripto){
        String mcla = ""; // variable que almacenará al mensaje descifrado
        char[] cripto = Cripto.toCharArray();
        
        // El descifrado se realiza carácter por carácter aplicando la fórmula
        // Mi = ((Ci - b) * aInv) mod n
        for (char character: cripto){
            mcla += this.toChar( ((this.toNumeric(character) - b) * aInv) % n );
        }
        return mcla;
    }
    
}
