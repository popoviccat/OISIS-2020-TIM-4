package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controlers.MyMouseListener;


public class TabKorisnici extends JPanel {

	private static final long serialVersionUID = 7445755320045782268L;

	Color peach = new Color(249, 229, 222);
	Color mint = new Color(140,208,172);
	
	private String bookName;
	
	public TabKorisnici(String text) {
		this.bookName = text;
		
		this.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		add(BorderLayout.PAGE_START, topPanel);
		
		topPanel.setLayout(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		topPanel.setBorder(new LineBorder(Color.white, 0));
		topPanel.setBackground(Color.white);
		this.add(topPanel, BorderLayout.CENTER);
	  
		JButton addUser = new JButton("Dodaj korisnika");
		addUser.setBackground(peach);
		addUser.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		addUser.setPreferredSize(new Dimension(150,26));
		addUser.addMouseListener(new MyMouseListener(addUser));
		addUser.setVisible(true);
		cs.insets = new Insets(20, 50, 0, 10);
	    cs.gridx = 0;
		cs.gridy = 0;
	    topPanel.add(addUser, cs);

	    JButton delUser = new JButton("Obrisi korisnika");
	    delUser.setBackground(peach);
	    delUser.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
	    delUser.setPreferredSize(new Dimension(150,26));
	    delUser.addMouseListener(new MyMouseListener(delUser));
	    cs.insets = new Insets(20, 20, 0, 20);
	    cs.gridx = 1;
		cs.gridy = 0;
	    topPanel.add(delUser, cs);
	    
		JButton saveBtn = new JButton("Sačuvaj");
		saveBtn.setBackground(peach);
		saveBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		saveBtn.setPreferredSize(new Dimension(100,26));
		saveBtn.addMouseListener(new MyMouseListener(saveBtn));
		cs.insets = new Insets(20, 20, 25, 40);
		cs.gridx = 2;
		cs.gridy = 2;
		cs.anchor = GridBagConstraints.LAST_LINE_END;
	    topPanel.add(saveBtn, cs);
		
		CreateTable ct = new CreateTable();
		cs.insets = new Insets(0, 0, 0, 0);
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 3;
		cs.weightx = 1.0;
		cs.weighty = 1.0;
		cs.anchor = GridBagConstraints.PAGE_END;
		topPanel.add(ct,cs);
		ct.setBackground(Color.white);
		ct.setPreferredSize(new Dimension(700,354));
		ct.setVisible(true);
		
	}
	
	public void saveBookState() {
		System.out.println("Cuvam sadrzaj knjige: " + this.bookName);
	}
	
	public String getName() {
		return this.bookName;
	}
}