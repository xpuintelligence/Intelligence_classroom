
from aip import AipFace
from aip import AipBodyAnalysis
import base64
import os
import cv2
import requests
from json import JSONDecoder
from FaceSearch import FaceSearch

face_pixel = []

class FaceDetect:

    def __init__(self, face_http_url, face_key, face_secret, file_path, client_baidu_body, client_baidu_face):

        self.url = face_http_url
        self.key = face_key
        self.secret = face_secret
        self.file_path = file_path
        self.client_baidu_body = client_baidu_body
        self.client_baidu_face = client_baidu_face
        self.face_num_class = 0
        self.student_num_class = 0
        self.headup_student = 0

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

    def detect(self, faces): # 识别

        face_num = len(faces) # 获取api返回的人脸数
        self.face_num_class = face_num
        print("当前识别到人脸数为："+str(face_num))
        img = cv2.imread(self.file_path, cv2.IMREAD_COLOR)
        for i in range(0,face_num):
            # 获取人脸坐标
            face_rectangle = faces[i]['face_rectangle']
            left = face_rectangle['left']
            top = face_rectangle['top']
            width = face_rectangle['width']
            height = face_rectangle['height']
            face_pixel_info = str(left)+","+str(top)+","+str(width)+","+str(height)
            # print(face_pixel_info)
            face_pixel.append(face_pixel_info)

            # 准备截取人脸 获取需要截取的区域坐标
            cropImg = img[top - 20:top + height + 20, left - 20:left + width + 20]

            # 图片路径
            facebase = "../faces/" + str(i) + ".jpg"
            print("saving.....")
            cv2.imwrite(facebase, cropImg)
        
        return face_pixel

    # 判断人数
    def get_student_num(self):

        # 打开图片
        student_file = open(self.file_path, 'rb').read()
        # 调用百度Ai识别人数
        try:
            self.student_info_class = self.client_baidu_body.bodyAnalysis(student_file)
            student_num = self.student_info_class['person_num']
            
            self.student_num_class = student_num
            return student_num
        except Exception:
            os.chdir("../faces")
            os.system("rm -rf *.jpg")
            pass

    # 判断抬头率
    def get_headup_rate(self):
        
        # 初始化抬头率
        for i in range(0, self.student_num_class):
            student_nose_y = self.student_info_class['person_info'][0]['body_parts']['nose']['y']
            student_nose_x = self.student_info_class['person_info'][0]['body_parts']['nose']['x']
            if student_nose_x is not 0.0 and student_nose_y is not 0.0:
                self.headup_student = self.headup_student + 1
        try:
            # 修复了一个bug 抬头人数判断问题
            if self.headup_student > self.face_num_class:
                self.headup_student = self.face_num_class
            headup_rate = self.headup_student/self.student_num_class
            return headup_rate
        except Exception:
            pass

    # 判断出勤率
    def attendence(self):
        
        # 获得出勤率
        try:
            attendence = self.face_num_class/self.student_num_class
            return attendence
        except Exception:
            pass


        



    

