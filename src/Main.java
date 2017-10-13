import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> input1 = new ArrayList<>();
            input1.add(4); input1.add(2); input1.add(5); input1.add(2); input1.add(6); input1.add(3); input1.add(5); input1.add(4);
            input1.add(5); input1.add(0); input1.add(4); input1.add(3); input1.add(1); input1.add(4); input1.add(1); input1.add(1);
            input1.add(1); input1.add(2); input1.add(3); input1.add(0); input1.add(2); input1.add(2); input1.add(2); input1.add(2);
            input1.add(1); input1.add(4); input1.add(0); input1.add(1); input1.add(3); input1.add(5); input1.add(6); input1.add(5);
            input1.add(4); input1.add(0); input1.add(6); input1.add(0); input1.add(3); input1.add(6); input1.add(6); input1.add(5);
            input1.add(4); input1.add(0); input1.add(1); input1.add(6); input1.add(4); input1.add(0); input1.add(3); input1.add(0);
            input1.add(6); input1.add(5); input1.add(3); input1.add(6); input1.add(2); input1.add(1); input1.add(5); input1.add(3);

        List<Integer> output1 = new ArrayList<>();
        for (int i = 1; i <= input1.size(); i++) {
            output1.add(0);
        }

        Board inp = new Board(input1);
        Board out = new Board(output1);

        List<Bone> bones = new ArrayList<>();
            bones.add(new Bone(0, 0, 1)); bones.add(new Bone(0, 1, 2)); bones.add(new Bone(0, 2, 3));
            bones.add(new Bone(0, 3, 4)); bones.add(new Bone(0, 4, 5)); bones.add(new Bone(0, 5, 6));
            bones.add(new Bone(0, 6, 7)); bones.add(new Bone(1, 1, 8)); bones.add(new Bone(1, 2, 9));
            bones.add(new Bone(1, 3, 10)); bones.add(new Bone(1, 4, 11)); bones.add(new Bone(1, 5, 12));
            bones.add(new Bone(1, 6, 13)); bones.add(new Bone(2, 2, 14)); bones.add(new Bone(2, 3, 15));
            bones.add(new Bone(2, 4, 16)); bones.add(new Bone(2, 5, 17)); bones.add(new Bone(2, 6, 18));
            bones.add(new Bone(3, 3, 19)); bones.add(new Bone(3, 4, 20)); bones.add(new Bone(3, 5, 21));
            bones.add(new Bone(3, 6, 22)); bones.add(new Bone(4, 4, 23)); bones.add(new Bone(4, 5, 24));
            bones.add(new Bone(4, 6, 25)); bones.add(new Bone(5, 5, 26)); bones.add(new Bone(5, 6, 27));
            bones.add(new Bone(6, 6, 28));

        Board finalBoard = Solver.solve(inp, out, bones);

        System.out.println(input1.get(1));

//        output1.toString();


    }

}
