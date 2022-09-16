package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import datatypes.DtActDeportiva;
import datatypes.DtClase;
import datatypes.DtInstitucionD;
import datatypes.DtSocio;
import datatypes.DtUsuario;

import excepciones.InstitucionRepetidaExcepcion;
import excepciones.NoesxisteSocio;
import excepciones.NoexsiteIntDep;
import interfaces.Fabrica;
import interfaces.IControlador;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Component;
import javax.swing.Box;


public class Principal {

	private JFrame frame;
    private AltaInstitucion altaInstitucion;
    private AltaActividad AltaAc;
    private AltaClase AltaCl;
    private AltaUsuario altaUsuario;
    private ConsultaActividadDep consActDep;
    private RegistroSocioAClase regSClase;
    private ConsultaUsuario ConstUser;
 
   
    private ModificarActDep modActDep;
    private ModificarUsuario modUser;
    private ModificarInstitucion modIntD;
    private RankingDeClases rankingClases;
    private ConsultadedictadodeClase ConsultdeDC;
    private RankingDeActividadDep rankingActD;
    private final JLabel fondo = new JLabel("");

    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Principal window = new Principal();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		
		estructuraJframe();
		Fabrica fab = Fabrica.getInstancia();
    	IControlador iC = fab.getIControlador();
    	
    	altaUsuario= new AltaUsuario(iC);
    	altaInstitucion= new AltaInstitucion(iC);
    	AltaAc= new AltaActividad(iC);
    	AltaCl= new AltaClase(iC);
    	ConstUser= new ConsultaUsuario(iC);    	
    	regSClase= new RegistroSocioAClase(iC);
    	consActDep= new ConsultaActividadDep(iC);
    	//casos no obligatorios
    	modActDep= new ModificarActDep(iC);
    	modUser= new ModificarUsuario(iC);
    	rankingClases = new RankingDeClases(iC);
    	modIntD= new ModificarInstitucion(iC);
    	ConsultdeDC= new ConsultadedictadodeClase(iC);
    	rankingActD= new RankingDeActividadDep(iC);
    	
    	//Casos Obligatorios
    	altaUsuario.setLocation(25, 43);
    	altaUsuario.setVisible(false);
    	frame.getContentPane().add(altaUsuario);
    	
    	consActDep.setLocation(25, 11);
    	consActDep.setVisible(false);
    	frame.getContentPane().add(consActDep);

    	
    	altaInstitucion.setLocation(25, 43);
    	altaInstitucion.setVisible(false);
    	frame.getContentPane().add(altaInstitucion);
    	
    	AltaAc.setLocation(25, 43);
    	AltaAc.setVisible(false);
    	frame.getContentPane().add(AltaAc);
    	
    	AltaCl.setLocation(25, 43);
    	AltaCl.setVisible(false);
    	frame.getContentPane().add(AltaCl);
    	
    	regSClase.setLocation(25, 43);
    	regSClase.setVisible(false);
    	frame.getContentPane().add(regSClase);
    	
    	ConstUser.setLocation(25, 43);
    	ConstUser.setVisible(false);
    	frame.getContentPane().add(ConstUser);
    	
    	//casos extras
    	modActDep.setLocation(25, 43);
    	modActDep.setVisible(false);
    	frame.getContentPane().add(modActDep);
    	
    	modUser.setLocation(25, 43);
    	modUser.setVisible(false);
    	frame.getContentPane().add(modUser);
    	
    	modIntD.setLocation(25, 43);
    	modIntD.setVisible(false);
    	frame.getContentPane().add(modIntD);
    	
    	rankingClases.setLocation(25, 43);
    	rankingClases.setVisible(false);
    	frame.getContentPane().add(rankingClases);
    	
    	ConsultdeDC.setLocation(25, 43);
    	ConsultdeDC.setVisible(false);
    	frame.getContentPane().add(ConsultdeDC);
    	
    	rankingActD.setLocation(25, 43);
    	rankingActD.setVisible(false);
    	frame.getContentPane().add(rankingActD);
    	
