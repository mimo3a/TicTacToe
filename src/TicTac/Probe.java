package TicTac;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Probe {
	JPanel mainPanel;
	JPanel innenPanel;
	JLabel ausenLabel;
	JLabel innenLabel;
	
	
	void myPanel() {
		mainPanel = new JPanel();		
		mainPanel.setLayout(new BorderLayout());
		
		ausenLabel = new JLabel("Gud");
		
		innenPanel = new JPanel();
		
		innenPanel.setLayout(new FlowLayout());
		innenLabel = new JLabel("i am the innen label");
		innenPanel.add(innenLabel);
		mainPanel.add(ausenLabel, BorderLayout.NORTH);
		mainPanel.add(innenPanel, BorderLayout.SOUTH); 
		
		JFrame frame = new JFrame("Probe 1");
		frame.setContentPane(mainPanel);
		frame.setSize(400,200);
		frame.setVisible(true);
		
		
		
	}
	void changeLabel() {
		ausenLabel.setText("www");
		innenLabel.setText("RRR");
	}
	public static void main(String[] args) {
		Probe pr1 = new Probe();
		pr1.myPanel();
		pr1.changeLabel();
		
	}
 
}
