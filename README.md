## 8 Puzzle with A* Search
_www.wikiwand.com/en/15_puzzle_
### 1. Definition
The 15-puzzle (also called Gem Puzzle, Boss Puzzle, Game of Fifteen, Mystic Square and many others) is a sliding puzzle that consists of a frame of numbered square tiles in random order with one tile missing. 
The puzzle also exists in other sizes, particularly the smaller 8-puzzle. If the size is 3×3 tiles, the puzzle is called the 8-puzzle or 9-puzzle, and if 4×4 tiles, the puzzle is called the 15-puzzle or 16-puzzle named, respectively, for the number of tiles and the number of spaces.
The object of the puzzle is to place the tiles in order by making sliding moves that use the empty space. 
### 2. Solving
The n-puzzle is a classical problem for modelling algorithms involving heuristics. Commonly used heuristics for this problem include counting the number of misplaced tiles and finding the sum of the taxicab distances between each block and its position in the goal configuration.
Note that both are admissible, i.e. they never overestimate the number of moves left, which ensures optimality for certain search algorithms such as A*.
### 3. Implementation
First, getBoard() method from Board class is used for construct an instance of puzzle from boards directory, then checks is puzzle solvable with isSolvable() method. If it is, it starts a timer for calculate the time passed and sends the puzzle to the constructor of Solver class.

In constructor, a Stack of solution and a Priority Queue of moves created. Initial state is inserted to PQ, in while loop, minimum number of PQ deleted. For every possible move, board is moved. If goal state is reached, solution path is pushed to the Stack and returned.

In Move class, compareTo() method uses manhattan distance for calculate heuristics.  
In Runner class, main() method can run multiple instances of boards located under boards directory.  
In Board class, isSolvable() method checks is board even and counts the number of inversions using 2 for loops,then returns isEvenBoard != isEvenInversions.  