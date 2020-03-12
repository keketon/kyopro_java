package main;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {

  // sum = nの区間を数える
  
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] in = br.readLine().split(" ");
    int n = Integer.parseInt(in[0]);
    
    in = br.readLine().split(" ");
    int[] a = Stream.of(in).mapToInt(Integer::parseInt).toArray();

    br.close();

    int left = 0, right = 0, sum = 0, ans = 0;

    for(; left < n; left++){
      for(; sum < n && right < n; right++){
        sum += a[right];
      }
      if(sum == n) ans++;
      sum -= a[left];
    }

    System.out.println(ans);
  }
}