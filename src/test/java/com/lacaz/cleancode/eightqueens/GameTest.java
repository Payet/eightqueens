package com.lacaz.cleancode.eightqueens;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


public class GameTest
{
	public static final int DIMENSION = 8;

	private Game game;

	@Before
	public void setUp() throws Exception
	{
		game = new Game(DIMENSION);
	}

	@Test
	public void isPositionValid_shouldReturnTrue_whenGameIsNew() throws Exception
	{
		boolean gamePositionValid = game.isPositionValid(0, 0);

		Assert.assertThat(gamePositionValid, is(equalTo(true)));
	}

	@Test
	public void isPositionValid_shouldReturnTrue_whenNoQueenIsOnTheSameColumn() throws Exception
	{
		game.setQueenPositionForRow(0, 0);

		boolean gamePositionValid = game.isPositionValid(1, 2);

		Assert.assertThat(gamePositionValid, is(equalTo(true)));
	}

	@Test
	public void isPositionValid_shouldReturnFalse_whenAQueenIsOnTheSameColumn() throws Exception
	{
		game.setQueenPositionForRow(0, 0);
		game.setQueenPositionForRow(1, 1);

		boolean gamePositionValid = game.isPositionValid(2, 1);

		Assert.assertThat(gamePositionValid, is(equalTo(false)));
	}

	@Test
	public void isPositionValid_shouldReturnFalse_whenAQueenIsOnTheSameRow() throws Exception
	{
		game.setQueenPositionForRow(0, 0);
		game.setQueenPositionForRow(1, 1);

		boolean gamePositionValid = game.isPositionValid(1, 2);

		Assert.assertThat(gamePositionValid, is(equalTo(false)));
	}

	@Test
	public void isPositionValid_shouldReturnFalse_whenAQueenIsOnTheDiagonalBefore() throws Exception
	{
		game.setQueenPositionForRow(0, 0);
		game.setQueenPositionForRow(1, 3);

		boolean gamePositionValid = game.isPositionValid(2, 2);

		Assert.assertThat(gamePositionValid, is(equalTo(false)));
	}

	@Test
	public void isPositionValid_shouldReturnTrue_whenAQueenIsNotOnTheDiagonalAfter() throws Exception
	{
		game.setQueenPositionForRow(0, 7);
		game.setQueenPositionForRow(1, 1);

		boolean gamePositionValid = game.isPositionValid(2, 5);

		Assert.assertThat(gamePositionValid, is(equalTo(false)));
	}

	@Test
	public void calculateNextRow_shouldSet92Solutions_whenANewGameOf8DimensionIsSet() throws Exception
	{
		game.calculateNextRows();

		Assert.assertThat(game.getSolutions().size(), is(equalTo(92)));
	}

}
