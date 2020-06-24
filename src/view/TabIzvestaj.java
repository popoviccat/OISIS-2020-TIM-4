package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import application.main;
import controlers.readFromFile;
import model.Korisnik;
import model.Lek;
import model.Prodaja;
import model.Racun;
import model.Stavka;
import model.TipKorisnika;

public class TabIzvestaj extends JPanel{

	private static final long serialVersionUID = -853959313304993533L;
	private String tabName;
	private Prodaja prodaja;
	private JPanel topPanel;
	private GridBagConstraints cs;
	
	public TabIzvestaj(String text) throws ClassNotFoundException, IOException {
		this.tabName = text;
		
		setLayout(new BorderLayout());
		topPanel = new JPanel();
		add(BorderLayout.PAGE_START, topPanel);
		
		topPanel.setLayout(new GridBagLayout());
		cs = new GridBagConstraints();
		topPanel.setBorder(new LineBorder(Color.white, 0));
		topPanel.setBackground(Color.white);
		add(topPanel, BorderLayout.CENTER);
		OdaberiTipIzvestaja otiDlg = new OdaberiTipIzvestaja(main.getInstance(), this);
		otiDlg.setVisible(true);
	}
	
	public void prazanPrikaz() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setBackground(Color.white);
		
		
		JLabel lbPrazno = new JLabel("Morate izabrati jedan od nacina za prikaz izvestaja.");
		lbPrazno.setFont(new Font("Arial", Font.PLAIN, 24));
		cs.gridx = 0;
		cs.gridy = 0;
		cs.anchor = GridBagConstraints.CENTER;
		centerPanel.add(lbPrazno,cs);
		
