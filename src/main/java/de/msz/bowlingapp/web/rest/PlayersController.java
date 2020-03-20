package de.msz.bowlingapp.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.msz.bowlingapp.domain.Player;
import de.msz.bowlingapp.service.FirestoreService;

@RestController
@RequestMapping("/api/players")
public class PlayersController {

	@Autowired
	private FirestoreService firestoreService;

	@GetMapping("")
	public List<Player> getPlayers() {

		List<Player> players = new ArrayList<>();

		firestoreService.getPlayers().forEach((k,p) -> {
			players.add(p);
		});

		return players;
	}
}
