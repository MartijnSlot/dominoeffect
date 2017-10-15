/**
 * bone position class for domino effect
 */
public class BonePos {

    private int n1;
    private int n2;

    BonePos(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    int getN1() {
        return n1;
    }

    void setN1(int n1) {
        this.n1 = n1;
    }

    int getN2() {
        return n2;
    }

    void setN2(int n2) {
        this.n2 = n2;
    }
}
