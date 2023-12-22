package stockfish.src;

public class MoveConverter {

    public static void main(String[] args) {
        String move = "d2d4";
        int[] startCoords = convertAlgebraicToIndices(move.substring(0, 2));
        int[] endCoords = convertAlgebraicToIndices(move.substring(2));

        System.out.println("Starting square: Column " + startCoords[0] + ", Row " + startCoords[1]);
        System.out.println("Ending square: Column " + endCoords[0] + ", Row " + endCoords[1]);
    }

    public static int[] convertAlgebraicToIndices(String algebraic) {
        int[] indices = new int[2];
        indices[0] = algebraic.charAt(0) - 'a'; // Convert file (column) to index
        indices[1] = 8 - Character.getNumericValue(algebraic.charAt(1)); // Convert rank (row) to index

        return indices;
    }
}
