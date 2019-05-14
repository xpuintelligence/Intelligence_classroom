# 这个程序用来测试新写的方法和功能

import os
import time
from LoadVideo import LoadVideo
from Wisdom import Wisdom
import getpass
from GetJudge import GetJudge


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
student_score_dict = GetJudge.each_student_headupRate(student_attendancedict_end,reco_time)

print("每个学生的抬头得分情况为：")
print(str(student_score_dict))

os.chdir("../faces")
os.system("rm -rf *.jpg")