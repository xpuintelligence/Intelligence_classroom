# import cv2


# class LoadVideo:

#     def __init__(self, username, password, ip):

#         self.username = username
#         self.password = password
#         self.ip = ip

#     def load(self):

#         video_path = "rstp://" + self.username + ":" + self.password + "@" + self.ip +":554//Streaming/Channels/1"

        
#         cap = cv2.VideoCapture(video_path)
#         ret, frame = cap.read()
#         i = 1
#         j = 0
#         #timeF = 1800  # 截图间隔为10分钟
#         #while ret:
#         ret, frame = cap.read()
#         cv2.imshow("frame", frame)
#             #if (i % timeF == 1):
#             #    cv2.imwrite('/home/pi/Desktop/images/' + str(i) + '.jpg', frame)
#             #    j = j + 1
#             #if cv2.waitKey(1) & 0xFF == ord('q'):
#             #    break
#             #i = i + 1
#         #if j == 10:  # 一节课截图10次
#             #cv2.destroyAllWindows()
#             #cap.release()
#         cv2.waitKey(0)


# if __name__ == "__main__":
#     print("user: ", end="")
#     username = input()
#     print("password: ", end="")
#     password = input()
#     print("ip: ", end="")
#     ip = input()

#     loadVideo = LoadVideo(username, password, ip)

#     loadVideo.load()

import cv2

video_path = "rtsp://admin:UMABQD@192.168.0.106:554/h264/ch1/main/av_stream"
cap = cv2.VideoCapture(video_path)
ret,frame = cap.read()
while ret:
    ret,frame = cap.read()
    img2 = cv2.resize(frame, (1280,720), interpolation=cv2.INTER_CUBIC);
    cv2.imshow("frame",img2)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        cv2.imwrite("../image/test3.jpg",frame)
        break
cv2.destroyAllWindows()
cap.release()
