package mytest;

public class Ex1 {
    public static void main(String[] args) {
        //swap 2 Strings without using temporary variable
        String x="Hello";
        String y="World";
        x=x+y;  //"HelloWorld

        y=x.substring(0, (x.length()-y.length()) );  //Hello
        x=x.substring(y.length());  //World

        System.out.println(x+" - "+y);

    }
}
