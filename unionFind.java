package main;

import java.util.*;
import java.util.stream.*;
import java.io.*;

class UnionFind {
  private int[] parent;
  private int[] rank;

  public int find(int i){
    int p = parent[i];
    if(i == p) return i;

    return parent[i] = find(p);
  }

  public void union(int i, int j){
    int root1 = find(i);
    int root2 = find(j);
    if(root1 == root2) return;

    if(rank[root1] > rank[root2]) parent[root2] = root1;
    else if(rank[root1] < rank[root2]) parent[root1] = root2;
    else parent[root2] = root1;
    rank[root1]++;
  }

  public UnionFind(int n){
    parent = new int[n];
    rank = new int[n];

    for(int i = 0; i < n; i++) parent[i] = i;
  }
}

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] in1 = br.readLine().split(" ");
    int N = Integer.parseInt(in1[0]);
    int M = Integer.parseInt(in1[1]);
    
    in1 = br.readLine().split(" ");
    int[] p = Stream.of(in1).mapToInt(Integer::parseInt).toArray();
    
    for(int i = 0; i < N; i++){
      p[i]--;
    }

    int[][] xy = new int[M][2];

    for(int i = 0; i < M; i++){
      in1 = br.readLine().split(" ");
      xy[i] = Stream.of(in1).mapToInt(Integer::parseInt).toArray();
      xy[i][0]--; xy[i][1]--;
    }

    br.close();

    UnionFind uf = new UnionFind(N);

    for(int i = 0; i < M; i++){
      uf.union(p[xy[i][0]], p[xy[i][1]]);
    }

    List<List<Integer>> childList = new ArrayList<>();
    for(int i = 0; i < N; i++){
      childList.add(new ArrayList<Integer>());
    }

    for(int i = 0; i < N; i++){
      childList.get(uf.find(i)).add(i);
    }

    int[] idxArray = new int[N];
    for(int i = 0; i < N; i++){
      idxArray[p[i]] = i;
    }

    Set<Integer> idxSet = new HashSet<>();
    int ans = 0;

    for(int i = 0; i < N; i++){

      if(childList.get(i).isEmpty()) continue;
      
      idxSet.clear();
      for(int child : childList.get(i)){
        idxSet.add(idxArray[child]);
      }

      for(int child : childList.get(i)){
        if(idxSet.contains(child)){
          ans++;
        }
      }
    }

    System.out.println(ans);
  }
}