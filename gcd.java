package main;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {

  public static int gcd(int a, int b){
    if(b > a) return gcd(b, a);
    if(b == 0) return a;

    return gcd(b, a % b);
  }

  public static long gcd(long a, long b){
    if(b > a) return gcd(b, a);
    if(b == 0L) return a;

    return gcd(b, a % b);
  }

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] in = br.readLine().split(" ");
    int n = Integer.parseInt(in[0]);
    
    int[] a = new int[n];
    int[] b = new int[n];
    
    for(int i = 0; i < n; i++){
      in = br.readLine().split(" ");
      a[i] = Integer.parseInt(in[0]);
      b[i] = Integer.parseInt(in[1]);
    }
    
    //int[] a = Stream.of(in).mapToInt(Integer::parseInt).toArray();

    br.close();

    for(int i = 0; i < n; i++){
      System.out.println(gcd(a[i], b[i]));
    }

    //System.out.println(s % t);
  }
}