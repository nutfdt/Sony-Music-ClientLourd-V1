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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.C_Categorie;
import controleur.Categorie;
import controleur.Tableau;
import outils.rounded;

public class PanelCategories extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JTextField txtType = new JTextField();
	private JTable tableCategories;
	
	private Tableau unTableau;
	private Object[] optionsPane = {"Supprimer", "Modifier", "Annuler"};
	
	public PanelCategories() {
		super(new Color(1, 89, 162));
		
		//Design button
		this.designBoutton(this.btEnregistrer);
		this.designBoutton(this.btAnnuler);
		
		
		//Construction du formulaire d'ajout
		this.panelForm.setBounds(10, 40, 300, 100);
		this.panelForm.setBackground(new Color(1, 89, 162));
		this.panelForm.setLayout(new GridLayout(2, 2));
		//Ligne 1
		JLabel type= new JLabel("Type: ");
		type.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		type.setForeground(Color.white);
        this.panelForm.add(type);
		this.panelForm.add(this.txtType);
		//Ligne 2
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.setVisible(true);
		this.add(this.panelForm);
		
		
		
		//construction panel table :
		this.panelTable.setBounds(300, 30, 750, 300);
		this.panelTable.setBackground(new Color(1, 89, 162));
		this.panelTable.setLayout(null);
		String entetes[]= {"IdCatégorie", "Type"};
		this.unTableau= new Tableau(this.obtenirCategories(), entetes);
		this.tableCategories = new JTable(this.unTableau);
		JScrollPane uneScroll= new JScrollPane(this.tableCategories);
		uneScroll.setBounds(20, 20, 700, 250);
		this.panelTable.add(uneScroll);
		this.add(this.panelTable);
		
		//rendre les bouton ecoutable
		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		this.tableCategories.addMouseListener(new MouseListener() {
			
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
					numLigne=tableCategories.getSelectedRow();
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
						int idCategorie=Integer.parseInt(unTableau.getValueAt(numLigne, 0)+"");
						
						//Supprimer dans la BDD
						C_Categorie.deleteCategorie(idCategorie);
						//Supprimer dans le tableau
						unTableau.supprimerLigne(numLigne);
						viderChamps();
					}
					else if(choice==JOptionPane.NO_OPTION) {
						//Modifier
						int idCategorie=Integer.parseInt(unTableau.getValueAt(numLigne, 0)+"");
						Categorie uneCategorie= C_Categorie.selectWhereCategorie(idCategorie);
						txtType.setText(uneCategorie.getType());
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
	
	
		public Object[][] obtenirCategories()
		{
			ArrayList<Categorie> lesCategories = C_Categorie.selectAllCategories();
			Object[][] matrice = new Object [lesCategories.size()][2];
			int i = 0;
			for (Categorie uneCategorie : lesCategories)
			{
				matrice[i][0] = uneCategorie.getIdcategorie();
				matrice[i][1] = uneCategorie.getType();
				i++;
			}
			return matrice;
		}
		
		
		
		public void viderChamps() {
			this.txtType.setText("");
			this.btEnregistrer.setText("Modifier");
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==this.btAnnuler) {
				this.viderChamps();
			}
			else if(e.getSource()==this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer"))
			{
				String type= this.txtType.getText();
				
				//Insère dans la BDD
				Categorie uneCategorie= new Categorie(type);
				C_Categorie.insertCategorie(uneCategorie);
				
				//Insère dans le tableau
				uneCategorie=C_Categorie.selectWhereCategorie(type);
				int idCategorie= uneCategorie.getIdcategorie();
				Object[] ligne= {idCategorie, type};
				this.unTableau.insererLigne(ligne);
				JOptionPane.showMessageDialog(this, "Insertion réussie ! ");
				this.viderChamps();
						
			}	
			
			else if(e.getSource()==this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier"))
			{
				String type= this.txtType.getText();
				
				
				int numLigne=this.tableCategories.getSelectedRow();
				int idCategorie=Integer.parseInt(this.unTableau.getValueAt(numLigne, 0)+"");
				
				//Insère dans la BDD
				Categorie uneCategorie= new Categorie(idCategorie, type);
				C_Categorie.updateCategorie(uneCategorie);
				
				//Insère dans le tableau
				uneCategorie=C_Categorie.selectWhereCategorie(idCategorie);
				Object[] ligne= {idCategorie, type};
				this.unTableau.modifierLigne(numLigne, ligne);
				JOptionPane.showMessageDialog(this, "Modification réussie ! ");
				this.viderChamps();
						
			}	
		}
}
