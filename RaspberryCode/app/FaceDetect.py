
from aip import AipFace
import base64
import os
import cv2
import requests
from json import JSONDecoder

class FaceDetect:

    def __init__(self, face_http_url, face_key, face_secret, file_path):

        self.url = face_http_url
        self.key = face_key
        self.secret = face_secret
        self.file_path = file_path

    def getface(self): # 获取人脸识别信息

        try:

            data = {"api_key": self.key, "api_secret": self.secret, "return_attributes": "gender,age,smiling,beauty"}

            files = {"image_file": open(file_path, "rb")}

            response = requests.post(self.url, data=data, files=files)

            req_con = response.content.decode('utf-8')

            req_dict = JSONDecoder().decode(req_con)

            faces = req_dict['faces']

            # face_num = len(faces)
            return faces

        except Exception:
            pass

    def detect(self,faces): # 识别

        face_num = len(faces)
        print(face_num)
        img = cv2.imread(self.file_path, cv2.IMREAD_COLOR)
        for i in range(0,face_num):
            face_rectangle = faces[i]['face_rectangle']
            # print(face_rectangle)

            left = face_rectangle['left']

            top = face_rectangle['top']

            width = face_rectangle['width']

            height = face_rectangle['height']

            cropImg = img[top - 20:top + height + 20, left - 20:left + width + 20]

            # 图片路径
            facebase = "/Users/rex/Desktop/Code/WisdomClassRaspberry/faces/" + str(i) + ".jpg"
            print("saving.....")
            cv2.imwrite(facebase, cropImg)
            #cv2.rectangle(img, (left, top), (left + width, top + height), (0, 255, 0), 2)

        #img2 = cv2.resize(img, (1280,720), interpolation=cv2.INTER_CUBIC);
        #cv2.imshow("detial",img2)
        #cv2.waitKey(0)
    




if __name__ == "__main__":
    # face++的api地址密码 用于人脸识别
    face_http_url = "https://api-cn.faceplusplus.com/facepp/v3/detect"
    face_key = "TISYNrP-YDndZwEYzO1rhlYbJuDZ7SxQ"
    face_secret = "Ts5ELvCMeBLJkuxhcew7W7ouEX91uxWr"

    file_path = "/Users/rex/Desktop/Code/WisdomClassRaspberry/image/test.jpg"

    faceDetect = FaceDetect(face_http_url, face_key, face_secret, file_path)
    faces = faceDetect.getface()
    faceDetect.detect(faces)