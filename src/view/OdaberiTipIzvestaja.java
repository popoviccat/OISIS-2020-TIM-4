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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

public class OdaberiTipIzvestaja extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1402697017431548922L;
	private JRadioButton radioBtnIzvestajOSvimLekovima;
	private JRadioButton radioBtnIzvestajOLekovimaPoProizvodjacu;
	private JRadioButton radioBtnIzvestajOLekovimaPoApotekaru;
	private ButtonGroup buttonGroup;
	private JLabel lbIzvestaj;
    private JButton btnChoose;
    private JButton btnCancel;
    
 
    public OdaberiTipIzvestaja(Frame parent, TabIzvestaj tabIzvestaj) throws ClassNotFoundException, IOException {
        super(parent, "Odaberi tip izveštaja", true);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        Color peach = new Color(249, 229, 222);
		Color mint = new Color(140,208,172);
 
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        buttonGroup = new ButtonGroup();
        
        lbIzvestaj = new JLabel("Tip izvestaja:");
        lbIzvestaj.setFont(new Font("Arial", Font.BOLD, 25));
        cs.insets = new Insets(40, 0, 30, 40);
        cs.gridx = 0;
        cs.gridy = 0;
        panel.add(lbIzvestaj, cs);
 
        radioBtnIzvestajOSvimLekovima = new JRadioButton("Ukupna prodaja svih lekova");
        radioBtnIzvestajOSvimLekovima.setFont(new Font("Arial", Font.PLAIN, 14));
        radioBtnIzvestajOSvimLekovima.setBackground(new Color(164, 218, 189));
        cs.gridx = 0;
        cs.gridy = 1;
        buttonGroup.add(radioBtnIzvestajOSvimLekovima);
        panel.add(radioBtnIzvestajOSvimLekovima, cs);
        
        radioBtnIzvestajOLekovimaPoProizvodjacu = new JRadioButton("Ukupna prodaja svih lekova odabranog proizvodjaca");
        radioBtnIzvestajOLekovimaPoProizvodjacu.setFont(new Font("Arial", Font.PLAIN, 14));
        radioBtnIzvestajOLekovimaPoProizvodjacu.setBackground(new Color(164, 218, 189));
        cs.gridx = 0;
        cs.gridy = 2;
        buttonGroup.add(radioBtnIzvestajOLekovimaPoProizvodjacu);
        panel.add(radioBtnIzvestajOLekovimaPoProizvodjacu, cs);
        
        radioBtnIzvestajOLekovimaPoApotekaru = new JRadioButton("Ukupna prodaja svih lekova koje je odabrani apotekar prodao");
        radioBtnIzvestajOLekovimaPoApotekaru.setFont(new Font("Arial", Font.PLAIN, 14));
        radioBtnIzvestajOLekovimaPoApotekaru.setBackground(new Color(164, 218, 189));
        cs.gridx = 0;
        cs.gridy = 3;
        buttonGroup.add(radioBtnIzvestajOLekovimaPoApotekaru);
        panel.add(radioBtnIzvestajOLekovimaPoApotekaru, cs);
        
        panel.setBackground(new Color(164, 218, 189));
        panel.setPreferredSize(new Dimension(310,351));
 
        btnChoose = new JButton("Choose");
        btnChoose.setPreferredSize(new Dimension(90,30));
        btnChoose.setBackground(peach);
        btnChoose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (odabranIzvestajOSvimLekovima()) {
					try {
						tabIzvestaj.prikazSvihLekova();
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (odabranIzvestajOLekovimaPoProizvodjacu()) {
					try {
						tabIzvestaj.prikazLekovaOdabranogProizvodjaca();
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (odabranIzvestajOLekovimaPoApotekaru()){
					try {
						tabIzvestaj.prikazLekovaOdabranogApotekara();
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(OdaberiTipIzvestaja.this, "Morate odabrati tip izvestaja.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				dispose();
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
        bp.add(btnChoose,cs);
        
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        bp.add(btnCancel,cs);
        
        bp.setBackground(mint);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(parent);
    }
    
    public boolean odabranIzvestajOSvimLekovima() {
    	return radioBtnIzvestajOSvimLekovima.isSelected();
    }
    
    public boolean odabranIzvestajOLekovimaPoProizvodjacu() {
    	return radioBtnIzvestajOLekovimaPoProizvodjacu.isSelected();
    }
    
    public boolean odabranIzvestajOLekovimaPoApotekaru() {
    	return radioBtnIzvestajOLekovimaPoApotekaru.isSelected();
    }
}
