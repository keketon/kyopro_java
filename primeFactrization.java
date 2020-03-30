package main;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {

  static Map<Integer, Integer> primeFactorization(int x){
    //Map<Integer, Integer> ret = new HashMap<>();
    Map<Integer, Integer> ret = new TreeMap<>();
    
    for(int i = 2; x > 1; i++){
      int cnt = 0;
      while(x % i == 0){
        x /= i;
        cnt++;
      }
      if(cnt > 0){
        ret.put(i, cnt);
      }
    }

    return ret;
  }

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String[] in = br.readLine().split(" ");
    int n = Integer.parseInt(in[0]);
    int k = Integer.parseInt(in[1]);

    br.close();

    List<Integer> ans = new ArrayList<>(k+10);

    Map<Integer, Integer> fact = primeFactorization(n);

    for(Map.Entry<Integer, Integer> entry: fact.entrySet()){
      for(int i = 0; i < entry.getValue(); i++){
        if(ans.size() < k) ans.add(entry.getKey());
        else ans.set(k - 1, ans.get(k - 1) * entry.getKey());
      }
    }
    
    if(ans.size() < k) System.out.println(-1);
    else{
      for(int i = 0; i < k-1; i++){
        System.out.print(ans.get(i) + " ");
      }
      System.out.println(ans.get(k-1));
    }
  }
}