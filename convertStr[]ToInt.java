package main;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class convertStrToInt {
    
    public static void convertStr_ToInt() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in1 = br.readLine().split(" ");

        br.close();
        //convert String[] to int[]
        int[] A = Stream.of(in1).mapToInt(Integer::parseInt).toArray();
        //convert String[] to List<Integer>
        List<Integer> list_A =
            Arrays.stream(in1)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        
        //print List
        list_A.forEach(System.out::println);
        System.out.println(list_A.get(0));
    }
}