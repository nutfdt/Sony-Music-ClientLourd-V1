package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Agent;
import controleur.C_Agent;
import controleur.C_Label;
import controleur.Label;
import outils.rounded;

public class PanelProfil extends PanelPrincipal implements ActionListener{
	private JTextArea txtProfil= new JTextArea();
	private JPanel panelUpdate= new JPanel();
	private JButton btAnnuler= new JButton("Annuler");
	private JButton btModifier= new JButton("Modifier");
	private JTextField txtNom= new JTextField();
	private JTextField txtEmail= new JTextField();
	private JTextField txtTel= new JTextField();
	private JTextField txtPrenom= new JTextField();
	private JTextField txtDateEmbauche= new JTextField();
	private JPasswordField txtMdp= new JPasswordField();
	
	private JButton btModifierProfil= new JButton("Modifier Profil");
	private boolean visible;
	private JComboBox<String> cbxIdLabel= new JComboBox<String>();
	private int idAgent;
	
	
	
	public PanelProfil(Agent unAgent) {
		super(new Color(1, 89, 162));
		
		this.idAgent=unAgent.getIduser();
		//Positionnement visualisation profil
		String message="";
		message="\t Informations de l'Agent: ";
		message= message+"\n\nNom: "+unAgent.getNom();
		message= message+"\nPrénom: "+unAgent.getPrenom();
		message= message+"\nE-mail: "+unAgent.getEmail();
		message=message+"\nTéléphone: "+unAgent.getTelephone();
		message=message+"\nDate d'embauche: "+unAgent.getDateEmbauche();
		message=message+"\nId Label: "+unAgent.getIdlabel();
		
		this.txtProfil.setBounds(20, 40, 450, 300);
		this.txtProfil.setText(message);
		Font unePolice= new Font("Arial", Font.BOLD, 14);
		this.txtProfil.setFont(unePolice);
		this.txtProfil.setBackground(new Color(1, 89, 162));
		this.txtProfil.setForeground(Color.white);
		
		
		//Design bouton
		this.designBoutton(this.btModifier);
		this.designBoutton(this.btModifierProfil);
		this.designBoutton(this.btAnnuler);
		
		
		//Construction du panel de modification de profil
		this.panelUpdate.setBounds(500, 40, 400, 300);
		this.panelUpdate.setLayout(new GridLayout(8,2));
		//1er ligne
		JLabel nom= new JLabel("Nom: ");
		nom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		nom.setForeground(Color.white);
		this.panelUpdate.add(nom);
		this.panelUpdate.add(this.txtNom);
		//2e ligne
		JLabel prenom= new JLabel("Prénom: ");
		prenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		prenom.setForeground(Color.white);
		this.panelUpdate.add(prenom);
		this.panelUpdate.add(this.txtPrenom);
		//3e ligne
		JLabel email= new JLabel("Email: ");
		email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		email.setForeground(Color.white);
		this.panelUpdate.add(email);
		this.panelUpdate.add(this.txtEmail);
		//4e ligne
		JLabel mdp= new JLabel("Mot de passe: ");
		mdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		mdp.setForeground(Color.white);
		this.panelUpdate.add(mdp);
		this.panelUpdate.add(this.txtMdp);
		//5e ligne
		JLabel tel= new JLabel("Téléphone: ");
		tel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		tel.setForeground(Color.white);
		this.panelUpdate.add(tel);
		this.panelUpdate.add(this.txtTel);
		//6e ligne
		JLabel dateEmbauche= new JLabel("Date d'embauche: ");
		dateEmbauche.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		dateEmbauche.setForeground(Color.white);
		this.panelUpdate.add(dateEmbauche);
		this.panelUpdate.add(this.txtDateEmbauche);
		//7e ligne
		JLabel idLabel= new JLabel("Id Label: ");
		idLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		idLabel.setForeground(Color.white);
		this.panelUpdate.add(idLabel);
		this.panelUpdate.add(this.cbxIdLabel);
		//this.panelUpdate.add(this.txtIdLabel);
		
		//8e ligne
		this.panelUpdate.add(this.btAnnuler);
		this.panelUpdate.add(this.btModifier);
		this.panelUpdate.setBackground(new Color(1, 89, 162));
		
		this.add(this.panelUpdate);
		this.panelUpdate.setVisible(false);
		this.visible=false;
		
		//Ajout des boutons modifier et annuler
		this.btModifierProfil.setBounds(150, 340, 150, 30);
		this.add(this.btModifierProfil);
		
		this.add(this.txtProfil);
		
		
		//Rendre les boutons cliquables
		this.btAnnuler.addActionListener(this);
		this.btModifier.addActionListener(this);
		this.btModifierProfil.addActionListener(this);
		
		//Remplir les champs avec les infos
		this.txtNom.setText(unAgent.getNom());
		this.txtPrenom.setText(unAgent.getPrenom());
		this.txtEmail.setText(unAgent.getEmail());
		this.txtTel.setText(unAgent.getTelephone());
		this.txtMdp.setText(unAgent.getMdp());
		this.txtDateEmbauche.setText(unAgent.getDateEmbauche());
		
		//Le getIdLabel est un int on le veut en String
		//Remplir les ComboBox
		this.remplirCBX(unAgent);
	}
	
