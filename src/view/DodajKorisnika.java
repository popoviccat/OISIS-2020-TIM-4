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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controlers.readFromFile;
import controlers.writeToFile;
import model.Korisnik;
import model.TipKorisnika;

public class DodajKorisnika extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -159924695243776854L;
	private JTextField tfKorIme;
	private JTextField tfIme;
	private JTextField tfPrez;
	private JTextField tfLoz;
    private JLabel lbKorIme;
    private JLabel lbIme;
    private JLabel lbPrez;
    private JLabel lbTip;
    private JLabel lbText;
    private JLabel lbLoz;
    public JButton btnAdd;
    private JButton btnCancel;
    
 
    public DodajKorisnika(Frame parent) throws ClassNotFoundException, IOException {
        super(parent, "Dodaj Korisnika", true);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        Color peach = new Color(249, 229, 222);
		Color mint = new Color(140,208,172);
 
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        lbText = new JLabel("Novi korisnik:");
        lbText.setFont(new Font("Arial", Font.BOLD, 25));
        cs.insets = new Insets(0, 0, 15, 0);
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 3;
        panel.add(lbText, cs);
 
        lbKorIme = new JLabel("Korisničko ime:  ");
        lbKorIme.setFont(new Font("Arial", Font.PLAIN, 14));
        lbKorIme.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(20, 0, 20, 0);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbKorIme, cs);
 
        tfKorIme = new JTextField(20);
        tfKorIme.setFont(new Font("Arial", Font.PLAIN, 14));
        tfKorIme.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(tfKorIme, cs);
 
        lbIme = new JLabel("Ime:  ");
        lbIme.setFont(new Font("Arial", Font.PLAIN, 14));
        lbIme.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(10, 0, 10, 0);
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lbIme, cs);
 
        tfIme = new JTextField(20);
        tfIme.setFont(new Font("Arial", Font.PLAIN, 14));
        tfIme.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(tfIme, cs);
        
        lbPrez = new JLabel("Prezime:  ");
        lbPrez.setFont(new Font("Arial", Font.PLAIN, 14));
        lbPrez.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(20, 0, 10, 0);
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        panel.add(lbPrez, cs);
 
        tfPrez = new JTextField(20);
        tfPrez.setFont(new Font("Arial", Font.PLAIN, 14));
        tfPrez.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        panel.add(tfPrez, cs);
        
        lbLoz = new JLabel("Lozinka:  ");
        lbLoz.setFont(new Font("Arial", Font.PLAIN, 14));
        lbLoz.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(20, 0, 20, 0);
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        panel.add(lbLoz, cs);
 
        tfLoz = new JTextField(20);
        tfLoz.setFont(new Font("Arial", Font.PLAIN, 14));
        tfLoz.setToolTipText("Lozinka mora da sadrzati barem 4 karaktera");
        tfLoz.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        tfLoz.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				int lenght = tfLoz.getText().length();
				 if(lenght < 4) {
					 JOptionPane.showMessageDialog(null,
					          "Warning: Lozinka treba da sadrzi najmanje 4 karaktera", "Warning Message",
					          JOptionPane.WARNING_MESSAGE);
				} 
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 2;
        panel.add(tfLoz, cs);
        
        lbTip = new JLabel("Tip korisnika:  ");
        lbTip.setFont(new Font("Arial", Font.PLAIN, 14));
        lbTip.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(10, 0, 0, 0);
        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 1;
        panel.add(lbTip, cs);
        
        TipKorisnika[] tip = {TipKorisnika.APOTEKAR,TipKorisnika.LEKAR};
        JComboBox tipLista = new JComboBox(tip);
        cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth = 2;
        panel.add(tipLista, cs);
        
        panel.setBorder(new LineBorder(Color.gray, 0));
        panel.setBackground(new Color(164, 218, 189));
        panel.setPreferredSize(new Dimension(310,351));
 
        btnAdd = new JButton("Add");
        btnAdd.setPreferredSize(new Dimension(90,30));
        btnAdd.setBackground(peach);
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
				try {
					ArrayList<Korisnik> korisnici;
					korisnici = readFromFile.readFromFileKor();
	        		int size = korisnici.size();
	        		boolean error = false;
	        		for (int i=0; i<size; i++) {
		        		if (tfKorIme.getText().equals( korisnici.get(i).getKorisnickoIme() )){
		        			error = true;
		        			break;
		        		}
	        		}
	        		if (tfLoz.getText().length() < 4) {
	        			JOptionPane.showMessageDialog(DodajKorisnika.this, "Lozinka mora sadrzati najmanje 4 karaktera!", "Error", JOptionPane.ERROR_MESSAGE);
	        			
	        		}else {
		        		if( !(tfKorIme.getText().equals("") || tfIme.getText().equals("") || tfPrez.getText().equals("") || tfLoz.getText().equals("")) ) {
				        	if (error == false) {
				        		
				        		Korisnik kor = new Korisnik();
				                kor.setKorisnickoIme(tfKorIme.getText());
				                kor.setIme(tfIme.getText());
				                kor.setPrezime(tfPrez.getText());
				                kor.setLozinka(tfLoz.getText());
				                kor.setTipKorisnika((TipKorisnika) tipLista.getSelectedItem());
				                
								writeToFile.writeToFileKor(kor);
				                
				        		JOptionPane.showMessageDialog(DodajKorisnika.this, "Uspešno ste dodali novog korisnika.", "Dodat korisnik", JOptionPane.INFORMATION_MESSAGE);
				                dispose();
				        	} else {
				        		tfKorIme.setText("");
			        			JOptionPane.showMessageDialog(DodajKorisnika.this, "Korisnik sa unetim korisnickim imenom vec postoji.", "Error", JOptionPane.ERROR_MESSAGE);
				        	}
		        		} else {
		        			JOptionPane.showMessageDialog(DodajKorisnika.this, "Sva polja moraju biti popunjena!", "Error", JOptionPane.ERROR_MESSAGE);
		        		}
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
        
        JPanel bp = new JPanel(new GridBagLayout());
        
        cs.insets = new Insets(20, 25, 20, 25);
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        bp.add(btnAdd,cs);
        
        
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        bp.add(btnCancel,cs);
        
        bp.setBackground(mint);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
 
    public String getKorIme() {
        return tfKorIme.getText().trim();
    }
 
    public String getIme() {
        return tfIme.getText().trim();
    }
    
    public String getPrez() {
        return tfPrez.getText().trim();
    }
    
    
}
