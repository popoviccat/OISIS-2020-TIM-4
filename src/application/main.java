package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.thoughtworks.xstream.XStream;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.LoginDialog;
import view.TabKorisnici;
import view.TabLekovi;
import view.TabRecepti;
import model.Korisnik;
import model.TipKorisnika;
import controlers.JTabbedPaneCloseButton;
import controlers.MyMouseListener;
import controlers.MyWindowListener;
import controlers.readFromFile;


public class main extends JFrame {
		
		private static final long serialVersionUID = -3987690974281770416L;
		
		private JPanel mainToolbar;
		private JPanel leftPanel;
		private JTabbedPaneCloseButton tabbedPane;
		
		int tabNumber = 0;
		Color peach = new Color(249, 229, 222);
		Color mint = new Color(140,208,172);
		
		// ========================= Singleton obrazac
		private static main instance = null;

		private main() {
			this.createToolbar();
			this.createMainPanel();
			this.initPosition();
		}
		
		public static main getInstance() {
			if (instance == null) {
				instance = new main();
			}
			return instance;
		}
		// =========================
		
		private void createToolbar() {
			mainToolbar = new JPanel();
			GridBagConstraints layout = new GridBagConstraints();
			mainToolbar.setLayout(new GridBagLayout());
			Icon logIn = new ImageIcon("images/login.png");
			
			mainToolbar.setBackground(Color.WHITE);
			mainToolbar.setBorder(BorderFactory.createMatteBorder(1,1,0,1,Color.BLACK));
			JLabel helloMessage = new JLabel("Zdravo, Petar Petrović!");
		    helloMessage.setFont(new Font("Ariel", Font.ITALIC, 20));
		    layout.insets = new Insets(5, 30, 5, 30);
		    layout.gridx = 0;
		    layout.gridy = 0;
		    layout.weightx = 1.0;
		    layout.anchor = GridBagConstraints.LINE_START;
		    mainToolbar.add(helloMessage, layout);
			
			JButton loginButton = new JButton("LOGIN", logIn);
			loginButton.setFont(new Font("Arial", Font.BOLD, 15));
			loginButton.setBackground(peach);
			loginButton.setPreferredSize(new Dimension(100, 35));
			loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
			loginButton.addMouseListener(new MyMouseListener(loginButton));
		    layout.gridx = 1;
		    layout.gridy = 0;
		    layout.anchor = GridBagConstraints.LINE_END;
			mainToolbar.add(loginButton, layout);
			this.add(mainToolbar, BorderLayout.NORTH);
		}

		private void createMainPanel() {
			this.createLeftPanel();
			this.createTabbedPane();
			JLayeredPane lpane = new JLayeredPane();
			JPanel rightPanel = new JPanel();
			
			
			add(leftPanel,BorderLayout.WEST);
			leftPanel.setPreferredSize(new Dimension(200,600));
			leftPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
			leftPanel.setBackground(Color.WHITE);
			

			ImagePanel panel = new ImagePanel(new ImageIcon("images/bg_image.jpg").getImage());
		    rightPanel.add(panel);
		    
		    
			
		    add(lpane, BorderLayout.CENTER);
		   
			lpane.add(rightPanel,new Integer(0), 0);
			rightPanel.setBorder(BorderFactory.createMatteBorder(1,0,1,1,Color.BLACK));
			rightPanel.setBackground(mint);
			rightPanel.setBounds(0, 0, 784, 515);
			
			lpane.add(tabbedPane, new Integer(1), 1);
			tabbedPane.setPreferredSize(new Dimension(800,700));
			tabbedPane.setBounds(0, 0, 784, 515);
			tabbedPane.setBorder(BorderFactory.createEmptyBorder());
			tabbedPane.setBackground(Color.WHITE);
		}
		
		class ImagePanel extends JPanel {

			  /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private Image img;

			  public ImagePanel(String img) {
			    this(new ImageIcon(img).getImage());
			  }

