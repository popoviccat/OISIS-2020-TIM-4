package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import lib.JTabbedPaneCloseButton;


public class main extends JFrame {


	

		
		private static final long serialVersionUID = -3987690974281770416L;
		
		private JPanel mainToolbar;
		private JSplitPane mainSplitPane;
		private JPanel leftPanel;
		private JTabbedPaneCloseButton tabbedPane;
		
		int tabNumber = 0;
		Color peach = new Color(249, 229, 222);
		Color mint = new Color(140,208,172);
		
		// ========================= Singleton obrazac
		private static main instance = null;

		private main() {
			this.createToolbar();
			this.createSplitPane();
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
			mainToolbar.add(new JLabel("Ovo je glavni toolbar"));
			this.add(mainToolbar, BorderLayout.NORTH);

		}

		private void createSplitPane() {
			this.createLeftPanel();
			this.createTabbedPane();
		
			
			add(leftPanel,BorderLayout.WEST);
			leftPanel.setPreferredSize(new Dimension(160,1200));
			leftPanel.setBackground(mint);
			add(tabbedPane,BorderLayout.CENTER);
			
			tabbedPane.setBackground(Color.PINK);
		}

		private void createLeftPanel() {
			leftPanel = new JPanel();
			JButton btn1= new JButton("Korisnici");
			btn1.setBackground(peach);
			btn1.setPreferredSize(new Dimension(120,40));
			JButton btn2= new JButton("Lekovi");
			btn2.setBackground(peach);
			btn2.setPreferredSize(new Dimension(120,40));
			JButton btn3= new JButton("Recepti");
			btn3.setBackground(peach);
			btn3.setPreferredSize(new Dimension(120,40));
			JButton btn4= new JButton("Izvestaj");
			btn4.setBackground(peach);
			btn4.setPreferredSize(new Dimension(120,40));
			JButton btn5= new JButton("Korpa");
			btn5.setBackground(peach);
			btn5.setPreferredSize(new Dimension(120,40));
			
			btn1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					addMyTabToTabbedPane("Knjiga " + (++tabNumber));
				}
			});
			
			btn2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					addMyTabToTabbedPane("Knjiga " + (++tabNumber));
				}
			});
			
			leftPanel.add(btn1);
			leftPanel.add(btn2);
			leftPanel.add(btn3);
			leftPanel.add(btn4);
			leftPanel.add(btn5, BorderLayout.SOUTH);
		}
		
			

		private void createTabbedPane() {
			this.tabbedPane = new JTabbedPaneCloseButton();
		}

		
		private void initPosition() {
			this.setSize(800, 600);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
		}
		
		// dodavanje taba u TabbedPaned
		private void addMyTabToTabbedPane(String bookName) {
			// ucitavanje ikonice
			ImageIcon icon = createImageIcon("images/img.png", true);
			// kreiranje instance MyTab
			BookTab mt = new BookTab(bookName);
			// dodavanje taba
			tabbedPane.addTab(bookName, icon, mt, "Prvi tab - Tooltip");
		}
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			main.getInstance();
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
	
	
}
