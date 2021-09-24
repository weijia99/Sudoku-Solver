import numpy as np

class solver():
    def __init__(self):
#         初始化读取矩阵
        self.matrix=np.loadtxt('q2.txt',dtype=int)
        self.x_start=[0,0,12,12,6]
        self.y_start=[0,12,0,12,6]
        self.flag = False

   # 设置多个判别矩阵，每一个
        self.row_judge = np.zeros((5,9,9),dtype=int)
#       这是判断行的（5个数独，在前面的9个位置里放入1-9的值
# 表示第一行已经有了这个值
        self.col_judge= np.zeros((5,9,9),dtype=int)
        self.nine_judge = np.zeros((5,3,3,9),dtype=int)
# 这是判断九宫格里这个值是否已经使用了，是1就是已经使用率,表示九宫格里已近有了
#     把一个大数都分成9个小的了


#     定义读取的5个9*9数组
    def get_sudokou(self):
        self.sudokou = np.zeros((5, 9, 9))
#         三维矩阵，表示有5个
        #         传入要走的
        #         设置初始化，已经有了的值，同时设置要走的点
        self.go ={}
        for i in range(5):
            self.go[i]=[]
        for i in range(5):
            for j in range(9):
                for k in range(9):
                    # 与之前的matrix进行映射
                    x = self.x_start[i]+j
                    y = self.y_start[i]+k
                    self.sudokou[i][j][k]=self.matrix[x][y]
                    if self.sudokou[i][j][k]==0:
                        # 第5个数组的边角都是用其他的数独来计算，直接跳过
                        if self.is_edge(j,k) and i==4:
                            continue
    #                     如果是0说明是要填的数字
                        self.go[i].append((j,k))
                    else:
    #                     s是值，那就把数字填入之前的
                        a = self.sudokou[i][j][k]

    #                     设置边界是已经使用了的
                        i = int(i)

                        a = int(a-1)
                        j = int(j)
                        k = int(k)
                        self.row_judge[i][j][a]=1
                        self.col_judge[i][k][a]=1
                        self.nine_judge[i][j//3][k//3][a]=1






    def is_edge(self,j,k):
        '''

        :param j:
        :param k:
        :return: 返回是否是左右上角和左右下角
        '''
        if j>=6 and k>=6 :
            return True
        elif j >= 6 and k <= 2:
            return True
        elif j <= 2 and k >= 6:
            return True
        elif j <= 2 and k <= 2:
            return True
        else:
            return False


    def is_coincidence(self,round,x,y):
        '''

        :param round:
        :param x:
        :param y:
        :return: 是否前4个与第五个重合的
        '''
        if round==0 and x >= 6 and y >= 6:
            return True
        elif round==1 and x >= 6 and y <= 2:
            return True
        elif round==2 and x <= 2 and y >= 6:
            return True
        elif round==3 and x <= 2 and y <= 2:
            return True
        else:
            return False


    # 递归寻找答案
    def dfs(self,round,total):
        '''

        :param round: 这是第几个数独
        :param total: 总共走的步数
        :return:
        '''
        # 递归基结束
        if round>=4 and total >=len(self.go[round]):
            self.flag = True
            return

#         解决普通的前4个,进入下一个
        if round<=3 and total >=len(self.go[round]):
            round +=1
            total = 0


        x,y = self.go[round][total]

        for i in range(9):
            # 这个数字没有被使用
            if self.is_ok(round,x,y,i):
                if self.is_coincidence(round,x,y):
                    flag =False
                    # 重复区域去看第5个数独是否满足,不满足不妨数字
                    if round==0:
                        if self.is_ok(4,x-6,y-6,i):
                            flag =True
                    if round==1:
                        if self.is_ok(4,x-6,y+6,i):
                            flag = True
                    if round==2:
                        if self.is_ok(4,x+6,y-6,i):
                            flag =True
                    if round==3:
                        if self.is_ok(4,x+6,y+6,i):
                            flag = True
                    # 填入数据
                    if flag:
                        self.update(round,x,y,i,1)
    #                     更新第五个数独的信息
                        if round==0:
                            self.update(4,x-6,y-6,i,1)
                        elif round==1:
                            self.update(4,x-6,y+6,i,1)
                        elif round==2:
                            self.update(4,x+6,y-6,i,1)
                        elif round==3:
                            self.update(4,x+6,y+6,i,1)
                        self.sudokou[round][x][y]=i+1
    #                     更新数字
                        self.dfs(round,total+1)
    #                     失败就恢复

                        self.update(round, x, y, i, 0)
                        if round==0:
                            self.update(4,x-6,y-6,i,0)
                        elif round==1:
                            self.update(4,x-6,y+6,i,1)
                        elif round==2:
                            self.update(4,x+6,y-6,i,1)
                        elif round==3:
                            self.update(4,x+6,y+6,i,1)
                else:
    #                不是重复区域，直接回溯
                    self.update(round,x,y,i,1)
                    self.sudokou[round][x][y]=i+1
                    self.dfs(round,total+1)
                    self.update(round, x, y, i, 0)

            if self.flag:
                return

    def is_ok(self,round,x,y,i):
        '''

        :param round:
        :param x:
        :param y:
        :param i:
        :return: 是否满足行和列
        '''
        return self.row_judge[round][x][i]==0 \
               and self.col_judge[round][y][i]==0 \
            and self.nine_judge[round][x//3][y//3][i]==0


    def update(self,round,x,y,i,val):
        self.row_judge[round][x][i] = val
        self.col_judge[round][y][i] = val
        self.nine_judge[round][x // 3][y // 3][i] = val

    def save(self):
        for i in  range(5):
            print(self.sudokou[i])


    def run(self):
        self.get_sudokou()
        self.dfs(0,0)
        self.save()



if __name__ == '__main__':
    solver = solver()
    solver.run()
