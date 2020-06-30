//Given the head node check if it is a SLL or CLL.

public class EX5 <E>{
    public static void main(String[] args) {
        SLL s = new SLL<>();
        s.addLast(0);
        s.addLast(1);
        s.addLast(2);
        s.addLast(3);
        s.addLast(4);
        CLL c = new CLL<>();
        c.addLast(0);
        c.addLast(1);
        c.addLast(2);
        c.addLast(3);
        c.addLast(4);
        //s.print();
        //System.out.println((check(s.getHead())));
        c.print();
        System.out.println((check(c.getHead())));
    }
    public static String check(CLL.SNode head){
        String s = "SLL";
        CLL.SNode current = head.getNext();
        while(current != null){
            if(current == head){
                s = "CLL";
                break;
            }
            current = current.getNext();
        }
        return s;
    }
}
