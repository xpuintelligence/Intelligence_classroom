import requests


url = "http://47.103.14.73:8080/wisdom_web/monitorStudent/setStudentStatus"
# 1为睡觉
# body = {"status":'1', "id":student_id}
# body = {"status":'1', "id":'41609030216'}
body = {"id":'1'}
response = requests.post(url,data=body)
print(response.text)

print(response.status_code)