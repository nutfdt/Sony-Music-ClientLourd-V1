package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import controleur.C_Artiste;
import controleur.Tableau;
import outils.rounded;
import controleur.Agent;
import controleur.Artiste;
import controleur.C_Agent;

public class PanelArtistes extends PanelPrincipal implements ActionListener {

	private JPanel panelForm = new JPanel();
    private JPanel panelTable = new JPanel();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btEnregistrer = new JButton("Enregistrer");
    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JPasswordField txtMdp= new JPasswordField();
    private JTextField txtTel = new JTextField();
	private JTextField txtNomdeScene = new JTextField();
	private JTextField txtTypePrincipal = new JTextField();
	private JComboBox<String> cbxIdAgent= new JComboBox<String>();
	private JTable tableArtistes;
	
	private Tableau unTableau;
	private Object[] optionsPane = {"Supprimer", "Modifier", "Annuler"};

    public PanelArtistes() {
		super (new Color(1, 89, 162));
		
		//Design des boutons
		this.designBoutton(this.btEnregistrer);
		this.designBoutton(this.btAnnuler);

        // Construction du PanelForm
        this.panelForm.setBounds(10, 40, 300, 300);
        this.panelForm.setBackground(new Color(1, 89, 162));
        this.panelForm.setLayout(new GridLayout(9, 2));
        //Ligne 1
        JLabel nom= new JLabel("Nom: ");
		nom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		nom.setForeground(Color.white);
        this.panelForm.add(nom);
        this.panelForm.add(this.txtNom);
        //Ligne 2
        JLabel prenom= new JLabel("Prénom: ");
		prenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		prenom.setForeground(Color.white);
        this.panelForm.add(prenom);
        this.panelForm.add(this.txtPrenom);
        //Ligne 3
        JLabel email= new JLabel("Email: ");
		email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		email.setForeground(Color.white);
        this.panelForm.add(email);
        this.panelForm.add(this.txtEmail);
        //Ligne 4
        JLabel mdp= new JLabel("Mdp: ");
		mdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		mdp.setForeground(Color.white);
        this.panelForm.add(mdp);
        this.panelForm.add(this.txtMdp);
        //Ligne 5
        JLabel tel= new JLabel("Téléphone: ");
		tel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		tel.setForeground(Color.white);
        this.panelForm.add(tel);
        this.panelForm.add(this.txtTel);
        //Ligne 6
        JLabel nomDeScene= new JLabel("Nom de Scene: ");
		nomDeScene.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		nomDeScene.setForeground(Color.white);
		this.panelForm.add(nomDeScene);
        this.panelForm.add(this.txtNomdeScene);
        //Ligne 7
        JLabel typePrincipal= new JLabel("Type Principal: ");
		typePrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		typePrincipal.setForeground(Color.white);
		this.panelForm.add(typePrincipal);
        this.panelForm.add(this.txtTypePrincipal);
        //Ligne 8
        JLabel idAgent= new JLabel("Id Agent: ");
        idAgent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idAgent.setForeground(Color.white);
		this.panelForm.add(idAgent);
        this.panelForm.add(this.cbxIdAgent);
        //Ligne 9
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btEnregistrer);
        this.panelForm.setVisible(true);

        this.add(this.panelForm);

        // Construction du PanelTable
        this.panelTable.setBounds(300, 30, 750, 300);
        this.panelTable.setBackground(new Color(1, 89, 162));
        this.panelTable.setLayout(null);
        String entetes [] = {"IdArtiste", "Nom", "Prénom", "Email", "Téléphone", "Nom de scène", "Type principal", "IdAgent"};
        this.unTableau= new Tableau(this.obtenirArtistes(), entetes);
        this.tableArtistes = new JTable(this.unTableau);
        JScrollPane uneScroll = new JScrollPane(this.tableArtistes);
        uneScroll.setBounds(20, 20, 700, 250);
        this.panelTable.add(uneScroll);
        
        this.add(this.panelTable);

        // Rendre les boutons Ã©coutables
        this.btAnnuler.addActionListener(this);
        this.btEnregistrer.addActionListener(this);
        
