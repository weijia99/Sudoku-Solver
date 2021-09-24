import xlwt
import xlrd
import numpy as np


def dfs(pos, order):
    global valid  # global指这个valid变量来自全局变量
    global boardList
    # 出口，当程序尝试溢出的第一位时，就说明前面的数独已经完成了

    if order == 4 and pos >= len(spaces[order]):
        valid = True
        return
    # if order == 4:
    #     valid = True
    #     return
    # 当一个9*9数独搞定时，遍历下一个order，即下一个9*9数独
    if order <= 3 and pos >= len(spaces[order]):
        order = order + 1
        pos = 0

    # 不断填数字，从1开始填起
    i, j = spaces[order][pos]
    # if i==8 and j==6 and order==0 and boardList[0][8][1]==4:
    #     print(1)


    for digit in range(9):
        #  如果这个位置可以填数字digit，先判断自己的order，再判断图像中间（第五个order）的order能不能符合数独规则
        if line[order][i][digit] == column[order][j][digit] == block[order][i // 3][j // 3][digit] == False:

            # 如果是重复区域
            if order == 0 and i >= 6 and j >= 6 or order == 1 and i >= 6 and j <= 2 or order == 2 and i <= 2 and j >= 6 or order == 3 and i <= 2 and j <= 2:
                permit = False
                if order == 0 and i >= 6 and j >= 6 and line[4][i-6][digit] == column[4][j-6][digit] == block[4][(i-6)// 3][(j-6)// 3][digit] == False:
                    permit = True
                if order == 1 and i >= 6 and j <= 2 and line[4][i-6][digit] == column[4][j+6][digit] == block[4][(i-6)// 3][(j+6)// 3][digit] == False:
                    permit = True
                if order == 2 and i <= 2 and j >= 6 and line[4][i+6][digit] == column[4][j-6][digit] == block[4][(i+6)// 3][(j-6)// 3][digit] == False:
                    permit = True
                if order == 3 and i <= 2 and j <= 2 and line[4][i+6][digit] == column[4][j+6][digit] == block[4][(i+6)// 3][(j+6)// 3][digit] == False:
                    permit = True
                if permit:
                    # 更新数据
                    line[order][i][digit] = column[order][j][digit] = block[order][i // 3][j // 3][digit] = True

                    if order == 0 and i >= 6 and j >= 6:
                        line[4][i-6][digit] = column[4][j-6][digit] = block[4][(i-6)// 3][(j-6)// 3][digit] = True
                    if order == 1 and i >= 6 and j <= 2:
                        line[4][i-6][digit] = column[4][j+6][digit] = block[4][(i-6)// 3][(j+6)// 3][digit] = True
                    if order == 2 and i <= 2 and j >= 6:
                        line[4][i+6][digit] = column[4][j-6][digit] = block[4][(i+6)// 3][(j-6)// 3][digit] = True
                    if order == 3 and i <= 2 and j <= 2:
                        line[4][i+6][digit] = column[4][j+6][digit] = block[4][(i+6)// 3][(j+6)// 3][digit] = True
                    # 记录
                    boardList[order][i][j] = int(digit + 1)
                    dfs(pos + 1, order)
                    #恢复
                    line[order][i][digit] = column[order][j][digit] = block[order][i // 3][j // 3][digit] = False
                    if order == 0 and i >= 6 and j >= 6:
                        line[4][i-6][digit] = column[4][j-6][digit] = block[4][(i-6)// 3][(j-6)// 3][digit] = False
                    if order == 1 and i >= 6 and j <= 2:
                        line[4][i-6][digit] = column[4][j+6][digit] = block[4][(i-6)// 3][(j+6)// 3][digit] = False
                    if order == 2 and i <= 2 and j >= 6:
                        line[4][i+6][digit] = column[4][j-6][digit] = block[4][(i+6)// 3][(j-6)// 3][digit] = False
                    if order == 3 and i <= 2 and j <= 2:
                        line[4][i+6][digit] = column[4][j+6][digit] = block[4][(i+6)// 3][(j+6)// 3][digit] = False

                # 重复区域不允许放置该数字时，不做任何操作，继续循环



            # 如果是普通区域
            else:
                line[order][i][digit] = column[order][j][digit] = block[order][i // 3][j // 3][digit] = True
                # 记录
                boardList[order][i][j] = int(digit + 1)
                dfs(pos + 1, order)
                #恢复
                line[order][i][digit] = column[order][j][digit] = block[order][i // 3][j // 3][digit] = False




        if valid:
            return
# dfs over

def read_data(pos_x,pos_y):
    readbook = xlrd.open_workbook("数独.xls")
    sheet = readbook.sheet_by_index(0)
    # x列y行
    a = [[0] * 9 for i in range(9)]
    for i in range(0,  9):
        for j in range(0,  9):
            text = sheet.cell(pos_x+i, pos_y+j).value
            if text == '' or text == ' ' or text == "":
                a[i][j] = 0
            else:
                a[i][j] = int(text)

    return a

def save_data(pos_x,pos_y,a):

    # 写入数据
    for i in range(0, 9):
        for j in range(0, 9):
            work_sheet.write(pos_x+i, pos_y+j, a[i][j])
    # 调整宽度
    for i in range(0,30):
        work_sheet.col(i).width = 80*8

    work_book.save("数独答案.xls")




work_book = xlwt.Workbook(encoding="utf-8", style_compression=0)
work_sheet = work_book.add_sheet("数独答案", cell_overwrite_ok=True)
# 五个9*9数独的左上角坐标
x_list=[0,0,12,12,6]
y_list=[0,12,0,12,6]

# 新建三维数组，且初始值为0
boardList = np.zeros((5, 9, 9))

# 从excel中读取数据，boardList[0]表示最左上角的9*9数独，是一个二维数组
for i in range(0, 5):
    pos_x = x_list[i]
    pos_y = y_list[i]
    boardList[i] = read_data(pos_x, pos_y)



# 打印五个9*9的数独
# for i in range(5):
#     print(boardList[i])
#     print("--------------------------------------")

# region start
line = [   [[False] * 9 for _ in range(9)]  for _a in range(5)  ]
column = [    [[False] * 9 for _ in range(9)]   for _a in range(5)  ]
block = [   [[[False] * 9 for _a in range(3)] for _b in range(3)] for _c in range(5) ]
valid = False
spaces = [list() * 5 for _ in range(5)]

# 初始化
for k in range(5):
    for i in range(9):
        for j in range(9):

            if boardList[k][i][j] == 0:
                jiaoluo = False
                if (i >= 6 and j >= 6) or (i >= 6 and j <= 2) or (i <= 2 and j >= 6) or (i <= 2 and j <= 2):
                    jiaoluo = True
                if k!=4:
                    spaces[k].append((i, j))
                else:  # k==4
                    if not jiaoluo:
                        spaces[k].append((i, j))
            else:
                digit = int(boardList[k][i][j]) - 1
                line[k][i][digit] = column[k][j][digit] = block[k][i // 3][j // 3][digit] = True

dfs(0, 0)


for i in range(0, 4):
    save_data(x_list[i], y_list[i], boardList[i])
