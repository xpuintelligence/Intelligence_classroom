package com.smart.service.impl;

import com.smart.mapper.TbAnswerQuestionMapper;
import com.smart.pojo.TbAnswerQuestion;
import com.smart.pojo.WisdomResult;
import com.smart.service.CourseCriticismService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public  class CourseCriticismServiceImpl implements CourseCriticismService {

    @Autowired
    TbAnswerQuestionMapper tbAnswerQuestionMapper;
    /**
     * 添加一条留言
     * @param tbAnswerQuestion
     * @return
     */
    @Override
    public WisdomResult addCriticism(TbAnswerQuestion tbAnswerQuestion) {
        //生成一条id
        UUID uuid = UUID.randomUUID();
        tbAnswerQuestion.setId(uuid.toString());
        int i = tbAnswerQuestionMapper.insert(tbAnswerQuestion);
        if (i == 1){
            return WisdomResult.ok();
        }
        return WisdomResult.errot("留言失败");
    }
}
