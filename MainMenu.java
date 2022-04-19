<<<<<<< HEAD
import javax.swing.*;
import java.awt.*;
=======
import java.awt.*;
import javax.swing.*;
>>>>>>> work
public class MainMenu extends JFrame{
	public MainMenu(String title){
		setTitle(title);
		setSize(600,400);
<<<<<<< HEAD
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		setLayout(new FlowLayout());
		JButton button = new JButton("MyButton");
		contentPane.add(button,BorderLayout.CENTER);
=======
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
>>>>>>> work
	}
}
