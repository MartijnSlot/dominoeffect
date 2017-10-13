import java.util.List;

public class Solver {

    /*
    get boneID from 2 indexes (BonePos) on inputboard
     */
    private static int findBoneID(int a, int b, List<Bone> bones) {
        int boneId = 0;
        for (int i = 0; a == bones.size() - 1; i++) {
            if ((bones.get(i).getPip1() == a && bones.get(i).getPip2() == b) || (bones.get(i).getPip1() == b && bones.get(i).getPip2() == a)) {
                boneId = bones.get(a).getId();
            }
        }
        return boneId;
    }

    /*
    removes boneID from list of bones
     */
    private static List<Bone> removeBone(int boneId, List<Bone> bones) {
        bones.remove(boneId);
        return bones;
    }

    /*
    get
     */
    private static int getBoneValue(List<Bone> bones, Board inp, Board out, int element) {
        //get available moves for the first 0 element in the outputboard
        List<BonePos> freemoves = Board.getFreeMoves(out);

        //get the 2 sets of board position that correspond to the free moves.
        List<Integer> first = inp.getValue(freemoves.get(0));
        List<Integer> second = inp.getValue(freemoves.get(freemoves.size() - 1));

        // if available moves = 0, then boneValue = 0
        switch (element) {
            case 1:
                return findBoneID(first.get(0), first.get(1), bones);
            case 2:
                return findBoneID(second.get(0), second.get(1), bones);
            default:
                return 0;
        }
    }

    static Board solve(Board board, Board resultBoard, List<Bone> bones) {

        if (bones.size() == 0) {
            return resultBoard;
        }

        if (getBoneValue(bones, board, resultBoard, 1) != 0 && getBoneValue(bones, board, resultBoard, 2) != 0) {
            solve(board, resultBoard, bones);
        }

        if (getBoneValue(bones, board, resultBoard, 1) != 0) {
            int el1;
            int el2;
            int boneValue = findBoneID(el1, el2, bones);
            resultBoard = Board.replaceBone(p, boneValue, resultBoard);
            bones = removeBone(boneValue, bones);
            solve(board, resultBoard, bones);
        }

        if (getBoneValue(bones, board, resultBoard, 2) != 0) {
            solve(board, resultBoard, bones);
        }

        return board;
    }
}
