package main;

import java.util.*;
import java.util.stream.*;
import java.io.*;

//最小値を与えるセグ木
class SegmentTree {
  public int n; //問題のサイズ
  public int nodeSize;  //最下層のノードサイズ
  public double[] aNode;
  public double[] bNode;
  public final int LIM = 1000000010;

  SegmentTree(int n){
    this.n = n;
    this.nodeSize = 1;
    while(this.nodeSize < n){
      this.nodeSize *= 2;
    }
    this.aNode = new double[2 * this.nodeSize - 1];
    this.bNode = new double[2 * this.nodeSize - 1];
    Arrays.fill(this.aNode, 1);
    Arrays.fill(this.bNode, 0);
  }

  void update(int x, double aVal, double bVal){
    x += this.nodeSize - 1;

    this.aNode[x] = aVal;
    this.bNode[x] = bVal;
    while(x > 0){
      x = (x - 1) / 2;
      aNode[x] = aNode[2*x+1] * aNode[2*x+2];
      bNode[x] = aNode[2*x+2] * bNode[2*x+1] + bNode[2*x+2];
    }
  }
}

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] in = br.readLine().split(" ");
    long n = Long.parseLong(in[0]);
    int m = Integer.parseInt(in[1]);

    long[] p = new long[m];
    double[] a = new double[m];
    double[] b = new double[m];
    
    for(int i = 0; i < m; i++){
      in = br.readLine().split(" ");
      p[i] = Long.parseLong(in[0]);
      a[i] = Double.parseDouble(in[1]);
      b[i] = Double.parseDouble(in[2]);
    }


    /*
    int[] a = new int[n];
    int[] b = new int[n];
    
    for(int i = 0; i < n; i++){
      in = br.readLine().split(" ");
      a[i] = Integer.parseInt(in[0]);
      b[i] = Integer.parseInt(in[1]);
    }
    
    int[] a = Stream.of(in).mapToInt(Integer::parseInt).toArray();
    */

    br.close();

    //座圧
    TreeSet<Long> pSet = new TreeSet<>();
    for(int i = 0; i < m; i++){
      pSet.add(p[i]);
    }
    int nSize = pSet.size();

    Map<Long, Integer> pMap = new HashMap<>();
    int cnt = 0;
    for(long t : pSet){
      pMap.put(t, cnt);
      cnt++;
    }
    
    //本編

    SegmentTree s = new SegmentTree(m);

    double mi = 1.0;
    double ma = 1.0;
    for(int i = 0; i < m; i++){
      s.update(pMap.get(p[i]), a[i], b[i]);
      mi = Math.min(mi, s.aNode[0] + s.bNode[0]);
      ma = Math.max(ma, s.aNode[0] + s.bNode[0]);
    }

    System.out.println(mi);
    System.out.println(ma);
  }
}