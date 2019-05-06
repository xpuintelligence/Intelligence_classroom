# -*-coding:utf-8 -*-
# 十一点也不可爱的人
from aip import AipFace
import base64
import os
import time
from FaceDataBase import FaceDataBase

student_name_list = []

# 测试用列表 是要更改的 目前希望是每个教室应该在的学生对应一个表 
# 这样可以获得这个教室里面所有学生的学号信息 
student_acutal_list = ['41609050203','41609050201','41604090109']
# 出勤学生学号信息的列表
student_id_list = []

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
                    # print(face_id)
                    time.sleep(0.3) # 同时调用次数太多导致api 故加300毫秒延迟  

                    faceDataBase = self.initdatabase()
                    student_id = str(face_id)
                    student_id_list.append(student_id)
                    # 判断学生出勤情况
                    self.get_headup_rate_each_student(student_id)
                    print(str(student_id)+"的出勤情况已登记")

                    student_name = faceDataBase.get_student_name(student_id) # 返回学生姓名
                    if student_name is not None:
                        student_name_list.append(student_name)
                        print("识别成功，已存储")

                except Exception:
                    os.chdir("../faces")
                    os.system("rm -rf *.jpg")
                    pass
        
        self.judge_id_In_Not(student_id_list)
        return student_name_list


    # 初始化数据库
    def initdatabase(self):
        # 数据库信息
        host = "101.132.78.78"
        user = "root"
        database = "team_model"
        password = "nanshen166013"
        
        # 调用数据库
        faceDataBase = FaceDataBase(host, user, database, password) # 初始化数据
        return faceDataBase

    # 每个学生的抬头率
    def get_headup_rate_each_student(self, student_id):

        faceDataBase = self.initdatabase()
        student_id_list = faceDataBase.get_student_id_all()
        if student_id in student_id_list:
            faceDataBase.attendence_insert(student_id)


    # 判断当前学号是否在该班级学号列表中
    def judge_id_In_Not(self, student_id_list):
        faceDataBase = self.initdatabase() # 初始化数据库对象
        # 利用当前教室学生的学号和检测到的学生的学号求补集
        student_left = list(set(student_acutal_list)-set(student_id_list)) 
        # 低头学生学号信息的列表
        student_headown_list = []
        # 如果补集存在（没有抬头的学生存在）
        if len(student_left):             
            for student_left_name in student_left:
                try:
                    # 返回当前低头的学生的id
                    student_headown_name = faceDataBase.get_student_name(str(student_left_name))
                    # 将低头学生的id保存到低头学生的列表中
                    student_headown_list.append(student_headown_name)
                except Exception:
                    os.chdir("../faces")
                    os.system("rm -rf *.jpg")
                    pass
            print("当前低头的学生为:"+str(student_headown_list))
        

        

           
        
 