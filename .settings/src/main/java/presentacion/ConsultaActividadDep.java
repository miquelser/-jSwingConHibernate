package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import datatypes.DtActDeportiva;
import datatypes.DtClase;
import excepciones.NoExisteClase;
import excepciones.NoexsiteIntDep;
import excepciones.SinActDep;
import interfaces.IControlador;
import logica.Clase;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

public class ConsultaActividadDep extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBoxInstDep;
	private JComboBox<String> comboBoxActDep;
	private JComboBox<String> comboBoxClases;
	/*private JButton btnConsultarInstitucion;
	private JButton btnConsultarActividad;
	private JButton btnConsultarClases;*/
	private JLabel lblInstitucionesDep;
	private JPanel panel_1;
	/*private JPanel panel_2;
	private JPanel panel_3;*/
	private JTextArea txtNombre;
	private JTextArea txtDesc;
	private JTextArea txtDur;
	private JTextArea txtCosto;
	private JTextArea txtFecha;
	private JTextArea txtNombreClase;
	private JTextArea txtFechaClase;
	private JTextArea txtHoraInicio;
	private JTextArea txtURLClase;
	private JTextArea txtFechaRegClase;
	private JTextArea txtAociados;
	private IControlador iC;
	
	

	public ConsultaActividadDep(IControlador iC) {
		this.iC=iC;
		
		setTitle("Consulta Actividad Deportiva");
		setBounds(100, 100, 897, 685);
		getContentPane().setLayout(null);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
	
		lblInstitucionesDep = new JLabel("Instituciones Deportivas");
		lblInstitucionesDep.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstitucionesDep.setForeground(new Color(0, 0, 0));
		lblInstitucionesDep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInstitucionesDep.setBounds(66, 10, 154, 31);
		getContentPane().add(lblInstitucionesDep);
		
		comboBoxInstDep = new JComboBox<String>();
		comboBoxInstDep.setBounds(66, 51, 154, 21);
		getContentPane().add(comboBoxInstDep);
		
		//BOTON CONSULTAR DE LA PRIMER PANTALLA.
		JButton btnConsultarInstitucion = new JButton("Consultar");
		btnConsultarInstitucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTxtAct();
				limpiarTxtClase();
				consultarInstitucionDeportivaActionPerfomed(e);
			}
		});
		btnConsultarInstitucion.setBounds(93, 172, 108, 21);
		getContentPane().add(btnConsultarInstitucion);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 10, 270, 222);
		getContentPane().add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(326, 10, 270, 222);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		
		JLabel lblActividadesDeportivas = new JLabel("Actividades Deportivas");
		lblActividadesDeportivas.setBounds(64, 5, 142, 17);
		lblActividadesDeportivas.setHorizontalAlignment(SwingConstants.CENTER);
		lblActividadesDeportivas.setForeground(Color.BLACK);
		lblActividadesDeportivas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblActividadesDeportivas);
		
		comboBoxActDep = new JComboBox<String>();
		comboBoxActDep.setBounds(64, 42, 142, 21);
		panel_1.add(comboBoxActDep);
		
		JButton btnConsultarActividad = new JButton("Consultar");
		btnConsultarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTxtClase();
				limpiarTxtClase();
				consultarActividadDeportivaActionPerformed(e);
			}
		});
		btnConsultarActividad.setBounds(90, 163, 109, 21);
		panel_1.add(btnConsultarActividad);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 243, 439, 356);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(10, 16, 58, 27);
		panel_2.add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcion.setBounds(10, 53, 85, 27);
		panel_2.add(lblDescripcion);
		
		JLabel lblDuracion = new JLabel("Duracion");
		lblDuracion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDuracion.setBounds(10, 90, 85, 27);
		panel_2.add(lblDuracion);
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCosto.setBounds(10, 127, 58, 27);
		panel_2.add(lblCosto);
		
		JLabel lblFecha = new JLabel("Fecha de Reguistro");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(10, 164, 161, 27);
		panel_2.add(lblFecha);
		
		txtNombre = new JTextArea();
		txtNombre.setEditable(false);
		txtNombre.setBounds(93, 21, 170, 22);
		panel_2.add(txtNombre);
		
		txtDesc = new JTextArea();
		txtDesc.setEditable(false);
		txtDesc.setBounds(103, 56, 326, 24);
		panel_2.add(txtDesc);
		
		txtDur = new JTextArea();
		txtDur.setEditable(false);
		txtDur.setBounds(93, 93, 118, 22);
		panel_2.add(txtDur);
		
		txtCosto = new JTextArea();
		txtCosto.setEditable(false);
		txtCosto.setBounds(93, 130, 64, 22);
		panel_2.add(txtCosto);
		
		txtFecha = new JTextArea();
		txtFecha.setEditable(false);
		txtFecha.setBounds(154, 167, 118, 22);
		panel_2.add(txtFecha);
		
		JLabel lblClases = new JLabel("Clases");
		lblClases.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblClases.setBounds(10, 270, 58, 27);
		panel_2.add(lblClases);
		
		// combo box de las clases
		comboBoxClases = new JComboBox<String>();
		comboBoxClases.setBounds(93, 275, 170, 21);
		panel_2.add(comboBoxClases);
		
		JButton btnConsultarClases = new JButton("Consultar");
		btnConsultarClases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarClasesActionPerformed(e);
			}
		});
		btnConsultarClases.setBounds(340, 323, 89, 22);
		panel_2.add(btnConsultarClases);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(459, 242, 412, 356);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNombre_1 = new JLabel("Nombre");
		lblNombre_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre_1.setBounds(10, 11, 58, 27);
		panel_3.add(lblNombre_1);
		
		JLabel lblFecha_1 = new JLabel("Fecha");
		lblFecha_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha_1.setBounds(10, 49, 58, 27);
		panel_3.add(lblFecha_1);
		
		JLabel lblHoraInicio = new JLabel("Hora de Inicio");
		lblHoraInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHoraInicio.setBounds(10, 87, 97, 27);
		panel_3.add(lblHoraInicio);
		
		JLabel lblURL = new JLabel("URL");
		lblURL.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblURL.setBounds(10, 125, 58, 27);
		panel_3.add(lblURL);
		
		JLabel lblFecha_1_1 = new JLabel("Fecha de Registro");
		lblFecha_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha_1_1.setBounds(10, 163, 123, 27);
		panel_3.add(lblFecha_1_1);
		
		JLabel lblNombre_1_1 = new JLabel("Asociados");
		lblNombre_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre_1_1.setBounds(10, 201, 80, 27);
		panel_3.add(lblNombre_1_1);
		
		txtNombreClase = new JTextArea();
		txtNombreClase.setEditable(false);
		txtNombreClase.setBounds(78, 14, 137, 22);
		panel_3.add(txtNombreClase);
		
		txtFechaClase = new JTextArea();
		txtFechaClase.setEditable(false);
		txtFechaClase.setBounds(78, 52, 137, 22);
		panel_3.add(txtFechaClase);
		
		 txtHoraInicio = new JTextArea();
		 txtHoraInicio.setEditable(false);
		txtHoraInicio.setBounds(117, 90, 137, 22);
		panel_3.add(txtHoraInicio);
		
		txtURLClase = new JTextArea();
		txtURLClase.setEditable(false);
		txtURLClase.setBounds(51, 128, 137, 22);
		panel_3.add(txtURLClase);
		
		txtFechaRegClase = new JTextArea();
		txtFechaRegClase.setEditable(false);
		txtFechaRegClase.setBounds(148, 166, 117, 22);
		panel_3.add(txtFechaRegClase);
		
		txtAociados = new JTextArea();
		txtAociados.setEditable(false);
		txtAociados.setBounds(100, 204, 137, 22);
		panel_3.add(txtAociados);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTxtAct();
				
				setVisible(false);
			}
		});
		btnNewButton.setBounds(275, 322, 106, 23);
		panel_3.add(btnNewButton);
		
		
		//Combo box que se llena una vez se consulta en la primer Pantalla.
		//DefaultComboBoxModel<String>modelActividades = new DefaultComboBoxModel<String>(icon.listarActividadesDepo(String nombreInst));

	}
	//Debo agregar esta funcion en Principal.java, para que cuando inicialice, cargue los comboBox.
	public void inicializarComboBoxes() {
		try {
			DefaultComboBoxModel<String> modelinstitucion = new DefaultComboBoxModel<String>(iC.listarInstituciones());
			comboBoxInstDep.setModel(modelinstitucion);
		} catch (NoexsiteIntDep e) {
			JOptionPane.showMessageDialog(null, "No se encontraron Intituciones. \n" + e.getMessage()+ "", "Consulta Actividad Dep", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}	
	}
	protected void consultarInstitucionDeportivaActionPerfomed(ActionEvent e) {

		try {
			String nombre = comboBoxInstDep.getSelectedItem().toString();		
			DefaultComboBoxModel<String>modelactividades = new DefaultComboBoxModel<String>(iC.listarActividades(nombre));
			comboBoxActDep.setModel(modelactividades);
			this.panel_1.setVisible(true);
		} catch (SinActDep e1) {
			JOptionPane.showMessageDialog(null, "No se encontraron Intituciones. \n" + e1.getMessage()+ "", "Consulta Actividad Dep", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		};	
	}
	protected void consultarActividadDeportivaActionPerformed(ActionEvent e) {
		//comboBoxInstDep.setEnabled(false);
		String nomAct = comboBoxActDep.getSelectedItem().toString();
		String nomInst = comboBoxInstDep.getSelectedItem().toString();
		DtActDeportiva dtAD = iC.elegirActividad(nomAct, nomInst);
		//llenar formulario
		int dur = dtAD.getDuracion();
		String durtxt= String.valueOf(dur);
		float  costo  = dtAD.getCosto();
		String costotxt = String.valueOf(costo);
		String horaR;
		if(dtAD.getFecha().getMinutes()<=9) {
			horaR=String.valueOf(dtAD.getFecha().getHours() + ":0" +dtAD.getFecha().getMinutes());
		}
		else {
			horaR=String.valueOf(dtAD.getFecha().getHours() + ":" +dtAD.getFecha().getMinutes());
		}
		this.txtNombre.setText(dtAD.getNombre());
		this.txtDesc.setText(dtAD.getDescripcion());
		this.txtDur.setText(durtxt+"  min");
		this.txtCosto.setText(costotxt+"$");
		this.txtFecha.setText(String.valueOf(dtAD.getFecha().getDate()+"/"+(dtAD.getFecha().getMonth()+1)+"/"+(dtAD.getFecha().getYear()+1900)+"  "+horaR));
		// fin formulario
		// llenar combo box
		DefaultComboBoxModel<String> modelClases;
		try {
			modelClases = new DefaultComboBoxModel<String>(iC.listarClase(nomInst,nomAct));
			comboBoxClases.setModel(modelClases);
		} catch (NoExisteClase e1) {
			JOptionPane.showMessageDialog(null, "No se encontraron Intituciones. \n" + e1.getMessage()+ "", "Consulta Actividad Dep", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		
		
	}
	protected void consultarClasesActionPerformed(ActionEvent e) {
		//comboBoxClases.setEnabled(false);
		String nomAct = (String) comboBoxActDep.getSelectedItem().toString();
		String nomInst = (String) comboBoxInstDep.getSelectedItem().toString();
		String nomClase = (String) comboBoxClases.getSelectedItem().toString();
		DtClase dtC = iC.elegirClase(nomClase, nomAct, nomInst);
		
		if(dtC.getHoraInicio().getMinutes()<=9) {
			this.txtHoraInicio.setText(String.valueOf(dtC.getHoraInicio().getHours() + ":0" +dtC.getHoraInicio().getMinutes()));
		}
		else {
			this.txtHoraInicio.setText(String.valueOf(dtC.getHoraInicio().getHours() + ":" +dtC.getHoraInicio().getMinutes()));
		}
		String horaR="";
		if(dtC.getFechaReg().getMinutes()<=9) {
			horaR=String.valueOf(dtC.getFechaReg().getHours() + ":0" +dtC.getFechaReg().getMinutes());
		}
		else {
			horaR=String.valueOf(dtC.getFechaReg().getHours() + ":" +dtC.getFechaReg().getMinutes());
		}
		this.txtNombreClase.setText(dtC.getNombre());
		this.txtFechaClase.setText(String.valueOf(dtC.getFecha().getDate() +"/"+(dtC.getFecha().getMonth()+1) + "/" +(dtC.getFecha().getYear()+1900)));	
		this.txtURLClase.setText(String.valueOf(dtC.getUrl()));
		this.txtFechaRegClase.setText(String.valueOf(dtC.getFechaReg().getDate()+"/"+(dtC.getFechaReg().getMonth()+1)+"/"+(dtC.getFechaReg().getYear()+1900)+"  "+horaR));
		this.txtAociados.setText(String.valueOf(dtC.getProfe()));
		
	}
	
	protected void limpiarTxtAct() {
		this.txtNombre.setText("");
		this.txtDesc.setText("");
		this.txtDur.setText("");
		this.txtCosto.setText("");
		this.txtFecha.setText("");
	}
	
	protected void limpiarTxtClase(){
		this.txtNombreClase.setText("");
		this.txtHoraInicio.setText("");
		this.txtFechaClase.setText("");	
		this.txtURLClase.setText("");
		this.txtFechaRegClase.setText("");
		this.txtAociados.setText("");
	}
}
