<template>
  <div>
      <el-dialog title="绑定" :visible.sync="bangding.open2" width="500px" append-to-body >
        <el-input v-model="searchmsg" placeholder="请输入姓名或电话" clearable size="small" @keyup.enter.native="search" style="width: 170px;margin-right: 10px;"/>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="search">查询</el-button>
        <p></p>
        <div style="height: 300px; overflow: auto;">
          <el-radio-group v-model="corpnamelist" v-loading="bangding.loading2">
               <el-radio v-for="city in bangding.templist2" :label="city.customer_code" :key="city.customer_code" style="width:155px;margin-bottom: 10px;">{{city.customer_name}}-{{city.customer_tel}}</el-radio>
          </el-radio-group>
        </div>
        <div v-show="bangding.templist2.length==0 && bangding.forms.length!==0" style="text-align: center;
        ">暂无该简历信息</div>
        <div v-show="bangding.templist2.length==0 && bangding.forms.length==0" style="text-align: center; ">暂无简历信息,先去抢占几份去吧</div>

        <div slot="footer" class="dialog-footer" >
          <el-button type="primary" @click="submitForm">确定</el-button>
          <el-button @click="bangding.open2=false">取 消</el-button>
        </div>
      </el-dialog>

      <el-dialog :visible.sync="opens" width="600px" :title="titles" append-to-body>
          <p></p>
          <span style="color: #303133;">简历是否满足要求</span>
          <div style="padding: 10px 0  0 22px;">
            <div style="display: flex;" v-for="item,i in ganglist">
              <div style="width: 80%; background: #f7f9fd;margin: 10px 20px 0 0;padding:10px 25px;color: #303133;"><span style="color: red;position: relative;margin-left: -13px;" v-if="item.isNecessary==1">★</span> {{item.sort}}、{{item.jobRequirements}}</div>
              <el-radio style="margin-top: 15px;" v-model="item.xuanze" :label="1">是</el-radio>
              <el-radio style="margin-top: 15px;" v-model="item.xuanze" :label="2">否</el-radio>
            </div>
            <br>
            <br>
            <span style="color: red;" >★</span> <span>加星为岗位必须满足，简历不满足不能绑定</span>
            <br>
            <br>
            <el-tag>你最近10天的简历通过率为{{format}}%</el-tag>
          </div>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForms">确定</el-button>
            <el-button @click="opens=false">取 消</el-button>
          </div>
      </el-dialog>
      <el-dialog :visible.sync="open1" width="70%" :title="titles" append-to-body :close-on-click-modal="false">
        <p></p>
        <span><svg-icon icon-class="jiben" class-name="card-panel-icon" /></span><span class="tit">基本信息</span>
        <div class="magen">
          <el-form  :model="perCustomerinfo"  label-width="80px" :inline="true" ref="forms" label-position="right" :rules="rules">
            <el-form-item label="姓名" prop="customerName">
              <el-input  v-model="perCustomerinfo.customerName" size="small"   suffix-icon="xxx"/>
            </el-form-item>
            <el-form-item label="性别" prop="customerSex">
              <el-select v-model='perCustomerinfo.customerSex' placeholder="" size="small">
                <el-option v-for="dict in customerSpecialitiesoptions" :key="dict.dictValue" :label="dict.dictLabel"
                  :value="parseInt(dict.dictValue)" />
              </el-select>
            </el-form-item>
            <el-form-item label="年龄" prop="customerAge">
              <el-input v-model.number="perCustomerinfo.customerAge" size="small" suffix-icon="xxx"/>
            </el-form-item>
            <el-form-item label="工作年限" prop="workYear">
              <el-select v-model='perCustomerinfo.workYear' placeholder="" size="small">
                <el-option v-for="dict in 10" :key="dict" :label="dict+'年'" :value="dict" suffix-icon="xxx"/>
              </el-select>
            </el-form-item>
            <el-form-item label="职位" prop="professionId" >
             <el-input  v-model="perCustomerinfo.professionId" size="small"   suffix-icon="xxx"/>
            </el-form-item>
          </el-form>
        </div>
        <span><svg-icon icon-class="jiaoyu" class-name="card-panel-icon" /></span><span class="tit">专业技能</span><span style="color: red;font-size: 14px;">专业技能不能少于2条</span>
        <i  class="el-icon-circle-plus-outline i"  @click="adds(4)"></i>
        <div class="magen">
            <el-form v-for="item,i in PerEnclosureSkills " :key="i" :model="item" label-width="80px" ref="forms1" label-position="right" :rules="rules">
              <el-form-item label="技能描述" prop="skillsDescribe">
                <el-input size="small" v-model="item.skillsDescribe" suffix-icon="xxx"></el-input>
              </el-form-item>
            </el-form>
        </div>
        <p></p>
        <br>
        <span id="gong"><svg-icon icon-class="work" class-name="card-panel-icon" /></span><span class="tit">工作经历</span><span style="color: red;font-size: 14px;">工作经历不能少于1条</span><i  class="el-icon-circle-plus-outline i"  @click="adds(1)"></i>
        <div class="magen">
          <el-form v-for="item,i in PerEnclosureWork" :key="i" :model="item"  label-width="80px" :inline="true" ref="forms2" label-position="right" :rules="rules">
            <el-form-item label="开始时间" prop="startTime">
              <el-date-picker v-model="item.startTime" type="date" placeholder="开始日期" :picker-options="pickerOptions1" size="small" value-format="yyyy-MM-dd" style="width:199px" ref="work">
              </el-date-picker>
              </el-date-picker>
            </el-form-item>
            <el-form-item label="结束时间" prop="endTime">
              <el-date-picker size="small" v-model="item.endTime" type="date" placeholder="结束日期"  :picker-options="pickerOptions4" value-format="yyyy-MM-dd" style="width:199px">
              </el-date-picker>
            </el-form-item>
            <br>
            <el-form-item label="公司名称" prop="companyName">
              <el-input size="small" v-model="item.companyName" suffix-icon="xxx"></el-input>
            </el-form-item>
            <el-form-item label="职位名称" prop="jobTitle">
              <el-input size="small" v-model="item.jobTitle" suffix-icon="xxx"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <p></p>
        <br>
        <span id="xiang"><svg-icon icon-class="xiangmu" class-name="card-panel-icon" /></span><span class="tit">项目经厉</span><span style="color: red;font-size: 14px;">项目经历不能少于2条</span>
        <i class="el-icon-circle-plus-outline i"  @click="adds(2)"></i>
        <div class="magen">
          <el-form v-for="item,i in projectexper" :key="i" :model="item"  label-width="80px" :inline="true" ref="forms3" label-position="left" :rules="rules">
            <el-form-item label="开始时间" prop="startTime">
              <el-date-picker v-model="item.startTime" type="date" placeholder="开始日期" :picker-options="pickerOptions1" size="small" value-format="yyyy-MM-dd" style="width:199px" ref="work">
              </el-date-picker>
              </el-date-picker>
            </el-form-item>
            <el-form-item label="结束时间" prop="endTime">
              <el-date-picker size="small" v-model="item.endTime" type="date" placeholder="结束日期"  :picker-options="pickerOptions4" value-format="yyyy-MM-dd" style="width:199px">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="项目名称" prop="projectName" >
              <el-input size="small" v-model="item.projectName" suffix-icon="xxx" ></el-input>
            </el-form-item>
            <br>
            <el-form-item class="qqq" label="项目描述" prop="duty">
               <el-input size="small" type="textarea" v-model="item.duty" ></el-input>
            </el-form-item>
            <br>
            <el-form-item class="qqq" label="职责描述" prop="zhize">
               <el-input size="small" type="textarea" v-model="item.zhize" ></el-input>
            </el-form-item>
          </el-form>
        </div>
        <p></p>
        <br>
        <span><svg-icon icon-class="jiaoyu" class-name="card-panel-icon" /></span><span class="tit">教育经历</span><span style="color: red;font-size: 14px;">教育经历不能少于1条</span><i class="el-icon-circle-plus-outline i"  @click="adds(3)"></i>
        <div class="magen">
            <el-form v-for="item,i in perEduc" :key="i" :model="item"  label-width="80px" :inline="true" ref="forms4" label-position="left" :rules="rules">
              <el-form-item label="开始时间" prop="startTime">
                <el-date-picker v-model="item.startTime" type="date" placeholder="开始日期" :picker-options="pickerOptions1" size="small" value-format="yyyy-MM-dd" style="width:199px" ref="work">
                </el-date-picker>
                </el-date-picker>
              </el-form-item>
              <el-form-item label="结束时间" prop="endTime">
                <el-date-picker size="small" v-model="item.endTime" type="date" placeholder="结束日期"  :picker-options="pickerOptions4" value-format="yyyy-MM-dd" style="width:199px">
                </el-date-picker>
              </el-form-item>
              <el-form-item label="所在学校" prop="schoolName" >
                <el-input size="small" v-model="item.schoolName" suffix-icon="xxx" ></el-input>
              </el-form-item>
              <el-form-item label="学历" prop="degree" >
                <el-select v-model='item.degree' filterable placeholder="" size="small">
                  <el-option v-for="dict in professionIdoptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
                </el-select>
              </el-form-item>
              <el-form-item label="专业" prop="major" >
                <el-input size="small" v-model="item.major" suffix-icon="xxx"></el-input>
              </el-form-item>
            </el-form>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm2">确定</el-button>
          <el-button @click="open1=false">取 消</el-button>
        </div>
      </el-dialog>
  </div>
