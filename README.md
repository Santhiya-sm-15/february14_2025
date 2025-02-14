# february14_2025
The problems that I solved today

1.Design an algorithm that accepts a stream of integers and retrieves the product of the last k integers of the stream. Implement the ProductOfNumbers class: ProductOfNumbers() Initializes the object with an empty stream. void add(int num) Appends the integer num to the stream. int getProduct(int k) Returns the product of the last k numbers in the current list. You can assume that always the current list has at least k numbers. The test cases are generated so that, at any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.

Code:
class ProductOfNumbers {
    List<Integer> l;
    List<Integer> prefix;
    Set<Integer> zero;
    public ProductOfNumbers() {
        l=new ArrayList<>();
        zero=new HashSet<>();
        prefix=new ArrayList<>();
    }
    public void add(int num) {
        l.add(num);
        if(num==0)
            zero.add(l.size()-1);
        if(prefix.isEmpty())
            prefix.add(num);
        else
        {
            if(prefix.get(prefix.size()-1)==0)
                prefix.add(num);
            else
                prefix.add(prefix.get(prefix.size()-1)*num);
        }
    }
    public int getProduct(int k) {
        for(int a:zero)
        {
            if(a>=l.size()-k && a<l.size())
                return 0;
        }
        if(l.size()-k-1>=0 && prefix.get(l.size()-k-1)!=0)
            return prefix.get(prefix.size()-1)/prefix.get(l.size()-k-1);
        return prefix.get(prefix.size()-1);
    }
}

2.There is a dungeon with n x m rooms arranged as a grid. You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes exactly one second. Return the minimum time to reach the room (n - 1, m - 1). Two rooms are adjacent if they share a common wall, either horizontally or vertically.

Code:
class Solution {
    public int minTimeToReach(int[][] moveTime) {
        int n=moveTime.length,m=moveTime[0].length;
        int[][] dist=new int[n][m];
        for(int[] x:dist)
            Arrays.fill(x,Integer.MAX_VALUE);
        dist[0][0]=0;
        int i,j;
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0]));
        pq.add(new int[]{0,0,0});
        int[][] dir={{0,-1},{-1,0},{0,1},{1,0}};
        while(!pq.isEmpty())
        {
            int[] x=pq.poll();
            int dis=x[0];
            int r=x[1];
            int c=x[2];
            if(r==n-1 && c==m-1)
                return dis;
            for(int[] d:dir)
            {
                int nr=r+d[0];
                int nc=c+d[1];
                if(nr>=0 && nr<n && nc>=0 && nc<m)
                {
                    int val=Math.max(dis,moveTime[nr][nc])+1;
                    if(val<dist[nr][nc])
                    {
                        dist[nr][nc]=val;
                        pq.add(new int[]{val,nr,nc});
                    }
                }
            }
        }
        return -1;
    }
}

3.You have n gardens, labeled from 1 to n, and an array paths where paths[i] = [xi, yi] describes a bidirectional path between garden xi to garden yi. In each garden, you want to plant one of 4 types of flowers. All gardens have at most 3 paths coming into or leaving it. Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers. Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)th garden. The flower types are denoted 1, 2, 3, or 4. It is guaranteed an answer exists.

Code:
class Solution {
    ArrayList<Integer>[] adj;
    public void bfs(int src,int[] color)
    {
        Queue<Integer> q=new LinkedList<>();
        q.add(src);
        color[src-1]=1;
        while(!q.isEmpty())
        {
            int x=q.poll();
            int c=color[x-1];
            for(int a:adj[x])
            {
                if(color[a-1]==-1 || color[a-1]==c)
                {
                    color[a-1]=c==4?1:c+1;
                    q.add(a);
                }
            }
        }
    }
    public int[] gardenNoAdj(int n, int[][] paths) {
        adj=new ArrayList[n+1];
        int i;
        for(i=1;i<=n;i++)
            adj[i]=new ArrayList<>();
        for(int[] x:paths)
        {
            adj[x[0]].add(x[1]);
            adj[x[1]].add(x[0]);
        }
        int[] color=new int[n];
        Arrays.fill(color,-1);
        for(i=1;i<=n;i++)
        {
            if(color[i-1]==-1)
                bfs(i,color);
        }
        return color;
    }
}
