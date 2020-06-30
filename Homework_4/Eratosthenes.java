//1. (20 points) The sieve of Eratosthenes is an algorithm for finding the prime numbers
//below some N, as described on Wikipedia. Write a method/function that implements the
//sieve of Eratosthenes using an ArrayList A of integers. Your method/function should
//not use any boolean arrays. Rather, it should store in A the current list of candidates for
//being prime. Thus, the algorithm should proceed by iteratively removing elements from
//A. What is the complexity of your method? Do you think this is a good implementation
//of the sieve of Eratosthenes? Brie
//y justify your answers.

//import java.util.ArrayList;
//import java.util.Iterator;

import static java.lang.StrictMath.sqrt;

public class Eratosthenes {
    public static void main(String[] args) {
        int N = 30;
//        ArrayList<Integer> A = eratosthenesW(N);
//        print(A);
//        ArrayList<Integer> B = eratosthenesM(N);
//        print(B);
        ArrayList<Integer> C = eratosthenes(N);
        print(C);
    }

    //as described in the exercise
    //I didn't figure out how to keep track of the indices
    public static ArrayList<Integer> eratosthenes(int n){
        ArrayList<Integer> A = new ArrayList<>(n-3);
        //populate A with the number 2 to n
        for(int i = 2; i < n; i++) A.add(i-2, i);
        //remove composite numbers
        for(int i = 0; i < A.size(); i++){
            int val = A.get(i);
            for(int j = val*val; j < n; j+=val){
                //A.remove()
            }
        }
        return A;
    }
    //the complexity is

    //as described in the exercise
    //by using modulo
    public static ArrayList<Integer> eratosthenesM(int n){
        ArrayList<Integer> A = new ArrayList<>(n-3);
        //populate A with the number 2 to n
        for(int i = 2; i < n; i++) A.add(i-2, i);
        //remove composite numbers
        for(int i = 0; i < A.size(); i++){
            int val = A.get(i);
            for(int j = val; j < A.size(); j++){
                if(j % val == 0)
                    A.remove(j);
            }
        }
        return A;
    }
    //the complexity is O(n^2)



    //implementation as described in wikipedia
    public static ArrayList<Integer> eratosthenesW(int n){
        ArrayList<Integer> A = new ArrayList<>(n-1);
        //populate A with the number 2 to n
        for(int i = 0; i < n; i++) A.add(i, i);
        //mark the composite elements
        for(int i = 2; i <= sqrt(A.size()); i++) {
            if(A.get(i) >= 2)
            for(int j = i*i; j < A.size(); j+=i){
                A.set(j, -1);
            }
        }
        //remove the marked elements
        int i = 0;
        while(i < A.size()) {
            if (A.get(i) < 2)
                A.remove(i);
            else i++;
        }
        return A;
    }
    //the complexity is O(n^2)


    //for testing
    public static void print(ArrayList<Integer> A){
        for(int i = 0; i < A.size(); i++)
            System.out.println(A.get(i));
    }

}
