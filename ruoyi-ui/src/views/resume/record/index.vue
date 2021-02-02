<template>
  <div class="app-container" >
      <div>
        <el-form :inline="true">
          <el-form-item label="姓名">
            <el-input  v-model="finddata.customerName"  @keyup.enter.native="find" size="medium"></el-input>
          </el-form-item>
          <el-form-item>
               <span style="color: rgb(234, 84, 85);">或</span>
          </el-form-item>
          <el-form-item label="电话">
            <el-input  v-model="finddata.customerTel" size="medium" @keyup.enter.native="find"></el-input>
          </el-form-item>
          <el-button type="primary" @click="find" icon="el-icon-search" size="medium">查询</el-button>
        </el-form>
      </div>
    <p></p>
    <div>
      <el-button style="border-radius: 0;" type="primary"><b>智能解析简历</b></el-button>
      <el-button  style="margin-left: 0;border-radius: 0;"><b><router-link to="/record/manually">手动上传简历</router-link></b></el-button>
    </div>
    <div style="margin-bottom: 20px;margin-top: 30px;">
      <el-radio  v-model="vadio" :label="1">国内开发</el-radio>
      <el-radio  v-model="vadio" :label="2" style="margin-left: -10px;">对日开发</el-radio>
    </div>
    <div style="position: relative; display: flex;flex-wrap: wrap; height: 220px;">
      <el-upload action="wqewq" ref="file" class="upload-demo" drag accept=".docx,.doc,.pdf" :limit="1" :on-exceed="handleExceed" :auto-upload="false" :on-change="oplodad" :before-remove="upoplodad">
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
      <div style="margin-top: 145px;margin-left: 10px;">
        <el-button  :loading="loadings" type="primary" @click="jiexii" size="medium">
          <span v-if="!loadings">开始解析</span>
          <span v-else>解 析 中...</span>
        </el-button>
      </div>
    </div>
    <!-- 基础信息 -->
    <div>
      <div class="tit">
        <b style="margin-right: 20px;">基本信息</b>
        <span style="font-size:13px ;color:#EA5455 ;">请核对 ( * ) 必填项信息是否正确完整，否则无法上传</span>
      </div>
      <div style="margin-top: 20px;">
        <el-form  :model="perCustomerinfo"  label-width="100px" :inline="true" ref="form" label-position="right" :rules="rules">
          <el-form-item label="姓名" prop="customerName">
            <el-input v-model="perCustomerinfo.customerName" size="small"  :disabled="true" suffix-icon="xxx"/>
          </el-form-item>
          <el-form-item label="电话" prop="customerTel">
            <el-input v-model="perCustomerinfo.customerTel" size="small"  :disabled="true" suffix-icon="xxx"/>
          </el-form-item>
          <el-form-item label="性别" prop="customerSex">
            <el-select v-model="perCustomerinfo.customerSex" placeholder="" size="small">
              <el-option v-for="dict in customerSexOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="parseInt(dict.dictValue)" />
            </el-select>
          </el-form-item>
          <el-form-item label="出生年月" prop="customerBirth" >
            <el-date-picker type="date"
            v-model="perCustomerinfo.customerBirth"
            size="small"
            style="width: 199px;"
            value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="工作经验" prop="workYear">
            <el-select v-model='perCustomerinfo.workYear' placeholder="" size="small">
              <el-option v-for="dict in 10" :key="dict" :label="dict+'年'" :value="dict" suffix-icon="xxx"/>
            </el-select>
          </el-form-item>
          <el-form-item label="毕业学院" prop="customerUniversity">
            <el-input v-model="perCustomerinfo.customerUniversity" size="small" @keyup.enter.native="handleQuery" suffix-icon="xxx"/>
          </el-form-item>
          <el-form-item label="学历" prop="education">
            <el-select v-model='perCustomerinfo.education' placeholder="" size="small">
              <el-option v-for="dict in customerSpecialitiesoptions" :key="dict.dictValue" :label="dict.dictLabel"
                :value="parseInt(dict.dictValue)" />
            </el-select>
          </el-form-item>

          <el-form-item label="技术方向" prop="professionId">
            <el-select v-model='perCustomerinfo.professionId' filterable  placeholder="" size="small">
              <el-option v-for="dict in professionIdoptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
            </el-select>
          </el-form-item>
          <el-form-item label="期望薪资" prop="expectationSalary">
            <el-input v-model="perCustomerinfo.expectationSalary" size="small"  suffix-icon="xxx"/>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="perCustomerinfo.email" size="small" suffix-icon="xxx" />
          </el-form-item>

          <el-form-item label="意向城市" prop="intentionArea">
            <el-select v-model='perCustomerinfo.intentionArea' placeholder=""  filterable size="small">
              <el-option v-for="dict in intentionareaOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
            </el-select>
          </el-form-item>
        </el-form>
        </div>
        </div>
        <div class="tit">
          <b style="margin-right: 20px; margin-bottom: 20px;">教育经历</b>
        </div>
        <ul style="list-style: none;padding-left: 1px;">
          <li v-for="work,i in perEducList"
           :key="i"
           style="padding: 5px 0 5px 20px;margin: 10px 0 10px 0; ">
            <p style="font-weight: 700;"><span>学校名称：</span>&nbsp<span class="miaoshu">{{work.schoolName}}</span></p>
            <p><span class="xiaotu">学<span style="display: inline-block; width: 32px;"></span>历：</span>&nbsp<span class="miaoshu">{{work.degree}}</span></p>
            <p><span class="xiaotu">专<span style="display: inline-block; width: 32px;"></span>业：</span>&nbsp<span class="miaoshu">{{work.major}}</span></p>
            <p><span class="xiaotu"> 时 间 段 :</span>&nbsp
            <span v-if="work.endTime" class="miaoshu">{{work.startTime?work.startTime+" 至 "+work.endTime :"未知"+" 至 "+work.endTime}}</span>
            <span v-else-if="work.startTime" class="miaoshu">{{work.endTime?work.startTime+" 至 "+work.endTime:work.startTime+" 至 "+"未知"}}</span>
            <span v-else class="miaoshu">{{"未知"+" 至 "+"未知"}}</span>
            </p>

          </li>
        </ul>


        <div class="tit">
          <b style="margin-right: 20px; margin-bottom: 20px;">工作经历</b>
        </div>
        <p></p>
        <ul style="list-style: none;padding-left: 1px;">
          <li v-for="work in work_experienceListArr" style="padding: 5px 0 5px 20px;margin: 10px 0 10px 0; ">
            <p style="font-weight: 700;"><span>公司名称：</span>&nbsp<span>{{work.companyName}}</span></p>
            <p><span class="xiaotu">公司类型：</span>&nbsp<span class="miaoshu">{{work.jobFunction}}</span></p>
            <p><span class="xiaotu">工作岗位：</span>&nbsp<span class="miaoshu">{{work.jobTitle}}</span></p>
            <p><span class="xiaotu">工作时间：</span>&nbsp<span class="miaoshu">{{work.startTime+" 至 "+work.endTime}}</span></p>
          </li>
        </ul>
        <div class="tit">
          <b style="margin-right: 20px;">项目经厉</b>
        </div>

        <ul style="list-style: none;padding-left: 1px;">
          <li v-for="project in project_experience" style="padding: 5px 0 5px 20px;margin: 10px 0 10px 0; ">
            <p style="font-weight: 700;"><span>项目名称：</span>&nbsp<span>{{project.projectName}}</span></p>
            <p><span class="xiaotu" >项目时间：</span>&nbsp<span class="miaoshu">{{project.joinprojectTime+" 至 "+project.quitprojectTime}}</span></p>
            <p><span style="line-height: 1.5;color: #606266;">{{project.duty}}</span></p>
          </li>
        </ul>
      <div class="qwe" style="height: 50px; line-height: 50px; background-color: #fff; bottom: 0;position: fixed;right: 10px;">
        <div style="position:absolute;right: 25%;">
          <el-button size="medium "  @click="handleQuery" >取消</el-button>
          <el-button size="medium " @click="resetQuery" type="primary" v-hasPermi="['resume:record:edit']">保存</el-button>
        </div>
      </div>
    <el-dialog :visible.sync="open"  title="简历查询" append-to-body>
      <div v-if="sous" style="height: 200px;">
        <div style="margin-top:200px" align="center">未查询到人员信息</div>
      </div>
      <div v-else>
        <el-table :data="tableData" border style="width: 100%" v-loading="loading">
          </el-table-column>
          <el-table-column prop="customer_name" label="姓名"width="70" >
            </el-table-column>
            <el-table-column prop="customer_tel" label="电话">
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
    updateRecord
  } from "@/api/resume/record/customerinfo";
  import {debounce} from "@/utils/ruoyi.js"
  export default {
    name: "Record",
    data() {
      return {
        // 遮盖层
        loading:false,
        // 总条数
        total:0,
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
          let formData = new FormData();
          formData.append('upfile', this.$refs.file.uploadFiles[0].raw);
          formData.append('resume_direction', this.vadio);
          this.loadings = true
          jiexi(formData).then(res => {
            this.msgSuccess("简历解析成功")
            this.perCustomerinfo = res.data.perCustomerinfo
            this.project_experience =res.data.project_experienceListArr
            this.work_experienceListArr = res.data.work_experienceListArr
            this.perEducList=res.data.education_experienceListArr
            this.loadings = false
          }).catch(() => {
              this.loadings = false;
           });
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
              updateRecord(this.perCustomerinfo).then(response => {
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
        if(this.finddata.customerName==""&&this.finddata.customerTel==""){
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
  .app-container{
    background-color: #fff;
    margin: 10px;
  }
  .xiaotu{
    color: #909399;
  }
  .miaoshu{
   color: #606266;
  }
  .tit{
    height: 42px;
    background: #F5F5F9;
    line-height: 42px;
    padding-left: 10px;
    margin-top: 20px;
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