        this.remplirCbxAgent();
        this.tableArtistes.addMouseListener(new MouseListener() {
			
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
					numLigne=tableArtistes.getSelectedRow();
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
						int idArtiste=Integer.parseInt(unTableau.getValueAt(numLigne, 0)+"");
						//Supprimer dans BDD
						C_Artiste.deleteArtiste(idArtiste);
						//Supprimer dans le tableau
						unTableau.supprimerLigne(numLigne);
					}
					else if(choice==JOptionPane.NO_OPTION) {
						//Modifier
						int idArtiste=Integer.parseInt(unTableau.getValueAt(numLigne, 0)+"");
						Artiste unArtiste= C_Artiste.selectWhereArtiste(idArtiste);
						txtNom.setText(unArtiste.getNom());
						txtPrenom.setText(unArtiste.getPrenom());
						txtEmail.setText(unArtiste.getEmail());
						txtMdp.setText(unArtiste.getMdp());
						txtTel.setText(unArtiste.getTelephone());
						txtNomdeScene.setText(unArtiste.getNomDeScene());
						txtTypePrincipal.setText(unArtiste.getTypePrincipal());
						btEnregistrer.setText("Modifier");
					}
				}
				
			}
		});
	}
    
    
    public void remplirCbxAgent() {
    	this.cbxIdAgent.removeAllItems();
    	ArrayList<Agent> lesAgents=C_Agent.selectAllAgents();
    	for(Agent unAgent: lesAgents) {
    		this.cbxIdAgent.addItem(unAgent.getIduser()+"- "+unAgent.getNom()+" "+unAgent.getPrenom());
    	}
    }

    public Object[][] obtenirArtistes() {
        ArrayList<Artiste> lesArtistes = C_Artiste.selectAllArtistes();
        Object [][] matrice = new Object [lesArtistes.size()][8];
        int i = 0;
        for (Artiste unArtiste : lesArtistes)
        {
            matrice[i][0] = unArtiste.getIduser();
            matrice[i][1] = unArtiste.getNom();
            matrice[i][2] = unArtiste.getPrenom();
            matrice[i][3] = unArtiste.getEmail();
            matrice[i][4] = unArtiste.getTelephone();
            matrice[i][5] = unArtiste.getNomDeScene();
			matrice[i][6] = unArtiste.getTypePrincipal();
			matrice[i][7] = unArtiste.getIdAgent();
            i++;
        }
        return matrice;
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

    public void viderChamps() {
        this.txtNom.setText("");
        this.txtPrenom.setText("");
        this.txtEmail.setText("");
        this.txtMdp.setText("");
        this.txtTel.setText("");
        this.txtNomdeScene.setText("");
		this.txtTypePrincipal.setText("");
		this.btEnregistrer.setText("Enregistrer");
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
            this.viderChamps();
        }
        else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String mdp=new String(this.txtMdp.getPassword());
            String email = this.txtEmail.getText();
            String telephone = this.txtTel.getText();
            String nomDeScene = this.txtNomdeScene.getText();
			String typePrincipal = this.txtTypePrincipal.getText();

			String chaine=this.cbxIdAgent.getSelectedItem().toString();
			String tab[]=chaine.split("-");
			int idAgent=Integer.parseInt(tab[0]);
			
			//On insère dans la BDD
			Artiste unArtiste= new Artiste(nom, email, mdp, telephone, "artiste", prenom, nomDeScene, typePrincipal, idAgent);
			C_Artiste.insertArtiste(unArtiste);
			//On insère dans le tableau
			unArtiste= C_Artiste.selectWhereArtiste(nom, email, mdp, telephone, prenom, nomDeScene, typePrincipal);
			int idArtiste=unArtiste.getIduser();
			Object[] ligne = {idArtiste, nom, prenom, email, telephone, nomDeScene, typePrincipal, idAgent};
			this.unTableau.insererLigne(ligne);
			JOptionPane.showMessageDialog(this, "Insertion réussie !");
			this.viderChamps();
        }
		
        else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String mdp=new String(this.txtMdp.getPassword());
            String email = this.txtEmail.getText();
            String telephone = this.txtTel.getText();
            String nomDeScene = this.txtNomdeScene.getText();
			String typePrincipal = this.txtTypePrincipal.getText();

			String chaine=this.cbxIdAgent.getSelectedItem().toString();
			String tab[]=chaine.split("-");
			int idAgent=Integer.parseInt(tab[0]);
			
			int numLigne=this.tableArtistes.getSelectedRow();
			int idArtiste=Integer.parseInt(this.unTableau.getValueAt(numLigne, 0)+"");
			
			//On modifie dans la BDD
			Artiste unArtiste=new Artiste(idArtiste, nom, email, mdp, telephone, "artiste", prenom ,nomDeScene, typePrincipal, idAgent);
			C_Artiste.updateArtiste(unArtiste);
			
			
			//Modifie dans le tableau
			Object[] ligne = {idArtiste, nom, prenom, email, telephone, nomDeScene, typePrincipal, idAgent};
			this.unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification réussie !");
			this.viderChamps();
        }
	}
}
