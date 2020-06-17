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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;

import controlers.MyFocusListener;
import controlers.readFromFile;
import controlers.writeToFile;
import model.Korisnik;
import model.Lek;
import model.Recept;
import model.TipKorisnika;

public class DodajRecept extends JDialog{
	
	private static final long serialVersionUID = -159924695243776854L;
	
	private JTextField tfSifra;
	private JTextField tfId;
	private JTextField tfJmbg;
	private JTextField tfKol;
	private JTextField tfDate;
    private JLabel lbSifra;
    private JLabel lbId;
    private JLabel lbJmbg;
    private JLabel lbLek;
    private JLabel lbKol;
    private JLabel lbDate;
    private JLabel lbText;
    private JLabel lbLekovi;
    public JButton btnAdd;
    private JButton btnCancel;
    private JButton btnAddLek;
    public JButton btnDltLek;
    private JList content;
    
    private SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy ';' HH:mm");
    private ArrayList<Lek> dodatiLek = new ArrayList<Lek>();
    private ArrayList<Integer> dodatiKol = new ArrayList<Integer>();
    private DefaultListModel listModel;
    private int count = 1;
    private int ID;
    
 
    public DodajRecept(Frame parent,  Korisnik logedOn) throws ClassNotFoundException, IOException {
        super(parent, "Dodaj Recept", true);
        
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
        tfSifra.setEditable(false);
        tfSifra.setText(String.valueOf( generateID() ));
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
        tfId.setText(logedOn.getKorisnickoIme());
        tfId.setEditable(false);
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
        tfJmbg.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				String num = tfJmbg.getText();
				int lenght = num.length();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE ) ) {
					getToolkit().beep();
					tfJmbg.setToolTipText("Polje prima samo niz od 13 brojeva");
					e.consume();
				} else if(lenght > 12) {
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
        cs.gridy = 3;
        cs.gridwidth = 2;
        panel.add(tfJmbg, cs);
        
        lbDate = new JLabel("Datum i vreme izdavanja:  ");
        lbDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lbDate.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cs.insets = new Insets(20, 0, 7, 0);
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        panel.add(lbDate, cs);
 
        Date date = new Date(System.currentTimeMillis());
        tfDate = new JTextField(20);
        tfDate.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        tfDate.setText(formatter.format(date));
        tfDate.setEditable(false);
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
        ArrayList<Lek> lekoviNaRec = new ArrayList<Lek>();
		for (int i=0; i<lekovi.size(); i++) {
			if(lekovi.get(i).getIzdajeSeNaRecept() == true) {
				lekoviNaRec.add(lekovi.get(i));
			}
		}
        JComboBox recLista = new JComboBox(lekoviNaRec.toArray());
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
        tfKol.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE ) ) {
					getToolkit().beep();
					tfKol.setToolTipText("Polje prima samo brojeve");
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
        		if(tfKol.getText().equals("")) {
        			JFrame info = new JFrame();
        			JOptionPane.showMessageDialog(info, "Molimo unesite kolicinu za odabrani lek.", "Warning", JOptionPane.WARNING_MESSAGE);
        		} else {
	        		if(count == 1) {
	        			listModel.clear();
	        			listModel.addElement(" " + "+ " + recLista.getSelectedItem() + "[" + tfKol.getText() + "]");
	        			dodatiLek.add((Lek) recLista.getSelectedItem());
	        			dodatiKol.add(Integer.valueOf(tfKol.getText()));
	        			
						tfKol.setText("");
		        		count ++;
	        		} else {
		        		if(count != 1 && count < 4) {
		        			
			        		listModel.addElement(" " + "+ " + recLista.getSelectedItem() + "[" + tfKol.getText() + "]");
			        		dodatiLek.add((Lek) recLista.getSelectedItem());
			        		dodatiKol.add(Integer.valueOf(tfKol.getText()));
			        		
							tfKol.setText("");
				        	count ++;
		        		}
	        		}
        		}
        	}
        });
        cs.insets = new Insets(10, 10, 10, 0);
        cs.gridx = 2;
        cs.gridy = 7;
        cs.gridwidth = 1;
        cs.gridheight = 2;
        panel.add(btnAddLek, cs);
        
        btnDltLek = new JButton("Obrisi lek");
        btnDltLek.setToolTipText("Moguce je dodati najvise 3 leka");
        btnDltLek.setPreferredSize(new Dimension(50,20));
        btnDltLek.setBackground(peach);
        btnDltLek.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(count < 5) {
        			if(content.getSelectedIndex() != -1) {
	        			dodatiLek.remove(content.getSelectedIndex());
	        			dodatiKol.remove(content.getSelectedIndex());
	        			listModel.remove(content.getSelectedIndex());
	        			
		        		count --;
        			}
        		}
        	}
        });
        cs.insets = new Insets(10, 10, 0, 0);
        cs.gridx = 2;
        cs.gridy = 9;
        cs.gridwidth = 1;
        cs.gridheight = 1;
        panel.add(btnDltLek, cs);
        
        lbLekovi = new JLabel("Dodati lekovi: ");
        lbLekovi.setFont(new Font("Arial", Font.ITALIC, 14));
        lbLekovi.setForeground(new Color (77, 77, 77));
        lbLekovi.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, new Color (111, 179, 143)));
        cs.insets = new Insets(5, 0, 5, 0);
        cs.gridx = 0;
        cs.gridy = 7;
        cs.gridwidth = 1;
        panel.add(lbLekovi, cs);
        
        listModel = new DefaultListModel();
        content = new JList(listModel);
        content.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listModel.addElement("");
        content.setVisibleRowCount(3);
        content.setPrototypeCellValue(String.format("%50s", ""));
        content.setFont(new Font("Arial", Font.PLAIN, 13));
        content.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, new Color (111, 179, 143)));
        content.setForeground(new Color (77, 77, 77));
        content.setBackground(new Color(202, 232, 216));
        cs.insets = new Insets(0, 0, 0, 10);
        cs.gridx = 0;
        cs.gridy = 8;
        cs.gridwidth = 2;
        cs.gridheight = 2;
        cs.anchor = GridBagConstraints.LINE_START;
        cs.fill = GridBagConstraints.VERTICAL;
        panel.add(content, cs);
        
        panel.setBackground(new Color(164, 218, 189));
        panel.setPreferredSize(new Dimension(400,511));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
 
        btnAdd = new JButton("Add");
        btnAdd.setPreferredSize(new Dimension(90,30));
        btnAdd.setBackground(peach);
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				try {
	        		if( tfJmbg.getText().equals("") ) {
	        			JOptionPane.showMessageDialog(DodajRecept.this, "Sva polja moraju biti popunjena!", "Error", JOptionPane.ERROR_MESSAGE);
	        		} else if(!(dodatiLek.isEmpty() == true)) {
			        	
			        	Recept newRec = new Recept(Integer.valueOf( tfSifra.getText()), tfId.getText(), tfJmbg.getText(), date );
			        	for(int i=0; i<dodatiLek.size(); i++) {
							newRec.dodajLek(dodatiLek.get(i), dodatiKol.get(i));
						}
			        	System.out.println(newRec);
						writeToFile.writeToFileRec(newRec);
			        	JOptionPane.showMessageDialog(DodajRecept.this, "Uspešno ste dodali novi recept.", "Dodat recept", JOptionPane.INFORMATION_MESSAGE);
			            dispose();
			        	
	        		} else {
	        			JOptionPane.showMessageDialog(DodajRecept.this, "Mora biti dodat bar jedan lek!", "Error", JOptionPane.ERROR_MESSAGE);
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
    
    private int generateID() throws ClassNotFoundException, IOException {
    	ArrayList<Recept> recepti =  readFromFile.readFromFileRec();
    	if(recepti == null) {
    		ID = 1;
    	} else {
    		int last = recepti.get(recepti.size()-1).getSifra();
    		System.out.println(last);
    		ID = last + 1;
    	}
    	return ID;
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
