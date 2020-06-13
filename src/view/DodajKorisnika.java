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

import controlers.MyFocusListener;
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
        MyFocusListener focusListener=new MyFocusListener();
        
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
        tfKorIme.setName("txtKorIme");
        tfKorIme.addFocusListener(focusListener);
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
        tfLoz.setToolTipText("Lozinka bi trebala da sadrzati barem 8 karaktera");
        tfLoz.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
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
	        		System.out.println(error);
	        		for (int i=0; i<size; i++) {
		        		if (tfKorIme.getText().equals( korisnici.get(i).getKorisnickoIme() )){
		        			System.out.println("POSTOJI ISTI");
		        			error = true;
		        			break;
		        		}
	        		}
		        	if (error == false) {
		        		System.out.println("novi");
		        		Korisnik kor = new Korisnik();
		                kor.setKorisnickoIme(tfKorIme.getText());
		                kor.setIme(tfIme.getText());
		                kor.setPrezime(tfPrez.getText());
		                kor.setLozinka(tfLoz.getText());
		                kor.setTipKorisnika((TipKorisnika) tipLista.getSelectedItem());
		                
						writeToFile.writeToFileKor(kor);
		                
		        		JOptionPane.showMessageDialog(DodajKorisnika.this, "Uspešno ste dodali novog korisnika.", "Dodat korisnik", JOptionPane.INFORMATION_MESSAGE);
		                dispose();
		                JOptionPane.showMessageDialog(DodajKorisnika.this, "Molimo ponovo otvorite tab kako biste videli azuriran prikaz tabele.", "Azuriranje prikaza", JOptionPane.INFORMATION_MESSAGE);
		        	} else {
		        		System.out.println(error);
		        		tfKorIme.setText("");
	        			JOptionPane.showMessageDialog(DodajKorisnika.this, "Korisnik sa unetim korisnickim imenom vec postoji.", "Error", JOptionPane.ERROR_MESSAGE);
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
