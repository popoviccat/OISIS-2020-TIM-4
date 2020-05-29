
package utils;

import javax.swing.RowFilter;

public class RowImeFilter extends RowFilter<MyTableModel, Integer> {

	private String value;

	public boolean include(
			javax.swing.RowFilter.Entry<? extends MyTableModel, ? extends Integer> entry) {
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
