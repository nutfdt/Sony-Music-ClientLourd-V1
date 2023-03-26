package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameListener;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

import controleur.C_Album;
import controleur.C_Artiste;
import controleur.Tableau;
import outils.rounded;
import controleur.Album;
import controleur.Artiste;

public class PanelAlbums extends PanelPrincipal implements ActionListener{

	private JPanel panelForm = new JPanel();
    private JPanel panelTable = new JPanel();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btEnregistrer = new JButton("Enregistrer");
    private JTextField txtNom = new JTextField();
	private JTextField txtAnneeSortie = new JTextField();


	private JComboBox<String> cbxIdArtiste = new JComboBox<String>();


    // Déclaration de table des artistes
    private JTable tableAlbums;
    private Tableau unTableau;
    private Object[] optionsPane = {"Supprimer", "Modifier", "Annuler"};

    public PanelAlbums() {
		super (new Color(1, 89, 162));
		
		//Design des butons
		this.designBoutton(this.btEnregistrer);
		this.designBoutton(this.btAnnuler);

        // Construction du PanelForm
        this.panelForm.setBounds(10, 50, 300, 180);
        this.panelForm.setBackground(new Color(1, 89, 162));
        this.panelForm.setLayout(new GridLayout(4,2));
        JLabel nom= new JLabel("Nom: ");
		nom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		nom.setForeground(Color.white);
        this.panelForm.add(nom);
        this.panelForm.add(this.txtNom);
        JLabel anneeSortie= new JLabel("Année de Sortie: ");
        anneeSortie.setForeground(Color.white);
		anneeSortie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		this.panelForm.add(anneeSortie);
        this.panelForm.add(this.txtAnneeSortie);
        JLabel idArtiste= new JLabel("Id Artiste: ");
		idArtiste.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		idArtiste.setForeground(Color.white);
        this.panelForm.add(idArtiste);
        this.panelForm.add(this.cbxIdArtiste);
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btEnregistrer);
        this.panelForm.setVisible(true);
        
        this.remplirCbxArtiste();

        this.add(this.panelForm);

        // Construction du PanelTable
        this.panelTable.setBounds(300, 30, 750, 300);
        this.panelTable.setBackground(new Color(1, 89, 162));
        this.panelTable.setLayout(null);
        String entetes [] = {"IdAlbum", "Nom", "AnneeSortie", "IdArtiste"};

        //Instanciation de la classe Tableau
        this.unTableau= new Tableau(this.obtenirAlbums(), entetes);
        this.tableAlbums = new JTable(this.unTableau);
        JScrollPane uneScroll = new JScrollPane(this.tableAlbums);
        uneScroll.setBounds(20, 20, 700, 250);
        this.panelTable.add(uneScroll);
        
        this.add(this.panelTable);

        // Rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btEnregistrer.addActionListener(this);
        
