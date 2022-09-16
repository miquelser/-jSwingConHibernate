package presentacion;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import interfaces.Fabrica;
import interfaces.IControlador;
import logica.Clase;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import datatypes.DtClase;
import excepciones.ExisteRegistroAClase;
import excepciones.NoExisteClase;
import excepciones.NoesxisteSocio;
import excepciones.NoexsiteIntDep;
import excepciones.SinActDep;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class RegistroSocioAClase extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	
	private IControlador iC;
	private JTable table;
	
	private JComboBox<String> cbInstitucion;
	private JComboBox<String> cbActividad;
	private JComboBox<String> cbSocio;
	private JComboBox<String> cbClase;
	private static DefaultTableModel resTabla;

	public RegistroSocioAClase(IControlador iC) {
		this.iC = iC;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Reguistro de Clase");
		
		setBounds(100, 100, 903, 616);
		getContentPane().setLayout(null);
		
		cbInstitucion = new JComboBox();
		cbInstitucion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					setComboBoxAd();
					resetTable(table, resTabla);
				} catch (SinActDep e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		cbInstitucion.setBounds(70, 49, 197, 22);
		getContentPane().add(cbInstitucion);
		
		cbActividad = new JComboBox();
		cbActividad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					resetTable(table, resTabla);
					setComboBoxClase();
					listarClase();
					} catch (SinActDep e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		cbActividad.setBounds(339, 49, 197, 22);
		getContentPane().add(cbActividad);
		
		cbSocio = new JComboBox();
		cbSocio.setBounds(339, 121, 197, 22);
		getContentPane().add(cbSocio);
		
		cbClase = new JComboBox();
		cbClase.setBounds(603, 49, 197, 22);
		getContentPane().add(cbClase);
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		cancelar.setBounds(551, 484, 132, 23);
		getContentPane().add(cancelar);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String c1 = cbClase.getSelectedItem().toString();
					String a1 = cbActividad.getSelectedItem().toString();
					String i1 = cbInstitucion.getSelectedItem().toString();
					String s1 = cbSocio.getSelectedItem().toString();
					
					iC.registroDictadoClases(c1 , a1 ,i1, s1);
					JOptionPane.showMessageDialog(null, "Reguistro con Exito");
				} catch (ExisteRegistroAClase e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnRegistrar.setBounds(703, 484, 132, 23);
		getContentPane().add(btnRegistrar);
		
		JScrollPane spClases = new JScrollPane();
		spClases.setBounds(44, 203, 791, 258);
		getContentPane().add(spClases);
		
		table = new JTable();
		table.setEnabled(false);
		spClases.setViewportView(table);
		resTabla = new DefaultTableModel();
		table.setModel(resTabla);
		
		JLabel lblNewLabel = new JLabel("Seleccione Intitucion:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(70, 24, 197, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione Actividad Deportiva:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(339, 26, 231, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Seleccione Clase:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(603, 26, 197, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Seleccione Usuario a reguistrar:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(339, 99, 231, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Lista de Clases");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(43, 178, 157, 14);
		getContentPane().add(lblNewLabel_4);
		
		resTabla.addColumn("Nombre");
		resTabla.addColumn("Fecha");
		resTabla.addColumn("Hora de inicio");
		resTabla.addColumn("Url");
		resTabla.addColumn("Fecha de Reguistro");
			

	}
	
	public void inicializarComboBoxes() {
		try {
			DefaultComboBoxModel<String> modelinstitucion = new DefaultComboBoxModel<String>(iC.listarInstituciones());
			cbInstitucion.setModel(modelinstitucion);
		} catch (NoexsiteIntDep e) {
			JOptionPane.showMessageDialog(null, "No puede registrar. \n" + e.getMessage()+ "", "Registro a Clase", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}	
	}
	
	public void setComboBoxAd() throws SinActDep {
		try {
			DefaultComboBoxModel<String>modelactividades = new DefaultComboBoxModel<String>(iC.listarActividades(cbInstitucion.getSelectedItem().toString()));
			cbActividad.setModel(modelactividades);
		} catch (SinActDep e) {
			JOptionPane.showMessageDialog(null, "No puede registrar. \n" + e.getMessage()+ "", "Registro a Clase", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void setComboBoxClase() throws SinActDep {
		try {
			DefaultComboBoxModel<String> modelClases = new DefaultComboBoxModel<String>(iC.listarClase(cbInstitucion.getSelectedItem().toString(),cbActividad.getSelectedItem().toString()));
			cbClase.setModel(modelClases);
		} catch (NoExisteClase e1) {
			JOptionPane.showMessageDialog(null, "No puede registrar. \n" + e1.getMessage()+ "", "Registro a Clase", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}
	
	public void setComboBoxSocio() throws NoesxisteSocio {
		try {
			DefaultComboBoxModel<String> modelSocios = new DefaultComboBoxModel<String>(iC.listarSocios());
			cbSocio.setModel(modelSocios);
		} catch (NoesxisteSocio e1) {
			JOptionPane.showMessageDialog(null, "No puede registrar. \n" + e1.getMessage()+ "", "Registro a Clase", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}
	
	public void listarClase() throws SinActDep {
		List<DtClase> listClase = iC.listarDatoClase(cbActividad.getSelectedItem().toString(),cbInstitucion.getSelectedItem().toString());
		for (DtClase c : listClase) {
			
			Object[] columna = new Object[5];

			if(c.getHoraInicio().getMinutes()<=9) {
				columna[2] = String.valueOf(c.getHoraInicio().getHours() + ":0" +c.getHoraInicio().getMinutes());
			}
			else {
				columna[2] = String.valueOf(c.getHoraInicio().getHours() + ":" +c.getHoraInicio().getMinutes());
			}
			String horaR;
			if(c.getFechaReg().getMinutes()<=9) {
				horaR=String.valueOf(c.getFechaReg().getHours() + ":0" +c.getFechaReg().getMinutes());
			}
			else {
				horaR=String.valueOf(c.getFechaReg().getHours() + ":" +c.getFechaReg().getMinutes());
			}
			
			columna[1] = String.valueOf(c.getFecha().getDate() +"/"+(c.getFecha().getMonth()+1) + "/" +(c.getFecha().getYear()+1900));	
			columna[0]= c.getNombre();
			columna[3]= c.getUrl();
			columna[4]= c.getFechaReg().getDate()+"/"+(c.getFechaReg().getMonth()+1)+"/"+(c.getFechaReg().getYear()+1900)+"  "+horaR;
			resTabla.addRow(columna);
		}
		
	}
	
	private void resetTable(JTable tabla, DefaultTableModel modelo){
		for (int i = 0; i < tabla.getRowCount(); i++) {
			modelo.removeRow(i);
			i-=1;
		}
	}
}
