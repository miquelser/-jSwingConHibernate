package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import datatypes.DtActDeportiva;
import datatypes.DtInstitucionD;
import datatypes.DtUsuario;
import excepciones.NoexsiteIntDep;
import excepciones.SinActDep;
import interfaces.Fabrica;
import interfaces.IControlador;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificarActDep extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txFnombre;
	private JTextField txFDuracion;
	private JTextField txFCosto;
	private JTextField txFfecha;
	private JTextField txFDescripcion;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JScrollPane scrollPane;
	private static JTable tableAd;
	
	private static DefaultTableModel tabAD;
	private JTextField txFInt;
	
	private IControlador iC;

	
	public ModificarActDep(IControlador iC) {
		this.iC=iC;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        
		setBounds(100, 100, 841, 497);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Modificar Actividad Deportiva");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 364, 42);
		getContentPane().add(lblNewLabel);
		
		txFnombre = new JTextField();
		txFnombre.setEditable(false);
		txFnombre.setBounds(83, 82, 116, 20);
		getContentPane().add(txFnombre);
		txFnombre.setColumns(10);
		
		txFDuracion = new JTextField();
		txFDuracion.setBounds(83, 130, 116, 20);
		getContentPane().add(txFDuracion);
		txFDuracion.setColumns(10);
		
		txFCosto = new JTextField();
		txFCosto.setBounds(359, 130, 79, 20);
		getContentPane().add(txFCosto);
		txFCosto.setColumns(10);
		
		txFfecha = new JTextField();
		txFfecha.setEditable(false);
		txFfecha.setBounds(359, 82, 199, 20);
		getContentPane().add(txFfecha);
		txFfecha.setColumns(10);
		
		txFDescripcion = new JTextField();
		txFDescripcion.setBounds(585, 106, 217, 68);
		getContentPane().add(txFDescripcion);
		txFDescripcion.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(7, 85, 66, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Descripcion:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(585, 84, 99, 14);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Duracion:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(7, 133, 66, 14);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Costo:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(303, 132, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Fecha de Reguistro:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(228, 84, 116, 14);
		getContentPane().add(lblNewLabel_5);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 184, 793, 207);
		getContentPane().add(scrollPane);
		
		tableAd = new JTable();
		tableAd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JTable tabla = (JTable) e.getSource();
				Point point = e.getPoint();
				int row = tabla.rowAtPoint(point);
				if(e.getClickCount() == 1) {
					txFInt.setText(tableAd.getValueAt(tableAd.getSelectedRow(), 0).toString());
					txFnombre.setText(tableAd.getValueAt(tableAd.getSelectedRow(), 1).toString());
					txFDescripcion.setText(tableAd.getValueAt(tableAd.getSelectedRow(), 2).toString());
					txFDuracion.setText(tableAd.getValueAt(tableAd.getSelectedRow(), 3).toString());
					txFCosto.setText(tableAd.getValueAt(tableAd.getSelectedRow(), 4).toString());
					txFfecha.setText(tableAd.getValueAt(tableAd.getSelectedRow(), 5).toString());
					
				}
			}
		});
		scrollPane.setViewportView(tableAd);
		
		tabAD = new DefaultTableModel();
		tableAd.setModel(tabAD);
		
		tabAD.addColumn("Nombre Intitucion");
		tabAD.addColumn("Nombre Actividad");
		tabAD.addColumn("Descripcion");
		tabAD.addColumn("Duracion en min");
		tabAD.addColumn("Costo $");	
		tabAD.addColumn("Fecha de Reguistro");
		
		tableAd.getColumnModel().getColumn(2).setPreferredWidth(185);
		tableAd.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableAd.getColumnModel().getColumn(4).setPreferredWidth(30);
		tableAd.getColumnModel().getColumn(5).setPreferredWidth(185);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				setVisible(false);
			}
		});
		btnNewButton.setBounds(533, 421, 130, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Aplicar Cambios");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				
				try {
					int duracion =Integer.valueOf(txFDuracion.getText());
					float costo = Float.valueOf(txFCosto.getText());
					iC.modificarActDep(txFInt.getText(),txFnombre.getText(), txFDescripcion.getText(),duracion,costo);
					JOptionPane.showMessageDialog(null, "La Actividad Deportiva "+txFnombre.getText()+" se actualizo correctamente");
					settable();
					limpiarCampos();
					
		        }
		        catch (NumberFormatException ex){
		        	JOptionPane.showMessageDialog(null, "Los datos de Costo o Duracion se an ingresado\nincorrectamente");
		        	ex.printStackTrace();
		        }
				
				
				
				//settable();
			}
		});
		btnNewButton_1.setBounds(685, 421, 130, 23);
		getContentPane().add(btnNewButton_1);
		
		txFInt = new JTextField();
		txFInt.setEditable(false);
		txFInt.setBounds(685, 27, 117, 19);
		getContentPane().add(txFInt);
		txFInt.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Intitucion de la actividad:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(533, 29, 151, 13);
		getContentPane().add(lblNewLabel_6);

	}
	
	public void settable() {
		Resettable(tableAd,tabAD);
		try {
			String[] intd = iC.listarInstitucionesM();
			for (String id : intd) {
				List<DtActDeportiva> ad;
				try {
					ad = iC.obtenerActividadesDdeUnaInt(id);
					for (DtActDeportiva a : ad) {
						Object[] columna = new Object[6];
						
						String horaR;
						if(a.getFecha().getMinutes()<=9) {
							horaR=String.valueOf(a.getFecha().getHours() + ":0" +a.getFecha().getMinutes());
						}
						else {
							horaR=String.valueOf(a.getFecha().getHours() + ":" +a.getFecha().getMinutes());
						}
						
						columna[0]= id;
						columna[1]= a.getNombre();
						columna[2]= a.getDescripcion();
						columna[3]= a.getDuracion();
						columna[4]= a.getCosto();
						columna[5]= a.getFecha().getDate()+"/"+(a.getFecha().getMonth()+1)+"/"+(a.getFecha().getYear()+1900)+"  "+horaR;
					
						tabAD.addRow(columna);
					}
				} catch (NoexsiteIntDep e1) {
			
					e1.printStackTrace();
				}
			}
		} catch (NoexsiteIntDep e) {
		
			e.printStackTrace();
		}	
		
	}
				


	
	private static void Resettable(JTable tabla, DefaultTableModel modelo){
		for (int i = 0; i < tabla.getRowCount(); i++) {
			modelo.removeRow(i);
			i-=1;
		}
	}
	
	public void limpiarCampos() {
		txFInt.setText("");
		txFnombre.setText("");
		txFDescripcion.setText("");
		txFDuracion.setText("");
		txFCosto.setText("");
		txFfecha.setText("");		
	}
}