N = int(input())
mem = [0, ] * max(4, N + 1)
mem[2], mem[3] = 1, 1

for i in range(4, N + 1):
    mem[i] = mem[i - 1]
    if i % 2 == 0:
        mem[i] = min(mem[i], mem[i // 2])
    if i % 3 == 0:
        mem[i] = min(mem[i], mem[i // 3])
    mem[i] += 1

print(mem[N])
