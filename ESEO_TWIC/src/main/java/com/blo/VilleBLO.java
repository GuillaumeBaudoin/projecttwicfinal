package com.blo;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleBLO {

	public ArrayList<Ville> getInfoVilles();
	
	public Ville getInfoVille(String name);
	
	public void creerVille(Ville ville);
	
}
