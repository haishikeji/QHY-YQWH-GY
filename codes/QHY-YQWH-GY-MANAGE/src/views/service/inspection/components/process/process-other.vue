<template>
  <div
      v-if="!task.nodeKey || $store.state.user.userId == task.assignee || solutions.length > 0 || solveLogs.length > 0">
    <!-- 非新增才有处理过程资料 -->
    <process-info-common :solutions="solutions" :solve-logs="solveLogs"
                         v-if="task.nodeKey && (solutions.length > 0 || solveLogs.length > 0)"></process-info-common>
    <!-- 技工发起的新增或者流程未结束并且是当前环节处理人才能执行操作 -->
    <el-card v-if="(mechanic && !task.nodeKey) || (task.status !== 1 && $store.state.user.userId == task.assignee)"
             style="margin-top: 16px">
      <!-- 内业分配环节 -->
      <el-form v-if="task.nodeKey === NODE_INTERNAL_DISTRIBUTION" :model="form" :rules="rules" ref="form"
               size="small" hide-required-asterisk
               label-position="left" label-width="80px">
        <el-form-item label="操作" prop="operateParam.action">
          <el-radio-group v-model="form.operateParam.action">
            <el-radio :key="0" :label="0">分配技工处理</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="处理人" prop="operateParam.assigneeId">
          <el-select v-model="form.operateParam.assigneeId" placeholder="请选择处理人" filterable clearable>
            <el-option
                v-for="assignee in assigneeList"
                :key="assignee.userId"
                :label="assignee.nickName"
                :value="assignee.userId"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <!-- 技工发起且未提交或者技工处理环节 -->
      <el-form v-else-if="(mechanic && !task.nodeKey) || task.nodeKey === NODE_MECHANIC_HANDLE" :model="form"
               :rules="rules" ref="form"
               size="small" hide-required-asterisk
               label-position="left" label-width="80px">
        <el-form-item label="操作" prop="operateParam.action">
          <el-radio-group v-model="form.operateParam.action" @change="onActionChange">
            <el-radio :key="0" :label="0">下发班组或劳务单位处理</el-radio>
            <el-radio :key="1" :label="1" v-if="task.nodeKey === NODE_MECHANIC_HANDLE">上报协调</el-radio>
            <el-radio :key="-1" :label="-1" v-if="!mechanic">驳回分配</el-radio>
          </el-radio-group>
        </el-form-item>
        <!-- 下发处理才需要选择处理人，上报协调则直接流转到所属管理站站长 -->
        <el-form-item label="处理人" prop="operateParam.assigneeId"
                      v-if="(mechanic && !task.nodeKey) || form.operateParam.action === 0">
          <el-select v-model="form.operateParam.assigneeId" placeholder="请选择处理人" filterable clearable>
            <el-option
                v-for="assignee in assigneeList"
                :key="assignee.userId"
                :label="assignee.nickName"
                :value="assignee.userId"
            />
          </el-select>
        </el-form-item>
        <el-form-item :label="form.operateParam.action === 1? '协调原因' : '驳回原因'" prop="operateParam.description"
                      v-if="task.nodeKey === NODE_MECHANIC_HANDLE && (form.operateParam.action === 1 || form.operateParam.action === -1)">
          <el-input
              v-model="form.operateParam.description"
              type="textarea"
              maxlength="200"
              show-word-limit
              :autosize="{ minRows: 4, maxRows: 8}"
              placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <!-- 班组或劳务单位处理问题环节 -->
      <el-form v-else-if="task.nodeKey === NODE_HANDLE_PROBLEM" :model="form" :rules="rules" ref="form"
               size="small" hide-required-asterisk
               label-position="left" label-width="80px">
        <el-form-item label="操作" prop="operateParam.action">
          <el-radio-group v-model="form.operateParam.action" @change="onActionChange">
            <el-radio :key="0" :label="0">处理完成</el-radio>
            <el-radio :key="1" :label="1">驳回</el-radio>
          </el-radio-group>
        </el-form-item>
        <solve-log-common ref="solveLogCommon" :solveLogCommon="form.solveLogCommon"
                          v-if="form.operateParam.action === 0"></solve-log-common>
        <el-form-item label="驳回原因" prop="operateParam.description" v-if="form.operateParam.action === 1">
          <el-input
              v-model="form.operateParam.description"
              type="textarea"
              maxlength="200"
              show-word-limit
              :autosize="{ minRows: 4, maxRows: 8}"
              placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <!-- 技工审核环节 -->
      <el-form
          v-else-if="task.nodeKey === NODE_MECHANIC_VERIFY"
          :model="form" :rules="rules" ref="form"
          size="small" hide-required-asterisk
          label-position="left" label-width="80px">
        <el-form-item label="审核" prop="operateParam.action">
          <el-radio-group v-model="form.operateParam.action" @change="onActionChange">
            <el-radio :key="0" :label="0">通过</el-radio>
            <el-radio :key="1" :label="1">驳回</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="驳回原因" prop="operateParam.description" v-if="form.operateParam.action === 1">
          <el-input
              v-model="form.operateParam.description"
              type="textarea"
              maxlength="200"
              show-word-limit
              :autosize="{ minRows: 4, maxRows: 8}"
              placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <!-- 站长审批、部门经理审批环节 -->
      <el-form
          v-else-if="task.nodeKey === NODE_STATION_APPROVAL || task.nodeKey === NODE_DEPARTMENT_MANAGER_APPROVAL"
          :model="form" :rules="rules" ref="form"
          size="small" hide-required-asterisk
          label-position="left" label-width="80px">
        <el-form-item label="审批" prop="operateParam.action">
          <el-radio-group v-model="form.operateParam.action" @change="onActionChange">
            <el-radio :key="0" :label="0">通过</el-radio>
            <el-radio :key="1" :label="1">驳回</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="处理意见" prop="operateParam.description" v-if="task.nodeKey === NODE_DEPARTMENT_MANAGER_APPROVAL && form.operateParam.action === 0">
          <el-input
              v-model="form.operateParam.description"
              type="textarea"
              maxlength="200"
              show-word-limit
              :autosize="{ minRows: 4, maxRows: 8}"
              placeholder="请输入内容"/>
        </el-form-item>
        <solution-common :solution-common="form.solutionCommon" v-if="form.operateParam.action === 1"></solution-common>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import mixin from './mixins/process'
