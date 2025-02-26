package mytest;

import java.util.*;

public class Ex3 {
    public static void main(String[] args) {

        String str="I am a student, a good Student! should study Lessons, lessons: for a good student not I am.";

        //Find the words which are used one time and print to the console.
        str=str.toLowerCase().replaceAll("[^a-z ]", "");
        //str=str.toLowerCase().replaceAll("[,.!:]", "");
        System.out.println(str);

        String [] words=str.split(" ");

        Map<String,Integer> map=new LinkedHashMap<>();

        for(String s:words){
            if(!map.containsKey(s)){
                map.put(s,1);
            }else{
                map.put(s,map.get(s)+1);
            }
        }

        System.out.println(map);

        Set<String> set=map.keySet();

        for(String s:set){
            if(map.get(s)==1){
                System.out.println(s+ " - "+map.get(s));
            }
        }

    }
}
