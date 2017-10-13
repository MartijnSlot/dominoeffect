import java.util.List;

public class Solver {

    /*
    get boneID from 2 indexes (BonePos) on inputboard
     */
    private static int findBoneID(List<Integer> values, List<Bone> bones) {
        int boneId = 0;
        for (int i = 0; i < bones.size() - 1; i++) {
            if ((bones.get(i).getPip1() == values.get(0) && bones.get(i).getPip2() == values.get(1)) || (bones.get(i).getPip1() == values.get(1) && bones.get(i).getPip2() == values.get(0))) {
                boneId = bones.get(i).getId();
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
        List<Integer> first = Board.getValue(freemoves.get(0), inp);
        List<Integer> second = Board.getValue(freemoves.get(freemoves.size() - 1), inp);

        // if available moves = 0, then boneValue = 0
        switch (element) {
            case 1:
                return findBoneID(first, bones);
            case 2:
                return findBoneID(second, bones);
            default:
                return 0;
        }
    }

    static Board solve(Board board, Board resultBoard, List<Bone> bones) {

        if (bones.size() == 0) {
            return resultBoard;
        }

        if (getBoneValue(bones, board, resultBoard, 1) != 0 && getBoneValue(bones, board, resultBoard, 2) != 0) {
            List <Integer> values1 = Board.getValue(Board.getFreeMoves(resultBoard).get(0), board);
            int boneValue1 = findBoneID(values1, bones);
            Board resultBoard1 = Board.replaceBone(Board.getFreeMoves(resultBoard).get(0), boneValue1, resultBoard);
            List<Bone> bones1 = removeBone(boneValue1, bones);
            solve(board, resultBoard1, bones1);

            List <Integer> values2 = Board.getValue(Board.getFreeMoves(resultBoard).get(1), board);
            int boneValue2 = findBoneID(values2, bones);
            Board resultBoard2 = Board.replaceBone(Board.getFreeMoves(resultBoard).get(1), boneValue2, resultBoard);
            List<Bone> bones2 = removeBone(boneValue2, bones);
            solve(board, resultBoard2, bones2);
        }

        if (getBoneValue(bones, board, resultBoard, 1) != 0) {
            List <Integer> values = Board.getValue(Board.getFreeMoves(resultBoard).get(0), board);
            int boneValue = findBoneID(values, bones);
            resultBoard = Board.replaceBone(Board.getFreeMoves(resultBoard).get(0), boneValue, resultBoard);
            bones = removeBone(boneValue, bones);
            solve(board, resultBoard, bones);
        }

        if (getBoneValue(bones, board, resultBoard, 2) != 0) {
            List <Integer> values = Board.getValue(Board.getFreeMoves(resultBoard).get(1), board);
            int boneValue = findBoneID(values, bones);
            resultBoard = Board.replaceBone(Board.getFreeMoves(resultBoard).get(1), boneValue, resultBoard);
            bones = removeBone(boneValue, bones);
            solve(board, resultBoard, bones);
        }

        return null;
    }
}