import processInfoCommon from './components/process-info-common'
import solutionCommon from './components/solution-common'
import solveLogCommon from './components/solve-log-common'

export default {
  name: 'process-other',
  mixins: [mixin],
  components: {
    processInfoCommon,
    solutionCommon,
    solveLogCommon
  },
  data() {
    return {
      type: this.$constants.PROBLEM_TYPE_OTHER,
      ruleAssignee: ['operateParam.assigneeId'], // 需要选择处理人时的验证规则,
      ruleReject: ['operateParam.description'],// 当前环节驳回验证规则
      ruleApprovalReject: ['solutionCommon.solution'],// 上报协调审批驳回验证规则
      rules: {
        'operateParam.assigneeId': [
          { required: true, message: '处理人不能为空', trigger: 'blur' }
        ],
        'operateParam.description': [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    /* 操作变化监听 */
    onActionChange(e) {
      this.$refs.form.clearValidate()
      this.reset(Number(e))
      this.getAssigneeList()
    },
    /* 内业分配环节 */
    internalDistribution() {
      return [].concat(this.ruleAssignee)
    },
    /* 技工处理环节 */
    mechanicHandle() {
      if (this.form.operateParam.action === 0) {
        // 下发处理
        this.form.operateParam.description = undefined
        return [].concat(this.ruleAssignee)
      } else if (this.form.operateParam.action === 1 || this.form.operateParam.action === -1) {
        // 上报协调或驳回分配
        this.form.operateParam.assigneeId = undefined
        return [].concat(this.ruleReject)
      }
    },
    /* 班组或劳务单位处理问题环节 */
    handleProblem() {
      if (this.form.operateParam.action === 0) {
        // 处理完成
        let validList = []
        for (let i = 0; i < this.form.solveLogCommon.length; i++) {
          validList.push('solveLogCommon[' + i + '].solveDate')
          validList.push('solveLogCommon[' + i + '].description')
          validList.push('solveLogCommon[' + i + '].image.imageUrl')
        }
        this.form.operateParam.description = undefined
        return validList
      } else if (this.form.operateParam.action === 1) {
        // 驳回申请
        this.form.solveLogCommon = []
        return [].concat(this.ruleReject)
      }
    },
    /* 技工审核环节 */
    mechanicVerify() {
      if (this.form.operateParam.action === 0) {
        // 通过
        this.form.operateParam.description = undefined
        return []
      } else if (this.form.operateParam.action === 1) {
        // 驳回
        return [].concat(this.ruleReject)
      }
    },
    /* 站长审批环节 */
    stationApproval() {
      if (this.form.operateParam.action === 0) {
        // 通过
        return []
      } else if (this.form.operateParam.action === 1) {
        // 驳回
        return [].concat(this.ruleApprovalReject)
      }
    },
    /* 部门经理审批环节 */
    departmentManagerApproval() {
      if (this.form.operateParam.action === 0) {
        // 通过
        return [].concat(this.ruleReject)
      } else if (this.form.operateParam.action === 1) {
        // 驳回
        return [].concat(this.ruleApprovalReject)
      }
    },
    /* 验证表单 */
    submit() {
      if (!this.mechanic && !this.task.nodeKey) {
        // 非技工发起且未提交直接返回验证通过
        return new Promise(resolve => {
          resolve(this.form)
        })
      }
      this.$refs.form.clearValidate()
      let validList = []
      if (this.mechanic && !this.task.nodeKey) {
        // 技工发起且未提交
        validList = [].concat(this.ruleAssignee)
      } else {
        switch (this.task.nodeKey) {
          case this.NODE_INTERNAL_DISTRIBUTION:
            validList = this.internalDistribution()
            break
          case this.NODE_MECHANIC_HANDLE:
            validList = this.mechanicHandle()
            break
          case this.NODE_HANDLE_PROBLEM:
            validList = this.handleProblem()
            break
          case this.NODE_MECHANIC_VERIFY:
            validList = this.mechanicVerify()
            break
          case this.NODE_STATION_APPROVAL:
            validList = this.stationApproval()
            break
          case this.NODE_DEPARTMENT_MANAGER_APPROVAL:
            validList = this.departmentManagerApproval()
            break
        }
      }
      let validateFieldList = []
      return new Promise(resolve => {
        if (validList.length <= 0) {
          this.form.operateParam.curNodeKey = this.task.nodeKey
          resolve(this.form)
        } else {
          this.$refs['form'].validateField(validList, e => {
            if (!e) {
              validateFieldList.push(e)
              if (validateFieldList.length === validList.length && validateFieldList.every(item => !item)) {
                this.form.operateParam.curNodeKey = this.task.nodeKey
                resolve(this.form)
              }
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
