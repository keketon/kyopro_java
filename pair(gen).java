package main;

import java.util.*;
import java.util.stream.*;
import java.io.*;

class IntPair{
  int f;
  int s;

  Pair(int f, int s){
    this.f = f;
    this.s = s;
  }

  @Override
  public String toString(){
    return "f: " + this.f + ", s: " + this.s;
  }

  @Override
  public boolean equals(Object obj){
    if(this == obj)
      return true;
    if(obj == null)
      return false;
    if(getClass() != obj.getClass())
      return false;
    
    IntPair other = (IntPair) obj;
    
    return (this.f == other.f && this.s == other.s);
  }

  @Override
  public int hashCode(){
    int result = 17;
    result = result * 31 + this.f;
    result = result * 31 + this.s;
    return result;
  }
}

public class Main {

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

    Set< IntPair > coins = new HashSet<>();

    for(int i = 0; i < n; i++){
      IntPair c = new IntPair(Math.min(a[i], b[i]), Math.max(a[i], b[i]));
      coins.add(c);
    }

    System.out.println(coins.size());
  }
}