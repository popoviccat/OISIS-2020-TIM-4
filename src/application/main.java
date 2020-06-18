package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controlers.JTabbedPaneCloseButton;
import controlers.MyMouseListener;
import controlers.MyWindowListener;
import model.Korisnik;
import model.Korpa;
import model.TipKorisnika;
import view.LoginDialog;
import view.OdaberiNacinDodavanjaUKorpu;
import view.TabIzvestaj;
import view.TabKorisnici;
import view.TabKorpa;
import view.TabLekovi;
import view.TabRecepti;

public class main extends JFrame {

	private static final long serialVersionUID = -3987690974281770416L;

	private JPanel mainToolbar;
	private JPanel leftPanel;
	private JTabbedPaneCloseButton tabbedPane;
	private Korisnik logedOn = new Korisnik("admin", "admin", "Admin", "Adminovic", TipKorisnika.APOTEKAR, false);
	public static LoginDialog loginDlg;

	int tabNumber = 0;
	Color peach = new Color(249, 229, 222);
	Color mint = new Color(140, 208, 172);
	
	private Korpa korpa;

	// ========================= Singleton obrazac
	private static main instance = null;

	private main() throws ClassNotFoundException, IOException {
		this.createToolbar();
		this.createMainPanel();
		this.initPosition();
	}

	public static main getInstance() throws ClassNotFoundException, IOException {
		if (instance == null) {
			instance = new main();
		}
		return instance;
	}
	// =========================

	private void createToolbar() throws ClassNotFoundException, IOException {
		mainToolbar = new JPanel();
		GridBagConstraints layout = new GridBagConstraints();
		mainToolbar.setLayout(new GridBagLayout());
		Icon logOut = new ImageIcon("images/logout.png");
		// logedOn = loginDlg.getLogedOnKor();

		// ArrayList<Korisnik> korisnici = readFromFile.readFromFileKor();
		// ArrayList<Lek> lek = new ArrayList<Lek>();
		//ArrayList<Recept> rec = new ArrayList<Recept>();
		// writeToFile.updateDatabaseKor(korisnici);
		// writeToFile.updateDatabaseLek(lek);
		//writeToFile.updateDatabaseRec(rec);
		mainToolbar.setBackground(Color.WHITE);
		mainToolbar.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
		JLabel helloMessage = new JLabel("Zdravo, " + logedOn.getIme() + " " + logedOn.getPrezime() + "!");
		helloMessage.setFont(new Font("Arial", Font.ITALIC, 20));
		layout.insets = new Insets(5, 30, 5, 30);
		layout.gridx = 0;
		layout.gridy = 0;
		layout.weightx = 1.0;
		layout.anchor = GridBagConstraints.LINE_START;
		mainToolbar.add(helloMessage, layout);

		JButton logoutButton = new JButton("Logout", logOut);
		logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
		logoutButton.setBackground(peach);
		logoutButton.setPreferredSize(new Dimension(110, 35));
		logoutButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		logoutButton.addMouseListener(new MyMouseListener(logoutButton));
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					logout();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		layout.gridx = 1;
		layout.gridy = 0;
		layout.anchor = GridBagConstraints.LINE_END;
		mainToolbar.add(logoutButton, layout);
		this.add(mainToolbar, BorderLayout.NORTH);
	}

