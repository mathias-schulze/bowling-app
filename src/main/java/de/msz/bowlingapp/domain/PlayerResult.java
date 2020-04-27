package de.msz.bowlingapp.domain;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResult {

	private Player player;
	private List<Game> games;
	private int noGames;
	private int sum;
	private BigDecimal avg;
}
