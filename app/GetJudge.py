# 用于判断当前学生的状态

class GetJudge:

    def __init__(self,sleepdict,student_attendancedict):

        # 初始化睡觉判断和出勤判断字典
        #self.sleepdict = dict()
        #self.student_attendancedict = dict()
        # 学生睡觉的字典
        self.sleepdict = sleepdict
        # 学生识别情况的字典
        self.student_attendancedict = student_attendancedict

    # 判断学生是不是在睡觉或者不认真听讲
    def sleepJudge(self, student_headown_list):
        if(len(student_headown_list)):
            for student_name in student_headown_list:
                if student_name in self.sleepdict.keys():
                    self.sleepdict[student_name] = self.sleepdict[student_name]+1
                else:
                    self.sleepdict[student_name] = 1

            print(self.sleepdict)

            # 判断学生多次低头的情况是不是在睡觉
            for student_name in self.sleepdict.keys():
                if(self.sleepdict[student_name]) >=3:
                    print("请提醒"+student_name+"同学，该同学可能正在睡觉")
        

    # 判断学生出勤情况的查询
    def student_attendance_Judge(self, student_name_list):

        if(len(student_name_list)):
            for student_name in student_name_list:
                if student_name in self.student_attendancedict.keys():
                    self.student_attendancedict[student_name] = self.student_attendancedict[student_name]+1
                else:
                    self.student_attendancedict[student_name] = 1

            print(self.student_attendancedict)


                

    