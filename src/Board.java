import java.util.ArrayList;
import java.util.List;

class Board {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 7;
    private List<Integer> fields = new ArrayList();

    public Board (List<Integer> fields) {
        this.fields = fields;
    }

    public int getField(int a) {
        return fields.get(a);
    }

    public Field adjustField (Field field, boolean filled) {
        field.setFilled(filled);
        return field;
    }
}
