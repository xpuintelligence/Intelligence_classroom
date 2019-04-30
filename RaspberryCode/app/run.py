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


def get_image():
    LoadVideo.load()

def run_program():
    print(1)
    Wisdom.run_wisdom()
    clean()

def clean():
    os.chdir("../image")
    os.system("rm -rf *.jpg")
    print("清除完毕")

if __name__ == "__main__":

    while(1):
        localtime = time.strftime("%H-%M-%S")
        newtime = localtime.split('-')
        if(int(newtime[2])%10==0):
            print()
            get_image()
            print("图片获取完毕")
            print("开始识别")
            print()
            run_program()
            print("识别结束 开始清除缓存")
            time.sleep(1)
        