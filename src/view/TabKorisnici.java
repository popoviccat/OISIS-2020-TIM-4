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
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import controlers.MyMouseListener;

public class TabKorisnici extends JPanel {

	private static final long serialVersionUID = 7445755320045782268L;

	Color peach = new Color(249, 229, 222);
	Color mint = new Color(140,208,172);
	
	JFrame frame = new JFrame();
	
	private String bookName;
	
	public TabKorisnici(String text) throws ClassNotFoundException, IOException {
		CreateTableKorisnik ct = new CreateTableKorisnik();
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
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DodajKorisnika dkDlg;
				try {
					dkDlg = new DodajKorisnika(frame);
				
                dkDlg.setVisible(true);
				//ct.model.addRow(new Object[]{"Novi Korisnik", "Novi", "Korisnik", "Novi Korisnik"});
                dkDlg.btnAdd.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				//ct.data = { { "pera", "Petar", "Petrovic", "Apotekar" }};
        			}
                });
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
				// check for selected row first
		        if (ct.tbl.getSelectedRow() != -1) {
		            // remove selected row from the model
		        	Object data = ct.tbl.getValueAt(ct.tbl.getSelectedRow(), 1);
		        	int rowCount = ct.model.getRowCount();
		        	for (int i = 0; i < rowCount; i++) {
		        		
		        			if (data == ct.model.getValueAt(i, 1)) {
		        				
		        				JFrame frame= new JFrame();
		        				int code=JOptionPane.showConfirmDialog(frame, "Da li ste sigurni da zelite da obrisete korisnika " + "\n" +
		        						ct.model.getValueAt(i, 1) + " " +
		        						ct.model.getValueAt(i, 2) + "?",
		        						"Obrisi korisnika?",JOptionPane.YES_NO_OPTION);
		        				 
		        				if (code == JOptionPane.YES_OPTION){
		        					ct.model.removeRow(i);
			        				JOptionPane.showMessageDialog(null, "Korisnik je obrisan.");
			        				ct.model.fireTableDataChanged();
			        				break;
			        				
		        				} else {
		        					frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		        				}
		        			}
		        	}
		        }
			}
		});
	    
	    cs.insets = new Insets(20, 20, 0, 20);
	    cs.gridx = 1;
		cs.gridy = 0;
	    topPanel.add(delUser, cs);
	    
		JButton saveBtn = new JButton("Sacuvaj");
		saveBtn.setBackground(peach);
		saveBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		saveBtn.setPreferredSize(new Dimension(100,26));
		saveBtn.addMouseListener(new MyMouseListener(saveBtn));
		saveBtn.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
	    		ct.model.fireTableDataChanged();
	    	}  
		});
		cs.insets = new Insets(20, 20, 25, 40);
		cs.gridx = 2;
		cs.gridy = 2;
		cs.anchor = GridBagConstraints.LAST_LINE_END;
	    topPanel.add(saveBtn, cs);
		
		
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