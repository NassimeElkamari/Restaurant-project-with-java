/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrestaurant;

import java.util.Scanner;
import java.sql.* ;

public class GestionCommande {
    
     public static void passercmd()
    {
        try {
            // A. Enregistrer la classe de pilote
            Class c = Class.forName("com.mysql.cj.jdbc.Driver") ;
            // Protocole de connexion
            String protocole =  "jdbc:mysql:" ;
            // Adresse IP de l’hôte de la base et port
            String ip =  "localhost" ;  // dépend du contexte
            String port =  "3306" ;  // port MySQL par défaut
            // Nom de la base ;
            String nomBase =  "DbRestau" ;  // dépend du contexte
            // Chaîne de connexion
            String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase ;
            // Identifiants de connexion et mot de passe
            String nomConnexion =  "root" ;  // dépend du contexte
            String motDePasse =  "Nassime2002#" ;  // dépend du contexte
            // B. Créer une connexion
            Connection conn = DriverManager.getConnection(
                    conString, nomConnexion, motDePasse) ;

            //Préparation de notre Requête paramétrée
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO commande(idcmd , idclient, date , livraison ) values(?,?,?,?)");
            Scanner s=new Scanner(System.in);
            //System.out.println("Entrer id client : ");
            //int sid=s.nextInt();
            //stmt.setInt(1,sid);
            int nbr=0;
            String choix="oui";
            String livr = "non" ;
            while(choix.equals("oui"))
            {
                System.out.println(" - Entrer > id commande : ");
                int sid=s.nextInt();
                stmt.setInt(1,sid);

                System.out.print("\n   - Entrer > ID client :  ");
                int sidclient=s.nextInt();
                stmt.setInt(2,sidclient);
                long millis=System.currentTimeMillis();

                java.sql.Date sdate = new java.sql.Date(millis);
                System.out.println(  "   -        > Date Commande : " + sdate);
                stmt.setDate(3, sdate);

                System.out.print("  Commande par livraison (oui/non) :  ");
                livr=s.next();
                stmt.setString(4, livr);



                int i=stmt.executeUpdate();
                System.out.println("   >> " + i+" commande inséré avec succés");
                System.out.println("\\nnVous voulez ajouter une autre commande (oui/non) ?");
                choix=s.next();
            }
            conn.close();
        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);
        }

    }



    public static void deletcmd()
    {
        try {
            // A. Enregistrer la classe de pilote
            Class c = Class.forName("com.mysql.cj.jdbc.Driver") ;
            // Protocole de connexion
            String protocole =  "jdbc:mysql:" ;
            // Adresse IP de l’hôte de la base et port
            String ip =  "localhost" ;  // dépend du contexte
            String port =  "3306" ;  // port MySQL par défaut
            // Nom de la base ;
            String nomBase =  "DbRestau" ;  // dépend du contexte
            // Chaîne de connexion
            String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase ;
            // Identifiants de connexion et mot de passe
            String nomConnexion =  "root" ;  // dépend du contexte
            String motDePasse =  "Nassime2002#" ;  // dépend du contexte
            // B. Créer une connexion
            Connection con = DriverManager.getConnection(
                    conString, nomConnexion, motDePasse) ;

            // C. Créer un objet Statement

            System.out.println("\n >> Suppression d'unE commande ...");
            //Préparation de notre Requête paramétrée

            Scanner s=new Scanner(System.in);
            System.out.print("\n   - Entrer > ID de la commande à supprimer :   ");
            int sidcmd=s.nextInt();
            PreparedStatement stmtcmd = con.prepareStatement("DELETE FROM COMMANDE WHERE idcmd = ?");
            stmtcmd.setInt(1,sidcmd);
            int i=stmtcmd.executeUpdate();

            System.out.println(" Suppression ...");

            System.out.println("La commande ID: " + sidcmd + " supprimée avec succès  ...");

            // E. Fermer la connexion
            con.close();

        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);

        }

    }

    public static void listecmd(){
        try {
            // A. Enregistrer la classe de pilote
            Class c = Class.forName("com.mysql.cj.jdbc.Driver") ;
            // Protocole de connexion
            String protocole =  "jdbc:mysql:" ;
            // Adresse IP de l’hôte de la base et port
            String ip =  "localhost" ;  // dépend du contexte
            String port =  "3306" ;  // port MySQL par défaut
            // Nom de la base ;
            String nomBase =  "DbRestau" ;  // dépend du contexte
            // Chaîne de connexion
            String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase ;
            // Identifiants de connexion et mot de passe
            String nomConnexion =  "root" ;  // dépend du contexte
            String motDePasse =  "Nassime2002#" ;  // dépend du contexte
            // B. Créer une connexion
            Connection con = DriverManager.getConnection(
                    conString, nomConnexion, motDePasse) ;
            // C. Créer un objet Statement
            PreparedStatement stmt = con.prepareStatement("SELECT idcmd, idclient, date , livraison FROM commande WHERE idclient = ? ORDER BY idcmd ASC");
            Scanner s=new Scanner(System.in);
            System.out.println("  --- Afficher les commande effectuées par un client --- ");
            System.out.print("\n   - Entrer > ID client rechercher :   ");
            int sidclt=s.nextInt();
            stmt.setInt(1, sidclt);
            ResultSet res = stmt.executeQuery(  );
            System.out.println("Liste des commandes effectués par le client ID: " + sidclt + "\n");
            System.out.print("ID CMD\t|\tID CLIENT \t|\tDATE CMD\t|\tLIVRAISON CMD\n");
            System.out.println("---------------------------------------------------------------");
            while (res.next()) {
                //Récupérer par nom de colonne
                int vidcmd = res.getInt("idcmd");
                int vidclt = res.getInt("idclient");
                Date vdatecmd = res.getDate("date");
                int vqtecmd = res.getInt("livraison");

                //Afficher les valeurs
                System.out.print(" " + vidcmd + "\t|\t");
                System.out.print(vidclt + "\t|\t");
                System.out.print(vdatecmd + "\t|\t");
                System.out.println(vqtecmd);
            }
            // E. Fermer la connexion
            con.close();

        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);

        }

    }

    public static void listeallcmd()
    {
        try {
            // A. Enregistrer la classe de pilote
            Class c = Class.forName("com.mysql.cj.jdbc.Driver") ;
            // Protocole de connexion
            String protocole =  "jdbc:mysql:" ;
            // Adresse IP de l’hôte de la base et port
            String ip =  "localhost" ;  // dépend du contexte
            String port =  "3306" ;  // port MySQL par défaut
            // Nom de la base ;
            String nomBase =  "DbRestau" ;  // dépend du contexte
            // Chaîne de connexion
            String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase ;
            // Identifiants de connexion et mot de passe
            String nomConnexion =  "root" ;  // dépend du contexte
            String motDePasse =  "Nassime2002#" ;  // dépend du contexte
            // B. Créer une connexion
            Connection con = DriverManager.getConnection(
                    conString, nomConnexion, motDePasse) ;
            // C. Créer un objet Statement
            PreparedStatement stmt = con.prepareStatement("SELECT idcmd, idclient, date, livraison FROM commande ORDER BY idcmd ASC");
            Scanner s=new Scanner(System.in);
            System.out.println("  --- Afficher toutes les commandes effectuées  --- ");
            ResultSet res = stmt.executeQuery(  );
            System.out.println("Liste des commandes effectuées : \n");
            System.out.print("ID CMD\t|\tID CLIENT \t|\tDATE CMD\t|\tLIVRAISON CMD\n");
            System.out.println("---------------------------------------------------------------");
            while (res.next()) {
                //Récupérer par nom de colonne
                int vidcmd = res.getInt("idcmd");
                int vidclt = res.getInt("idclient");
                Date vdatecmd = res.getDate("date");
                int vqtecmd = res.getInt("livraison");

                //Afficher les valeurs
                System.out.print(" " + vidcmd + "\t|\t");
                System.out.print(vidclt + "\t|\t");
                System.out.print(vdatecmd + "\t|\t");
                System.out.println(vqtecmd);
            }
            // E. Fermer la connexion
            con.close();

        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);

        }

    }

    public static void menugestioncmd()
    {
        int choix = 0;
        Scanner masaisie = new Scanner(System.in,"UTF-8");

        do {
            System.out.println(" >> GESTION DES COMMANDES << \n");
            System.out.println("      1. Passer une commande  ");
            System.out.println("      2. Importer la liste des commandes  ");
            System.out.println("      3. Modifier la quantité commandée d'une commande  ");
            System.out.println("      4. Supprimer une commande  ");
            System.out.println("      5. Afficher la liste des commandes effectuées par un client  ");
            System.out.println("      6. Afficher toutes les commandes effectuées  ");
            System.out.println("      0. Retourner au menu principal  ");
            System.out.print("\n Entrez votre choix :  ");
            try
            {
                choix = masaisie.nextByte();

            }catch(Exception e)
            {
                System.out.println("  Erreur, choix imprévu !!!!!   -------");
                e.printStackTrace();
            }
            switch(choix)
            {
                case 1 :
                    System.out.println("\n ++++++ Choix 1 : Passer une Commande  ++++++ \n");
                    passercmd();
                    main.pressEnterToContinue();
                    break;

                case 2 :
                    System.out.println("\n ++++++ Choix 2 : Importer la liste des commandes  ++++++ \n");
                    //loadclients();
                    main.pressEnterToContinue();
                    break;

                case 3 :
                    System.out.println("\n ++++++ Choix 3 : Supprimer une commande  ++++++ \n");
                    deletcmd();
                    main.pressEnterToContinue();
                    break;
                case 4 :
                    System.out.println("\n ++++++ Choix 4 :  Afficher la liste des clients  ++++++ \n");
                    listecmd();
                    main.pressEnterToContinue();
                    break;
                case 5 :
                    System.out.println("\n ++++++ Choix 5 :  Afficher la liste des clients  ++++++ \n");
                    listeallcmd();
                    main.pressEnterToContinue();
                    break;
                case 0: {
                    System.out.println("\n \n   -------   Fin de l'interface de gestion des CLIENTS   -------");
                    main.pressEnterToContinue();
                    main.menu();
                    break;
                }
                default : System.out.println("  Erreur, choix imprévu !!!!!   -------");

            }

        }while(choix!=0);


    }

    
}
