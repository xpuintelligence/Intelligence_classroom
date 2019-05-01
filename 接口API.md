后台API文档
===========
##web或app端登陆
###_请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73/wisdom_web/login/all

###_需传输的参数_
name|describe
----|------
account|账号
password|密码
status|学生为1，老师为2

###_返回值_
name|describe
----|------
status|返回值状态 1--->成功  2--->失败
msg|返回的消息信息
data|查询到的学生信息对象，对应数据库中的tb_student的各个值
###返回值样本
```
账号密码正确情况：
    {"status":1,
    "msg":"true",
    "data":{"id":"41609050128","name":"宁大力","sex":"male","picture":"","classId":"080902201601","professionId":"080902","collegeId":"06","password":"00000","wexinId":"","phoneCode":"","email":null}}
账号或密码错误情况：
    {"status":0,
    "msg":"账号或密码错误",
    "data":null}
```
