/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrestaurant;

import java.util.Scanner;
import java.sql.* ;
import java.io.* ;

public class GestionClient {
   
    public static Savepoint savepoint;

    public static void addclient()
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
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO client(idclient,food,payement) values(?,?,?)");
            Scanner s=new Scanner(System.in);
            System.out.println("Entrer id client : ");
            int sid=s.nextInt();
            stmt.setInt(1,sid);
            int nbr=0;
            String choix="oui";
            while(choix.equals("oui"))
            {
                System.out.println("  --- Ajout d'client " + ++nbr + "  --- ");
                System.out.print("\n   - Entrer > food :   ");
                String sfood=s.next();
                System.out.print("   - Entrer > total payement :   ");
                String spay=s.next();
                stmt.setString(2,sfood);
                stmt.setString(3,spay);

                int i=stmt.executeUpdate();
                System.out.println("   >> " + i+" client inséré avec succés");
                System.out.println("\\nnVous voulez ajouter un autre client (oui/non) ?");
                choix=s.next();
            }
            conn.close();
        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);
        }

    }

    public static void loadclients()
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

            try{
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO client(idclient,food,payement) values(?,?,?)");

                // Le fichier d'entrée
                FileInputStream file = new FileInputStream("C:\\Users\\nassi\\IdeaProjects\\GestionRestaurant\\src\\GestionRestaurant\\liste.txt");

                Scanner s = new Scanner(file);
                int nbr=0;
                System.out.println("Liste des clients importés à partir du fichier 'ListeClient.txt' \n");
                //System.out.print("ID\t|\tNom\t\t|\tAdresse\t\t|\tTéléphone\n");
                System.out.print("idClient\t\t|\tFood\t\t|\tPayement\n");
                System.out.println("-------------------------------------------------------------------");
                while(s.hasNextLine())
                {
                    //int sid=s.nextInt();
                    String sname=s.next();
                    String sadr=s.next();
                    long stel=s.nextLong();

                    //System.out.print(" " + sid + "\t|\t");
                    System.out.print(sname + "\t\t|\t");
                    System.out.print(sadr + "\t\t|\t");
                    System.out.println(stel);
                    //stmt.setInt(1,sid);
                    stmt.setString(1,sname);
                    stmt.setString(2,sadr);
                    stmt.setLong(3,stel);
                    int i=stmt.executeUpdate();
                    //System.out.println(i+" inséré avec succés");
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            conn.close();

        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);
        }

    }

    public static void updateprix()
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
            PreparedStatement stmt = conn.prepareStatement("UPDATE client SET payement = ? WHERE idclient = ?");
            Scanner s=new Scanner(System.in);
            System.out.println("  --- Modifier le total du payement d'un client --- ");
            System.out.print("\n   - Entrer > ID client à modifier :   ");
            int sidclient=s.nextInt();

            System.out.print("   - Entrer le nouveau prix en DH :   ");
            int sprix=s.nextInt();

            stmt.setInt(1, sprix);
            stmt.setInt(2,sidclient);

            int i=stmt.executeUpdate();
            System.out.println("   >> " + i+" prix modifée avec succés");

            conn.close();
        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);
        }

    }

    public static void deletclient()
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
            try{
                //définir la gestion des transactions manuelles
                con.setAutoCommit(false);

                //définir un point de sauvegarde
                Savepoint savepoint = con.setSavepoint("Savepoint1");

                // C. Créer un objet Statement

                System.out.println("\n >> Suppression d'un client ...");
                //Préparation de notre Requête paramétrée

                Scanner s=new Scanner(System.in);
                System.out.print("\n   - Entrer > ID du client à supprimer :   ");
                int sidclt=s.nextInt();
                boolean ok=true;
                while(ok)
                {
                    PreparedStatement stmtcmd = con.prepareStatement("DELETE FROM Commande WHERE idclient = ?");

                    stmtcmd.setInt(1,sidclt);
                    int j=stmtcmd.executeUpdate();
                    if(j==0)
                        ok=false;
                }
                PreparedStatement stmtclt = con.prepareStatement("DELETE FROM CLIENT WHERE idclient = ?");
                stmtclt.setInt(1,sidclt);
                int i=stmtclt.executeUpdate();


                System.out.println(" Suppression ...");
                // S'il n'y a pas d'erreur.
                con.commit();

                System.out.println("Le client ID: " + sidclt + "  supprimé avec succès, ainsi toutes ses commandes  ...");
            }
            catch(SQLException se)
            {
                // S'il y a une erreur.
                con.rollback(savepoint);
            }


            // E. Fermer la connexion
            con.close();

        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);

        }

    }

    public static void listeclient(){
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
            Statement smt = con.createStatement() ;
            System.out.println(" >> Générer la liste des clients ...\n");
            // D. Exécuter des requêtes

            String sql = "SELECT idclient , food , payement " + " FROM CLIENT ; "
                    ;

            ResultSet res = smt.executeQuery(sql) ;
            System.out.println("Liste des clients  \n");
            System.out.print("ID\t|\tFOOD\t\t|\tPAYEMENT\t|");
            System.out.println("----------------------------------------------------------------------------");
            while (res.next()) {
                //Récupérer par nom de colonne
                int id = res.getInt("idclient");
                String nom = res.getString("food");
                String pay = res.getString("payement");
                //Afficher les valeurs
                System.out.print(" " + id + "\t|\t");
                System.out.print(nom + "\t\t\t|\t");
                System.out.print(pay + "\t\t|\n");

            }
            // E. Fermer la connexion
            con.close();

        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);

        }

    }

    public static void menugestionclt()
    {
        int choix = 0;
        Scanner masaisie = new Scanner(System.in,"UTF-8");

        do {
            System.out.println(" >> GESTION DES CLIENTS << \n");
            System.out.println("      1. Ajouter un client  ");
            System.out.println("      2. Importer la liste des clients  ");
            System.out.println("      3. Modifier les information d'un client  ");
            System.out.println("      4. Supprimer un client  ");
            System.out.println("      5. Afficher la liste des clients  ");
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
                    System.out.println("\n ++++++ Choix 1 : Ajouter un Client  ++++++ \n");
                    addclient();
                    main.pressEnterToContinue();
                    break;

                case 2 :
                    System.out.println("\n ++++++ Choix 2 : Importer la liste des clients  ++++++ \n");
                    loadclients();
                    main.pressEnterToContinue();
                    break;
                case 3 :
                    System.out.println("\n ++++++ Choix 3 : Modifier les informations d'un client  ++++++ \n");
                    updateprix();
                    main.pressEnterToContinue();
                    break;
                case 4 :
                    System.out.println("\n ++++++ Choix 4 : Supprimer un client  ++++++ \n");
                    deletclient();
                    main.pressEnterToContinue();
                    break;
                case 5 :
                    System.out.println("\n ++++++ Choix 5 :  Afficher la liste des clients  ++++++ \n");
                    listeclient();
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
