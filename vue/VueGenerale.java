package vue;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controleur.Agent;
import controleur.Sony_Music;
import outils.rounded;

public class VueGenerale extends JFrame implements ActionListener{
	private JPanel panelMenu= new JPanel();
	private JButton btAgents= new JButton("Gest. Agents");
	private JButton btAlbums= new JButton("Gest. Albums");
	private JButton btArtistes= new JButton("Gest. Artistes");
	private JButton btChansons= new JButton("Gest. Chansons");
	private JButton btCategories= new JButton("Gest. Categories");
	private JButton btProfil= new JButton("Mon Profil");
	private JButton btQuitter= new JButton("Quitter");
	
	private static PanelProfil unPanelProfil;
	private static PanelAgents unPanelAgent= new PanelAgents();
	private static PanelAlbums unPanelAlbum= new PanelAlbums();
	private static PanelArtistes unPanelArtiste= new PanelArtistes();
	private static PanelChansons unPanelChanson= new PanelChansons();
	private static PanelCategories unPanelCategorie= new PanelCategories();
	
	public VueGenerale(Agent unAgent) {
		unPanelProfil= new PanelProfil(unAgent);
		
		
		this.setTitle("Gestion Sony_Music");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(1, 89, 162));
		this.setBounds(100, 100, 1200, 700);
		this.setLayout(null);
		
		//Construction des bouttons
		this.designBoutton(this.btProfil);
		this.designBoutton(this.btAgents);
		this.designBoutton(this.btAlbums);
		this.designBoutton(this.btArtistes);
		this.designBoutton(this.btChansons);
		this.designBoutton(this.btCategories);
		this.designBoutton(this.btQuitter);
		
		//Construction du panel 
		this.panelMenu.setBounds(100, 20, 1000, 40);
		this.panelMenu.setBackground(new Color(1, 89, 162));
		//1 lignes sur 5 colonnes
		this.panelMenu.setLayout(new GridLayout(1, 7));
		this.panelMenu.add(this.btProfil);
		this.panelMenu.add(this.btAgents);
		this.panelMenu.add(this.btAlbums);
		this.panelMenu.add(this.btArtistes);
		this.panelMenu.add(this.btChansons);
		this.panelMenu.add(this.btCategories);
		this.panelMenu.add(this.btQuitter);
		this.add(this.panelMenu);
		
		//Rendre les boutons ecoutables
		this.btQuitter.addActionListener(this);
		this.btAgents.addActionListener(this);
		this.btProfil.addActionListener(this);
		this.btAlbums.addActionListener(this);
		this.btArtistes.addActionListener(this);
		this.btChansons.addActionListener(this);
		this.btCategories.addActionListener(this);
		
		//Insertion des paneaux dans la fenêtre
		this.add(unPanelProfil);
		this.add(unPanelAgent);
		this.add(unPanelAlbum);
		this.add(unPanelArtiste);
		this.add(unPanelChanson);
		this.add(unPanelCategorie);
		
		this.setVisible(false);
	}

	public void designBoutton(JButton bouton) {
		bouton.setFont(new Font("Arial", Font.BOLD, 12));
		bouton.setBorder(new rounded(20));
		bouton.setForeground(Color.white);
		bouton.setFocusPainted(false);
		bouton.setContentAreaFilled(false);
		
		bouton.addMouseListener(new MouseAdapter() {
	         // Redéfinition de la méthode mouseEntered() pour changer la couleur de fond du bouton lorsque la souris entre dans la zone du bouton
	         public void mouseEntered(MouseEvent e) {
	        	 bouton.setForeground(Color.gray);
	         }
	         
	         // Redéfinition de la méthode mouseExited() pour changer la couleur de fond du bouton lorsque la souris quitte la zone du bouton
	         public void mouseExited(MouseEvent e) {
	        	 bouton.setForeground(Color.white);
	         }
	      });
	}
	
	
	public static void activerPanel(int choix) {
		//Ajouter les cas de visibilité des panels
		unPanelProfil.setVisible(false);
		unPanelAgent.setVisible(false);
		unPanelAlbum.setVisible(false);
		unPanelArtiste.setVisible(false);
		unPanelChanson.setVisible(false);
		unPanelCategorie.setVisible(false);
		switch(choix) {
		case 1: unPanelProfil.setVisible(true); break;
		case 2: unPanelAgent.setVisible(true); break;
		case 3: unPanelAlbum.setVisible(true); break;
		case 4: unPanelArtiste.setVisible(true); break;
		case 5: unPanelChanson.setVisible(true); break;
		case 6: unPanelCategorie.setVisible(true); break;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btQuitter) {
			int retour= JOptionPane.showConfirmDialog(this, "Voulez vous quitter l'application ? ", 
					"Quitter l'application ? " ,JOptionPane.YES_NO_OPTION);
			if(retour==0) {
				this.dispose();
				Sony_Music.gererVueConnexion(true);
			}
		}
		else if(e.getSource()== this.btProfil) {
			activerPanel(1);
		}
		else if(e.getSource()== this.btAgents) {
			activerPanel(2);
		}
		else if(e.getSource()== this.btAlbums) {
			activerPanel(3);
		}
		else if(e.getSource()== this.btArtistes) {
			activerPanel(4);
		}
		else if(e.getSource()== this.btChansons) {
			activerPanel(5);
		}
		else if(e.getSource()== this.btCategories) {
			activerPanel(6);
		}
	}
}
