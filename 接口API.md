后台API文档
===========
## web或app端登陆
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73/wisdom_web/login/all

### _需传输的参数_
name|describe
----|------
account|账号
password|密码
status|学生为1，老师为2

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  0--->失败
msg|返回的消息信息
data|查询到的学生信息对象，对应数据库中的tb_student的各个值
### 返回值样本
```json
账号密码正确情况:
    {"status":1,
    "msg":"true",
    "data":{"id":"41609050128","name":"宁大力","sex":"male","picture":"","classId":"080902201601","professionId":"080902","collegeId":"06","password":"00000","wexinId":"","phoneCode":"","email":null}}
    
账号或密码错误情况:
    {"status":0,
    "msg":"账号或密码错误",
    "data":null}
```
----
## 微信小程序登陆
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73/wisdom_web/login/weixinLogin

### _需传输的参数_
name|describe
----|------
appid|小程序id
secret|小程序的appSecret
js_code|登陆时获取的code
grant_type|授权类型，此处只需填写 authorization_code

### _返回值_
name|describe
----|------
status|返回值状态 0--->未绑定 1--->学生 2--->老师 
msg|服务器去腾讯api中获取的openid
data|null
### 返回值样本
```json
学生第一次登陆:
    {"status":1,
    "msg":获取到的openid,
    "data":null }
老师第一次登陆:
    {"status":2,
    "msg":获取到的openid,
    "data":null}
该用户未绑定:
        {"status":2,
        "msg":获取到的openid,
        "data":null}
```
----
## 微信小程序绑定用户与openid
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73/wisdom_web/wx/bindOpenid

### _需传输的参数_
name|describe
----|------
account|绑定的账号
password|绑定的密码
status|绑定人员的类型：1-->学生、2-->老师

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  0--->失败
msg|成功或错误消息
data|null
### 返回值样本
```json
用户绑定的账号密码正确:
    {"status":1,
    "msg":"true",
    "data":null }
用户绑定的账号或密码错误:
    {"status":0,
    "msg":"账号或密码错误",
    "data":null}
```
----
## 查询本周的课程信息
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73/wisdom_web/studentCourseInfo/thisWeek

### _需传输的参数_
name|describe
----|------
无|无

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  （目前没有查不出来的情况）
msg|true
data|（List集合）课程的信息，该节课老师的信息，该节课的时间地点
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": [
        {
            "id": "20190506100000",
            "time": 1557108000000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        },
        {
            "id": "20190509100000",
            "time": 1557367200000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        }
    ]
}
```
----
## 查询本月的课程信息
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73/wisdom_web/studentCourseInfo/thisMonth
### _需传输的参数_
name|describe
----|------
无|无
### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  （目前没有查不出来的情况）
msg|true
data|（List集合）每一节课课程的信息，该节课老师的信息，该节课的时间地点
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": [
        {
            "id": "20190506100000",
            "time": 1557108000000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        },
        {
            "id": "20190509100000",
            "time": 1557367200000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        }
    ]
}
```
----
## 查询一段时间内的课程信息
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73/wisdom_web/studentCourseInfo/thisWeek

### _需传输的参数_
name|describe
----|------
begin|开始时间（格式要求：2018-10-10 00:00:00）
end|结束时间（格式要求如上）

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  （目前没有查不出来的情况）
msg|true
data|（List集合）课程的信息，该节课老师的信息，该节课的时间地点
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": [
        {
            "id": "20190506100000",
            "time": 1557108000000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        },
        {
            "id": "20190509100000",
            "time": 1557367200000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        }
    ]
}
```
----
## 查询相对于本周而言，前x周的考勤 
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73/wisdom_web/studentCourseInfo/frontXWeek

### _需传输的参数_
name|describe
----|------
n|与本周相比前n周（前n周的考勤 本周为11周，那么前1周为第10周）

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  （目前没有查不出来的情况）
msg|true
data|（List集合）课程的信息，该节课老师的信息，该节课的时间地点
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": [
        {
            "id": "20190506100000",
            "time": 1557108000000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        },
        {
            "id": "20190509100000",
            "time": 1557367200000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        }
    ]
}
```
----
## 查询相对于本月而言，前n月的课程信息 
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73/wisdom_web/studentCourseInfo/frontXMonth

### _需传输的参数_
name|describe
----|------
n|与本与相比前n月（前n月的考勤 本月为本学期第3月，那么前1月为第2月）

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  （目前没有查不出来的情况）
msg|true
data|（List集合）课程的信息，该节课老师的信息，该节课的时间地点
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": [
        {
            "id": "20190506100000",
            "time": 1557108000000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        },
        {
            "id": "20190509100000",
            "time": 1557367200000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        }
    ]
}
```
----
## 查询本学期第x个月的课程信息
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73/wisdom_web/studentCourseInfo/xMonth

### _需传输的参数_
name|describe
----|------
n|本学期的第x个月

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  （目前没有查不出来的情况）
msg|true
data|（List集合）课程的信息，该节课老师的信息，该节课的时间地点
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": [
        {
            "id": "20190506100000",
            "time": 1557108000000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        },
        {
            "id": "20190509100000",
            "time": 1557367200000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        }
    ]
}
```
----
## 查询本学期第x个周的课程信息
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73/wisdom_web/studentCourseInfo/xWeek

### _需传输的参数_
name|describe
----|------
n|本学期的第x个周的考勤信息（0-20）

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  （目前没有查不出来的情况）
msg|true
data|（List集合）课程的信息，该节课老师的信息，该节课的时间地点
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": [
        {
            "id": "20190506100000",
            "time": 1557108000000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        },
        {
            "id": "20190509100000",
            "time": 1557367200000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        }
    ]
}
```
----
## 查询今天的课程信息
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73/wisdom_web/studentCourseInfo/today

### _需传输的参数_
name|describe
----|------
无|无

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  （目前没有查不出来的情况）
msg|true
data|（List集合）课程的信息，该节课老师的信息，该节课的时间地点
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": [
        {
            "id": "20190506100000",
            "time": 1557108000000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        },
        {
            "id": "20190509100000",
            "time": 1557367200000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        }
    ]
}
```
----
## 查询本学期所有的课程信息
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73/wisdom_web/studentCourseInfo/thisSemester

### _需传输的参数_
name|describe
----|------
无|无

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  （目前没有查不出来的情况）
msg|true
data|（List集合）课程的信息，该节课老师的信息，该节课的时间地点
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": [
        {
            "id": "20190506100000",
            "time": 1557108000000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        },
        {
            "id": "20190509100000",
            "time": 1557367200000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        }
    ]
}
```
----
## 查询固定某一天的课程信息
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73/wisdom_web/studentCourseInfo/oneday

### _需传输的参数_
name|describe
----|------
datetime|2019-10-10这样的也可以加时分秒 2019-10-10 00:00:00这种

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  （目前没有查不出来的情况）
msg|true
data|（List集合）课程的信息，该节课老师的信息，该节课的时间地点
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": [
        {
            "id": "20190506100000",
            "time": 1557108000000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        },
        {
            "id": "20190509100000",
            "time": 1557367200000,
            "courseId": "1009",
            "courseName": "软件项目管理",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "1110",
            "teacherId": "17",
            "teacherName": "陈金广"
        }
    ]
}
```
----

