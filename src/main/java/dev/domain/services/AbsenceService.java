package dev.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.domain.Absence;
import dev.domain.Collegue;
import dev.domain.exceptions.CollegueIntrouvableException;
import dev.repository.CollegueRepo;

@Service
public class AbsenceService {
	private CollegueRepo collegueRepo;
	
	public AbsenceService(CollegueRepo collegueRepo) {
		this.collegueRepo = collegueRepo;
	}
	
	
	
	
	public List<Absence> getAbsencesByCollegue(Long id) throws CollegueIntrouvableException {
		Collegue collegue = this.recupererCollegue(id);
		return collegue.getListeAbsencesDuCollegue();
	}
	
	public Collegue recupererCollegue(Long id) throws CollegueIntrouvableException {
		Optional<Collegue> collegue =  this.collegueRepo.findById(id);
		if(collegue.isPresent()) {
			return collegue.get();
		} else {
			throw new CollegueIntrouvableException("L'id ne correspond à aucun collegue");
		}
	}
}
