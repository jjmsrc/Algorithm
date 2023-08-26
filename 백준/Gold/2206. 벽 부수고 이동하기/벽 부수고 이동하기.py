import sys
from collections import deque
input = lambda : sys.stdin.readline().rstrip()


N, M = map(int, input().split())
field = [list(bool(True if x == '1' else False) for x in input()) for _ in range(N)]


def in_grid(x, y, r = N, c = M):
    return 0 <= x < c and 0 <= y < r


queue = deque()
queue.append((0, 0, True))
MAX_DIST = 1000 * 1000
NOT_BROKEN, BROKEN = 0, 1
dist = [[[MAX_DIST, MAX_DIST] for _ in range(M)] for _ in range(N)]
dist[0][0][NOT_BROKEN] = 1
dxdys = [(1, 0), (0, 1), (-1, 0), (0, -1)]
while queue:
    x, y, can_break = queue.popleft()
    if x == M - 1 and y == N - 1:
        break
    d = dist[y][x][NOT_BROKEN]
    d_broken = dist[y][x][BROKEN]

    for i, j in dxdys:
        xx = x + i
        yy = y + j
        if in_grid(xx, yy):
            if can_break:
                if not field[yy][xx] and dist[yy][xx][NOT_BROKEN] > d + 1:
                    dist[yy][xx][NOT_BROKEN] = d + 1
                    queue.append((xx, yy, True))
                if field[yy][xx] and dist[yy][xx][BROKEN] > d + 1:
                    dist[yy][xx][BROKEN] = d + 1
                    queue.append((xx, yy, False))
            else:
                if not field[yy][xx] and dist[yy][xx][BROKEN] > d_broken + 1:
                    dist[yy][xx][BROKEN] = d_broken + 1
                    queue.append((xx, yy, False))


print(min(dist[-1][-1]) if min(dist[-1][-1]) != MAX_DIST else -1)