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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlers.readFromFile;
import controlers.writeToFile;
import model.Lek;

public class IzmeniLek extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -159924695243776854L;
	private JTextField tfSifra;
	private JTextField tfIme;
	private JTextField tfProiz;
	private JTextField tfCena;
    private JLabel lbSifra;
    private JLabel lbIme;
    private JLabel lbProiz;
    private JLabel lbRec;
    private JLabel lbText;
    private JLabel lbCena;
    public JButton btnSave;
    private JButton btnCancel;
    private Lek selLek;
 
    public IzmeniLek(Frame parent, String selSifra) throws ClassNotFoundException, IOException {
        super(parent, "Dodaj Lek", true);
        selLek = getSelectedLek(selSifra);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        Color peach = new Color(249, 229, 222);
		Color mint = new Color(140,208,172);
 
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        lbText = new JLabel("Izmeni lek:");
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
 
        tfSifra = new JTextField(20);
        tfSifra.setFont(new Font("Arial", Font.PLAIN, 14));
        tfSifra.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        tfSifra.setName("txtSifra");
        tfSifra.setText(selLek.getSifra());
        tfSifra.setEditable(false);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(tfSifra, cs);
 
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
        tfIme.setText(selLek.getIme());
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(tfIme, cs);
        
        lbProiz = new JLabel("Proizvodjac:  ");
        lbProiz.setFont(new Font("Arial", Font.PLAIN, 14));
        lbProiz.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(20, 0, 10, 0);
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        panel.add(lbProiz, cs);
 
        tfProiz = new JTextField(20);
        tfProiz.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        tfProiz.setText(selLek.getProizvodjac());
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        panel.add(tfProiz, cs);
        
        lbCena = new JLabel("Cena:  ");
        lbCena.setFont(new Font("Arial", Font.PLAIN, 14));
        lbCena.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(20, 0, 20, 0);
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        panel.add(lbCena, cs);
 
        tfCena = new JTextField(100);
        tfCena.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        tfCena.setText(String.valueOf( selLek.getCena() ));
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
		});
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 2;
        panel.add(tfCena, cs);
        
        lbRec = new JLabel("Izdaje se na recept:  ");
        lbRec.setFont(new Font("Arial", Font.PLAIN, 14));
        lbRec.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(10, 0, 0, 0);
        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 1;
        panel.add(lbRec, cs);
        
        Vector<String> booleanVector = new Vector<String>();
        booleanVector.add("Da");
        booleanVector.add("Ne");
        JComboBox recLista = new JComboBox(booleanVector);
        if (selLek.getIzdajeSeNaRecept() == true) {
        	recLista.setSelectedIndex(0);
        } else {
        	recLista.setSelectedIndex(1);
        }
        cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth = 2;
        panel.add(recLista, cs);
        
        panel.setBackground(new Color(164, 218, 189));
        panel.setPreferredSize(new Dimension(360,360));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
 
        btnSave = new JButton("Save");
        btnSave.setPreferredSize(new Dimension(90,30));
        btnSave.setBackground(peach);
        btnSave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
				try {
					ArrayList<Lek> lekovi = readFromFile.readFromFileLek();
	        		
	        		if( !(tfSifra.getText().equals("") || tfIme.getText().equals("") || tfProiz.getText().equals("") || tfCena.getText().equals("")) ) {
		        	
		        		for(int i=0; i<lekovi.size(); i++) {
		        			if (selSifra.equals((String)lekovi.get(i).getSifra())) {
				        		lekovi.get(i).setIme(tfIme.getText());
				        		lekovi.get(i).setProizvodjac(tfProiz.getText());
				        		lekovi.get(i).setCena(Float.parseFloat( tfCena.getText()) );
				        		if (recLista.getSelectedItem().equals("Da")) {
				        			lekovi.get(i).setIzdajeSeNaRecept(true);
				        		}
				        		if (recLista.getSelectedItem().equals("Ne")) {
				        			lekovi.get(i).setIzdajeSeNaRecept(false);
				        		}
				        		break;
		        			}
		        		}
		        		
						writeToFile.updateDatabaseLek(lekovi);
		                
		        		JOptionPane.showMessageDialog(IzmeniLek.this, "Lek je uspesno izmenjen.", "Izmenjen lek", JOptionPane.INFORMATION_MESSAGE);
		                dispose();
		        	
		        	
	        		} else {
	        			JOptionPane.showMessageDialog(IzmeniLek.this, "Sva polja moraju biti popunjena!", "Error", JOptionPane.ERROR_MESSAGE);
	        			tfSifra.setText(selLek.getSifra());
	        			tfIme.setText(selLek.getIme());
	        			tfProiz.setText(selLek.getProizvodjac());
	        			tfCena.setText(String.valueOf( selLek.getCena() ));
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
        bottomPanel.add(btnSave,cs);
        
        
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
    
    private Lek getSelectedLek(String selSifra) throws ClassNotFoundException, IOException {
		
		ArrayList<Lek> lekovi = readFromFile.readFromFileLek();
		for(int i=0; i<lekovi.size(); i++) {
			if (selSifra.equals((String)lekovi.get(i).getSifra())) {
				selLek = new Lek(lekovi.get(i).getSifra(),
						lekovi.get(i).getIme(), 
						lekovi.get(i).getProizvodjac(),  
						lekovi.get(i).getIzdajeSeNaRecept(),
						lekovi.get(i).getCena(),
						lekovi.get(i).getLogickiObrisan());
				break;
			}
		}
		return selLek;
	}
    
}
