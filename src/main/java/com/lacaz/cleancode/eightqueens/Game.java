package com.lacaz.cleancode.eightqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


public class Game
{
	private Integer[] queensPositionPerRow;
	private List<Integer[]> solutions;

	public Game(int dimension)
	{
		queensPositionPerRow = new Integer[dimension];
		solutions = new ArrayList<>();
	}

	public Game(Game game2copy)
	{
			this.queensPositionPerRow = Arrays.copyOf(game2copy.queensPositionPerRow, game2copy.getGameDimension());
			this.solutions = game2copy.getSolutions();
	}

	public boolean isPositionValid(int row, int column)
	{
		int numberOfRowsDefined = getNumberOfRowsDefined();

		long numberOfMatchingPositions = IntStream.range(0, numberOfRowsDefined)
																.filter( gameRow -> isPositionValidForExistingGameRow(row, column, gameRow))
																.count();

		return numberOfMatchingPositions == 0;
	}

	private boolean isPositionValidForExistingGameRow(int row, int column, int gameRow)
	{
		return isPositionOnSameRowOrColumn(row, column, gameRow) || isOnDiagonal(row, column, gameRow);
	}

	private boolean isOnDiagonal(int row, int column, int gameRow)
	{
		int gameColumn = queensPositionPerRow[gameRow];
		int distance = row - gameRow;
		return (column == (gameColumn + distance)) || (column == (gameColumn - distance));
	}

	private boolean isPositionOnSameRowOrColumn(int row, int column, int gameRow)
	{
		int gameColumn = queensPositionPerRow[gameRow];
		if (row == gameRow || column == gameColumn)
		{
			return true;
		}
		return false;
	}

	public void setQueenPositionForRow(int row, Integer column)
	{
		queensPositionPerRow[row] = column;
	}



	public void calculateNextRows()
	{
		int nextRowToCalculate = getNumberOfRowsDefined();

		for (int column = 0; column < getGameDimension(); column++)
		{
			if (isPositionValid(nextRowToCalculate, column))
			{
				Game solutionGame = new Game(this);
				solutionGame.setQueenPositionForRow(nextRowToCalculate, column);
				if (solutionGame.getNumberOfRowsDefined() == getGameDimension())
				{
					solutions.add(solutionGame.queensPositionPerRow);
				}

				solutionGame.calculateNextRows();
			}
		}
	}

	private int getGameDimension()
	{
		return queensPositionPerRow.length;
	}

	private int getNumberOfRowsDefined()
	{
		return Long.valueOf(Arrays.stream(queensPositionPerRow)
				.filter(row -> row != null)
				.count()).intValue();
	}

	public List<Integer[]> getSolutions()
	{
		return solutions;
	}

	public void displaySolutions()
	{
		for (Integer[] solution : solutions)
		{

			System.out.println("--- Solution: ---");
			for (Integer col : solution)
			{
				for (int i = 0; i < getGameDimension(); i++)
				{
					if (col == i)
					{
						System.out.print(" X ");
					}
					else
					{
						System.out.print(" * ");
					}
				}
				System.out.println("");
			}

		}
	}
}
