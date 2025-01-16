package com.px.service.service;

import java.util.List;

import com.px.service.domain.Inspection;

/**
 * 巡检问题Service接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface IInspectionService {
    /**
     * 查询巡检问题
     *
     * @param id 巡检问题主键
     * @return 巡检问题
     */
    public Inspection selectInspectionById(Long id);

    /**
     * 查询巡检问题列表
     *
     * @param inspection 巡检问题
     * @return 巡检问题集合
     */
    public List<Inspection> selectInspectionList(Inspection inspection);

    /**
     * 新增巡检问题
     *
     * @param inspection 巡检问题
     * @return 结果
     */
    public int insertInspection(Inspection inspection);

    /**
     * 修改巡检问题
     *
     * @param inspection 巡检问题
     * @return 结果
     */
    public int updateInspection(Inspection inspection);

    /**
     * 批量删除巡检问题
     *
     * @param ids 需要删除的巡检问题主键集合
     * @return 结果
     */
    public int deleteInspectionByIds(Long[] ids);

    /**
     * 删除巡检问题信息
     *
     * @param id 巡检问题主键
     * @return 结果
     */
    public int deleteInspectionById(Long id);

    /**
     * 巡检问题流程流转
     *
     * @param inspection 参数
     */
    void handleProcess(Inspection inspection);
}
