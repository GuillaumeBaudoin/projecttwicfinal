package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
@RequestMapping("/path")
class VilleController {

	@Autowired
	VilleBLO villeBLOService;
/*
	// Methode GET
	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody
	public List<Ville> appelGet() {
		System.out.println("Appel GET");

		List<Ville> villes = villeBLOService.getInfoVille();

		return villes;
	}
	*/
	
	// Methode GET
		@RequestMapping(value = "/ville", method = RequestMethod.GET)
		@ResponseBody
		public List<Ville> appelGetcodePostal(@RequestParam(value = "name") String name) {
			System.out.println("Appel GET");

			List<Ville> villes = villeBLOService.getInfoVille();
			List<Ville> villes2 = new ArrayList<>();		
			
			for(Ville ville : villes) {
				if(ville.getCode_postal().equals(name)){
					villes2.add(ville);
				}
				
			}
			

			return villes2;
		}
	
	
				// Methode POST
		
				@PostMapping("ajout")
				public void ajoutVille(@RequestBody Ville nouvelleVille) {
					System.out.println("Appel POST");

					villeBLOService.ajoutVille(nouvelleVille);
					
					
					

					
				}
	
	
}
