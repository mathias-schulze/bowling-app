package de.msz.bowlingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

	private String id;
	private String spieler;
	private String termin;
	private long timestamp;
	private int ergebnis;
}
