<template>
  <el-form-item :label="title" prop="solutionCommon.solution"
                :rules="{ required: true, message: title + '不能为空'}">
    <el-input
        v-model="solutionCommon.solution"
        type="textarea"
        maxlength="200"
        show-word-limit
        :autosize="{ minRows: 4, maxRows: 8}"
        :placeholder="'请输入' + title"/>
  </el-form-item>
</template>

<script>
export default {
  name: 'solution-common',
  props: {
    title: {
      type: String,
      default: '解决方案'
    },
    solutionCommon: {
      type: Object,
      default() {
        return {}
      }
    }
  }
}
</script>

<style scoped>

</style>