</template>

<script>
  import {
    delFollow,
    queryResumeSalary,
    acquisitionList,
    getid
  } from "@/api/demand/binding";
  import { debounce } from "@/utils/ruoyi"
  export default {
    props:{
      bangding:{
        type:Object,
      },
    },
    data(){
      return {
        pickerOptions1:{},
        pickerOptions4:{},
        corpnamelist:null,
        opens:false,
        titles:"",
        radio:null,
        searchmsg:"",
        wen:true,
        timer:null,
        ganglist:[],
        idlist:[],
        format:"",
        open1:false,
        formdata:null,
        perCustomerinfo:{},
        rules: {
          customerSex:[{
            required: true,
            message: "性别不能为空",
            trigger: ["blur", "change"]
          }, ],
          customerAge:[{
            required: true,
            message: "年龄不能为空",
            trigger: ["blur", "change"]
          }, ],
          skillsDescribe:[{
            required: true,
            message: "技能描述不能为空",
            trigger: ["blur", "change"]
          }, ],
          customerName:[{
            required: true,
            message: "姓名不能为空",
            trigger: ["blur", "change"]
          }, ],
          customerBirth:[{
            required: true,
            message: "出生年月不能为空",
            trigger: ["blur", "change"]
          },],
          customerUniversityTime:[{
            required: true,
            message: "毕业时间不能为空",
            trigger: ["blur", "change"]
          }],
          customerUniversity:[{
            required: true,
            message: "毕业学校不能为空",
            trigger: ["blur", "change"]
          }],
          education: [{
            required: true,
            message: "学历不能为空",
            trigger: ["blur", "change"]
          },],
          startTime:[{
            required: true,
            message: "开始时间不能为空",
            trigger: ["blur", "change"]
          },],
          endTime:[{
            required: true,
            message: "结束时间不能为空",
            trigger: ["blur", "change"]
          },],
          companyName:[{
            required: true,
            message: "公司名称不能为空",
            trigger: ["blur", "change"]
          }],
          jobTitle:[{
            required: true,
            message: "职位名称不能为空",
            trigger: ["blur", "change"]
          }],
          projectName:[{
            required: true,
            message: "项目名称不能为空",
            trigger: ["blur", "change"]
          }],
          duty:[{
            required: true,
            message: "项目描述不能为空",
            trigger: ["blur", "change"]
          }],
          zhize:[{
            required: true,
            message: "职责描述不能为空",
            trigger: ["blur", "change"]
          }],
          schoolName:[{
            required: true,
            message: "所在学校不能为空",
            trigger: ["blur", "change"]
          }],
          degree:[{
            required: true,
            message: "学历不能为空",
            trigger: ["blur", "change"]
          }],
          major:[{
            required: true,
            message: "专业不能为空",
            trigger: ["blur", "change"]
          }],
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
        },
        customerSpecialitiesoptions:[],
        professionIdoptions:[],
        PerEnclosureWork:[],
        projectexper:[],
        perEduc:[],
        PerEnclosureSkills:[],

      }
    },
    watch:{
      "bangding.open2"(newValue, oldValue){
        if(newValue==true){
          this.searchmsg =""
        }
      }
    },
    created(){
      this.getDicts("sys_user_sex").then(response => {
        this.customerSpecialitiesoptions = response.data;
      });
      this.getDicts("per_customerinfo_professionid").then(response => {
        this.professionIdoptions = response.data;
      });
    },
    methods:{
      // 搜索简历
      search(){
        this.corpnamelist=null
        let that = this
        that.bangding.loading2=true
        if(that.searchmsg==""){
        setTimeout(function(){
          that.bangding.templist2=that.bangding.forms
          that.bangding.loading2=false
        },1000)
        }else{
          clearInterval(that.timer)
          that.timer=setTimeout(function(){
            var data= that.bangding.forms.filter(item=>{
              return item.customer_name == that.searchmsg || item.customer_tel==that.searchmsg
             })
            that.bangding.templist2 = data
            that.bangding.loading2=false
          },1000)
        }
      },
      adds(ind){
        var data = {
        }
        if(ind==1){
          this.PerEnclosureWork.push(data)
        }else if(ind==2){
          this.projectexper.push(data)
        }else if(ind==3){
          this.perEduc.push(data)
        }else{
          this.PerEnclosureSkills.push(data)
        }
      },
      // 确定按钮
      submitForm(){
          if(this.corpnamelist==null){
            this.msgError("请选择简历")
          }else{
            let form= new FormData()
            form.append("customerCode",this.corpnamelist)
            queryResumeSalary(form).then(res=>{
              if(res=="可以绑定"){
                acquisitionList(this.bangding.id).then(res=>{
                  this.ganglist = res.data.marDemandRequirements
                  this.format = res.data.format
                  getid(form).then(res=>{
                    this.perCustomerinfo = res.data.perCustomerinfo
                  })
                  if(this.ganglist.length==0){
                    this.open1 = true
                    this.titles = "补充简历模板信息"
                  }else{
                    this.formdata = null
                    this.titles = "绑定要求"
                    this.opens = true
                    this.idlist = []
                  }
                })
              }else{
                let that = this
                this.$confirm('该简历与需求不符"'+ res+'"是否去修改', "警告", {
                  confirmButtonText: "去修改",
                  cancelButtonText: "取消",
                  type: "warning"
                }).then(function(){
                    that.$router.push({path:"/record/manually",query:{customerCode:that.corpnamelist}});
                }).then(() => {
                  this.bangding.open2=false
                }).catch(()=>{})
              }
            })
          }
        },

        bixuan(){
          var a;
          this.ganglist.forEach(item=>{
            if(item.isNecessary == 1){
              if(item.xuanze !== 1){
                a = 1
              }
            }
          })
          return a
        },
        submitForms:debounce(function(){this.submitFor()}),
        submitFor(){
          if(this.bixuan()==1){
            this.msgError("必须满足项没有选择")
          }else{
              this.ganglist.forEach(item=>{
                if(item.xuanze == 1){
                  this.idlist.push({requirementsId:item.id,sort:item.sort})
                }
              })
              this.titles = "补充简历模板信息"
              this.open1 = true
          }
        },
        bitian(){
          var a = 0
          this.$refs["forms"].validate((valid) => {
            if (valid) {
              a+=1
            }
           })
          this.$refs["forms1"].forEach(item=>{
            item.validate((valid) => {
              if (valid) {
                a+=1
              }
            })
          })
          this.$refs["forms2"].forEach(item=>{
            item.validate((valid) => {
              if (valid) {
                a+=1
              }
            })
          })
          this.$refs["forms3"].forEach(item=>{
            item.validate((valid) => {
              if (valid) {
                a+=1
              }
            })
          })
          this.$refs["forms4"].forEach(item=>{
            item.validate((valid) => {
              if (valid) {
                a+=1
              }
            })
          })
          if(a == 0){
            return true
          }
        },
        submitForm2(){
          if(!this.bitian()){
            let zm={
              customerCode:this.corpnamelist,
              demandId:this.bangding.id,
              marDemandresumeRequirement:this.idlist,
              perEnclosureCustomerinfo:this.perCustomerinfo,
              perEnclosureEducation:this.perEduc,
              perEnclosureSkills:this.PerEnclosureSkills,
              perEnclosureProject:this.projectexper,
              perEnclosureWorkList:this.PerEnclosureWork
            }
            let formdata = new FormData()
            formdata.append("zm",JSON.stringify(zm))
            delFollow(formdata).then(res=>{
              this.msgSuccess("绑定成功")
              this.opens=false
              this.bangding.open2=false
              this.$emit("gettelist")
              this.$emit("getList")
            })
          }
        },
        oplodad(){
            this.wen = false
        },
        handleExceed(){
          this.msgError(`当前限制选择 1 个文件`);
        },
        upoplodad(){
          this.$refs.file.clearFiles()
          this.wen = true
        },
    }

  }
</script>

<style scoped>

  .tit{
    font-weight: 700;
    margin-right: 10px;
    font-size: 18px;
    color: #303133;
  }
  .card-panel-icon{
    margin-left: 25px;
    margin-right:10px;
    color: #303133;
  }
  .magen{
    margin-top: 20px;
    padding:0 90px 0 55px;
  }
  .i{
    float: right;
    margin-right: 45px;
    font-size: 20px;
  }
  .qqq{
    width: 100%;
  }
  .qqq >>>.el-form-item__content {
    width:calc(100% - 80px) ;
  }
</style>
