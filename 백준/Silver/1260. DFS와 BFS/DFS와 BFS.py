import sys
input = sys.stdin.readline

N, M, V = map(int, input().split())
V -= 1

edges = [[False, ] * N for _ in range(N)]
visit = [False, ] * N
stack, queue = [V, ], [V, ]

for v, e in [map(int, input().split()) for _ in range(M)]:
    edges[v - 1][e - 1] = True
    edges[e - 1][v - 1] = True

while stack:
    v = stack.pop()
    if visit[v] == False:
        print(v + 1, end=" ")
        visit[v] = True
        for i in range(N - 1, -1, -1):
            if edges[v][i] and not visit[i]:
                stack.append(i)
print()
visit = [False, ] * N
while queue:
    v = queue.pop(0)
    if visit[v] == False:
        print(v + 1, end=" ")
        visit[v] = True
        for i in range(N):
            if edges[v][i] and not visit[i]:
                queue.append(i)
