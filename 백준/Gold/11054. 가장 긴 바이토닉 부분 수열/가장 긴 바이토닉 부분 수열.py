import sys
input = sys.stdin.readline

N = int(input())
nums = [0, ] + list(map(int, input().split())) + [0, ]

mem = [0, ] * (N + 1)
mem_reverse = [0, ] * (N + 1)

for i in range(1, N + 1):
    for j in range(i):
        if nums[j] < nums[i] and mem[i] <= mem[j]:
            mem[i] = mem[j] + 1
        if nums[-j - 1] < nums[-i - 1] and mem_reverse[i] <= mem_reverse[j]:
            mem_reverse[i] = mem_reverse[j] + 1

print(max(mem[i] + mem_reverse[-i] for i in range(1, N + 1)) - 1)
