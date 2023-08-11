N = int(input())
ans = []

for i in range(N // 5 + 1):
    NN = N - i * 5
    if NN % 3 == 0:
        ans.append(i + NN // 3)

print(min(ans) if ans else -1)
