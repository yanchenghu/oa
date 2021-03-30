<template>
  <div class="app-containe">
      <el-row :gutter="10">
        <el-col :span="16">
          <div style="position: fixed;z-index: 5; background-color: #fff;width: calc(58.33333% - 16px); border-bottom: 1px solid #dcdfe6 ;padding: 40px 0 0 40px;">
            <el-button style="border-radius: 0;" type="primary"><b>智能解析简历</b></el-button>
            <el-button  style="margin-left: 0;border-radius: 0; margin-bottom: 20px;"><b><router-link to="/record/manually">手动上传简历</router-link></b></el-button>
          </div>
          <div class="col-left" :style="{'height':height}">
               <div style="padding-top: 90px;">
                 <div>
                   <el-input prefix-icon="el-icon-search" style="width: 90%;margin-bottom: 30px;"  v-model="finddata.customerName" placeholder="请输入姓名或电话"  @keyup.enter.native="find" size="medium">
                        <el-button slot="append"  @click="find" >查询</el-button>
                    </el-input>
                 </div>
                 <span><svg-icon icon-class="jiben" class-name="card-panel-icon" /></span><span class="tit">基本信息</span><span style="font-size:12px ;color:#ea5455 ;">请核对 ( * ) 必填项信息是否正确完整，并保存否则上传失败</span>
                  <el-form  :model="perCustomerinfo" style="margin-left: 30px;margin-top: 20px;"  label-width="70px" :inline="true" ref="form" label-position="right" :rules="rules">
                    <el-col :span="12">
                      <span class="bitian">*</span>
                      <el-form-item label="姓名" prop="customerName">
                        <el-input v-model="perCustomerinfo.customerName" size="small"  :disabled="true" suffix-icon="xxx"/>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <span class="bitian">*</span>
                      <el-form-item label="电话" prop="customerTel">
                        <el-input v-model="perCustomerinfo.customerTel" size="small"  :disabled="true" suffix-icon="xxx"/>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="性别" prop="customerSex">
                        <el-select v-model="perCustomerinfo.customerSex" placeholder="" size="small">
                          <el-option v-for="dict in customerSexOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="parseInt(dict.dictValue)" />
                        </el-select>
                      </el-form-item>
                    </el-col>

                    <el-col :span="12">
                      <el-form-item label="出生年月" prop="customerBirth" >
                        <el-date-picker type="date"
                        v-model="perCustomerinfo.customerBirth"
                        size="small"
                        style="width: 199px;"
                        value-format="yyyy-MM-dd">
                        </el-date-picker>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <span class="bitian">*</span>
                      <el-form-item label="工作经验" prop="workYear">
                        <el-select v-model='perCustomerinfo.workYear' placeholder="" size="small">
                          <el-option v-for="dict in 10" :key="dict" :label="dict+'年'" :value="dict" suffix-icon="xxx"/>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <span class="bitian">*</span>
                      <el-form-item label="学历" prop="education">
                        <el-select v-model='perCustomerinfo.education' placeholder="" size="small">
                          <el-option v-for="dict in customerSpecialitiesoptions" :key="dict.dictValue" :label="dict.dictLabel"
                            :value="parseInt(dict.dictValue)" />
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <span class="bitian">*</span>
                      <el-form-item label="技术方向" prop="professionId">
                        <el-select v-model='perCustomerinfo.professionId' filterable  placeholder="" size="small">
                          <el-option v-for="dict in professionIdoptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="毕业学院" prop="customerUniversity">
                        <el-input v-model="perCustomerinfo.customerUniversity" size="small" @keyup.enter.native="handleQuery" suffix-icon="xxx"/>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <span class="bitian"> *</span>
                      <el-form-item label="意向城市" prop="intentionArea">
                        <el-select v-model='perCustomerinfo.intentionArea' placeholder=""  filterable size="small">
                          <el-option v-for="dict in intentionareaOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="期望薪资" prop="expectationSalary">
                        <el-input v-model="perCustomerinfo.expectationSalary" size="small"  suffix-icon="xxx"/>
                      </el-form-item>
                    </el-col>
                    <el-cpl :span="12">
                      <el-form-item label="邮箱" prop="email">
                        <el-input v-model="perCustomerinfo.email" size="small" suffix-icon="xxx" />
                      </el-form-item>
                    </el-cpl>
                  </el-form>
                  <span><svg-icon icon-class="jiaoyu" class-name="card-panel-icon" /></span><span class="tit">教育经历</span>
                    <ul style="list-style: none;" class="font">
                      <li v-for="work,i in perEducList"
                       :key="i"
                       style="margin: 20px 0 10px 0; ">
                        <p class="biaoti">{{work.schoolName}}</p>
                        <p><span class="xiaotu">学<span style="display: inline-block; width: 25px;"></span>历：</span>&nbsp<span class="miaoshu" >{{work.degree}}</span></p>
                        <p><span class="xiaotu">专<span style="display: inline-block; width: 25px;"></span>业：</span>&nbsp<span class="miaoshu" >{{work.major}}</span></p>
                        <p><span class="xiaotu"> 时 间 段 :</span>&nbsp
                        <span class="miaoshu"  v-if="work.endTime">{{work.startTime?work.startTime+" 至 "+work.endTime :"未知"+" 至 "+work.endTime}}</span>
                        <span class="miaoshu"  v-else-if="work.startTime">{{work.endTime?work.startTime+" 至 "+work.endTime:work.startTime+" 至 "+"未知"}}</span>
                        <span  class="miaoshu" v-else>{{"未知"+" 至 "+"未知"}}</span>
                        </p>
                      </li>
                  </ul>
                  <span><svg-icon icon-class="work" class-name="card-panel-icon" /></span><span class="tit">工作经历</span>
                   <ul style="list-style: none;" class="font">
                     <li v-for="work in work_experienceListArr" style="margin: 20px 0 10px 0;">
                       <p class="biaoti">{{work.companyName?work.companyName:"未知"}}</p>
                       <p><span class="xiaotu">公司类型：</span>&nbsp<span class="miaoshu" >{{work.jobFunction?work.jobFunction:"未知"}}</span></p>
                       <p><span class="xiaotu">工作岗位：</span>&nbsp<span class="miaoshu" >{{work.jobTitle?work.jobTitle:"未知"}}</span></p>
                       <p><span class="xiaotu">工作时间：</span>&nbsp
                       <span class="miaoshu"  v-if="work.endTime">{{work.startTime?work.startTime+" 至 "+work.endTime :"未知"+" 至 "+work.endTime}}</span>
                       <span class="miaoshu"  v-else-if="work.startTime">{{work.endTime?work.startTime+" 至 "+work.endTime:work.startTime+" 至 "+"未知"}}</span>
                       <span class="miaoshu"  v-else>{{"未知"+" 至 "+"未知"}}</span></p>
                     </li>
                   </ul>
                   <span><svg-icon icon-class="xiangmu" class-name="card-panel-icon" /></span><span class="tit">项目经厉</span>
                   <ul style="list-style: none;" class="font">
                     <li v-for="project in project_experience" style="margin: 20px 0 10px 0; ">
                       <p class="biaoti">{{project.projectName}}</p>
                       <p><span class="xiaotu">项目时间：</span>&nbsp<span class="miaoshu"  v-if="project.quitprojectTime">{{project.joinprojectTime?project.joinprojectTime+" 至 "+project.quitprojectTime : "未知"+" 至 "+project.quitprojectTime}}</span>
                     <span class="miaoshu"  v-else-if="project.joinprojectTime">{{project.quitprojectTime?project.joinprojectTime+" 至 "+project.quitprojectTime:project.joinprojectTime+" 至 "+"未知"}}</span>
                     <span class="miaoshu"  v-else>{{"未知"+" 至 "+"未知"}}</span></p>
                       <p><span class="miaoshu"  style="line-height: 1.5;">{{project.duty}}</span></p>
                     </li>
                   </ul>
               </div>

          </div>
          <div style="text-align: center;position: fixed; bottom: 0; background-color: #fff;width: calc(58.33333% - 16px);border-top: 1px solid #dcdfe6 ; padding: 5px 0;">
              <el-button size="medium "  @click="handleQuery" >取消</el-button>
              <el-button size="medium " @click="resetQuery" type="primary" v-hasPermi="['resume:record:edit']">立即保存</el-button>
          </div>
        </el-col>

        <el-col :span="8">
            <div class="col-right" :style="{'height':height}">
                  <el-upload style="width:358px;margin: 0 auto;" action="wqewq"  ref="file" class="upload-demo" drag accept=".docx,.doc,.pdf" :limit="1" :on-exceed="handleExceed" :auto-upload="false" :on-change="oplodad" :before-remove="upoplodad">
                    <div v-if="wen">
                        <i class="el-icon-upload"></i>
                      <div><b>点击或拖拽上传简历</b></div>
                      <div class="el-upload__text" style="width: 230px;margin: 0 auto;">简历支持.docx .doc .pdf格式，大小不超过500kb，拖拽文件可直接上传</div>
                    </div>
                    <div v-else>
                      <i class="el-icon-success" style="color:rgb(0,218,175);
                        font-size: 67px;
                          margin: 40px 0 16px;
                          line-height: 50px;"></i>
                      <div><b>上传成功 </b></div>
                    </div>
                  </el-upload>
                  <div style="text-align: center;margin-top: 30px;">
                    <el-button style="width: 200px;"  :loading="loadings" type="primary" @click="jiexii" size="medium">
                      <span v-if="!loadings">开始解析</span>
                      <span v-else>解 析 中...</span>
                    </el-button>
                  </div>
            </div>
        </el-col>
      </el-row>

    <el-dialog :visible.sync="open"  title="简历查询" append-to-body>
      <div v-if="sous" style="height: 200px;">
        <div style="margin-top:200px" align="center">未查询到人员信息</div>
      </div>
      <div v-else>
        <el-table :data="tableData" border style="width: 100%" v-loading="loading">
          </el-table-column>
          <el-table-column  label="姓名"width="70" >
            <template slot-scope="scope">
                <span v-if="scope.row.customer_name.length>2">{{scope.row.customer_name.substring(0,1)+" * "+scope.row.customer_name.substr(-1, 1)}}</span>
                <span v-else>{{scope.row.customer_name.substring(0,1)+" * "}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="customer_tel" label="电话">
            <template slot-scope="scope">
              <!-- <span v-if="name===scope.row.">{{scope.row.customer_tel.replace(reg,"$1****$2")}}</span> -->
              <span>{{scope.row.customer_tel.replace(reg,"$1****$2")}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="customer_birth" label="出生日期">
          </el-table-column>
          <el-table-column prop="profession_id" label="技术方向" :formatter="professionIdSituationFormat" width="60">
          </el-table-column>
          <el-table-column label="录入简历时间"  prop="add_time" >
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.add_time, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="更新简历时间"  prop="add_time" >
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.update_time, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="add_name" label="占有人" width="70"  fixed="right">
          </el-table-column>
          <el-table-column label="操作"  width="90" fixed="right">
            <template slot-scope="scope">
              <el-button v-hasPermi="['resume:record:query']" @click="handsee(scope.row.customer_code)" type="text" size="small">查看</el-button>
              <el-button v-if="!scope.row.add_name" @click="handleClick(scope.row.customer_code)" type="text" size="small">抢占</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="finddata.pageNum"
        :limit.sync="finddata.pageSize"
        @pagination="select"
              />
    </el-dialog>
  </div>

</template>

<script>
  import {
    getRecord,
    jiexi,
    listRecord,
    addRecord,
    updateRecord,
    handInsert
  } from "@/api/resume/record/customerinfo";
  import {debounce,} from "@/utils/ruoyi.js"
  export default {
    name: "Record",
    data() {
      return {
        // 遮盖层
        loading:false,
        // 总条数
        total:0,
        reg: /^(\d{3})\d{4}(\d{4})$/,
        vadio: 1,
        msg: "",
        // 接受文件
        text: "",
        // 查询参数
        finddata: {
          pageNum: 1,
          pageSize: 10,
          customerName: "",
          customerTel: "",
        },
        // 是否查询到
        sous: false,
        //查询
        open: false,
        // 技术方向字典
        professionIdoptions: [],
        // 简历学历字典
        customerSpecialitiesoptions: [],
        // 简历性别字典
        customerSexOptions: [],
        // 简历地区字典
        intentionareaOptions:[],
        loadings:false,
        height:document.body.scrollHeight-104+"px",
        // 表格元素
        tableData: [],
        // 表单元素
        // 工作经历
        work_experienceListArr: {},
        // 基础信息
        perCustomerinfo:{},
        // 教育经历
        perEducList:[],
        // 项目经验
        project_experience: [],
        // 上传文件图标
        wen: true,
        // 表单校验
        rules: {
          customerName:[{
            required: true,
            message: "姓名不能为空",
            trigger: ["blur", "change"]
          }, ],
          customerTel:[{
            required: true,
            message: "电话不能为空",
            trigger: ["blur", "change"]
          },],
          education: [{
            required: true,
            message: "学历不能为空",
            trigger: ["blur", "change"]
          }, ],
          professionId: [{
            required: true,
            message: "技术方向不能为空",
            trigger: ["blur", "change"]
          }, ],
          intentionArea: [{
            required: true,
            message: "意向城市不能为空",
            trigger: ["blur", "change"]
          }],
          workYear: [{
            required: true,
            message: "工作年限不能为空",
            trigger: ["blur", "change"]
          }],
        }
      };
    },

    created() {
      // 获取学历字典
      this.getDicts("per_customerinfo_education").then(response => {
        this.customerSpecialitiesoptions = response.data;
      });
      // 获取性别字典
      this.getDicts("sys_user_sex").then(response => {
        this.customerSexOptions = response.data;
      });
      // 获取技术方向字典
      this.getDicts("per_customerinfo_professionid").then(response => {
        this.professionIdoptions = response.data;
      });
      // 获取城市字典
      this.getDicts("per_customerinfo_intentionarea").then(response => {
        this.intentionareaOptions = response.data;
      });

    },
    methods: {
      professionIdSituationFormat(row,column){
        return this.selectDictLabel(this.professionIdoptions, row.profession_id);
      },

      oplodad(file) {
        this.wen = false
      },
      upoplodad(file) {
        this.reset()
      },
      handleExceed() {
        this.msgError(`当前限制选择 1 个文件`);
      },
      // 简历解析
      jiexii:debounce(function(){this.jiexiis()}),
      jiexiis(){
        if (this.$refs.file.uploadFiles[0] == null) {
          this.msgError("请选择简历")
        } else {
          if(this.$refs.file.uploadFiles[0].raw.size>10*1000*1000){
          this.msgError("文件过大，请重新上传")
          this.wen = true
          this.$refs.file.clearFiles()
          }else{
            let formData = new FormData();
            formData.append('upfile', this.$refs.file.uploadFiles[0].raw);
            // formData.append('resume_direction', this.vadio);
            this.loadings = true
            jiexi(formData).then(res => {
              this.msgSuccess("简历解析成功,记得点击保存")
              this.perCustomerinfo = res.data.perCustomerinfo
              this.project_experience =res.data.project_experienceListArr
              this.work_experienceListArr = res.data.work_experienceListArr
              this.perEducList=res.data.education_experienceListArr
              this.loadings = false

            }).catch(() => {
                this.$refs.file.clearFiles()
                this.wen = true
                this.loadings = false;
             })
          }

        }

      },
      // 查看
      handsee(value){
         let customerCode = value
         this.$router.push({path:"/record/particulars",query:{customerCode:customerCode}});
         this.open = false;
      },
      // 抢占
      handleClick(val) {
        var formData = new FormData()
        formData.append("customerCode",val)
        addRecord(formData).then(res=>{
          if(res.code===200){
            this.msgSuccess("抢占成功"),
            this.select()
          }
        })
      },

      // 取消
      handleQuery() {
        this.reset()
      },
      //保存
      resetQuery:debounce(function(){this.resetQuerys()}),
      resetQuerys() {
        if (this.$refs.file.uploadFiles[0] == null) {
          this.msgError("请选择简历")
        } else {
          this.$refs["form"].validate((valid) => {
            if (valid) {
              let zm = {
                perCustomerinfo:this.perCustomerinfo,
                PerWorList:this.work_experienceListArr,
                PerProjList:this.project_experience,
                perEducList:this.perEducList,
              }
              var formData = new FormData()
              formData.append("zm",JSON.stringify(zm))
              formData.append("file",null)
              handInsert(formData).then(response => {
                this.msgSuccess("保存成功");
                this.reset()
              })
            }
          });
        }
      },
      select(){
          this.loading=true
          listRecord(this.finddata).then(res =>{
          this.total = res.total;
          this.loading=false
          if(res.rows.length==0){
            this.sous=true
          }else{
            this.sous=false
            this.tableData=res.rows
          }
          });
      },
      // 查询
      find() {
        // this.select()ss
        // this.open = true;
        if(this.finddata.customerName==""){
          this.msgError("请输入姓名或电话")
        }else{
          this.open = true;
          this.select()
        }
      },
      // 重置表单
      reset() {
        this.perCustomerinfo = {}
        this.project_experience = {}
        this.work_experienceListArr=[]
        this.perEducList=[]
        this.$refs.file.clearFiles()
        this.wen = true
        this.resetForm("form");
      },
    }
  };
</script>

<style  scoped>
  .qwe{
    width: calc(100% - 220px);
  }
  .app-containe{
    padding: 15px 10px 0 10px;
  }
  >>>.el-upload-dragger{
    background-color: #f7f9fd;
  }
  >>>.el-input-group__append{
    background-color: #1682e6 ;
    border-color: #1890ff;
    color: #FFFFFF;
  }
  >>>.el-input-group__append:hover{
    background: #46a6ff;
    border-color: #46a6ff;
    color: #FFFFFF;
  }
  >>>.el-form-item label:after {
        content: "";
        display: inline-block;
        width: 100%;
      }
  >>>.el-form-item__label {
      text-align: justify
  }
  >>>.el-form-item.is-required .el-form-item__label:before {
          content: none !important;
      }
    >>>.el-form-item {
      margin-right: 50px;
      margin-bottom: -10px;
    }
  .col-left{
    padding:40px;
    background-color: #fff;
    overflow: auto;
  }
  .col-left::-webkit-scrollbar {
    width: 5px;
    height: 1px;
  }
  .col-left::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: #909399;
  }
  .col-left::-webkit-scrollbar-track {
    box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    background: #ededed;
    border-radius: 5px;
  }

  .col-right{
    padding-top: 150px;
    text-align: center;
    background-color: #fff;
  }
  .xiaotu{
    color: #909399;
  }
  .miaoshu{
   color: #606266;
  }
  .font{
    font-size: 14px;
    /* padding-left: 1px !important; */
  }
  .bitian{
    display: inline-block;
    margin-left: -15px;
    margin-top: 10px;
    margin-right: 10px;
    color: red;
  }
  .card-panel-icon{
    margin-right:10px;
    color: #303133;
  }
  .tit{
    height: 42px;
    font-weight: 700;
    margin-right: 10px;
  }

  .input {
    width: 150px;
    height: 40px;
    margin-right: 10px;
  }

  .but {
    width: 90px;
    height: 40px;
    background: none;
    border: 1px solid grey;
  }
</style>
