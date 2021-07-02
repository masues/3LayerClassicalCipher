import javax.swing.*;

public class Ventana extends JFrame{
	JButton limpiar,generar;
	JRadioButton hombre,mujer;
	JComboBox<String> ciudades;
	JLabel nomb, apMat, apPat, fechNac, dia, mes, anio, ciudad,sexo,mensajes;
	JTextField nombreI, apMatI, apPatI, diaI, mesI, anioI;
	JPanel panel;
	ButtonGroup grupoSexo;
	

	public Ventana(){
		super();
		super.setTitle("Practica 10 - Excepciones");
		super.setSize(520,600);
		this.setLayout (null);
		this.setLocationRelativeTo(null);
		

		//labels
		nomb=new JLabel("Nombre");
		nomb.setBounds(155,37,100,30);//230 para la caja
		this.add(nomb);

		apMat=new JLabel("Apellido Materno");
		apMat.setBounds(105,103,100,30);
		this.add(apMat);

		apPat=new JLabel("Apellido Paterno");
		apPat.setBounds(106,70,100,30);
		this.add(apPat);

		fechNac=new JLabel("Fecha de Nacimiento                     Dia                      Mes                  Anio");
		fechNac.setBounds(80,175,500,30);
		this.add(fechNac);

		ciudad=new JLabel("Ciudad de Nacimiento");
		ciudad.setBounds(74,230,150,30);
		this.add(ciudad);


		//TextFields
		nombreI= new JTextField();
		nombreI.setBounds(230,37,260,90);
		this.add(nombreI);

		apMatI= new JTextField();
		apMatI.setBounds(230,103,260,30);
		this.add(apMatI);
		
		apPatI= new JTextField();
		apPatI.setBounds(230,70,260,30);
		this.add(apPatI);
		
		diaI= new JTextField();
		diaI.setBounds(290,175,30,30);
		this.add(diaI);

		mesI= new JTextField();
		mesI.setBounds(375,175,30,30);
		this.add(mesI);

		anioI= new JTextField();
		anioI.setBounds(455,175,30,30);
		this.add(anioI);

		//buttons
		limpiar=new JButton("Limpiar campos");
		limpiar.setBounds(205,370,130,25);
		//limpiar.addActionListener(manejador);
		this.add(limpiar);

		generar=new JButton("Generar CURP");
		generar.setBounds(360,370,130,25);
		//generar.addActionListener(manejador);
		this.add(generar);

		//ComboBox
		//String[] opcionesC=Diccionario.palabras;
		ciudades=new JComboBox<String>();
		ciudades.setBounds(230,230,260,30);
		this.add(ciudades);

		//RadioButtons
		panel=new JPanel();
		grupoSexo=new ButtonGroup();
		

		sexo=new JLabel("Sexo");
		sexo.setBounds(50,300,50,30);
		panel.add(sexo);

		hombre=new JRadioButton("Hombre");
		hombre.setBounds(290,300,100,30);
		grupoSexo.add(hombre);
		panel.add(hombre);
		//hombre.addActionListener(manejador);
		

		mujer=new JRadioButton("Mujer");
		mujer.setBounds(390,300,100,30);
		grupoSexo.add(mujer);
		panel.add(mujer);
		//mujer.addActionListener(manejador);
		

		panel.setBounds(140,315,500,50);

		this.add(panel);

		//mensajes
		mensajes=new JLabel();
		mensajes.setBounds(30,370,460,110);
		this.add(mensajes);


		super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setVisible(true);

	}

	public static void main(String[] args){
		
		Ventana miVentana=new Ventana();
		
	}

}