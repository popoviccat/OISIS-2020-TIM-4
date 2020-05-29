
package utils;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MyTableModel implements TableModel {

	private Object[] columns = new Object[] { "KorisniÄ�ko ime", "Ime", "Prezime",
			"Tip Korisnika" };

	private Object[][] data = { { "pera", "Petar", "PetroviÄ‡", "Apotekar" },
			{ "laza", "Lazar", "LaziÄ‡", "Lekar" },
			{ "mika", "Milan", "MikiÄ‡", "Apotekar" },
			{ "ana", "Ana", "PetroviÄ‡", "Administrator" },
			 };

	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return data[0].length;
	}

	public String getColumnName(int columnIndex) {
		return (String) columns[columnIndex];
	}

	public Class<?> getColumnClass(int columnIndex) {
		return data[0][columnIndex].getClass();
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return false;
		} else {
			return true;
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = aValue;
	}

	public void addTableModelListener(TableModelListener l) {

	}

	public void removeTableModelListener(TableModelListener l) {

	}

}
