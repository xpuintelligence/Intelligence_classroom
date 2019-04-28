# -*-coding:utf-8 -*-

from aip import AipFace
import base64
import os
import time
from FaceDataBase import FaceDataBase

student_name_list = []



class FaceSearch:

    def __init__(self,facefolder,imageType,groupIdList,client_baidu_face):

        self.facefolder = facefolder   # 获取图片的文件夹
        self.client_baidu_face = client_baidu_face 
        self.imageType = imageType
        self.groupIdList = groupIdList         

    # 人脸搜索
    def search(self):

        for filename in os.listdir(self.facefolder):  # 遍历图片文件夹中所有的人像
            face_path = self.facefolder + filename;   
            image = base64.b64encode(open(face_path, 'rb').read())  
            try:
                result = self.client_baidu_face.search(image, self.imageType, self.groupIdList);  # 调用人脸搜索在人脸库中搜索人脸返回json值
                face_id = result['result']['user_list'][0]['user_id']   # 对JSON进行分析获取图片ID(学号)
                # print(face_id)
                time.sleep(0.3) # 同时调用次数太多导致api 故加300毫秒延迟  

                faceDataBase = self.initdatabase()
                student_id = str(face_id)
                # 判断学生出勤情况
                #self.get_headup_rate_each_student(student_id)
                #print(str(student_id)+"的出勤情况已登记")

                student_name = faceDataBase.get_student_name(student_id) # 返回学生姓名
                if student_name is not None:
                    student_name_list.append(student_name)
                    print("识别成功，已存储")

            except Exception:
                pass
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
        

           
        
 
