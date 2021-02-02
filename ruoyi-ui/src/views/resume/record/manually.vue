<template>
  <div class="app-container">

    <div style="width: 35%;margin-bottom: 30px;">
      <el-button style="border-radius: 0;"><b><router-link to="/resume/record">录入解析简历</router-link></b></el-button>
      <el-button style="margin-left: 0;border-radius: 0;" type="primary"><b>手动上传简历</b></el-button>
    </div>
    <div style="margin-top:30px;margin-bottom: 20px;">
      <el-radio v-model="perCustomerinfo.resumeDirection" :label="1" >国内开发</el-radio>
      <el-radio v-model="perCustomerinfo.resumeDirection" :label="2" style="margin-left: -10px;">对日开发</el-radio>
    </div>
    <el-upload style="padding-bottom: 30px;" action="wqewq" ref="file" class="upload-demo" drag accept=".docx,.doc,.pdf" :limit="1" :on-exceed="handleExceed" :auto-upload="false" :on-change="oplodad" :before-remove="upoplodad" :file-list="filelist">
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
    <div>
      <div class="tit">
        <b style="margin-right: 20px;">基本信息</b>
        <span style="font-size:13px ;color:#EA5455 ;">请核对 ( * ) 必填项信息是否正确完整，否则无法上传</span>
      </div>
      <div style="margin-top: 20px;">
        <el-form  :model="perCustomerinfo"  label-width="100px" :inline="true" ref="forms" label-position="right" :rules="rules">
          <el-form-item label="姓名" prop="customerName">
            <el-input  v-model="perCustomerinfo.customerName" size="small"   suffix-icon="xxx"/>
          </el-form-item>
          <el-form-item label="电话" prop="customerTel">
            <el-input :disabled="button2==2"  v-model="perCustomerinfo.customerTel" size="small"   suffix-icon="xxx"/>
          </el-form-item>
          <el-form-item label="性别" prop="customerSex">
            <el-select v-model="perCustomerinfo.customerSex" placeholder="" size="small">
              <el-option v-for="dict in customerSexOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="parseInt(dict.dictValue)" />
            </el-select>
          </el-form-item>
          <el-form-item label="出生年月" prop="customerBirth"  style="width: 299px;">
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
            <el-input v-model="perCustomerinfo.customerUniversity" size="small" suffix-icon="xxx"/>
          </el-form-item>
          <el-form-item label="学历" prop="education">
            <el-select v-model='perCustomerinfo.education' placeholder="" size="small">
              <el-option v-for="dict in customerSpecialitiesoptions" :key="dict.dictValue" :label="dict.dictLabel"
                :value="parseInt(dict.dictValue)" />
            </el-select>
          </el-form-item>

          <el-form-item label="技术方向" prop="professionId" >
            <el-select v-model='perCustomerinfo.professionId' filterable placeholder="" size="small">
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
            <el-select v-model='perCustomerinfo.intentionArea' placeholder="" filterable size="small">
              <el-option v-for="dict in intentionareaOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
            </el-select>
          </el-form-item>
        </el-form>
        </div>
        </div>
        <div class="tit">
          <b style="margin-right: 20px;">教育经历</b>
          <i v-show="input3" class="el-icon-edit-outline i"  @click="adds(3)"></i>
        </div>
        <p></p>
        <el-form v-show="!input3" ref="form" :model="perEduc" label-width="80px" style="width: 400px; margin-left: 10px;">
          <el-form-item label="学校名称" prop="schoolName">
            <el-input v-model="perEduc.schoolName"></el-input>
          </el-form-item>
          <el-form-item label="学历" prop="degree">
            <el-input v-model="perEduc.degree"></el-input>
          </el-form-item>
          <el-form-item label="专业" prop="major">
            <el-input v-model="perEduc.major"></el-input>
          </el-form-item>
          <el-form-item label="时间段">
            <el-date-picker v-model="perEduc.startTime" type="date" placeholder="开始日期" :picker-options="pickerOptions3" value-format="yyyy-MM-dd" style="width:150px">
            </el-date-picker>
            <el-date-picker v-model="perEduc.endTime" type="date" placeholder="结束日期"  :picker-options="pickerOptions6" value-format="yyyy-MM-dd" style="width:150px">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button v-if="button==1" type="primary" @click="add(3)">添加</el-button>
            <el-button v-if="button==2" type="primary" @click="reseat">确定</el-button>
            <el-button @click="reseat(3)">取消</el-button>
          </el-form-item>
        </el-form>
        <ul style="list-style: none; padding-left: 1px;">
          <li v-for="work,i in perEducList"
           :key="i"
           style="padding: 5px 0 5px 20px;margin: 10px 0 10px 0; ">
           <div style="position: absolute; right: 60px;"><el-button type="text" @click="delete3(work)"><i class="el-icon-delete" ></i>删除</el-button>
             <el-button type="text" @click="updata3(work)"><i class="el-icon-edit"></i>修改</el-button></div>
            <p style="font-weight: 700;"><span>学校名称：</span>&nbsp<span class="miaoshu">{{work.schoolName}}</span></p>
            <p ><span class="xiaotu">学<span style="display: inline-block; width: 32px;"></span>历：</span>&nbsp<span class="miaoshu">{{work.degree}}</span></p>
            <p><span class="xiaotu">专<span style="display: inline-block; width: 32px;"></span>业：</span>&nbsp<span class="miaoshu">{{work.major}}</span></p>
            <p><span class="xiaotu"> 时 间 段 :</span>&nbsp
            <span class="miaoshu" v-if="work.endTime">{{work.startTime?work.startTime+" 至 "+work.endTime :"未知"+" 至 "+work.endTime}}</span>
            <span class="miaoshu" v-else-if="work.startTime">{{work.endTime?work.startTime+" 至 "+work.endTime:work.startTime+" 至 "+"未知"}}</span>
            <span class="miaoshu" v-else>{{"未知"+" 至 "+"未知"}}</span>
            </p>

          </li>
        </ul>

        <div class="tit">
          <b style="margin-right: 20px; margin-bottom: 20px;">工作经历</b>
          <i v-show="input" class="el-icon-edit-outline i"  @click="adds(1)"></i>
        </div>
        <p></p>
        <el-form v-show="!input" ref="form" :model="work_experienceList" label-width="80px" style="width: 400px; margin-left: 10px;">
          <el-form-item label="公司名称" prop="companyName">
            <el-input v-model="work_experienceList.companyName"></el-input>
          </el-form-item>
          <el-form-item label="公司类型" prop="jobFunction">
            <el-input v-model="work_experienceList.jobFunction"></el-input>
          </el-form-item>
          <el-form-item label="工作岗位" prop="jobFunction">
            <el-input v-model="work_experienceList.jobTitle"></el-input>
          </el-form-item>
          <el-form-item label="工作时间" >
            <el-date-picker v-model="work_experienceList.startTime" type="date" placeholder="开始日期" :picker-options="pickerOptions1" value-format="yyyy-MM-dd" style="width:150px" ref="work">
            </el-date-picker>
            <el-date-picker v-model="work_experienceList.endTime" type="date" placeholder="结束日期"  :picker-options="pickerOptions4" value-format="yyyy-MM-dd" style="width:150px">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button v-if="button==1" type="primary" @click="add(1)">添加</el-button>
            <el-button v-if="button==2" type="primary" @click="reseat">确定</el-button>
            <el-button @click="reseat(1)">取消</el-button>
          </el-form-item>
        </el-form>
        <ul style="list-style: none;padding-left: 1px;">
          <li v-for="work,i in work_experienceListArr"
           :key="i"
           style="padding: 5px 0 5px 20px;margin: 10px 0 10px 0; ">
           <div style="position: absolute; right: 60px;"><el-button type="text" @click="delete1(work)"><i class="el-icon-delete" ></i>删除</el-button>
             <el-button type="text" @click="updata1(work)"><i class="el-icon-edit"></i>修改</el-button></div>
            <p style="font-weight: 700;"><span>公司名称：</span>&nbsp<span>{{work.companyName}}</span></p>
            <p><span class="xiaotu">公司类型：</span>&nbsp<span class="miaoshu">{{work.jobFunction}}</span></p>
            <p><span class="xiaotu">工作岗位：</span>&nbsp<span class="miaoshu">{{work.jobTitle}}</span></p>
            <p><span class="xiaotu" >工作时间：</span>&nbsp
            <span class="miaoshu" v-if="work.endTime">{{work.startTime?work.startTime+" 至 "+work.endTime :"未知"+" 至 "+work.endTime}}</span>
            <span class="miaoshu" v-else-if="work.startTime">{{work.endTime?work.startTime+" 至 "+work.endTime:work.startTime+" 至 "+"未知"}}</span>
            <span class="miaoshu" v-else>{{"未知"+" 至 "+"未知"}}</span>
            </p>

          </li>
        </ul>
        <div class="tit" id="tit2">
          <b style="margin-right: 20px;">项目经厉</b>
           <i v-show="input2" class="el-icon-edit-outline i"  @click="adds(2)"></i>
        </div>
        <br/>
        <el-form v-show="!input2" ref="form" :model="project_exper" label-width="80px" style="width: 400px;margin-left: 10px;">

          <el-form-item label="项目名称" prop="jobFunction">
            <el-input v-model="project_exper.projectName"></el-input>
          </el-form-item>
          <el-form-item label="项目时间">
            <el-date-picker v-model="project_exper.joinprojectTime" type="date" placeholder="开始日期" :picker-options="pickerOptions2" value-format="yyyy-MM-dd" style="width:150px">
            </el-date-picker>
            <el-date-picker v-model="project_exper.quitprojectTime" type="date" placeholder="结束日期"  :picker-options="pickerOptions5" value-format="yyyy-MM-dd" style="width:150px">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="项目描述" prop="duty" style="width: 200%;">
              <el-input type="textarea" v-model="project_exper.duty"></el-input>
            </el-form-item>
          <el-form-item>
            <el-button v-if="button==1" type="primary" @click="add(2)">添加</el-button>
            <el-button v-if="button==2" type="primary" @click="reseat">确定</el-button>
            <el-button @click="reseat(2)">取消</el-button>
          </el-form-item>
        </el-form>

        <ul style="list-style: none;padding-left: 1px;">
          <li v-for="project in project_experience" style="padding: 5px 0 5px 20px;margin: 10px 0 10px 0;">
            <div style="position: absolute; right: 60px;"><el-button type="text" @click="delete2(project)"><i class="el-icon-delete"></i>删除</el-button>
              <el-button type="text" @click="updata2(project)"><a href="#tit2"><i class="el-icon-edit"></i>修改</a></el-button></div>
            <p style="font-weight: 700;"><span>项目名称：</span>&nbsp<span>{{project.projectName}}</span></p>
            <p><span class="xiaotu">项目时间：</span>&nbsp
            <span class="miaoshu" v-if="project.quitprojectTime">{{project.joinprojectTime?project.joinprojectTime+" 至 "+project.quitprojectTime : "未知"+" 至 "+project.quitprojectTime}}</span>
            <span class="miaoshu" v-else-if="project.joinprojectTime">{{project.quitprojectTime?project.joinprojectTime+" 至 "+project.quitprojectTime:project.joinprojectTime+" 至 "+"未知"}}</span>
            <span class="miaoshu" v-else>{{"未知"+" 至 "+"未知"}}</span>
            </p>
            <p><span class="miaoshu" style="line-height: 1.5;">{{project.duty}}</span></p>
          </li>
        </ul>
        <br>
         <div style="width:calc(100% - 220px) ; height: 50px; line-height: 50px; background-color: #fff; bottom: 0;position: fixed;right: 10px;">
           <div style="position:absolute;right: 25%;">
             <el-button  v-if="button2==1" size="medium" @click="handleQuery" class="but">取消</el-button>
             <el-button v-if="button2==1" size="medium" @click="resetQuery" type="primary">录入抢占</el-button>
             <el-button v-else size="medium" @click="resetQuery" type="primary">保存</el-button>
           </div>
         </div>

  </div>
