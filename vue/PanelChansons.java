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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Album;
import controleur.C_Album;
import controleur.C_Categorie;
import controleur.C_Chanson;
import controleur.Categorie;
import controleur.Chanson;
import controleur.Tableau;
import outils.rounded;

public class PanelChansons extends PanelPrincipal implements ActionListener{
	private JPanel panelForm= new JPanel();
	private JPanel panelTable= new JPanel();
	
	
	private JButton btAnnuler= new JButton("Annuler");
	private JButton btEnregistrer= new JButton("Enregistrer");
	
	private JTextField txtTitre= new JTextField();
	private JTextField txtSortie= new JTextField();
	private JTextField txtDuree= new JTextField();
	private JTextField txtLangue= new JTextField();
	private JComboBox<String> cbxIdCategorie= new JComboBox<String>();
	private JComboBox<String> cbxIdAlbum= new JComboBox<String>();
	private JTable tableChansons;
	
	private Tableau unTableau;
	private Object[] optionsPane = {"Supprimer", "Modifier", "Annuler"};
	
	//Déclaration de table des Chansons
	
	public PanelChansons() {
		super(new Color(1, 89, 162));
		
		//Design des boutons
		this.designBoutton(this.btEnregistrer);
		this.designBoutton(this.btAnnuler);
		
		//Construction du formulaire
		this.panelForm.setBounds(10, 40, 300, 300);
		this.panelForm.setBackground(new Color(1, 89, 162));
        this.panelForm.setLayout(new GridLayout(7, 2));
        //Ligne 1
        JLabel titre= new JLabel("Titre: ");
		titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		titre.setForeground(Color.white);
        this.panelForm.add(titre);
        this.panelForm.add(this.txtTitre);
        //Ligne 2
        JLabel sortie= new JLabel("Date de Sortie: ");
        sortie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sortie.setForeground(Color.white);
        this.panelForm.add(sortie);
        this.panelForm.add(this.txtSortie);
        //Ligne 3
        JLabel duree= new JLabel("Durée: ");
        duree.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        duree.setForeground(Color.white);
        this.panelForm.add(duree);
        this.panelForm.add(this.txtDuree);
        
        JLabel langue= new JLabel("Langue: ");
        duree.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        duree.setForeground(Color.white);
        this.panelForm.add(langue);
        this.panelForm.add(this.txtLangue);
        //Ligne 4
        JLabel idcategorie= new JLabel("Id Catégorie: ");
        idcategorie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idcategorie.setForeground(Color.white);
		this.panelForm.add(idcategorie);
        this.panelForm.add(this.cbxIdCategorie);
        //Ligne 5
        JLabel idalbum= new JLabel("Id Album: ");
        idalbum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idalbum.setForeground(Color.white);
		this.panelForm.add(idalbum);
        this.panelForm.add(this.cbxIdAlbum);
        //Ligne 6
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btEnregistrer);
        this.panelForm.setVisible(true);
        
        
        this.add(this.panelForm);
        
        
       
        
            
        
        //Construction du JTable
        this.panelTable.setBounds(350, 70, 8000, 350);
        this.panelTable.setBackground(new Color(1, 89, 162));
        this.panelTable.setLayout(null);
        String entetes [] = {"IdChanson", "Titre", "Sortie", "Durée", "IdCatégorie", "IdAlbum", "Langue"};
        this.unTableau= new Tableau(this.obtenirChansons(), entetes);
        this.tableChansons = new JTable(this.unTableau);
        JScrollPane uneScroll = new JScrollPane(this.tableChansons);
        uneScroll.setBounds(70, 70, 750, 300);
        this.panelTable.add(uneScroll);
        
        this.add(this.panelTable);
        
        //Rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btEnregistrer.addActionListener(this);
        this.remplirCbx();
        
