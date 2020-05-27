package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import utils.RowImeFilter;
import utils.RowPrezimeFilter;
import utils.MyTableModel;
import utils.MyTableModel;


public class BookTab extends JPanel {

	private static final long serialVersionUID = 7445755320045782268L;

	private JTable tblStudenti;
	private MyTableModel tableModel;
	private TableRowSorter<MyTableModel> tableSorter;
	private JTextField tfFilter;
	private RowImeFilter imeFilter;
	private RowPrezimeFilter prezimeFilter;
	
	private String bookName;
	
	public BookTab(String text) {
		this.bookName = text;
		
		
		this.setLayout(new BorderLayout());
		
		
		JPanel topPanel = new JPanel();
		//JPanel centerPanel = new JPanel();
		JPanel downPanel = new JPanel();
		add(BorderLayout.PAGE_START, topPanel);
		add(BorderLayout.PAGE_END, downPanel);
		
		
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(downPanel, BorderLayout.SOUTH);
		//this.add(centerPanel, BorderLayout.CENTER);

	
		JButton btnn = new JButton("SaÄ�uvaj");
		
	    downPanel.add(btnn);
	  
	    JLabel lbl = new JLabel("Sortiraj");
	    lbl.setVisible(true);
	   
	    topPanel.add(lbl);

	    String[] choices = { "Po imenu","Po prezimenu", "Po tipu korisnika"};

	    final JComboBox<String> cb = new JComboBox<String>(choices);

	    cb.setVisible(true);
	    topPanel.add(cb);
	    
	    JButton btn = new JButton("OK");	   
		topPanel.add(btn);
		
		initTable();
		initTFFilter();
		//initTable table = new initTable();
		//initTable.setVisible(true);
		//JScrollPane centerPanel = new JScrollPane(table);
		//centerPanel.setPreferredSize(new Dimension(200,200));
		
		
		createTable();

	}
	
	public void createTable() {
		

		initTable();
		initTFFilter();

		// Zaglavlje kolone se ne mora ruÄ�no ubacivati. JScrollPane Ä‡e odraditi
		// taj posao.
		add(new JScrollPane(tblStudenti));
		add(tfFilter, BorderLayout.NORTH);
	
	}
	
	private void initTable() {
		tableModel = new MyTableModel();
		tblStudenti = new JTable(tableModel);

		tableSorter = new TableRowSorter<MyTableModel>(tableModel);
		tableSorter.setComparator(0, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// Case sensitive.
				return o1.compareTo(o2);
			}

		});

		List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		tableSorter.setSortKeys(sortKeys);

		tableSorter.setRowFilter(constructFilter());

		tblStudenti.setRowSorter(tableSorter);

		tblStudenti.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()
								&& tblStudenti.getSelectedRow() != -1) {
							System.out.println(tblStudenti.getValueAt(
									tblStudenti.getSelectedRow(), 1)
									+ " "
									+ tblStudenti.getValueAt(
											tblStudenti.getSelectedRow(), 2));
						}
					}
				});
		tblStudenti.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		// PoÅ¾eljna veliÄ�ina pogleda tabele u okviru scrollpane-a. Layout
		// manager uzima ovu osobinu u obzir.
		tblStudenti.setPreferredScrollableViewportSize(new Dimension(500, 400));

		// Å irenje tabele kompletno po visini pogleda scrollpane-a.
		tblStudenti.setFillsViewportHeight(true);
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
	
	public void saveBookState() {
		System.out.println("Cuvam sadrzaj knjige: " + this.bookName);
	}
	
	public String getName() {
		return this.bookName;
	}
}