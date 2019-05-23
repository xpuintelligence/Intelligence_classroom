#!/usr/bin/env python
#coding=utf-8

from aliyunsdkcore.client import AcsClient
from aliyunsdkcore.request import CommonRequest

class FaceNotice:

    def notice(phone_number,student_name):
        client = AcsClient('LTAIN87V3L9IqotZ', 'DlBTCRZ3P8oVcLoinzNBHPydkMITit', 'default')

        request = CommonRequest()
        request.set_accept_format('json')
        request.set_domain('dysmsapi.aliyuncs.com')
        request.set_method('POST')
        request.set_protocol_type('https') # https | http
        request.set_version('2017-05-25')
        request.set_action_name('SendSms')

        request.add_query_param('PhoneNumbers', str(phone_number))
        request.add_query_param('SignName', "瑞克斯的博客系统")
        request.add_query_param('TemplateCode', "SMS_166095489")
        format = "{\"name\":\""+str(student_name)+"\"}"
        request.add_query_param('TemplateParam', format)

        response = client.do_action(request)
        # python2:  print(response)
        print(str(response, encoding = 'utf-8'))