        this.tableChansons.addMouseListener(new MouseListener() {
			
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
					numLigne=tableChansons.getSelectedRow();
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
						int idChanson=Integer.parseInt(unTableau.getValueAt(numLigne, 0)+"");
						//Supprimer dans la BDD
						C_Chanson.deleteChanson(idChanson);
						//Supprimer dans le tableau
						unTableau.supprimerLigne(numLigne);
					}
					
					else if(choice==JOptionPane.NO_OPTION) {
						//Modifier
						int idChanson=Integer.parseInt(unTableau.getValueAt(numLigne, 0)+"");
						Chanson uneChanson= C_Chanson.selectWhereChanson(idChanson);
						txtTitre.setText(uneChanson.getTitre());
						txtSortie.setText(uneChanson.getDate());
						txtDuree.setText(uneChanson.getDuree());
						txtDuree.setText(uneChanson.getLangue());
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
	
	public void remplirCbx() {
		this.cbxIdAlbum.removeAllItems();
		this.cbxIdCategorie.removeAllItems();
		ArrayList<Album> lesAlbums= C_Album.selectAllAlbums();
		ArrayList<Categorie> lesCategories= C_Categorie.selectAllCategories();
		for (Album unAlbum: lesAlbums) {
			this.cbxIdAlbum.addItem(unAlbum.getIdalbum()+"- "+unAlbum.getNom());
		}
		for (Categorie uneCategorie: lesCategories) {
			this.cbxIdCategorie.addItem(uneCategorie.getIdcategorie()+"- "+uneCategorie.getType());
	}
	}
	
	
	
	public Object[][] obtenirChansons(){
		ArrayList<Chanson> lesChansons= C_Chanson.selectAllChansons();
		Object[][] matrice= new Object[lesChansons.size()][7];
		int i=0;
		for(Chanson uneChanson : lesChansons) {
			matrice[i][0]= uneChanson.getIdchanson();
			matrice[i][1]= uneChanson.getTitre();
			matrice[i][2]= uneChanson.getDate();
			matrice[i][3]= uneChanson.getDuree();
			matrice[i][4]= uneChanson.getIdcategorie();
			matrice[i][5]= uneChanson.getIdalbum();
			matrice[i][6]= uneChanson.getLangue();
			
			i++;
		}
		return matrice;
	}
	
	public void viderChamps() {
		this.txtTitre.setText("");
		this.txtSortie.setText("");
		this.txtDuree.setText("");
		this.txtLangue.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btAnnuler) {
			this.viderChamps();
		}
		else if(e.getSource()==this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			String titre=this.txtTitre.getText();
			String sortie=this.txtSortie.getText();
			String duree=this.txtDuree.getText();
			String langue=this.txtLangue.getText();
			
			//On récupère l'Id Album
			String chaine= this.cbxIdAlbum.getSelectedItem().toString();
			String tab[]= chaine.split("-");
			int idAlbum=Integer.parseInt(tab[0]);
			
			//On récupère l'Id Catégorie
			chaine=this.cbxIdCategorie.getSelectedItem().toString();
			tab=chaine.split("-");
			int idCategorie= Integer.parseInt(tab[0]);
			
			//On insère dans la BDD
			Chanson uneChanson= new Chanson(titre, sortie, duree, idCategorie, idAlbum, langue);
			C_Chanson.insertChanson(uneChanson);
			//On insère dans le tableau
			uneChanson=C_Chanson.selectWhereChanson(titre, sortie, duree, idAlbum, idCategorie, langue);
			int idChanson=uneChanson.getIdchanson();
			
			Object[] ligne= {idChanson, titre, sortie, duree, idCategorie, idAlbum, langue};
			this.unTableau.insererLigne(ligne);
			JOptionPane.showMessageDialog(this, "Insertion réussie !");
			this.viderChamps();
		}
		
		else if(e.getSource()==this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String titre=this.txtTitre.getText();
			String sortie=this.txtSortie.getText();
			String duree=this.txtDuree.getText();
			String langue=this.txtLangue.getText();
			
			//On récupère l'Id Album
			String chaine= this.cbxIdAlbum.getSelectedItem().toString();
			String tab[]= chaine.split("-");
			int idAlbum=Integer.parseInt(tab[0]);
			
			//On récupère l'Id Catégorie
			chaine=this.cbxIdCategorie.getSelectedItem().toString();
			tab=chaine.split("-");
			int idCategorie= Integer.parseInt(tab[0]);
			
			int numLigne= this.tableChansons.getSelectedRow();
			int idChanson=Integer.parseInt(this.unTableau.getValueAt(numLigne, 0)+"");
			
			//Modifie dans la BDD
			Chanson uneChanson=new Chanson(titre, sortie, duree, idCategorie, idAlbum, langue);
			C_Chanson.updateChanson(uneChanson);
			
			//On modifie dans le tableau
			Object[] ligne= {idChanson, titre, sortie, duree, idCategorie, idAlbum, langue};
			this.unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification réussie !");
			this.viderChamps();
		}
		
	}
}

	
