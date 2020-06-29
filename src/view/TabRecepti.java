package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controlers.MyMouseListener;
import model.Korisnik;
import model.TipKorisnika;

public class TabRecepti extends JPanel{
	
	private static final long serialVersionUID = -6600124705287732081L;
	
	Color peach = new Color(249, 229, 222);
	Color mint = new Color(140,208,172);
	
	JFrame frame = new JFrame();
	
	private String tabName;
	
	public TabRecepti(String text, Korisnik logedOn) throws ClassNotFoundException, IOException {
		CreateTableRecept ct = new CreateTableRecept();
		ct.CreateTableRecept();
		
		this.tabName = text;
		
		this.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		add(BorderLayout.PAGE_START, topPanel);
		
		topPanel.setLayout(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		topPanel.setBorder(new LineBorder(Color.white, 0));
		topPanel.setBackground(Color.white);
		this.add(topPanel, BorderLayout.CENTER);
	  
		JButton addDrug = new JButton("Dodaj recept");
		addDrug.setBackground(peach);
		addDrug.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		addDrug.setPreferredSize(new Dimension(150,26));
		addDrug.addMouseListener(new MyMouseListener(addDrug));
		addDrug.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFrame frame= new JFrame();
					DodajRecept dkDlg = new DodajRecept(frame, logedOn);
					dkDlg.setVisible(true);
				
					ct.TableUpdate();
					
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		addDrug.setVisible(true);
		cs.insets = new Insets(20, 50, 0, 10);
	    cs.gridx = 0;
		cs.gridy = 0;
	    topPanel.add(addDrug, cs);
	    
		cs.insets = new Insets(10, 0, 0, 0);
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 3;
		cs.weightx = 1.0;
		cs.weighty = 1.0;
		cs.anchor = GridBagConstraints.NORTH;
		topPanel.add(ct,cs);
		ct.setBackground(Color.white);
		ct.setPreferredSize(new Dimension(700,380));
		ct.setVisible(true);
		
		if(logedOn.getTipKorisnika() == TipKorisnika.APOTEKAR || logedOn.getTipKorisnika() == TipKorisnika.ADMINISTRATOR) {
			addDrug.setVisible(false);
			ct.setPreferredSize(new Dimension(700,440));
			ct.tbl.setPreferredScrollableViewportSize(new Dimension(650,330));
		}
	    
	}
	
	public String getName() {
		return this.tabName;
	}
}