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