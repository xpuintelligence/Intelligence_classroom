后台API文档
===========
## 1.4后台api版本更新
+ 查询开学到现在的每日统计考勤以及统计这段时间的统计考勤  添加了分页
+ 修复了教室登陆返回1的bug
+ 修复了数据库无事务提交的bug
+ 修复了app端课程信息不显示的bug
----------
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
           "id": "41609050128", //学生id
           "name": "宁大力", //学生姓名
           "sex": "male", //学生性别
           "phone": "", //学生电话号码
           "email": null, //学生邮箱
           "classId": "080902201601", //班级id
           "className": "软件1班", //班级名称
           "professionId": "080902", //专业id
           "professionName": "软件工程", //专业名称
           "collegeId": "06", //学院id
           "collegeName": "计算机科学学院", //学院名称
           "weixinId": null //微信号
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
        "id": "41609050128", //学生id
        "name": "宁大力", //姓名
        "sex": "male", //性别
        "phone": "", //电话号码
        "email": null, //电子邮箱
        "classId": "080902201601", //班级id
        "className": "软件1班", //班级名称
        "professionId": "080902", //专业id
        "professionName": "软件工程", //专业名称
        "collegeId": "06", //学院名称
        "collegeName": "计算机科学学院", 学院名称
        "weixinId": null} //微信号
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
        "id": "41609050128", //学生id
        "name": "宁大力", //姓名
        "sex": "male", //性别
        "phone": "", //电话号码
        "email": null, //电子邮箱
        "classId": "080902201601", //班级id
        "className": "软件1班", //班级名称
        "professionId": "080902", //专业id
        "professionName": "软件工程", //专业名称
        "collegeId": "06", //学院id
        "collegeName": "计算机科学学院", //学院名称
        "weixinId": null} //微信号
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
            "id": "20190503081000", //这节课的id
            "time": 1556842200000, //上课时间
            "courseId": "1008", //课程id
            "courseName": "企业管理与技术经济",//课程名称
            "courseNote": null,//课程备注
            "attendanceTotalScore": 60,//考勤总分
            "leaveScore": 20,//请假分数
            "attendScore": 20,//出席分数
            "lateAttendScore": 10,//迟到分数
            "headUpScore": 40,//抬头总分
            "classroomId": "B135",//教室id
            "teacherId": "15",//教师id
            "teacherName": "龚东生"//教师姓名
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
### _类似请求，返回值类似_
name|method|url|requestParams|describe
----|------|---|----|--------
查询本月的课程信息|post|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/thisMonth|无|无
查询一段时间内的课程信息|post|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/getASpellTime|begin-->开始时间  end-->结束时间|无
查询相对于本周而言，前x周的考勤 |post|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/frontXWeek|n-->前n周|无
查询相对于本月而言，前n月的课程信息 |post|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/frontXMonth|n-->前n月|无
查询本学期第x个月的课程信息|post|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/xMonth|n-->第n月|无
查询本学期第x个周的课程信息|post|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/xWeek|n-->第n月|无
查询固定一天的课程信息|post|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/oneday|dateTime-->某天的日期|无
查询本学期所有的课程信息|post|http://47.103.14.73:8080/wisdom_web/studentCourseInfo/thisSemester|无|无
----
## 查询某一段时间的考勤信息
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73:8080/wisdom_web/studetnAttendance/getCourseOfStudentWithSpellTime
api说明|该为每节课的考勤信息+每节课的详细信息包括打分情况
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
            "attendance_id": "20190401081000-41609050128",//考勤id
            "courseitem_id": "20190401081000",//考勤的那节课的id
            "name": "算法设计与分析",//那节课的课程名
            "time": "2019-04-01 08:10:00.0",//上课时间
            "status": "attend",//到勤状态
            "head_up_rate": "1",//抬头率
            "goal": "60",//考勤得分
            "teacher_id": "12",//老师id
            "teacher_name": "李婷",//老师姓名
            "classroom_id": "D3102",//教室id
            "course_node": null,//课程备注
            "student_id": null,//学生id
            "student_name": null//学生姓名
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
### _类似请求，返回值类似_
name|method|url|requestParams|describe
----|------|---|----|--------
查询某段时间的具体某一节课的课程信息|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/getCourseOfStudentWithSpellTime|start-->开始时间  end-->结束时间  courseId-->课程id|通过传输来的课程id，与起始时间计算出该学生这段时间内该课程的考勤信息
查询本周某一节课的考勤信息|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/oneCourseAttThisWeek|courseId-->课程id|无
查询上一周某一节课的考勤信息|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/oneCourseAttLastWeek|courseId-->课程id|无
查询本月某一节课的考勤信息|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/oneCourseAttThisMonth|courseId-->课程id|无
查询上一月某一节课的考勤信息|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/oneCourseAttLastMonth|courseId-->课程id|无
查询这个学期的某一节课的课程信息|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/oneCourseAttThisSemester|courseId-->课程id|无
----
## 查询该学生一段时间内的所有课程的考勤信息
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73:8080/wisdom_web/studetnAttendance/allCourseAttASpellTime
api说明|该为每节课的考勤信息
### _需传输的参数_
name|describe
----|------
start|开始时间  2019-03-04 00:00:00   
end|结束时间  2019-05-11 00:00:00      

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  （目前5月份的数据没插，查不出来）
msg|true
data|（List集合）课程考勤信息
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": [
        {
            "attendance_id": "20190401081000-41609050128",//考勤的id
            "courseitem_id": "20190401081000",//这节课的id
            "name": "算法设计与分析",//这节课的课程名
            "time": "2019-04-01 08:10:00.0",//上课时间
            "status": "attend",//道勤状态
            "head_up_rate": "1",//抬头率
            "goal": "60",//考勤得分
            "teacher_id": "12",//教师id
            "teacher_name": "李婷",//教师姓名
            "classroom_id": "D3102",//教室id
            "course_node": null,//课程备注
            "student_id": null,//学生id
            "student_name": null//学生名称
        },
        {
            "attendance_id": "20190401101000-41609050128",
            "courseitem_id": "20190401101000",
            "name": "信息安全技术",
            "time": "2019-04-01 10:10:00.0",
            "status": "attend",
            "head_up_rate": "1",
            "goal": "60",
            "teacher_id": "13",
            "teacher_name": "陈亮",
            "classroom_id": "D3102",
            "course_node": null,
            "student_id": null,
            "student_name": null
        }
    ]
}

