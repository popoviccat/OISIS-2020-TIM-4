package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class TabRecepti extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1392769694146741116L;

	private JPanel topPanel;
	
	private String bookName;
	
	public TabRecepti(String text) {
		this.bookName = text;
		this.setLayout(new BorderLayout());;
		
		// panel za toolbar
		this.topPanel = new JPanel();
		topPanel.add(new JLabel("Toolbar za knjigu " + this.bookName));
		this.add(topPanel, BorderLayout.NORTH);
		
		// panel za stablo
		JPanel leftPanel = new JPanel();
		leftPanel.add(new JLabel("Stablo knjige " + this.bookName));
		
		// panel za sadrzaj stranice
		JPanel rightPanel = new JPanel();
		rightPanel.add(new JLabel("Sadrzaj izabrane stranice knjige " + this.bookName));
		
		// split pane
		this.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel),
				BorderLayout.CENTER);
	}
	
	public void saveBookState() {
		System.out.println("Cuvam sadrzaj knjige: " + this.bookName);
	}
	
	public String getName() {
		return this.bookName;
	}

}

