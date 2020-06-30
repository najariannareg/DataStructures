
import java.util.Iterator;
import java.util.ArrayList;

public class Test {
    public static void rangeBubbleSort(LinkedPositionalList<Integer> lpl,
                                       Position<Integer> p1, Position<Integer> p2){
        boolean flag = true;
        while(flag){
            Position<Integer> start = p1;
            flag = false;
            while(start != null && lpl.after(start) != p2){
                if(start.getElement() > lpl.after(start).getElement()) {
                    Position<Integer> temp = lpl.addBefore(start, lpl.remove(lpl.after(start)));
                    flag = true;
                    if(start == p1)
                        p1 = temp;
                }
            }
            if(lpl.after(start) != p2)
                start = lpl.after(start);
            else break;
        }
    }


    public static void print(LinkedPositionalList<Integer> lpl){
        System.out.print("{");
        for(Position<Integer> p: lpl.positions()){
            System.out.print(p.getElement() + (p == lpl.last()? "}" : ", "));
        }
        System.out.println();
    }

//    public static void main(String[] args) {
//        LinkedPositionalList<Integer> lpl = new LinkedPositionalList<>();
//        Position<Integer> p0 = lpl.addFirst(55);
//        Position<Integer> p1 = lpl.addLast(8);
//        Position<Integer> p2 = lpl.addLast(2);
//        Position<Integer> p3 = lpl.addLast(9);
//        Position<Integer> p4 = lpl.addLast(14);
//        Position<Integer> p5 = lpl.addLast(1);
//        rangeBubbleSort(lpl, p0, p5);
//        print(lpl);
//    }

    public static <E> boolean dist(Iterator<E> it1, Iterator<E> it2, int d){
        while(it1.hasNext() && it2.hasNext()){
            it1.next();
            it2.next();
        }
        int count = 0;
        while(it1.hasNext()){
            count++;
            it1.next();
        }
        while(it2.hasNext()){
            count++;
            it2.next();
        }
        return count <= d;
    }

    public static <E> int closer(ArrayList<E> al, Iterator<E> it){
        int count = 0;
        while(it.hasNext()){
            it.next();
            count++;
        }
        if(count < al.size() - count)
            return 1;
        return 0;
    }

    public static boolean isPower2(int num){
//        while(number % 2 == 0)
//            number /= 2;
//        if(number == 1)
//            return true;
        return (num!=0 && ((num & (num-1)) == 0));
    }

    public static void main(String[] args) {

        System.out.println(isPower2(64));

//        ArrayList<Integer> al = new ArrayList<>();
//        al.add(0);
//        al.add(0);
//        al.add(0);
//        al.add(0);
//        al.add(0);
//        al.add(0);
//        al.add(0);
//        Iterator<Integer> it1 = al.iterator();
//        Iterator<Integer> it2 = al.iterator();
//        it1.next();
//        it1.next();
//        it1.next();
//        System.out.println(dist(it1, it2, 5));
//        System.out.println(closer(al, it1));
    }
}
