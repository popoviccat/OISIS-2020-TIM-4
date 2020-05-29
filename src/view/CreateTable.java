package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
<<<<<<< HEAD:src/view/CreateTable.java
=======
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
>>>>>>> e878c505ac6500fbd80b6b11919a1b6e8ea0fbba:src/application/TabKorisnici.java

import controlers.MyTableModel;
import utils.RowImeFilter;
import utils.RowPrezimeFilter;

public class CreateTable extends JPanel{
	

<<<<<<< HEAD:src/view/CreateTable.java
	/**
	 * 
	 */
	private static final long serialVersionUID = 8915538830180476453L;
	
	private JTable tblStudenti;
=======
public class TabKorisnici extends JPanel {

	private static final long serialVersionUID = 7445755320045782268L;

	private JTable korisnici;
>>>>>>> e878c505ac6500fbd80b6b11919a1b6e8ea0fbba:src/application/TabKorisnici.java
	private MyTableModel tableModel;
	private TableRowSorter<MyTableModel> tableSorter;
	private RowImeFilter imeFilter;
	private RowPrezimeFilter prezimeFilter;

	private JTextField tfFilter;

	public CreateTable() {
		Icon icon = new ImageIcon("images/search.png");
		JLabel title = new JLabel("Pretraga");
		JLabel pic = new JLabel( icon);
		this.setLayout(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		
		initTable();
		initTFFilter();

		// Zaglavlje kolone se ne mora ručno ubacivati. JScrollPane će odraditi
		// taj posao.
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 3;
		cs.weightx = 1.0;
		add(new JScrollPane(tblStudenti),cs);
		tblStudenti.setBackground(new Color(140,208,172));
		
		cs.insets = new Insets(0, 10, 15, 0);
	    cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		cs.anchor = GridBagConstraints.FIRST_LINE_START;
		add(title,cs);
		
<<<<<<< HEAD:src/view/CreateTable.java
		cs.insets = new Insets(0, 5, 15, 18);
	    cs.gridx = 1;
		cs.gridy = 0;
		add(pic,cs);
		
		cs.insets = new Insets(0, 0, 15, 415);
	    cs.gridx = 2;
		cs.gridy = 0;
		tfFilter.setPreferredSize(new Dimension(180,20));
		add(tfFilter,cs);
		tfFilter.setBackground(Color.white);

=======
		this.add(container);
		/*container.setBorder( new EmptyBorder( 20, 20, 20, 20 ) );*/
		container.add(new JScrollPane(korisnici), BorderLayout.CENTER);
		container.setBorder(new LineBorder(Color.WHITE));
		add(tfFilter, BorderLayout.NORTH);
	
>>>>>>> e878c505ac6500fbd80b6b11919a1b6e8ea0fbba:src/application/TabKorisnici.java
	}

	private void initTable() {
		tableModel = new MyTableModel();
<<<<<<< HEAD:src/view/CreateTable.java
		tblStudenti = new JTable(tableModel);

=======
		korisnici = new JTable(tableModel);
		
		
>>>>>>> e878c505ac6500fbd80b6b11919a1b6e8ea0fbba:src/application/TabKorisnici.java
		tableSorter = new TableRowSorter<MyTableModel>(tableModel);
		tableSorter.setComparator(0, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}

		});

		List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		tableSorter.setSortKeys(sortKeys);

		tableSorter.setRowFilter(constructFilter());

		korisnici.setRowSorter(tableSorter);

		korisnici.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()
								&& korisnici.getSelectedRow() != -1) {
							System.out.println(korisnici.getValueAt(
									korisnici.getSelectedRow(), 1)
									+ " "
									+ korisnici.getValueAt(
											korisnici.getSelectedRow(), 2));
						}
					}
				});
		korisnici.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

<<<<<<< HEAD:src/view/CreateTable.java
		// Poželjna veličina pogleda tabele u okviru scrollpane-a. Layout
		// manager uzima ovu osobinu u obzir.
		tblStudenti.setPreferredScrollableViewportSize(new Dimension(650,260));

		// Širenje tabele kompletno po visini pogleda scrollpane-a.
		tblStudenti.setFillsViewportHeight(true);
=======
		korisnici.setPreferredScrollableViewportSize(new Dimension(700, 400));

		korisnici.setFillsViewportHeight(true);
>>>>>>> e878c505ac6500fbd80b6b11919a1b6e8ea0fbba:src/application/TabKorisnici.java
	}

	private void initTFFilter() {
		tfFilter = new JTextField();
		tfFilter.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				filter(tfFilter.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				filter(tfFilter.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				filter(tfFilter.getText());
			}
		});
	}

	private void filter(String value) {
		imeFilter.setValue(value);
		prezimeFilter.setValue(value);
		tableSorter.sort();
	}

	private RowFilter<MyTableModel, Integer> constructFilter() {
		imeFilter = new RowImeFilter();
		prezimeFilter = new RowPrezimeFilter();

		List<RowFilter<MyTableModel, Integer>> filters = new ArrayList<RowFilter<MyTableModel, Integer>>(
				2);
		filters.add(imeFilter);
		filters.add(prezimeFilter);
		RowFilter<MyTableModel, Integer> orFilter = RowFilter.orFilter(filters);
		return orFilter;
	}
	
}
