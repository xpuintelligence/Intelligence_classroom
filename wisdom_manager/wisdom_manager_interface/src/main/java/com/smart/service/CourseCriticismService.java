package com.smart.service;

import com.smart.pojo.TbAnswerQuestion;
import com.smart.pojo.WisdomResult;

import java.util.UUID;

public interface CourseCriticismService {

    /**
     * 给课程留言，父节点
     * param tbAnswerQuestion 课程的具体内容
     * @return
     */
    public WisdomResult addCriticism(TbAnswerQuestion tbAnswerQuestion);
}
