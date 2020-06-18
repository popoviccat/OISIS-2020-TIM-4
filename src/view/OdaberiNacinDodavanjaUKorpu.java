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

public class OdaberiNacinDodavanjaUKorpu extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1402697017431548922L;
	private JRadioButton radioBtnDodajLek;
	private JRadioButton radioBtnDodajLekoveIzRecepta;
	private ButtonGroup buttonGroup;
	private JLabel lbNacinDodavanjaUKorpu;
    private JButton btnChoose;
    private JButton btnCancel;
    
 
    public OdaberiNacinDodavanjaUKorpu(Frame parent) {
        super(parent, "Odaberi nacin dodavanja lekova u korpu", true);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        Color peach = new Color(249, 229, 222);
		Color mint = new Color(140,208,172);
 
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        buttonGroup = new ButtonGroup();
        
        lbNacinDodavanjaUKorpu = new JLabel("Nacin dodavanja lekova u korpu:");
        lbNacinDodavanjaUKorpu.setFont(new Font("Arial", Font.BOLD, 25));
        cs.insets = new Insets(40, 0, 20, 40);
        cs.gridx = 0;
        cs.gridy = 0;
        panel.add(lbNacinDodavanjaUKorpu, cs);
 
        radioBtnDodajLek = new JRadioButton("Dodaj lek");
        radioBtnDodajLek.setFont(new Font("Arial", Font.PLAIN, 14));
        radioBtnDodajLek.setBackground(new Color(164, 218, 189));
        cs.gridx = 0;
        cs.gridy = 1;
        buttonGroup.add(radioBtnDodajLek);
        panel.add(radioBtnDodajLek, cs);
        
        radioBtnDodajLekoveIzRecepta = new JRadioButton("Dodaj sve lekove iz recepta");
        radioBtnDodajLekoveIzRecepta.setFont(new Font("Arial", Font.PLAIN, 14));
        radioBtnDodajLekoveIzRecepta.setBackground(new Color(164, 218, 189));
        cs.gridx = 0;
        cs.gridy = 2;
        buttonGroup.add(radioBtnDodajLekoveIzRecepta);
        panel.add(radioBtnDodajLekoveIzRecepta, cs);
        
        panel.setBackground(new Color(164, 218, 189));
        panel.setPreferredSize(new Dimension(310,351));
 
        btnChoose = new JButton("Choose");
        btnChoose.setPreferredSize(new Dimension(90,30));
        btnChoose.setBackground(peach);
        btnChoose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (radioBtnDodajLek.isSelected()) {
					try {
						DodajLekUKorpu dlukDlg = new DodajLekUKorpu(parent);
						dlukDlg.setVisible(true);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (radioBtnDodajLekoveIzRecepta.isSelected()) {
					DodajLekoveIzReceptaUKorpu dlirukDlg;
					try {
						dlirukDlg = new DodajLekoveIzReceptaUKorpu(parent);
						dlirukDlg.setVisible(true);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(OdaberiNacinDodavanjaUKorpu.this, "Morate odabrati nacin dodavanja u korpu.", "Error", JOptionPane.ERROR_MESSAGE);
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
 
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(parent);
    }
}
