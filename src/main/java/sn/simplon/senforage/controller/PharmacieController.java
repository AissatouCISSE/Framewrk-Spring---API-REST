package sn.simplon.senforage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sn.simplon.senforage.exception.ResourceNotFoundException;
import sn.simplon.senforage.model.Pharmacie;
import sn.simplon.senforage.repository.PharmacieRepository;

@RestController
@RequestMapping("/api/p1")
public class PharmacieController {

	@Autowired
	private PharmacieRepository pharmacieRepository;
	//create get all pharmacies api
	@GetMapping("/pharmacies")
	public List<Pharmacie> getAllPharmacies(){
		return pharmacieRepository.findAll();
	}
	
	//create pharmacie
	@PostMapping(  "/pharmacies")
	public Pharmacie createPharmacie( @RequestBody Pharmacie pharmacie) {
		return pharmacieRepository.save(pharmacie);
	}
	
	//get pharmacie by id
	@GetMapping("/pharmacies/{id}")
	public ResponseEntity<Pharmacie> getPharmacieById(@PathVariable(value="id") int pharmacieId) throws ResourceNotFoundException{
		Pharmacie pharmacie = pharmacieRepository.findById(pharmacieId).orElseThrow(()-> new ResourceNotFoundException("Pharmacie not found for this id::"+ pharmacieId));
		return ResponseEntity.ok().body(pharmacie);
	}
	
	//update pharmacie
	@PutMapping(value = "/pharmacies/{id}")
	public ResponseEntity<Pharmacie> updatePharmacie (@PathVariable(value="id") int pharmacieId , @RequestBody Pharmacie pharmacieDetails) throws ResourceNotFoundException {
		Pharmacie pharmacie = pharmacieRepository.findById(pharmacieId).orElseThrow(()-> new ResourceNotFoundException("Pharmacie not found for this id::"+ pharmacieId));
		pharmacie.setNom(pharmacieDetails.getNom());
		pharmacie.setQuartier(pharmacieDetails.getQuartier());
		pharmacie.setVille(pharmacieDetails.getVille());
		pharmacie.setEtat(pharmacieDetails.getEtat());
		pharmacieRepository.save(pharmacie);
		return ResponseEntity.ok().body(pharmacie);
		
		
	}
	
	//delete pharmacie
	@DeleteMapping(value = "/pharmacies/{id}")
	public ResponseEntity<?> deletePharmacie(@PathVariable(value="id") int pharmacieId) throws ResourceNotFoundException {
		Pharmacie pharmacie = pharmacieRepository.findById(pharmacieId).orElseThrow(()-> new ResourceNotFoundException("Pharmacie not found for this id::"+ pharmacieId));
		pharmacieRepository.deleteById(pharmacieId);
		return ResponseEntity.ok().build();
	}
	
}

