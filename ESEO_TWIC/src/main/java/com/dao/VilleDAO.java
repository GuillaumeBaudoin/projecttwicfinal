package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {
	
	public ArrayList<Ville> findAllVilles();
	
	public Ville findVille(String name);
	
	public void creerVille(Ville ville);

}
