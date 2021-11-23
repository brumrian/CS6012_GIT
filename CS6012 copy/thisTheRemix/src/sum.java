import java.util.GregorianCalendar;

public class sum {

    public static int sum(int N){
        if(N == 1){
            return 1;
        }
       int sum = N + sum(N-1);
        return sum;
    }

    public static int gaussCheck(int n){

        int check = n*(n+1);
        check = check / 2;


        return check;
    }

    public static void main(String[] args) {
        int result = sum(10);
        if (result == gaussCheck(10)) {
            System.out.println("success");
        }
        System.out.println(result);


        GregorianCalendar date = new GregorianCalendar(2008, 12, 15);
        GregorianCalendar date2 = new GregorianCalendar(2008, 12,12);

        System.out.println(date2.compareTo(date));
    }
}
