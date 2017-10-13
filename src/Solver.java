import java.util.ArrayList;
import java.util.List;

public class Solver {

    /**
     get boneID from 2 indexes (BonePos) on inputboard
     **/
    private static int findBoneID(List<Integer> values, List<Bone> bones) {
        int boneId = 0;
        for (int i = 0; i < bones.size() - 1; i++) {
            if ((bones.get(i).getPip1() == values.get(1) && bones.get(i).getPip2() == values.get(1)) || (bones.get(i).getPip1() == values.get(0) && bones.get(i).getPip2() == values.get(0))) {
                boneId = bones.get(i).getId();
            }
        }
        return boneId;
    }

    /**
     removes boneID from list of bones
     **/
    private static List<Bone> removeBone(int boneId, List<Bone> bones) {
        if (bones.size() != 0) {
            bones.remove(boneId);
        }
        return bones;
    }

    /**
     get inputboard values that correspond to the freemoves of the outputboard
     **/
    private static List<Integer> getBoneValue(Board inp, BonePos bonePos) {
        //get available moves for the first 0 element in the outputboard
        //get the 2 sets of board position that correspond to the free moves.
        // if available moves = 0, then boneValue = 0
        return Board.getValue(bonePos, inp);
    }

    static Board solve(Board board, Board resultBoard, List<Bone> bones) {
//        System.out.println(resultBoard.toString());

        if (bones.size() == 0) {
            System.out.println("solution: \n" + resultBoard.toString());
            return resultBoard;
        }

        List<BonePos> freemoves = Board.getFreeMoves(resultBoard);

        if (freemoves.size() == 2 && freemoves.get(0) != null && freemoves.get(1) != null) {

            List<Integer> values1 = Board.getValue(freemoves.get(0), board);
            int boneValue1 = findBoneID(values1, bones);
            Board resultBoard1 = Board.deepCopy(Board.replaceBone(freemoves.get(0), boneValue1, resultBoard));
            List<Bone> bones1 = deepCopyBones(removeBone(boneValue1, bones));

            List<Integer> values2 = Board.getValue(freemoves.get(1), board);
            int boneValue2 = findBoneID(values2, bones);
            Board resultBoard2 = Board.deepCopy(Board.replaceBone(freemoves.get(1), boneValue2, resultBoard));
            List<Bone> bones2 = removeBone(boneValue2, bones);

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

    private static List<Bone> deepCopyBones(List<Bone> bones) {
        List<Bone> boner = new ArrayList<>();
        for (Bone bone : bones) {
            boner.add(new Bone(new Integer(bone.getPip1()), new Integer(bone.getPip2()), new Integer(bone.getId())));
        }
        return boner;
    }
}
