# -*-coding:utf-8 -*-
from aip import AipFace
import base64
import os
import time
from FaceDataBase import FaceDataBase

# 测试用列表 是要更改的 目前希望是每个教室应该在的学生对应一个表 
 # 教室目前所在的学生
student_actual_list = []
# 这样可以获得这个教室里面所有学生的学号信息 
student_name_list = []
# 出勤学生学号信息的列表
student_id_list = []
# 出勤学生信息防止重复
student_id_list_actual = []

class FaceSearch:

    def __init__(self,facefolder,imageType,groupIdList,client_baidu_face):

        self.facefolder = facefolder   # 获取图片的文件夹
        self.client_baidu_face = client_baidu_face 
        self.imageType = imageType
        self.groupIdList = groupIdList 


    # 人脸搜索
    def search(self):

        for filename in os.listdir(self.facefolder):  # 遍历图片文件夹中所有的人像
            if filename.endswith(".jpg"):
                face_path = self.facefolder + filename;   
                image = base64.b64encode(open(face_path, 'rb').read())  
                try:
                    result = self.client_baidu_face.search(image, self.imageType, self.groupIdList);  # 调用人脸搜索在人脸库中搜索人脸返回json值
                    face_id = result['result']['user_list'][0]['user_id']   # 对JSON进行分析获取图片ID(学号)
                    time.sleep(0.3) # 同时调用次数太多导致api 故加300毫秒延迟  

                    faceDataBase = self.initdatabase()
                    student_id = str(face_id)
                    student_id_list.append(student_id)
                    # 判断学生出勤情况
                    #self.get_headup_rate_each_student(student_id, faceDataBase)
                    #print(str(student_id)+"的出勤情况已登记")
                    print('当前出勤学生为:'+str(student_id))

                    student_name = faceDataBase.get_student_name(student_id) # 返回学生姓名
                    if student_name is not None:
                        student_name_list.append(student_name)
                        print("识别成功，已存储")

                    # 防止重复
                    for student_id in student_id_list:
                        if student_id not in student_id_list_actual:
                            student_id_list_actual.append(student_id)

                except Exception:
                    os.chdir("../faces")
                    os.system("rm -rf *.jpg")
                    pass
        
        student_headown_list = self.judge_id_In_Not(student_id_list)
        return student_name_list, student_headown_list, student_id_list_actual


    # 初始化数据库对象 服务器数据库
    def initdatabase(self):
        # 数据库信息
        print("调用服务器数据库")
        host = "101.132.78.78"
        user = "root"
        database = "team_model"
        password = "nanshen166013"
        
        
        # 调用数据库
        faceDataBase = FaceDataBase(host, user, database, password) # 初始化数据
        return faceDataBase


    # 初始化数据库对象_Debug 本地数据库
    def initdatabase_debug(self):
        print("调用本地debug数据库")
        # 数据库信息
        host = "127.0.0.1"
        user = "root"
        database = "Wisdom_Class"
        password = "123456"
        
        # 调用数据库
        faceDataBase_debug = FaceDataBase(host, user, database, password) # 初始化数据
        return faceDataBase_debug


    # 每个学生的抬头率
    def get_headup_rate_each_student(self, student_id, faceDataBase):

        #faceDataBase = self.initdatabase()
        student_id_list = faceDataBase.get_student_id_all()
        if student_id in student_id_list:
            faceDataBase.attendence_insert(student_id)



    # 判断当前学号是否在该班级学号列表中
    def judge_id_In_Not(self, student_id_list):
        # 低头学生学号信息的列表
        student_headown_list = []
        # 目前教室里真实存在的学生
        student_actual_list_now = []
        # 初始化数据库对象_服务器
        faceDataBase = self.initdatabase()
        # 初始化数据库对象_debug
        faceDataBase_debug = self.initdatabase_debug() 
        # 利用当前教室学生的学号和检测到的学生的学号求补集
        student_actual_list = faceDataBase_debug.get_student_id_all_debug() # 当前使用的是本地测试数据库
        # 输出当前教室应该有的学生
        #print(student_actual_list)
        for actual_student in student_actual_list:
            if actual_student is not None:
                student_actual_list_now.append(actual_student)
        # 输出当前教室实际存在的学生
        #print(student_actual_list_now)
        student_left = list(set(student_actual_list_now)-set(student_id_list)) 
        # 如果补集存在（没有抬头的学生存在)
        if len(student_left) or len(student_left) <= len(student_actual_list)/2:             
            for student_left_name in student_left:
                # 返回当前低头的学生的id
                student_headown_name = faceDataBase.get_student_name(str(student_left_name))
                # 将低头学生的id保存到低头学生的列表中
                student_headown_list.append(student_headown_name)

                student_headown_list=self.if_all_write(student_actual_list, student_headown_list)
            # print("当前低头的学生为:"+str(student_headown_list))
            return student_headown_list
            

    # 判断是不是大家都在写作业做题
    def if_all_write(self, student_actual_list, student_headown_list):
        # 当前教室人数
        judge_student_num = len(student_actual_list)
        # 判断当前低头人数是否多于全班人数的一半
        if(len(student_headown_list)>=judge_student_num/2):
            student_headown_list = []
            return student_headown_list
        else:
            return student_headown_list


        

        

           
        
 