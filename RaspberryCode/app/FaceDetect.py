
from aip import AipFace
from aip import AipBodyAnalysis
import base64
import os
import cv2
import requests
from json import JSONDecoder
from FaceSearch import FaceSearch

class FaceDetect:

    def __init__(self, face_http_url, face_key, face_secret, file_path, client_baidu_body):

        self.url = face_http_url
        self.key = face_key
        self.secret = face_secret
        self.file_path = file_path
        self.client_baidu_body = client_baidu_body

    def getface(self): # 获取人脸识别信息

        try:

            data = {"api_key": self.key, "api_secret": self.secret, "return_attributes": "gender,age,smiling,beauty"}

            files = {"image_file": open(self.file_path, "rb")}

            response = requests.post(self.url, data=data, files=files)

            req_con = response.content.decode('utf-8')

            req_dict = JSONDecoder().decode(req_con)

            faces = req_dict['faces']
            return faces

        except Exception:
            pass

    def detect(self,faces): # 识别

        face_num = len(faces) # 获取api返回的人脸数
        print("当前识别到人脸数为："+str(face_num))
        img = cv2.imread(self.file_path, cv2.IMREAD_COLOR)
        for i in range(0,face_num):
            # 获取人脸坐标
            face_rectangle = faces[i]['face_rectangle']
            # print(face_rectangle)

            left = face_rectangle['left']

            top = face_rectangle['top']

            width = face_rectangle['width']

            height = face_rectangle['height']
            # 准备截取人脸 获取需要截取的区域坐标
            cropImg = img[top - 20:top + height + 20, left - 20:left + width + 20]

            # 图片路径
            facebase = "../faces/" + str(i) + ".jpg"
            print("saving.....")
            cv2.imwrite(facebase, cropImg)
            #cv2.rectangle(img, (left, top), (left + width, top + height), (0, 255, 0), 2)

        #img2 = cv2.resize(img, (1280,720), interpolation=cv2.INTER_CUBIC);
        #cv2.imshow("detial",img2)
        #cv2.waitKey(0)

    def get_student_num(self):

        # 打开图片
        student_file = open(self.file_path, 'rb').read()
        # 调用百度Ai识别人数
        student_num = self.client_baidu_body.bodyAnalysis(student_file)['person_num']

        print("当前识别到的人体数为: "+str(student_num))

    

