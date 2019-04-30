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


# 注意 部分机器测试bug解决方法

	bug是由于百度云ai开放平台的人脸识别库中的部分bugs
	解决方法：
	1.打开终端 输入pip3 show baidu-aip
	2.进入baidu-aip的包安装文件夹aip
	3.打开face.py全部删除 把如下内容复制粘贴进去替换即可

	[代码]

	# -*- coding: utf-8 -*-

	"""
	人脸识别
	"""

	import re
	import sys
	import math
	import time
	from .base import AipBase
	from .base import base64
	from .base import json
	from .base import urlencode
	from .base import quote

	class AipFace(AipBase):

		"""
		人脸识别
		"""

		__detectUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/detect'

		__searchUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/search'

		__userAddUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add'

		__userUpdateUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/update'

		__faceDeleteUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/delete'

		__userGetUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/get'

		__faceGetlistUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/getlist'

		__groupGetusersUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/getusers'

		__userCopyUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/copy'

		__userDeleteUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/delete'

		__groupAddUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/add'

		__groupDeleteUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/delete'

		__groupGetlistUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/getlist'

		__personVerifyUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/person/verify'

		__videoSessioncodeUrl = 'https://aip.baidubce.com/rest/2.0/face/v1/faceliveness/sessioncode'

		
		def detect(self, image, image_type, options=None):
			"""
				人脸检测
			"""
			options = options or {}

			data = {}
			data['image'] = image
			data['image_type'] = image_type

			data.update(options)
			return self._request(self.__detectUrl, json.dumps(data, ensure_ascii=False,cls = MyEncoder), {
				'Content-Type': 'application/json',
			})
		
		def search(self, image, image_type, group_id_list, options=None):
			"""
				人脸搜索
			"""
			options = options or {}

			data = {}
			data['image'] = image
			data['image_type'] = image_type
			data['group_id_list'] = group_id_list

			data.update(options)
			return self._request(self.__searchUrl, json.dumps(data, ensure_ascii=False,cls = MyEncoder), {
				'Content-Type': 'application/json',
			})
		
		def addUser(self, image, image_type, group_id, user_id, options=None):
			"""
				人脸注册
			"""
			options = options or {}

			data = {}
			data['image'] = image
			data['image_type'] = image_type
			data['group_id'] = group_id
			data['user_id'] = user_id

			data.update(options)
			return self._request(self.__userAddUrl, json.dumps(data, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})
		
		def updateUser(self, image, image_type, group_id, user_id, options=None):
			"""
				人脸更新
			"""
			options = options or {}

			data = {}
			data['image'] = image
			data['image_type'] = image_type
			data['group_id'] = group_id
			data['user_id'] = user_id

			data.update(options)
			return self._request(self.__userUpdateUrl, json.dumps(data, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})
		
		def faceDelete(self, user_id, group_id, face_token, options=None):
			"""
				人脸删除
			"""
			options = options or {}

			data = {}
			data['user_id'] = user_id
			data['group_id'] = group_id
			data['face_token'] = face_token

			data.update(options)
			return self._request(self.__faceDeleteUrl, json.dumps(data, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})
		
		def getUser(self, user_id, group_id, options=None):
			"""
				用户信息查询
			"""
			options = options or {}

			data = {}
			data['user_id'] = user_id
			data['group_id'] = group_id

			data.update(options)
			return self._request(self.__userGetUrl, json.dumps(data, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})
		
		def faceGetlist(self, user_id, group_id, options=None):
			"""
				获取用户人脸列表
			"""
			options = options or {}

			data = {}
			data['user_id'] = user_id
			data['group_id'] = group_id

			data.update(options)
			return self._request(self.__faceGetlistUrl, json.dumps(data, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})
		
		def getGroupUsers(self, group_id, options=None):
			"""
				获取用户列表
			"""
			options = options or {}

			data = {}
			data['group_id'] = group_id

			data.update(options)
			return self._request(self.__groupGetusersUrl, json.dumps(data, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})
		
		def userCopy(self, user_id, options=None):
			"""
				复制用户
			"""
			options = options or {}

			data = {}
			data['user_id'] = user_id

			data.update(options)
			return self._request(self.__userCopyUrl, json.dumps(data, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})
		
		def deleteUser(self, group_id, user_id, options=None):
			"""
				删除用户
			"""
			options = options or {}

			data = {}
			data['group_id'] = group_id
			data['user_id'] = user_id

			data.update(options)
			return self._request(self.__userDeleteUrl, json.dumps(data, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})
		
		def groupAdd(self, group_id, options=None):
			"""
				创建用户组
			"""
			options = options or {}

			data = {}
			data['group_id'] = group_id

			data.update(options)
			return self._request(self.__groupAddUrl, json.dumps(data, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})
		
		def groupDelete(self, group_id, options=None):
			"""
				删除用户组
			"""
			options = options or {}

			data = {}
			data['group_id'] = group_id

			data.update(options)
			return self._request(self.__groupDeleteUrl, json.dumps(data, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})
		
		def getGroupList(self, options=None):
			"""
				组列表查询
			"""
			options = options or {}

			data = {}

			data.update(options)
			return self._request(self.__groupGetlistUrl, json.dumps(data, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})
		
		def personVerify(self, image, image_type, id_card_number, name, options=None):
			"""
				身份验证
			"""
			options = options or {}

			data = {}
			data['image'] = image
			data['image_type'] = image_type
			data['id_card_number'] = id_card_number
			data['name'] = name

			data.update(options)
			return self._request(self.__personVerifyUrl, json.dumps(data, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})
		
		def videoSessioncode(self, options=None):
			"""
				语音校验码接口
			"""
			options = options or {}

			data = {}

			data.update(options)
			return self._request(self.__videoSessioncodeUrl, json.dumps(data, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})
		

		__faceverifyUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/faceverify'

		def faceverify(self, images):
			"""
				在线活体检测
			"""

			return self._request(self.__faceverifyUrl, json.dumps(images, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})

		__matchUrl = 'https://aip.baidubce.com/rest/2.0/face/v3/match'

		def match(self, images):
			"""
				人脸比对
			"""

			return self._request(self.__matchUrl, json.dumps(images, ensure_ascii=False), {
				'Content-Type': 'application/json',
			})


	class MyEncoder(json.JSONEncoder):
		def default(self, obj):
			if isinstance(obj, bytes):
				return str(obj, encoding='utf-8')
			return json.JSONEncoder.default(self, obj)


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

# 2019-4-29

	修复了bug
	添加了每次识别结束后删除faces文件夹下面待识别图片的功能
	添加了多次识别功能

# 2019-4-30

	正式发布智慧教室(树莓派)V1.0 测试版本
	完成系统架构搭建 并测试成功