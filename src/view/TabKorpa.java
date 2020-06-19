package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import application.main;
import controlers.MyMouseListener;
import controlers.writeToFile;
import model.Korpa;
import model.Lek;
import model.Racun;
import model.Stavka;

public class TabKorpa extends JPanel{

	private static final long serialVersionUID = -853959313304993533L;
	private String tabName;
	private JPanel topPanel;
	private GridBagConstraints cs;
	
	public TabKorpa(String text) throws ClassNotFoundException, IOException {
		this.tabName = text;
		
		Color peach = new Color(249, 229, 222);
		
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
			JLabel lbPraznaKorpa1 = new JLabel("Korpa je prazna!");
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
			cs.gridwidth = 2;
			cs.anchor = GridBagConstraints.NORTH;
			topPanel.add(ct,cs);
			ct.setBackground(Color.white);
			ct.setPreferredSize(new Dimension(700,380));
			ct.setVisible(true);
			
			JLabel lbUkupnaCena = new JLabel("Ukupna cena artikala u korpi: " + main.getInstance().getKorpa().getCenaSvihLekovaUKorpi());
			lbUkupnaCena.setFont(new Font("Arial", Font.BOLD, 25));
			cs.anchor = GridBagConstraints.EAST;
			cs.insets = new Insets(10, 0, 10, 10);
			cs.gridx = 0;
			cs.gridy = 1;
			topPanel.add(lbUkupnaCena,cs);
			
			JButton btnZavrsiProdaju = new JButton("Potvrdi kupovinu");
			btnZavrsiProdaju.setBackground(peach);
			btnZavrsiProdaju.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			btnZavrsiProdaju.setPreferredSize(new Dimension(150,26));
			btnZavrsiProdaju.addMouseListener(new MyMouseListener(btnZavrsiProdaju));
			btnZavrsiProdaju.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int rezultat = JOptionPane.showConfirmDialog(TabKorpa.this, "Da li ste sigurni da zelite da zavrsite kupovinu?", "Potvrda kupovine", JOptionPane.YES_NO_OPTION);
					if (rezultat == JOptionPane.YES_OPTION) {
						try {
							Korpa korpa = main.getInstance().getKorpa();
							HashMap<Lek, Integer> lekoviUKorpi = korpa.getLekoviUKorpi();
							Racun noviRacun = new Racun(main.getInstance().getKorisnickoImeUlogovanogKorisnika());
							
							for (Lek l : lekoviUKorpi.keySet()) {
								Stavka s = new Stavka(l, lekoviUKorpi.get(l));
								noviRacun.getProdatiLekovi().add(s);
							}
							
							writeToFile.writeToFileProdajaAddRacun(noviRacun);
							
							main.getInstance().isprazniKorpu();
							
							topPanel.removeAll();
							
							JLabel lbPraznaKorpa1 = new JLabel("Korpa je prazna!");
							lbPraznaKorpa1.setFont(new Font("Arial", Font.BOLD, 35));
							cs = new GridBagConstraints();
							cs.gridx = 0;
							cs.gridy = 0;
							topPanel.add(lbPraznaKorpa1,cs);
							
							JLabel lbPraznaKorpa2 = new JLabel("Zapocnite prodaju lekova klikom na dugme Dodaj u korpu.");
							lbPraznaKorpa2.setFont(new Font("Arial", Font.PLAIN, 25));
							cs.insets = new Insets(20, 0, 0, 0);
							cs.gridx = 0;
							cs.gridy = 1;
							topPanel.add(lbPraznaKorpa2,cs);
							
							SwingUtilities.updateComponentTreeUI(topPanel);
							
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			
			cs.gridx = 0;
			cs.gridy = 2;
			cs.gridwidth = 1;
			topPanel.add(btnZavrsiProdaju, cs);
			
			JButton btnBrisanjeKorpe = new JButton("Obrisi korpu");
			btnBrisanjeKorpe.setBackground(peach);
			btnBrisanjeKorpe.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			btnBrisanjeKorpe.setPreferredSize(new Dimension(150,26));
			btnBrisanjeKorpe.addMouseListener(new MyMouseListener(btnBrisanjeKorpe));
			btnBrisanjeKorpe.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int rezultat = JOptionPane.showConfirmDialog(TabKorpa.this, "Da li ste sigurni da zelite da obrisete sve artikle iz korpe?", "Brisanje artikala iz korpe", JOptionPane.YES_NO_OPTION);
					if (rezultat == JOptionPane.YES_OPTION) {
						try {
							main.getInstance().isprazniKorpu();
							
							topPanel.removeAll();
							
							JLabel lbPraznaKorpa1 = new JLabel("Korpa je prazna!");
							lbPraznaKorpa1.setFont(new Font("Arial", Font.BOLD, 35));
							cs = new GridBagConstraints();
							cs.gridx = 0;
							cs.gridy = 0;
							topPanel.add(lbPraznaKorpa1,cs);
							
							JLabel lbPraznaKorpa2 = new JLabel("Zapocnite prodaju lekova klikom na dugme Dodaj u korpu.");
							lbPraznaKorpa2.setFont(new Font("Arial", Font.PLAIN, 25));
							cs.insets = new Insets(20, 0, 0, 0);
							cs.gridx = 0;
							cs.gridy = 1;
							topPanel.add(lbPraznaKorpa2,cs);
							
							SwingUtilities.updateComponentTreeUI(topPanel);
							
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			cs.gridx = 1;
			cs.gridy = 2;
			cs.weightx = 0;
			topPanel.add(btnBrisanjeKorpe, cs);
		}
	}
	
	public String getName() {
		return this.tabName;
	}
}