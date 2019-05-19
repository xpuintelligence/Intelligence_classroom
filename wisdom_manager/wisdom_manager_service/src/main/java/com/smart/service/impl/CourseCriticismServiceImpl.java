package com.smart.service.impl;

import com.smart.mapper.TbAnswerQuestionMapper;
import com.smart.pojo.TbAnswerQuestion;
import com.smart.pojo.TbAnswerQuestionExample;
import com.smart.pojo.WisdomResult;
import com.smart.service.CourseCriticismService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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

    /**
     * 通过课程id获得该节课程的评论
     * @param courseId 课程id
     */
    @Override
    public WisdomResult getCriticismListForOneCourse(String courseId) {
        //设置查询
        TbAnswerQuestionExample tbAnswerQuestionExample = new TbAnswerQuestionExample();
        TbAnswerQuestionExample.Criteria criteria = tbAnswerQuestionExample.createCriteria();
        //先查询出根节点留言
        criteria.andFatherIdEqualTo(courseId);
        //查出了根节点的留言节点
        List<TbAnswerQuestion> tbAnswerQuestions = tbAnswerQuestionMapper.selectByExample(tbAnswerQuestionExample);
        //通过遍历把剩下的都查出来
        for (TbAnswerQuestion tbAnswerQuestion : tbAnswerQuestions) {

        }

        return null;
    }
}
