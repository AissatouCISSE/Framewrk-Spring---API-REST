package sn.simplon.senforage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.simplon.senforage.model.Pharmacie;

@Repository
public interface PharmacieRepository extends JpaRepository<Pharmacie, Integer> {

}
