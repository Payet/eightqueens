package com.lacaz.cleancode.eightqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
			this.queensPositionPerRow = Arrays.copyOf(game2copy.queensPositionPerRow, game2copy.queensPositionPerRow.length);
			this.solutions = game2copy.getSolutions();
	}

	public boolean isPositionValid(int row, int column)
	{
		int numberOfRowsDefined = getNumberOfRowsDefined();

		for (int gameRow = 0; gameRow < numberOfRowsDefined; gameRow++)
		{

			int gameColumn = queensPositionPerRow[gameRow];

			if (isPositionOnSameRowOrColumn(row, column, gameRow, gameColumn))
			{
				return false;
			}

			if (isOnDiagonal(row, column, gameRow, gameColumn))
			{
				return false;
			}

		}

		return true;
	}

	private boolean isOnDiagonal(int row, int column, int gameRow, int gameColumn)
	{
		int distance = row - gameRow;
		return (column == (gameColumn + distance)) || (column == (gameColumn - distance));
	}

	private boolean isPositionOnSameRowOrColumn(int row, int column, int gameRow, int gameColumn)
	{
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


	public boolean calculateNextRows()
	{
		int nextRowToCalculate = getNumberOfRowsDefined();

		for (int column = 0; column < queensPositionPerRow.length; column++)
		{
			if (isPositionValid(nextRowToCalculate, column))
			{
				Game solutionGame = new Game(this);
				solutionGame.setQueenPositionForRow(nextRowToCalculate, column);
				if (solutionGame.getNumberOfRowsDefined() == -1)
				{
					solutions.add(solutionGame.queensPositionPerRow);
					return true;
				}

				if (!solutionGame.calculateNextRows())
				{
					solutionGame.setQueenPositionForRow(nextRowToCalculate, null);
				}
			}
		}

		return false;
	}

	private int getNumberOfRowsDefined()
	{
		int nextRowToCalculate = -1;
		for (int row = 0; row < queensPositionPerRow.length; row++)
		{
			if (queensPositionPerRow[row] == null)
			{
				nextRowToCalculate = row;
				break;
			}
		}
		return nextRowToCalculate;
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
				for (int i = 0; i < queensPositionPerRow.length; i++)
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
