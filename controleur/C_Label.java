package controleur;

import java.util.ArrayList;

import modele.ModeleLabel;

public class C_Label {
	public static void insertLabel (Label unLabel) {
		//on controle les donn�es
		ModeleLabel.insertLabel(unLabel);
	}
	public static ArrayList<Label> selectAllLabels() {
		//on controle les donn�es
		return ModeleLabel.selectAllLabels();
	}
	public static void deleteLabel (int idlabel) {
		//on controle les donn�es
		ModeleLabel.deleteLabel(idlabel);;
	}
	public static void updateLabel (Label unLabel) {
		//on controle les donn�es
		ModeleLabel.updateLabel(unLabel);
	}
	public static Label selectWhereLabel(int idLabel) {
		//on controle les donn�es
		return ModeleLabel.selectWhereLabel(idLabel);
	}
	public static Label selectWhereLabel(String email, String mdp) {
		//on controle les donn�es
		return ModeleLabel.selectWhereLabel(email, mdp);
	}
}
