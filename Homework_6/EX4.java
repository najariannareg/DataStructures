//4. (15 points) Write a program that reads a sequence of integers and prints these integers
//in the sorted order of their sums of digits. If multiple integers have the same sum of
//digits, only the rst such number is printed. Your program should use a map. Test your
//program with a SortedTableMap, a BSTMap, and an AVLMap; compare their performance
//on the same sequence of 1000 integers, recording actual execution times.

public class EX4 {

    public static int sumOfDigits(int num){
        int sum = 0;
        while(num > 0){
            sum += num%10;
            num /= 10;
        }
        return sum;
    }

    public static void printMap(Map<Integer, Integer> map){
        boolean flag = true;
        for(Integer i: map.values()){
            System.out.print((flag? "{": ", ") + i);
            flag = false;
        }
        System.out.println("}");
    }

    public static void main(String[] args) {
        //create array
        int[] arr = new int[1000];
        //populate array
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random()*100);
        }

        long start, end;

        //create map1
        SortedTableMap<Integer, Integer> map1 = new SortedTableMap<>();
        //populate map1
        start = System.nanoTime();
        for(int i = arr.length-1; i >= 0; i--)
            map1.put(sumOfDigits(arr[i]), arr[i]);
        end = System.nanoTime();
        printMap(map1);
        System.out.println(end - start);


        TreeMap<Integer, Integer> map2 = new TreeMap<>();

        start = System.nanoTime();
        for(int i = arr.length-1; i >= 0; i--)
            map2.put(sumOfDigits(arr[i]), arr[i]);
        end = System.nanoTime();
        printMap(map2);
        System.out.println(end - start);


        AVLTreeMap<Integer, Integer> map3 = new AVLTreeMap<>();

        start = System.nanoTime();
        for(int i = arr.length-1; i >= 0; i--)
            map3.put(sumOfDigits(arr[i]), arr[i]);
        end = System.nanoTime();
        printMap(map3);
        System.out.println(end - start);
    }

}
