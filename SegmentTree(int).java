package main;

import java.util.*;
import java.util.stream.*;
import java.io.*;

//最小値を与えるセグ木
class SegmentTree {
  public int n; //問題のサイズ
  public int nodeSize;  //最下層のノードサイズ
  public int[] node;
  public final int LIM = 1000000010;

  SegmentTree(int n){
    this.n = n;
    this.nodeSize = 1;
    while(this.nodeSize < n){
      this.nodeSize *= 2;
    }
    this.node = new int[2 * this.nodeSize - 1];
    Arrays.fill(this.node, LIM);
  }

  SegmentTree(int[] ori){
    this.n = ori.length;
    this.nodeSize = 1;
    while(this.nodeSize < n){
      this.nodeSize *= 2;
    }

    this.node = new int[2 * this.nodeSize - 1];
    Arrays.fill(this.node, LIM);
    
    for(int i = 0; i < this.n; i++) this.node[i + this.nodeSize - 1] = ori[i];
    for(int i = this.nodeSize-2; i >= 0; i--) {
      this.node[i] = Math.min(this.node[2 * i + 1], this.node[2 * i + 2]);
    }
  }

  void update(int x, int val){
    x += this.nodeSize - 1;

    this.node[x] = val;
    while(x > 0){
      x = (x - 1) / 2;
      this.node[x] = Math.min(this.node[2*x+1], this.node[2*x+2]);
    }
  }

  int getVal(int a, int b, int k, int l, int r){
    if(r <= a || b <= l) return LIM;
    if(a <= l && r <= b) return this.node[k];

    int vl = getVal(a, b, 2 * k + 1, l, (l + r) / 2);
    int vr = getVal(a, b, 2 * k + 2, (l + r) / 2, r);
    return Math.min(vl, vr);
  }

  int getVal(int a, int b){
    return getVal(a, b, 0, 0, this.nodeSize);
  }
}

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] in = br.readLine().split(" ");
    int n = Integer.parseInt(in[0]);
    int q = Integer.parseInt(in[1]);

    in = br.readLine().split(" ");
    int[] origin = Stream.of(in).mapToInt(Integer::parseInt).toArray();

    int[] p = new int[q];
    int[] a = new int[q];
    int[] b = new int[q];
    
    for(int i = 0; i < q; i++){
      in = br.readLine().split(" ");
      p[i] = Integer.parseInt(in[0]);
      a[i] = Integer.parseInt(in[1]);
      b[i] = Integer.parseInt(in[2]);
    }

    br.close();

    SegmentTree s = new SegmentTree(origin);

    for(int i = 0; i < q; i++){
      if(p[i] == 1) s.update(a[i], b[i]);
      else {
        System.out.println(s.getVal(a[i], b[i]));
      }
    }
    //System.out.println(ans);
  }
}