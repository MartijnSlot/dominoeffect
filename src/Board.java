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

    public void replaceValue(Board resultBoard, int i, int value) {
        resultBoard.getFields().set(i, value);
    }

    public int findFree(Board resultBoard) {
        return resultBoard.getFields().indexOf(0);
    }

    public Board replaceBone(int i, int newValue, Board resultBoard) {
        resultBoard.getFields().set(i, newValue);
        return resultBoard;
    }

    public List<Position> moves(int i, Board resultBoard) {
        List<Position> moves = new ArrayList<>();
        if ((i + 1) % WIDTH == 0) {
            moves.add(new Position(i, i + WIDTH));
        } else if (i + WIDTH >= resultBoard.getFields().size()) {
            moves.add(new Position(i, i + 1));
        } else {
            moves.add(new Position(i, i + WIDTH));
            moves.add(new Position(i, i + 1));
        }
        moves = checkMoves(moves, resultBoard);
        return moves;
    }

    private List<Position> checkMoves(List<Position> moves, Board resultBoard) {
        for (int i = 0; i < moves.size(); i++) {
            if (resultBoard.getValue(moves.get(i).getN1()) != 0 && resultBoard.getValue(moves.get(i).getN2()) != 0) {
                moves.remove(i);
            }
        }
        return moves;
    }
}

