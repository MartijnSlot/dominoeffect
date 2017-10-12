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

    public void replaceFieldValue(Board board, int i, int value) {
        board.getFields().set(i, value);
    }

    public int findFree (Board board) {
        return board.getFields().indexOf(0);
    }

    private Board replaceBone (int i, int newValue, Board board) {
        board.getFields().set(i, newValue);
        return board;
    }
}

