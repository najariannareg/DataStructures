//Implement SLL reversal recursively.

public class EX8 {
    public static void main(String[] args) {
        SLL<Integer> s = new SLL<>();
        s.addLast(0);
        s.addLast(1);
        s.addLast(2);
        s.addLast(3);
        s.addLast(4);
        s.addLast(5);
        s.addLast(6);
        s.print();
        //it work perfectly
        //s.reverseSLLIt();
        //it is printing 0 which is not the expected result
        //reverseSLLIt(s.getHead());
        //it is printing 0 which is not the expected result
        reverseSLLRec(null, s.getHead(), s.getHead().getNext());
        s.print();
    }
    public static void reverseSLLIt(SLL.SNode head){
        SLL.SNode prev = null;
        SLL.SNode next = head.getNext();
        while(next != null){
            head.setNext(prev);
            prev = head;
            head = next;
            next = next.getNext();
        }
        head.setNext(prev);
    }
    public static void reverseSLLRec(SLL.SNode prev, SLL.SNode head, SLL.SNode next){
        if(next != null){
            head.setNext(prev);
            reverseSLLRec(head, next, next.getNext());
        }
        head.setNext(prev);
    }
}
