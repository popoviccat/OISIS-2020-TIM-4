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
	private JPanel mainPanel;
	private GridBagConstraints cs;
	
	Color peach = new Color(249, 229, 222);
	
	public TabKorpa(String text) throws ClassNotFoundException, IOException {
		this.tabName = text;
		
		setLayout(new BorderLayout());
		mainPanel = new JPanel();
		
		mainPanel.setLayout(new GridBagLayout());
		cs = new GridBagConstraints();
		mainPanel.setBorder(new LineBorder(Color.white, 0));
		mainPanel.setBackground(Color.white);
		add(mainPanel, BorderLayout.CENTER);
		
		initTabKorpa();
	}
	
	public void initTabKorpa() throws ClassNotFoundException, IOException {
		mainPanel.removeAll();
		
		Korpa korpa =  main.getInstance().getKorpa();
		
		JButton btnDodajLek = new JButton("Dodaj lek");
		btnDodajLek.setBackground(peach);
		btnDodajLek.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		btnDodajLek.setPreferredSize(new Dimension(150,26));
		btnDodajLek.addMouseListener(new MyMouseListener(btnDodajLek));
		btnDodajLek.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DodajLekUKorpu dlukDlg = new DodajLekUKorpu(main.getInstance(), TabKorpa.this);
					dlukDlg.setVisible(true);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		cs.gridx = 0;
		cs.gridy = 0;
		cs.anchor = GridBagConstraints.WEST;
		cs.insets = new Insets(20, 20, 5, 10);
		mainPanel.add(btnDodajLek,cs);
		
		JButton btnDodajRecept = new JButton("Dodaj recept");
		btnDodajRecept.setBackground(peach);
		btnDodajRecept.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		btnDodajRecept.setPreferredSize(new Dimension(150,26));
		btnDodajRecept.addMouseListener(new MyMouseListener(btnDodajRecept));
		btnDodajRecept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DodajLekoveIzReceptaUKorpu dlirukDlg = new DodajLekoveIzReceptaUKorpu(main.getInstance(), TabKorpa.this);
					dlirukDlg.setVisible(true);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		cs.gridx = 1;
		cs.gridy = 0;
		cs.insets = new Insets(20, 20, 5, 0);
		mainPanel.add(btnDodajRecept,cs);
		
		cs.insets = new Insets(0, 0, 0, 0);
		cs.gridx = 0;
		cs.gridy = 1;
		cs.weighty = 1;
		cs.gridwidth = 2;
		cs.anchor = GridBagConstraints.CENTER;
		//Prikazi da je korpa prazna u slucaju da nema nijednog artikla u njoj
		if (korpa.getLekoviUKorpi().isEmpty()) {
			
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new GridBagLayout());
			centerPanel.setBackground(Color.white);
			
			GridBagConstraints centerPanelConstr = new GridBagConstraints();
			
			JLabel lbPraznaKorpa1 = new JLabel("Korpa je prazna!");
			lbPraznaKorpa1.setFont(new Font("Arial", Font.BOLD, 35));
			centerPanelConstr.gridx = 0;
			centerPanelConstr.gridy = 0;
			centerPanel.add(lbPraznaKorpa1,centerPanelConstr);
			
			JLabel lbPraznaKorpa2 = new JLabel("Zapocnite prodaju lekova dodavanjem jednog leka ili svih lekova sa recepta.");
			lbPraznaKorpa2.setFont(new Font("Arial", Font.PLAIN, 20));
			centerPanelConstr.insets = new Insets(20, 0, 0, 0);
			centerPanelConstr.gridx = 0;
			centerPanelConstr.gridy = 2;
			centerPanel.add(lbPraznaKorpa2,centerPanelConstr);
			
			centerPanel.setPreferredSize(new Dimension(700,350));
			mainPanel.add(centerPanel, cs);
		} else {
			CreateTableKorpa ct = new CreateTableKorpa();
			try {
				ct.CreateTableKorpa();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			mainPanel.add(ct,cs);
			ct.setBackground(Color.white);
			
			JLabel lbUkupnaCena = new JLabel("Ukupna cena artikala u korpi: " + main.getInstance().getKorpa().getCenaSvihLekovaUKorpi() + " din.");
			lbUkupnaCena.setFont(new Font("Arial", Font.BOLD, 22));
			cs.anchor = GridBagConstraints.EAST;
			cs.insets = new Insets(0, 0, 10, 20);
			cs.gridx = 0;
			cs.gridy = 2;
			cs.weighty = 0;
			mainPanel.add(lbUkupnaCena,cs);
		}
		
		JButton btnBrisanjeKorpe = new JButton("Obrisi korpu");
		btnBrisanjeKorpe.setBackground(peach);
		btnBrisanjeKorpe.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		btnBrisanjeKorpe.setPreferredSize(new Dimension(150,26));
		btnBrisanjeKorpe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int rezultat = JOptionPane.showConfirmDialog(TabKorpa.this, "Da li ste sigurni da zelite da obrisete sve artikle iz korpe?", "Brisanje artikala iz korpe", JOptionPane.YES_NO_OPTION);
				if (rezultat == JOptionPane.YES_OPTION) {
					try {
						main.getInstance().isprazniKorpu();						
						initTabKorpa();
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		cs.gridx = 0;
		cs.gridy = 3;
		cs.gridwidth = 1;
		cs.weighty = 0;
		cs.anchor = GridBagConstraints.WEST;
		cs.insets = new Insets(0, 20, 20, 0);
		mainPanel.add(btnBrisanjeKorpe, cs);
		
		
		JButton btnZavrsiProdaju = new JButton("Potvrdi kupovinu");
		btnZavrsiProdaju.setBackground(peach);
		btnZavrsiProdaju.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		btnZavrsiProdaju.setPreferredSize(new Dimension(150,26));
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
						initTabKorpa();
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		cs.gridx = 1;
		cs.gridy = 3;
		cs.anchor = GridBagConstraints.EAST;
		cs.insets = new Insets(0, 0, 20, 20);
		mainPanel.add(btnZavrsiProdaju, cs);
		
		if (korpa.getLekoviUKorpi().isEmpty()) {
			btnBrisanjeKorpe.setEnabled(false);
			btnZavrsiProdaju.setEnabled(false);
		} else {
			btnBrisanjeKorpe.addMouseListener(new MyMouseListener(btnBrisanjeKorpe));
			btnZavrsiProdaju.addMouseListener(new MyMouseListener(btnZavrsiProdaju));
		}
		
		SwingUtilities.updateComponentTreeUI(mainPanel);
	}
	
	public String getName() {
		return this.tabName;
	}
}