			  public ImagePanel(Image img) {
			    this.img = img;
			    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
			    setPreferredSize(size);
			    setMinimumSize(size);
			    setMaximumSize(size);
			    setSize(size);
			    setLayout(null);
			  }

			  public void paintComponent(Graphics g) {
			    g.drawImage(img, 0, 0, null);
			  }
		}

		private void createLeftPanel() {
			
			leftPanel = new JPanel();
			GridBagConstraints gbc = new GridBagConstraints();
			leftPanel.setLayout(new GridBagLayout());
			Icon icon = new ImageIcon("images/cart.png");
			Icon logoI = new ImageIcon("images/Logo.png");
			float f = 15;
			
			JLabel logo = new JLabel(logoI);
			
			JButton btn1= new JButton("Korisnici");
			btn1.setFont(btn1.getFont().deriveFont(f));
			btn1.setBackground(peach);
			btn1.setPreferredSize(new Dimension(130,40));
			btn1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
			btn1.addMouseListener(new MyMouseListener(btn1));
			
			JButton btn2= new JButton("Lekovi");
			btn2.setFont(btn2.getFont().deriveFont(f));
			btn2.setBackground(peach);
			btn2.setPreferredSize(new Dimension(130,40));
			btn2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
			btn2.addMouseListener(new MyMouseListener(btn2));
			
			JButton btn3= new JButton("Recepti");
			btn3.setFont(btn3.getFont().deriveFont(f));
			btn3.setBackground(peach);
			btn3.setPreferredSize(new Dimension(130,40));
			btn3.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
			btn3.addMouseListener(new MyMouseListener(btn3));
			
			JButton btn4= new JButton("Izveštaj");
			btn4.setFont(btn4.getFont().deriveFont(f));
			btn4.setBackground(peach);
			btn4.setPreferredSize(new Dimension(130,40));
			btn4.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
			btn4.addMouseListener(new MyMouseListener(btn4));
			
			JButton btn5= new JButton("Korpa", icon);
			btn5.setFont(btn5.getFont().deriveFont(f));
			btn5.setBackground(peach);
			btn5.setPreferredSize(new Dimension(130,40));
			btn5.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
			btn5.addMouseListener(new MyMouseListener(btn5));
			
			btn1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addTab_KorisniciToTabbedPane();
				}
			});
			
			btn2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addTab_LekoviToTabbedPane();
				}
			});
			
			btn3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addTab_ReceptiToTabbedPane();
				}
			});
			
			btn4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addTab_IzvestajToTabbedPane();
				}
			});
			
			btn5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addTab_KorpaToTabbedPane();
				}
			});
			
			gbc.insets = new Insets(10, 0, 10, 0);
			
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets (20,0,10,0);
			leftPanel.add(logo, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.insets = new Insets (10,0,10,0);
			leftPanel.add(btn1, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 2;
			leftPanel.add(btn2, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 3;
			leftPanel.add(btn3, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 4;
			leftPanel.add(btn4, gbc);
		
			gbc.gridx = 0;
			gbc.gridy = 5;
			gbc.weighty = 1.0;
			gbc.anchor = GridBagConstraints.PAGE_END;
			gbc.insets = new Insets (0,0,30,0);
			leftPanel.add(btn5, gbc);
		}
		
			

		private void createTabbedPane() {
			this.tabbedPane = new JTabbedPaneCloseButton();
		}

		
		private void initPosition() {
			ImageIcon icon = createImageIcon("images/logo.png", true);
			this.setSize(1000, 600);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			addWindowListener(new MyWindowListener());
			setTitle("Apoteka");
			this.setIconImage(icon.getImage());
			this.setVisible(true);
			setResizable(false);
		}

		// dodavanje taba u TabbedPaned
		private void addTab_KorisniciToTabbedPane() {
			String title = "Korisnici"; 
			// ucitavanje ikonice
			ImageIcon icon = createImageIcon("images/users.png", true);
			// kreiranje instance MyTab
			TabKorisnici mt = new TabKorisnici(title);
			// dodavanje taba
			tabbedPane.addTab(title, icon, mt);
		}
		
		private void addTab_LekoviToTabbedPane() {
			String title = "Lekovi"; 
			ImageIcon icon = createImageIcon("images/drugs.png", true);
			TabLekovi mt = new TabLekovi(title);
			tabbedPane.addTab(title, icon, mt);
		}
		
		private void addTab_ReceptiToTabbedPane() {
			String title = "Recepti"; 
			ImageIcon icon = createImageIcon("images/presc.png", true);
			TabRecepti mt = new TabRecepti(title);
			tabbedPane.addTab(title, icon, mt);
		}
		
		private void addTab_IzvestajToTabbedPane() {
			String title = "Izveštaj"; 
			ImageIcon icon = createImageIcon("images/report.png", true);
			TabKorisnici mt = new TabKorisnici(title);
			tabbedPane.addTab(title, icon, mt);
		}
		
		private void addTab_KorpaToTabbedPane() {
			String title = "Korpa"; 
			ImageIcon icon = createImageIcon("images/cart.png", true);
			TabKorisnici mt = new TabKorisnici(title);
			tabbedPane.addTab(title, icon, mt);
		}
		
		public static void main(String[] args) throws IOException {
			main.getInstance();
		    final JFrame frame = new JFrame("Login to Apoteka");
		    final JPanel cont = new JPanel(new GridBagLayout());
		    GridBagConstraints gb = new GridBagConstraints();
		    final JButton btnLogin = new JButton("Click to login");
		    cont.setBackground(Color.white);
		   // readFromFile.readFromFileKor();
		   // readFromFile();
		    /*
		    btnLogin.addActionListener(
		    		new ActionListener(){
		    			public void actionPerformed(ActionEvent e) {
		    				LoginDialog loginDlg = new LoginDialog(frame);
		                    loginDlg.setVisible(true);
		                    // if logon successfully
		                    if(loginDlg.isSucceeded()){
		                    	main.getInstance();
		                    	frame.hide();
		                    }
		                }
		    });
		    
		    btnLogin.setFont(new Font("Ariel", Font.BOLD, 20));
		    btnLogin.setBackground(new Color(249, 229, 222));
		    btnLogin.setPreferredSize(new Dimension(200,60));
		    btnLogin.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		    btnLogin.addMouseListener(new MyMouseListener(btnLogin));
		  
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.add(cont);
		    
		    gb.insets = new Insets(40, 60, 40, 60);
	        gb.gridx = 0;
	        gb.gridy = 0;
	        gb.gridwidth = 1;
		    cont.add(btnLogin, gb);
		    frame.pack();
	        frame.setResizable(false);
		    frame.setVisible(true);
		    frame.setLocationRelativeTo(null);*/
		}

		protected static ImageIcon createImageIcon(String path, boolean scaleImage) {
			if (scaleImage) {
				// ukoliko vam je potrebno skaliranje slike
				ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
				Image image = imageIcon.getImage(); // transform it
				Image newimg = image.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
				imageIcon = new ImageIcon(newimg); // transform it back
				return imageIcon;

			} else {
				return new ImageIcon(path);
			}
		}
		
		public static void readFromFile() throws IOException {
			Korisnik kor1 = new Korisnik("Admin", "admin", "Administrator", "Adminović", TipKorisnika.ADMINISTRATOR);
			Korisnik[] korisnici = new Korisnik[] { kor1};
	
			File f = new File("Korisnici.xml");
			
			OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
			  
			try {
	
				XStream xs = new XStream();
				xs.alias("korisnik", Korisnik.class);
	
				String s = xs.toXML(korisnici); 
				xs.toXML(korisnici, os); 
				System.out.println(s);
	
				Korisnik[] ucitaniKorisnici = (Korisnik[]) xs.fromXML(s);
				int br = ucitaniKorisnici.length;
				for (int i = 0; i < br; i++) {
					if (ucitaniKorisnici[i].getTipKorisnika() == TipKorisnika.ADMINISTRATOR) {
						System.out.println(ucitaniKorisnici[i].getIme());
						
					}
				}
			} finally {
				os.close();
			}
		}
}
