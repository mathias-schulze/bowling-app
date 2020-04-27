package de.msz.bowlingapp.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.msz.bowlingapp.domain.Event;
import de.msz.bowlingapp.domain.Result;
import de.msz.bowlingapp.service.FirestoreService;

@RestController
@RequestMapping("/api/results")
public class ResultsController {

	@Autowired
	private FirestoreService firestoreService;

	@GetMapping("/lastResult")
	public Result getLastResult() {
		
		Result result = new Result();
		
		Event lastEvent = firestoreService.getLastEvent();
		result.setEvent(lastEvent);
		
		return result;
	}
}
