package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import controlers.readFromFile;
import model.Korisnik;
import model.TipKorisnika;
import utils.Row1Filter;
import utils.Row2Filter;
import utils.Row3Filter;

public class CreateTableKorisnik extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8915538830180476453L;
	
	private Object[] columns = new Object[] { "Korisnicko ime",
			"Ime",
			"Prezime",
			"Tip Korisnika", 
			"Obrisan"};
	
	public DefaultTableModel model;
	public JTable tbl;
	public TableRowSorter<DefaultTableModel> tableSorter;
	private Row1Filter imeFilter;
	private Row2Filter prezimeFilter;
	private Row3Filter tipFilter;

	private JTextField tfFilter;

	public void CreateTableKorisnik() throws ClassNotFoundException, IOException {
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

	private void initTable() throws ClassNotFoundException, IOException {
		
		ArrayList<Korisnik> korisnici = readFromFile.readFromFileKor();
		
		int size = korisnici.size();
		Object[][] rowData = new Object[size][10];
		for (int i=0; i<size; i++) {
			rowData[i][0] = korisnici.get(i).getKorisnickoIme();
			rowData[i][1] = korisnici.get(i).getIme();
			rowData[i][2] = korisnici.get(i).getPrezime();
			rowData[i][3] = korisnici.get(i).getTipKorisnika();
			rowData[i][4] = korisnici.get(i).getLogickiObrisan();
		}
		model = new DefaultTableModel(rowData,columns){
		      public Class<?> getColumnClass(int column)
		      {
		        switch(column)
		        {
		        case 0:
		          return String.class;
		        case 1:
		          return String.class;
		        case 2:
		          return String.class;
		        case 3:
		          return TipKorisnika.class;
		        case 4:
		          return Boolean.class;

		          default:
		            return String.class;
		        }
		      }
		      @Override
		      public boolean isCellEditable(int rowIndex, int columnIndex) {
		    	  return columnIndex == 100;
		      }
		    };
		
		
		
		tbl = new JTable(model) {
			public Component prepareRenderer (TableCellRenderer renderer,int Index_row, int Index_col) {
				Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
				if ((boolean) tbl.getValueAt(Index_row, 4) == false) {
					//even index, selected or not selected
					if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
						comp.setBackground(new Color(203, 231, 218));
						comp.setForeground(Color.black);
						tbl.setRowHeight(Index_row, 23);
					} else {
						if (isCellSelected(Index_row, Index_col)) {
							comp.setBackground(new Color(108, 172, 139));
							comp.setFont(new Font("Arial", Font.PLAIN, 14));
							comp.setForeground(Color.white);
							tbl.setRowHeight(Index_row, 27);
						} else {
							comp.setBackground(Color.white);
							comp.setForeground(Color.black);
							tbl.setRowHeight(Index_row, 23);
						}
				    }
				} else {
					comp.setBackground(new Color(197, 197, 197));
					comp.setForeground(Color.black);
				}
			  return comp;
			}
		};
		//tbl.setEnabled(false);
		model.fireTableDataChanged();
		JTableHeader header = tbl.getTableHeader();
		header.setBackground(new Color(141, 191, 165));
		header.setPreferredSize(new Dimension(650,30));
		header.setFont(new Font("Arial", Font.ITALIC, 14));
		header.setBorder(BorderFactory.createMatteBorder(0,0,3,0,new Color(56, 97, 76)));
		header.setReorderingAllowed(false);
		
		tableSorter = new TableRowSorter<DefaultTableModel>(model);
		tbl.setGridColor(new Color(141, 191, 165));
		
		tbl.setRowHeight(23);
		

		List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
		tableSorter.setSortKeys(sortKeys);

		tableSorter.setRowFilter(constructFilter());

		tbl.setRowSorter(tableSorter);

		tbl.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		// Poželjna veličina pogleda tabele u okviru scrollpane-a. Layout
		// manager uzima ovu osobinu u obzir.
		tbl.setPreferredScrollableViewportSize(new Dimension(650,283));

		// Širenje tabele kompletno po visini pogleda scrollpane-a.
		tbl.setFillsViewportHeight(true);
	}
	
	public void TableUpdate() throws ClassNotFoundException, IOException {
			
		ArrayList<Korisnik> korisnici = readFromFile.readFromFileKor();
		int size = korisnici.size();
		
		Object[][] rowData = new Object[size][10];
		for (int i=0; i<size; i++) {
			rowData[i][0] = korisnici.get(i).getKorisnickoIme();
			rowData[i][1] = korisnici.get(i).getIme();
			rowData[i][2] = korisnici.get(i).getPrezime();
			rowData[i][3] = korisnici.get(i).getTipKorisnika();
			rowData[i][4] = korisnici.get(i).getLogickiObrisan();
		}
		model = new DefaultTableModel(rowData,columns){
		      public Class<?> getColumnClass(int column) {
		    	  switch(column) {
			    	  case 0:
			    		  return String.class;
			    	  case 1:
			    		  return String.class;
			    	  case 2:
			    		  return String.class;
			    	  case 3:
			    		  return TipKorisnika.class;
			    	  case 4:
			    		  return Boolean.class;
	
			    	  default:
			        	return String.class;
		    	  }
		      }
		      @Override
		      public boolean isCellEditable(int rowIndex, int columnIndex) {
		    	  return columnIndex == 100;
		      }
		    };
		    
		tbl.setModel(model);
		
		tableSorter = new TableRowSorter<DefaultTableModel>(model);
		
		List<RowSorter.SortKey> sortKeys2 = new ArrayList<RowSorter.SortKey>();
		sortKeys2.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sortKeys2.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		sortKeys2.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
		sortKeys2.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
		sortKeys2.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
		tableSorter.setSortKeys(sortKeys2);

		tbl.setRowSorter(tableSorter);
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
		imeFilter = new Row1Filter();
		prezimeFilter = new Row2Filter();
		tipFilter = new Row3Filter();

		List<RowFilter<DefaultTableModel, Integer>> filters = new ArrayList<RowFilter<DefaultTableModel, Integer>>(
				3);
		filters.add(imeFilter);
		filters.add(prezimeFilter);
		filters.add(tipFilter);
		RowFilter<DefaultTableModel, Integer> orFilter = RowFilter.orFilter(filters);
		return orFilter;
	}
	
}
