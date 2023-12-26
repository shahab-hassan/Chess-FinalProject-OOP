package stockfish.src;

public class MoveConverter {

    public static void main(String[] args) {
        String move = "d2d4";
        int[] startCoords = convertAlgebraicToIndices(move.substring(0, 2));
        int[] endCoords = convertAlgebraicToIndices(move.substring(2));

    }

    public static int[] convertAlgebraicToIndices(String algebraic) {
        int[] indices = new int[2];
        indices[0] = algebraic.charAt(0) - 'a'; // Convert file (column) to index
        indices[1] = 8 - Character.getNumericValue(algebraic.charAt(1)); // Convert rank (row) to index

        return indices;
    }
}
