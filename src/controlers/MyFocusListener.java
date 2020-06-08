package controlers;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class MyFocusListener implements FocusListener{

	@Override
	public void focusGained(FocusEvent arg0) {
		
		JTextField txt=(JTextField) arg0.getComponent();
		txt.setBackground(Color.WHITE);
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		JTextField txt=(JTextField) arg0.getComponent();
		txt.setBackground(Color.GRAY);
		

		//polje prezime je obavezno za unos:
		if (txt.getName().equals("txtKorIme")){

		if (txt.getText().trim().equals("") || txt.getText().trim().equals("Obavezno polje...") ){
			txt.setText("Obavezno polje...");
			txt.requestFocus();
			txt.setForeground(Color.RED);
		}else{
			txt.setForeground(Color.BLACK);
		}
		}
		
	}

}
