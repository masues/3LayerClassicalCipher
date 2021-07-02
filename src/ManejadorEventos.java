import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * Clase para manejar excepciones en la selección de acciones
 * 
 * @author Diego Cesar Herrera Sanchez
 * @author José Gerardo Prado Padilla
 * @author Mario Alberto Suárez Espinoza
 */
public class ManejadorEventos implements ActionListener {
	Interfaz inter;
	String texto, textoClaro, textoCifrado, archivo, ruta;

	public ManejadorEventos() {
		texto = "";
		textoClaro = "";
		textoCifrado = "";
		archivo = "";
		ruta = "";
	}

	public void actionPerformed(ActionEvent e) {
		String evento = e.getActionCommand();// accioncommand para los botones
		switch (evento) {
			case "Ejecutar":

				try {
					this.comprobarCadenas();// se comprueba que exista texto en el recuadro
					this.comprobarSeleccion();// se comprueba que exista una eleccion
					this.obtenerTexto();// se obtiene la ruta escrita
					if (inter.cifrar.isSelected())
						this.generarCifrado();// si esta seleccionado cifrar, genera el cifrado
					else if (inter.descifrar.isSelected())
						this.generarDescifrado();// si esta seleccionado descifrar, genera el descifrado
				} catch (CampoVacioException e1) {
					inter.lSalida.setText("Debes Seleccionar un archivo"); // excepcion controlada

				} catch (SeleccionException e2) {
					inter.lSalida.setText("Selecciona algo por hacer");// excepcion controlada
				} catch (IOException e3) {
					inter.lSalida.setText("Archivo no encontrado");// excepcion controlada
				}

				break;
			case "Limpiar campos":// limpia los campos
				inter.entrada.setText("");
				inter.lSalida.setText("");
				inter.grupoFuncion.clearSelection();
				break;
			case "Explorar":
				// muestra el cuadro de diálogo de archivos, para que el usuario pueda elegir el
				// archivo a abrir
				this.buscarArchivo();

				break;
		}
	}

	public void buscarArchivo() {
		JFileChooser selectorArchivos = new JFileChooser();
		selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		selectorArchivos.showOpenDialog(null);

		File archivo = selectorArchivos.getSelectedFile();

		if ((archivo == null) || (archivo.getName().equals(""))) {
			inter.lSalida.setText("Debes Seleccionar un archivo");
		} else {
			inter.entrada.setText(archivo.getAbsolutePath());
		}

	}

	public void comprobarCadenas() throws CampoVacioException {
		if (inter.entrada.getText().equals(""))
			throw new CampoVacioException();
	}

	public void comprobarSeleccion() throws SeleccionException {
		if (inter.grupoFuncion.getSelection() == null)
			throw new SeleccionException();
	}

	public void obtenerTexto() throws IOException {
		File f;
		archivo = inter.entrada.getText();// se obtinene la cadena ingresada
		f = new File(archivo);// se crea un file
		ruta = f.getAbsolutePath();// se obtiene la ruta completa, servira para el guardado

		if (f.exists()) {// si el archivo existe
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			texto = "";
			String linea;
			while ((linea = br.readLine()) != null) {
				texto += linea;
			} // lee todo el texto
			fr.close();
			br.close();
		} else
			throw new IOException();// sino se envia una exception para avisar que no existe

		texto = texto.replaceAll("\\s", "");
		texto = this.convertirMayusculas(texto);

	}

	private void generarCifrado() {

		// primera capa del cifrado
		// transposicion por grupos
		System.out.println("Texto claro " + texto);
		int[] PMcla = new int[] { 1, 3, 5, 2, 4 };
		Grupos transGrupos = new Grupos(PMcla);
		textoCifrado = transGrupos.encrypt(texto);

		// segunda capa del cifrado
		// cifrado vigenere

		Vigenere vg = new Vigenere("ESTAESLACLAVE");
		textoCifrado = vg.encrypt(textoCifrado);

		// tercera capa del cifrado
		// cifrado afin

		Afin afin = new Afin(5, 0, false);
		textoCifrado = afin.encrypt(textoCifrado);

		this.guardarCifrado();// se invoca el metodo para guardar el cifrado
	}

	public void guardarCifrado() {
		// se le da formato al archivo generado
		File f = new File(ruta + "-Cifrado.gva");

		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(textoCifrado);
			bw.flush();
			fw.close();
			inter.lSalida.setText("Cifrado generado con exito");
		} catch (Exception e) {
			inter.lSalida.setText("No se pudo guardar el archivo");
		}

	}

	private void generarDescifrado() {

		// tercera capa del cifrado
		// cifrado afin
		System.out.println("Cifrado " + texto);
		Afin afin = new Afin(5, 0, false);
		textoClaro = afin.decrypt(texto);

		// segunda capa del cifrado
		// cifrado vigenere

		Vigenere vg = new Vigenere("ESTAESLACLAVE");
		textoClaro = vg.decrypt(textoClaro);

		// primera capa del cifrado
		// transposicion por grupos

		int[] PMcla = new int[] { 1, 3, 5, 2, 4 };
		Grupos transGrupos = new Grupos(PMcla);
		textoClaro = transGrupos.decrypt(textoClaro);

		// se invoca la funcion para guardar el archivo
		this.guardarDescifrado();
	}

	public void guardarDescifrado() {
		// se le da formato al archivo generado
		File f = new File(ruta + "-Descifrado.txt");

		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(textoClaro);
			bw.flush();
			fw.close();
			inter.lSalida.setText("Descifrado generado con exito");
		} catch (Exception e) {
			inter.lSalida.setText("No se pudo guardar el archivo");
		}

	}

	public String convertirMayusculas(String cadena) {
		char[] array = cadena.toCharArray();
		char letra;
		for (int i = 0; i < array.length; i++) {
			if (array[i] >= 97 && array[i] <= 122) {
				letra = array[i];
				array[i] = (char) ((int) letra - 32);
			} else if (array[i] == 164) {
				array[i] = 165;
			}
		}

		return String.valueOf(array);
	}

}