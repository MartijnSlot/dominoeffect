public class Field {


    private final int value;
    private boolean filled;

    public Field (int value) {
        this.filled = false;
        this.value = value;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public void getFilled(boolean filled) {
        this.filled = filled;
    }

    public int getValue() {
        return value;
    }

    public boolean isFilled() {
        return filled;
    }
}
