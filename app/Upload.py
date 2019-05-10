from qiniu import Auth, put_file, etag
import qiniu.config
import os

class Upload:

    def __init__(self):

        # 七牛云的access_key 和 secret_key
        self.access_key = "-oNo1ZYr8u46jWJhmjXE927uHg2v0iPdmVAdhga-"
        self.secret_key = "Jh2afu387eAhQMaATFAHAEZJvhwGio-ohU17f2xH"

    def upload(self,filepath,filename):
        #构建鉴权对象
        q = Auth(self.access_key, self.secret_key)
        #要上传的空间
        bucket_name = 'wisdom_class_image'
        #要上传文件的本地路径
        localfile = filepath
        #上传后保存的文件名
        key = filename
        #生成上传 Token，可以指定过期时间等
        token = q.upload_token(bucket_name, key, 3600)
        ret, info = put_file(token, key, localfile)
        print(info)
        assert ret['key'] == key
        assert ret['hash'] == etag(localfile)