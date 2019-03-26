package CSE362AI;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class Board {
    public int size;
    public int[] tiles;
    public int location;
    public int hamming,manhattan=-1;

    public Board(int[][] blocks){
        try{
            size = blocks.length;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        int checkpoint = 0;
        tiles = new int[size*size];
        for(int i=0; i<size; i++)
            for (int j=0; j<size; j++){
                if(blocks[i][j]==0)
                    location = checkpoint;
                tiles[checkpoint++] = blocks[i][j];
            }
    }

    // constructor for the new neighbors.
    private Board(int[] blocks, int size, int zeroLocation) {
        this.size = size;
        this.location = zeroLocation;
        tiles = new int[size*size];
        System.arraycopy(blocks, 0, tiles, 0, tiles.length);
    }

    public int getSize(){
        return size;
    }

    // TODO: 21-Mar-19 check hamming distance
    public int getHamming(){
        if(hamming != -1) return hamming;

        hamming = 0;
        int i=0;
        while(i < tiles.length){
            if (tiles[i] != (i+1) && tiles[i] != 0) //if tile is not in the right position or it is not piece 0
                hamming++;
            i++;
        }

        return hamming;
    }

    // TODO: 21-Mar-19 check manhattan distance
    public int getManhattan(){
        if(manhattan != -1) return manhattan;

        manhattan = 0;
        for(int i=0; i<tiles.length; i++){
            if(tiles[i] == (i+1) || i == location) continue; //if tiles[i] is goal or 0 continue
            manhattan += Math.abs((i / size) - ((tiles[i] - 1) / size)); // count rows displaced;
            manhattan += Math.abs((i % size) - ((tiles[i] - 1) % size)); // count columns displaced;
        }
        return manhattan;
    }

    public boolean isGoal(){
        if(tiles[tiles.length-1] != 0) return false; //if 0 is not in the corner

        for(int i=0; i<tiles.length-1; i++)
            if(tiles[i] != (i+1)) //check all tiles
                return false;
        return true;
    }

    public boolean isSolvable(){
        AtomicInteger inversions = new AtomicInteger();
        for(int i=0; i<tiles.length; i++){
            if (tiles[i] == 0) continue; //if tile is not 0
            for(int j=i; j< tiles.length; j++) //check other tiles
                if (tiles[j] < tiles[i] && tiles[j] != 0) inversions.getAndIncrement();}

        boolean isEvenBoard = (size % 2) == 0; // true if even, false if odd

        if(isEvenBoard)
            inversions.addAndGet(location / size); //if board is even add row 0
        boolean isEvenInversions = (inversions.get() % 2) == 0; //true if total inversions is even

        return (isEvenBoard != isEvenInversions);
    }

    //HELPER METHODS
    public boolean equals(Object y){
        if (y == null) return false;
        if (y == this) return true;
        if (this.getClass() != y.getClass()) return false;
        for (int i = 0; i < tiles.length; i++)//check if any difference for all tiles
            if (this.tiles[i] != ((Board) y).tiles[i]) return false;
        return true;//else return true
    }

    public Iterable<Board> neighbors(){
        Stack<Board> neighbors = new Stack<>();

        if (location / size != 0) // has up neighbor?
            pushNeighborToStack(neighbors, -size);
        if (location / size != size - 1) // has down neighbor?
            pushNeighborToStack(neighbors, size);
        if (location % size != 0) // has left neighbor?
            pushNeighborToStack(neighbors, -1);
        if (location % size != size - 1) // has right neighbor?
            pushNeighborToStack(neighbors, 1);

        return neighbors;
    }

    protected void pushNeighborToStack(Stack<Board> neighbors, int displace) {
        swap(tiles, location, location + displace); // Swap neighbor tile and blank tile
        neighbors.push(new Board(tiles, size, location + displace)); // Push neighbor board to stack
        swap(tiles, location, location + displace); // Switch tiles back to its original state
    }

    private void swap(int[] swapBoard, int tile1, int tile2) {
        int temp = swapBoard[tile1];
        swapBoard[tile1] = swapBoard[tile2];
        swapBoard[tile2] = temp;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < tiles.length; i++) {
            s.append(String.format("%2d ", tiles[i]));
            if ((i + 1) % size == 0)
                s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Board testBoard = new Board(new int[][]{{0, 7, 2}, {5, 8, 3}, {1, 6, 4}});
        System.out.println(testBoard.toString());
    }
}
