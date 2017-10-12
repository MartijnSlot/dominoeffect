import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 7;
    private List<Field> fields = new ArrayList();

    public Board (List<Field> fields) {
        this.fields = fields;
    }
}
