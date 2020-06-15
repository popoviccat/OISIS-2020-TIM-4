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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controlers.MyMouseListener;
import controlers.ObrisiIzTabele;

public class TabKorisnici extends JPanel {

	private static final long serialVersionUID = 7445755320045782268L;

	Color peach = new Color(249, 229, 222);
	Color mint = new Color(140,208,172);
	
	JFrame frame = new JFrame();
	
	private String tabName;
	
	public TabKorisnici(String text) throws ClassNotFoundException, IOException {
		CreateTableKorisnik ct = new CreateTableKorisnik();
		ct.CreateTableKorisnik();
		
		this.tabName = text;
		
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
		addUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFrame frame= new JFrame();
					DodajKorisnika dkDlg = new DodajKorisnika(frame);
					dkDlg.setVisible(true);
				
					ct.TableUpdate();
					
					System.out.println("Poslednji dodat "+ ct.model.getValueAt(ct.model.getRowCount()-1, 0));
					
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
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
	    
	    delUser.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
	    		try {
	    			ObrisiIzTabele.ObrisiKor(ct.tbl.getSelectedRow(), (String) ct.tbl.getValueAt(ct.tbl.getSelectedRow(), 0));
					ct.TableUpdate();
					
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
		});
	    
	    cs.insets = new Insets(20, 20, 0, 20);
	    cs.gridx = 1;
		cs.gridy = 0;
	    topPanel.add(delUser, cs);
	    
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
		
		/*JButton saveBtn = new JButton("Sacuvaj");
		saveBtn.setBackground(peach);
		saveBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		saveBtn.setPreferredSize(new Dimension(100,26));
		saveBtn.addMouseListener(new MyMouseListener(saveBtn));
		saveBtn.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
	    		CreateTableKorisnik updated;
				try {
					updated = new CreateTableKorisnik();
				
	    			cs.insets = new Insets(0, 0, 0, 0);
	    			cs.gridx = 0;
	    			cs.gridy = 1;
	    			cs.gridwidth = 3;
	    			cs.weightx = 1.0;
	    			cs.weighty = 1.0;
	    			cs.anchor = GridBagConstraints.PAGE_END;
					topPanel.remove(ct);
					topPanel.add(updated, cs, 1);
					updated.setBackground(Color.CYAN);
					updated.setPreferredSize(new Dimension(700,354));
					updated.setVisible(true);
				
		    		topPanel.revalidate();
		    		topPanel.repaint();
					} catch (ClassNotFoundException | IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
	    		ArrayList<Korisnik> korisnici;
	    		try {
					korisnici = readFromFile.readFromFileKor();
					System.out.println(korisnici.get(korisnici.size()-1));
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}  
		});
		cs.insets = new Insets(20, 20, 25, 40);
		cs.gridx = 2;
		cs.gridy = 2;
		cs.anchor = GridBagConstraints.LAST_LINE_END;
	    topPanel.add(saveBtn, cs);*/
	    
	}
	
	public String getName() {
		return this.tabName;
	}
}