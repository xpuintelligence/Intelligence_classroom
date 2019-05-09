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

    # 获得当前班级所有学生的学号_debug 本地数据库
    def get_student_id_all_debug(self):
        student_id_class = []
        conn = pymysql.connect(host=self.host,user=self.user,password=self.password,database=self.database,port=3306,charset="utf8")
        sql = "select id from tb_student_debug2;"
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


    # 学生出勤统计 出勤插入1 未出勤插入0

    def attendence_insert(self, student_id):
        conn = pymysql.connect(host=self.host,user=self.user,password=self.password,database=self.database,port=3306,charset="utf8")
        sql = "INSERT INTO `team_model`.`tb_attendance` (`attendance_id`, `courseitem_id`, `student_id`, `status`) VALUES ('%s','%s', '%s', 'attend');"
        # INSERT INTO `team_model`.`tb_attendance` (`attendance_id`, `courseitem_id`, `student_id`, `status`) VALUES ('2019-4-30 20:29', '100120190422101000', '41609030209', 'attend');
        cursor = conn.cursor()
        attendence_id = time.strftime('%Y-%m-%d %H:%M')
        courseitem_id = "100120190422101000"
        data = (attendence_id, courseitem_id, student_id)
        try:
            cursor.execute(sql%data)
            conn.commit()
        except Exception as e:
            conn.rollback()
        finally:
            print("连接关闭")
            conn.close()