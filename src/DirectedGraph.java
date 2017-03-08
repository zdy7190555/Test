import java.util.HashMap;
import java.util.Map;
/**
 * Created by Charles on 2016/11/23.
 */
public class DirectedGraph
{
    private int[][] matrix;
    private int numNodes;
    private Map map=new HashMap();
    private int[] InDegree;
    public DirectedGraph(int numNodes)
    {
        this.numNodes=numNodes;
        matrix=new int[numNodes][numNodes];
        InDegree=new int[numNodes];
        //初始化矩阵
        for(int i=0;i<numNodes;i++)
        {
            for(int j=0;j<numNodes;j++)
            {
               matrix[i][j]=32767;
            }
        }
        for(int i=0;i<numNodes;i++)
        {
            matrix[i][i]=0;
        }
    }
    public void setMap(String name,int index)
    {
        map.put(index,name);
    }
    public void addEdge(Graph.MSTEdge edge)
    {
         matrix[edge.head][edge.tail]=edge.dist;
         InDegree[edge.tail]++;
    }
    public void TopoSort()
    {
        int[] InDegree=this.InDegree;
        int[] flag=new int[numNodes];
        int num=0;
        for (int i=0;i<numNodes;i++)
        {
            //找到当前入度为零点
            if (InDegree[i]==0 && flag[i]!=1)
            {
                flag[i] = 1;
                for (int j = 0; j < numNodes; j++)
                {
                    if (matrix[i][j] != 32767 && matrix[i][j]!=0)
                        InDegree[j]--;
                }
                num++;
            }
        }
        if (num!=numNodes)
        {
            System.out.println("图中回路为:");
            printLoop(flag);
        }
        else
            System.out.println("图中无回路");
    }
    public void printLoop(int[] flag)
    {
        int start;
        for (start=0;start<numNodes;start++)
        {
            if(flag[start]==1)
            {
                continue;
            }
            int k=start;
            System.out.print(map.get(start));
            flag[start]=1;
            do {
                for (int j = 0; j < numNodes; j++) {
                    if (matrix[k][j] != 32767 && matrix[k][j] != 0) {
                        System.out.print("-->" + map.get(j));
                        flag[j]=1;
                        k = j;
                        break;
                    }
                }
            } while (k!=start);
            System.out.print("\n");
        }
        if (start==numNodes)
        {
            return;
        }
    }
    public void print()
    {
        System.out.printf("              ");
        for(int i=0;i<numNodes;i++)
        {
            System.out.printf(map.get(i)+"   ");
        }
        System.out.print("\n");
        for(int i=0;i<numNodes;i++)
        {
            System.out.printf("%8s",map.get(i));
            for(int j=0;j<numNodes;j++)
            {
                System.out.printf("%8s",matrix[i][j]);
            }
            System.out.print("\n");
        }
    }
}
