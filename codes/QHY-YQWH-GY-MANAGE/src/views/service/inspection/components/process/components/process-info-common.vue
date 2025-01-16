<template>
  <el-tabs type="border-card">
    <el-tab-pane :label="firstTitle" v-if="solutions.length > 0">
      <el-form size="small" label-position="left" label-width="80px" disabled>
        <el-row :gutter="20" v-for="(item, index) in solutions" :key="index">
          <el-col :span="5">
            <el-form-item label="日期">
              <el-date-picker
                  clearable
                  style="width: 100%;"
                  v-model="item.createTime"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="请选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="提交人">
              <el-input
                  v-model="item.nickName"
                  maxlength="200"
                  placeholder="请输入内容"/>
            </el-form-item>
          </el-col>
          <el-col :span="15">
            <el-form-item label="解决方案">
              <el-input
                  v-model="item.solution"
                  maxlength="200"
                  show-word-limit
                  placeholder="请输入内容"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-tab-pane>
    <el-tab-pane label="解决日志" v-if="solveLogs.length > 0">
      <el-form size="small" label-position="left" label-width="80px" disabled>
        <div v-for="(item, index) in solveLogs" :key="index">
          <el-row :gutter="20">
            <el-col :span="5">
              <el-form-item label="日期">
                <el-date-picker
                    clearable
                    style="width: 100%;"
                    v-model="item.solveDate"
                    type="date"
                    value-format="yyyy-MM-dd"
                    placeholder="请选择日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="提交人">
                <el-input
                    v-model="item.nickName"
                    maxlength="200"
                    placeholder="请输入内容"/>
              </el-form-item>
            </el-col>
            <el-col :span="15">
              <el-form-item label="解决日志">
                <el-input
                    type="textarea"
                    v-model="item.description"
                    maxlength="200"
                    show-word-limit
                    autosize
                    placeholder="请输入内容"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="日志图片">
            <image-upload
                ref="upload"
                v-model="item.image.imageUrl"
                :file-size="10"
                :is-show-tip="false"
                :limit="item.image.imageUrl.split(',').length"
            />
          </el-form-item>
        </div>
      </el-form>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
export default {
  name: 'process-info-common',
  props: {
    firstTitle: {
      type: String,
      default: '解决方案'
    },
    solutions: {
      type: Array,
      default() {
        return []
      }
    },
    solveLogs: {
      type: Array,
      default() {
        return []
      }
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
