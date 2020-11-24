<template>
  <div class="app-container">
    <div style="margin-bottom: 30px;display: flex;justify-content: space-between;">
      <div style="width: 35%;">
        <b>录入解析简历</b>
          <b> / </b>
        <el-button type="primary" >
          <b><router-link to="/record/manually">手动上传简历</router-link></b>
        </el-button>
      </div>
      <div style="display: flex;flex-wrap: wrap; width: 60%;justify-content: flex-end;">
        <div>
          <label>电话 </label>&nbsp;<input type="text" class="input" v-model="finddata.customerTel">
        </div>
        <div>
          <label>姓名 </label>&nbsp;<input type="text" class="input" v-model="finddata.customerName">
        </div>
        <button class="but" @click="find">查询</button>
      </div>
    </div>
    <div style="position: relative; display: flex;flex-wrap: wrap; margin-bottom: 20px;height: 220px;">
      <el-upload action="wqewq" ref="file" class="upload-demo" drag accept=".docx" :limit="1" :on-exceed="handleExceed" :auto-upload="false" :on-change="oplodad" :before-remove="upoplodad">
        <div v-if="wen">
          <i class="el-icon-circle-plus" style="color:#0081FF;
          font-size: 67px;
            margin: 40px 0 16px;
            line-height: 50px;"></i>
          <div><b>点击上传解析简历 </b></div>
          <div class="el-upload__text" style="width: 200px;margin: 0 auto;">简历支持.docx格式，大小不超过500kb，拖拽文件可直接上传</div>
        </div>
        <div v-else>
          <i class="el-icon-success" style="color:rgb(0,218,175);
            font-size: 67px;
              margin: 40px 0 16px;
              line-height: 50px;"></i>
          <div><b>上传成功 </b></div>
        </div>

      </el-upload>
      <div style="position: absolute;bottom:42px ; left: 365px ;">
        <el-radio border v-model="vadio" :label="1">国内</el-radio>
        <el-radio border v-model="vadio" :label="2" style="margin:3px 10px 3px 0;">对日</el-radio>
        <button class="but" @click="jiexii">解析</button>
      </div>
      <!-- <div style="clear: both;"></div> -->

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
          <el-form-item label="出生年月" prop="customerBirth">
            <el-input v-model="perCustomerinfo.customerBirth" size="small" suffix-icon="xxx"/>
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
            <el-select v-model='perCustomerinfo.professionId' placeholder="" size="small">
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
            <el-select v-model='perCustomerinfo.intentionArea' placeholder="" size="small">
              <el-option v-for="dict in intentionareaOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
            </el-select>
          </el-form-item>
        </el-form>
        </div>
        </div>
        <div class="tit">
          <b style="margin-right: 20px; margin-bottom: 20px;">教育经历</b>
        </div>
        <ul style="list-style: none;">
          <li v-for="work,i in perEducList"
           :key="i"
           style="background: #F5F5F9;padding: 5px 0 5px 20px;margin: 10px 0 10px 0; border-left: 2px solid #13CE66;">
            <p><span>学校名称：</span>&nbsp<span>{{work.schoolName}}</span></p>
            <p><span>学<span style="display: inline-block; width: 32px;"></span>历：</span>&nbsp<span>{{work.degree}}</span></p>
            <p><span>专<span style="display: inline-block; width: 32px;"></span>业：</span>&nbsp<span>{{work.major}}</span></p>
            <p><span> 时 间 段 :</span>&nbsp
            <span v-if="work.endTime">{{work.startTime?work.startTime+" 至 "+work.endTime :"未知"+" 至 "+work.endTime}}</span>
            <span v-else-if="work.startTime">{{work.endTime?work.startTime+" 至 "+work.endTime:work.startTime+" 至 "+"未知"}}</span>
            <span v-else>{{"未知"+" 至 "+"未知"}}</span>
            </p>

          </li>
        </ul>


        <div class="tit">
          <b style="margin-right: 20px; margin-bottom: 20px;">工作经历</b>
        </div>
        <p></p>
        <ul style="list-style: none;">
          <li v-for="work in work_experienceListArr" style="background: #F5F5F9;padding: 5px 0 5px 20px;margin: 10px 0 10px 0; border-left: 2px solid #13CE66;">
            <p><span>公司名称：</span>&nbsp<span>{{work.companyName}}</span></p>
            <p><span>公司类型：</span>&nbsp<span>{{work.jobFunction}}</span></p>
            <p><span>工作岗位：</span>&nbsp<span>{{work.jobTitle}}</span></p>
            <p><span>工作时间：</span>&nbsp<span>{{work.startTime+" 至 "+work.endTime}}</span></p>
          </li>
        </ul>
        <div class="tit">
          <b style="margin-right: 20px;">项目经厉</b>
        </div>

        <ul style="list-style: none;">
          <li v-for="project in project_experience" style="background: #F5F5F9;padding: 5px 0 5px 20px;margin: 10px 0 10px 0; border-left: 2px solid #13CE66;">
            <p><span>项目名称：</span>&nbsp<span>{{project.projectName}}</span></p>
            <p><span>项目时间：</span>&nbsp<span>{{project.joinprojectTime+" 至 "+project.quitprojectTime}}</span></p>
            <p><span>{{project.duty}}</span></p>
          </li>
        </ul>
        <br>
        <el-button size="medium " type="" @click="handleQuery" >取消</el-button>
        <el-button size="medium " @click="resetQuery" type="success" v-hasPermi="['resume:record:edit']">保存</el-button>



    <el-dialog :visible.sync="open" style="width: 100%;" title="简历查询" >
      <div v-if="sous" style="height: 200px;">
        <div style="margin-top:200px" align="center">未查询到人员信息</div>
      </div>
      <div v-else>
        <el-table :data="tableData" border style="width: 100%" v-loading="loading">
          </el-table-column>
          <el-table-column prop="customer_name" label="姓名"width="70" align="center">
            </el-table-column>
            <el-table-column prop="customer_tel" label="电话" align="center"width="110">
          </el-table-column>
          <el-table-column prop="customer_birth" label="出生日期" align="center"width="100">
          </el-table-column>
          <el-table-column prop="profession_id" label="技术方向" align="center":formatter="professionIdSituationFormat" >

          </el-table-column>
          <el-table-column prop="add_time" label="录入/更新简历时间" width="230" align="center">
          </el-table-column>
          <el-table-column prop="add_name" label="占有人" width="70" align="center">
          </el-table-column>
          <el-table-column label="操作" align="center" width="90" fixed="right">
            <template slot-scope="scope">
              <el-button v-hasPermi="['resume:record:query']" @click="handsee(scope.row.customer_code)" type="text" size="small">查看</el-button>
              <el-button @click="handleClick(scope.row.customer_code)" type="text" size="small">抢占</el-button>
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
      jiexii() {
        if (this.$refs.file.uploadFiles[0] == null) {
          this.msgError("请选择简历")
        } else {
          let formData = new FormData();
          formData.append('upfile', this.$refs.file.uploadFiles[0].raw);
          formData.append('resume_direction', this.vadio);
          jiexi(formData).then(res => {
            this.msgSuccess("简历解析成功")
            this.perCustomerinfo = res.data.perCustomerinfo
            this.project_experience =res.data.project_experienceListArr
            this.work_experienceListArr = res.data.work_experienceListArr
            this.perEducList=res.data.education_experienceListArr
          })
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
        }
        )
      },

      // 取消
      handleQuery() {
        this.reset()
      },
      //保存
      resetQuery() {
        this.$refs["form"].validate((valid) => {
          if (valid) {
            updateRecord(this.perCustomerinfo).then(response => {
              this.msgSuccess("保存成功");
              this.reset()
            })
          }
        });
      },
      select(){
          this.loading=true
          listRecord(this.finddata).then(res =>          {
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
        this.select()
        this.open = true;
        // if(this.finddata.customerName==""&&this.finddata.customerTel==""){
        //   this.msgError("请输入用户名或密码")
        // }else{
        //   this.open = true;
        //   this.select()
        // }
        // this.open = true;
        // this.loading=true
        // listRecord(this.finddata).then(res =>          {
        // this.total = res.total;
        // this.loading=false
        // if(res.rows.length==0){
        //   this.sous=true
        // }else{
        //   this.sous=false
        //   this.tableData=res.rows
        // }
        // });
      },
      // 重置表单
      reset() {
        this.perCustomerinfo = {}
        this.project_experience = {}
        this.work_experienceListArr=[]
        this.$refs.file.clearFiles()
        this.wen = true
        this.resetForm("form");
      },
    }
  };
</script>

<style scoped>
  .tit {
    height: 42px;
    background: #F5F5F9;
    line-height: 42px;
    padding-left: 10px;
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
