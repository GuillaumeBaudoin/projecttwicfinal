package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.config.JDBCConfiguration;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {
	private static final String SQL_INSERT="INSERT INTO ville_france VALUES(?,?,?,?,?,?,?)";
	private static final String SQL_DELETE = "DELETE from ville_france where ville_france.Code_commune_INSEE =?";
	private static final String SQL_UPDATE="UPDATE ville_france set Nom_Commune = ?, code_postal = ? where code_commune_INSEE =?";
	public ArrayList<Ville> findAllVilles() {
		
		Connection con = JDBCConfiguration.getConnection();
		
		Ville ville = null;
		ArrayList<Ville> villes = new ArrayList();
		try {
			String query = "SELECT * FROM ville_france";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				ville = new Ville();
				ville.setNom_commune(rs.getString("Nom_Commune"));
				ville.setCode_commune_INSEE(rs.getString("Code_commune_INSEE"));
				ville.setCode_postal(rs.getString("Code_postal"));
				ville.setLibelle_acheminement(rs.getString("Libelle_acheminement"));
				ville.setLigne_5(rs.getString("Ligne_5"));
				ville.setLatitude(rs.getString("Latitude"));
				ville.setLongitude(rs.getString("Longitude"));
				villes.add(ville);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return villes;
	}
	
	public Ville getInfoVille(String name) {
		Connection con = JDBCConfiguration.getConnection();
		
		Ville ville = null;
		try {
			String query = "SELECT * FROM ville_france where Code_commune_INSEE=" + name;

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				ville = new Ville();
				ville.setNom_commune(rs.getString("Nom_Commune"));
				ville.setCode_commune_INSEE(rs.getString("Code_commune_INSEE"));
				ville.setCode_postal(rs.getString("Code_postal"));
				ville.setLatitude(rs.getString("Latitude"));
				ville.setLongitude(rs.getString("Longitude"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ville;
	}
	
	public void creerVille(Ville ville){
		PreparedStatement preparedStatement = null;
		try {
			
			String query = SQL_INSERT ;

			preparedStatement = JDBCConfiguration.getConnection()
					.prepareStatement(
							initialisationRequetePreparee(query, ville.getCode_commune_INSEE(), ville.getNom_commune(),
									ville.getCode_postal(), ville.getLibelle_acheminement(), ville.getLigne_5(),
									ville.getLatitude(),ville.getLongitude()),
							Statement.RETURN_GENERATED_KEYS);
			// execute the query, and get a java resultset
			preparedStatement.executeUpdate();
			
			
				System.out.println("Ligne insérée");
				
			
		
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	public void deleteVille(String code){
		PreparedStatement preparedStatement = null;
		try {
			
			String query = SQL_DELETE ;

			preparedStatement = JDBCConfiguration.getConnection()
					.prepareStatement(
							initialisationRequetePreparee(query, code),
							Statement.RETURN_GENERATED_KEYS);
			// execute the query, and get a java resultset
			preparedStatement.executeUpdate();
			
			
				System.out.println("Ligne supprimée");
				
			
		
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public void updateVille(Ville updatedVille, String codecommu){
		Connection con = JDBCConfiguration.getConnection();
        try {
            String query = "UPDATE ville_france SET code_postal= ?, nom_commune= ?, libelle_acheminement= ?, ligne_5= ?, latitude= ?, longitude= ? WHERE code_commune_INSEE= ?";
            PreparedStatement preparedstatement = con.prepareStatement(query);
           
            preparedstatement.setString(1, updatedVille.getCode_postal());
            preparedstatement.setString(2, updatedVille.getNom_commune());
            preparedstatement.setString(3, updatedVille.getLibelle_acheminement());
            preparedstatement.setString(4, updatedVille.getLigne_5());
            preparedstatement.setString(5, updatedVille.getLatitude());
            preparedstatement.setString(6, updatedVille.getLongitude());
            preparedstatement.setString(7, codecommu);
            preparedstatement.executeUpdate();
           
            System.out.println("Ligne modifiée");
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	
	public ArrayList<Ville> trouverVilles(Ville ville) {
        
        Connection con = JDBCConfiguration.getConnection();
        
        ArrayList<Ville> villes = new ArrayList();
        
        
        try {
            String query = "SELECT * FROM ville_france WHERE Code_commune_INSEE ="+ville.getCode_commune_INSEE();

 

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                ville = new Ville();
                ville.setNom_commune(rs.getString("Nom_Commune"));
                ville.setCode_commune_INSEE(rs.getString("Code_commune_INSEE"));
                ville.setCode_postal(rs.getString("Code_postal"));
                ville.setLibelle_acheminement(rs.getString("Libelle_acheminement"));
                ville.setLigne_5(rs.getString("Ligne_5"));
                ville.setLatitude(rs.getString("Latitude"));
                ville.setLongitude(rs.getString("Longitude"));
                
                
                villes.add(ville);
            }

 

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        return villes;
    }
	
	
	public void supprimerVille(String code) {
        Connection con = JDBCConfiguration.getConnection();
        try {
            String query = "DELETE FROM ville_france WHERE code_commune_INSEE= ?";
            PreparedStatement preparedstatement = con.prepareStatement(query);
           
            preparedstatement.setString(1, code);
            preparedstatement.executeUpdate();
           
            System.out.println("Ligne supprimée");
           
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	protected static String initialisationRequetePreparee(String sql, Object... objets) {
		String[] listeSQL = (sql+" ").split("\\?");
		StringBuilder newSQL = new StringBuilder(listeSQL[0]);
		for(int i = 0; i<objets.length; i++) {
			newSQL.append("\"" + objets[i] + "\"" + listeSQL[i+1]);
		}
		return newSQL.toString().replaceAll("\"null\"", "null");
	}
	
	
}
