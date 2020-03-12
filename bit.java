package main;

import java.util.*;
import java.util.stream.*;
import java.io.*;

class Bit{
  //1-indexed
  List<Integer> bit;
  int size;

  Bit(int size){
    this.size = size;
    this.bit = new ArrayList<Integer>(this.size + 10);
    for(int i = 0; i <= this.size; i++){
      this.bit.add(0);
    }
  }

  void add(int idx, int x){
    for(int i = idx; i <= this.size; i += i & -i){
      bit.set(i, bit.get(i) + x);
    }
  }

  int sum(int idx){
    int ret = 0;
    for(int i = idx; i > 0; i -= i & -i){
      ret += bit.get(i);
    }
    return ret;
  }
}

public class Main {

  //順列の転倒数を求める
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] in = br.readLine().split(" ");
    int n = Integer.parseInt(in[0]);
    
    in = br.readLine().split(" ");
    int[] p = Stream.of(in).mapToInt(Integer::parseInt).toArray();

    br.close();

    Bit b = new Bit(n);

    long ans = 0;

    for(int i = 0; i < n; i++){
      ans += i - b.sum(p[i]);
      b.add(p[i], 1);
    }

    System.out.println(ans);
  }
}