    	fondo.setBounds(0, -32, 1266, 684);
    	frame.getContentPane().add(fondo);
    	Imagen();
    	
    	
	}

	private void estructuraJframe() {
		
		frame = new JFrame();
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				fondo.setBounds(0, -32,frame.getWidth(),frame.getHeight());
				Imagen();
			}
		});
	
		
		frame.setBounds(200, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuBar.setForeground(new Color(0, 128, 128));
		menuBar.setBackground(new Color(173, 216, 230));
		menuBar.setMargin(new Insets(2, 3, 6, 0));
		frame.setJMenuBar(menuBar);
		
		JButton btnInicio = new JButton("");
		btnInicio.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/home-solid-24.png")));
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fondo.setBounds(0, -32, frame.getWidth(),frame.getHeight());
				altaUsuario.setVisible(false);
				altaInstitucion.setVisible(false);
		    	AltaCl.setVisible(false);
		    	AltaAc.setVisible(false);
		    	ConstUser.setVisible(false);
		    	consActDep.setVisible(false);
		    	regSClase.setVisible(false);
		    	modActDep.setVisible(false);
		    	modIntD.setVisible(false);
		    	modUser.setVisible(false);
		    	rankingClases.setVisible(false);
		    	ConsultdeDC.setVisible(false);
		    	rankingActD.setVisible(false);
			}
		});
		btnInicio.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnInicio.setBackground(new Color(0, 128, 128));
		menuBar.add(btnInicio);
		
		JMenu mnAltas = new JMenu("Alta");
		mnAltas.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/plus-medical-regular-24.png")));
		mnAltas.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuBar.add(mnAltas);
		
		JMenu mnModificar = new JMenu("Modificacion");
		mnModificar.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/edit-alt-solid-24.png")));
		mnModificar.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuBar.add(mnModificar);
		
		JMenu mnConsulta = new JMenu("Consulta");
		mnConsulta.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/file-find-solid-24.png")));
		mnConsulta.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuBar.add(mnConsulta);
		
		JMenu mnRegistro = new JMenu("Registro");
		mnRegistro.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/receipt-regular-24.png")));
		mnRegistro.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuBar.add(mnRegistro);
		
		JMenu mnRanking = new JMenu("Ranking");
		mnRanking.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/bar-chart-regular-24.png")));
		mnRanking.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuBar.add(mnRanking);
		
		
		JMenuItem itemAltaUsuario = new JMenuItem("Alta Usuarios");
		itemAltaUsuario.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/user-plus-solid-24.png")));
		itemAltaUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
		itemAltaUsuario.addActionListener(new ActionListener() { //Accion del item boton Alta usuario
			public void actionPerformed(ActionEvent e) {
				altaInstitucion.setVisible(false);
		    	AltaCl.setVisible(false);
		    	AltaAc.setVisible(false);
		    	ConstUser.setVisible(false);
		    	consActDep.setVisible(false);
		    	regSClase.setVisible(false);
		    	modActDep.setVisible(false);
		    	modIntD.setVisible(false);
		    	modUser.setVisible(false);
		    	rankingClases.setVisible(false);
		    	ConsultdeDC.setVisible(false);
		    	rankingActD.setVisible(false);
				altaUsuario.setVisible(true);
				altaUsuario.setComboBox();
				
				
			}
		});
		
		mnAltas.add(itemAltaUsuario);
		
		
		JMenuItem mnAltaInstitucion = new JMenuItem("Alta Institucion");
		mnAltaInstitucion.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/institution-solid-24.png")));
		mnAltaInstitucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaUsuario.setVisible(false);
		    	AltaCl.setVisible(false);
		    	AltaAc.setVisible(false);
		    	ConstUser.setVisible(false);
		    	consActDep.setVisible(false);
		    	regSClase.setVisible(false);
		    	modActDep.setVisible(false);
		    	modIntD.setVisible(false);
		    	modUser.setVisible(false);
		    	rankingClases.setVisible(false);
		    	ConsultdeDC.setVisible(false);
		    	rankingActD.setVisible(false);
				altaInstitucion.setVisible(true);
				
				
			}
		});
		mnAltaInstitucion.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnAltas.add(mnAltaInstitucion);
		
		JMenuItem itemAltaActividad = new JMenuItem("Alta Actividad");
		itemAltaActividad.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/run-regular-24.png")));
		itemAltaActividad.setFont(new Font("Segoe UI", Font.BOLD, 14));
		itemAltaActividad.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				altaUsuario.setVisible(false);
				altaInstitucion.setVisible(false);
		    	AltaAc.setVisible(false);
		    	ConstUser.setVisible(false);
		    	consActDep.setVisible(false);
		    	regSClase.setVisible(false);
		    	modActDep.setVisible(false);
		    	modIntD.setVisible(false);
		    	modUser.setVisible(false);
		    	rankingClases.setVisible(false);
		    	ConsultdeDC.setVisible(false);
		    	rankingActD.setVisible(false);
				AltaAc.setVisible(true);
				AltaAc.iniciarlizarComboBoxes();	
			
			}
		
		});
		mnAltas.add(itemAltaActividad);
		
		JMenuItem itemconsActDep = new JMenuItem("Consulta de Actividad Deportiva");
		itemconsActDep.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/caret-right-circle-solid-24.png")));
		itemconsActDep.setFont(new Font("Segoe UI", Font.BOLD, 14));
		itemconsActDep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				altaUsuario.setVisible(false);
				altaInstitucion.setVisible(false);
		    	AltaCl.setVisible(false);
		    	AltaAc.setVisible(false);
		    	ConstUser.setVisible(false);
		    	regSClase.setVisible(false);
		    	modActDep.setVisible(false);
		    	modIntD.setVisible(false);
		    	modUser.setVisible(false);
		    	rankingClases.setVisible(false);
		    	ConsultdeDC.setVisible(false);
		    	rankingActD.setVisible(false);
				consActDep.inicializarComboBoxes();
				consActDep.setVisible(true);
			
			}
		
		});
		mnConsulta.add(itemconsActDep);
		

		JMenuItem itemAltaClase = new JMenuItem("Alta Clase");
		itemAltaClase.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/clipboard-regular-24.png")));
		itemAltaClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaUsuario.setVisible(false);
				altaInstitucion.setVisible(false);
		    	AltaAc.setVisible(false);
		    	ConstUser.setVisible(false);
		    	consActDep.setVisible(false);
		    	regSClase.setVisible(false);
		    	modActDep.setVisible(false);
		    	modIntD.setVisible(false);
		    	modUser.setVisible(false);
		    	rankingClases.setVisible(false);
		    	ConsultdeDC.setVisible(false);
		    	rankingActD.setVisible(false);
				AltaCl.iniciarlizarComboBoxes();
				AltaCl.setVisible(true);
			}
		});
		itemAltaClase.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnAltas.add(itemAltaClase);	
		
		
		JMenuItem itemRegistroClase = new JMenuItem("Registro de Socio a Clase");
		itemRegistroClase.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/street-view-regular-24.png")));
		itemRegistroClase.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				altaUsuario.setVisible(false);
				altaInstitucion.setVisible(false);
		    	AltaCl.setVisible(false);
		    	AltaAc.setVisible(false);
		    	ConstUser.setVisible(false);
		    	consActDep.setVisible(false);;
		    	modActDep.setVisible(false);
		    	modIntD.setVisible(false);
		    	modUser.setVisible(false);
		    	rankingClases.setVisible(false);
		    	ConsultdeDC.setVisible(false);
		    	rankingActD.setVisible(false);
				try {
					regSClase.setComboBoxSocio();
					regSClase.inicializarComboBoxes();
				} catch (NoesxisteSocio e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				regSClase.setVisible(true);
			
			}
		
		});
		itemRegistroClase.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnRegistro.add(itemRegistroClase);
		
		JMenuItem itemconsUsuario= new JMenuItem("Consulta de Usuario");
		itemconsUsuario.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/caret-right-circle-solid-24.png")));
		itemconsUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
		itemconsUsuario.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				altaUsuario.setVisible(false);
				altaInstitucion.setVisible(false);
		    	AltaCl.setVisible(false);
		    	AltaAc.setVisible(false);
		    	consActDep.setVisible(false);
		    	regSClase.setVisible(false);
		    	modActDep.setVisible(false);
		    	modIntD.setVisible(false);
		    	modUser.setVisible(false);
		    	rankingClases.setVisible(false);
		    	ConsultdeDC.setVisible(false);
		    	rankingActD.setVisible(false);
				try {
					ConstUser.setVisible(true);
					ConstUser.iniciarlizarComboBoxes();
				} catch (NoexsiteIntDep e1) {
					e1.printStackTrace();
				}
			
			}
		
		});
		mnConsulta.add(itemconsUsuario);
		
		//Casos no obligatorios
		JMenuItem itemModUser = new JMenuItem("Modificar Usuario");
		itemModUser.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/user-circle-solid-24.png")));
		itemModUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaUsuario.setVisible(false);
				altaInstitucion.setVisible(false);
		    	AltaCl.setVisible(false);
		    	AltaAc.setVisible(false);
		    	ConstUser.setVisible(false);
		    	consActDep.setVisible(false);
		    	regSClase.setVisible(false);
		    	modActDep.setVisible(false);
		    	modIntD.setVisible(false);
		    	rankingClases.setVisible(false);
		    	ConsultdeDC.setVisible(false);
		    	rankingActD.setVisible(false);
				modUser.completarTabla();
				modUser.setVisible(true);
			}
		});
		itemModUser.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnModificar.add(itemModUser);
		
		JMenuItem itemModInsts = new JMenuItem("Modificar Institucion");
		itemModInsts.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/bank-solid-24.png")));
		itemModInsts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaUsuario.setVisible(false);
				altaInstitucion.setVisible(false);
		    	AltaCl.setVisible(false);
		    	AltaAc.setVisible(false);
		    	ConstUser.setVisible(false);
		    	consActDep.setVisible(false);
		    	regSClase.setVisible(false);
		    	modActDep.setVisible(false);
		    	modUser.setVisible(false);
		    	rankingClases.setVisible(false);
		    	ConsultdeDC.setVisible(false);
		    	rankingActD.setVisible(false);
				modIntD.completarTabla();
				modIntD.setVisible(true);
			}
		});
		itemModInsts.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnModificar.add(itemModInsts);
		
		JMenuItem itemModActDep = new JMenuItem("Modificar Actividad Deportiva");
		itemModActDep.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/book-content-solid-24.png")));
		itemModActDep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaUsuario.setVisible(false);
				altaInstitucion.setVisible(false);
		    	AltaCl.setVisible(false);
		    	AltaAc.setVisible(false);
		    	ConstUser.setVisible(false);
		    	consActDep.setVisible(false);
		    	regSClase.setVisible(false);
		    	modIntD.setVisible(false);
		    	modUser.setVisible(false);
		    	rankingClases.setVisible(false);
		    	ConsultdeDC.setVisible(false);
		    	rankingActD.setVisible(false);
				modActDep.settable();
				modActDep.setVisible(true);			
			}
		});
		itemModActDep.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnModificar.add(itemModActDep);
		
		JMenuItem itemRanking = new JMenuItem("Ranking de Clases");
		itemRanking.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/chart-regular-24.png")));
		itemRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaUsuario.setVisible(false);
				altaInstitucion.setVisible(false);
		    	AltaCl.setVisible(false);
		    	AltaAc.setVisible(false);
		    	ConstUser.setVisible(false);
		    	consActDep.setVisible(false);
		    	regSClase.setVisible(false);
		    	modActDep.setVisible(false);
		    	modIntD.setVisible(false);
		    	modUser.setVisible(false);
		    	ConsultdeDC.setVisible(false);
		    	rankingActD.setVisible(false);
				rankingClases.setVisible(true);
				rankingClases.listarAD();
			}
		});
		itemRanking.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnRanking.add(itemRanking);
		
		JMenuItem itemConsultadeDictadodeC = new JMenuItem("Consulta de Dictado de Clase");
		itemConsultadeDictadodeC.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/caret-right-circle-solid-24.png")));
		itemConsultadeDictadodeC.setFont(new Font("Segoe UI", Font.BOLD, 14));
		itemConsultadeDictadodeC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaUsuario.setVisible(false);
				altaInstitucion.setVisible(false);
		    	AltaCl.setVisible(false);
		    	AltaAc.setVisible(false);
		    	ConstUser.setVisible(false);
		    	consActDep.setVisible(false);
		    	regSClase.setVisible(false);
		    	modActDep.setVisible(false);
		    	modIntD.setVisible(false);
		    	modUser.setVisible(false);
		    	rankingClases.setVisible(false);
		    	ConsultdeDC.setVisible(false);
		    	rankingActD.setVisible(false);
				ConsultdeDC.setComboBoxP();
				ConsultdeDC.setVisible(true);	
				
			}
			
		});
		mnConsulta.add(itemConsultadeDictadodeC);
		
		JMenuItem itemRankingA = new JMenuItem("Ranking de Actividades");
		itemRankingA.setIcon(new ImageIcon(Principal.class.getResource("/recursosF/dribbble-logo-24.png")));
		itemRankingA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaUsuario.setVisible(false);
				altaInstitucion.setVisible(false);
		    	AltaCl.setVisible(false);
		    	AltaAc.setVisible(false);
		    	ConstUser.setVisible(false);
		    	consActDep.setVisible(false);
		    	regSClase.setVisible(false);
		    	modActDep.setVisible(false);
		    	modIntD.setVisible(false);
		    	modUser.setVisible(false);
		    	ConsultdeDC.setVisible(false);
				rankingClases.setVisible(false);
				rankingActD.listarAD();
				rankingActD.setVisible(true);
			}
		});
		itemRankingA.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnRanking.add(itemRankingA);
		
		Component horizontalStrut = Box.createHorizontalStrut(15);
		menuBar.add(horizontalStrut);
		
		Component verticalStrut = Box.createVerticalStrut(55);
		verticalStrut.setEnabled(false);
		menuBar.add(verticalStrut);
		
		
	}
    	
	public void Imagen() {
		ImageIcon imagen= new ImageIcon(Principal.class.getResource("/recursosF/among-us.jpg"));
		Icon icono= new ImageIcon(imagen.getImage().getScaledInstance(fondo.getWidth(),fondo.getHeight(),Image.SCALE_DEFAULT));
		fondo.setIcon(icono);
	}
}
    
