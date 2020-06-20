package com.blo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO {

	@Autowired
	private VilleDAO villeDAO;
	
	public ArrayList<Ville> findAllVilles() {
		ArrayList<Ville> ville;
		
		ville = villeDAO.findAllVilles();
		return ville;
	}
	
	public Ville getInfoVille(String name) {
		Ville ville;
		
		ville = villeDAO.getInfoVille(name);
		return ville;
	}
	
	public void creerVille(Ville ville) {
		villeDAO.creerVille(ville);
	}
	
	public void deleteVille(String code) {
		villeDAO.deleteVille(code);
	}
	
	public void updateVille(Ville updatedVille, String codecommu) {
		villeDAO.updateVille(updatedVille, codecommu);	}
	
	public ArrayList<Ville> trouverVilles(Ville ville){
		ArrayList<Ville> villes;
		
		villes = villeDAO.trouverVilles(ville);
		return villes;
	}
	
	public void supprimerVille(String code) {
		 villeDAO.supprimerVille(code);
		
	}

}
