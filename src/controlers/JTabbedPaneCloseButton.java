package controlers;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;

import view.TabKorisnici;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * @author 6dc
 *
 * A class which creates a JTabbedPane and auto sets a close button when you add a tab
 */
public class JTabbedPaneCloseButton extends JTabbedPane {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6162048567980039381L;
	
	public static int countKor, countLek, countRec, countIz, countKorpa = 0;
	
	public JTabbedPaneCloseButton() {
        super();
    }

    /* Override Addtab in order to add the close Button everytime */
    @Override
    public void addTab(String title, Icon icon, Component component, String tip) {
        super.addTab(title, icon, component, tip);
        int count = this.getTabCount() - 1;
        setTabComponentAt(count, new CloseButtonTab(component, title, icon));
    }

    @Override
    public void addTab(String title, Icon icon, Component component) {
    	switch(title) {
		case "Korisnici":
			if (countKor < 1) {
				addTab(title, icon, component, null);
				countKor ++;
			} else {}
		break;
		case "Lekovi":
			if (countLek < 1) {
				addTab(title, icon, component, null);
				countLek ++;
			} else {}
		break;
		case "Recepti":
			if (countRec < 1) {
				addTab(title, icon, component, null);
				countRec ++;
			} else {}
		break;
		case "Izvestaj":
			if (countIz < 1) {
				addTab(title, icon, component, null);
				countIz ++;
			} else {}
		break;
		case "Korpa":
			if (countKorpa < 1) {
				addTab(title, icon, component, null);
				countKorpa ++;
			} else {}
			break;
		default:
			break;
    	}
    	
    }

    @Override
    public void addTab(String title, Component component) {
        addTab(title, null, component);
    }

    /* addTabNoExit */
    public void addTabNoExit(String title, Icon icon, Component component, String tip) {
        super.addTab(title, icon, component, tip);
    }

    public void addTabNoExit(String title, Icon icon, Component component) {
        addTabNoExit(title, icon, component, null);
    }

    public void addTabNoExit(String title, Component component) {
        addTabNoExit(title, null, component);
    }
    
	/* Button */
    public class CloseButtonTab extends JPanel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 4141334065998335653L;
		
		private Component tab;

        public CloseButtonTab(final Component tab, String title, Icon icon) {
            this.tab = tab;
            setOpaque(false);
            FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
            setLayout(flowLayout);
            JLabel jLabel = new JLabel(title);
            jLabel.setIcon(icon);
            add(jLabel);
            JButton button = new JButton(MetalIconFactory.getInternalFrameCloseIcon(16));
            button.setMargin(new Insets(0, 0, 0, 0));
            button.addMouseListener(new CloseListener(tab));
            add(button);
        }
    }
    /* ClickListener */
    public class CloseListener implements MouseListener
    {
        private Component tab;

        public CloseListener(Component tab){
            this.tab=tab;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() instanceof JButton){
                JButton clickedButton = (JButton) e.getSource();
                JTabbedPane tabbedPane = (JTabbedPane) clickedButton.getParent().getParent().getParent();
              
                	switch((tab).getName()) {
                		case "Korisnici":
                			countKor --;
                		break;
                		case "Lekovi":
                			countLek --;
                		break;
                		case "Recepti":
                			countRec --;
                		break;
                		case "Izvestaj":
                			countIz --;
                		break;
                		case "Korpa":
                			countKorpa --;
                		break;
                		default:;
         
                }
                tabbedPane.remove(tab);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource() instanceof JButton){
                JButton clickedButton = (JButton) e.getSource();
             //   clickedButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,3));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource() instanceof JButton){
                JButton clickedButton = (JButton) e.getSource();
             //   clickedButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,3));
            }
        }
    }
}
