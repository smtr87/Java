package bfsdfs;
/*
0 1 1 1 0 0 0 
1 0 0 0 0 0 0 
1 0 0 1 0 0 1 
1 0 1 0 1 1 0 
0 0 0 1 0 0 0 
0 0 0 1 0 0 1 
0 0 1 0 0 1 0
*/
/*
0 0 1 0 0 1 
0 0 0 1 1 0
 1 0 0 1 1 0 
0 1 1 0 0 1
 0 1 1 0 0 1
 1 0 0 1 1 0
*/
import java.util.Scanner;
//import java.lang.String; 
//import java.util.Arrays;
public class BfsDfs {

 public static void main(String args[]) {
        int n;
        int k = 0;
        Scanner sc = new Scanner(System.in);
        for (; /* infinite loop to use the program multiple time without existing from the console */;) {
            System.out.print("\n Enter first Non-NEGATIVE Integer to continue:  ");
            k = sc.nextInt();
            if (k == 0) {
                BfsDfs.makegraph(); // imported through line import static bfsdfs.BfsDfs.makegraph;
            } else {
                System.out.println("\n AGAIN THANKS FOR TRYING THIS\n :) ");
                break;
            }
        }
}

 private static void makegraph() {
     int n;
     System.out.println("\n Enter the total number of nodes in the graph:\n ");
    Scanner sc = new Scanner(System.in);
    n=sc.nextInt();
    String [] nodename = new String[n];// names can be of 25 characters
    int nodedata[]= new int[n];
    int edge[][]=new int[n][n]; // this represents the adjacency matrix of the graph
    setnodeinfo(nodename,nodedata,n);
    graphtest(nodename,nodedata,edge,n);
     
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

public static void setnodeinfo( String[] nodename,int nodedata[],int n)
{
    int x, y;
    Scanner sc= new Scanner(System.in);
    Scanner sr= new Scanner(System.in);
    for(x=0; x<n; x++)
    {
        System.out.println("\n Enter node name for node: "+(x+1));
        nodename[x]=sc.nextLine();
        System.out.println ("\n Enter node data for node: "+nodename[x]);
        nodedata[x]=sr.nextInt();
    }
   //  for(x=0; x<n; x++)
   // System.out.println(nodename[x] + nodedata[x]);
   //  BfsDfs.main(null);
}

private static void graphtest(String [] nodename,int nodedata[], int edge[][], int n)
{
    int k;
        Scanner sc= new Scanner(System.in);
    for(;;)
    {
        System.out.println("\n Enter any Positive Number to continue with same NodeName and DATA:  ");
        k=sc.nextInt();
        if (k>0)
        {
            graphmatrix(edge,n);
            showgraphmatrix(edge,n);
            nodetest(nodename, edge, n);
           drawgraph(nodename,nodedata,edge,n);
           bfs(nodename,nodedata,edge,n);
        }
        else
        {
            //System.out.print("\n THANK YOU FOR TRYING THIS\n :) \n");
            break;
        }
    }
}

public static void graphmatrix(int edge[][],int n){
int x,y;
    Scanner sc= new Scanner(System.in);
    System.out.println("\nEnter graph matrix:\n");
    for(x=0; x<n; x++)
        for(y=0; y<n; y++)
            edge[x][y]=sc.nextInt();
}

public static void showgraphmatrix(int edge[][], int n) // to output the graph matrix
{
    System.out.println("\nGraph Matrix is : \n");
    int x,y;
    for(x=0; x<n; x++)
    {
        for(y=0; y<n; y++)
            System.out.print(edge[x][y]+" ");
        System.out.print("\n");
    }
}

private static void  nodetest(String [] nodename,int edge[][],int n) // for LOOP test 
{
    int x,y,k;
    for(x=0; x<n; x++)
    {
        k=0;// condition tracker
        for(y=0; y<n; y++)
        {
            if (edge[x][y]==1 && x!=y)
                k=1;
            if (edge[x][y]==1 && x==y)
                System.out.println ("\nNode"+nodename[x]+"has a LOOP.\n" );
        }
        if(k==0)
            System.out.println("\n Node"+nodename[x]+"is Disconnected.\n");
    }
    System.out.println("Loop test and disconnected node test have been completed ");
}

private static void drawgraph(String [] nodename, int nodedata[], int edge[][],int n) // this function will draw the graph in a linear way based on nodes
{
    int x,y,test=0;
    for(x=0; x<n; x++) // will check the directed graph
    {
        System.out.print(nodename[x]+""+"("+nodedata[x]+")");
        System.out.print(",adj=");
        test=0; // condition tracker
        for(y=0; y<n; y++)
        {

            if(edge[x][y]!= 0 && edge[y][x]!=0)
            {

                System.out.print(nodename[y]+" ");
                test=1;
            }
            else if (edge[x][y]!= 0 && edge[y][x]== 0)
            {

                System.out.print(nodename[y]+" ");
                test =1;
            }
        }
        if (test==0)
            System.out.print(nodename[x]);
        System.out.print("\n");
    }
}

private static void bfs(String[] nodename,int nodedata[],int edge[][],int n)
{
    String ch;
    String [] list= new String[n+1]; // o.unit
    int listindex=0;
    String found = null;
    int x,y,k,ext;
    int nextnode=1,blindtest;
    System.out.print("\nEnter the Start Node:\n ");
        Scanner sc= new Scanner(System.in);
        ch=sc.nextLine();
    System.out.print("\nYou entered "+ch+" as Start Node:\n");
    list[0]=ch;// taking start node into BFS list
    // printf("\nYou entered  %s  , as Start Node:\n", list[0]);
    int p= getlocation(ch, nodename,n); // getting index number of the start node
  //  System.out.print("The location of the node is in position "+p);
    
  System.out.print("\nEnter the data to search :\n ");
     Scanner sr= new Scanner(System.in);
        int item=sr.nextInt();
    
    for(x=p,k=0; (x<n||x>=0)&&k<n; k++)
    {
        blindtest=0;
        ext=listindex;
        for(y=0; y<n; y++)
        {
            if(edge[x][y]==1)
            {
                blindtest=1;
                if (presenttest(listindex, list,nodename[y])==1) // it will check if the node is already visited or not
                    list[++listindex]= nodename[y]; //taking node's name into BFS list
                  if (item == nodedata[y]){ found=nodename[y];}
            }
        }
        /*  if (blindtest==0)
              {x=nextnodefromdicntnode(list,nodename,n,ext);
            printf("discnt node test %d  ",x);}
          else */
        x=getlocation(list[nextnode++],nodename,n); // assigning the location of next node of BFS list into x
    }
    System.out.print("\nTHE DESIRED BREADTH FIRST TRAVERSAL ORDER IS:\n");
    for(x=0; x<n; x++)
    {   // System.out.print(" "+list[x]+" >>");
    drawadj(list[x],nodename,nodedata,edge,n);
    if (found==list[x]) break;
    }
    System.out.println("the searched data"+item+"Found in node:"+found);
    
    
   // System.out.print("\nTHE DESIRED DEPTH FIRST TRAVERSAL ORDER IS:\n");
    //dodfs(ch,nodename,edge,n);
}
private static void drawadj(String ch,String [] nodename, int nodedata[], int edge[][],int n) // this function will draw the graph in a linear way based on nodes
{
    int x=getlocation(ch, nodename,n),y,test=0;
     // will check the directed graph
    {
        System.out.print(nodename[x]+""+"("+nodedata[x]+")");
        System.out.print(",adj=");
        test=0; // condition tracker
        for(y=0; y<n; y++)
        {

            if(edge[x][y]!= 0 && edge[y][x]!=0)
            {

                System.out.print(nodename[y]+" ");
                test=1;
            }
            else if (edge[x][y]!= 0 && edge[y][x]== 0)
            {

                System.out.print(nodename[y]+" ");
                test =1;
            }
        }
        if (test==0)
            System.out.print(nodename[x]);
        System.out.print(".\n");
    }
}
private static int presenttest(int listindex, String [] list, String ch) //will return "1"  if searched item is not in the array
{
    int x,k=1;
    for(x=0; x<=listindex; x++)
        if(list[x].equals(ch)==true)//string compare 
            k=0;
    return k;
}

private static int getlocation(String ch, String [] nodename, int n)
{
    int location=0, x,chk=0;
    //printf(" location test for node:  %s\n",ch);
 
    for(x=0; x<n; x++)
    {
        //printf("   %s \n", nodename[x]);
        if (nodename[x].equals(ch)==true)
        {
            chk=1;
            location=x;
            break;
        }
    }
  // System.out.println("loctest "+chk+ch);
    if (chk==0 && ch!=null)
    {
       System.out.print("\nNo Node exist with the entered name, TRY AGAIN! \n");
        location= -1;
    }
//    if (chk==1)
            return location;  
}

}

