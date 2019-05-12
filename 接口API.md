后台API文档
===========
## web或app端登陆
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73:8080/wisdom_web/login/all

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
   {
       "status": 1,
       "msg": "true",
       "data": {
           "id": "41609050128",
           "name": "宁大力",
           "sex": "male",
           "phone": "",
           "email": null,
           "classId": "080902201601",
           "className": "软件1班",
           "professionId": "080902",
           "professionName": "软件工程",
           "collegeId": "06",
           "collegeName": "计算机科学学院",
           "weixinId": null
       }
   }
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
url|http://47.103.14.73:8080/wisdom_web/login/weixinLogin

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
    "data":服务其所对应的session值 }
老师第一次登陆:
    {"status":2,
    "msg":获取到的openid,
    "data":服务其所对应的session值}
该用户未绑定:
        {"status":2,
        "msg":获取到的openid,
        "data":服务其所对应的session值}
```
----
## 微信小程序绑定用户与openid
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73:8080/wisdom_web/wx1/bindOpenid

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
    "data":
     {
        "id": "41609050128",
        "name": "宁大力",
        "sex": "male",
        "phone": "",
        "email": null,
        "classId": "080902201601",
        "className": "软件1班",
        "professionId": "080902",
        "professionName": "软件工程",
        "collegeId": "06",
        "collegeName": "计算机科学学院",
        "weixinId": null}
用户绑定的账号或密码错误:
    {"status":0,
    "msg":"账号或密码错误",
    "data":null}
```
----
## 微信小程序获取学生的个人信息
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73:8080/wisdom_web/wxGetPersonInfo/getStudentInfo

