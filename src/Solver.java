import java.util.ArrayList;
import java.util.List;

/**
 * solver class for the domino effect
 */
public class Solver {

    /**
     * get BoneID from 2 values
     * @param values 2 ints
     * @param bones the list of available bones
     * @return the number of the bone
     */
    private static int findBoneID(List<Integer> values, List<Bone> bones) {
        int boneId = 0;
        for (int i = 0; i < bones.size(); i++) {
            if ((bones.get(i).getPip1() == values.get(1) && bones.get(i).getPip2() == values.get(0)) || (bones.get(i).getPip1() == values.get(0) && bones.get(i).getPip2() == values.get(1))) {
                boneId = bones.get(i).getId();
            }
        }
        return boneId;
    }

    /**
     * removes a bone from the bone list
     * @param boneId id of the bone to remove
     * @param bones list of available bones
     * @return a list of available bones except for the removed bone
     */
    private static List<Bone> removeBone(int boneId, List<Bone> bones) {
        if (bones.size() != 0) {
            bones.removeIf(bone -> bone.getId() == boneId); // #tochfunctioneel!
        }
        return bones;
    }

    /**
     * Solver method for the domino effect
     * @param board, inputboard
     * @param resultBoard, resultboard
     * @param bones, available bones
     * @return the resultboard if correct, null if no solution is possible
     */
    static Board solve(Board board, Board resultBoard, List<Bone> bones) {

        Boolean solved = false;

        if (bones.size() == 0) {
            System.out.println("solution: \n" + resultBoard.toString());
            solved = true;
            return resultBoard;
        }

        List<BonePos> freemoves = Board.getFreeMoves(resultBoard);

        if (freemoves.size() == 2 && freemoves.get(0) != null && freemoves.get(1) != null) {

            Board resultBoard1 = Board.deepCopy(resultBoard);
            Board resultBoard2 = Board.deepCopy(resultBoard);
            List<Bone> bones1 = deepCopyBones(bones);
            List<Bone> bones2 = deepCopyBones(bones);

            List<Integer> values1 = Board.getValue(freemoves.get(0), board);
            int boneValue1 = findBoneID(values1, bones1);
            resultBoard1 = Board.replaceBone(freemoves.get(0), boneValue1, resultBoard1);
            bones1 = removeBone(boneValue1, bones1);

            List<Integer> values2 = Board.getValue(freemoves.get(1), board);
            int boneValue2 = findBoneID(values2, bones2);
            resultBoard2 = Board.replaceBone(freemoves.get(1), boneValue2, resultBoard2);
            bones2 = removeBone(boneValue2, bones2);

            solve(board, resultBoard1, bones1);
            solve(board, resultBoard2, bones2);
        }

        if (freemoves.size() == 1 && freemoves.get(0) != null) {
            List<Integer> values3 = Board.getValue(freemoves.get(0), board);
            int boneValue3 = findBoneID(values3, bones);
            Board resultBoard3 = Board.deepCopy(Board.replaceBone(freemoves.get(0), boneValue3, resultBoard));
            List<Bone> bones3 = deepCopyBones(removeBone(boneValue3, bones));
            solve(board, resultBoard3, bones3);
        }

        if (freemoves.size() == 2 && freemoves.get(0) == null && freemoves.get(0) != null) {
            List<Integer> values4 = Board.getValue(freemoves.get(1), board);
            int boneValue4 = findBoneID(values4, bones);
            Board resultBoard4 = Board.deepCopy(Board.replaceBone(freemoves.get(1), boneValue4, resultBoard));
            List<Bone> bones4 = deepCopyBones(removeBone(boneValue4, bones));
            solve(board, resultBoard4, bones4);
        }
        return null;
    }

    /**
     * deep copies the bone list
     * @param bones
     * @return bones
     */
    private static List<Bone> deepCopyBones(List<Bone> bones) {
        List<Bone> boner = new ArrayList<>();
        for (Bone bone : bones) {  //#tochfunctioneel!
            boner.add(new Bone(new Integer(bone.getPip1()), new Integer(bone.getPip2()), new Integer(bone.getId())));
        }
        return boner;
    }
}
