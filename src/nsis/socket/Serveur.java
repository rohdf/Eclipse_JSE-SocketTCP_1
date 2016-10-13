package nsis.socket;
import java.util.*;

import nsis.dico.Dico;

import java.io.*; import java.net.*;
public class Serveur{
 ServerSocket serverSocket;
 Socket clientSocket;
 Dico dico;
 int nbClient;

 public Serveur(String cheminFichierDico){
 nbClient = 0;
 //chargement du dictionnaire
 dico = new Dico(cheminFichierDico);
 try {
	dico.charger();
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
 try{
 System.out.println("Lancement du serveur");
 //association d'un port au service
 serverSocket = new ServerSocket(5000);
 while(nbClient<4){
 nbClient++;
 System.out.println("\nJe suis en attente d'un client");
 //création d'une connexion
 clientSocket = serverSocket.accept();
 //ouverture du flux d'écriture du serveur
 BufferedWriter ecriture = new BufferedWriter(
 new OutputStreamWriter(clientSocket.getOutputStream()));
 //ouverture du flux de lecture du serveur
 BufferedReader lecture = new BufferedReader(
 new InputStreamReader(clientSocket.getInputStream()));
 //lecture du produit demandé.
 String clef = lecture.readLine();
 System.out.println("Recherche sur la clef : " + clef);
 //recherche du prix dans le dictionnaire
 String valeur = dico.consulter(clef);
 System.out.println("Retour (" + clef + "," + valeur + ")");
 ecriture.write(valeur);
 ecriture.newLine();
 ecriture.flush();
 }
 System.out.println("\nJ'ai eu assez de clients");
 }
 catch(IOException e) {e.printStackTrace();}
 }
}