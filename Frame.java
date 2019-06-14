import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame extends JFrame
{
	public Frame()
	{
		setLayout(null);
		setTitle("KugelDrawning");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setBounds(75, 25, 1200, 700);		
		Panel pan = new Panel();
		Thread t = new Thread(pan);
		t.start();
		add(pan);		
		setVisible(true);
		
		JButton btn = new JButton("BREAK");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					int count = 0;
					while(true)
					System.out.println(count++);					
				}				
			});
			th.start();
			try {
				th.sleep(15);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}		
		});
		
		btn.setBounds(5, 5, 100, 50);
	//	add(btn);	
	}
}
