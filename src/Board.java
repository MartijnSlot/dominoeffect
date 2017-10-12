import java.util.ArrayList;
import java.util.List;

class Board {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 7;
    private List<Integer> fields = new ArrayList<Integer>();

    public Board (List<Integer> fields) {
        this.fields = fields;
    }

    public int getField(int a) {
        return fields.get(a);
    }

    public List<Integer> getFields() {
        return fields;
    }

    public void replaceFieldField (int field, int value) {
        fields.set(field, value);
    }

    public static Board solve(Board board1, Board resultBoard, List<Bone> bones) {

        return resultBoard;
    }
}
