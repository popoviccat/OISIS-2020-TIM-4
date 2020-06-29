package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import application.main;
import model.Lek;
import utils.Row0Filter;
import utils.Row1Filter;
import utils.Row2Filter;
import utils.Row4Filter;

public class CreateTableKorpa extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5583791462138578512L;

	private Object[] columns = new Object[] { "Sifra",
			"Ime",
			"Proizvodjac",
			"Na recept", 
			"Cena",
			"Kolicina",
			"Ukupna cena"};
	
	public DefaultTableModel model;
	public JTable tbl;
	private TableRowSorter<DefaultTableModel> tableSorter;
	private Row0Filter sifraFilter;
	private Row1Filter imeFilter;
	private Row2Filter proizvFilter;
	private Row4Filter cenaFilter;
	private DefaultTableCellRenderer centerRenderer;
	private JTextField tfFilter;

	public void CreateTableKorpa() throws ClassNotFoundException, IOException {
		Icon icon = new ImageIcon("images/search.png");
		JLabel title = new JLabel("Pretraga");
		JLabel pic = new JLabel( icon);
		this.setLayout(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		
		initTable();
		initTFFilter();

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

	public void initTable() throws ClassNotFoundException, IOException {
		
		HashMap<Lek, Integer> lekoviUKorpi = main.getInstance().getKorpa().getLekoviUKorpi();
		
		int size = lekoviUKorpi.size();
		Object[][] rowData = new Object[size][7];
		int brojac = 0;
		for (Lek l : lekoviUKorpi.keySet()) {
			rowData[brojac][0] = l.getSifra();
			rowData[brojac][1] = l.getIme();
			rowData[brojac][2] = l.getProizvodjac();
			rowData[brojac][3] = l.getIzdajeSeNaRecept();
			rowData[brojac][4] = l.getCena();
			rowData[brojac][5] = lekoviUKorpi.get(l);
			
			DecimalFormat twoDForm = new DecimalFormat("######.##");
			float cena = BigDecimal.valueOf( l.getCena() * lekoviUKorpi.get(l) ).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
			
			rowData[brojac][6] = cena;
			
			brojac++;
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
		          return Float.class;
		        case 5:
			      return Integer.class;
		        case 6:
			      return Float.class;

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
			  return comp;
			}
		};

		model.fireTableDataChanged();
		JTableHeader header = tbl.getTableHeader();
		header.setBackground(new Color(141, 191, 165));
		header.setPreferredSize(new Dimension(650,30));
		header.setFont(new Font("Arial", Font.ITALIC, 14));
		header.setBorder(BorderFactory.createMatteBorder(0,0,3,0,new Color(56, 97, 76)));
		header.setReorderingAllowed(false);
		
		centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tbl.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		
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

		tbl.setPreferredScrollableViewportSize(new Dimension(650,250));

		tbl.setFillsViewportHeight(true);
		
		tbl.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;
			
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		    	this.setHorizontalAlignment( JLabel.CENTER );
		    	Float d = (Float)value;
		        String s = String.valueOf(d.floatValue()) + " din.";
		        Component c = super.getTableCellRendererComponent(table, s, isSelected, hasFocus, row, column);
		        return c;
		    }
		});
		tbl.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;
			
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		    	this.setHorizontalAlignment( JLabel.CENTER );
		    	Float d = (Float)value;
		        String s = String.valueOf(d.floatValue()) + " din.";
		        Component c = super.getTableCellRendererComponent(table, s, isSelected, hasFocus, row, column);
		        return c;
		    }
		});
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
