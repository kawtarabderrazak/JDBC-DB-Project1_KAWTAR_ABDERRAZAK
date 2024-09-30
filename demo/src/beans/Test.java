/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author PC
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void save(Site s) {
        //Information d'accès à la base de données
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/db";
        Connection cn = null;
        Statement st = null;
        try {
            // Étape 1 : Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            // Étape 2 : Récupération de la connexion
            cn = DriverManager.getConnection(url, user, password);
            // Étape 3 : Création d'un Statement
            st = cn.createStatement();
            String req = "insert into site values(null,'"+s.getNom()+"')";
            // Etape 4 :Exécution de la requete 
            st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossible de charger le driver");
        } finally {
            try {
                // Étape 5 : Libérer les ressources de la mémoire
                st.close();
                cn.close();
            } catch (SQLException ex) {
                System.out.println("Impossible de libérer les ressources");
            }
        }
    } 
    
    public static void main(String[] args) {
        // Insertion des données (à ne pas exécuter si tu ne veux pas ajouter à la base)
        /* save(new Site("SAFI"));
         save(new Site("MARRAKECH"));
         save(new Site("EL JADIDA"));*/
         // récupération des données
         load();
    }
    public static void load(){
        // information d'accès a la base de donnéées
        String user ="root";
        String password ="";
        String url ="jdbc:mysql://localhost/db";
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // Etape 1 : chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            // Etape 2 : Récupération de la connexion
            cn = DriverManager.getConnection(url,user,password);
            // Etape 3 : Création d'un statement
            st = cn.createStatement();
            String req = "select* from site";
            // Etape 4 : Exécussion de la requete
            rs = st.executeQuery(req);
            // Etape 5 : Création d'un statement
            while(rs.next()){
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
                
            }


       }catch (SQLException e) {
           System.out.println(e.getMessage());
       }catch (ClassNotFoundException ex) {
           System.out.println("imposible de charger le driver");

           
       }finally{
            try{
                // Etape  6: Libérer les ressources de la mémoire
                st.close();
                cn.close();
                
            }catch (SQLException ex){
              System.out.println("imposible de libérer les ressource");   
                
            }
        }
        
    }
   
}