        this.tableAlbums.addMouseListener(new MouseListener() {
			
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
					numLigne=tableAlbums.getSelectedRow();
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
						int idAlbum=Integer.parseInt(unTableau.getValueAt(numLigne, 0)+"");
						//Suppression de l'album dans la BDD
						C_Album.deleteAlbum(idAlbum);
						//Suppression de l'album dans la table
						unTableau.supprimerLigne(numLigne);
					}
					else if(choice==JOptionPane.NO_OPTION) {
						//Modifier
						txtNom.setText(unTableau.getValueAt(numLigne, 1)+"");
						txtAnneeSortie.setText(unTableau.getValueAt(numLigne, 2)+"");
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

    public Object[][] obtenirAlbums() {
        ArrayList<Album> lesAlbums = C_Album.selectAllAlbums();
        Object [][] matrice = new Object [lesAlbums.size()][4];
        int i = 0;
        for (Album unAlbum : lesAlbums)
        {
            matrice[i][0] = unAlbum.getIdalbum();
            matrice[i][1] = unAlbum.getNom();
            matrice[i][2] = unAlbum.getAnneeSortie();
            matrice[i][3] = unAlbum.getIdartiste();
            i++;
        }
        return matrice;
    }

	public void remplirCbxArtiste() {
        this.cbxIdArtiste.removeAllItems();
        ArrayList<Artiste> lesArtistes= C_Artiste.selectAllArtistes();
        for(Artiste unArtiste: lesArtistes) {
        	this.cbxIdArtiste.addItem(unArtiste.getIduser()+"- "+unArtiste.getNomDeScene());
        }
    }

	

    public void viderChamps() {
        this.txtNom.setText("");
		this.txtAnneeSortie.setText("");
		this.btEnregistrer.setText("Enregistrer");
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
            this.viderChamps();
        }
        else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
            String nom = this.txtNom.getText();
            //Récupération de l'IdArtiste
            String chaine=this.cbxIdArtiste.getSelectedItem().toString();
            String tab[]=chaine.split("-");
            int idArtiste=Integer.parseInt(tab[0]);
            
            //On récupère l'année de Sortie
			int anneeSortie = 0;
			//Vérification de l'annee Sortie
			boolean parseAnneeSortie=true;
			try {
				anneeSortie=Integer.parseInt(this.txtAnneeSortie.getText());
			}
			catch(NumberFormatException exp) {
				JOptionPane.showMessageDialog(this, "L'année de sortie indiquée doit être\n un entier !");
				parseAnneeSortie=false;
			}
			//On vérifie que l'année parsée correspond bien à un format année
			if(parseAnneeSortie==true) {
				String temp=String.valueOf(anneeSortie);
				if(temp.length()!=4) {
					parseAnneeSortie=false;
					JOptionPane.showMessageDialog(this, "L'année de sortie ne correspond pas à une année (YYYY)");
				}
				else {
					//Insertion de l'Album dans la BDD
					Album unAlbum= new Album(nom, anneeSortie, idArtiste);
					C_Album.insertAlbum(unAlbum);
					//Insertion de l'Album dans le tableau
					unAlbum=C_Album.selectWhereAlbum(nom, anneeSortie, idArtiste);
					int idAlbum=unAlbum.getIdalbum();
					Object[] ligne={idAlbum, nom, anneeSortie, idArtiste};
					unTableau.insererLigne(ligne);
					JOptionPane.showMessageDialog(this, "Insertion réussie de l'album");
					this.viderChamps();
				}
			}
        }
        else if(e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
        	String nom=this.txtNom.getText();
        	//Récupération de l'IdArtiste
            String chaine=this.cbxIdArtiste.getSelectedItem().toString();
            String tab[]=chaine.split("-");
            int idArtiste=Integer.parseInt(tab[0]);
            
            //On récupère l'année de Sortie
			int anneeSortie = 0;
			//Vérification de l'annee Sortie
			boolean parseAnneeSortie=true;
			try {
				anneeSortie=Integer.parseInt(this.txtAnneeSortie.getText());
			}
			catch(NumberFormatException exp) {
				JOptionPane.showMessageDialog(this, "L'année de sortie indiquée doit être\n un entier !");
				parseAnneeSortie=false;
			}
			//On vérifie que l'année parsée correspond bien à un format année
			if(parseAnneeSortie==true) {
				String temp=String.valueOf(anneeSortie);
				if(temp.length()!=4) {
					parseAnneeSortie=false;
					JOptionPane.showMessageDialog(this, "L'année de sortie ne correspond pas à une année (YYYY)");
				}
				else {
					//On update l'Album
					int numLigne=this.tableAlbums.getSelectedRow();
					int idAlbum=Integer.parseInt(this.unTableau.getValueAt(numLigne, 0)+"");
					//On update dans la Bdd
					Album unAlbum=new Album(idAlbum, nom, anneeSortie, idArtiste);
					C_Album.updateAlbum(unAlbum);
					//On update dans la table
					Object[] ligne= {idAlbum, nom, anneeSortie, idArtiste};
					this.unTableau.modifierLigne(numLigne, ligne);
					JOptionPane.showMessageDialog(this, "Modification réussie !");
					this.viderChamps();
				}
			}
        	
        }
		
	}

	

}
