# Maks Value
INF = 9999999
# Membuat matriks ketetanggaan
G = [
    [0, 8700, 0, 0, 0, 0, 0, 0, 14000],
    [8700, 0, 8500, 28300, 0, 0, 0, 0, 0],
    [0, 8500, 0, 0, 9300, 0, 0, 4000, 0],
    [0, 28300, 0, 0, 6200, 0, 10200, 0, 0],
    [0, 0, 9300, 6200, 0, 1400, 0, 14800, 0],
    [0, 0, 0, 0, 1400, 0, 13100, 0, 0],
    [0, 0, 0, 10200, 0, 13100, 0, 0, 0],
    [0, 0, 4000, 0, 14800, 0, 0, 0, 15400],
    [14000, 0, 0, 0, 0, 0, 0, 15400, 0]
]

# Jumlah Simpul
N = len(G)

print('======= Matriks Ketetanggaan ========')
for i in range(N):
    print("[", end='')
    print(', '.join(map(str, G[i])), end='')
    print("]")

selected_node = [0, 0, 0, 0, 0, 0, 0, 0, 0]
no_edge = 0

selected_node[0] = True
total_jarak = 0

# Cetak Simpul dan Jarak
print("\n======= Simpul : Jarak =========")
while (no_edge < N - 1):
    minimum = INF
    a = 0
    b = 0
    for m in range(N):
        if selected_node[m]:
            for n in range(N):
                if ((not selected_node[n]) and G[m][n]):
                    # not in selected and there is an edge
                    if minimum > G[m][n]:
                        minimum = G[m][n]
                        a = m
                        b = n
    print(str(a+1) + "-" + str(b+1) + ":" + str(G[a][b]))
    total_jarak += G[a][b]
    selected_node[b] = True
    no_edge += 1

print(f"Total Jarak : {total_jarak}")
