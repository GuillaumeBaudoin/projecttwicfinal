package com.controller;

import java.util.ArrayList;


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
//@RequestMapping("/path")
class VilleController {

	@Autowired
	VilleBLO villeBLOService;

	// Methode GET
	@RequestMapping(value = "/villes", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> findAllVilles() {
		System.out.println("Appel GET");

		ArrayList<Ville> ville = villeBLOService.findAllVilles();

		return ville;
	}
	
	// Methode GET
		@RequestMapping(value = "/ville", method = RequestMethod.GET)
		@ResponseBody
		public Ville getInfoVille(@RequestParam(value= "name") String name) {
			System.out.println("Appel GET");

			Ville ville = villeBLOService.getInfoVille(name);

			return ville;
		}
		
		//Methode POST
		@PostMapping("create")
		public void CreerVille(@RequestBody Ville nouvelleVille) {
			villeBLOService.creerVille(nouvelleVille);
		}
		
		// Methode DELETE
		@RequestMapping(value = "/ville", method = RequestMethod.DELETE)
		@ResponseBody
		public void deleteVille(@RequestParam(value = "codeINSEE") String code) {
			villeBLOService.deleteVille(code);
			
		}
		
		// Methode PUT
		@RequestMapping(value = "/updateVille", method = RequestMethod.PUT)
		public void updateVille(@RequestBody Ville updatedVille) {
			villeBLOService.updateVille(updatedVille);
			
		}
		
}
