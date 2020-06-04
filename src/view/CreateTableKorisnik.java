package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
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
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;


import utils.RowImeFilter;
import utils.RowPrezimeFilter;
import utils.RowTipKorisnikaFilter;

public class CreateTableKorisnik extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8915538830180476453L;
	
	private Object[] columns = new Object[] { "Korisnicko ime",
			"Ime",
			"Prezime",
			"Tip Korisnika" };

	public Object[][] data = { { "pera", "Petar", "Petrovic", "Apotekar" },
			{ "laza", "Lazar", "Lazic", "Lekar" },
			{ "mika", "Milan", "Mikic", "Apotekar" },
			{ "sara", "Sara", "Saric", "Apotekar" },
			{ "barbara", "Barbara", "Barbaric", "Lekar" },
			{ "ana", "Ana", "Petrovic", "Administrator" },
			 };
	
	public DefaultTableModel model;
	public JTable tbl;
	private TableRowSorter<DefaultTableModel> tableSorter;
	private RowImeFilter imeFilter;
	private RowPrezimeFilter prezimeFilter;
	private RowTipKorisnikaFilter tipFilter;

	private JTextField tfFilter;

	public CreateTableKorisnik() {
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
		add(new JScrollPane(tbl),cs);
		tbl.setBackground(new Color(220, 239, 230));
		
		cs.insets = new Insets(0, 10, 15, 0);
	    cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		cs.anchor = GridBagConstraints.FIRST_LINE_START;
		add(title,cs);
		
		cs.insets = new Insets(0, 5, 15, 18);
	    cs.gridx = 1;
		cs.gridy = 0;
		add(pic,cs);
		
		cs.insets = new Insets(0, 0, 16, 416);
	    cs.gridx = 2;
		cs.gridy = 0;
		tfFilter.setPreferredSize(new Dimension(180,20));
		add(tfFilter,cs);
		tfFilter.setBackground(Color.white);

	}

	private void initTable() {
		model = new DefaultTableModel(data, columns);
		
		tbl = new JTable(model) {
			public Component prepareRenderer (TableCellRenderer renderer,int Index_row, int Index_col) {
				Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
				//even index, selected or not selected
				if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
					comp.setBackground(new Color(203, 231, 218));
				} else {
					if (isCellSelected(Index_row, Index_col)) {
						comp.setBackground(new Color(141, 191, 165));
						comp.setFont(new Font("Arial", Font.PLAIN, 14));
				} else
					comp.setBackground(Color.white);
			    }
			  return comp;
			}
		};
		JTableHeader header = tbl.getTableHeader();
		header.setBackground(new Color(141, 191, 165));
		header.setPreferredSize(new Dimension(650,30));
		header.setFont(new Font("Arial", Font.ITALIC, 14));
		header.setBorder(BorderFactory.createMatteBorder(0,0,3,0,new Color(56, 97, 76)));
		
		tbl.setRowHeight(23);
		
		tableSorter = new TableRowSorter<DefaultTableModel>(model);
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
		sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
		tableSorter.setSortKeys(sortKeys);

		tableSorter.setRowFilter(constructFilter());

		tbl.setRowSorter(tableSorter);

		tbl.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting() && tbl.getSelectedRow() != -1) {
							System.out.println("SELECTED"+tbl.getValueAt(tbl.getSelectedRow(), 1) + " " + tbl.getValueAt(tbl.getSelectedRow(), 2));
						}
					}
				});
		tbl.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		// Poželjna veličina pogleda tabele u okviru scrollpane-a. Layout
		// manager uzima ovu osobinu u obzir.
		tbl.setPreferredScrollableViewportSize(new Dimension(650,260));

		// Širenje tabele kompletno po visini pogleda scrollpane-a.
		tbl.setFillsViewportHeight(true);
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
		tipFilter.setValue(value);
		tableSorter.sort();
	}

	private RowFilter<DefaultTableModel, Integer> constructFilter() {
		imeFilter = new RowImeFilter();
		prezimeFilter = new RowPrezimeFilter();
		tipFilter = new RowTipKorisnikaFilter();

		List<RowFilter<DefaultTableModel, Integer>> filters = new ArrayList<RowFilter<DefaultTableModel, Integer>>(
				3);
		filters.add(imeFilter);
		filters.add(prezimeFilter);
		filters.add(tipFilter);
		RowFilter<DefaultTableModel, Integer> orFilter = RowFilter.orFilter(filters);
		return orFilter;
	}
	
}
