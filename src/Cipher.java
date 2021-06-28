/**
 *
 * @author Mario Alberto Suárez Espinoza
 */
public class Cipher {
    public static void main(String[] args) {
        // Algoritmo afín con a=5, b=0, alfabeto castellano
        Afin afin = new Afin(5,0,false);
        String mcla = "LAPIZ";
        System.out.println("mcla = "+mcla);
        System.out.println("crip = "+afin.encrypt(mcla));
        System.out.println("decrypt(crip) = "+afin.decrypt(afin.encrypt(mcla)));
        String cripto2 = "VWÑDY";
        // Algoritmo afín con a=5, b=3, alfabeto castellano
        Afin afin2 = new Afin(5,3,false);
        System.out.println("cript2 = "+cripto2);
        System.out.println("decrypt(cript2) = "+afin2.decrypt(cripto2));
    }
}