### _需传输的参数_
name|describe
----|------
无|无

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
    "data":
     {
        "id": "41609050128",
        "name": "宁大力",
        "sex": "male",
        "phone": "",
        "email": null,
        "classId": "080902201601",
        "className": "软件1班",
        "professionId": "080902",
        "professionName": "软件工程",
        "collegeId": "06",
        "collegeName": "计算机科学学院",
        "weixinId": null}
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
url|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/thisWeek

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
            "id": "20190503081000",
            "time": 1556842200000,
            "courseId": "1008",
            "courseName": "企业管理与技术经济",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "B135",
            "teacherId": "15",
            "teacherName": "龚东生"
        },
        {
              "id": "20190503081000",
              "time": 1556842200000,
              "courseId": "1008",
              "courseName": "企业管理与技术经济",
              "courseNote": null,
              "attendanceTotalScore": 60,
              "leaveScore": 20,
              "attendScore": 20,
              "lateAttendScore": 10,
              "headUpScore": 40,
              "classroomId": "B135",
              "teacherId": "15",
              "teacherName": "龚东生"
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
url|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/thisMonth
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
            "id": "20190503081000",
            "time": 1556842200000,
            "courseId": "1008",
            "courseName": "企业管理与技术经济",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "B135",
            "teacherId": "15",
            "teacherName": "龚东生"
        },
        {
              "id": "20190503081000",
              "time": 1556842200000,
              "courseId": "1008",
              "courseName": "企业管理与技术经济",
              "courseNote": null,
              "attendanceTotalScore": 60,
              "leaveScore": 20,
              "attendScore": 20,
              "lateAttendScore": 10,
              "headUpScore": 40,
              "classroomId": "B135",
              "teacherId": "15",
              "teacherName": "龚东生"
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
url|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/thisWeek

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
            "id": "20190503081000",
            "time": 1556842200000,
            "courseId": "1008",
            "courseName": "企业管理与技术经济",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "B135",
            "teacherId": "15",
            "teacherName": "龚东生"
        },
        {
              "id": "20190503081000",
              "time": 1556842200000,
              "courseId": "1008",
              "courseName": "企业管理与技术经济",
              "courseNote": null,
              "attendanceTotalScore": 60,
              "leaveScore": 20,
              "attendScore": 20,
              "lateAttendScore": 10,
              "headUpScore": 40,
              "classroomId": "B135",
              "teacherId": "15",
              "teacherName": "龚东生"
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
url|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/frontXWeek

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
            "id": "20190503081000",
            "time": 1556842200000,
            "courseId": "1008",
            "courseName": "企业管理与技术经济",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "B135",
            "teacherId": "15",
            "teacherName": "龚东生"
        },
        {
              "id": "20190503081000",
              "time": 1556842200000,
              "courseId": "1008",
              "courseName": "企业管理与技术经济",
              "courseNote": null,
              "attendanceTotalScore": 60,
              "leaveScore": 20,
              "attendScore": 20,
              "lateAttendScore": 10,
              "headUpScore": 40,
              "classroomId": "B135",
              "teacherId": "15",
              "teacherName": "龚东生"
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
url|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/frontXMonth

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
            "id": "20190503081000",
            "time": 1556842200000,
            "courseId": "1008",
            "courseName": "企业管理与技术经济",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "B135",
            "teacherId": "15",
            "teacherName": "龚东生"
        },
        {
              "id": "20190503081000",
              "time": 1556842200000,
              "courseId": "1008",
              "courseName": "企业管理与技术经济",
              "courseNote": null,
              "attendanceTotalScore": 60,
              "leaveScore": 20,
              "attendScore": 20,
              "lateAttendScore": 10,
              "headUpScore": 40,
              "classroomId": "B135",
              "teacherId": "15",
              "teacherName": "龚东生"
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
url|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/xMonth

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
            "id": "20190503081000",
            "time": 1556842200000,
            "courseId": "1008",
            "courseName": "企业管理与技术经济",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "B135",
            "teacherId": "15",
            "teacherName": "龚东生"
        },
        {
              "id": "20190503081000",
              "time": 1556842200000,
              "courseId": "1008",
              "courseName": "企业管理与技术经济",
              "courseNote": null,
              "attendanceTotalScore": 60,
              "leaveScore": 20,
              "attendScore": 20,
              "lateAttendScore": 10,
              "headUpScore": 40,
              "classroomId": "B135",
              "teacherId": "15",
              "teacherName": "龚东生"
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
url|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/xWeek

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
            "id": "20190503081000",
            "time": 1556842200000,
            "courseId": "1008",
            "courseName": "企业管理与技术经济",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "B135",
            "teacherId": "15",
            "teacherName": "龚东生"
        },
        {
              "id": "20190503081000",
              "time": 1556842200000,
              "courseId": "1008",
              "courseName": "企业管理与技术经济",
              "courseNote": null,
              "attendanceTotalScore": 60,
              "leaveScore": 20,
              "attendScore": 20,
              "lateAttendScore": 10,
              "headUpScore": 40,
              "classroomId": "B135",
              "teacherId": "15",
              "teacherName": "龚东生"
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
url|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/today

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
            "id": "20190503081000",
            "time": 1556842200000,
            "courseId": "1008",
            "courseName": "企业管理与技术经济",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "B135",
            "teacherId": "15",
            "teacherName": "龚东生"
        },
        {
              "id": "20190503081000",
              "time": 1556842200000,
              "courseId": "1008",
              "courseName": "企业管理与技术经济",
              "courseNote": null,
              "attendanceTotalScore": 60,
              "leaveScore": 20,
              "attendScore": 20,
              "lateAttendScore": 10,
              "headUpScore": 40,
              "classroomId": "B135",
              "teacherId": "15",
              "teacherName": "龚东生"
          }
    ]
}
```
----
 ## 查询的课程信息
 ### _请求方式与url_
 name|describe
 ----|------
 method|post
 url|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/oneday
 
 ### _需传输的参数_
 name|describe
 ----|------
 dateTime|2010-10-10 00:00:00
 
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
            "id": "20190503081000",
            "time": 1556842200000,
            "courseId": "1008",
            "courseName": "企业管理与技术经济",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "B135",
            "teacherId": "15",
            "teacherName": "龚东生"
        },
        {
              "id": "20190503081000",
              "time": 1556842200000,
              "courseId": "1008",
              "courseName": "企业管理与技术经济",
              "courseNote": null,
              "attendanceTotalScore": 60,
              "leaveScore": 20,
              "attendScore": 20,
              "lateAttendScore": 10,
              "headUpScore": 40,
              "classroomId": "B135",
              "teacherId": "15",
              "teacherName": "龚东生"
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
url|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/thisSemester

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
            "id": "20190503081000",
            "time": 1556842200000,
            "courseId": "1008",
            "courseName": "企业管理与技术经济",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "B135",
            "teacherId": "15",
            "teacherName": "龚东生"
        },
        {
              "id": "20190503081000",
              "time": 1556842200000,
              "courseId": "1008",
              "courseName": "企业管理与技术经济",
              "courseNote": null,
              "attendanceTotalScore": 60,
              "leaveScore": 20,
              "attendScore": 20,
              "lateAttendScore": 10,
              "headUpScore": 40,
              "classroomId": "B135",
              "teacherId": "15",
              "teacherName": "龚东生"
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
url|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/oneday

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
            "id": "20190503081000",
            "time": 1556842200000,
            "courseId": "1008",
            "courseName": "企业管理与技术经济",
            "courseNote": null,
            "attendanceTotalScore": 60,
            "leaveScore": 20,
            "attendScore": 20,
            "lateAttendScore": 10,
            "headUpScore": 40,
            "classroomId": "B135",
            "teacherId": "15",
            "teacherName": "龚东生"
        },
        {
              "id": "20190503081000",
              "time": 1556842200000,
              "courseId": "1008",
              "courseName": "企业管理与技术经济",
              "courseNote": null,
              "attendanceTotalScore": 60,
              "leaveScore": 20,
              "attendScore": 20,
              "lateAttendScore": 10,
              "headUpScore": 40,
              "classroomId": "B135",
              "teacherId": "15",
              "teacherName": "龚东生"
          }
    ]
}
```
----
## 查询某一段时间的考勤信息
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73:8080/wisdom_web/studetnAttendance/getCourseOfStudentWithSpellTime

### _需传输的参数_
name|describe
----|------
start|开始时间  2019-03-04 00:00:00   
end|结束时间  2019-05-11 00:00:00  
courseId|课程id  1004         

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  （目前没有查不出来的情况）
msg|true
data|（List集合）课程考勤信息
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": [
        {
            "attendance_id": "20190401081000-41609050128",
            "courseitem_id": "20190401081000",
            "name": "算法设计与分析",
            "time": "2019-04-01 08:10:00.0",
            "status": "attend",
            "head_up_rate": "1",
            "goal": "60",
            "teacher_id": "12",
            "teacher_name": "李婷",
            "classroom_id": "D3102",
            "course_node": null,
            "student_id": null,
            "student_name": null
        },
        {
            "attendance_id": "20190405101000-41609050128",
            "courseitem_id": "20190405101000",
            "name": "算法设计与分析",
            "time": "2019-04-05 10:10:00.0",
            "status": "attend",
            "head_up_rate": "1",
            "goal": "60",
            "teacher_id": "12",
            "teacher_name": "李婷",
            "classroom_id": "C354",
            "course_node": null,
            "student_id": null,
            "student_name": null
        }
    ]
}
```
----

