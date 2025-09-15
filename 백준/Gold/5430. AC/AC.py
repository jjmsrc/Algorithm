import sys

inp = sys.stdin.readline


def solve():
    cmd = inp().rstrip()
    n = int(inp())
    arr = inp()[1:-2].split(',')

    if len(arr[0]) == 0:
        arr = []

    is_reversed = False
    start = 0
    end = 0

    for c in cmd:
        if c == 'R':
            is_reversed = not is_reversed
        if c == 'D':
            if is_reversed:
                end += 1
            else:
                start += 1

    if start + end > len(arr):
        print('error')
        return

    arr = arr[start:len(arr) - end]
    if is_reversed:
        arr = arr[::-1]

    print(f"[{','.join(arr)}]")


if __name__ == '__main__':
    T = int(inp())
    for i in range(T):
        solve()