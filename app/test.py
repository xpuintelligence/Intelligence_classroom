import cv2

file_path = "../image/2headown1.jpg"
img = cv2.imread(file_path, cv2.IMREAD_COLOR)
size = img.shape
print(size[0]*(1/5))