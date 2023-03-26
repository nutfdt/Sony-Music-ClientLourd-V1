package vue;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Agent;
import controleur.C_Agent;
import controleur.C_Label;
import controleur.Label;
import controleur.Tableau;
import outils.Date;
import outils.rounded;


public class PanelAgents extends PanelPrincipal implements ActionListener{
	
	private JPanel panelForm= new JPanel();
	private JPanel panelTable= new JPanel();
	
	private JButton btAnnuler= new JButton("Annuler");
	private JButton btEnregistrer= new JButton("Enregistrer");
	
	private JTextField txtNom= new JTextField();
	private JTextField txtPrenom= new JTextField();
	private JTextField txtEmail= new JTextField();
	private JTextField txtTel= new JTextField();
	private JTextField txtDateEmbauche= new JTextField();
	private JComboBox<String> cbxIdLabel= new JComboBox<String>();
	private JPasswordField txtMdp= new JPasswordField();
	private JTable tableAgents;
	
	private Tableau unTableau;
	private Date uneDate;
	private Object[] optionsPane = {"Supprimer", "Modifier", "Annuler"};
	
	
	public PanelAgents() {
		super(new Color(1, 89, 162));
		
		//Design buton
		this.designBoutton(this.btEnregistrer);
		this.designBoutton(this.btAnnuler);
		
		
		//Construction du PanelForm
		this.panelForm.setBounds(10, 40, 300, 300);
		this.panelForm.setBackground(new Color(1, 89, 162));
		this.panelForm.setLayout(new GridLayout(8,2));
		//1er ligne
		JLabel nom= new JLabel("Nom: ");
		nom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		nom.setForeground(Color.white);
		this.panelForm.add(nom);
		this.panelForm.add(this.txtNom);
		//2e ligne
		JLabel prenom= new JLabel("Prénom: ");
		prenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		prenom.setForeground(Color.white);
		this.panelForm.add(prenom);
		this.panelForm.add(this.txtPrenom);
		//3e ligne
		JLabel email= new JLabel("Email: ");
		email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		email.setForeground(Color.white);
		this.panelForm.add(email);
		this.panelForm.add(this.txtEmail);
		//4e ligne
		JLabel mdp= new JLabel("Mot de passe: ");
		mdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		mdp.setForeground(Color.white);
		this.panelForm.add(mdp);
		this.panelForm.add(this.txtMdp);
		//5e ligne
		JLabel tel= new JLabel("Téléphone: ");
		tel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		tel.setForeground(Color.white);
		this.panelForm.add(tel);
		this.panelForm.add(this.txtTel);
		//6e ligne
		JLabel dateEmbauche= new JLabel("Date d'embauche: ");
		dateEmbauche.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		dateEmbauche.setForeground(Color.white);
		this.panelForm.add(dateEmbauche);
		this.panelForm.add(this.txtDateEmbauche);
		//7e ligne
		JLabel idLabel= new JLabel("Id Label: ");
		idLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		idLabel.setForeground(Color.white);
		this.panelForm.add(idLabel);
		this.panelForm.add(this.cbxIdLabel);
		//8e ligne
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm);
		this.panelForm.setVisible(true);
		
		//Construction du panelTable
		this.panelTable.setBounds(300,  30,  750,  300);
		this.panelTable.setBackground(new Color(1, 89, 162));
		this.panelTable.setLayout(null);
		String entetes[]= {"IdAgent", "Nom", "Prénom", "Email", "Téléphone", "dateEmbauche", "IdLabel"};
		this.unTableau= new Tableau(this.obtenirAgents(), entetes);
		this.tableAgents= new JTable(this.unTableau);
		JScrollPane uneScroll= new JScrollPane(this.tableAgents);
		uneScroll.setBounds(20, 20, 700, 250);
		this.panelTable.add(uneScroll);
		this.add(this.panelTable);
		
		//Rendre les boutons cliquables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		this.remplirCbxLabel();
		
