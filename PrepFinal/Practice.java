public class Practice {

    public static int closest(AVLTreeMap<Integer, Integer> t, int k){
        int minDiff = Integer.MAX_VALUE;
        int closestValue = Integer.MAX_VALUE;
        TreeMap.BalanceableBinaryTree.BSTNode<Entry<Integer, Integer>> root = (TreeMap.BalanceableBinaryTree.BSTNode<Entry<Integer, Integer>>)t.root();
        while(root != null && root.getElement() != null){
            int key = root.getElement().getKey();
            int diff = Math.abs(k - key);
            if(diff < minDiff){
                minDiff = diff;
                closestValue = key;
            }
            else if(diff > minDiff)
                break;
            if(key > k)
                root = (TreeMap.BalanceableBinaryTree.BSTNode<Entry<Integer, Integer>>)root.getLeft();
            else if(key < k)
                root = (TreeMap.BalanceableBinaryTree.BSTNode<Entry<Integer, Integer>>)root.getRight();
            else
                break;
        }
        return closestValue;
    }


    public static void queueSort(Queue<Integer> q){
        int count = 0;
        int min = Integer.MAX_VALUE;
        int curr = Integer.MAX_VALUE;
        for(int i = 0; i < q.size(); i++){
            min = q.dequeue();
            for(int j = 0; j < q.size() - count; j++){
                curr = q.dequeue();
                if(curr < min){
                    q.enqueue(min);
                    min = curr;
                }else
                    q.enqueue(curr);
            }
            for(int j = 0; j < count; j++)
                q.enqueue(q.dequeue());
            q.enqueue(min);
            count++;
        }
    }

    public static <E> void queueReverse(Queue<E> q){
        int reversed = 0;
        E curr = null;
        for(int i = 0; i < q.size(); i++){
            curr = q.dequeue();
            for(int j = 0; j < q.size() - reversed; j++)
                q.enqueue(q.dequeue());
            q.enqueue(curr);
            for(int j = 0; j < reversed; j++)
                q.enqueue(q.dequeue());
            reversed++;
        }
    }

    public static void main(String[] args) {

        Queue<Integer> q = new LinkedQueue<>();
        q.enqueue(31);
        q.enqueue(3);
        q.enqueue(7);
        q.enqueue(5);
        q.enqueue(9);
        q.enqueue(16);
        q.enqueue(23);
        //queueSort(q);
        queueReverse(q);
        int size = q.size();
        for(int i = 0; i < size; i++){
            System.out.println(q.dequeue());
        }




//        AVLTreeMap<Integer, Integer> t = new AVLTreeMap<>();
//        t.put(9,9);
//        t.put(4,4);
//        t.put(17,17);
//        t.put(3,3);
//        t.put(6,6);
//        t.put(16,16);
//        t.put(20,20);
//        System.out.println(closest(t, 18));
    }

}
