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
        conn = pymysql.connect(host="127.0.0.1",user="root",password="123456",database="Wisdom_Class",port=3306,charset="utf8")
        sql = "select student_name from student_info where student_id = '%s'"
        data = (student_id)
        cursor = conn.cursor()
        #try:
        result=cursor.execute(sql%data)
        ret = cursor.fetchone()
        student_name = ret[0]
        print(student_name)
        #except Exception as e:
            #conn.rollback()
        #finally:
            #conn.close()