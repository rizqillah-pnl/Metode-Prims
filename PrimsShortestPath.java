/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program7;

/**
 *
 * @author Mayor Kucing
 */
import java.util.Scanner;

class MST {

    // Jumlah Vertex dari Graph
    private static int V;

    // Fungsi untuk mencari nilai minimum dari Graph
    // nilai berasal dari setiap vertex
    int minKey(int key[], Boolean mstSet[]) {
        // Inisialisasi nilai Minimum
        // MAX_VALUE adalah nilai sebesar 2147483647
        int min = Integer.MAX_VALUE, min_index = -1;

        // Mencari Nilai Minimum
        for (int v = 0; v < V; v++) {
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }

        // Mengembalikan nilai minimum
        return min_index;
    }

    // Fungsi untuk mencetak seluruh perjalanan yang dapat ditempuh
    void showMST(int parent[], int graph[][]) {
        System.out.println("Simpul \tJarak");
        int total = 0;
        for (int i = 1; i < V; i++) {
            System.out.println((parent[i] + 1) + " - " + (i + 1) + "\t  " + graph[i][parent[i]]);
            total += graph[i][parent[i]];
        }
        System.out.println("Total Jarak = " + total);
    }

    // Fungsi untuk mengatur jumlah Vertex/Node
    void setV(int v) {
        V = v;
    }

    // Fungsi yang akan mencari lintasan terpendek tanpa menyebabkan sirkulasi
    // Menggunakan representasi dari matrix perbandingan
    void algoritmaPrimMST(int graph[][]) {
        // Array yang akan menyimpan seluruh data dari MST
        int parent[] = new int[V];

        // Array untuk mengambil nilai bobot minimum dari edge
        int key[] = new int[V];

        // Mewakili himpunan simpul yang termasuk dalam MST
        Boolean mstSet[] = new Boolean[V];

        // Inisialisasi seluruh key dengan nilai MAX
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Sertakan simpul pertama pertama di MST.
        key[0] = 0;
        // ambil node pertama
        parent[0] = -1;

        // MST memiliki simpul sebanyak nilai V
        for (int count = 0; count < V - 1; count++) {
            // Pilih simpul key minimum dari himpunan simpul
            int u = minKey(key, mstSet);

            // Tambahkan simpul terpilih ke MST Set
            mstSet[u] = true;

            // Perbarui nilai key dan indeks parent dari yang berdekatan
            // fokuskan ke simpul yang belum terpilih
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Cetak lintasan yang Ditempuh
        showMST(parent, graph);
    }

    public static void main(String[] args) {
        /* Ibaratkan bahwa Lintasan Seperti Berikut
             100         300
        (1)--------(2)--------(3)
         | \        |          |
         |  \       |          |
         |   \      |          |
         |    \250  | 80       | 750
         |      \   |          |
      700|        \ |   800    |
         |         (4)--------(5)
         |          |
         |          | 550
         |          |
        (6)--------(7)
             500
         */
        MST t = new MST();
        Scanner in = new Scanner(System.in);
        int graph[][];

        System.out.print("Masukkan Jumlah Node : ");
        int v = in.nextInt();
        t.setV(v);
        graph = new int[v][v];

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                graph[i][j] = -1;
            }
        }

        System.out.println("\n================ Masukkan Bobot Tiap Node ================");
        System.out.println("(Note: Nilai Berupa Matriks Perbandingan!)\n");
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                    continue;
                }
                if (graph[i][j] != -1) {
                    continue;
                }

                System.out.print("Node[" + (i + 1) + "][" + (j + 1) + "] = ");
                graph[i][j] = in.nextInt();
                graph[j][i] = graph[i][j];
            }
        }

        System.out.println("\n======== Perjalanan Dalam Bentuk Matriks Perbandingan ========");
        for (int i = 0; i < v; i++) {
            System.out.print("{ ");
            for (int j = 0; j < v; j++) {
                System.out.print(graph[i][j] + ", ");
            }
            System.out.println("}");
        }

        System.out.println("\n============= Jarak Yang Dapat Ditempuh =============");
        t.algoritmaPrimMST(graph);
    }
}
