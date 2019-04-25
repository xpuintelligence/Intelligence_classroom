
import cv2
import requests
from json import JSONDecoder


import os
from aip import AipBodyAnalysis
from aip import AipFace
import base64


import LoadDataBase

studentRecoName = []


class FaceReco:

    def __init__(self, http_url, key, secret):

        self.http_url = http_url
        self.key = key
        self.secret = secret

    # 连接face++ api
    def ConnectAPI(self, filepath):

        try:

            data = {"api_key": self.key, "api_secret": self.secret, "return_attributes": "gender,age,smiling,beauty"}

            files = {"image_file": open(filepath, "rb")}

            response = requests.post(self.http_url, data=data, files=files)

            req_con = response.content.decode('utf-8')

            req_dict = JSONDecoder().decode(req_con)

            faces = req_dict['faces']

            # print(faces)

            return faces


        except Exception:
            pass

    # 识别
    def faceReco(self, faces, filepath, client, LoadDataBase):

        face_num = len(faces)

        

        # 打开图片
        student_file = open(filepath, 'rb').read()
        # 调用百度Ai识别人数
        student_num = client.bodyNum(student_file)['person_num']

        print("一共有" + str(student_num) + "个学生")

        for i in range(0, face_num):

            face_rectangle = faces[i]['face_rectangle']

            # face_info = str(face_rectangle['left']) + ' ' + str(face_rectangle['top']) + ' ' + str(
            # face_rectangle['width']) + ' ' + str(face_rectangle['height']);

            left = face_rectangle['left']

            top = face_rectangle['top']

            width = face_rectangle['width']

            height = face_rectangle['height']

            cropImg = img[top - 20:top + height + 20, left - 20:left + width + 20]

            # 图片路径
            facebase = "C:/Users/Rex/Desktop/face++/FaceRecoV2.0/faces/" + str(i) + ".png"

            # 保存图片

            cv2.imwrite(facebase, cropImg)
            cv2.rectangle(img, (left, top), (left + width, top + height), (0, 255, 0), 2)
            # print("图片保存")

            # 识别人脸
            for filename in os.listdir(r'C:/Users/Rex/Desktop/face++/FaceRecoV2.0/faceCompare'):

                facecompare = "C:/Users/Rex/Desktop/face++/FaceRecoV2.0/faceCompare/" + filename

                flag = self.FaceCompare(facebase, facecompare)

                if flag == 1:
                    facename = filename.replace('.jpg', '')

                    print("对象为" + facename)
                    LoadDataBase.setStudentHeadupJudge(facename)
                    print("\n")
                    if facename not in studentRecoName:
                        studentRecoName.append(facename)

        print(studentRecoName)

        return student_num

        # 获得全班抬头率

    def getHeadupRate(self, filepath, bodyClient):

        student_file = open(filepath, 'rb').read()

        headup_student_count = 0
        studentbody = bodyClient.bodyAnalysis(student_file)
        student_num = studentbody['person_num']
        for i in range(0, student_num):

            student_nose_x = studentbody['person_info'][i]['body_parts']['nose']['x']
            student_nose_y = studentbody['person_info'][i]['body_parts']['nose']['y']
            if (student_nose_x != 0.0 and student_nose_y != 0.0):
                headup_student_count = headup_student_count + 1

        return headup_student_count

    # 人脸对比
    def FaceCompare(self, face1, face2, faceClient):

        try:
            result = faceClient.match([
                {
                    'image': base64.b64encode(open(face1, 'rb').read()),
                    'image_type': 'BASE64',
                },
                {
                    'image': base64.b64encode(open(face2, 'rb').read()),
                    'image_type': 'BASE64',
                }
            ])

            compare_result = result['result']['score']
            if compare_result > 70:
                return 1
        except Exception:
            pass


if __name__ == "__main__":

    # face++的api地址密码 用于人脸识别
    face_http_url = "https://api-cn.faceplusplus.com/facepp/v3/detect"
    face_key = "TISYNrP-YDndZwEYzO1rhlYbJuDZ7SxQ"
    face_secret = "Ts5ELvCMeBLJkuxhcew7W7ouEX91uxWr"

    # 百度AI的api地址和密码 用于人数识别
    APP_ID = '15764227'
    API_KEY = 'ciU5i0A0Rd77WIZMkBlgutGt'
    SECRET_KEY = '8N3GpZKlOSLzyFknCsiH6ncoufCSMLaC'

    bodyClient = AipBodyAnalysis(APP_ID, API_KEY, SECRET_KEY)
    faceClient = AipFace(APP_ID, API_KEY, SECRET_KEY)

    face = FaceReco(face_http_url, face_key, face_secret)

    # 统计学生人数

    student_num_total = []

    AllheadupRate = 0.0

    # time_start=time.time()

    # 初始化数据库存储服务

    host = "119.29.254.47"

    user = "root"

    database = "wisdom_classroom"

    password = "ndl.04551"

    LoadDataBase = LoadDataBase(host, user, database, password)

    # 将截图的所有图片进行分析
    for filename in os.listdir('C:/Users/Rex/Desktop/face++/FaceRecoV2.0/image/'):
        if filename.endswith('.png'):
            filepath = "C:/Users/Rex/Desktop/face++/FaceRecoV2.0/image/" + filename

            # 百度AI的api地址和密码 用于人数识别

            faces = face.ConnectAPI(filepath)

            student_num = face.faceReco(faces, filepath, bodyClient, LoadDataBase)

            student_num_total.append(student_num)

            headupRate = face.getHeadupRate(filepath, bodyClient)

            # print("全班人数为{},抬头人数为{},抬头率为{:.2f}".format(student_num,headupRate,(headupRate/student_num)))

            AllheadupRate = AllheadupRate + headupRate / student_num

    # 抬头率
    AllheadupRate = round(AllheadupRate / 5, 2)
    # 教室应到人数
    course_number_people = 31
    # 教室目前人数
    actual_number = max(student_num_total)
    print("\n")
    print("教室目前人数为{},这节课的抬头率为{}".format(actual_number, AllheadupRate))

    # 存储全班抬头率

    LoadDataBase.insertHeadUPSql(course_number_people, actual_number, headupRate)

    os.system("del" + "C:\\Users\\Rex\\Desktop\\face++\\FaceRecoV2.0\\faces\\*.jpg")
