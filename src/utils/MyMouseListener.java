package utils;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import application.main;

public class MyMouseListener implements MouseListener{
	
	public MyMouseListener(JButton btn) {
		btn1 = btn;
	}
	
	Color peach = new Color(249, 229, 222);
	Color mint = new Color(140,208,172);
	JButton btn1= null;

	public void mouseClicked(MouseEvent arg0) {
	
	}

	public void mouseEntered(MouseEvent arg0) {
		btn1.setBackground(mint);
		btn1.setForeground(Color.white);
	}

	public void mouseExited(MouseEvent arg0) {
		btn1.setBackground(peach);
		btn1.setForeground(Color.black);
	}

	public void mousePressed(MouseEvent arg0) {
		
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
		
	}

}
