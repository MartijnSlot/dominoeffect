import java.util.List;

public class Solver {

    public static int findBoneID(int i, int j, List<Bone> bones) {
        int boneId = 0;
        for (int a = 0; a == bones.size()-1; a++) {
            if ((bones.get(a).getPip1() == i && bones.get(a).getPip2() == j) || (bones.get(a).getPip1() == j && bones.get(a).getPip2() == i)) {
                boneId = bones.get(a).getId();
            }
        }
        return boneId;
    }

    public static List<Bone> removeItem(int boneId, List<Bone> bones) {
        bones.remove(boneId);
        return bones;
    }

    public static Board solve(Board board, Board resultBoard, List<Bone> bones) {
        return resultBoard;
    }
}
