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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import controlers.readFromFile;
import model.Lek;
import utils.Row0Filter;
import utils.Row1Filter;
import utils.Row2Filter;
import utils.Row4Filter;

public class CreateTableLek extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5583791462138578512L;

	private Object[] columns = new Object[] { "Sifra",
			"Ime",
			"Proizvodjac",
			"Izdaje se na recept", 
			"Cena"};
	
	/*public Object[][] data = { { "N0000", "Brufen", "Galenika", true, 249, false },
			{ "N0001", "Kafetin", "Alkaloid", false, 169, false },
			{ "N0002", "Lorazepam", "Hemofarm", true, 409, false },
			 };*/
	
	public DefaultTableModel model;
	public JTable tbl;
	private TableRowSorter<DefaultTableModel> tableSorter;
	private Row0Filter sifraFilter;
	private Row1Filter imeFilter;
	private Row2Filter proizvFilter;
	private Row4Filter cenaFilter;

	private JTextField tfFilter;

	public void CreateTableLek() throws ClassNotFoundException, IOException {
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
		
		ArrayList<Lek> lekovi = readFromFile.readFromFileLek();
		int size = lekovi.size();
		int rows = 0;
		/*for (int b=0; b<size; b++) {
			if(lekovi.get(b).getLogickiObrisan() == false) {
				rows ++;
			}
		}*/
		//System.out.println(rows);
		Object[][] rowData = new Object[size][10];
		for (int i=0; i<size; i++) {
			//if(lekovi.get(i).getLogickiObrisan() == false) {
				//System.out.println("USAO");
			rowData[i][0] = lekovi.get(i).getSifra();
			rowData[i][1] = lekovi.get(i).getIme();
			rowData[i][2] = lekovi.get(i).getProizvodjac();
			rowData[i][3] = lekovi.get(i).getIzdajeSeNaRecept();
			rowData[i][4] = lekovi.get(i).getCena() + " din.";
			//}
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
		          return Boolean.class;
		        case 4:
		          return String.class;

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
				
				
				//even index, selected or not selected
				if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
					comp.setBackground(new Color(203, 231, 218));
				} else {
					if (isCellSelected(Index_row, Index_col)) {
						comp.setBackground(new Color(141, 191, 165));
						comp.setFont(new Font("Arial", Font.PLAIN, 14));
					} else {
						comp.setBackground(Color.white);
					}
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
		
		tbl.setRowHeight(23);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tbl.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		
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
		sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
		tableSorter.setSortKeys(sortKeys);

		tableSorter.setRowFilter(constructFilter());

		tbl.setRowSorter(tableSorter);

		tbl.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting() && tbl.getSelectedRow() != -1) {
							//System.out.println("SELECTED"+tbl.getValueAt(tbl.getSelectedRow(), 1) + " " + tbl.getValueAt(tbl.getSelectedRow(), 2));
						}
					}
				});
		tbl.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		// Poželjna veličina pogleda tabele u okviru scrollpane-a. Layout
		// manager uzima ovu osobinu u obzir.
		tbl.setPreferredScrollableViewportSize(new Dimension(650,283));

		// Širenje tabele kompletno po visini pogleda scrollpane-a.
		tbl.setFillsViewportHeight(true);
	}
	
	public void TableUpdate() throws ClassNotFoundException, IOException {
			
		ArrayList<Lek> lekovi = readFromFile.readFromFileLek();
		int size = lekovi.size();
		
		Object[][] rowData = new Object[size][10];
		for (int i=0; i<size; i++) {
			rowData[i][0] = lekovi.get(i).getSifra();
			rowData[i][1] = lekovi.get(i).getIme();
			rowData[i][2] = lekovi.get(i).getProizvodjac();
			rowData[i][3] = lekovi.get(i).getIzdajeSeNaRecept();
			rowData[i][4] = lekovi.get(i).getCena();
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
			    		  return Boolean.class;
			    	  case 4:
			    		  return String.class;
	
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
		sifraFilter.setValue(value);
		imeFilter.setValue(value);
		proizvFilter.setValue(value);
		cenaFilter.setValue(value);
		tableSorter.sort();
	}

	private RowFilter<DefaultTableModel, Integer> constructFilter() {
		sifraFilter = new Row0Filter();
		imeFilter = new Row1Filter();
		proizvFilter = new Row2Filter();
		cenaFilter = new Row4Filter();

		List<RowFilter<DefaultTableModel, Integer>> filters = new ArrayList<RowFilter<DefaultTableModel, Integer>>(
				4);
		filters.add(sifraFilter);
		filters.add(imeFilter);
		filters.add(proizvFilter);
		filters.add(cenaFilter);
		RowFilter<DefaultTableModel, Integer> orFilter = RowFilter.orFilter(filters);
		return orFilter;
	}
	
}
