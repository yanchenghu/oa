<template>
  <div class="app-containe">
    <el-row style="display: flex;">
      <!-- 需求表格 -->
      <el-col style="width: 190px; text-align: center; background: #fff;padding: 10px;margin-right: 10px;">
        <el-input prefix-icon="el-icon-search" v-model="queryParams.projectName" size="medium" placeholder="搜索需求" @input="input">
        </el-input>
        <el-radio-group v-model="fromdata.nood" size="medium" style="margin-top: 10px;" @change="changes" v-loading="loading2">
           <div  v-for="nood in tabledata" style="width: 100% ;height: 40px;">
              <el-tooltip class="item" effect="dark" :content="nood.projectName" v-if="nood.projectName.length>=7" placement="top">
                    <el-radio-button style="display: inline-block; width: 100%;"  :label="nood.demandId" >{{`${nood.projectName.slice(0,7)}...`}}</el-radio-button>
              </el-tooltip>
              <el-radio-button style="display: inline-block; width: 100%;" v-else :label="nood.demandId" >{{nood.projectName}}</el-radio-button>
           </div>
        </el-radio-group>
        <el-pagination
          layout="prev, next"
          v-show="total2>0"
          :total="total2"
          :current-page.sync="queryParams.pageNum"
          :page-size.sync="queryParams.pageSize"
          @current-change="getxuqiulist"
          >
        </el-pagination>
    </el-col>
    <el-col style="background-color: #fff;padding: 10px;width:calc( 100% - 200px);">
       <el-form style="padding-bottom: 25px;" ref="queryForm" :model="form" label-width="80px" :inline="true">
         <el-form-item label="技术方向" prop="professionId">
           <el-select v-model="fromdata.professionId" size="small" @change="change" clearable>
             <el-option v-for="dict in professionIdoptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
           </el-select>
         </el-form-item>
         <el-form-item label="工作年限" prop="workYear">
           <el-select v-model='fromdata.workYear' size="small" @change="change" clearable>
             <el-option v-for="dict in 10" :key="dict" :label="dict+'年'" :value="dict"/>
           </el-select>
         </el-form-item>
         <el-form-item label="最低学历" prop="education">
           <el-select v-model='fromdata.education' size="small" @change="change" clearable>

             <el-option v-for="dict in customerSpecialitiesoptions" :key="dict.dictValue" :label="dict.dictLabel"
               :value="dict.dictValue" />
           </el-select>
         </el-form-item>
         <el-form-item label="期望城市" prop="intentionArea">
           <el-select v-model='fromdata.intentionArea' size="small" @change="change" clearable>

             <el-option v-for="dict in intentionareaOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
           </el-select>
         </el-form-item>
         <el-form-item label="录入人" prop="opertCode" v-hasPermi="['resume:record:allquery']">
           <el-select  filterable  v-model="fromdata.opertCode"  placeholder="请选择员工" size="small"  @change="change">
             <el-option
                v-for="dict in userlist"
                :key="dict.userName"
                :label="dict.nickName"
                :value="dict.userName"
              />
           </el-select>
        </el-form-item>
         <el-button  type="primary" size="small" @click="resetQuery">重置</el-button>
         <el-button  type="primary" size="small" @click="next">换一批</el-button>
       </el-form>
       <!-- 数据表格 -->
       <el-table   :data="msgtaba" v-loading="loading">
         <el-table-column type="selection"  width="55" />
         <el-table-column prop="customerName" label="姓名">
           <template slot-scope="scope">
             <el-button  type="text"  @click="handlesee(scope.row)" >{{scope.row.customerName}}</el-button>
           </template>
         </el-table-column>
         <el-table-column prop="customerTel" label="电话" width="110" >
           <template slot-scope="scope">
             <span v-if="checkPermi(['resume:record:allquery'])">{{scope.row.customerTel}}</span>
             <span v-else>{{scope.row.customerTel.replace(reg,"$1****$2")}}</span>
           </template>
         </el-table-column>
         <el-table-column prop="customerBirth" label="出生日期"  >
         </el-table-column>
         <el-table-column prop="professionId" label="技术方向"   :formatter="professionIdopFormat">
         </el-table-column>
         <el-table-column prop="workYear" label="工作年限"  width="80">
           <template slot-scope="scope">
             {{scope.row.workYear}}年
           </template>
         </el-table-column>
         <el-table-column prop="education" label="学历"  width="80" :formatter="customerFormat"/>

         <el-table-column prop="customerUniversity" label="毕业院校" />

         <el-table-column prop="expectationSalary" label="期望薪资" />

         <el-table-column prop="intentionArea"  label="期望城市" :formatter="intentionareaFormat"/>
         <el-table-column prop="intentionArea"  label="添加时间">
           <template slot-scope="scope">
             <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>
           </template>
         </el-table-column>
         <el-table-column prop="opertName"  label="录入人" />
         <el-table-column label="操作"  class-name="small-padding fixed-width" width="180">
           <template slot-scope="scope">
                   <el-button type="text"  @click="handleUpdate(scope.row)" v-hasPermi="['resume:peopost:preview']">
                     <svg-icon icon-class="preview" style="font-size: 14px;"/>预览
                   </el-button>
                   <el-button  type="text"  @click="handleDelete(scope.row)" v-hasPermi="['resume:peopost:seize']"><svg-icon icon-class="button"  style="font-size: 14px;"/>抢占</el-button>

                   <el-button  type="text"  @click="handlesee(scope.row)" ><svg-icon icon-class="eye-open" style="font-size: 14px;"/>查看</el-button>
           </template>
         </el-table-column>
       </el-table>
       <pagination
         v-show="total>0"
         :total="total"
         :page.sync="fromdata.pageNum"
         :limit.sync="fromdata.pageSize"
         @pagination="getList"
       />
    </el-col>

 <!-- 筛选列表 -->
    </el-row>
       <el-dialog title="预览" :visible.sync="open" width="70%"  >
       <iframe
          :src="src"
          style="overflow: auto; position: absolute; top: 40px; right: 0; bottom: 0; left: 0; width: 100%; height:1000%;border: none;"></iframe>
      </el-dialog>
  </div>

