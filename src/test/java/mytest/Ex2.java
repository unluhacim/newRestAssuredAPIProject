package mytest;

public class Ex2 {
    public static void main(String[] args) {

        //write a java code to find second largest number

        int [] num={100, 2,-1,100,54,96,23,43,43, 0};

        int largest=0;
        int largest2=0;

        for(int i=0; i<num.length; i++){
            int n=num[i];
            if(n>largest){
                largest2=largest;
                largest=n;
            }else if(n>largest2 && n!=largest){
                largest2=n;
            }

        }
        System.out.println("Second largest: "+ largest2);

    }
}
