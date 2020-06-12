package com.dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.config.JDBCConfiguration;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {
	
	private static final String SQL_SELECT_VILLE = "SELECT * FROM ville_france";
	
	private static final String SQL_INSERT = "INSERT INTO ville_france VALUES (?,?,?,?,?,?,?)";

	public List<Ville> findAllVilles(){
		
		List<Ville> villes = new ArrayList<>();
		
		try {
			
			String query = SQL_SELECT_VILLE;
			
			
			Statement st = JDBCConfiguration.getConnection().createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				Ville ville = new Ville();
				ville.setNom_commune(rs.getString("Nom_commune"));
				ville.setCode_postal(rs.getString("Code_postal"));
				ville.setCode_commune_INSEE(rs.getString("Code_commune_INSEE"));
				ville.setLibelle_acheminement(rs.getString("Libelle_acheminement"));
				ville.setLigne_5(rs.getString("Ligne_5"));
				
				villes.add(ville);
			}
			
			// iterate through the java resultset
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return villes;
	
	}
	
	
	public void ajoutVille(Ville ville) {
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
	
	
	
	
	protected static String initialisationRequetePreparee(String sql, Object... objets) {
		String[] listeSQL = (sql+" ").split("\\?");
		StringBuilder newSQL = new StringBuilder(listeSQL[0]);
		for(int i = 0; i<objets.length; i++) {
			newSQL.append("\"" + objets[i] + "\"" + listeSQL[i+1]);
		}
		return newSQL.toString().replaceAll("\"null\"", "null");
	}	
}
