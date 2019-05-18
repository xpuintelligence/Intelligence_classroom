# 这个程序用来测试新写的方法和功能

import os
from Wisdom import Wisdom
from GetJudge import GetJudge
from FaceDataBase import FaceDataBase

# 初始化数据库
def initdatabase():
    # 数据库信息
    print("调用服务器数据库")
    host = "101.132.78.78"
    user = "root"
    database = "team_model"
    password = "nanshen166013"
    faceDataBase = FaceDataBase(host, user, database, password)
    return faceDataBase

# 初始化睡觉判断和出勤判断字典
sleepdict = dict()
student_attendancedict = dict()

sleepdict_end, student_attendancedict_end, student_id_list_actual= Wisdom.run_wisdom(sleepdict,student_attendancedict)

print()
print("识别结束当前每个学生的出勤情况为：")
print(str(student_attendancedict_end))
print("本次识别中低头的学生情况为：")
print(str(sleepdict_end))
print("学号信息为:")
print(student_id_list_actual)

# 识别次数 目前设置为4次

reco_time = 4
student_score_dict,student_headuprate_dict,student_id_headuprate_score_dict = GetJudge.each_student_headupRate(student_attendancedict_end,reco_time)

print("每个学生的抬头得分情况为：")
print(str(student_score_dict))
print(str(student_headuprate_dict))
print(str(student_id_headuprate_score_dict))
#facedatabase = initdatabase()
#facedatabase.attendance_insert_attend_nosleep(student_id_list_actual,student_headuprate_dict,student_id_headuprate_score_dict)

os.chdir("../faces")
os.system("rm -rf *.jpg")