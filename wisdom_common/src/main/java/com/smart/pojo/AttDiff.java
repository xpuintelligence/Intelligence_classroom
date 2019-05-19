package com.smart.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 俩个考勤区间进行划分
 */
@Data
public class AttDiff implements Serializable {
    private String thisGrade;//这次的分数
    private String thisHeadup;//这次的抬头率
    private String attDiff;//考勤分数不一样
    private String headupDiff;//抬头率不一样
}
