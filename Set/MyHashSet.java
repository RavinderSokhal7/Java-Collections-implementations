// time : 0( 1 ), O ( n ), because using simple bst not Self Balancing Bst (O(logn)) : space : O ( n )
class MyHashSet {
    class Node{
        int hash;
        int key;
        // int value;
        Node left;
        Node right;
        Node(int h, int k){
            hash = h;key = k;
        }
    }

    private int initialCapacity = 1001;
    private Node [] HashT; // buckets / hashtable
    // hash function
    private int hashCode(int key){
        return key%initialCapacity; // Better the hash function better the performance
    }
    // inserting in bst
    private Node insert(Node root, int key){
    	int hash = hashCode(key);
    	if(root == null) return new Node(hash, key);
    	Node p = root;
    	while(p!=null){
    		if(p.key == key) return root;
    		else if(p.key>key){
    			if(p.left==null){
    				p.left = new Node (hash, key);return root;
    			}
    			p = p.left;
    		}
    		else{
    			if(p.right==null){
    				p.right = new Node(hash, key); return root;
    			}
    			p = p.right;
    		}
    	}
    	return root;
    }
    private boolean isLeaf(Node p){
    	if(p.left == null && p.right == null) return true;
    	return false;
    }
    private boolean isSingle(Node p){
    	if((p.left!=null && p.right==null) || (p.left==null && p.right!=null)) return true;
    	return false;
    }
    // inorder succesor
    private Node succesor(Node p){
    	p = p.right;
    	if(p==null) return null;
    	while(p.left!=null) p = p.left;
    	return p;
    }
    // delete from BST
    private Node delete(Node root, int key){
    	if(root==null) return null;
    	Node p = root;
    	if(p.key == key){
			if(isLeaf(p)){
				return null;
			}
			else if(isSingle(p)){
				if(p.left!=null){
					return p.left;
				}
				return p.right;
			}
			else{
				Node q = succesor(p);
				p.key = q.key;
				p.right = delete(p.right, p.key);
			}
		}
		else if(p.key<key) p.right = delete(p.right, key);
		else p.left = delete(p.left, key);
    	return root;
    }
    //Find in BST
    private boolean find(Node p, int key){
    	if(p==null) return false;
    	while(p!=null){
    		if(p.key==key) return true;
    		else if(p.key<key) p = p.right;
    		else p = p.left;
    	}
    	return false;
    }
    /** Main DataStructure methods */
    public MyHashSet() {
        HashT = new Node[initialCapacity];
    }
    // add key if not present
    public void add(int key) {
        int hash = hashCode(key);
        HashT[hash] = insert(HashT[hash], key);
    }
    // remove key if present
    public void remove(int key) {
        int hash = hashCode(key);
        HashT[hash] = delete(HashT[hash], key);
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return find(HashT[hashCode(key)],key);
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
