package view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.*;

import controlers.Login;

public class LoginDialog extends JDialog{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private JTextField tfUsername;
	    private JPasswordField pfPassword;
	    private JLabel lbUsername;
	    private JLabel lbPassword;
	    private JLabel lbText;
	    private JButton btnLogin;
	    private JButton btnCancel;
	    private boolean succeeded;
	 
	    public LoginDialog(Frame parent) {
	        super(parent, "Login", true);
	        //
	        JPanel panel = new JPanel(new GridBagLayout());
	        GridBagConstraints cs = new GridBagConstraints();
	        Color peach = new Color(249, 229, 222);
			Color mint = new Color(140,208,172);
	 
	        cs.fill = GridBagConstraints.HORIZONTAL;
	        
	        lbText = new JLabel("Login to Apoteka");
	        lbText.setFont(new Font("Arial", Font.BOLD, 25));
	        cs.insets = new Insets(0, 0, 15, 0);
	        cs.gridx = 0;
	        cs.gridy = 0;
	        cs.gridwidth = 3;
	        panel.add(lbText, cs);
	 
	        lbUsername = new JLabel("Korisničko ime: ");
	        cs.insets = new Insets(20, 0, 20, 0);
	        cs.gridx = 0;
	        cs.gridy = 1;
	        cs.gridwidth = 1;
	        panel.add(lbUsername, cs);
	 
	        tfUsername = new JTextField(20);
	        cs.gridx = 1;
	        cs.gridy = 1;
	        cs.gridwidth = 2;
	        panel.add(tfUsername, cs);
	 
	        lbPassword = new JLabel("Lozinka: ");
	        cs.insets = new Insets(10, 0, 10, 0);
	        cs.gridx = 0;
	        cs.gridy = 2;
	        cs.gridwidth = 1;
	        panel.add(lbPassword, cs);
	 
	        pfPassword = new JPasswordField(20);
	        cs.gridx = 1;
	        cs.gridy = 2;
	        cs.gridwidth = 2;
	        panel.add(pfPassword, cs);
	        panel.setBorder(new LineBorder(Color.gray, 0));
	        panel.setBackground(mint);
	        panel.setPreferredSize(new Dimension(400,200));
	 
	        btnLogin = new JButton("Login");
	        btnLogin.setBackground(peach);
	        btnLogin.addActionListener(new ActionListener() {
	 
	            public void actionPerformed(ActionEvent e) {
	                try {
						if (Login.authenticate(getUsername(), getPassword())) {
						    JOptionPane.showMessageDialog(LoginDialog.this,
						            "Zdravo " + getUsername() + "! Uspešno ste se ulogovali.",
						            "Login to Apoteka",
						            JOptionPane.INFORMATION_MESSAGE);
						    succeeded = true;
						    dispose();
						} else {
						    JOptionPane.showMessageDialog(LoginDialog.this,
						            "Netačno korisničko ime ili lozinka!",
						            "Login to Apoteka",
						            JOptionPane.ERROR_MESSAGE);
						    // reset username and password
						    tfUsername.setText("");
						    pfPassword.setText("");
						    succeeded = false;
 
						}
					} catch (HeadlessException | ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	        });
	        btnCancel = new JButton("Cancel");
	        btnCancel.setBackground(peach);
	        btnCancel.addActionListener(new ActionListener() {
	 
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	            }
	        });
	        
	        JPanel bp = new JPanel(new GridBagLayout());
	        
	        cs.insets = new Insets(0, 30, 10, 30);
	        cs.gridx = 0;
	        cs.gridy = 0;
	        cs.gridwidth = 1;
	        bp.add(btnLogin,cs);
	        
	        
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
	 
	    public String getUsername() {
	        return tfUsername.getText().trim();
	    }
	 
	    public String getPassword() {
	        return new String(pfPassword.getPassword());
	    }
	 
	    public boolean isSucceeded() {
	        return succeeded;
	    }
}
