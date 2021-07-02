/**
 * Clase que implementa el funcionamiento de un algoritmo de cifrado Vigeneré
 * Este algoritmo sólo soporta letras mayúsculas. Soporta la letra 'Ñ', este
 * es manejado con su valor ASCII el cual es 165 o Unicode 209
 * @author Diego Cesar Herrera Sanchez
 */
import java.util.*;

// MODIFICAR PARA QUE RECIVA LA CADENA DE UNA VEZ.
public class Vigenere{
		char abecedario[];
		int matriz[][];
		String texto,clave;

	public Vigenere(String clave){

		//Tamaño de las matrices a utilizar
		matriz = new int [27][27];

		// Letras que se usaran, como la letra ´Ñ´ es un simbolo especial, este se
		// maneja en su forma unicode.
		char abc[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N',209,'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		abecedario=abc;

		
		// Creación de la matriz de 27 x 27, este se utilizará para saber cual es la
		// letra correspondiente a partir del algoritmo de Vigeneré.
		int numero=1;
		int cont=0;
		//int i;
		for (int i=0;i<27;i++){
			for (int j=0; j<27; j++){
					matriz[i][j]= numero-1;
					numero++;
					if(numero>27){
						numero=1;
					}
			}numero++;
			if(numero>27){
				numero=1;
			}
		}

		//se toma la clave recibida
		this.clave=clave;

	}

	public static void main (String g[]){
		Scanner sc = new Scanner(System.in);

		
		Vigenere vg =new Vigenere("ESTAESLACLAVE");
		System.out.println("Palabra encriptar");
		String texto= sc.nextLine();
		
		String cripto = vg.encrypt(texto);
		String descripto = vg.decrypt(cripto);
		System.out.println(cripto);
		System.out.println(descripto);
	}


	public String encrypt(String Mcla){
        return this.encrypt(Mcla, matriz, abecedario);
    }

    public String decrypt(String Mcla){
        return this.decrypt(Mcla, matriz, abecedario);
    }

	/*
	* Con ayuda de esta función se busca que la clave y la cadena sean del mismo
	* tamaño, agregando las mismas letras en la clave tantas veces lo sea necesario
	* regresa en el array, este lo regresa.
	*/
	public char[] rellena(char[] clave,int longclave, int longtexto){
		// Se crea un nuevo array para que se almacenen las letras
		char[] arrayclave = new char[longtexto];
		int i=0,j=0;

		// Dentro del ciclo comprueba si ha llegado al final de la clave, en caso
		// de que así sea, entonces volverá a la letra inicial.
		do{

			// En caso de que ya se llego al final de la clave, se reinicia.
			if(j==longclave){
				j=0;
				arrayclave[i] = clave[j];
			}else{
				arrayclave[i] = clave[j];
				j++;
				i++;
			}
		}while(i<longtexto);
	return arrayclave;
	}


	/*
	* Función encargada de encriptar la cadena, recibe los parametros de la CADENA
	* así como la clave a utilizarjunto con los arrelgos, se obtiene sus longitudes y se crean las
	* dos arreglos donde se guardarán los valores de cada letra.
	*/
	private String encrypt(String mcla, int[][] matriz, char[] abecedario){

		// Se obtiene las longitudes del mcla y de la clave
		int longText= mcla.length();
		int longClave= clave.length();

		// Se crean arreglos en donde se contienen las letras de cada cadena.
		char[] letrasT = mcla.toCharArray();
		char[] letrasCl = clave.toCharArray();

		// Comprobamos si las longitudes de la clave y el mcla, en caso de que falten
		// espacios, este manda a llamar la Función que reajusta.
		if(longText > longClave){
			letrasCl = rellena(letrasCl, longClave, longText);
			longClave = letrasCl.length;
		}

		// Creamos dos arreglos para almacenar las posciciones de las letras
		int[] posicionT = new int[longText];
		int[] posicionC = new int[longClave];

		// Esto ciclos detecta las posiciones y las almacena en los arreglos anteriores
		for(int i=0; i<longText; i++){
			for (int p=0; p<27;p++){
				if (letrasT[i]==abecedario[p])
					posicionT[i]=p;
			}
		}
		for(int i=0; i<longClave; i++){
			for (int p=0; p<27;p++){
				if (letrasCl[i]==abecedario[p])
					posicionC[i]=p;
			}
		}

		// En esta parte se obtiene las nuevas ordenadas para saber cual letra
		// corresponde, recordemos que como los dos arreglos tienen el mismo tamaño,
		// es una matriz cuadrada por lo que solo se usa un ciclo for.
		int[] posCripto = new int[longText];
		char[] letCripto = new char[longText];
		for(int i=0; i < longText; i++){
			posCripto[i]=matriz[posicionT[i]][posicionC[i]];
		}

		// Colocamos las letras que corresponde a las posiciones nuevas obtenidas.
		int longCripto = posCripto.length;
		for(int i=0; i<longCripto; i++){
			for (int p=0; p<27;p++){
				if (p==posCripto[i])
					letCripto[i]=abecedario[p];
			}
		}

		//FALTA CONVERTIR EL ARREGLO EN CADENA.
		String cripto = String.valueOf(letCripto);
		/*System.out.println("Clave cifrado "+clave);

		System.out.println("Clave cifrado "+String.valueOf(letrasCl));*/
	return cripto;
	}


	/*
	* Función encargada de desencriptar la cadena encriptada, recibe los parametros de la CADENA
	* así como la clave a utilizar junto con los arrelgos, se obtiene sus longitudes y se crean las
	* dos arreglos donde se guardarán los valores de cada letra.
	*/
	private String decrypt(String cript, int[][] matriz, char[] abecedario){

		// Se obtienen las longitudes del cripto y de la clave.
		int longCript= cript.length();
		int longClave= clave.length();

		// Arreglos donde almacenamos los caracteres de cada cadena.
		char[] letrasT = cript.toCharArray();
		char[] letrasCl = clave.toCharArray();

		// Si la clave es más pequeña que el cripto, mandamos a rellenarla.
		if(longCript > longClave){
			letrasCl = rellena(letrasCl, longClave, longCript);
			longClave = letrasCl.length;
		}

		// Arreglos que almacenaran las posiciones de la clave y el cripto.
		int[] posicionT = new int[longCript];
		int[] posicionC = new int[longClave];
		for(int i=0; i<longCript; i++){
			for (int p=0; p<27;p++){
				if (letrasT[i]==abecedario[p])
					posicionT[i]=p;
			}
		}
		for(int i=0; i<longClave; i++){
			for (int p=0; p<27;p++){
				if (letrasCl[i]==abecedario[p])
					posicionC[i]=p;
			}
		}


		// En esta parte se obtiene la posición de la letras, esto se obtiene
		// siguiendo la regla, posición de la letra cripto - posición de la clave
		// en caso de que el valor sea menor a 0, se suman 27.
		int[] posCripto = new int[longCript];
		char[] letMcla = new char[longCript];
		for(int i=0; i < longCript; i++){
			int pos = posicionT[i]-posicionC[i];
			if(pos < 0){
				pos+=27;
			}
			posCripto[i]=pos;
		}

		// Colocamos las letras que corresponde la posición para obtener el mcla
		int longCripto = posCripto.length;
		for(int i=0; i<longCripto; i++){
			for (int p=0; p<27;p++){
				if (p==posCripto[i])
					letMcla[i]=abecedario[p];
			}
		}

		//FALTA CONVERTIR EL ARREGLO EN CADENA.
		String mcla = String.valueOf(letMcla);
		/*System.out.println("Clave descifrado "+clave);
		System.out.println("Clave descifrado "+String.valueOf(letrasCl));
		*/
	return mcla;
	}

}
