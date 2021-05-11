package com.ruoyi.analysis.mapper;

import com.ruoyi.analysis.domain.ProjectPersonnel;

import java.util.List;

/**
 * @Description: 项目人员统计
 * @Author ych
 * @Date 2021/4/22
 */
public interface ProjectPersonnelMapper {

    List<ProjectPersonnel> getAllCorpName(ProjectPersonnel projectPersonnel);

    List<ProjectPersonnel> getAcurrentDateList(ProjectPersonnel projectPersonnel);

    List<ProjectPersonnel> getnMonthlastList(ProjectPersonnel projectPersonnel);

    List<ProjectPersonnel> getnlastYearDateList(ProjectPersonnel projectPersonnel);
}
