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
import java.util.Collections;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.main;
import controlers.readFromFile;
import model.Lek;

public class DodajLekUKorpu extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -159924695243776854L;
	private JComboBox<String> cbSifreLekova;
	private JTextField tfKolicina;
	private JLabel lbText;
    private JLabel lbSifra;
    private JLabel lbKolicina;
    public JButton btnAdd;
    private JButton btnCancel;
    
 
    public DodajLekUKorpu(Frame parent, TabKorpa tabKorpa) throws ClassNotFoundException, IOException {
        super(parent, "Dodaj Lek u korpu", true);
        
        ArrayList<Lek> lekovi = readFromFile.readFromFileLek();
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        Color peach = new Color(249, 229, 222);
		Color mint = new Color(140,208,172);
 
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        lbText = new JLabel("Unesite sifru i kolicinu:");
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
        cs.insets = new Insets(20, 0, 20, 0);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbSifra, cs);
 
        Vector<String> sifreLekova = new Vector<>();
        
        for (Lek l : lekovi) {
        	sifreLekova.add(l.getSifra());
        }
        
        Collections.sort(sifreLekova);
        
        lbKolicina = new JLabel("Kolicina:  ");
        lbKolicina.setFont(new Font("Arial", Font.PLAIN, 14));
        lbKolicina.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(20, 0, 20, 0);
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lbKolicina, cs);
 
        tfKolicina = new JTextField(100);
        tfKolicina.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        tfKolicina.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_PERIOD) ) {
					getToolkit().beep();
					tfKolicina.setToolTipText("Polje prima samo brojeve");
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
		});
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(tfKolicina, cs);
        
        panel.setBackground(new Color(164, 218, 189));
        panel.setPreferredSize(new Dimension(360,360));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
 
        btnAdd = new JButton("Add");
        btnAdd.setPreferredSize(new Dimension(90,30));
        btnAdd.setBackground(peach);
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (getSifra().isEmpty()) {
        			JOptionPane.showMessageDialog(DodajLekUKorpu.this, "Morate uneti sifru leka!", "Error", JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        		
        		if (tfKolicina.getText().isEmpty()) {
        			JOptionPane.showMessageDialog(DodajLekUKorpu.this, "Morate uneti kolicinu!", "Error", JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        		
        		if (getKolicina() < 1) {
        			JOptionPane.showMessageDialog(DodajLekUKorpu.this, "Najmanja dozvoljena kolicina je 1!", "Error", JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        		
        		for (Lek l : lekovi) {
        			if (l.getSifra().equals(getSifra())) {
        				
        				if (l.getIzdajeSeNaRecept()) {
        					JOptionPane.showMessageDialog(DodajLekUKorpu.this, 
        							"Odabrani lek se moze prodati samo uz recept!", "Error", JOptionPane.ERROR_MESSAGE);
        					return;
        				}
        				
        				try {
							main.getInstance().getKorpa().dodajLekUKorpu(l, getKolicina());
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        				
        				try {
							tabKorpa.initTabKorpa();
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        				dispose();
        				return;
        			}
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
        return (String) cbSifreLekova.getSelectedItem();
    }
    
    public int getKolicina() {
        return Integer.parseInt( tfKolicina.getText());
    }
}
