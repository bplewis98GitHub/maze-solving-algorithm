
/****
 * A class to store and traverse a maze
 * @author esahe2
 *
 */
import java.util.*;

public class Maze {

	/**
	 * Two dimensional array to represent a maze
	 */
	private char[][] maze;

	/**
	 * Constructor initializing the maze array
	 * 
	 * @param maze
	 */
	public Maze(char[][] maze) {
		this.maze = maze;
	}

	/**
	 * You need to implement this method
	 * 
	 * @param start: The start Position in the maze
	 * @param goal:  The goal Position in the maze
	 * @return An array of Position which stores a solution to the maze. If a
	 *         solution is not found a null value should be returned.
	 */
	public Position[] traversewithStack(Position start, Position goal) {

		Stack<Position> stack = new Stack<Position>();
		Position current = new Position(start.getRow(), start.getColumn());
		stack.push(start);
		while (current != goal || !stack.isEmpty()) {
			int x = current.getColumn();
			int y = current.getRow();
			if (current.equals(goal)) {
				break;
			}
			if (x > 0 && maze[y][x - 1] == ' ') {
				maze[y][x - 1] = 'v';
				current = new Position(y, x - 1);
				stack.push(current);
			} else if (maze[y - 1][x] == ' ') {
				maze[y - 1][x] = 'v';
				current = new Position(y - 1, x);
				stack.push(current);
			} else if (x < 13 && maze[y][x + 1] == ' ') {
				maze[y][x + 1] = 'v';
				current = new Position(y, x + 1);
				stack.push(current);
			} else if (maze[y + 1][x] == ' ') {
				maze[y + 1][x] = 'v';
				current = new Position(y + 1, x);
				stack.push(current);
			} else {
				stack.pop();
				if(stack.isEmpty()) {
					break;
				}
				current = (Position) stack.peek();
			}

		}

		Position[] stackArray = new Position[stack.size()];
		int count = 0;
		if (!stack.isEmpty()) {
			for (Position cell : stack) {
				stackArray[count] = cell;
				count++;
			}
		}else {
			stackArray = new Position[1];;
			stackArray[0] = null;
		}
		return stackArray;
	}

	/**
	 * You need to implement this method
	 * 
	 * @param start: The start Position in the maze
	 * @param goal:  The goal Position in the maze
	 * @return An array of Position which stores a solution to the maze. If a
	 *         solution is not found a null value should be returned.
	 */
	public Position[] traverseRecursive(Position start, Position goal) {
		refresh();
		int x = start.getRow();
		int y = start.getColumn();
		Position current = new Position(x, y);
		Position[] array = new Position[20];
		Position[] temp = new Position[20];
		int i = 1;
		maze[x][y] = 'v';
		array[0] = start;
		MazeRecursion(start, goal, current, array, i, x, y);
		temp = Arrays.copyOf(array, 19);
		return temp;

		// Your implementation goes here.
	}

//Refreshes Maze so all 'v's are ' '
	private void refresh() {
		for (int i = 0; i <= 5; i++) {
			for (int j = 0; j <= 12; j++) {
				if (maze[i][j] == 'v') {
					maze[i][j] = ' ';
				}
			}
		}
	}
	
	private boolean MazeRecursion(Position start, Position goal, Position current, Position[] array, int i, int x, int y) {
		if(current.getRow() == goal.getRow() && current.getColumn() == goal.getColumn()) {
			
			return true;
		}
		else {
			if(y > 0 && maze[x][y - 1] == ' ') {
				maze[x][y - 1] = 'v';
				current = new Position(current.getRow(), current.getColumn() - 1);
				array[i] = current;
				return MazeRecursion(start, goal, current, array, i+=1, x, y - 1);
			}
			if(x > 0 && maze[x - 1][y] == ' ') {
				maze[x - 1][y] = 'v';
				current = new Position(current.getRow() - 1, current.getColumn());
				array[i] = current;
				return MazeRecursion(start, goal, current, array, i+=1, x - 1, y);
			}
			if(y < 13 && maze[x][y + 1] == ' ') {
				maze[x][y + 1] = 'v';
				current = new Position(current.getRow(), current.getColumn() + 1);
				array[i] = current;
				return MazeRecursion(start, goal, current, array, i+=1, x, y + 1);
			}
			if(x < 6 && maze[x + 1][y] == ' ') {
				maze[x + 1][y] = 'v';
				current = new Position(current.getRow() + 1, current.getColumn());
				array[i] = current;
				return MazeRecursion(start, goal, current, array, i+=1, x + 1, y);
			}
			
			i -= 1;
			current = array[i - 1];
			current = new Position(current.getRow(), current.getColumn());
			array[i] = current;
			return MazeRecursion(start, goal, current, array, i, current.getRow(), current.getColumn());
			
		}
		
	}

//	private boolean test(Position start, Position goal, Position current, Position[] array, int i, int x, int y) {
//		if (current.getRow() == goal.getRow() && current.getColumn() == goal.getColumn()) {
//
//			return true;
//		} else {
//			if (x > 0 && maze[y][x - 1] == ' ') {
//				maze[y][x - 1] = 'v';
//				current = new Position(y, x - 1);
//				array[i] = current;
//				i += 1;
//				return test(start, goal, current, array, i, x - 1, y);
//			}
//			if (maze[y - 1][x] == ' ') {
//				maze[y - 1][x] = 'v';
//				current = new Position(y - 1, x);
//				array[i] = current;
//				i += 1;
//				return test(start, goal, current, array, i, x, y - 1);
//			}
//			if (x < 13 && maze[y][x + 1] == ' ') {
//				maze[y][x + 1] = 'v';
//				current = new Position(y, x + 1);
//				array[i] = current;
//				i += 1;
//				return test(start, goal, current, array, i, x + 1, y);
//			}
//			if (maze[y + 1][x] == ' ') {
//				maze[y + 1][x] = 'v';
//				current = new Position(y + 1, x);
//				array[i] = current;
//				i += 1;
//				return test(start, goal, current, array, i, x, y + 1);
//			}
//		}
//		
//		//Implement if maze can't get past first slot, it should stop and return false
//		
//		i -= 1;
//		current = array[i - 1];
//		current = new Position(current.getRow(), current.getColumn());
//		array[i] = current;
//		return test(start, goal, current, array, i, current.getColumn(), current.getRow());
//	}

}