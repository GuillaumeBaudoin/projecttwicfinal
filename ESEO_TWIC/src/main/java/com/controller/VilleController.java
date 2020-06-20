package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		
		@GetMapping("trouver")
        public List<Ville> trouver(@RequestParam(name="codeCommune", defaultValue="") String codeCommune){
            Ville ville = new Ville();
            ville.setCode_commune_INSEE(codeCommune);
            return villeBLOService.trouverVilles(ville);
        }
		
		// Methode PUT
		@PutMapping("update/{codeCommune}")
        public void modifier(@RequestBody Ville villeModif, @PathVariable("codeCommune") String codeCommune) {
            villeBLOService.updateVille(villeModif, codeCommune);
        }
		
		@DeleteMapping("/delete/{codeCommune}")
        public void supprimer(@PathVariable("codeCommune") String codeCommuneINSEE) {
            villeBLOService.supprimerVille(codeCommuneINSEE);
        }
		
}
