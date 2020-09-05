
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class BSTit{
    public Stack<TreeNode> s;
    public TreeNode cur;
    BSTit(TreeNode root){
        cur = root;
        s = new Stack<>();
        while(cur!=null){
            s.push(cur);
            cur = cur.left;
        }
    }
    
    public boolean hasNext(){
        if(cur!=null || !s.isEmpty()) return true;
        return false;
    }
    
    public TreeNode nextNode(){
        if(s.isEmpty()) return null;
        TreeNode ret = s.pop();
        cur = ret.right;
        while(cur!=null){
            s.push(cur); cur = cur.left;
        }
        return ret;
    }
}
