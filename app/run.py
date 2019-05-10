# -*-coding:utf-8 -*-


import os
import time
from LoadVideo import LoadVideo
from Wisdom import Wisdom
import getpass

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
    Wisdom.run_wisdom(sleepdict_run,student_attendancedict_run)
    clean()


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
    account = input()
    print("密码: ", end="")
    password_login = getpass.getpass()
    if account == "admin" and password_login == "123":
        return 1
    else:
        return 0


if __name__ == "__main__":

    flag = author()
    if flag == 1:
        print("登陆成功")
        print("是否继续？:(Y/N)",end = "")
        stillflag = input()
        if stillflag == "Y" or stillflag == "y":
            time.sleep(1)
            os.system("clear")
            print("Welcome!,Press Ctrl+C to quit")
            print("输入网络摄像头的用户名: ", end="")
            user = input()
            print("输入网络摄像头密码：", end="")
            password = getpass.getpass()
            print("请输入网络摄像头的ip地址: ", end="")
            ip = input()
            print("你输入的内容为："+user+":"+password+"@"+ip+",请检查!")
            print("是否继续?(Y/N)")
            passflag = input()
            if(passflag == "y" or passflag == "Y"):
                try:
                    while True:
                        localtime = time.strftime("%H-%M-%S")
                        newtime = localtime.split('-')
                        if(int(newtime[2])%10==0):
                            print()
                            get_image(user, ip, password)
                            print("图片获取完毕")
                            print("开始识别")
                            print()
                            run_program(sleepdict,student_attendancedict)
                            print("识别结束 开始清除缓存")
                            time.sleep(1)
                except KeyboardInterrupt:
                    pass
            else:
                print("程序退出，欢迎使用!")
                
    else:
        print("登陆失败")