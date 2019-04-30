import time

while(1):
    localtime = time.strftime("%H-%M-%S")
    # print(localtime)
    newtime = localtime.split('-')
    if(int(newtime[2])%5==0):
        print(newtime[2])
        print("ScreenShot")
        time.sleep(1)
        
    else:
        continue