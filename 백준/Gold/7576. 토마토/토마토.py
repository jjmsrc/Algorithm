import sys
import collections
input = lambda : sys.stdin.readline().rstrip()

EMPTY = -1
GREEN = 0
RED = 1

M, N = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(N)]


def in_grid(pos, length):
    return 0 <= pos[0] < length[0] and 0 <= pos[1] < length[1]


def check_around(x, y):
    global grid
    for xx, yy in zip([1, -1, 0, 0], [0, 0, 1, -1]):
        xxx, yyy = x + xx, y + yy
        if in_grid((xxx, yyy), (N, M)) and grid[xxx][yyy] == GREEN:
            yield (xxx, yyy)
    

def get_tomatoes(val):
    global grid
    t = []
    for i in range(len(grid)):
        for j in range(len(grid[0])):
            if grid[i][j] == val:
                t.append((i, j))
    return t


cnt_days = 0
t = collections.deque(get_tomatoes(RED))
while t:
    grid_changed = False
    t_len = len(t)
    for _ in range(t_len):
        x, y = t.popleft()
        for xx, yy in check_around(x, y):
            grid[xx][yy] = RED
            if not grid_changed:
                grid_changed = True
            t.append((xx, yy))
        grid[x][y] = EMPTY

    if grid_changed:
        cnt_days += 1
    else:
        break
    

if get_tomatoes(GREEN):
    print(-1)
else:
    print(cnt_days)
    