<template>
  <div class="app-container">
    <div style="margin-bottom: 30px;display: flex;justify-content: space-between;">
      <div style="width: 25%;">
        <p><b>录入解析简历</b></p>
      </div>
      <div style="display: flex;flex-wrap: wrap; width: 60%;justify-content: flex-end;">
        <div>
          <label>电话 </label>&nbsp;<input type="text" class="input" v-model="finddata.phon">
        </div>
        <div>
          <label>姓名 </label>&nbsp;<input type="text" class="input" v-model="finddata.name">
        </div>
        <button class="but" @click="find">查询</button>
      </div>
    </div>
    <div style="margin-bottom:40px;">
         <input type="file" ref="files" >

         <button class="but" @click="jiexii">解析</button>
    </div>
    <div>
      <p><h4><b>姓名/电话</b></h4></p>
      <div>

          <el-form label-width="68px" :inline="true">
              <el-form-item label="性别" prop="sex">
                <el-input
                  v-model="queryParams.sex"
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <el-form-item label="出生年月" prop="birthday">
                <el-input
                  v-model="queryParams.birthday"
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <el-form-item label="毕业学院" prop="school">
                <el-input
                  v-model="queryParams.school"
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <el-form-item label="学历" prop="education">
                <el-input
                  v-model="queryParams.education"
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <el-form-item label="期望薪资" prop="pay">
                <el-input
                  v-model="queryParams.pay"
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <el-form-item label="技术方向" prop="direction">
                <el-input
                  v-model="queryParams.direction"
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <br/>
              <el-form-item label="工作经验" prop="workjingyan">
                <el-input
                style="width: 300px;"
                 type="textarea"
                  v-model="queryParams.workjingyan"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <br>
              <el-form-item label="工作经历" prop="workjingli">
                <el-input
                style="width: 300px;"
                 type="textarea"
                  v-model="queryParams.workjingli"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <br/>
              <el-form-item label="项目经历" prop="xiangmu">
                <el-input
                style="width: 500px;"
                 type="textarea"
                  v-model="queryParams.xiangmu"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <br>
              <el-form-item>
                  <el-button  size="medium " @click="handleQuery" class="but">取消</el-button>
                  <el-button size="medium " @click="resetQuery" class="but">保存</el-button>
                </el-form-item>
              </el-form>
         </el-form>
      </div>

    </div>
    <el-dialog :visible.sync="open" style="width: 90%;">
        <div v-if="sous" style="height: 400px;">
          <h3><p><b>简历查询-查询不到结果</b></p></h3>
          <div style="margin-top:200px" align="center">未查询到人员信息</div>
        </div>
        <div v-else >
          <h4><p><b>简历查询-可查询到结果</b></p></h4>
          <el-table
              :data="tableData"
              border
              style="width: 100%">
              <el-table-column
                prop="date"
                label="姓名电话"
                width="150">
              </el-table-column>
              <el-table-column
                prop="name"
                label="出生日期"
                width="140">
              </el-table-column>
              <el-table-column
                prop="province"
                label="技术方向"
                width="160">
              </el-table-column>
              <el-table-column
                prop="city"
                label="录入/更新简历时间"
                width="150">
              </el-table-column>
              <el-table-column
                prop="address"
                label="占有人"
                width="120">
              </el-table-column>
              <el-table-column

                label="详情"
                width="100">
                <template slot-scope="scope">
                 <el-button @click="handleClick()" type="text" size="small">查看</el-button>
                </template>
              </el-table-column>
            </el-table>
        </div>
    </el-dialog>
</div>


 </template>

<script>
import {jiexi,listRecord,addRecord } from "@/api/resume/record/customerinfo";

export default {
  name: "Record",
  data() {
    return {
      text:"",
      // 查询参数
      finddata:{
        name:null,
         phone:null,
      },
      // 是否查询到
      sous:false,
      //查询
      open:false,
      // 简历性别字典
      customerSexOptions: [],
      // 表单参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sex: null,
        birthday: null,
        school: null,
        education: null,
        pay: null,
        direction: null,
        workjinli: null,
        workjingyan: null,
        xingmu: null,
      },
      // 表格元素
      tableData:[],
      // 表单元素
      from:[]

      // 表单校验
      // rules: {
      //   customerName: [
      //     { required: true, message: "简历姓名不能为空", trigger: "blur" }
      //   ],
      //   customerTel: [
      //     { required: true, message: "联系电话不能为空", trigger: "blur" }
      //   ],
      // }
    };
  },
  created() {


  },
  methods: {
    // 简历解析
    jiexii(){
      this.text=this.$refs.files.files[0]
      console.log(this.text)
      let formData=new FormData();
      formData.append('upfile',this.text);
      jiexi(formData).then(res=>{
        this.recordList=res.data
      }
      )
    },
    // 查看
    handleClick(){

    },
    // 取消
    handleQuery(){

    },
    //保存
    resetQuery(){
      addRecord(this.from).then(

      )
    },
    // 查询
    find(){
      this.open=true;
      listRecord(this.finddata).then(response => {

      });
    },


  }
};
</script>

<style>
  .input{
    width: 150px;
    height: 40px;
    margin-right: 10px;
  }
  .but{
    width: 90px;
    height: 40px;
    background: none;
    border: 1px solid grey;
  }
</style>
