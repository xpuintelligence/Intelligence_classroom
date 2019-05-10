# -*-coding:utf-8 -*-

from aip import AipFace
from aip import AipBodyAnalysis
import os
import cv2
import numpy as np
from FaceSearch import FaceSearch
from FaceDetect import FaceDetect
from PIL import Image, ImageDraw, ImageFont
from GetJudge import GetJudge
from Upload import Upload



class Wisdom:

    def drawInfo(img,student_name_list,headup_rate,attendence,student_num,image_height,image_width,filename):

        output_path = "../output/"+filename
        # 利用pillow包输出中文
        pil_im = Image.fromarray(img)
        draw = ImageDraw.Draw(pil_im)
        font = ImageFont.truetype("STHeiti Light.ttc", 50, encoding="utf-8")
        draw.text((int(image_width*(1/10)), int(image_height*(1/5))), "student: "+str(student_name_list), (0, 255, 0), font=font)
        img = cv2.cvtColor(np.array(pil_im), cv2.COLOR_RGB2BGR)
                
        img = cv2.putText(img, "headupRate: "+str(headup_rate), (int(image_width*(1/10)), int(image_height*(1/5)+100)), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 0), 3)
        img = cv2.putText(img, "Attendence: "+str(attendence), (int(image_width*(1/10)), int(image_height*(1/5)+200)), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 0), 3)
        img = cv2.putText(img, "student_num: "+str(student_num), (int(image_width*(1/10)), int(image_height*(1/5)+300)), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 0), 3)
                
        img2 = cv2.resize(img, (1280,720), interpolation=cv2.INTER_CUBIC);

        # 保存处理之后的图片
        cv2.imwrite(output_path,img2)
        # 准备上传七牛云
        upload = Upload()
        upload.upload(output_path,filename)


    def run_wisdom(sleepdict,student_attendancedict):

        # face++的api地址密码 用于人脸识别
        face_http_url = "https://api-cn.faceplusplus.com/facepp/v3/detect"
        face_key = "TISYNrP-YDndZwEYzO1rhlYbJuDZ7SxQ"
        face_secret = "Ts5ELvCMeBLJkuxhcew7W7ouEX91uxWr"

        # 百度AI人脸库的api地址和密码 用于人脸库搜索识别
        APP_ID_FACE = '15764893'
        API_KEY_FACE = 'rHGwsXanKC4yWj4pXeepDZcc'
        SECRET_KEY_FACE = 'qMONYxM1xTwnQh2q0DsHB7YG5Ht9LOal'

        # 百度AI人体的api信息 用于人体识别分析
        APP_ID_BODY = "15764227"
        API_KEY_BODY = 'ciU5i0A0Rd77WIZMkBlgutGt'
        SECRET_KEY_BODY = '8N3GpZKlOSLzyFknCsiH6ncoufCSMLaC'
        # 实例化人脸分析搜索对象
        client_baidu_face = AipFace(APP_ID_FACE, API_KEY_FACE, SECRET_KEY_FACE) 
        # 实例化人体分析对象
        client_baidu_body = AipBodyAnalysis(APP_ID_BODY, API_KEY_BODY, SECRET_KEY_BODY) 

        # 实例化判断类的对象
        getJudge = GetJudge(sleepdict,student_attendancedict)
    
        # 测试图片的文件位置
        for filename in os.listdir("../image/"):
            if filename.endswith('.jpg'):
                print(filename)
                file_path = "../image/"+filename
                # 实例化人脸检测对象
                faceDetect = FaceDetect(face_http_url, face_key, face_secret, file_path, client_baidu_body, client_baidu_face)
                faces = faceDetect.getface()  # 获取人脸数
                # 分割人脸存储到指定文件夹
                face_pixel = faceDetect.detect(faces)
                student_num = faceDetect.get_student_num()
                # print(face_pixel)
                print("当前识别到的人体数为: "+str(student_num))

                # 测试图片格式为BASE64
                imageType = "BASE64" 
                groupIdList = "wisdom_class,1" # 人脸库的名字和id
                facefolder = "../faces/" # 存储的位置
                faceSearch = FaceSearch(facefolder, imageType, groupIdList, client_baidu_face) # 实例化人脸搜索对象
                student_name_list = [] # 初始化人名列表
                student_name_list ,student_headown_list= faceSearch.search()    # 人脸库人脸搜素
                print("当前识别到的学生为："+str(student_name_list))
                # 当前学生的单个考勤查询
                getJudge.student_attendance_Judge(student_name_list)
                if len(student_headown_list):
                    print("低头的学生为: "+str(student_headown_list))
                # 检测学生是不是睡觉或不认真听课
                getJudge.sleepJudge(student_headown_list)

                headup_rate = faceDetect.get_headup_rate()
                print("班级当前抬头率为："+str(headup_rate))
                attendence = faceDetect.attendence()
                print("出勤率为: "+str(attendence))

                # 读取图片
                img = cv2.imread(file_path, cv2.IMREAD_COLOR)
                # 获取图片的尺寸
                imgsize = img.shape
                # 图片的高
                image_height = imgsize[0]
                # 图片宽
                image_width = imgsize[1]
                for i in range(0,len(face_pixel)):
                    face_rectangle = list(face_pixel[i].split(","))
                    left = int(face_rectangle[0])
                    top = int(face_rectangle[1])
                    width = int(face_rectangle[2])
                    height = int(face_rectangle[3])
                    
                    #print(face_rectangle)
                    cv2.rectangle(img, (left, top), (left + width, top + height), (0, 255, 0), 2)

                # 利用pillow包输出中文
                
                Wisdom.drawInfo(img,student_name_list,headup_rate,attendence,student_num,image_height,image_width,filename)

                # 删除faces文件夹的待识别面部
                os.chdir("../faces")
                os.system("rm -rf *.jpg")
                print("清除临时文件完毕")
                student_name_list.clear()
                print("人名已经清除")
                print()
