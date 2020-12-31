<template>
  <div class="app-containe">
    <div style="display: flex;">
      <!-- 需求表格 -->
      <div class="left">
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
      </div>

 <!-- 筛选列表 -->

      <div  class="right">
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

          <el-button  type="primary" @click="resetQuery">重置</el-button>
          <el-button  type="primary" @click="next">换一批</el-button>
        </el-form>
        <!-- 数据表格 -->
        <el-table   :data="msgtaba" v-loading="loading" tooltip-effect="light">
          <el-table-column type="selection"  width="55" align="center"/>
          <el-table-column prop="customerName" label="姓名" width="100" align="center"/>
          <el-table-column prop="customerTel" label="电话" width="100" align="center"/>
          <el-table-column prop="customerBirth" label="出生日期" width="100" align="center">
          </el-table-column>
          <el-table-column prop="professionId" label="技术方向" align="center" width="100" :formatter="professionIdopFormat">
          </el-table-column>
          <el-table-column prop="workYear" label="工作年限" align="center" width="80">
            <template slot-scope="scope">
              {{scope.row.workYear}}年
            </template>
          </el-table-column>
          <el-table-column prop="education" label="学历" align="center" width="100" :formatter="customerFormat"/>

          <el-table-column prop="customerUniversity" label="毕业院校" align="center"/>

          <el-table-column prop="expectationSalary" label="期望薪资" align="center"/>

          <el-table-column prop="intentionArea" align="center" label="期望城市" :formatter="intentionareaFormat"/>
          <el-table-column prop="intentionArea" align="center" label="添加时间">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
               <el-tooltip class="item" effect="dark" content="预览" placement="top-start">
                    <el-button size="mini" type="text"  @click="handleUpdate(scope.row)" v-hasPermi="['resume:peopost:preview']">
                      <svg-icon icon-class="preview" style="font-size: 16px;"/>
                    </el-button>
                </el-tooltip>

              <el-tooltip class="item" effect="dark" content="抢占" placement="top">
                    <el-button size="mini" type="text"  @click="handleDelete(scope.row)" v-hasPermi="['resume:peopost:seize']"><svg-icon icon-class="button" style="font-size: 16px;"/></el-button>
              </el-tooltip>

              <el-tooltip class="item" effect="dark" content="查看" placement="top-end">
                    <el-button size="mini" type="text"  @click="handlesee(scope.row)" ><svg-icon icon-class="eye-open" style="font-size: 16px;"/></el-button>
              </el-tooltip>

            </template>
          </el-table-column>
        </el-table>
        <pagination
          style="background-color: #F2F6FC;"
          v-show="total>0"
          :total="total"
          :page.sync="fromdata.pageNum"
          :limit.sync="fromdata.pageSize"
          @pagination="getList"
        />
         <el-dialog title="预览" :visible.sync="open" width="70%"  >
         <iframe
            :src="src"
            style="overflow: auto; position: absolute; top: 40px; right: 0; bottom: 0; left: 0; width: 100%; height:1000%;border: none;"></iframe>
        </el-dialog>

      </div>

      </div>
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
  export default {
    name: "Record",

    data() {
      return {
        loading2:true,
        nale:"",
        // 全选
        checkAll:false,
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
          pageSize: 10,
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

      this.getxuqiulist()
      this.getList()
    },
    methods: {
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
        })
      },

      // 筛选更多
      getList(){
        this.loading = true;
        listRecord(this.fromdata).then(response => {
          this.msgtaba = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      change(){
          this.fromdata.pageNum = 1;
          this.getList()
      },
      changes(value){
        getFollow(value).then(res=>{
          this.fromdata.professionId = res.data.marDeman.technologyDirection
          this.fromdata.workYear = res.data.marDeman.directWorklife
          this.fromdata.education = res.data.marDeman.education
          this.fromdata.pageNum = 1;
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
       this.fromdata={}
       this.change();
     },
     // 预览
     handleUpdate(adinw){
       let srcs = process.env.VUE_APP_BASE_API+adinw.resumePath
       this.src=`https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=http://localhost${srcs}`
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
     padding: 10px;
     margin-right: 10px;
     width: 170px;
     text-align: center;
     height:100%;
     background: #fff;
  }
  .right{
    width: 80%;
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
