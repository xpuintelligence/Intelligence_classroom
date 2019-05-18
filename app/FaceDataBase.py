import pymysql
import time

class FaceDataBase:

    def __init__(self, host, user, database, password):

        self.host = host
        self.user = user
        self.database = database
        self.password = password

    
    # 获取人名
    def get_student_name(self, student_id):
        conn = pymysql.connect(host=self.host,user=self.user,password=self.password,database=self.database,port=3306,charset="utf8")
        sql = "select name from tb_student where id = '%s'"
        data = (student_id)
        cursor = conn.cursor()
        try:
            cursor.execute(sql%data)
            ret = cursor.fetchone()
            student_name = ret[0]
            return student_name
        except Exception as e:
            conn.rollback()
        finally:
            conn.close()

    # 获得当前班级所有学生的学号

    def get_student_id_all(self):
        student_id_class = []
        conn = pymysql.connect(host=self.host,user=self.user,password=self.password,database=self.database,port=3306,charset="utf8")
        sql = "select id from tb_student;"
        cursor = conn.cursor()
        try:
            cursor.execute(sql)
            results = cursor.fetchall()
            for student_id in results:
                student_id_class.append(student_id[0])
            return student_id_class
        except Exception as e:
            conn.rollback()
        finally:
            conn.close()

    # 获得学生的id
    def get_student_id(self,student_name):
        conn = pymysql.connect(host=self.host,user=self.user,password=self.password,database=self.database,port=3306,charset="utf8")
        sql = "select id from tb_student where name = '%s'";
        data = (student_name)
        cursor = conn.cursor()
        try:
            cursor.execute(sql%data)
            ret = cursor.fetchone()
            student_id= ret[0]
            return student_id
        except Exception as e:
            conn.rollback()
        finally:
            conn.close()



    # 获得当前班级所有学生的学号_debug 本地数据库
    def get_student_id_all_debug(self):
        student_id_class = []
        conn = pymysql.connect(host=self.host,user=self.user,password=self.password,database=self.database,port=3306,charset="utf8")
        sql = "select id from tb_student_debug1;"
        cursor = conn.cursor()
        try:
            cursor.execute(sql)
            results = cursor.fetchall()
            for student_id in results:
                student_id_class.append(student_id[0])
            return student_id_class
        except Exception as e:
            conn.rollback()
        finally:
            conn.close()



    # 获取课程id
    def getCourseID(self):
        conn = pymysql.connect(host=self.host,user=self.user,password=self.password,database=self.database,port=3306,charset="utf8")
        sql = "select id from tb_courseitem where time = '%s';"
        now_date = time.strftime('%Y-%m-%d 10:00:00')
        print(now_date)
        cursor = conn.cursor()
        try:
            cursor.execute(sql%now_date)
            ret = cursor.fetchone()
            courseitem_id= ret[0]
            return courseitem_id
        except Exception as e:
            conn.rollback()
        finally:
            conn.close()

    
    # 将不睡觉的学生插入数据库
    def attendance_insert_attend_nosleep(self,student_id_list,student_id_headuprate_dict,student_id_headuprate_score_dict):
        conn = pymysql.connect(host=self.host,user=self.user,password=self.password,database=self.database,port=3306,charset="utf8")
        sql = "INSERT INTO `team_model`.`tb_attendance` (`attendance_id`, `courseitem_id`, `student_id`, `create_date`, `status`, `head_up_rate`, `goal`, `sleep`, `headup_score`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')"
        courseitem_id = self.getCourseID()
        #courseitem_id = "20190606140000"
        create_date = time.strftime('%Y-%m-%d 10:00:00')
        #create_date = "2019-06-14 10:00:00"
        status = 'attend'
        cursor = conn.cursor()
        for student_id in student_id_list:
            attendance_id = courseitem_id+'-'+student_id
            head_up_rate = str(student_id_headuprate_dict[student_id])
            goal = '60'
            sleep = '0'
            headup_score = student_id_headuprate_score_dict[student_id]
            data = (attendance_id,courseitem_id,student_id,create_date,status,head_up_rate,goal,sleep,headup_score)
            try:
                cursor.execute(sql%data)
                conn.commit()
            except Exception as e:
                conn.rollback()

        conn.close()


    # 将睡觉的学生插入数据库
    def attendance_insert_attend_sleep(self, sleep_student_dict,student_id_headuprate_dict,student_id_headuprate_score_dict):

        conn = pymysql.connect(host=self.host,user=self.user,password=self.password,database=self.database,port=3306,charset="utf8")
        sql = "INSERT INTO `team_model`.`tb_attendance` (`attendance_id`, `courseitem_id`, `student_id`, `create_date`, `status`, `head_up_rate`, `goal`, `sleep`, `headup_score`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')"
        courseitem_id = self.getCourseID()
        create_date = time.strftime('%Y-%m-%d 10:00:00')
        status = 'attend'
        cursor = conn.cursor()
        for sleep_student_name in sleep_student_dict.keys():
            sleep_student_id = self.get_student_id(sleep_student_name)
            attendance_id = courseitem_id + '-' + sleep_student_id
            head_up_rate = str(student_id_headuprate_dict[sleep_student_id])
            goal = '60'
            sleep = '1'
            headup_score = student_id_headuprate_score_dict[sleep_student_id]
            data = (attendance_id, courseitem_id, sleep_student_id, create_date, status, head_up_rate, goal, sleep, headup_score)
            try:
                cursor.execute(sql % data)
                conn.commit()
            except Exception as e:
                conn.rollback()

        conn.close()

