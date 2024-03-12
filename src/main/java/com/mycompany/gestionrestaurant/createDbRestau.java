/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrestaurant;

import java.util.Scanner;
import java.sql.* ;

public class createDbRestau {
     public static void createDbRestau()
    {
        try {
            // chargement de la classe par son nom
            Class c = Class.forName("com.mysql.cj.jdbc.Driver") ;

            // Protocole de connexion
            String protocole =  "jdbc:mysql:" ;
            // Adresse IP de l’hôte de la base et port
            String ip =  "localhost" ;  // dépend du contexte
            String port =  "3306" ;  // port MySQL par défaut
            // Chaîne de connexion
            String conString = protocole +  "//" + ip +  ":" + port ;
            // Identifiants de connexion et mot de passe
            String nomConnexion =  "root" ;  // dépend du contexte
            String motDePasse =  "Nassime2002#" ;  // dépend du contexte
            // C. Connexion
            Connection conct = DriverManager.getConnection(
                    conString, nomConnexion, motDePasse) ;

            // D. Exécuter la requéte
            Statement stmt = conct.createStatement();
            //étape 4: exécuter la requéte
            System.out.println("\n >> Création de la base de données ...");
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS DbRestau");
            System.out.println("Base de données DbRestau crée avec succés ...\n\n");

            // E. Fermer la connexion
            conct.close();
        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);
        }

    }

    public static void createTBclient()
    {
        try {
            // chargement de la classe par son nom
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
            System.out.println("\n >> Création de la table Client ...");
            // D. Exécuter des requêtes
            String sql =  "CREATE TABLE IF NOT EXISTS CLIENT " +
                    "(idclient INTEGER NOT NULL AUTO_INCREMENT, " +
                    " food VARCHAR(100), " +
                    " payement INTEGER, " +
                    " PRIMARY KEY ( idclient ))";

            smt.executeUpdate(sql);

            System.out.println("La table CLIENT créée avec succés ...");

            // E. Fermer la connexion
            con.close();

        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);

        }

    }

    public static void createTBcmd()
    {
        try {
            // chargement de la classe par son nom
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
            // D. Exécuter des requêtes
            System.out.println("\n >> Création de la table Commande ...");
            String sql =  "CREATE TABLE IF NOT EXISTS COMMANDE " +
                    "(idcmd INTEGER NOT NULL AUTO_INCREMENT, " +
                    " idclient INTEGER NOT NULL, " +
                    " date DATE, " +
                    " livraison BOOLEAN NOT NULL DEFAULT FALSE , " +
                    " FOREIGN KEY (idclient) REFERENCES CLIENT(idclient)," +
                    " PRIMARY KEY ( idcmd ))" ;

            smt.executeUpdate(sql);

            System.out.println("La table COMMANDE créée avec succés ...");

            // E. Fermer la connexion
            con.close();

        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);

        }

    }

    public static void deletclient()
    {
        try {
            // chargement de la classe par son nom
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

                // C. Créer un objet Statement
                Statement smt = con.createStatement() ;

                System.out.println("\n >> Suppression de la table Client ...");
                // D. Exécuter des requêtes

                smt.executeUpdate("DROP TABLE IF EXISTS COMMANDE");

                smt.executeUpdate("DROP TABLE IF EXISTS CLIENT");

                System.out.println(" La table Client supprimée avec succés ...\n\n");


                // S'il n'y a pas d'erreur.
                con.commit();
            }
            catch(SQLException se)
            {
                // S'il y a une erreur.
                con.rollback();
            }
            // E. Fermer la connexion
            con.close();

        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);

        }

    }

    public static void deletcmd()
    {
        try {
            // chargement de la classe par son nom
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
            System.out.println("\n >> Suppression de la table Commande ...");
            // D. Exécuter des requêtes

            smt.executeUpdate("DROP TABLE IF EXISTS COMMANDE");
            System.out.println(" La table Commande supprimée avec succés ...\n\n");

            // E. Fermer la connexion
            con.close();

        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);

        }

    }

    public static void deletBDRestau()
    {
        try {
            // chargement de la classe par son nom
            Class c = Class.forName("com.mysql.cj.jdbc.Driver") ;

            // Protocole de connexion
            String protocole =  "jdbc:mysql:" ;
            // Adresse IP de l’hôte de la base et port
            String ip =  "localhost" ;  // dépend du contexte
            String port =  "3306" ;  // port MySQL par défaut
            // Chaîne de connexion
            String conString = protocole +  "//" + ip +  ":" + port ;
            // Identifiants de connexion et mot de passe
            String nomConnexion =  "root" ;  // dépend du contexte
            String motDePasse =  "Nassime2002#" ;  // dépend du contexte
            // C. Connexion
            Connection conct = DriverManager.getConnection(
                    conString, nomConnexion, motDePasse) ;

            // D. Exécuter la requéte
            Statement stmt = conct.createStatement();
            //étape 4: exécuter la requéte
            System.out.println("\n >> Suppression de la base de données ...");
            stmt.executeUpdate("DROP DATABASE IF EXISTS DbRestau");
            System.out.println(" Base de données DbRestau supprimée avec succés ...\n\n");

            // E. Fermer la connexion
            conct.close();
        }  catch (Exception e) {
            // gestion des exceptions
            System.out.println(e);
        }
    }

    public static void menuDB()
    {
        int choix = 0;
        Scanner masaisie = new Scanner(System.in,"UTF-8");

        do {
            System.out.println(" >> CREATION DE LA BASE DE DONNEES DES CLIENTS / COMMANDES << \n");
            System.out.println("      1. Créer la base de données DbRestau  ");
            System.out.println("      2. Créer la table des clients  ");
            System.out.println("      3. Créer la table des commandes  ");
            System.out.println("      4. Supprimer la table des clients  ");
            System.out.println("      5. Supprimer la table des commandes  ");
            System.out.println("      6. Supprimer la base de données DbRestau  ");
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
                    System.out.println("\n ++++++ Choix 1 : Créer la base de données  ++++++ \n");
                    createDbRestau();
                    main.pressEnterToContinue();
                    break;

                case 2 :
                    System.out.println("\n ++++++ Choix 2 : Créer  la table CLIENT  ++++++ \n");
                    createTBclient();
                    main.pressEnterToContinue();
                    break;
                case 3 :
                    System.out.println("\n ++++++ Choix 3 : Créer  la table COMMANDE  ++++++ \n");
                    createTBcmd();
                    main.pressEnterToContinue();
                    break;
                case 4 :
                    System.out.println("\n ++++++ Choix 4 : Supprimer  la table CLIENT  ++++++ \n");
                    deletclient();
                    main.pressEnterToContinue();
                    break;
                case 5 :
                    System.out.println("\n ++++++ Choix 5 : Supprimer  la table COMMANDE  ++++++ \n");
                    deletcmd();
                    main.pressEnterToContinue();
                    break;
                case 6 :
                    System.out.println("\n ++++++ Choix 6 : Supprimer  la base de données DbRestau  ++++++ \n");
                    deletBDRestau();
                    main.pressEnterToContinue();
                    main.pressEnterToContinue();
                    break;

                case 0: {
                    System.out.println("\n \n   -------   Fin de l'interface de gestion de la base de données DbRestau  -------");
                    main.pressEnterToContinue();
                    main.menu();
                    break;
                }
                default : System.out.println("  Erreur, choix imprévu !!!!!   -------");

            }

        }while(choix!=0);


    }
}