</template>

<script>

  import {workDel, getRecord, handupdata, handInsert, projdeDel, educaDel} from "@/api/resume/record/customerinfo";
  import {debounce} from "@/utils/ruoyi.js"
  export default{
    name:"manually",
    data(){
      return{
        wen:true,
        button2:1,
        button:1,
        input3:true,
        input2:true,
        input:true,
        vadio:1,
        filelist:[],
        // 技术方向字典
        professionIdoptions: [],
        // 简历学历字典
        customerSpecialitiesoptions: [],
        // 简历性别字典
        customerSexOptions: [],
        // 简历地区字典
        intentionareaOptions:[],
        // 工作经历
        work_experienceList:{},
        work_experienceListArr: [],
        // 基础信息
        perCustomerinfo:{
          resumeDirection:1,
        },
        // 教育经历
        perEduc:{},
        perEducList:[],
        // 项目经验
        project_exper:{},
        project_experience: [],
        timers:null,

        // 时间选择禁止
        pickerOptions1:{
          disabledDate:(time) => {
            if (this.work_experienceList.endTime) {
                return time.getTime() > new Date(this.work_experienceList.endTime).getTime();
            }else{
                return time.getTime() >  Date.now()
            }
          }
        },
        pickerOptions2:{
          disabledDate:(time) => {
            if (this.project_exper.quitprojectTime) {
                return time.getTime() > new Date(this.project_exper.quitprojectTime).getTime();
            }else{
                return time.getTime() >  Date.now()
            }
          }
        },
        pickerOptions3:{
          disabledDate:(time) => {
            if (this.perEduc.endTime) {
                return time.getTime() > new Date(this.perEduc.endTime).getTime();
            }else{
                return time.getTime() >  Date.now()
            }
          }
        },
        pickerOptions4:{
          disabledDate:(time) => {
            if (this.work_experienceList.startTime){
                return time.getTime() < new Date(this.work_experienceList.startTime).getTime() ||time.getTime() >  Date.now()
            }
            return time.getTime() >  Date.now()
          }
        },
        pickerOptions5:{
          disabledDate:(time) => {
            if (this.project_exper.joinprojectTime){
                return time.getTime() < new Date(this.project_exper.joinprojectTime).getTime() ||time.getTime() >  Date.now()
            }
            return time.getTime() >  Date.now()
          }
        },
        pickerOptions6:{
          disabledDate:(time) => {
            if (this.perEduc.startTime){
                return time.getTime() < new Date(this.perEduc.startTime).getTime() ||time.getTime() >  Date.now()
            }
            return time.getTime() >  Date.now()
          }
        },
        // 表单校验
        rules: {
           customerTel: [
             { required: true, message: "手机号码不能为空", trigger: "blur" },
             {
               pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
               message: "请输入正确的手机号码",
               trigger: ["blur", "change"]
             }
           ],
          customerName:[{
            required: true,
            message: "姓名不能为空",
            trigger: ["blur", "change"]
          }, ],
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
      }
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
    mounted() {
      this.getcustomerCode()
    },
    watch:{
      "$route":"getcustomerCode"
    },
    methods:{
      upoplodad(file) {
        this.$refs.file.clearFiles()
        this.wen = true
      },
      tiems(time,endtime){
        if(endtime){
          return time.getTime() > new Date(endtime).getTime();
        }else{
          return time.getTime() > Date.now()
        }
      },
      timess(time,startime){
        if (startime) {
            return  time.getTime() < new Date(startime).getTime() ||time.getTime() >  Date.now()
        }
        return time.getTime() >  Date.now()
      },

      // 获取简历详情信息
      getcustomerCode(){
          let query = this.$route.query.customerCode
          if(query==null){
            this.handleQuery()
            this.button2=1
            this.wen=true
          }else{
            this.button2=2
            var formData = new FormData()
            formData.append("customerCode",query)
            getRecord(formData).then(res=>{
            this.perCustomerinfo = res.data.perCustomerinfo
            this.project_experience =res.data.PerProjList
            this.work_experienceListArr = res.data.PerWorList
            this.perEducList=res.data.perEducList
            if(this.perCustomerinfo.resumePath == null || this.perCustomerinfo.resumePath == ''){
              this.filelist = []
              this.wen=true
            }else{
              this.wen=false
              this.filelist = [{name:`${this.perCustomerinfo.resumePath.slice(this.perCustomerinfo.resumePath.lastIndexOf('/')+1,-1)}`,file:this.perCustomerinfo.resumePath}]
            }
            })
          }
      },

      //保存
      resetQuerys(but){
          this.$refs["forms"].validate((valid) => {
            if (valid) {
              let zm = {
                perCustomerinfo:this.perCustomerinfo,
                PerWorList:this.work_experienceListArr,
                PerProjList:this.project_experience,
                perEducList:this.perEducList,
              }
              zm = JSON.stringify(zm)
              let file = null
              if (this.$refs.file.uploadFiles[0] == null) {
                file={}
              }else{
                file = this.$refs.file.uploadFiles[0].raw
              }
              var formData = new FormData()
              formData.append("zm",zm)
              formData.append("file",file)
              if(but==1){
                handInsert(formData).then(response => {
                  this.handleQuery()
                  this.msgSuccess("添加成功")
                })
              }else if(but==2){
                handupdata(formData).then(response => {
                 this.getcustomerCode()
                 this.msgSuccess("修改成功")
              })
              }
            }
          });
      },
      resetQuery:debounce(function(){this.resetQuerys(this.button2)},1000),
      handleExceed() {
        this.msgError(`当前限制选择 1 个文件`);
      },
      adds(i){
        this.button=1
        if(i==1){
          this.input = !this.input
        }else if(i==2){
          this.input2 = !this.input2
        }else if(i==3){
          this.input3 = !this.input3
        }
      },
      // 添加信息
      add(inp){
      if(inp==1){
        if(this.work_experienceListArr.length>=5){
           this.msgError("最多添加五条")
        }else{
        this.work_experienceListArr.push(JSON.parse(JSON.stringify(this.work_experienceList)));}
      }else if(inp==2){
        if(this.project_experience.length>=5){
          this.msgError("最多添加五条")
        }else{
        this.project_experience.push(JSON.parse(JSON.stringify(this.project_exper)));}
      }else{
        if(this.perEducList.length>=5){
          this.msgError("最多添加五条")
        }else{
        this.perEducList.push(JSON.parse(JSON.stringify(this.perEduc)));}
      }
        this.reseat(inp)
      },
      oplodad(file) {
        // console.log(file)

        this.wen = false
      },
      // 取消
      reseat(ind){
        if(ind ==1){
          this.input=true
        }else if(ind ==2){
          this.input2=true
        }else if(ind ==3){
          this.input3=true
        }
        this.project_exper={}
        this.work_experienceList={}
        this.perEduc={}
      },
      updata2(project){
        this.button=2
        this.input2 = false
        this.project_exper=project
      },
      updata1(work){
        this.button=2
        this.input = false
        this.work_experienceList=work
      },
      updata3(work){
        this.button=2
        this.input3 = false
        this.perEduc=work
      },


       splices(arr,data){
         let i = arr.indexOf(data)
         return arr.splice(i,1)
       },
      delete1(work){
        let that = this
        this.$confirm('是否删除这条教育经历', "警告", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(function() {
            if(work.id){
            var data ={
              id:work.id
            }
            return workDel(data).then(res=>{
              that.splices(that.work_experienceListArr,work)
            })
            }else{
            return that.splices(that.work_experienceListArr,work)
            }
          }).then(() => {
            this.msgSuccess("删除成功");
          })
      },
      delete2(project){
        let that = this
        this.$confirm('是否删除这条工作经历', "警告", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(function() {
            if(project.custproId){
            var data ={
              custproId:project.custproId
            }
            return projdeDel(data).then(res=>{
              that.splices(that.project_experience,project)
            })

            }else{
            return  that.splices(that.project_experience,project)
            }
          }).then(() => {
            this.msgSuccess("删除成功");
          })
      },
      delete3(work){
        let that = this
        this.$confirm('是否删除这条教育经历', "警告", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(function() {
            if(work.id){
            var data ={
              id:work.id
            }
            return educaDel(data).then(res=>{
              that.splices(that.perEducList,work)
            })
            }else{
            return that.splices(that.perEducList,work)
            }
          }).then(() => {
            this.msgSuccess("删除成功");
          })
      },
      handleQuery(){
        this.$refs.file.clearFiles()
        this.reset()
      },
      reset(){
        this.perEducList=[]
        this.perCustomerinfo = {
            resumeDirection:1,
        }
        this.project_experience = []
        this.work_experienceListArr=[]
        this.resetForm("forms");
      },
    }
  }
</script>

<style scoped>
  .upload-demo{
    width: 360px;
  }
  .xiaotu{
    color: #909399;
  }
  .miaoshu{
   color: #606266;
  }
  .i{
      color: #1890FF;
  }
  .i:hover{
    cursor:pointer
  }
  .tit {
    height: 42px;
    background: #F5F5F9;
    line-height: 42px;
    padding-left: 20px;
  }
</style>
