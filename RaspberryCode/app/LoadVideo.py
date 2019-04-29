# import cv2

# # class LoadVideo:

# #     def __init__(self, username, password, ip):

# #         self.username = username
# #         self.password = password
# #         self.ip = ip

# def load():

#         # video_path = "rtsp://admin:UMABQD@"+self.ip+":554/h264/ch1/main/av_stream"
#         video_path = "../test.mp4"
#         cap = cv2.VideoCapture(video_path)
#         ret, frame = cap.read()
#         i = 1
#         j = 0
#         timeF = 600  # 截图间隔为10分钟
#         while ret:
#             ret, frame = cap.read()
#             #cv2.imshow("frame", frame)
#             if(i % timeF == 1):
#                 cv2.imwrite('../images/' + str(i) + '.jpg', frame)
#                 j = j + 1
#             if cv2.waitKey(1) & 0xFF == ord('q'):
#                 break
#                 i = i + 1
#             if j == 10:  # 一节课截图10次
#                 cv2.destroyAllWindows()
#                 cap.release()
#             cv2.waitKey(0)


# if __name__ == "__main__":
#     #print("user: ", end="")
#     #username = input()
#     #print("password: ", end="")
#     #password = input()
#     #print("ip: ", end="")
#     #ip = input()

#     #loadVideo = LoadVideo(username, password, ip)

#     load()

import cv2
 
       
vc = cv2.VideoCapture('../test.mp4') #读入视频文件
c=1
 
if vc.isOpened(): #判断是否正常打开
    rval , frame = vc.read()
else:
    rval = False
 
timeF = 300  #视频帧计数间隔频率
 
while rval:   #循环读取视频帧
    rval, frame = vc.read()
    if(c%timeF == 0): #每隔timeF帧进行存储操作
        cv2.imwrite('../image/'+str(c) + '.jpg',frame) #存储为图像
    c = c + 1
    cv2.waitKey(1)
vc.release()
