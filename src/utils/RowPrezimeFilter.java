/**
 * 
 */
package utils;

import javax.swing.RowFilter;

/**
 * @author Danijel
 * 
 */
public class RowPrezimeFilter extends RowFilter<MyTableModel, Integer> {

	private String value;

	@Override
	public boolean include(
			javax.swing.RowFilter.Entry<? extends MyTableModel, ? extends Integer> entry) {
		return entry.getStringValue(2).toLowerCase()
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