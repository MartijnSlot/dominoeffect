import java.util.ArrayList;
import java.util.List;

class Board {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 7;
    private List<Integer> fields = new ArrayList<>();

    public Board(List<Integer> fields) {
        this.fields = fields;
    }

    public List<Integer> getFields() {
        return fields;
    }

    public int getValue(int i) {
        return fields.get(i);
    }

    public static void replaceValue(Board resultBoard, int i, int value) {
        resultBoard.getFields().set(i, value);
    }

    public static int findFree(Board resultBoard) {
        return resultBoard.getFields().indexOf(0);
    }

    public static Board replaceBone(int i, int newValue, Board resultBoard) {
        resultBoard.getFields().set(i, newValue);
        return resultBoard;
    }

    public static List<BonePos> moves(int i, Board resultBoard) {
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

    private static List<BonePos> checkMoves(List<BonePos> moves, Board resultBoard) {
        for (int i = 0; i < moves.size(); i++) {
            if (resultBoard.getValue(moves.get(i).getN1()) != 0 && resultBoard.getValue(moves.get(i).getN2()) != 0) {
                moves.remove(i);
            }
        }
        return moves;
    }

    public static List<BonePos> getFreeMoves(Board resultBoard) {
        return moves(findFree(resultBoard), resultBoard);
    }
}

