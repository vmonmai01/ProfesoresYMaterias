package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import controller.ControladorAsignatura;
import controller.ControladorAsignaturaPorDocente;
import controller.ControladorDocente;

import model.Asignatura;
import model.Docente;

import model.Asignaturaspordocente;

import javax.swing.JTextField;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JTextField jtfFiltrar;

	private JComboBox<Docente> jcbCargarMaterias;

	// Elemento JList a utilizar en el ejemplo necesitamos 2, para noseleccionados y
	// seleccionados

	private JList jlNoSeleccionadas;
	private JList jlSeleccionadas;
	// Modelo del elemento JList, necesario para que podamos c�modamente agregar y
	// eliminar elementos
	private DefaultListModel<Asignatura> listModelNoSeleccionados = null;
	private DefaultListModel<Asignatura> listModelSeleccionados = null;

	// Lista de todas las Asignaturas de la BBDD, para incluir en el elemento JList
	private List<Asignatura> Asignaturas = ControladorAsignatura.findAll();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[] { 1.0 };
		gridBagLayout.columnWeights = new double[] { 1.0 };
		//		gridBagLayout.columnWidths = new int[]{0};
		//		gridBagLayout.rowHeights = new int[]{0};
		//		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		//		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblTitulo = new JLabel("Docentes y asignaturas");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 2;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		panel.add(lblTitulo, gbc_lblTitulo);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 0;
		gbc_panel_3.gridheight = 0;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 6;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_3.rowHeights = new int[] { 0, 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				guardar();

			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.gridx = 8;
		gbc_btnGuardar.gridy = 0;
		panel_3.add(btnGuardar, gbc_btnGuardar);

		jtfFiltrar = new JTextField();
		GridBagConstraints gbc_jtfFiltrar = new GridBagConstraints();
		gbc_jtfFiltrar.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFiltrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFiltrar.gridx = 0;
		gbc_jtfFiltrar.gridy = 2;
		panel.add(jtfFiltrar, gbc_jtfFiltrar);
		jtfFiltrar.setColumns(10);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				getDocentes();
			}
		});
		btnFiltrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.insets = new Insets(0, 0, 5, 0);
		gbc_btnFiltrar.gridx = 1;
		gbc_btnFiltrar.gridy = 2;
		panel.add(btnFiltrar, gbc_btnFiltrar);

		jcbCargarMaterias = new JComboBox();
		GridBagConstraints gbc_jcbCargarMaterias = new GridBagConstraints();
		gbc_jcbCargarMaterias.insets = new Insets(0, 0, 5, 5);
		gbc_jcbCargarMaterias.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbCargarMaterias.gridx = 0;
		gbc_jcbCargarMaterias.gridy = 4;
		panel.add(jcbCargarMaterias, gbc_jcbCargarMaterias);

		JButton btnCargarMaterias = new JButton("Cargar Materias");
		btnCargarMaterias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				deseleccionarTodos();
				/*
				 * Cargar materias
				 */

			}
		});
		btnCargarMaterias.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnCargarMaterias = new GridBagConstraints();
		gbc_btnCargarMaterias.insets = new Insets(0, 0, 5, 0);
		gbc_btnCargarMaterias.gridx = 1;
		gbc_btnCargarMaterias.gridy = 4;
		panel.add(btnCargarMaterias, gbc_btnCargarMaterias);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 5;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblList1 = new JLabel("Asignaturas no seleccionadas");
		lblList1.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblList1 = new GridBagConstraints();
		gbc_lblList1.insets = new Insets(0, 0, 5, 5);
		gbc_lblList1.gridx = 0;
		gbc_lblList1.gridy = 0;
		panel_1.add(lblList1, gbc_lblList1);

		JLabel lblList2 = new JLabel("Asignatura/s seleccionada/s");
		lblList2.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblList2 = new GridBagConstraints();
		gbc_lblList2.insets = new Insets(0, 0, 5, 0);
		gbc_lblList2.anchor = GridBagConstraints.EAST;
		gbc_lblList2.gridx = 2;
		gbc_lblList2.gridy = 0;
		panel_1.add(lblList2, gbc_lblList2);

		jlNoSeleccionadas = new JList(this.getDefaultListModelNoSeleccionados());
		GridBagConstraints gbc_jlNoSeleccionadas = new GridBagConstraints();
		gbc_jlNoSeleccionadas.insets = new Insets(0, 0, 0, 5);
		gbc_jlNoSeleccionadas.fill = GridBagConstraints.BOTH;
		gbc_jlNoSeleccionadas.gridx = 0;
		gbc_jlNoSeleccionadas.gridy = 1;
		panel_1.add(jlNoSeleccionadas, gbc_jlNoSeleccionadas);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.VERTICAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JButton btnAllSelect = new JButton(">>");
		btnAllSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				seleccionarTodos();
			}
		});
		btnAllSelect.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnAllSelect = new GridBagConstraints();
		gbc_btnAllSelect.insets = new Insets(0, 0, 5, 0);
		gbc_btnAllSelect.gridx = 0;
		gbc_btnAllSelect.gridy = 0;
		panel_2.add(btnAllSelect, gbc_btnAllSelect);

		JButton btnOneSelect = new JButton(">");
		btnOneSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarAsignaturas();
			}
		});
		btnOneSelect.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnOneSelect = new GridBagConstraints();
		gbc_btnOneSelect.insets = new Insets(0, 0, 5, 0);
		gbc_btnOneSelect.gridx = 0;
		gbc_btnOneSelect.gridy = 1;
		panel_2.add(btnOneSelect, gbc_btnOneSelect);

		JButton btnOneNoSelect = new JButton("<");
		btnOneNoSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deseleccionarAsignaturas();
			}
		});
		btnOneNoSelect.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnOneNoSelect = new GridBagConstraints();
		gbc_btnOneNoSelect.insets = new Insets(0, 0, 5, 0);
		gbc_btnOneNoSelect.gridx = 0;
		gbc_btnOneNoSelect.gridy = 2;
		panel_2.add(btnOneNoSelect, gbc_btnOneNoSelect);

		JButton btnAllNoSelect = new JButton("<<");
		btnAllNoSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deseleccionarTodos();
			}
		});
		btnAllNoSelect.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnAllNoSelect = new GridBagConstraints();
		gbc_btnAllNoSelect.gridx = 0;
		gbc_btnAllNoSelect.gridy = 3;
		panel_2.add(btnAllNoSelect, gbc_btnAllNoSelect);

		jlSeleccionadas = new JList(this.getDefaultListModelSeleccionados());
		GridBagConstraints gbc_jlSeleccionadas = new GridBagConstraints();
		gbc_jlSeleccionadas.fill = GridBagConstraints.BOTH;
		gbc_jlSeleccionadas.gridx = 2;
		gbc_jlSeleccionadas.gridy = 1;
		panel_1.add(jlSeleccionadas, gbc_jlSeleccionadas);

	}

	/**
	 * Método que construye el modelo de JList, a utilizar para agregar y eliminar
	 * Asignaturas
	 */
	private DefaultListModel getDefaultListModelNoSeleccionados() {
		this.listModelNoSeleccionados = new DefaultListModel<Asignatura>();
		return this.listModelNoSeleccionados;
	}

	private DefaultListModel getDefaultListModelSeleccionados() {
		this.listModelSeleccionados = new DefaultListModel<Asignatura>();
		return this.listModelSeleccionados;
	}

	/**
	 * Método para obtener los Docentes
	 */
	private void getDocentes() {
		jcbCargarMaterias.removeAllItems();
		List<Docente> l = ControladorDocente.findByLikeName(jtfFiltrar.getText());
		for (Docente o : l) {
			jcbCargarMaterias.addItem(o);
		}
	}



	/**
	 * Para eliminar todas las Asignaturas seleccionadas, deber�amos comenzar desde
	 * la �ltima e ir haciendo el barrido hasta la primera.
	 */
	private void seleccionarAsignaturas() {

		for (int i = this.jlNoSeleccionadas.getSelectedIndices().length - 1; i >= 0; i--) {
			this.listModelSeleccionados
			.addElement(this.listModelNoSeleccionados.get(this.jlNoSeleccionadas.getSelectedIndices()[i]));
			this.listModelNoSeleccionados.removeElementAt(this.jlNoSeleccionadas.getSelectedIndices()[i]);
		}
	}

	/*
	 * 
	 */
	private void deseleccionarAsignaturas() {

		for (int i = this.jlSeleccionadas.getSelectedIndices().length - 1; i >= 0; i--) {

			this.listModelNoSeleccionados
			.addElement(this.listModelSeleccionados.get(this.jlSeleccionadas.getSelectedIndices()[i]));
			this.listModelSeleccionados.removeElementAt(this.jlSeleccionadas.getSelectedIndices()[i]);

		}
	}

	/*
	 * deseleccionar todas las asignaturas
	 */
	private void deseleccionarTodos() {

		this.listModelNoSeleccionados.removeAllElements();
		this.listModelSeleccionados.removeAllElements();
		this.listModelNoSeleccionados.addAll(this.Asignaturas);

	}

	/*
	 * Seleccionar todas las asignaturas
	 */
	private void seleccionarTodos() {

		this.listModelNoSeleccionados.removeAllElements();
		this.listModelSeleccionados.removeAllElements();
		this.listModelSeleccionados.addAll(this.Asignaturas);

	}

	private void guardar() {
		List<Asignatura> listaParaGuardar = new ArrayList<Asignatura>();
		listaParaGuardar.removeAll(listaParaGuardar);

		for (int i = 0; i < listModelSeleccionados.size(); i++) {
			listaParaGuardar.add(listModelSeleccionados.getElementAt(i));
		}
		for (Asignatura Asignaturas : listaParaGuardar) {
			Asignaturaspordocente v = ControladorAsignaturaPorDocente
					.asignaturaDocenteProfesor((Docente) jcbCargarMaterias.getSelectedItem(), Asignaturas);
			if (v != null) {

				ControladorAsignaturaPorDocente.update(v);
			} else {
				v = new Asignaturaspordocente();
				v.setAsignatura(Asignaturas);
				v.setDocente((Docente) jcbCargarMaterias.getSelectedItem());
				ControladorAsignaturaPorDocente.insert(v);
			}
		}
		System.out.println("\n ");
	}

}
