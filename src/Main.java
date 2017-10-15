import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the domino effect
 */
public class Main {

    public static void main(String[] args) {

        /**
         * input 1
         */
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

        /**
         * input 2
         */
            List<Integer> input2 = new ArrayList<>();
        input2.add(0); input2.add(1); input2.add(0); input2.add(0);
        input2.add(3); input2.add(2); input2.add(0); input2.add(1);
        input2.add(1); input2.add(2); input2.add(0); input2.add(1);
        input2.add(3); input2.add(2); input2.add(2); input2.add(1);
        input2.add(2); input2.add(3); input2.add(3); input2.add(3);

        List<Integer> output2 = new ArrayList<>();
        for (int i = 1; i <= input2.size(); i++) {
            output2.add(0);
        }

        List<Bone> bones2 = new ArrayList<>();
        bones2.add(new Bone(0, 0, 1)); bones2.add(new Bone(0, 1, 2)); bones2.add(new Bone(1, 2, 3));
        bones2.add(new Bone(0, 3, 4)); bones2.add(new Bone(1, 1, 5)); bones2.add(new Bone(0, 2, 6));
        bones2.add(new Bone(2, 2, 7)); bones2.add(new Bone(1, 3, 8)); bones2.add(new Bone(2, 3, 9));
        bones2.add(new Bone(3, 3, 10));

        /**
         * input 3
         */
        List<Integer> input3 = new ArrayList<>();
        input3.add(0); input3.add(1); input3.add(0); input3.add(0); input3.add(0); input3.add(2);

        List<Integer> output3 = new ArrayList<>();
        for (int i = 1; i <= input3.size(); i++) {
            output3.add(0);
        }

        List<Bone> bones3 = new ArrayList<>();
        bones3.add(new Bone(0, 0, 1)); bones3.add(new Bone(0, 1, 2)); bones3.add(new Bone(0, 2, 3));

        Board inp = new Board(input3);
        Board out = new Board(output3);

        System.out.println("input: \n" + inp.toString());
        Solver.solve(inp, out, bones3);

    }

}
