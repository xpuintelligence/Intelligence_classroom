import pymysql
import time


class LoadDataBase:

    def __init__(self, host, user, database, password):

        self.host = host
        self.user = user
        self.database = database
        self.password = password

    
    # 获取课程id
    def getCourseID(self):

        conn = pymysql.connect(host=self.host, user=self.user, password=self.password, database=self.database,
                               port=3306, charset="utf8")

        current_time = time.strftime('%Y-%m-%d %H:%M:%s', time.localtime(time.time()))
        current_time_Hour = time.strftime('%H', time.localtime(time.time()))
        current_time_Date = time.strftime('%Y-%m-%d ', time.localtime(time.time()))
        if current_time == '08' or current_time == '10':
            class_time = current_time_Date + current_time_Hour + ":10:00"

        sql = "select course_id from schedule_class where course_time = '%s';"

        data = (class_time)

        cursor = conn.cursor()

        try:
            couse_id = cursor.execute(sql % data)
            conn.commit()
            return couse_id
        except Exception as e:
            conn.rollback()

        finally:
            conn.close()

    # 班级抬头率
    def insertHeadUPSql(self, course_number_people, actual_number, headupRate):

        conn = pymysql.connect(host=self.host, user=self.user, password=self.password, database=self.database,
                               port=3306, charset="utf8")

        sql = "UPDATE `wisdom_classroom`.`schedule_class` SET `course_number_people` = '%s',`actual_number` = '%s', `course_headup` = '%s' WHERE (`course_id` = '%s');"

        course_id = self.getCourseID();

        data = (course_number_people, actual_number, headupRate, course_id)

        cursor = conn.cursor()

        try:
            cursor.execute(sql % data)
            conn.commit()
            print("插入成功")
        except Exception as e:
            conn.rollback()

        finally:
            conn.close()

    # 插入学生抬头率
    def setStudentHeadupJudge(self, student_id):

        conn = pymysql.connect(host=self.host, user=self.user, password=self.password, database=self.database,
                               port=3306, charset="utf8")

        sql = "UPDATE `wisdom_classroom`.`headup_info` SET `student_id` = '%s',`judge1` = '1' WHERE (`course_id` = '%s');"

        course_id = self.getCourseID();

        data = (student_id, course_id)

        cursor = conn.cursor()

        try:
            cursor.execute(sql % data)
            conn.commit()
            print("插入成功")
        except Exception as e:
            conn.rollback()

        finally:
            conn.close()
