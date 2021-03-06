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
import controlers.ObrisiIzTabele;
import model.Korisnik;
import model.TipKorisnika;

public class TabLekovi extends JPanel{

	private static final long serialVersionUID = -853959313304993533L;
	
	Color peach = new Color(249, 229, 222);
	Color mint = new Color(140,208,172);
	
	JFrame frame = new JFrame();
	
	private String tabName;
	
	public TabLekovi(String text, Korisnik logedOn) throws ClassNotFoundException, IOException {
		CreateTableLek ct = new CreateTableLek();
		ct.CreateTableLek(logedOn);
		
		this.tabName = text;
		
		this.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		add(BorderLayout.PAGE_START, topPanel);
		
		topPanel.setLayout(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		topPanel.setBorder(new LineBorder(Color.white, 0));
		topPanel.setBackground(Color.white);
		this.add(topPanel, BorderLayout.CENTER);
	  
		JButton addDrug = new JButton("Dodaj lek");
		addDrug.setBackground(peach);
		addDrug.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		addDrug.setPreferredSize(new Dimension(150,26));
		addDrug.addMouseListener(new MyMouseListener(addDrug));
		addDrug.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFrame frame= new JFrame();
					DodajLek dkDlg = new DodajLek(frame);
					dkDlg.setVisible(true);
				
					ct.TableUpdate(logedOn);
					
					System.out.println("Poslednji dodat "+ ct.model.getValueAt(ct.model.getRowCount()-1, 0));
					
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
	    
	    JButton delDrug = new JButton("Obrisi lek");
	    delDrug.setBackground(peach);
	    delDrug.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
	    delDrug.setPreferredSize(new Dimension(150,26));
	    delDrug.addMouseListener(new MyMouseListener(delDrug));
	    
	    delDrug.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
	    		try {
	    			if (ct.tbl.getSelectedRow() != -1 ) {
		    			ObrisiIzTabele.ObrisiLek(ct.tbl.getSelectedRow(), (String) ct.tbl.getValueAt(ct.tbl.getSelectedRow(), 0));
						ct.TableUpdate(logedOn);
	    			}
					
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
		});
	    cs.insets = new Insets(20, 20, 0, 0);
	    cs.gridx = 1;
		cs.gridy = 0;
	    topPanel.add(delDrug, cs);
	    
	    
	    JButton editDrug = new JButton("Izmeni lek");
	    editDrug.setBackground(peach);
	    editDrug.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
	    editDrug.setPreferredSize(new Dimension(150,26));
	    editDrug.addMouseListener(new MyMouseListener(editDrug));
	    
	    editDrug.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
	    		
				try {
					if(ct.tbl.getSelectedRow() != -1) {
						JFrame frame= new JFrame();
						IzmeniLek dkDlg;
						dkDlg = new IzmeniLek(frame, (String) ct.tbl.getValueAt(ct.tbl.getSelectedRow(), 0));
						dkDlg.setVisible(true);
					
						ct.TableUpdate(logedOn);
					}
					
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	    cs.insets = new Insets(20, 30, 0, 20);
	    cs.gridx = 2;
		cs.gridy = 0;
		cs.anchor = GridBagConstraints.FIRST_LINE_START;
	    topPanel.add(editDrug, cs);
	    
	    
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
		
		if(logedOn.getTipKorisnika() == TipKorisnika.LEKAR) {
			addDrug.setVisible(false);
			delDrug.setVisible(false);
			editDrug.setVisible(false);
			ct.setPreferredSize(new Dimension(700,440));
			ct.tbl.setPreferredScrollableViewportSize(new Dimension(650,330));
		}
	    
	}
	
	public String getName() {
		return this.tabName;
	}
}