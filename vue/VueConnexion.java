package vue;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controleur.Agent;
import controleur.C_Agent;
import controleur.Sony_Music;
import outils.rounded;

public class VueConnexion extends JFrame implements ActionListener, KeyListener{
    private JTextField txtEmail= new JTextField();
	private JPasswordField txtMdp= new JPasswordField();
	private JButton btAnnuler= new JButton("Annuler");
	private JButton btSeConnecter= new JButton("Connexion");
	private JPanel panelCon=new JPanel();

    public VueConnexion(){
        this.setTitle("Connexion des utilisateurs");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(1, 89, 162) );
		this.setBounds(200, 200, 700, 350);
		this.setLayout(null);
		
		ImageIcon logo= new ImageIcon("src/images/sonyMusic_logo.png");
		JLabel monLogo= new JLabel(logo);
		monLogo.setBounds(5, 5, 270, 270);
		this.add(monLogo);
		
		//Design des boutons, JTextField
		this.designBoutton(this.btSeConnecter);
		this.designBoutton(this.btAnnuler);
		
		
		//Construction du panel connexion
		this.panelCon.setBounds(350, 40, 280, 200);
		this.panelCon.setBackground(new Color(1, 89, 162));
		
		//3 lignes sur 2 colonnes
		this.panelCon.setLayout(new GridLayout(3, 2));
		//Premiere grille ligne 1 colonne 1 on met l'email
		JLabel email= new JLabel("Email: ");
		email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		email.setForeground(Color.white);
		this.panelCon.add(email);
		this.panelCon.add(this.txtEmail);
		//Premiere grille ligne 1 colonne 2 on met le mdp
		JLabel mdp= new JLabel("MDP: ");
		mdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		mdp.setForeground(Color.white);
		this.panelCon.add(mdp);
		this.panelCon.add(this.txtMdp);
		//Premiere grille ligne 2 colonne 1 on met le boutton Annuler
		this.panelCon.add(this.btAnnuler);
		//Premiere grille ligne 2 colonne 2 on met le boutton Se Connecter
		this.panelCon.add(this.btSeConnecter);
		this.add(this.panelCon);
		
		//Rendre les boutons écoutable
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		//Ajout evenements frappe de touche
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		
		this.setVisible(true);
		
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

    public void traitement() {
    	String email=this.txtEmail.getText();
    	String mdp= new String(this.txtMdp.getPassword());
    	
    	//Si email ou mdp vide on indique de remplir correctement les champs de connexion
    	if(email.equals("") || mdp.equals("")) {
    		JOptionPane.showMessageDialog(this, "Veuillez remplir vos identifiants !");
    	}
    	//Sinon on vérifie que les identifiants de connexion sont corrects
    	else {
    		Agent unAgent= C_Agent.selectWhereAgent(email, mdp);
    		//On vérifie si l'agent existe
    		if(unAgent==null) {
    			JOptionPane.showMessageDialog(this, "Veuillez remplir vos identifiants !");
    		}
    		else {
    			JOptionPane.showMessageDialog(this, "Bienvenue Mr./Mme. "+unAgent.getNom()+" - "+unAgent.getPrenom());
    			this.txtEmail.setText("");
    			this.txtMdp.setText("");
    			Sony_Music.gererVueConnexion(false);
    			Sony_Music.gererVueGenerale(true, unAgent);
    		}
    	}
    }
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btAnnuler) {
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}
		else if(e.getSource()==this.btSeConnecter) {
			this.traitement();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar()== KeyEvent.VK_ENTER) {
			this.traitement();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
