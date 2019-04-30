# -*-coding:utf-8 -*-

from aip import AipFace
from aip import AipBodyAnalysis
import base64
import os
import cv2
import requests
import numpy as np
import threading
import time
from json import JSONDecoder
from FaceSearch import FaceSearch
from FaceDetect import FaceDetect
from PIL import Image, ImageDraw, ImageFont
from LoadVideo import LoadVideo
from Wisdom import Wisdom

# 获取图片
def get_image(user, ip, password):
    LoadVideo.load(user, ip, password)

# 识别程序
def run_program():
    Wisdom.run_wisdom()
    clean()

# 清除缓存
def clean():
    os.chdir("../image")
    os.system("rm -rf *.jpg")
    print("清除完毕")

if __name__ == "__main__":
    print("输入网络摄像头的用户名: ",end = "")
    user = input()
    print("输入网络摄像头的ip地址：",end = "")
    password = input()
    print("请输入网络摄像头的ip地址: ",end = "")
    ip = input()
    while(1):
        localtime = time.strftime("%H-%M-%S")
        newtime = localtime.split('-')
        if(int(newtime[2])%10==0):
            print()
            get_image(user, ip, password)
            print("图片获取完毕")
            print("开始识别")
            print()
            run_program()
            print("识别结束 开始清除缓存")
            time.sleep(1)
        