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