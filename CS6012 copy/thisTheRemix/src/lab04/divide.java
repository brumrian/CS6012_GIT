package lab04;

public class divide {

    public static int divide(int A, int B){
        if(A == 0){
            return 0;
        }
        return(1 + divide(A-B, B));
    }

    public static void main(String[] args) {
        int result = divide(45,5);
        System.out.println(result);
    }
}
