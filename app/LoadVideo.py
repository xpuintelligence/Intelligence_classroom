
import cv2
import time
import os

class LoadVideo:

    # 截图 利用ffmpeg
    def load(user, ip, password):  

        print("开始捕获图片")
        localtime = time.strftime("%H-%M-%S")
        os.system("ffmpeg -i rtsp://"+user+":"+password+"@"+ip+":554/h264/ch1/main/av_stream -y -f mjpeg -t 0.001 -s 1280x720 "+"../image/"+localtime+".jpg")
        print("已经截图成功，名字为: "+localtime+".jpg")

