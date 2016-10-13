package nsis.socket;
import java.io.*;
import java.net.*;

public class Client {
Socket clientSocket;
 String nomDeLaPersonne;
 String nomServeur;
InetAddress adresse;

 public Client(String idServeur,String idPersonne){
 nomDeLaPersonne = idPersonne;
 nomServeur = idServeur;
 try{
 System.out.println("Le client à lui même - Je souhaite connaître le numéro de : "+nomDeLaPersonne);
 adresse = InetAddress.getByName(nomServeur);
 clientSocket = new Socket(adresse,5000);

 //ouverture du flux d'écriture du client
 BufferedWriter ecriture = new BufferedWriter(
 new OutputStreamWriter(clientSocket.getOutputStream()));
 //ouverture du flux de lecture du client
 BufferedReader lecture = new BufferedReader(
 new InputStreamReader(clientSocket.getInputStream()));

 //écriture dans le flux de la question posée au serveur
 ecriture.write(nomDeLaPersonne);
 ecriture.newLine();
 ecriture.flush();
 System.out.println("Numéro de " + nomDeLaPersonne + " ?");
 System.out.println("Réponse: " + lecture.readLine());

 //fermeture des flux
 ecriture.close();
 lecture.close();
 //fermeture de la connexion
 clientSocket.close();
 }catch(UnknownHostException e) {e.printStackTrace();}
 catch(IOException e) {e.printStackTrace();}
 }
}
