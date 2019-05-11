# WisdomClassRaspberry
# 智慧教室树莓派端

/app 核心代码文件夹

	LoadVideo.py rstp视频流读取模块 

	FaceDataBase.py 数据库读取模块    

	FaceSearch.py 人脸搜索模块

	FaceDetect 人脸识别模块
	
	Wisdom    功能执行模块
	
	debugFunc  测试功能使用的模块
	
	deleteCache 删除缓存模块
	
	GetJudge   判断模块
	
	Upload     上传模块

	run.py 程序执行文件

/faces 裁剪人脸文件夹

/image 测试图片文件夹

/output 处理完成的图片文件夹

# 文件树
    
    .
    ├── README.md
    ├── app
    │   ├── FaceDataBase.py
    │   ├── FaceDetect.py
    │   ├── FaceSearch.py
    │   ├── GetJudge.py
    │   ├── LoadVideo.py
    │   ├── Upload.py
    │   ├── Wisdom.py
    │   ├── debugFunc.py
    │   ├── deleteCache.py
    │   └── run.py
    ├── faces
    │   └── README.md
    ├── image
    │   └── README.md
    └── output
	

# 运行方法

	1.确认安装opencv 
	pip3 install opencv-python
	2.确认安装百度云开放平台
	pip3 install baidu-aip
	3.安装七牛云
	pip3 install qiniu
	4.运行
	python3 run.py


# 注意 部分机器测试bug解决方法

	bug是由于百度云ai开放平台的人脸识别库中的部分bugs

[解决方法](http://120.79.148.86/2019/05/10/Fix-Bug-Baidu-Aip/)

# 工作日志

[日志](http://120.79.148.86/2019/05/11/Wisdom-record/)