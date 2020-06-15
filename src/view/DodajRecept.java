package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controlers.MyFocusListener;
import controlers.readFromFile;
import controlers.writeToFile;
import model.Korisnik;
import model.Lek;
import model.TipKorisnika;

public class DodajRecept extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -159924695243776854L;
	private JTextField tfSifra;
	private JTextField tfId;
	private JTextField tfJmbg;
	private JTextField tfLek;
	private JTextField tfKol;
	private JTextField tfDate;
    private JLabel lbSifra;
    private JLabel lbId;
    private JLabel lbJmbg;
    private JLabel lbLek;
    private JLabel lbKol;
    private JLabel lbDate;
    private JLabel lbText;
    public JButton btnAdd;
    private JButton btnCancel;
    private JButton btnAddLek;
    private JTextArea content;
    
    private int count = 1;
    
 
    public DodajRecept(Frame parent) throws ClassNotFoundException, IOException {
        super(parent, "Dodaj Lek", true);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        Color peach = new Color(249, 229, 222);
		Color mint = new Color(140,208,172);
 
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        lbText = new JLabel("Novi recept:");
        lbText.setFont(new Font("Arial", Font.BOLD, 25));
        cs.insets = new Insets(0, 0, 15, 0);
        cs.gridx = 0;
        cs.gridy = 0;
        cs.weightx = 1;
        cs.gridwidth = 3;
        panel.add(lbText, cs);
 
        lbSifra = new JLabel("Sifra:  ");
        lbSifra.setFont(new Font("Arial", Font.PLAIN, 14));
        lbSifra.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(20, 0, 10, 0);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbSifra, cs);
 
        tfSifra = new JTextField(20);
        tfSifra.setFont(new Font("Arial", Font.PLAIN, 14));
        tfSifra.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        tfSifra.setName("txtKorIme");
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(tfSifra, cs);
 
        lbId = new JLabel("ID lekara:  ");
        lbId.setFont(new Font("Arial", Font.PLAIN, 14));
        lbId.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(20, 0, 10, 0);
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lbId, cs);
 
        tfId = new JTextField(20);
        tfId.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(tfId, cs);
        
        lbJmbg = new JLabel("JMBG pacijenta:  ");
        lbJmbg.setFont(new Font("Arial", Font.PLAIN, 14));
        lbJmbg.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(20, 0, 10, 0);
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        panel.add(lbJmbg, cs);
 
        tfJmbg = new JTextField(20);
        tfJmbg.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        panel.add(tfJmbg, cs);
        
        /*
        tfCena.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_PERIOD) ) {
					getToolkit().beep();
					tfCena.setToolTipText("Polje prima samo brojeve");
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});*/
        
        lbDate = new JLabel("Datum i vreme izdavanja:  ");
        lbDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lbDate.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(20, 0, 7, 0);
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        panel.add(lbDate, cs);
 
        tfDate = new JTextField(20);
        tfDate.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 2;
        panel.add(tfDate, cs);
        
        lbLek = new JLabel("Lek:  ");
        lbLek.setFont(new Font("Arial", Font.PLAIN, 14));
        lbLek.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(20, 0, 8, 0);
        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 1;
        panel.add(lbLek, cs);
        
        ArrayList<Lek> lekovi = readFromFile.readFromFileLek();
        JComboBox recLista = new JComboBox(lekovi.toArray());
        recLista.setSelectedIndex(0);
        cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth = 2;
        panel.add(recLista, cs);
        
        lbKol = new JLabel("Kolicina izabranog leka:  ");
        lbKol.setFont(new Font("Arial", Font.PLAIN, 14));
        lbKol.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(20, 0, 10, 0);
        cs.gridx = 0;
        cs.gridy = 6;
        cs.gridwidth = 1;
        panel.add(lbKol, cs);
        
        tfKol = new JTextField(20);
        tfKol.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(20, 0, 10, 0);
        cs.gridx = 1;
        cs.gridy = 6;
        cs.gridwidth = 2;
        panel.add(tfKol, cs);
        
        btnAddLek = new JButton("Dodaj lek");
        btnAddLek.setToolTipText("Moguce je dodati najvise 3 leka");
        btnAddLek.setPreferredSize(new Dimension(50,20));
        btnAddLek.setBackground(peach);
        btnAddLek.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(count < 4) {
        			content.append("\n"+ " " +count + ". " + "dodato");
        			count ++;
        		}
        	}
        });
        cs.insets = new Insets(10, 10, 10, 0);
        cs.gridx = 2;
        cs.gridy = 7;
        cs.gridwidth = 1;
        panel.add(btnAddLek, cs);
        
        content = new JTextArea(" Dodati lekovi: ");
        content.setEditable(false);
        content.setFont(new Font("Arial", Font.PLAIN, 13));
        content.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, new Color (111, 179, 143)));
        content.setForeground(new Color (77, 77, 77));
        content.setBackground(new Color(202, 232, 216));
        cs.insets = new Insets(10, 0, 10, 10);
        cs.gridx = 0;
        cs.gridy = 7;
        cs.gridwidth = 2;
        panel.add(content, cs);
        
        panel.setBackground(new Color(164, 218, 189));
        panel.setPreferredSize(new Dimension(370,463));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
 
        btnAdd = new JButton("Add");
        btnAdd.setPreferredSize(new Dimension(90,30));
        btnAdd.setBackground(peach);
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
				try {
					ArrayList<Lek> lekovi = readFromFile.readFromFileLek();
	        		int size = lekovi.size();
	        		boolean error = false;
	        		System.out.println(error);
	        		for (int i=0; i<size; i++) {
		        		if (tfSifra.getText().equals( lekovi.get(i).getSifra() )){
		        			error = true;
		        			break;
		        		}
	        		}
	        		if( !(tfSifra.getText().equals("") || tfId.getText().equals("") || tfJmbg.getText().equals("") || tfLek.getText().equals("")) ) {
			        	if (error == false) {
			        		Lek lek = new Lek();
			        		lek.setSifra(tfSifra.getText());
			        		lek.setIme(tfId.getText());
			        		lek.setProizvodjac(tfJmbg.getText());
			        		if (recLista.getSelectedItem().equals("Da")) {
			        			lek.setIzdajeSeNaRecept(true);
			        		}
			        		if (recLista.getSelectedItem().equals("Ne")) {
			        			lek.setIzdajeSeNaRecept(false);
			        		}
			                
							writeToFile.writeToFileLek(lek);
			                
			        		JOptionPane.showMessageDialog(DodajRecept.this, "Uspešno ste dodali novi lek.", "Dodat lek", JOptionPane.INFORMATION_MESSAGE);
			                dispose();
			        	} else {
			        		System.out.println(error);
			        		tfSifra.setText("");
		        			JOptionPane.showMessageDialog(DodajRecept.this, "Lek sa unetom sifrom vec postoji!", "Error", JOptionPane.ERROR_MESSAGE);
			        	}
	        		} else {
	        			JOptionPane.showMessageDialog(DodajRecept.this, "Sva polja moraju biti popunjena!", "Error", JOptionPane.ERROR_MESSAGE);
	        		}
	        		
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(90,30));
        btnCancel.setBackground(peach);
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        
        cs.insets = new Insets(20, 25, 20, 25);
        cs.gridx = 0;
        cs.gridy = 0;
        cs.weightx = 0;
        cs.gridwidth = 1;
        bottomPanel.add(btnAdd,cs);
        
        
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        bottomPanel.add(btnCancel,cs);
        
        bottomPanel.setBackground(mint);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
 
    public String getSifra() {
        return tfSifra.getText().trim();
    }
 
    public String getIme() {
        return tfId.getText().trim();
    }
    
    public String getProiz() {
        return tfJmbg.getText().trim();
    }
    
}
