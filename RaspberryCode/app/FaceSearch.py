# -*-coding:utf-8 -*-

from aip import AipFace
import base64
import os
import time

class FaceSearch:

    def __init__(self,facefolder,imageType,groupIdList,client):

        self.facefolder = facefolder   # 获取图片的文件夹
        self.client = client  
        self.imageType = imageType
        self.groupIdList = groupIdList         



    def search(self):

        for filename in os.listdir(self.facefolder):  # 遍历图片文件夹中所有的人像
            face_path = self.facefolder + filename;   
            image = base64.b64encode(open(face_path, 'rb').read())  
            try:
                result = self.client.search(image, self.imageType, self.groupIdList);  #调用人脸搜索在人脸库中搜索人脸返回json值
                face_id = result['result']['user_list'][0]['user_id']   #对JSON进行分析获取图片ID(学号)
                print(face_id)     
                time.sleep(1) # 同时调用次数太多导致api 故加1秒延迟
            except Exception:
              pass
 
