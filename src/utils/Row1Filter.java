package utils;

import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;


public class Row1Filter extends RowFilter<DefaultTableModel, Integer> {

	private String value;

	@Override
	public boolean include(
			javax.swing.RowFilter.Entry<? extends DefaultTableModel, ? extends Integer> entry) {
		return entry.getStringValue(1).toLowerCase()
				.contains(getValue().toLowerCase());
	}

	public String getValue() {
		if (value == null) {
			return "";
		}
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
