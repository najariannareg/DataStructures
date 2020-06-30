//Given the head node, split a CLL from the middle with max 1.5 traversals
//to make 2 new CLLs.

public class EX9 {
    public static void main(String[] args) {
        CLL<Integer> c = new CLL<>();
        c.addLast(0);
        c.addLast(1);
        c.addLast(2);
        c.addLast(3);
        c.addLast(4);
        c.addLast(5);
        //c.addLast(6);
        c.print();

        //CLL<Integer> c1 = c.split();
        //CLL<Integer> c1 = split(c.getTail());
        CLL<Integer> c1 = splitH(c.getHead());
        c1.print();
        c.print();

    }
    public static CLL split(CLL.SNode tail){
        CLL c = new CLL<>();
        CLL.SNode head1 = tail.getNext();
        CLL.SNode tail1 = head1;
        CLL.SNode curr = head1;
        while(curr != tail && curr.getNext() != tail){
            c.addLast(tail1.getElement());
            tail1 = tail1.getNext();
            curr = curr.getNext().getNext();
        }
        c.addLast(tail1.getElement());
        tail.setNext(tail1.getNext());
        tail1.setNext(head1);
        return c;
    }


    public static CLL splitH(CLL.SNode head){
        CLL c = new CLL<>();
        CLL.SNode tail1 = head;
        CLL.SNode tail2 = head;
        while(tail2.getNext() != head && tail2.getNext().getNext() != head){
            c.addLast(tail1.getElement());
            tail1 = tail1.getNext();
            tail2 = tail2.getNext().getNext();
        }
        c.addLast(tail1.getElement());

        if(tail2.getNext().getNext() == head)
            tail2 = tail2.getNext();
        tail2.setNext(tail1.getNext());
        tail1.setNext(head);
        return c;
    }

}
