# WisdomClassRaspberry
# 智慧教室树莓派端

/app 核心代码文件夹

	LoadVideo.py rstp视频流读取模块 （有bug，暂时无法使用）

	FaceReco.py 图像识别处理模块     （bug，暂时无法使用）

	FaceDataBase.py 数据库读取模块    

	FaceSearch.py 人脸搜索模块

	FaceDetect 人脸识别模块

	run.py 程序执行文件

/faces 裁剪人脸文件夹

/image 测试图片文件夹

# 运行方法

	1.确认安装opencv
	pip3 install opencv-python
	2.确认安装百度云开放平台
	pip3 install baidu-aip
	3.运行
	python3 run.py


# 2019-4-25
	
	编写了人脸识别模块FaceDetect
	编写了人脸搜索模块FaceSearch
	添加了run.py运行文件
	修改了FaceDetect FaceSearch
	修复了同时调用次数太多导致api调用失败的bug

# 2019-4-26

	引入了百度云ai人体识别的接口 丰富了软件功能
	添加了数据库读写的功能
	编写了数据库读写类FaceDataBase
	修复了同时调用次数太多导致api调用失败的bug(改为300毫秒调用一次api)

# 2019-4-27
	
	修复了bug
	添加了显示出勤率，抬头率的方法

# 2019-4-28
	
	修复了bug
	优化了数据库算法
	编写了每个学生出勤率计算的方法
	完成了与整个系统的数据库的对接

