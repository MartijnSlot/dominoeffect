import java.util.ArrayList;
import java.util.List;

class Board {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 7;
    private List<Integer> fields = new ArrayList<>();

    Board(List<Integer> fields) {
        this.fields = fields;
    }

    private List<Integer> getFields() {
        return fields;
    }

    //map BonePos p to actual values on the inputboard
    List<Integer> getValue(BonePos p) {
        List<Integer> boneValues = new ArrayList<>();
        boneValues.add(fields.get(p.getN1()));
        boneValues.add(fields.get(p.getN2()));
        return boneValues;
    }

    private static int findFree(Board resultBoard) {
        return resultBoard.getFields().indexOf(0);
    }

    static Board replaceBone(BonePos p, int boneValue, Board resultBoard) {
        resultBoard.getFields().set(p.getN1(), boneValue);
        resultBoard.getFields().set(p.getN2(), boneValue);
        return resultBoard;
    }

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

    private static List<BonePos> checkMoves(List<BonePos> moves, Board resultBoard) {
        for (int i = 0; i < moves.size(); i++) {
            if (getValue(moves.get(i).getN1()) != 0 && getValue(moves.get(i).getN2()) != 0) {
                moves.remove(i);
            }
        }
        return moves;
    }

    static List<BonePos> getFreeMoves(Board resultBoard) {
        return moves(findFree(resultBoard), resultBoard);
    }
}