```
### _类似请求，返回值类似_
name|method|url|requestParams|describe
----|------|---|----|--------
查询该学生开学到现在内的所有课程的考勤信息|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/allCourseAttThisSemester|无|无
查询该学生本周内的所有课程的考勤信息|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/allCourseAttThisWeek|无|无
查询该学生上周内的所有课程的考勤信息|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/allCourseAttLastWeek|无|无
查询该学生本月内的所有课程的考勤信息|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/allCourseAttThisMonth|无|无
查询该学生上月内的所有课程的考勤信息|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/allCourseAttLastMonth|无|无
----
## 学生查询自己的每天的考勤平均分，平均抬头，有几节课，率等（以下统称为每日统计考勤）
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73:8080/wisdom_web/studetnAttendance/getProAttOfST
api说明|查询一段时间内的每日统计考勤，以及所有天的平均值
### _需传输的参数_
name|describe
----|------
start|开始时间  2019-03-04 00:00:00   
end|结束时间  2019-05-11 00:00:00      

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功  （目前5月份的数据没插，查不出来）
msg|true
data|这段时间的每日统计考勤的集合
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": {
        "startTime": null,--->开始时间
        "endTime": null,--->结束时间
        "num": 3,--->一共多少天的考勤，就是结束时间与开始时间中间有多少天
        "headUpRateAverage": "0.55",--->num天的平均抬头率
        "list": [--->每一个小集合是每一天的统计考勤
            {
                "date": "2019-04-01",--->当天日期
                "sum": "2",--->有几节课
                "avg_head_up_rate": "1",--->这一天的抬头率
                "avgGoal": "60.0000"--->这一天的平均分
            },
            {
                "date": "2019-04-02",
                "sum": "3",
                "avg_head_up_rate": "1",
                "avgGoal": "60.0000"
            },
            {
                "date": "2019-04-03",
                "sum": "1",
                "avg_head_up_rate": "1",
                "avgGoal": "60.0000"
            }
        ],
        "attendanceGoalAverage": "33.10"--->num天的平均考勤分数
    }
}
```
### _类似请求，返回值类似_
name|method|url|requestParams|describe
----|------|---|----|--------
查询本周的每日统计考勤以及统计这段时间的统计考勤|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/getProAttOfTW|无|无
查询上周的每日统计考勤以及统计这段时间的统计考勤|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/getProAttOfLW|无|无
查询本月的每日统计考勤以及统计这段时间的统计考勤|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/getProAttOfTM|无|无
查询上月的每日统计考勤以及统计这段时间的统计考勤|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/getProAttOfLM|无|无
查询某天的每日统计考勤|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/getProAttOfOD|oneday-->某一天的时间(2018-05-12 00:00:00)|无
查询今天的每日统计考勤|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/getProAttOfT|无|无
查询开学到现在的每日统计考勤以及统计这段时间的统计考勤|post|http://47.103.14.73:8080/wisdom_web/studetnAttendance/getProAttOfTS|page-->第几页（没有该参数默认为第1页）size-->页面几行信息（默认15）|可以使用分页
----
## 给某个固定的学生发响应的信息
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73:8080/wisdom_web/monitorStudent/setStudentStatus
api说明|给服务器发送一个消息加入到消息队列里面
### _需传输的参数_
name|describe
----|------
msg|要发送的消息
id|要给哪一个学生发送消息
status|状态值

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功 0--->失败
msg|true
data|
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": {}
     
}
```
----
## 给某个固定的学生发响应的信息
### _请求方式与url_
name|describe
----|------
method|post
url|http://47.103.14.73:8080/wisdom_web/monitorStudent/getStudentMsg
api说明|获取服务器消息队列中某个学生的消息
### _需传输的参数_
name|describe
----|------
id|学生的id

### _返回值_
name|describe
----|------
status|返回值状态 1--->成功 0--->失败
msg|true
data|具体的消息
### 返回值样本
```json
{
    "status": 1,
    "msg": "true",
    "data": {
        "id": "41609050201",--->该学生的学号
        "status": "1",--->状态值
        "msg": null--->消息字段
    }
}
```
----

