import javax.swing.*;
import java.awt.*;
public class MainMenu extends JFrame{
	public MainMenu(String title){
		setTitle(title);
		setSize(600,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		setLayout(new FlowLayout());
		JButton button = new JButton("MyButton");
		contentPane.add(button,BorderLayout.CENTER);
	}
}
