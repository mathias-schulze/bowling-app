package de.msz.bowlingapp.domain;

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
}
