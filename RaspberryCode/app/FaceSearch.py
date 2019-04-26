# -*-coding:utf-8 -*-

from aip import AipFace
import base64
import os
import time
from FaceDataBase import FaceDataBase
from test import get_student_name

face_id_list = []

class FaceSearch:

    def __init__(self,facefolder,imageType,groupIdList,client_baidu_face):

        self.facefolder = facefolder   # 获取图片的文件夹
        self.client_baidu_face = client_baidu_face 
        self.imageType = imageType
        self.groupIdList = groupIdList         



    def search(self):

        for filename in os.listdir(self.facefolder):  # 遍历图片文件夹中所有的人像
            face_path = self.facefolder + filename;   
            image = base64.b64encode(open(face_path, 'rb').read())  
            try:
                result = self.client_baidu_face.search(image, self.imageType, self.groupIdList);  #调用人脸搜索在人脸库中搜索人脸返回json值
                face_id = result['result']['user_list'][0]['user_id']   #对JSON进行分析获取图片ID(学号)
                # print(face_id)
                face_id_list.append(face_id)
                time.sleep(0.3) # 同时调用次数太多导致api 故加1秒延迟  

                # 数据库信息
                host = "127.0.0.1"
                user = "root"
                database = "Wisdom_Class"
                password = "123456"

                # 调用数据库
                faceDataBase = FaceDataBase(host, user, database, password) # 初始化数据库
                student_id = str(face_id)
                student_name = faceDataBase.get_student_name(student_id) # 返回学生姓名
                if student_name is not None:
                    print(student_name)
            except Exception:
              pass
        
 
