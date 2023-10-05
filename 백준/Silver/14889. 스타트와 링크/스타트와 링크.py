# BJ14889

def solve(cnt, idx, min_val):
    if cnt == 0:
        team_a = []
        team_b = []
        for i in range(0, N):
            if Team[i]:
                team_a.append(i)
            else:
                team_b.append(i)

        # calculate the sum
        res = get_diff(team_a, team_b)

        # compare the min val
        if min_val > res:
            min_val = res

        return min_val

    # set team color
    for i in range(idx, N-cnt+1):
        Team[i] = True
        min_val = solve(cnt-1, i+1, min_val)
        Team[i] = False

    return min_val


def get_diff(team_a, team_b):
    sum_a = 0
    sum_b = 0

    for i in range(0, int(N/2)-1):  # choose 2 member
        for j in team_a[i + 1:]:
            sum_a += Synergy[team_a[i]][j] + Synergy[j][team_a[i]]
        for j in team_b[i + 1:]:
            sum_b += Synergy[team_b[i]][j] + Synergy[j][team_b[i]]

    return abs(sum_a-sum_b)


N = int(input())
Synergy = [list(map(int, input().split(' '))) for _ in range(N)]
Team = [False for _ in range(N)]

print(solve(int(N/2), 0, 50*N))
