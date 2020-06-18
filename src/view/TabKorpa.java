package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import application.main;
import model.Korpa;
import model.Prodaja;

public class TabKorpa extends JPanel{

	private static final long serialVersionUID = -853959313304993533L;
	private String tabName;
	private Prodaja prodaja;
	private JPanel topPanel;
	private GridBagConstraints cs;
	
	public TabKorpa(String text) throws ClassNotFoundException, IOException {
		this.tabName = text;
		
		setLayout(new BorderLayout());
		topPanel = new JPanel();
		add(BorderLayout.PAGE_START, topPanel);
		
		topPanel.setLayout(new GridBagLayout());
		cs = new GridBagConstraints();
		topPanel.setBorder(new LineBorder(Color.white, 0));
		topPanel.setBackground(Color.white);
		add(topPanel, BorderLayout.CENTER);
		
		Korpa korpa =  main.getInstance().getKorpa();
		
		//Prikazi da je korpa prazna u slucaju da nema nijednog artikla u njoj
		if (korpa.getLekoviUKorpi().isEmpty()) {
			JLabel lbPraznaKorpa1 = new JLabel("Vasa korpa je prazna!");
			lbPraznaKorpa1.setFont(new Font("Arial", Font.BOLD, 35));
			cs.gridx = 0;
			cs.gridy = 0;
			topPanel.add(lbPraznaKorpa1,cs);
			
			JLabel lbPraznaKorpa2 = new JLabel("Zapocnite prodaju lekova klikom na dugme Dodaj u korpu.");
			lbPraznaKorpa2.setFont(new Font("Arial", Font.PLAIN, 25));
			cs.insets = new Insets(20, 0, 0, 0);
			cs.gridx = 0;
			cs.gridy = 1;
			topPanel.add(lbPraznaKorpa2,cs);
		} else {
			CreateTableKorpa ct = new CreateTableKorpa();
			try {
				ct.CreateTableKorpa();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			cs.gridx = 0;
			cs.gridy = 0;
			cs.weightx = 1.0;
			cs.weighty = 1.0;
			cs.anchor = GridBagConstraints.NORTH;
			topPanel.add(ct,cs);
			ct.setBackground(Color.white);
			ct.setPreferredSize(new Dimension(700,380));
			ct.setVisible(true);
			
			JLabel lbUkupnaCena = new JLabel("Ukupna cena artikala u korpi: " + main.getInstance().getKorpa().getCenaSvihLekovaUKorpi());
			lbUkupnaCena.setFont(new Font("Arial", Font.BOLD, 25));
			cs.anchor = GridBagConstraints.EAST;
			cs.insets = new Insets(0, 0, 10, 10);
			cs.gridx = 0;
			cs.gridy = 1;
			topPanel.add(lbUkupnaCena,cs);
		}
	}
	
	public String getName() {
		return this.tabName;
	}
}