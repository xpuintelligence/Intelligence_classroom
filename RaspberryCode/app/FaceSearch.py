# -*-coding:utf-8 -*-

from aip import AipFace
import base64
import os

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
                result = client.search(image, self.imageType, self.groupIdList);  #调用人脸搜索在人脸库中搜索人脸返回json值
                face_id = result['result']['user_list'][0]['user_id']   #对JSON进行分析获取图片ID(学号)
                print(face_id)      
            except Exception:
                pass
 
if __name__ == "__main__":

    # 百度AI人脸库的api地址和密码 用于人脸库搜索识别
    APP_ID = '15764893'
    API_KEY = 'rHGwsXanKC4yWj4pXeepDZcc'
    SECRET_KEY = 'qMONYxM1xTwnQh2q0DsHB7YG5Ht9LOal'

    client = AipFace(APP_ID, API_KEY, SECRET_KEY) # 实例化脸部搜索对象

    imageType = "BASE64"

    groupIdList = "wisdom_class,1"

    facefolder = "../faces/"

    faceSearch = FaceSearch(facefolder, imageType, groupIdList, client)

    faceSearch.search()

   