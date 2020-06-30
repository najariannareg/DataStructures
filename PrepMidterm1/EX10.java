//Given the head node of DLL, split it to 2 SLL from the middle. 1st one having
//pointers going backward. 2nd going forward.


public class EX10 {
    public static void main(String[] args) {
        SLL<Integer> s1 = new SLL<>();
        SLL<Integer> s2 = new SLL<>();
        DLL<Integer> d = new DLL<>();
        d.addLast(0);
        d.addLast(1);
        d.addLast(2);
        d.addLast(3);
        d.addLast(4);
        d.addLast(5);
        d.addLast(6);
        d.print();
        splitDLL2SLL(d.getHead(), s1, s2);
        s1.print();
        s2.print();
    }
    public static void splitDLL2SLL(DLL.DNode head, SLL s1, SLL s2){
        DLL.DNode tail1 = head;
        DLL.DNode tail2 = head;
        while(tail2.getNext() != null && tail2.getNext().getNext() != null) {
            s1.addFirst(tail1.getElement());
            tail1 = tail1.getNext();
            tail2 = tail2.getNext().getNext();
        }
        s1.addFirst(tail1.getElement());
        tail2 = tail1.getNext();
        while(tail2.getNext() != null){
            s2.addLast(tail2.getElement());
            tail2 = tail2.getNext();
        }
    }
}
