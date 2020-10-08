package dev.domain.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.domain.Absence;
import dev.domain.Collegue;
import dev.domain.EStatutDemandeAbsence;
import dev.repository.AbsenceRepo;

@Service
public class AbsenceService {
	private AbsenceRepo absenceRepo;
	
	public AbsenceService(AbsenceRepo absenceRepo) {
		this.absenceRepo = absenceRepo;
	}

	public Absence creerAbsence(Absence absence) {
		return this.absenceRepo.save(absence);
	}

	public boolean controleChevaucheDate(LocalDate dateDebut, LocalDate dateFin, Collegue collegue) {
		List<Absence> absences = collegue.getListeAbsencesDuCollegue();
		boolean controleValide = true;
		
		if(absences.size() != 0) {
			for(Absence absence : absences) {
				controleValide = this.checkerDate(dateDebut, dateFin, absence);
			}	
		}
		return controleValide;
	}
	
	private boolean checkerDate(LocalDate dateDebut, LocalDate dateFin, Absence absence) {
		if(dateDebut.isBefore(absence.getDatePremierJourAbsence()) && dateFin.isBefore(absence.getDateDernierJourAbsence())
			|| dateDebut.isBefore(absence.getDatePremierJourAbsence()) && dateFin.isAfter(absence.getDateDernierJourAbsence())
			|| dateDebut.isAfter(absence.getDatePremierJourAbsence()) && dateDebut.isBefore(absence.getDateDernierJourAbsence())
			|| dateDebut.isAfter(absence.getDatePremierJourAbsence()) && dateFin.isBefore(absence.getDateDernierJourAbsence())
			|| dateDebut.isEqual(absence.getDatePremierJourAbsence()) ||  dateFin.isEqual(absence.getDateDernierJourAbsence())
		)	{
			
			return this.checkerStatusAbsence(absence) ? true : false;
		} else {
			return true;
		}
	}

	private boolean checkerStatusAbsence(Absence absence) {
		return absence.getStatutDemandeAbsence().equals(EStatutDemandeAbsence.REJETEE) ? true : false;
	}
}
