package de.msz.bowlingapp.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.msz.bowlingapp.domain.Event;
import de.msz.bowlingapp.service.FirestoreService;

@RestController
@RequestMapping("/api/events")
public class EventsController {

	@Autowired
	private FirestoreService firestoreService;

	@GetMapping("")
	public List<Event> getEvents() {

		List<Event> events = new ArrayList<>();

		firestoreService.getEvents().forEach((k,e) -> {
			events.add(e);
		});

		return events;
	}
}
