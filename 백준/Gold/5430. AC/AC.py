def solve():
    p = input()
    n = int(input())
    arr = input()[1:-1].split(',')
    if len(arr[0]) == 0:
        arr = []

    start = 0
    end = 0
    is_reversed = False
    for f in p:
        if f == 'D':
            if is_reversed:
                end += 1
            else:
                start += 1
        else:
            is_reversed = not is_reversed

    if start + end > n:
        print('error')
    else:
        if len(arr) == 0:
            print("[]")
        else:
            if end == 0:
                arr = arr[start:]
            else:
                arr = arr[start:-end]
            if is_reversed:
                arr = arr[::-1]
            print("[%s]" % ",".join(arr))


if __name__ == '__main__':
    T = int(input())
    for _ in range(T):
        solve()