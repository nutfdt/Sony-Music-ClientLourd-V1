package outils;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.AbstractBorder;

public class rounded extends AbstractBorder{
	private int radius;
	   
	// Constructeur pour spécifier le rayon du bord arrondi
	public rounded(int radius) {
	   this.radius = radius;
	}
	   
	// Redéfinition de la méthode paintBorder() pour dessiner les bords arrondis
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	   super.paintBorder(c, g, x, y, width, height);
	   g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	}
	   
	// Redéfinition de la méthode getBorderInsets() pour spécifier les marges autour du bord arrondi
	public Insets getBorderInsets(Component c) {
	   return new Insets(this.radius/2, this.radius/2, this.radius/2, this.radius/2);
	}
}