import java.util.ArrayList;
import java.util.List;

/**
 * board implementation of the domino game. When changing input, also change WIDTH and HEIGHT here
 */
class Board {

    private static final int WIDTH = 3;
    private static final int HEIGHT = 2;
    private List<Integer> fields = new ArrayList<>();

    Board(List<Integer> fields) {
        this.fields = fields;
    }

    /**
     * map BonePos p to actual values on the inputboard
     * @param p, a position
     * @param inputboard; the game board
     * @return boneValues
     */
    static List<Integer> getValue(BonePos p, Board inputboard) {
        List<Integer> boneValues = new ArrayList<>();
        boneValues.add(inputboard.getFields().get(p.getN1()));
        boneValues.add(inputboard.getFields().get(p.getN2()));
        return boneValues;
    }

    /**
     * finds the first free spot, a 0 on the resultboard
     * @param resultBoard; board with the free spot
     * @return the index of the free spot
     */
    private static int findFree(Board resultBoard) {
        return resultBoard.getFields().indexOf(0);
    }

    /**
     * replaces the 0s signifying an empty spot with a bone on the resultboard
     * @param p, the position
     * @param boneValue, the identifier of the bones
     * @param resultBoard, the resultboard
     * @return changed resultboard
     */
    static Board replaceBone(BonePos p, int boneValue, Board resultBoard) {
        resultBoard.getFields().set(p.getN1(), boneValue);
        resultBoard.getFields().set(p.getN2(), boneValue);
        return resultBoard;
    }

    /**
     * get the possible bone moves from an empty spot
     * @param i, the empty spot
     * @param resultBoard, the board to calculate the empty moves on
     * @return a list of possible moves
     */
    private static List<BonePos> moves(int i, Board resultBoard) {
        List<BonePos> moves = new ArrayList<>();
        if ((i + 1) % WIDTH == 0) {
            moves.add(new BonePos(i, i + WIDTH));
        } else if (i + WIDTH >= resultBoard.getFields().size()) {
            moves.add(new BonePos(i, i + 1));
        } else {
            moves.add(new BonePos(i, i + WIDTH));
            moves.add(new BonePos(i, i + 1));
        }
        moves = checkMoves(moves, resultBoard);
        return moves;
    }

    /**
     * checks the results of the move function above for non-empty moves
     * @param moves, list of positions
     * @param resultBoard, board to check the position on
     * @return list of positions where a bone can be places
     */
    private static List<BonePos> checkMoves(List<BonePos> moves, Board resultBoard) {
        for (int i = 0; i < moves.size(); i++) {
            if (getValue(moves.get(i), resultBoard).get(0) != 0 && getValue(moves.get(i), resultBoard).get(1) != 0) {
                moves.remove(i);
            }
        }
        return moves;
    }

    /**
     * deep copies board
     * @param board to copy
     * @return copied board
     */
    static Board deepCopy(Board board) {
        List<Integer> oldCopy = board.getFields();
        List<Integer> arrayCopy = new ArrayList<>();
        for (Integer oldCopyElement : oldCopy) {
            arrayCopy.add(new Integer(oldCopyElement));
        }
        return new Board(arrayCopy);
    }

    static List<BonePos> getFreeMoves(Board resultBoard) {
        return moves(findFree(resultBoard), resultBoard);
    }

    private List<Integer> getFields() {
        return fields;
    }

    public String toString() {
        String grid = "";
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                int a = fields.get(j + i * WIDTH);
                String b = (a < 10) ? (b = (a + "  ")) : (b = (a + " "));
                grid += (b);
            }
            grid += "\n";
        }
        return grid;
    }
}

