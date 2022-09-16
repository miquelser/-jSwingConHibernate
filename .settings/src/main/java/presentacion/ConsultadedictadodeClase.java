package presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import datatypes.DtActDeportiva;
import datatypes.DtClase;
import datatypes.DtInstitucionD;
import datatypes.DtUsuario;
import excepciones.ActividadRepetidaExcepcion;
import excepciones.ClaseRepetidaExcepcion;
import excepciones.InstitucionRepetidaExcepcion;
import excepciones.NoExisteClase;
import excepciones.NoexsiteIntDep;
import excepciones.SinActDep;
import interfaces.Fabrica;
import interfaces.IControlador;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;
import com.toedter.components.JSpinField;

public class ConsultadedictadodeClase extends JInternalFrame {

	private JPanel contentPane;
	private JTextArea tADtClase;
	private JComboBox cBselectIntDep;
	private JComboBox cBselectactividadD;
	private JComboBox cBselectClase;
	private JScrollPane sPAD;
	private JTable tableAD;
	private JLabel titseleAD;
	private JLabel titselecClase;
	private JLabel titAD;
	private JScrollPane sPC;
	private JLabel titclases;
	private JTable tableClases;
	
	private static DefaultTableModel tablaAD;
	private static DefaultTableModel tablaC;
	
	private IControlador iC;
	private JLabel fondo;
	