	private void createMainPanel() throws ClassNotFoundException, IOException {
		this.createLeftPanel();
		this.createTabbedPane();
		JLayeredPane lpane = new JLayeredPane();
		JPanel rightPanel = new JPanel();

		add(leftPanel, BorderLayout.WEST);
		leftPanel.setPreferredSize(new Dimension(200, 600));
		leftPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		leftPanel.setBackground(Color.WHITE);

		ImagePanel panel = new ImagePanel(new ImageIcon("images/bg_image.jpg").getImage());
		rightPanel.add(panel);

		add(lpane, BorderLayout.CENTER);

		lpane.add(rightPanel, new Integer(0), 0);
		rightPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
		rightPanel.setBackground(mint);
		rightPanel.setBounds(0, 0, 784, 515);

		lpane.add(tabbedPane, new Integer(1), 1);
		tabbedPane.setPreferredSize(new Dimension(800, 700));
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

	private void createLeftPanel() throws ClassNotFoundException, IOException {

		this.korpa = new Korpa();
		
		// logedOn = loginDlg.getLogedOnKor();
		leftPanel = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		leftPanel.setLayout(new GridBagLayout());
		Icon icon = new ImageIcon("images/cart.png");
		Icon logoI = new ImageIcon("images/Logo.png");
		float f = 15;

		JLabel logo = new JLabel(logoI);

		JButton btn1 = new JButton("Korisnici");
		btn1.setFont(btn1.getFont().deriveFont(f));
		btn1.setBackground(peach);
		btn1.setPreferredSize(new Dimension(130, 40));
		btn1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btn1.addMouseListener(new MyMouseListener(btn1));

		JButton btn2 = new JButton("Lekovi");
		btn2.setFont(btn2.getFont().deriveFont(f));
		btn2.setBackground(peach);
		btn2.setPreferredSize(new Dimension(130, 40));
		btn2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btn2.addMouseListener(new MyMouseListener(btn2));

		JButton btn3 = new JButton("Recepti");
		btn3.setFont(btn3.getFont().deriveFont(f));
		btn3.setBackground(peach);
		btn3.setPreferredSize(new Dimension(130, 40));
		btn3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btn3.addMouseListener(new MyMouseListener(btn3));

		JButton btn4 = new JButton("Izvestaj");
		btn4.setFont(btn4.getFont().deriveFont(f));
		btn4.setBackground(peach);
		btn4.setPreferredSize(new Dimension(130, 40));
		btn4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btn4.addMouseListener(new MyMouseListener(btn4));

		JButton btn5 = new JButton("Dodaj u korpu");
		btn5.setFont(btn5.getFont().deriveFont(f));
		btn5.setBackground(peach);
		btn5.setPreferredSize(new Dimension(130, 40));
		btn5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btn5.addMouseListener(new MyMouseListener(btn5));
		
		JButton btn6 = new JButton("Korpa", icon);
		btn6.setFont(btn6.getFont().deriveFont(f));
		btn6.setBackground(peach);
		btn6.setPreferredSize(new Dimension(130, 40));
		btn6.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btn6.addMouseListener(new MyMouseListener(btn6));

		if (logedOn.getTipKorisnika() == TipKorisnika.LEKAR || logedOn.getTipKorisnika() == TipKorisnika.APOTEKAR) {
			btn1.setVisible(false);
			btn4.setVisible(false);
		}
		if (logedOn.getTipKorisnika() == TipKorisnika.LEKAR
				|| logedOn.getTipKorisnika() == TipKorisnika.ADMINISTRATOR) {
			btn5.setVisible(false);
			btn6.setVisible(false);
		}

		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addTab_KorisniciToTabbedPane();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addTab_LekoviToTabbedPane();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addTab_ReceptiToTabbedPane();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addTab_IzvestajToTabbedPane();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					openDlgAddToCart();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addTab_KorpaToTabbedPane();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(20, 0, 10, 0);
		leftPanel.add(logo, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 0, 10, 0);
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
		gbc.insets = new Insets(0, 0, 30, 0);
		leftPanel.add(btn5, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.weighty = 0;
		leftPanel.add(btn6, gbc);
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
	private void addTab_KorisniciToTabbedPane() throws ClassNotFoundException, IOException {
		String title = "Korisnici";
		// ucitavanje ikonice
		ImageIcon icon = createImageIcon("images/users.png", true);
		// kreiranje instance MyTab
		TabKorisnici mt = new TabKorisnici(title);
		// dodavanje taba
		tabbedPane.addTab(title, icon, mt);
	}

	private void addTab_LekoviToTabbedPane() throws ClassNotFoundException, IOException {
		String title = "Lekovi";
		ImageIcon icon = createImageIcon("images/drugs.png", true);
		TabLekovi mt = new TabLekovi(title, logedOn);
		tabbedPane.addTab(title, icon, mt);
	}

	private void addTab_ReceptiToTabbedPane() throws ClassNotFoundException, IOException {
		String title = "Recepti";
		ImageIcon icon = createImageIcon("images/presc.png", true);
		TabRecepti mt = new TabRecepti(title, logedOn);
		tabbedPane.addTab(title, icon, mt);
	}

	private void addTab_IzvestajToTabbedPane() throws ClassNotFoundException, IOException {
		String title = "Izvestaj";
		ImageIcon icon = createImageIcon("images/report.png", true);
		TabIzvestaj mt = new TabIzvestaj(title);
		tabbedPane.addTab(title, icon, mt);
	}

	private void addTab_KorpaToTabbedPane() throws ClassNotFoundException, IOException {
		String title = "Korpa";
		ImageIcon icon = createImageIcon("images/cart.png", true);
		TabKorpa mt = new TabKorpa(title);
		tabbedPane.addTab(title, icon, mt);
	}
	
	private void openDlgAddToCart() throws ClassNotFoundException, IOException {
		OdaberiNacinDodavanjaUKorpu ondukDlg = new OdaberiNacinDodavanjaUKorpu(main.getInstance());
		ondukDlg.setVisible(true);
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		main.getInstance();
		// login();
	}

	public static void login() {

		final JFrame frame = new JFrame("Login to Apoteka");
		final JPanel cont = new JPanel(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();
		final JButton btnLogin = new JButton("Click to login");
		ImageIcon icon = createImageIcon("images/logo.png", true);
		cont.setBackground(Color.white);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginDlg = new LoginDialog(frame);
				loginDlg.setVisible(true);
				// if logon successfully
				if (loginDlg.isSucceeded()) {
					try {
						main.getInstance();
						main.getInstance().setVisible(true);
						;
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame.hide();
				}
			}
		});

		btnLogin.setFont(new Font("Ariel", Font.BOLD, 20));
		btnLogin.setBackground(new Color(249, 229, 222));
		btnLogin.setPreferredSize(new Dimension(200, 60));
		btnLogin.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
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
		frame.setLocationRelativeTo(null);
		frame.setIconImage(icon.getImage());
	}

	public static void logout() throws ClassNotFoundException, IOException {

		JFrame frame = main.getInstance();
		int code = JOptionPane.showConfirmDialog(frame, "Da li ste sigurni da zelite da se odjavite?", "Logout?",
				JOptionPane.YES_NO_OPTION);

		if (code != JOptionPane.YES_OPTION) {
			frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		} else {
			frame.dispose();
			instance = null;
			login();
		}
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

	public Korpa getKorpa() {
		return korpa;
	}

	/*
	 * public Korisnik getLogedOnKor() throws ClassNotFoundException, IOException {
	 * 
	 * ArrayList<Korisnik> korisnici = readFromFile.readFromFileKor(); String korIme
	 * = loginDlg.getUsername(); for(int i=0; i<korisnici.size(); i++) { if
	 * (korIme.equals((String)korisnici.get(i).getKorisnickoIme())) { //trazi isto
	 * ime int index = i; logedOn = new
	 * Korisnik(korisnici.get(i).getKorisnickoIme(), korisnici.get(i).getLozinka(),
	 * korisnici.get(i).getIme(), korisnici.get(i).getPrezime(),
	 * korisnici.get(i).getTipKorisnika(), korisnici.get(i).getLogickiObrisan());
	 * break; } } return logedOn; }
	 */
}
	

