package main.java;

class Move implements Comparable<Move>{
    int moves;
    Board board;
    Move parent;

    //Constructor
    public Move(Board board, int moves, Move parent){
        this.board = board;
        this.moves = moves;
        this.parent = parent;
    }

    //Compare moves with manhattan distance
    @Override
    public int compareTo(Move o) {
        int difference = this.board.getManhattan() + this.moves - o.board.getManhattan() - o.moves;
        if (difference != 0) return difference; //return normal difference of priority functions
        if (this.moves > o.moves) return -1; //if priority is the same give preference to the one with more moves
        return 1;
    }
}