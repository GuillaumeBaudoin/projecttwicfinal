package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {
	
	public ArrayList<Ville> findAllVilles();
	
	public Ville getInfoVille(String name);
	
	public void creerVille(Ville ville);
	
	public void deleteVille(String code);
	
	public void updateVille(Ville updatedVille);

}
