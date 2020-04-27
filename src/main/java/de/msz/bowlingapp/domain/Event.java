package de.msz.bowlingapp.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

	private String id;
	private String beschreibung;
	private String datum;
	private List<Player> players;
}