	public ConsultadedictadodeClase(IControlador iC) {
		this.iC=iC;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consulta de Dictado de Clase");
        
		setBounds(100, 100, 807, 498);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tADtClase = new JTextArea();
		tADtClase.setBackground(SystemColor.info);
		tADtClase.setBounds(536, 161, 224, 232);
		contentPane.add(tADtClase);
		
		tADtClase.setVisible(false);
		
		cBselectIntDep = new JComboBox();
		cBselectIntDep.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent i) {
					
				if(cBselectIntDep.getSelectedIndex()>0) {
					try {//chekear
						Resettable(tableAD,tablaAD);
						Resettable(tableClases,tablaC);
						tADtClase.setVisible(false);//si cambia la int una ves que ya habia dictado una clase las oculto y reseteo para seleccionar nuevamente
						cBselectClase.setVisible(false);
						titselecClase.setVisible(false);
						sPC.setVisible(false);
						tableClases.setVisible(false);
						titclases.setVisible(false);
						
						setComboBoxAD();
						listarAD();
					} catch (SinActDep e1) {
						// TODO Bloque catch generado automáticamente
						e1.printStackTrace();
					}
		        	titAD.setVisible(true);		
					titseleAD.setVisible(true);
					tableAD.setVisible(true);
					sPAD.setVisible(true);
					cBselectactividadD.setVisible(true);
					}
					else {
						tADtClase.setVisible(false);//problemas con visiones pensar
						cBselectClase.setVisible(false);
						titselecClase.setVisible(false);
						sPC.setVisible(false);
						tableClases.setVisible(false);
						titclases.setVisible(false);
						titAD.setVisible(false);		
						titseleAD.setVisible(false);
						tableAD.setVisible(false);
						sPAD.setVisible(false);
						cBselectactividadD.setVisible(false);
					}
					
				
		}
		});
		cBselectIntDep.setBounds(245, 16, 159, 22);
		contentPane.add(cBselectIntDep);

		
		cBselectactividadD = new JComboBox();
		cBselectactividadD.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent a) {
				

				if(cBselectactividadD.getSelectedIndex()>-1) {			
					if(cBselectactividadD.getSelectedIndex()>0) {
						Resettable(tableClases,tablaC);
					
						tADtClase.setVisible(false);

						
						
						try {
							cBselectClase.setVisible(true);
							titselecClase.setVisible(true);
							sPC.setVisible(true);
							tableClases.setVisible(true);
							titclases.setVisible(true);
							setComboBoxC();
							listarC();
							
						} catch (SinActDep e) {
							// TODO Bloque catch generado automáticamente
							e.printStackTrace();
						}
						
							
					}
					else {
						cBselectClase.setVisible(false);
						titselecClase.setVisible(false);
						sPC.setVisible(false);
						tableClases.setVisible(false);
						titclases.setVisible(false);	
					}
					
				}


			
		}
		});

		cBselectactividadD.setBounds(6, 372, 132, 22);
		contentPane.add(cBselectactividadD);
		
		cBselectactividadD.setVisible(false);
		
		cBselectClase = new JComboBox();
		cBselectClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				if(cBselectClase.getSelectedIndex()>-1) {			
					if(cBselectClase.getSelectedIndex()>0) {
						
						tADtClase.setVisible(true);
						DatosClase();
					}
					else {
						tADtClase.setVisible(false);
					}
					
				}
				
				
			}
		});
		cBselectClase.setBounds(562, 128, 166, 22);
		contentPane.add(cBselectClase);
		
		cBselectClase.setVisible(false);
		
		JLabel titselectID = new JLabel("Seleccione Intitucion Deportiva:");
		titselectID.setForeground(SystemColor.inactiveCaptionBorder);
		titselectID.setFont(new Font("Tahoma", Font.BOLD, 14));
		titselectID.setBounds(10, 11, 225, 29);
		contentPane.add(titselectID);
		
		sPAD = new JScrollPane();
		sPAD.setBounds(7, 87, 132, 232);
		contentPane.add(sPAD);
		
		sPAD.setVisible(false);
		
		tableAD = new JTable();
		sPAD.setViewportView(tableAD);
		
		tablaAD = new DefaultTableModel();	
		tableAD.setModel(tablaAD);
		
		tablaAD.addColumn("Nombre");
		
		tableAD.setVisible(false);
		
		titseleAD = new JLabel("<html>Selecciona una<p>Actividad Deportiva:<html>");
		titseleAD.setForeground(new Color(255, 255, 255));
		titseleAD.setFont(new Font("Tahoma", Font.BOLD, 14));
		titseleAD.setBounds(3, 314, 139, 62);
		contentPane.add(titseleAD);
		
		titseleAD.setVisible(false);
		
		titselecClase = new JLabel("<html>Selecciona<p>una Clase:<html>");
		titselecClase.setForeground(SystemColor.window);
		titselecClase.setFont(new Font("Tahoma", Font.BOLD, 14));
		titselecClase.setBounds(476, 110, 89, 40);
		contentPane.add(titselecClase);
		
		titselecClase.setVisible(false);
		
		titAD = new JLabel("Actividades Deportivas");
		titAD.setForeground(SystemColor.inactiveCaptionBorder);
		titAD.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		titAD.setBounds(6, 65, 149, 30);
		contentPane.add(titAD);
		
		titAD.setVisible(false);
		
		sPC = new JScrollPane();
		sPC.setBounds(258, 128, 132, 265);
		contentPane.add(sPC);
		
		
		
		sPC.setVisible(false);
		
		tableClases = new JTable();
		sPC.setViewportView(tableClases);
		
		tablaC = new DefaultTableModel();	
		tableClases.setModel(tablaC);
		
		tablaC.addColumn("Nombre");
				
		tableClases.setVisible(false);
		
		titclases = new JLabel("Clases:");
		titclases.setForeground(SystemColor.window);
		titclases.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		titclases.setBounds(258, 115, 75, 14);
		contentPane.add(titclases);
		
		titclases.setVisible(false);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(637, 438, 123, 21);
		contentPane.add(btnNewButton);
		
		
		
		fondo = new JLabel("New label");
		fondo.setBounds(0, 0, 793, 468);
		contentPane.add(fondo);
		Imagen();
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				fondo.setBounds(0,0,getWidth(),getHeight());
				Imagen();
			}
		});
		
		
		
	
	}

	public void setComboBoxP() {

		try {
			DefaultComboBoxModel<String> modelinstitucion = new DefaultComboBoxModel<String>(iC.listarInstituciones());
			cBselectIntDep.setModel(modelinstitucion);
		} catch (NoexsiteIntDep e) {
			// TODO Bloque catch generado automáticamente
			JOptionPane.showMessageDialog(null, "No puede crear Clase. \n" + e.getMessage()+ "", "Dictado de Clase", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
        

    }
	
	public void setComboBoxAD() {
        
        try {
			DefaultComboBoxModel<String>modelactividades = new DefaultComboBoxModel<String>(iC.listarActividades(cBselectIntDep.getSelectedItem().toString()));
			cBselectactividadD.setModel(modelactividades);
		} catch (SinActDep e) {
			JOptionPane.showMessageDialog(null, "No puede crear Dictar. \n" + e.getMessage()+ "", "Dictado de Clase", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

    }
	
	public void setComboBoxC() {
		try {
			DefaultComboBoxModel<String>modelClases = new DefaultComboBoxModel<String>(iC.listarClase(cBselectIntDep.getSelectedItem().toString(),cBselectactividadD.getSelectedItem().toString()));
			cBselectClase.setModel(modelClases);
		} catch (NoExisteClase e1) {
			JOptionPane.showMessageDialog(null, "No se encontraron Intituciones. \n" + e1.getMessage()+ "", "Consulta Actividad Dep", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
        
    }
	
	
	public void listarAD() throws SinActDep {
		
		String[] imp;
		try {
			imp = iC.listarActividades(cBselectIntDep.getSelectedItem().toString());
			for (String a : imp) {
				Object[] columna = new Object[1];
				columna[0]= a;
				tablaAD.addRow(columna);
			}
			tablaAD.removeRow(0);
		} catch (SinActDep e) {
			e.printStackTrace();
		}
		
					
	}
	
	public void listarC() throws SinActDep{
		
		String[] imp;
		try {
			imp = iC.listarClase(cBselectIntDep.getSelectedItem().toString(), cBselectactividadD.getSelectedItem().toString());
			for (String a : imp) {
				Object[] columna = new Object[1];
				columna[0]= a;
				tablaC.addRow(columna);
			}
			tablaC.removeRow(0);
		} catch (NoExisteClase e) {
			
			e.printStackTrace();
		} 
		
		
	
	}
	public void DatosClase() {
		Fabrica f = Fabrica.getInstancia();
		IControlador iConS= f.getIControlador();
	 	
		DtClase c = iC.elegirClase(cBselectClase.getSelectedItem().toString(), cBselectactividadD.getSelectedItem().toString(), cBselectIntDep.getSelectedItem().toString());
		String horaI;
		if(c.getHoraInicio().getMinutes()<=9) {
			horaI=c.getHoraInicio().getHours() + ":0" +c.getHoraInicio().getMinutes();
		}
		else {
			horaI=c.getHoraInicio().getHours() + ":" +c.getHoraInicio().getMinutes();
		}
		String horaR;
		if(c.getFechaReg().getMinutes()<=9) {
			horaR=String.valueOf(c.getFechaReg().getHours() + ":0" +c.getFechaReg().getMinutes());
		}
		else {
			horaR=String.valueOf(c.getFechaReg().getHours() + ":" +c.getFechaReg().getMinutes());
		}
		
		String cadena="Clases\n";
		cadena=cadena +"Nombre: "+c.getNombre()+"\n";
		cadena=cadena +"Fecha de clase: "+c.getFecha().getDate() +"/"+(c.getFecha().getMonth()+1) + "/" +(c.getFecha().getYear()+1900)+"\n";
		cadena=cadena +"Hora de inicio: "+horaI+"\n";
		cadena=cadena +"Url: "+c.getUrl()+"\n";
		cadena=cadena +"Fecha de Reguistro: "+c.getFechaReg().getDate()+"/"+(c.getFechaReg().getMonth()+1)+"/"+(c.getFechaReg().getYear()+1900)+"  "+horaR+"\n\n";

		tADtClase.setText(cadena);
	 	
		
	}
	
	private void Resettable(JTable tabla, DefaultTableModel modelo){
		for (int i = 0; i < tabla.getRowCount(); i++) {
			modelo.removeRow(i);
			i-=1;
		}
	}
	
	public void Imagen() {
		ImageIcon imagen= new ImageIcon(Principal.class.getResource("/recursosF/retrowave.jpg"));
		Icon icono= new ImageIcon(imagen.getImage().getScaledInstance(fondo.getWidth(),fondo.getHeight(),Image.SCALE_DEFAULT));
		fondo.setIcon(icono);
	}
}