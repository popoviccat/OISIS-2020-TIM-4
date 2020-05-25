package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class BookTab extends JPanel {

	private static final long serialVersionUID = 7445755320045782268L;

	//private JPanel topPanel;
	GridBagConstraints gbc = new GridBagConstraints();
	private String bookName;
	
	public BookTab(String text) {
		this.bookName = text;
		
		
		this.setLayout(new BorderLayout());;
		
		JPanel topPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel downPanel = new JPanel();
		add(BorderLayout.PAGE_START, topPanel);
		add(BorderLayout.PAGE_END, downPanel);
		
		
		this.add(topPanel, BorderLayout.WEST);
		this.add(downPanel, BorderLayout.SOUTH);
		this.add(centerPanel, BorderLayout.CENTER);

	
		JButton btnn = new JButton("Sačuvaj");
		
	    downPanel.add(btnn);
	  
	    JLabel lbl = new JLabel("Sortiraj");
	    lbl.setVisible(true);
	   
	    topPanel.add(lbl);

	    String[] choices = { "Po imenu","Po prezimenu", "Po tipu korisnika"};

	    final JComboBox<String> cb = new JComboBox<String>(choices);

	    cb.setVisible(true);
	    topPanel.add(cb);
	    
	    JButton btn = new JButton("OK");	   
		topPanel.add(btn);
	  
	}
	
	public void saveBookState() {
		System.out.println("Cuvam sadrzaj knjige: " + this.bookName);
	}
	
	public String getName() {
		return this.bookName;
	}
}
