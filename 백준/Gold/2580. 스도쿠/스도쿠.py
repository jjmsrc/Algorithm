# get field data
field = []
for i in range(9):
    field.append(list(map(int, input().split(' '))))

# get coordinates have a value 0
problems = []
for row in range(9):
    for col in range(9):
        if field[row][col] == 0:
            problems.append((row, col))

empty_numbers = {'row': [{} for i in range(9)], 'col': [{} for i in range(9)],
                 'box': [[{} for i in range(3)] for i in range(3)]}
for x, y in problems:
    nums = list(range(1, 10))
    for i in range(9):
        for j in range(9):
            if field[x][i] == nums[j]:  # check row
                nums[j] = 0
        for j in range(9):
            if field[i][y] == nums[j]:  # check col
                nums[j] = 0

    x_box, y_box = int(x / 3) * 3, int(y / 3) * 3
    for i in range(3):
        for j in range(3):
            for k in range(9):
                if field[x_box + i][y_box + j] == nums[k]:  # check box
                    nums[k] = 0

    for num in nums:
        if num != 0:
            empty_numbers['row'][x][num] = True
            empty_numbers['col'][y][num] = True
            empty_numbers['box'][int(x / 3)][int(y / 3)][num] = True


def solve_sudoku(idx):
    # check index
    if idx == len(problems):
        return True

    # call solve_sudoku function with backtracking
    x, y = problems[idx]
    for num in range(1, 10):
        if num in empty_numbers['row'][x] \
                and num in empty_numbers['col'][y] \
                and num in empty_numbers['box'][int(x / 3)][int(y / 3)]:
            field[x][y] = num
            del empty_numbers['row'][x][num]
            del empty_numbers['col'][y][num]
            del empty_numbers['box'][int(x / 3)][int(y / 3)][num]
            if solve_sudoku(idx + 1):
                return True
            else:
                field[x][y] = 0
                empty_numbers['row'][x][num] = True
                empty_numbers['col'][y][num] = True
                empty_numbers['box'][int(x / 3)][int(y / 3)][num] = True

    # failed to solve a current problem
    return False


# print field data
if solve_sudoku(0):
    for i in range(9):
        for j in range(9):
            print(field[i][j], end = ' ')
        print()
