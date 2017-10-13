public class Bone {

    private final int pip1;
    private final int pip2;
    private final int id;

    Bone(int pip1, int pip2, int id) {
        this.pip1 = pip1;
        this.pip2 = pip2;
        this.id = id;
    }

    int getPip1() {
        return pip1;
    }

    int getPip2() {
        return pip2;
    }

    int getId() {
        return id;
    }

}