	public void remplirCBX(Agent unAgent) {
		this.cbxIdLabel.removeAllItems();
		ArrayList<Label> lesLabels= C_Label.selectAllLabels();
		for(Label unLabel: lesLabels) {
			if(unAgent.getIdlabel()==unLabel.getIdlabel()) {
				this.cbxIdLabel.addItem(unLabel.getIdlabel()+"- "+unLabel.getNom()+", "+unLabel.getAdresse());
			}
		}
	}
	
	public boolean verificationDate(String date) {
		boolean result=false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
		    dateFormat.parse(date);
		    //La date est dans un format valide
		    result=true;
		} catch (ParseException e) {
		    //La date n'est pas dans un format valide
			JOptionPane.showMessageDialog(this, "La date donnée n'est pas au bon format\n(yyyy-mm-dd)",
					"Erreur", JOptionPane.ERROR_MESSAGE);
		}
		return result;
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btModifierProfil) {
			if(this.visible==false) {
				this.panelUpdate.setVisible(true);
				this.visible=true;
			}
			else {
				this.panelUpdate.setVisible(false);
				this.visible=false;
			}
		}
		
		else if(e.getSource()==this.btAnnuler) {
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtMdp.setText("");
			this.txtTel.setText("");
			this.txtDateEmbauche.setText("");
		}
		else if(e.getSource()==this.btModifier) {
			String nom= this.txtNom.getText();
			String prenom= this.txtPrenom.getText();
			String email= this.txtEmail.getText();
			String telephone= this.txtTel.getText();
			String mdp= new String( this.txtMdp.getPassword());
			//On récupère l'IdAgent séléctionné dans le ComboBox
			String chaine=this.cbxIdLabel.getSelectedItem().toString();
			String tab[]= chaine.split("-");
			int idLabel=Integer.parseInt(tab[0].trim());
			
			//On récupère la date D'embauche
			String dateEmbauche=this.txtDateEmbauche.getText();
			
			if(this.verificationDate(dateEmbauche)==true) {
				Agent updateAgent= new Agent(this.idAgent, nom, email, mdp, telephone, "agent", prenom, dateEmbauche, idLabel);
				C_Agent.updateAgent(updateAgent);
				JOptionPane.showMessageDialog(this, "Modification réussie !");
				this.panelUpdate.setVisible(false);
				this.visible=false;
				//Positionnement visualisation profil
				String message="";
				message="\t Informations de l'Agent: ";
				message= message+"\n\nNom: "+nom;
				message= message+"\nPrénom: "+prenom;
				message= message+"\nE-mail: "+email;
				message=message+"\nTéléphone: "+telephone;
				message=message+"\nDate d'embauche: "+dateEmbauche;
				message=message+"\nId Label: "+idLabel;
				this.txtProfil.setText(message);
			}
			
			
			
		}
		
	}
	
}
