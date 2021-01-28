<template>
  <el-dialog :title="title" :visible.sync="open.opens" width="40%" append-to-body>
    <el-form :model="form" :rules="rules" ref="form" label-position="right" label-width="80px">
      <el-form-item label="简历状态">
        <el-select v-model="form.updateStatic" placeholder="请选择简历状态">
          <el-option label="跟进中" :value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="跟进情况" prop="memoDetail">
          <el-input v-model="form.memoDetail" type="textarea" placeholder="请输入内容" style="width: 80%;"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="open.opens = false">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import{genzongbut}from'@/api/resume/mytrckresume.js'
  export default{
    name:"my",
    props: {
      open: {
        type: Object,
      },
      title: {
        type: String,
        default: ''
      },
      form:{
        type:Object,
      }
    },
    data(){
      return {
        rules:{
          memoDetail: [
          { required: true, message: "跟踪情况不能为空", trigger: "blur" }
        ],
        },
      }
    },
    methods:{
      submitForm(){
        this.$refs["form"].validate(valid => {
          if (valid) {
            genzongbut(this.form).then(res=>{
              this.msgSuccess("操作成功");
              this.open.opens = false;
              this.$emit("getlist")
            })
          }
        });
      },
    },
  }
</script>

<style>
</style>
