import pymysql


student_id_class = []

class FaceDataBase:

    def __init__(self, host, user, database, password):

        self.host = host
        self.user = user
        self.database = database
        self.password = password

    
    # 获取人名
    def get_student_name(self, student_id):
        conn = pymysql.connect(host=self.host,user=self.user,password=self.password,database=self.database,port=3306,charset="utf8")
        sql = "select student_name from student_info where student_id = '%s'"
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
        conn = pymysql.connect(host=self.host,user=self.user,password=self.password,database=self.database,port=3306,charset="utf8")
        sql = "select student_id from student_info"
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

    def attendence_insert(self, attendence, student_id):
        conn = pymysql.connect(host=self.host,user=self.user,password=self.password,database=self.database,port=3306,charset="utf8")
        sql = "UPDATE `wisdom_class`.`student_info` SET `attendence` = '%s' WHERE (`student_id` = '%s');"
        cursor = conn.cursor()
        data = (attendence, student_id)
        #try:
        cursor.execute(sql%data)
        conn.commit()
        #except Exception as e:
        conn.rollback()
        #finally:
        conn.close()