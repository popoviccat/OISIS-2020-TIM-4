package utils;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class LineWrapCellRenderer extends JTextArea implements TableCellRenderer {
	
	private static final long serialVersionUID = -8389174791182748239L;

	@Override
    public Component getTableCellRendererComponent( JTable table,  Object value, boolean isSelected, boolean hasFocus,  int row, int column) {
        this.setText((String)value);
        this.setWrapStyleWord(true);            
        this.setLineWrap(true);
        this.setFont(new Font("Arial", Font.PLAIN, 12));
        return this;
    }
}
