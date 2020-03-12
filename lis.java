package main;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {

  //単調増加(not 単調非減少)の場合

  static final int INF = 100000001; //1e8 + 1

  static int binarySearch(int x, int maxIdx, int[] a){
    int left = 0; int right = maxIdx + 1;
    while(right - left > 1){
      int mid = (right + left) / 2;
      if(a[mid] < x) left = mid;
      else right = mid;
    }
    a[right] = x;
    return right;
  }

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] in = br.readLine().split(" ");
    int n = Integer.parseInt(in[0]);
    
    in = br.readLine().split(" ");
    int[] a = Stream.of(in).mapToInt(Integer::parseInt).toArray();

    br.close();

    int[] lis = new int[n+1];
    Arrays.fill(lis, INF);
    lis[0] = -INF;

    int ans = 0;
    for(int i = 0; i < n; i++){
      ans = Math.max(ans, binarySearch(a[i], ans, lis));
    }

    System.out.println(ans);
  }
}