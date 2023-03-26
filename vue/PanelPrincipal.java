package vue;
import java.awt.Color;
import javax.swing.JPanel;

public abstract class PanelPrincipal extends JPanel{
	public PanelPrincipal(Color uneCouleur) {
		this.setBounds(50, 100, 1090, 500);
		this.setBackground(uneCouleur);
		this.setLayout(null);
		
		this.setVisible(false);
	}
}
