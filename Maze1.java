/*Problem: http://simplecodecjava.blogspot.com/2015/08/bai-toan-tim-uong-i-trong-ma-cung.html*/
import java.io.FileInputStream;
import java.util.Scanner;

class Maze1 {

    static int Answer;
    static char A[][];

    public static void main(String args[]) throws Exception {
        // Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("res/maze1.txt"));

        int T = 2;
        int SIZE = 16;
        int start = 0;
        int des = 0;
        A = new char[16][16];
        
        for (int t = 1; t <= T; t++) {
            Graph g = new Graph(SIZE * SIZE);

            int no = sc.nextInt();
            for (int y = 0; y < SIZE; y++) {
                String line = sc.next();
                for (int x = 0; x < SIZE; x++) {
                    A[y][x] = line.charAt(x);
                    if (A[y][x] == '2')
                        start = cor(y,x);
                    else if (A[y][x] == '3')
                        des = cor(y,x);
                }
            }

            for (int y = 0; y < SIZE; y++) {
                for (int x = 0; x < SIZE; x++) {
                    if (A[y][x] == 0) {
                        if (y >= 1 && A[y - 1][x] == 0)
                            g.addEdge(cor(y, x), cor(y - 1, x));
                        if (x >= 1 && A[y][x - 1] == 0)
                            g.addEdge(cor(y, x), cor(y, x - 1));
                    }
                }
            }

            Answer = g.BFS(start, des);

            // Print the answer to standard output(screen).
            System.out.println("Case #" + (t) + " " + Answer);
            // System.out.println(Answer);
        }
        sc.close();
    }

    private static int cor(int y, int x) {
        return y * 16 + x;
    }
}

class Graph {
    int V;
    Queue adj[];
    boolean visited[];

    public Graph(int size) {
        V = size;
        adj = new Queue[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Queue();
        }
    }

    public void addEdge(int a, int b) {
        adj[a].add(b);
        adj[b].add(a);
    }

    public int BFS(int start, int des) {
        visited = new boolean[V];
        return visit(start, des);
    }

    public int visit(int start, int des) {
        Queue q = new Queue();
        q.enQueue(start);
        visited[start] = true;
        while (q.isEmpty() == false) {
            int v = q.deQueue();
            if (v == des)
                return 1;
            for (int i = adj[v].rear; i < adj[v].front; i++) {
                int n = adj[v].data[i];
                if (visited[n] == false) {
                    q.enQueue(n);
                    visited[n] = true;
                }
            }
        }
        return 0;
    }
}

class Queue {
    int data[];
    int front, rear;
    int MAX = 1000;

    Queue() {
        data = new int[MAX];
        front = 0;
        rear = 0;
    }

    public void enQueue(int x) {
        data[front++] = x;
        if (front == MAX)
            front = 0;
        if (front == rear)
            System.out.println("tran");
    }

    public void add(int x) {
        data[front++] = x;
        if (front == MAX)
            front = 0;
        if (front == rear)
            System.out.println("tran");
    }

    public int deQueue() {
        int value = data[rear++];
        if (rear == MAX)
            rear = 0;
        return value;
    }

    public boolean isEmpty() {
        return (front == rear);
    }
}
