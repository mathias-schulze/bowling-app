package de.msz.bowlingapp.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.msz.bowlingapp.domain.Game;
import de.msz.bowlingapp.service.FirestoreService;

@RestController
@RequestMapping("/api/games")
public class GamesController {

	@Autowired
	private FirestoreService firestoreService;

	@GetMapping("")
	public List<Game> getEvents() {

		List<Game> games = new ArrayList<>();

		firestoreService.getGames().forEach((k,g) -> {
			games.add(g);
		});

		return games;
	}
}
