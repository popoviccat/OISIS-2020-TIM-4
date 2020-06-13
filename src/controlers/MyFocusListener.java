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
		txt.setForeground(Color.BLACK);
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		JTextField txt=(JTextField) arg0.getComponent();
		txt.setBackground(Color.lightGray);
		

		//polje korIme je obavezno za unos:
		if (txt.getName().equals("txtKorIme")){
			System.out.println(txt.getName());

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
