package main.java;

import edu.princeton.cs.algs4.*;

public class Solver {
    private Stack<Board> solution;
    private int moves;

    public Solver(Board initial){
        solution = new Stack<>();
        MinPQ<Move> q = new MinPQ<>();
        q.insert(new Move(initial, 0, null));
        while(true){
            Move move = q.delMin();
            if(move.board.isGoal()){ //goal has been reached, collect solution path
                this.moves = move.moves;
                do{
                    solution.push(move.board);
                    move = move.parent;
                }
                while(move != null);
                return; //done solving
            }
            for(Board next : move.board.neighbors()){
                if(move.parent == null || !next.equals(move.parent.board)) //check one move back to prevent looping
                    q.insert(new Move(next, move.moves+1, move));
            }
        }
    }

    public int moves(){
        return moves;
    }

    public Iterable<Board> solution(){
        return solution;
    }

    public static Board getBoard(In in){
        int size = in.readInt();
        int[][] blocks = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                blocks[i][j] = in.readInt();
        return new Board(blocks);
    }

    public static void main(String[] args) {
        //args used for multiple run with Runner
        Board puzzle = getBoard(new In(args[0]));

        // check if puzzle is solvable
        // if solvable, solve it and output solution
        if (puzzle.isSolvable()) {
            Stopwatch stopwatch = new Stopwatch();
            Solver solver = new Solver(puzzle);
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
            StdOut.println("Solved in " + stopwatch.elapsedTime() + " seconds.");
        }
        else {
            StdOut.println("Unsolvable puzzle");
            throw new IllegalArgumentException();
        }
    }
}