		centerPanel.setPreferredSize(new Dimension(700,350));
		topPanel.add(centerPanel, cs);
	}
	
	public void prikazSvihLekova() throws ClassNotFoundException, IOException {
		prodaja = readFromFile.readFromFileProdaja();
		CreateTableIzvestaj ct = new CreateTableIzvestaj();
		try {
			ct.CreateTableIzvestaj(prodaja.getListaSvihProdatihStavki());
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lbIzvestaj = new JLabel(" Ukupna prodaja svih lekova");
		lbIzvestaj.setBorder(BorderFactory.createMatteBorder(0,4,0,0,new Color(56, 97, 76)));
		lbIzvestaj.setFont(new Font("Arial", Font.BOLD, 23));
		cs.insets = new Insets(15, 50, 0, 0);
		cs.gridx = 0;
		cs.gridy = 0;
		cs.anchor = GridBagConstraints.FIRST_LINE_START;
		topPanel.add(lbIzvestaj,cs);
		
		cs.insets = new Insets(10, 0, 0, 0);
		cs.gridx = 0;
		cs.gridy = 1;
		cs.weightx = 1.0;
		cs.weighty = 1.0;
		cs.anchor = GridBagConstraints.NORTH;
		topPanel.add(ct,cs);
		ct.setBackground(Color.white);
		ct.setPreferredSize(new Dimension(700,380));
		ct.setVisible(true);
	}
	
	public void prikazLekovaOdabranogProizvodjaca() throws ClassNotFoundException, IOException{
		
		ArrayList<Lek> lekovi = readFromFile.readFromFileLek();
		ArrayList<String> proizvodjaci = new ArrayList<>();
		for (Lek l : lekovi) {
			if (!proizvodjaci.contains(l.getProizvodjac())) {
				proizvodjaci.add(l.getProizvodjac());
			}
		}
		
		if (proizvodjaci.isEmpty()) {
			return;
		}
		
		CreateTableIzvestaj ct = new CreateTableIzvestaj();
		ArrayList<Stavka> stavkeOdabranogProizvodajca = stavkeOdabranogProizvodadjaca(proizvodjaci.get(0));
		
		try {
			ct.CreateTableIzvestaj(stavkeOdabranogProizvodajca);
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lbIzvestaj = new JLabel(" Ukupna prodaja svih lekova odabranog proizvodjaca");
		lbIzvestaj.setBorder(BorderFactory.createMatteBorder(0,4,0,0,new Color(56, 97, 76)));
		lbIzvestaj.setFont(new Font("Arial", Font.BOLD, 23));
		cs.insets = new Insets(15, 50, 0, 0);
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 2;
		cs.anchor = GridBagConstraints.FIRST_LINE_START;
		topPanel.add(lbIzvestaj,cs);
		
		JLabel lbProizvodjac = new JLabel("Proizvodjac:");
		cs.insets = new Insets(21, 49, 0, 0);
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		topPanel.add(lbProizvodjac,cs);
		
		JComboBox prLista = new JComboBox(proizvodjaci.toArray());
		prLista.setSelectedIndex(0);
		prLista.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String proiz = (String) prLista.getSelectedItem();
					ArrayList<Stavka> stavkeOdabranogProizvodajca = new ArrayList<Stavka>();
					try {
						stavkeOdabranogProizvodajca = stavkeOdabranogProizvodadjaca(proiz);
					} catch (ClassNotFoundException | IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						topPanel.remove(topPanel.getComponentCount() - 1);
						
						CreateTableIzvestaj ct = new CreateTableIzvestaj();
						ct.CreateTableIzvestaj(stavkeOdabranogProizvodajca);
						topPanel.add(ct,cs);
						ct.setBackground(Color.white);
						ct.setPreferredSize(new Dimension(700,355));
						ct.setVisible(true);
						
						SwingUtilities.updateComponentTreeUI(topPanel);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
			}
		});
		
		cs.insets = new Insets(18, 18, 0, 0);
		cs.gridx = 1;
		cs.gridy = 1;
		topPanel.add(prLista, cs);
		
		cs.insets = new Insets(5, 0, 0, 0);
		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 2;
		cs.weightx = 1.0;
		cs.weighty = 1.0;
		cs.anchor = GridBagConstraints.NORTH;
		topPanel.add(ct,cs);
		ct.setBackground(Color.white);
		ct.setPreferredSize(new Dimension(700,355));
		ct.setVisible(true);
	}
	
	public void prikazLekovaOdabranogApotekara() throws ClassNotFoundException, IOException {
		
		ArrayList<Korisnik> korisnici = readFromFile.readFromFileKor();
		
		ArrayList<String> apotekari = new ArrayList<>();
		
		for (Korisnik k : korisnici) {
			if (k.getTipKorisnika() == TipKorisnika.APOTEKAR && k.getLogickiObrisan() == false) {
				apotekari.add(k.getKorisnickoIme());
			}
		}
		
		prodaja = readFromFile.readFromFileProdaja();

		CreateTableIzvestaj ct = new CreateTableIzvestaj();
		
		ArrayList<Stavka> stavkeOdabranogApotekara = prodaja.getListaSvihProdatihStavkiOdabranogApotekara(apotekari.get(0));
		
		try {
			ct.CreateTableIzvestaj(stavkeOdabranogApotekara);
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lbIzvestaj = new JLabel(" Ukupna prodaja svih lekova koje je odabrani apotekar prodao");
		lbIzvestaj.setBorder(BorderFactory.createMatteBorder(0,4,0,0,new Color(56, 97, 76)));
		lbIzvestaj.setFont(new Font("Arial", Font.BOLD, 23));
		cs.insets = new Insets(15, 40, 0, 0);
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 2;
		cs.anchor = GridBagConstraints.FIRST_LINE_START;
		topPanel.add(lbIzvestaj,cs);
		
		JLabel lbApotekar = new JLabel("Apotekar:");
		cs.insets = new Insets(21, 49, 0, 0);
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		topPanel.add(lbApotekar,cs);
		
		JComboBox prLista = new JComboBox(apotekari.toArray());
		prLista.setSelectedIndex(0);
		
		prLista.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String apot = (String) prLista.getSelectedItem();
					ArrayList<Stavka> stavkeOdabranogApotekara = new ArrayList<Stavka>();
					stavkeOdabranogApotekara = prodaja.getListaSvihProdatihStavkiOdabranogApotekara(apot);
					
					try {
						topPanel.remove(topPanel.getComponentCount() - 1);
						
						CreateTableIzvestaj ct = new CreateTableIzvestaj();
						ct.CreateTableIzvestaj(stavkeOdabranogApotekara);
						topPanel.add(ct,cs);
						ct.setBackground(Color.white);
						ct.setPreferredSize(new Dimension(700,355));
						ct.setVisible(true);
						
						SwingUtilities.updateComponentTreeUI(topPanel);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
			}
		});
		
		cs.insets = new Insets(18, 34, 0, 0);
		cs.gridx = 1;
		cs.gridy = 1;
		topPanel.add(prLista, cs);
		
		cs.insets = new Insets(5, 0, 0, 0);
		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 2;
		cs.weightx = 1.0;
		cs.weighty = 1.0;
		cs.anchor = GridBagConstraints.NORTH;
		topPanel.add(ct,cs);
		ct.setBackground(Color.white);
		ct.setPreferredSize(new Dimension(700,355));
		ct.setVisible(true);
	}
	
	public ArrayList<Stavka> stavkeOdabranogProizvodadjaca(String proizvodjac) throws ClassNotFoundException, IOException {
		prodaja = readFromFile.readFromFileProdaja();
		
		ArrayList<Stavka> stavkeOdabranogProizvodajca = new ArrayList<Stavka>();
		
		for (Stavka s : prodaja.getListaSvihProdatihStavki()) {
			if (s.getLek().getProizvodjac().equals(proizvodjac)) {
				stavkeOdabranogProizvodajca.add(s);
			}				
		}
		
		return stavkeOdabranogProizvodajca;
	}
	
	public String getName() {
		return this.tabName;
	}
}