package com.smart;

import com.smart.pojo.WisdomResult;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

public class CourseAdvice {

    public WisdomResult checkParamAndResult(ProceedingJoinPoint joinPoint){
        //获取所有参数以
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //通过这获取到方法的所有参数名称的字符串数组
        String[] parameterNames = methodSignature.getParameterNames();

        try {
            //通过你需要获取的参数名称的下标获取到对应的值
            ArrayUtils.indexOf(parameterNames,"");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new WisdomResult();
    }
}
