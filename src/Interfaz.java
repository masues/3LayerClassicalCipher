import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

/**
 * Clase que crea la interfaz gráfica
 * 
 * @author Diego Cesar Herrera Sanchez
 * @author José Gerardo Prado Padilla
 * @author Mario Alberto Suárez Espinoza
 */
public class Interfaz extends JFrame {
	JButton ejecutar, limpiar, abrir;
	JPanel panel;
	ButtonGroup grupoFuncion;
	JRadioButton cifrar, descifrar;
	JLabel lTitulo, lFuncion, lIngresar, lSalida;
	JTextField entrada;
	JScrollPane scroll1;
	ManejadorEventos manejador;
	Fondo fondo;
	Color colorFuente;

	public Interfaz(ManejadorEventos mane) {
		// titulo y formato de la ventana
		super();
		super.setTitle("Cifrador");
		super.setSize(650, 400);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.manejador = mane;
		this.colorFuente = new Color(255, 255, 255);

		// fondo
		try {
			fondo = new Fondo(ImageIO.read(new File("./fondo.jpg")));
			JPanel panel = (JPanel) this.getContentPane();
			panel.setBorder(fondo);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		// titulo
		lTitulo = new JLabel("Cifrador Conchita");
		lTitulo.setFont(new Font("Comic Sans", Font.BOLD, 50));
		lTitulo.setForeground(colorFuente);
		lTitulo.setBounds(90, 30, 500, 70);
		this.add(lTitulo);

		// instruccion de archivo
		lIngresar = new JLabel("Ingrese la ruta del archivo: ");
		lIngresar.setBounds(50, 220, 350, 30);
		lIngresar.setForeground(colorFuente);
		this.add(lIngresar);

		// recuadro de entrada
		entrada = new JTextField();
		scroll1 = new JScrollPane(entrada);
		scroll1.setBounds(50, 250, 400, 50);
		this.add(scroll1);

		// instruccion de accion
		lFuncion = new JLabel("Elija una opcion:");
		lFuncion.setBounds(50, 130, 200, 30);
		this.add(lFuncion);
		lFuncion.setForeground(colorFuente);

		// grupo para elecciones
		panel = new JPanel();
		panel.setOpaque(false);
		grupoFuncion = new ButtonGroup();

		cifrar = new JRadioButton("cifrar");
		cifrar.setBounds(290, 300, 100, 30);
		cifrar.setForeground(colorFuente);
		grupoFuncion.add(cifrar);
		panel.add(cifrar);
		cifrar.addActionListener(manejador);
		cifrar.setOpaque(false);

		descifrar = new JRadioButton("descifrar");
		descifrar.setBounds(390, 300, 100, 30);
		descifrar.setForeground(colorFuente);
		grupoFuncion.add(descifrar);
		panel.add(descifrar);
		descifrar.addActionListener(manejador);
		descifrar.setOpaque(false);

		panel.setBounds(0, 130, 500, 50);

		this.add(panel);

		// boton que limpia los campos

		limpiar = new JButton("Limpiar campos");
		limpiar.setBounds(350, 180, 150, 25);
		limpiar.addActionListener(manejador);
		this.add(limpiar);
		// boton de ejecucion
		ejecutar = new JButton("Ejecutar");
		ejecutar.setBounds(120, 180, 100, 25);
		ejecutar.addActionListener(manejador);
		this.add(ejecutar);
		// boton de archivos
		abrir = new JButton("Explorar");
		abrir.setBounds(470, 260, 100, 25);
		abrir.addActionListener(manejador);
		this.add(abrir);
		// label de mensajes
		lSalida = new JLabel("");
		lSalida.setBounds(50, 310, 400, 30);
		lSalida.setForeground(colorFuente);
		this.add(lSalida);

		super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		super.setVisible(true);

	}

	public static void main(String[] args) {
		ManejadorEventos manejador = new ManejadorEventos();
		Interfaz inter = new Interfaz(manejador);
		manejador.inter = inter;

	}
}