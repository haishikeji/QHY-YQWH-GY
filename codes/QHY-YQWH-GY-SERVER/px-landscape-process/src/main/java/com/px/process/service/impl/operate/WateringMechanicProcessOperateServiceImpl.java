package com.px.process.service.impl.operate;

import com.px.common.exception.ServiceException;
import com.px.common.utils.DateUtils;
import com.px.process.domain.ProcessIns;
import com.px.process.domain.RunTask;
import com.px.process.domain.TaskHis;
import org.springframework.stereotype.Component;

/**
 * @author: Alvin
 * @date: Created in 2022/7/12
 * @description: 浇水问题流程-技工
 */
@Component("WateringMechanicProcessOperateServiceImpl")
public class WateringMechanicProcessOperateServiceImpl extends WateringProcessOperateServiceImpl {

    @Override
    public void startProcess(ProcessIns processIns, Long station, Long assigneeId) {
        if (assigneeId == null) {
            throw new ServiceException("未指定下一环节处理人");
        }
        // 插入运行中任务
        RunTask runTask = new RunTask();
        runTask.setProcessDefId(processIns.getProcessDefId());
        runTask.setProcessInsId(processIns.getId());
        runTask.setName("问题处理");
        runTask.setNodeKey(NODE_HANDLE_PROBLEM);
        runTask.setBusinessId(processIns.getBusinessId());
        runTask.setBusinessType(processIns.getBusinessType());
        runTask.setAssignee(assigneeId.toString());
        runTask.setCreateTime(DateUtils.getNowDate());
        mRunTaskMapper.insertRunTask(runTask);
        // 插入历史任务记录
        TaskHis startTask = new TaskHis();
        startTask.setProcessDefId(processIns.getProcessDefId());
        startTask.setProcessInsId(processIns.getId());
        startTask.setName("提交问题");
        startTask.setNodeKey(NODE_START);
        startTask.setType(0L);
        startTask.setBusinessId(processIns.getBusinessId());
        startTask.setBusinessType(processIns.getBusinessType());
        startTask.setAssignee(processIns.getStartUserId().toString());
        startTask.setStartTime(DateUtils.getNowDate());
        startTask.setEndTime(DateUtils.getNowDate());
        mTaskHisMapper.insertTaskHis(startTask);
        TaskHis mechanicTask = new TaskHis();
        mechanicTask.setProcessDefId(processIns.getProcessDefId());
        mechanicTask.setProcessInsId(processIns.getId());
        mechanicTask.setName("技工处理");
        mechanicTask.setNodeKey(NODE_MECHANIC_HANDLE);
        mechanicTask.setType(0L);
        mechanicTask.setBusinessId(processIns.getBusinessId());
        mechanicTask.setBusinessType(processIns.getBusinessType());
        mechanicTask.setAssignee(processIns.getStartUserId().toString());
        mechanicTask.setStartTime(DateUtils.getNowDate());
        mechanicTask.setEndTime(DateUtils.getNowDate());
        mTaskHisMapper.insertTaskHis(mechanicTask);
        TaskHis distributeAction = new TaskHis();
        distributeAction.setProcessDefId(processIns.getProcessDefId());
        distributeAction.setProcessInsId(processIns.getId());
        distributeAction.setName("下发处理");
        distributeAction.setType(1L);
        distributeAction.setBusinessId(processIns.getBusinessId());
        distributeAction.setBusinessType(processIns.getBusinessType());
        distributeAction.setAssignee(processIns.getStartUserId().toString());
        distributeAction.setStartTime(DateUtils.getNowDate());
        distributeAction.setEndTime(DateUtils.getNowDate());
        mTaskHisMapper.insertTaskHis(distributeAction);
        TaskHis lastTask = new TaskHis();
        lastTask.setProcessDefId(processIns.getProcessDefId());
        lastTask.setProcessInsId(processIns.getId());
        lastTask.setName("问题处理");
        lastTask.setNodeKey(NODE_HANDLE_PROBLEM);
        lastTask.setType(0L);
        lastTask.setBusinessId(processIns.getBusinessId());
        lastTask.setBusinessType(processIns.getBusinessType());
        lastTask.setAssignee(assigneeId.toString());
        lastTask.setStartTime(DateUtils.getNowDate());
        mTaskHisMapper.insertTaskHis(lastTask);
    }
}
