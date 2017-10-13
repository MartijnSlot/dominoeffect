import java.util.List;

public class Solver {

    private static int findBoneID(BonePos p, List<Bone> bones) {
        int boneId = 0;
        for (int a = 0; a == bones.size() - 1; a++) {
            if ((bones.get(a).getPip1() == p.getN1() && bones.get(a).getPip2() == p.getN2()) || (bones.get(a).getPip1() == p.getN2() && bones.get(a).getPip2() == p.getN1())) {
                boneId = bones.get(a).getId();
            }
        }
        return boneId;
    }

    private static List<Bone> removeItem(int boneId, List<Bone> bones) {
        bones.remove(boneId);
        return bones;
    }

    private static int getBoneValue(List<Bone> bones, Board inp, Board out) {
        List<BonePos> freemoves = Board.getFreeMoves(out);
        switch (freemoves.size()) {
            case 1:
                return findBoneID(freemoves.get(0), bones);
            case 2:
                return findBoneID(freemoves.get(1), bones);
            default:
                return 0;
        }
    }

    static Board solve(Board board, Board resultBoard, List<Bone> bones) {
        return resultBoard;
    }
}
