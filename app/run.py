# -*-coding:utf-8 -*-

import os
import time
from LoadVideo import LoadVideo
from Wisdom import Wisdom
from GetJudge import GetJudge
import sys

# 初始化睡觉判断和出勤判断字典
sleepdict = dict()
student_attendancedict = dict()

# 获取图片
def get_image(user, ip, password):
    LoadVideo.load(user, ip, password)


# 识别程序
def run_program(sleepdict,student_attendancedict):
    sleepdict_run = sleepdict
    student_attendancedict_run = student_attendancedict
    sleepdict_end, student_attendancedict_end, student_id_list_actual = Wisdom.run_wisdom(sleepdict_run,student_attendancedict_run)
    clean()
    return sleepdict_end, student_attendancedict_end, student_id_list_actual


# 清除缓存
def clean():
    os.chdir("../image")
    os.system("rm -rf *.jpg")
    os.chdir("../output")
    os.system("rm -rf *.jpg")
    print("清除完毕")


def author():
    print()
    print("欢迎使用智慧教室学生状态检测系统(树莓派端),按Ctrl+C退出程序")
    print("项目地址：https://github.com/RoWe98/WisdomClassRaspberry")
    print("在使用之前请先登陆管理员账号")
    print()
    print("账号: ", end="")
    #account = input()
    account = "admin"
    print("密码: ", end="")
    #password_login = getpass.getpass()
    password_login = "123"
    if account == "admin" and password_login == "123":
        return 1
    else:
        return 0


if __name__ == "__main__":

    flag = author()
    if flag == 1:
        print("登陆成功")
        print("是否继续？:(Y/N)",end = "")
        #stillflag = input()
        stillflag = 'y'
        if stillflag == "Y" or stillflag == "y":
            time.sleep(1)
            os.system("clear")
            print("Welcome!,Press Ctrl+C to quit")
            # print("输入网络摄像头的用户名: ", end="")
            # user = input()
            user = "admin"
            # print("输入网络摄像头密码：", end="")
            # password = getpass.getpass()
            password = "UMABQD"
            # print("请输入网络摄像头的ip地址: ", end="")
            # ip = input()
            ip = "192.168.0.103"
            print("你输入的内容为："+user+":"+password+"@"+ip+",请检查!")
            #print("是否继续?(Y/N)")
            #passflag = input()
            passflag = "y"
            if passflag == "y" or passflag == "Y":
                try:
                    print("开始")
                    i = 0
                    while i!=4:
                        localtime = time.strftime("%H-%M-%S")
                        newtime = localtime.split('-')
                        sys.stdout.write("\r"+"Now time:"+str(newtime))
                        sys.stdout.flush()
                        time.sleep(0.1)
                        if int(newtime[2])%20==0:
                            print()
                            get_image(user, ip, password)
                            print("图片获取完毕")
                            print("开始识别")
                            print()
                            sleepdict_end, student_attendancedict_end, student_id_list_actual = run_program(sleepdict,student_attendancedict)
                            print("识别结束 开始清除缓存")
                            time.sleep(1)
                            i = i + 1
                
                    reco_time = i
                    student_score_dict,student_headuprate_dict,student_id_headuprate_score_dict = GetJudge.each_student_headupRate(student_attendancedict_end,reco_time)
                    print("每个学生的抬头得分情况为：")
                    print(str(student_score_dict))
                    print(str(student_headuprate_dict))
                    print(str(student_id_headuprate_score_dict))
                except KeyboardInterrupt:
                    pass
            else:
                print("程序退出，欢迎使用!")
                
    else:
        print("登陆失败")