from collections import deque

def n_queen(n):
    if n == 1:
        return 1
    if n in [0, 2, 3,]:
        return 0
    
    row = [True for _ in range(n)]
    col = [True for _ in range(n)]
    diag_45 = [True for _ in range(2 * n - 1)]
    diag_135 = [True for _ in range(2 * n - 1)]
    cnt_success = 0
    
    stack = deque()
    stack.append((0, 0))
    while stack:
        i, j = stack.pop()
        if i == n: # row max, success
            cnt_success += 1
            i, j = stack.pop()
            row[i], col[j], diag_45[i+j], diag_135[i + n - 1 - j] = (True,) * 4
            stack.append((i, j + 1))
            continue
        elif j == n: # col max, fail
            if i == 0:
                break
            i, j = stack.pop()
            row[i], col[j], diag_45[i+j], diag_135[i + n - 1 - j] = (True,) * 4
            stack.append((i, j + 1))
            continue
        elif row[i] and col[j] and diag_45[i+j] and diag_135[i + n - 1 - j]:
            row[i], col[j], diag_45[i+j], diag_135[i + n - 1 - j] = (False,) * 4
            stack.append((i, j))
            stack.append((i + 1, 0))
        else:
            stack.append((i, j + 1))
    return cnt_success

N = int(input())
print(n_queen(N))