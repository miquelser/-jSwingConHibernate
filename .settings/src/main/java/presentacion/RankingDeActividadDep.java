package presentacion;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import datatypes.DtActDeportiva;
import datatypes.DtClase;
import excepciones.NoexsiteIntDep;
import excepciones.SinActDep;
import interfaces.IControlador;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RankingDeActividadDep extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IControlador icon;
	private JTable tablaDeClases;
	
	private static DefaultTableModel tablaAD;

	
	public RankingDeActividadDep(IControlador icon) {
		this.icon = icon;
		setBounds(100, 100, 517, 379);
		setTitle("Ranking de Actividades Deportivas");
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 482, 329);
		getContentPane().add(scrollPane);
		
		tablaDeClases = new JTable();
		tablaDeClases.setEnabled(false);
		scrollPane.setViewportView(tablaDeClases);
		tablaAD = new DefaultTableModel();	
		tablaDeClases.setModel(tablaAD);
		
		tablaAD.addColumn("Posicion");
		tablaAD.addColumn("Nombre");	
		tablaAD.addColumn("descripcion");	
		tablaAD.addColumn("Costo");	
		
		tablaDeClases.getColumnModel().getColumn(0).setPreferredWidth(30);
		tablaDeClases.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaDeClases.getColumnModel().getColumn(2).setPreferredWidth(250);
		tablaDeClases.getColumnModel().getColumn(3).setPreferredWidth(50);
		
		
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		
		
		
		
		

	}

	public void listarAD()  {

			resetTable(tablaDeClases,tablaAD);
			List<DtActDeportiva>imp = icon.rankingDeActividadesD();
			int cont=1;
			for (DtActDeportiva a : imp) {

				Object[] columna = new Object[4];
				columna[0]= cont;
				columna[1]= a.getNombre();
				columna[2]= a.getDescripcion();
				columna[3]= a.getCosto()+"$";
				cont++;
				tablaAD.addRow(columna);
			}
	}	
		
	
	private void resetTable(JTable tabla, DefaultTableModel modelo){
		for (int i = 0; i < tabla.getRowCount(); i++) {
			modelo.removeRow(i);
			i-=1;
		}
	}
}
