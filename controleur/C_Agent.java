package controleur;

import java.util.ArrayList;

import modele.ModeleAgent;


public class C_Agent {
	public static void insertAgent (Agent unAgent) {
		//on controle les donn�es
		ModeleAgent.insertAgent(unAgent);
	}
	public static ArrayList<Agent> selectAllAgents() {
		//on controle les donn�es
		return ModeleAgent.selectAllAgents();
	}
	public static void deleteAgent (int idinter) {
		//on controle les donn�es
		ModeleAgent.deleteAgent(idinter);;
	}
	public static void updateAgent (Agent unAgent) {
		//on controle les donn�es
		ModeleAgent.updateAgent(unAgent);
	}
	public static Agent selectWhereAgent(int idAgent) {
		//on controle les donn�es
		return ModeleAgent.selectWhereAgent(idAgent);
	}
	public static Agent selectWhereAgent(String email, String mdp) {
		//on controle les donn�es
		return ModeleAgent.selectWhereAgent(email, mdp);
	}
}
