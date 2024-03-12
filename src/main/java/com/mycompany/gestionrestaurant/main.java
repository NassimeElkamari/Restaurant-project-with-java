/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrestaurant;

import java.util.Scanner;

/**
 *
 * @author nassi
 */
public class main {
    
     public static void pressEnterToContinue()
    { 
        System.out.println("\n     >>>>   Press Enter key to continue...\n\n");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {} 
    }
    
     
    public static void menu()
    {
         int choix = 0;
         Scanner masaisie = new Scanner(System.in,"UTF-8");
	 
	 do {
	        System.out.println(" >> GESTION DES CLIENT / COMMANDES << \n");
                System.out.println("      1. Gestion la base de données DbRestau  ");
                System.out.println("      2. Gestion des Clients  ");
	 	System.out.println("      3. Gestion des Commandes   ");
                System.out.println("      0. Quitter l'application   ");
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
                                System.out.println("\n ++++++ Choix 1 : Gestion de la base de données DbRestau ++++++ \n");
                                createDbRestau.menuDB();
                                pressEnterToContinue();
                            	break;
			 
			 case 2 : 
			 	//system("cls");
                                System.out.println("\n ++++++ Choix 2 : Gestion des Clients  ++++++ \n");
                                GestionClient.menugestionclt();
                                pressEnterToContinue();
                            	break;
			 case 3 : 
			 	System.out.println("\n ++++++ Choix 3 : Gestion des Commandes  ++++++ \n");
                                GestionCommande.menugestioncmd();
                                pressEnterToContinue();
                                break;
			 case 0: 
                                System.out.println("\n   Merci \n   -------   Fin de Programme   -------");			 	
				break;
			 default : System.out.println("  Erreur, choix imprévu !!!!!   -------");
		 
                }    
              
	 }while(choix!=0);		

    }
    
    public static void main(String[] args) {

    menu();    

    }
    
    
}
