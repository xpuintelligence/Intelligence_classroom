
from aip import AipFace
from aip import AipBodyAnalysis
import base64
import os
import cv2
import requests
from json import JSONDecoder
from FaceSearch import FaceSearch
from FaceDetect import FaceDetect



if __name__ == "__main__":
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

    # 测试图片的文件位置
    file_path = "../image/test2.jpg"
    # 实例化人脸检测对象
    faceDetect = FaceDetect(face_http_url, face_key, face_secret, file_path, client_baidu_body)
    faces = faceDetect.getface()  # 获取人脸数
    # 分割人脸存储到指定文件夹
    faceDetect.detect(faces)
    faceDetect.get_student_num()

    # 测试图片格式为BASE64
    imageType = "BASE64" 
    
    groupIdList = "wisdom_class,1" # 人脸库的名字和id

    facefolder = "../faces/" # 存储的位置

    faceSearch = FaceSearch(facefolder, imageType, groupIdList, client_baidu_face) # 实例化人脸搜索对象
    faceSearch.search()    # 人脸库人脸搜素