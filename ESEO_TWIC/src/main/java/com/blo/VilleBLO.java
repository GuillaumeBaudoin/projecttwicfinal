package com.blo;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleBLO {

	public ArrayList<Ville> findAllVilles();
	
	public Ville getInfoVille(String name);
	
	public void creerVille(Ville ville);
	
	public void deleteVille(String code);
	
	public void updateVille(Ville updatedVille);
	
}
