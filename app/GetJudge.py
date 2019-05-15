# 用于判断当前学生的状态
from FaceDataBase import FaceDataBase

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

            #print(self.sleepdict)

            # 判断学生多次低头的情况是不是在睡觉
            for student_name in self.sleepdict.keys():
                if(self.sleepdict[student_name]) >=3:
                    print("请提醒"+student_name+"同学，该同学可能正在睡觉")
            
            return self.sleepdict
        

    # 判断学生出勤情况的查询
    def student_attendance_Judge(self, student_name_list):

        if(len(student_name_list)):
            for student_name in student_name_list:
                if student_name in self.student_attendancedict.keys():
                    self.student_attendancedict[student_name] = self.student_attendancedict[student_name]+1
                else:
                    self.student_attendancedict[student_name] = 1

            return self.student_attendancedict



    def initdatabase():
        # 数据库信息
        print("调用服务器数据库")
        host = "101.132.78.78"
        user = "root"
        database = "team_model"
        password = "nanshen166013"
        faceDataBase = FaceDataBase(host, user, database, password)
        return faceDataBase


    # 该学生本节课的抬头（认真听课）分数,该学生本节课的抬头率
    def each_student_headupRate(student_attendancedict_end,reco_time):

        # 调用数据库
        faceDataBase = GetJudge.initdatabase()
        # 学生分数的字典
        student_score_dict = dict()
        # 学生抬头率的字典
        student_headuprate_dict = dict()
        # 学生id+抬头成绩的字典
        student_id_headuprate_score_dict = dict()
        # 对每个学生的分数进行判断
        for student_name in student_attendancedict_end.keys():
            student_headuprate = student_attendancedict_end[student_name]/reco_time
            student_score = (student_attendancedict_end[student_name]/reco_time)*40
            student_id = faceDataBase.get_student_id(student_name)
            student_headuprate_dict[student_id] = student_headuprate
            student_score_dict[student_name] = student_score
            student_id_headuprate_score_dict[student_id] = student_score

        return student_score_dict,student_headuprate_dict,student_id_headuprate_score_dict


        






                

    