		this.tableAgents.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne=0;
				if(e.getClickCount()==1) {
					numLigne=tableAgents.getSelectedRow();
					int choice = JOptionPane.showOptionDialog(null,
						    "Que voulez-vous faire ?",
						    "Choix enregistrement",
						    JOptionPane.YES_NO_CANCEL_OPTION,
						    JOptionPane.QUESTION_MESSAGE,
						    null,
						    optionsPane,
						    optionsPane[2]);
					if(choice==JOptionPane.YES_OPTION) {
						//Supprimer
						int idAgent=Integer.parseInt(unTableau.getValueAt(numLigne, 0)+"");
						//Suppression de l'agent dans la BDD
						C_Agent.deleteAgent(idAgent);
						//Suppression dans le tableau
						unTableau.supprimerLigne(numLigne);
					}
					else if(choice==JOptionPane.NO_OPTION) {
						//Modifier
						int idAgent=Integer.parseInt(unTableau.getValueAt(numLigne, 0)+"");
						Agent unAgent= C_Agent.selectWhereAgent(idAgent);
						txtNom.setText(unAgent.getNom());
						txtEmail.setText(unAgent.getEmail());
						txtMdp.setText(unAgent.getMdp());
						txtTel.setText(unAgent.getTelephone());
						txtPrenom.setText(unAgent.getPrenom());
						txtDateEmbauche.setText(unAgent.getDateEmbauche());
						btEnregistrer.setText("Modifier");
					}
				}
				
			}
		});
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
	
	public void remplirCbxLabel() {
		this.cbxIdLabel.removeAllItems();
		ArrayList<Label> lesLabels=C_Label.selectAllLabels();
		for(Label unLabel: lesLabels) {
			this.cbxIdLabel.addItem(unLabel.getIdlabel()+"- "+unLabel.getNom());;
		}
	}
	
	public Object[][] obtenirAgents(){
		ArrayList<Agent> lesAgents= C_Agent.selectAllAgents();
		Object[][] matrice= new Object[lesAgents.size()][7];
		int i=0;
		for (Agent unAgent: lesAgents) {
			matrice[i][0]=unAgent.getIduser();
			matrice[i][1]=unAgent.getNom();
			matrice[i][2]=unAgent.getPrenom();
			matrice[i][3]=unAgent.getEmail();
			matrice[i][4]=unAgent.getTelephone();
			matrice[i][5]=unAgent.getDateEmbauche();
			matrice[i][6]=unAgent.getIdlabel();
			i++;
		}
		
		return matrice;
	}
	
	public void viderChamps() {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtEmail.setText("");
		this.txtMdp.setText("");
		this.txtTel.setText("");
		this.txtDateEmbauche.setText("");
		this.btEnregistrer.setText("Enregistrer");
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
		if(result==true) {
			int year=Integer.parseInt(date.split("-")[0]);
			int month=Integer.parseInt(date.split("-")[1]);
			int day=Integer.parseInt(date.split("-")[2]);
			this.uneDate= new Date(year, month, day);
			//On appelle la méthode verifDate dans la classe Date pour vérifié que la date rentrée est bonne 
			
			if(!uneDate.verifDate(year, month, day)) {
				JOptionPane.showMessageDialog(this, "La date donnée n'est pas correcte");
				result=false;
			}
		}
		return result;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btAnnuler) {
			this.viderChamps();
		}
		else if(e.getSource()==this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			String nom= this.txtNom.getText();
			String prenom= this.txtPrenom.getText();
			String email= this.txtEmail.getText();
			String mdp= new String(this.txtMdp.getPassword());
			String tel= this.txtTel.getText();
			String dateEmbauche=this.txtDateEmbauche.getText();
			
			
			//On vérifie la date d'embauche
			if(this.verificationDate(dateEmbauche)) {
				//On récupère l'IdLabel
				String chaine=this.cbxIdLabel.getSelectedItem().toString();
				String tab[]=chaine.split("-");
				int idLabel=Integer.parseInt(tab[0]);
				
				//On enregistre dans la Bdd
				Agent unAgent= new Agent(nom, email, mdp, tel, "agent",prenom, dateEmbauche, idLabel);
				C_Agent.insertAgent(unAgent);
				//On enregistre dans le tableau
				unAgent=C_Agent.selectWhereAgent(email, mdp);
				int idAgent=unAgent.getIduser();
				Object[] ligne= {idAgent, nom, prenom, email, tel, dateEmbauche, idLabel};
				this.unTableau.insererLigne(ligne);
				JOptionPane.showMessageDialog(this, "Insertion réussie !");
			}
			
			
			
		}
		else if(e.getSource()==this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String nom= this.txtNom.getText();
			String prenom= this.txtPrenom.getText();
			String email= this.txtEmail.getText();
			String mdp= new String(this.txtMdp.getPassword());
			String tel= this.txtTel.getText();
			String dateEmbauche=this.txtDateEmbauche.getText();
			
			//On vérifie la date d'embauche
			if(this.verificationDate(dateEmbauche)) {
				//On récupère l'IdLabel
				String chaine=this.cbxIdLabel.getSelectedItem().toString();
				String tab[]=chaine.split("-");
				int idLabel=Integer.parseInt(tab[0]);
				
				int numLigne=this.tableAgents.getSelectedRow();
				int idAgent=Integer.parseInt(this.unTableau.getValueAt(numLigne, 0)+"");
				
				//Update dans la BDD
				Agent unAgent= new Agent(idAgent, nom, email, mdp, tel, "agent", prenom, dateEmbauche, idLabel);
				C_Agent.updateAgent(unAgent);
				//Update dans le tableau
				Object[] ligne= {idAgent, nom, prenom, email, tel, dateEmbauche, idLabel};
				this.unTableau.modifierLigne(numLigne, ligne);
				JOptionPane.showMessageDialog(this, "Modification réussie !");
				this.viderChamps();
			}
			
		}
		
	}
}