</template>



<script>
  import {
    listRecord,
    getRecord,
    delRecord,
    addRecord,
    updateRecord,
    exportRecord,
    listFollow,
    getFollow,
  } from "@/api/resume/peopost/peopost";
  import {debounce} from "@/utils/ruoyi.js"
  import {checkPermi} from "@/utils/permission.js"
  import {yuangonglist} from "@/api/analysis/personnelanalysis.js"
  export default {
    name: "Record",

    data() {
      return {
        loading2:true,
        nale:"",
        // 全选
        checkAll:false,
        reg:/^(\d{3})\d{4}(\d{4})$/,
        // 表单内容
        fromdata: {
          pageNum: 1,
          pageSize: 10,
          customerSex: null,
          customerTel: null,
          customerUniversity: null,
          customerSpecialities: null,
          workYear: null,
          addTime: null,
          expectationSalary: null,
          professionId: null,
          education: null,
          nood:null,
          opertCode:null,
        },
        // 表格数据
        msgtaba: [],
        total2:0,
        // 需求数组
        tabledata: [],
        // 表单参数
        form: {},
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
         userlist:[],
        src:"",
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 简历表格数据
        recordList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 简历性别字典
        customerSexOptions: [],
        // 技术方向字典
        professionIdoptions: [],
        // 简历学历字典
        customerSpecialitiesoptions: [],
        // 城市
        intentionareaOptions:[],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 14,
          projectName:null,
        },
        // 表单校验
        rules: {
          customerName: [{
            required: true,
            message: "简历姓名不能为空",
            trigger: "blur"
          }],
          customerTel: [{
            required: true,
            message: "联系电话不能为空",
            trigger: "blur"
          }],
        },
      };
    },
    created() {
      // 获取学历字典
      this.getDicts("per_customerinfo_education").then(response => {
        this.customerSpecialitiesoptions = response.data;
      });
      // 获取技术方向字典
      this.getDicts("per_customerinfo_professionid").then(response => {
        this.professionIdoptions = response.data;
      });
      // 获取城市字典
      this.getDicts("per_customerinfo_intentionarea").then(response => {
        this.intentionareaOptions = response.data;
      });
      this.getuserlist()
      this.getxuqiulist()
    },
    methods: {
      getuserlist(){
        yuangonglist().then(res=>{
          this.userlist = res.data
        })
      },
      // 技术方向
      professionIdopFormat(row, column) {
        return this.selectDictLabel(this.professionIdoptions, row.professionId);
      },
      // 学历
      customerFormat(row, column) {
        return this.selectDictLabel(this.customerSpecialitiesoptions, row.education);
      },
      // 城市
      intentionareaFormat(row, column) {
        return this.selectDictLabel(this.intentionareaOptions, row.intentionArea);
      },
      input:debounce(function(){this.inpu()},500),
      inpu(){
        this.queryParams.pageNum = 1;
        this.getxuqiulist();
      },
      getxuqiulist(){
        this.loading2 = true
        listFollow(this.queryParams).then(res=>{
          this.tabledata = res.rows
          this.loading2 = false
          this.total2 = res.total
          if(this.tabledata.length==0){
            this.loading = false;
          }else{
            this.fromdata.nood = ''+ this.tabledata[0].demandId
            this.changes(this.fromdata.nood)
          }
        })
      },
      // 筛选更多
      getList(){
        this.loading = true;
        if(this.fromdata.workYear==''&&this.fromdata.professionId==""&&this.fromdata.education==""){
          this.loading = false;
          this.msgtaba = []
        }else{
            listRecord(this.fromdata).then(response => {
            this.msgtaba = response.rows;
            this.total = response.total;
            this.loading = false;
          })
        }
      },
      change(value){
        this.fromdata.pageNum = 1;
        this.getList()
      },
      changes(value){
        getFollow(value).then(res=>{
          this.fromdata.professionId = res.data.marDeman.technologyDirection
          this.fromdata.workYear = res.data.marDeman.directWorklife
          this.fromdata.education = res.data.marDeman.education
          this.fromdata.pageNum = 1
          this.getList()
        })
      },
     // 换一批
     next(){
       this.fromdata.pageNum += 1
       if(this.fromdata.pageNum-1>=this.total/this.fromdata.pageSize){
         this.fromdata.pageNum = 1
         this.getList()
       }else{
         this.getList()
       }
     },
     resetQuery() {
       this.fromdata={
         professionId:"",
         workYear:"",
         education:"",
       }
       // this.change();
       this.msgtaba = []
     },
     // 预览
     handleUpdate(adinw){
       let srcs = process.env.VUE_APP_BASE_API+adinw.resumePath
       this.src=`https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=${srcs}`
       this.open=true
     },
     // 抢占
     handleDelete(value){
        var formData = new FormData()
        formData.append("customerCode",value.customerCode)
        addRecord(formData).then(res=>{
          if(res.code===200){
            this.msgSuccess("抢占成功"),
            this.getList()
          }
        })
     },
     // 查看
     handlesee(row){
       this.$router.push({path:"/record/particulars",query:{customerCode:row.customerCode}});
     }
    }
  };
</script>
<style scoped>
  .left{
     width:200px;
     padding: 10px;
     margin-right: 10px;

  }
  .right{
    width: 100%;
    padding: 10px;
    background: #fff;
  }
  >>>.el-radio-button__inner {
      width: 100%;
  }
  .app-containe{
    padding: 10px;
  }
</style>
