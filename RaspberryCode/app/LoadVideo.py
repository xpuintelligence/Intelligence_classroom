import cv2


class LoadVideo:

    def __init__(self, username, password, ip):

        self.username = username
        self.password = password
        self.ip = ip

    def load(self):

        video_path = "rstp://" + self.username + ":" + self.password + "@" + self.ip + "/"

        
        cap = cv2.VideoCapture(video_path)
        ret, frame = cap.read()
        i = 1
        j = 0
        timeF = 1800  # 截图间隔为10分钟
        while ret:
            ret, frame = cap.read()
            cv2.imshow("frame", frame)
            if (i % timeF == 1):
                cv2.imwrite('/home/pi/Desktop/images/' + str(i) + '.jpg', frame)
                j = j + 1
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break
            i = i + 1
        if j == 10:  # 一节课截图10次
            cv2.destroyAllWindows()
            cap.release()


if __name__ == "__main__":
    username = eval(print("请输入串流摄像头的用户名："))
    password = eval(print("请输入串流摄像头的密码："))
    ip = eval(print("请输入串流摄像头的ip地址："))

    loadVideo = LoadVideo(username, password, ip)

    loadVideo.load()
