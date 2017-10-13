import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> input1 = Arrays.asList(4, 2, 5, 2, 6, 3, 5, 4, 5, 0, 4, 3, 1, 4, 1, 1, 1, 2, 3, 0, 2, 2, 2, 2, 1, 4, 0, 1, 3, 5, 6, 5, 4, 0, 6, 0, 3, 6, 6, 5, 4, 0, 1, 6, 4, 0, 3, 0, 6, 5, 3, 6, 2, 1, 5, 3); //make arraylist
        Board resultBoard1 = new Board(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        List<Bone> bones = Arrays.asList(new Bone(0, 0, 1), new Bone(0, 1, 2), new Bone(0, 2, 3), new Bone(0, 3, 4), new Bone(0, 4, 5), new Bone(0, 5, 6),
            new Bone(0, 6, 7), new Bone(1, 1, 8), new Bone(1, 2, 9), new Bone(1, 3, 10), new Bone(1, 4, 11), new Bone(1, 5, 12), new Bone(1, 6, 13),
            new Bone(2, 2, 14), new Bone(2, 3, 15), new Bone(2, 4, 16), new Bone(2, 5, 17), new Bone(2, 6, 18), new Bone(3, 3, 19), new Bone(3, 4, 20),
            new Bone(3, 5, 21), new Bone(3, 6, 22), new Bone(4, 4, 23), new Bone(4, 5, 24), new Bone(4, 6, 25),
            new Bone(5, 5, 26), new Bone(5, 6, 27), new Bone(6, 6, 28));
        Board board1 = new Board(input1);

        Board finalBoard = Solver.solve(board1, resultBoard1, bones);

        for (int i = 0; i < bones.size(); i++) {
            bones.remove(i);
        }

//        finalBoard.toString();
    }

}
