package com.px.service.service;

import java.util.List;
import com.px.service.domain.DrugInventory;

/**
 * 药品库存Service接口
 * 
 * @author 品讯科技
 * @date 2024-08
 */
public interface IDrugInventoryService
{
    /**
     * 查询药品库存
     * 
     * @param id 药品库存主键
     * @return 药品库存
     */
    public DrugInventory selectDrugInventoryById(Long id);

    /**
     * 查询药品库存列表
     * 
     * @param drugInventory 药品库存
     * @return 药品库存集合
     */
    public List<DrugInventory> selectDrugInventoryList(DrugInventory drugInventory);

    /**
     * 新增药品库存
     * 
     * @param drugInventory 药品库存
     * @return 结果
     */
    public int insertDrugInventory(DrugInventory drugInventory);

    /**
     * 修改药品库存
     * 
     * @param drugInventory 药品库存
     * @return 结果
     */
    public int updateDrugInventory(DrugInventory drugInventory);

    /**
     * 批量删除药品库存
     * 
     * @param ids 需要删除的药品库存主键集合
     * @return 结果
     */
    public int deleteDrugInventoryByIds(Long[] ids);

    /**
     * 删除药品库存信息
     * 
     * @param id 药品库存主键
     * @return 结果
     */
    public int deleteDrugInventoryById(Long id);

    /**
     * 批量插入药品库存信息
     * @param drugInventories 库存信息
     * @return 结果
     */
    int insertListDrugInventories(List<DrugInventory> drugInventories);

}
