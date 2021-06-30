/**
 *
 * @author Mario Alberto Suárez Espinoza
 */
public class Cipher {
    public static void main(String[] args) {

        /*                    Algoritmo de cifrado afín                       */
        // Algoritmo afín con a=5, b=0, alfabeto castellano
        Afin afin = new Afin(5,0,false);
        String mclaAfin = "LAPIZ";
        System.out.println("************** Cifrado afín **************");
        System.out.println("         mcla = "+mclaAfin);
        String cripAfin = afin.encrypt(mclaAfin); // Cifrado
        System.out.println("        crip  = "+cripAfin);
        System.out.println("decrypt(crip) = "+afin.decrypt(cripAfin)); // Descifrado
        System.out.println("");
        
        String crip2 = "VWÑDY";
        // Algoritmo afín con a=5, b=3, alfabeto castellano
        Afin afin2 = new Afin(5,3,false);
        System.out.println("        cript2  = "+crip2);
        System.out.println("decrypt(cript2) = "+afin2.decrypt(crip2));
        System.out.println("");
        System.out.println("");

        /*           Algoritmo de cifrado transposición por grupos            */
        int [] PMcla = new int [] {1,3,5,2,4};
        Grupos transGrupos = new Grupos(PMcla);
        String mclaGr = "LAINSOPORTABLELEVEDADDELSER";
        System.out.println("************** Cifrado transposición por grupos **************");
        System.out.println("         mcla = "+mclaGr);
        String cripGr = transGrupos.encrypt(mclaGr); // Cifrado
        System.out.println("         crip = "+cripGr);
        System.out.println("decrypt(crip) = "+transGrupos.decrypt(cripGr)); // Descifrado
    }
}
