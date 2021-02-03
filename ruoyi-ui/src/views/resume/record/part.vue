<template>
  <div class="app-container">
    <div style="display: flex;">
       <!-- 左边信息栏 -->
      <div style="width: 90%; padding-right: 10px;">
          <div class="tit">
            <b style="margin-right: 20px;">基本信息</b>
          </div>
          <div v-if="perro.addTime">
            <ul style="list-style: none;">
              <li class="li">
                <span class="time">
                  <p>{{perro.addTime}}</p>
                  <p style="color: #1890ff;">{{friendlyDate(perro.addTime)}}</p>
                  <p></p>
                </span>
                <span class="span s">
                <p><b>占有</b></p>
                <p>占有人：{{perro.addName}}</p>
                <p>{{perro.addName}} 于 {{perro.addTime}} 占有该简历</p>
                </span>
              </li>
              <li class="li">
                <span class="time">
                  <p>{{perro.editTime}}</p>
                  <p style="color: #1890ff;">{{friendlyDate(perro.editTime)}}</p>
                  <p></p>
                </span>
                <span class="span">
                <p><b>释放</b></p>
                <p>该简历占有人：{{perro.addName}}</p>
                <p>于 {{perro.editTime}} 释放</p>
                </span>
              </li>
            </ul>
          </div>
          <div style="margin-top: 20px; padding-left: 20px;">
            <el-form  :model="perCustomerinfo"  label-width="70px" :inline="true" ref="form" label-position="right" >
              <el-form-item label="姓名" prop="customerName">
                <el-input v-model="perCustomerinfo.customerName" size="small"  disabled suffix-icon="xxx"/>
              </el-form-item>
              <el-form-item label="电话" prop="customerTel">
                <el-input v-model="perCustomerinfo.customerTel" size="small"  disabled suffix-icon="xxx"/>
              </el-form-item>
              <el-form-item label="性别" prop="customerSex">
                <el-select v-model="perCustomerinfo.customerSex" placeholder="" size="small" disabled>
                  <el-option v-for="dict in customerSexOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="parseInt(dict.dictValue)" />
                </el-select>
              </el-form-item>
              <el-form-item label="出生年月" prop="customerBirth">
                <el-input v-model="perCustomerinfo.customerBirth" size="small"  suffix-icon="xxx" disabled/>
              </el-form-item>
              <el-form-item label="工作经验" prop="workYear">
                <el-select v-model='perCustomerinfo.workYear' placeholder="" size="small" disabled>
                  <el-option v-for="dict in 10" :key="dict" :label="dict+'年'" :value="dict" />
                </el-select>
              </el-form-item>
              <el-form-item label="毕业学院" prop="customerUniversity">
                <el-input v-model="perCustomerinfo.customerUniversity" size="small"  suffix-icon="xxx" disabled/>
              </el-form-item>
              <el-form-item label="学历" prop="education">
                <el-select v-model='perCustomerinfo.education' placeholder="" size="small" disabled>
                  <el-option v-for="dict in customerSpecialitiesoptions" :key="dict.dictValue" :label="dict.dictLabel"
                    :value="parseInt(dict.dictValue)" />
                </el-select>
              </el-form-item>

              <el-form-item label="技术方向" prop="professionId">
                <el-select v-model='perCustomerinfo.professionId' placeholder="" size="small" disabled>
                  <el-option v-for="dict in professionIdoptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
                </el-select>
              </el-form-item>
              <el-form-item label="期望薪资" prop="expectationSalary">

                <el-input v-model="perCustomerinfo.expectationSalary" size="small"  suffix-icon="xxx" disabled/>
              </el-form-item>
              <el-form-item label="邮箱" prop="email">
                <el-input v-model="perCustomerinfo.email" size="small" suffix-icon="xxx" disabled/>
              </el-form-item>

              <el-form-item label="意向城市" prop="intentionArea">
                <el-select v-model='perCustomerinfo.intentionArea' placeholder="" size="small" disabled>
                  <el-option v-for="dict in intentionareaOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
                </el-select>
              </el-form-item>
              <el-form-item label="录入时间" prop="email">
                <el-input v-model="perCustomerinfo.addTime" size="small" suffix-icon="xxx" disabled/>
              </el-form-item>
            </el-form>
          </div>
          <div class="tit">
                <b style="margin-right: 20px;">入项信息</b>
          </div>
          <div>
            <ul style="list-style: none;" class="font">
              <li v-if="mapList.length ==0">
                <span>暂无入项信息</span>
              </li>
              <li  v-else v-for="map in mapList" style="padding-left: 20px; margin-top: 20px;">
                {{perCustomerinfo.customerName}} 于 {{map.trackingtime}} 入项 {{map.corp_name}} 公司项目

              </li>
            </ul>
          </div>
          <br/>
          <div class="tit">
            <b style="margin-right: 20px;">教育经历</b>
          </div>
          <ul style="list-style: none;" class="font">
            <li v-for="work,i in perEducList"
             :key="i"
             style=";padding: 5px 0 5px 20px;margin: 10px 0 10px 0; ">
              <p class="biaoti"><span >学校名称：</span>&nbsp<span >{{work.schoolName}}</span></p>
              <p><span class="xiaotu">学<span style="display: inline-block; width: 30px;"></span>历：</span>&nbsp<span class="miaoshu" >{{work.degree}}</span></p>
              <p><span class="xiaotu">专<span style="display: inline-block; width: 30px;"></span>业：</span>&nbsp<span class="miaoshu" >{{work.major}}</span></p>
              <p><span class="xiaotu"> 时 间 段 :</span>&nbsp
              <span class="miaoshu"  v-if="work.endTime">{{work.startTime?work.startTime+" 至 "+work.endTime :"未知"+" 至 "+work.endTime}}</span>
              <span class="miaoshu"  v-else-if="work.startTime">{{work.endTime?work.startTime+" 至 "+work.endTime:work.startTime+" 至 "+"未知"}}</span>
              <span  class="miaoshu" v-else>{{"未知"+" 至 "+"未知"}}</span>
              </p>
            </li>
          </ul>

          <div class="tit">
            <b style="margin-right: 20px; margin-bottom: 20px;">工作经历</b>
          </div>
          <p></p>
          <ul style="list-style: none;" class="font">
            <li v-for="work in work_experienceListArr" style="padding: 5px 0 5px 20px;margin: 10px 0 10px 0;">
              <p class="biaoti"><span >公司名称：</span>&nbsp<span >{{work.companyName?work.companyName:"未知"}}</span></p>
              <p><span class="xiaotu">公司类型：</span>&nbsp<span class="miaoshu" >{{work.jobFunction?work.jobFunction:"未知"}}</span></p>
              <p><span class="xiaotu">工作岗位：</span>&nbsp<span class="miaoshu" >{{work.jobTitle?work.jobTitle:"未知"}}</span></p>
              <p><span class="xiaotu">工作时间：</span>&nbsp
              <span class="miaoshu"  v-if="work.endTime">{{work.startTime?work.startTime+" 至 "+work.endTime :"未知"+" 至 "+work.endTime}}</span>
              <span class="miaoshu"  v-else-if="work.startTime">{{work.endTime?work.startTime+" 至 "+work.endTime:work.startTime+" 至 "+"未知"}}</span>
              <span class="miaoshu"  v-else>{{"未知"+" 至 "+"未知"}}</span></p>
            </li>
          </ul>
          <div class="tit">
            <b style="margin-right: 20px;">项目经厉</b>
          </div>
          <ul style="list-style: none;" class="font">
            <li v-for="project in project_experience" style="padding: 5px 0 5px 20px;margin: 10px 0 10px 0; ">
              <p class="biaoti"><span >项目名称：</span>&nbsp<span >{{project.projectName}}</span></p>
              <p><span class="xiaotu">项目时间：</span>&nbsp<span class="miaoshu"  v-if="project.quitprojectTime">{{project.joinprojectTime?project.joinprojectTime+" 至 "+project.quitprojectTime : "未知"+" 至 "+project.quitprojectTime}}</span>
            <span class="miaoshu"  v-else-if="project.joinprojectTime">{{project.quitprojectTime?project.joinprojectTime+" 至 "+project.quitprojectTime:project.joinprojectTime+" 至 "+"未知"}}</span>
            <span class="miaoshu"  v-else>{{"未知"+" 至 "+"未知"}}</span></p>
              <p><span class="miaoshu"  style="line-height: 1.5;">{{project.duty}}</span></p>
            </li>
          </ul>
          <br>

          <div class="tit">
            <b style="margin-right: 20px;">跟踪记录</b>
          </div>
          <el-table :data="tableData"  style="width: 100%;margin-top: 20px;padding-left: 10px;">
            </el-table-column>
            <el-table-column prop="contact_time" label="跟踪时间"width="200" >
              </el-table-column>
              <el-table-column prop="nick_name" label="跟踪人" width="100">
            </el-table-column>
            <el-table-column prop="update_static" label="跟踪状态"  :formatter="updatestaticFormat" width="100">
            </el-table-column>
            <el-table-column prop="follow_status" label="简历状态"   :formatter="followstatusFormat" width="100">
            </el-table-column>
            <el-table-column prop="memo_detail" label="跟踪情况"  >
            </el-table-column>

          </el-table>
      </div>

      <!-- 右边操作栏 -->
      <div style="position: fixed;right: 25px;top: 85px;">
        <el-button plain style="margin-left: 0; display: block;margin-top: 30px;" v-for="but,index in button" :type="but.type" :disabled="but.disabled" :key="index" @click="buttoncli(index)">{{but.name}}</el-button>
      </div>
      <el-dialog title="预览" :visible.sync="open2" width="70%">
       <iframe
          :src="src"
          style="overflow: auto; position: absolute; top: 40px; right: 0; bottom: 0; left: 0; width: 100%; height:1000%; border: none;"
        ></iframe>
      </el-dialog>
      <mytrack :open="open" :title="title" :form="form" @getlist="getcustomerCode"></mytrack>
    </div>
  </div>
</template>

<script>
  import {
    getRecord,
    addRecord,
    delRecord,
  } from "@/api/resume/record/customerinfo";
  import{genzongbut}from'@/api/resume/mytrckresume.js'
  import store from "@/store";
  import {debounce} from "@/utils/ruoyi.js"
  import mytrack from "../../components/resume/mytrack.vue"
  export default{
    name:"part",
    components:{
      mytrack
    },
    data(){
      return {
        title:"",
        open:{
          opens:false,
        },
        name:store.getters.name,
        // 简历预览
        open2:false,
        // 入项信息
        mapList:[],
        // 按钮数据
        button:[
          {name:"抢占简历",type:"warning",disabled:true},
          {name:"跟踪简历",type:"warning",disabled:true},
          {name:"预览简历",type:"primary",disabled:true},
          {name:"下载原版",type:"xiazai",disabled:true},
          {name:"更改简历",type:"success",disabled:true},
          {name:"释放简历",type:"danger",disabled:true}
        ],
        // 抢占信息
        perro:{},
        // 表格数据
        tableData: [],
        // 教育经验
        perEducList:[],
        src:null,
        // 项目经验
        project_experience: [],
        // 工作经历
        work_experienceListArr: {},
        // 列表数据请求
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        // 接收基础信息
         perCustomerinfo:{},
         // 技术方向字典
         professionIdoptions: [],
         // 简历学历字典
         customerSpecialitiesoptions: [],
         // 简历性别字典
         customerSexOptions: [],
         // 简历地区字典
         intentionareaOptions:[],
         // 跟踪状态字典
         updatestatic:[],
         timer:null,
         // 简历状态字典
         followstatus:[],
         form:{},
         rules:{
           memoDetail: [
           { required: true, message: "跟踪情况不能为空", trigger: "blur" }
         ],
         },
      }
    },
    created(){
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
      this.getDicts("per_cuscontact_updatestatic").then(response => {
              this.updatestatic = response.data;
      });
      this.getDicts("mar_demandresumefollow_status").then(response => {
              this.followstatus = response.data;
      });
      this.getDicts("per_customerinfo_intentionarea").then(response => {
        this.intentionareaOptions = response.data;
      });
       this.getcustomerCode()
    },
    methods:{
      // 获取简历详情信息
      getcustomerCode(){
          var formData = new FormData()
          formData.append("customerCode",this.$route.query.customerCode)
          getRecord(formData).then(res=>{
          this.perCustomerinfo = res.data.perCustomerinfo
          this.project_experience =res.data.PerProjList
          this.work_experienceListArr = res.data.PerWorList
          this.tableData = res.data.perCuscontactList
          this.perro = res.data.perrobcustomer
          this.mapList = res.data.mapList
          this.perEducList=res.data.perEducList
          let reg = /^(\d{3})\d{4}(\d{4})$/;
          if(this.perro !== null){
            this.button[0].disabled = true
            if(this.perro.addPeople===this.name){
              this.button[4].disabled = false
              this.button[1].disabled = false
              this.button[5].disabled = false
              this.button[2].disabled = false
              this.button[3].disabled = false
            }else{
              this.perCustomerinfo.customerTel = this.perCustomerinfo.customerTel.replace(reg, "$1****$2");
            }
          }else{
            this.perro={}
            this.button[0].disabled = false
            this.perCustomerinfo.customerTel = this.perCustomerinfo.customerTel.replace(reg, "$1****$2");
          }
        })

      },

      // 跟踪字典翻译
      updatestaticFormat(row, column) {
        return this.selectDictLabel(this.updatestatic, row.update_static);
      },
      // 简历状态字典翻译
      followstatusFormat(row, column) {
        return this.selectDictLabel(this.followstatus, row.follow_status);
      },
      // 按钮操作
      buttoncli(val){
        if (val==0) {
          // 抢占简历
          var formData = new FormData()
          formData.append("customerCode",this.perCustomerinfo.customerCode)
          addRecord(formData).then(
          this.getcustomerCode(),
          this.msgSuccess("抢占成功"),
          )
        }else if(val==2){
          // 预览简历
          if(this.perCustomerinfo.resumePath==""||this.perCustomerinfo.resumePath==null){
            this.msgError("该简历暂无原版")
          }else{
            let srcs = process.env.VUE_APP_BASE_API+this.perCustomerinfo.resumePath
            this.src=`https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=${srcs}`
            this.open2=true
          }
        }else if(val==3){
          // 下载原版
         if(this.perCustomerinfo.resumePath==""||this.perCustomerinfo.resumePath==null){
           this.msgError("该简历暂无原版")
         }else{
           let srcs = process.env.VUE_APP_BASE_API+this.perCustomerinfo.resumePath
           window.open(srcs, '_blank');
         }
        }else if(val==4){
          // 更新简历信息
           this.$router.push({path:"/record/manually",query:{customerCode:this.perCustomerinfo.customerCode}});
        }else if(val==1){
          this.form = {}
          // 跟踪简历
          this.open.opens=true
          this.title = "简历跟踪"
          this.form.contactCustomercode = this.perCustomerinfo.customerCode
          this.form.updateStatic = 2
        }else if(val==5){
          // 释放简历
          var formData = new FormData()
          formData.append("customerCode",this.perCustomerinfo.customerCode)
          this.$confirm('是否确认释放简历编号为"' + this.$route.query.customerCode + '"的数据项?', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
              return delRecord(formData);
            }).then(() => {
              this.button[4].disabled = true
              this.button[1].disabled = true
              this.button[5].disabled = true
              this.button[2].disabled = true
              this.button[3].disabled = true
              this.getcustomerCode()
              this.msgSuccess("释放成功");
            })
        }
      },

    },
  }
</script>

<style scoped>
  .biaoti{
    font-weight: 700;
  }
  .time{
    width: 80px;
  }
  .li{display: flex; font-size: 14px;}
  .span{
    border-left: 1px solid grey;
    padding: 0  0  10px 10px;
  }
  .s{
    border-bottom: 1px solid grey;
  }

  >>>.el-form-item--medium .el-form-item__label{
    height: 36px;
  }
  .tit {
    height: 42px;
    background: #F5F5F9;
    line-height: 42px;
    padding-left: 20px;
  }
  >>>.el-form-item label:after {
        content: " ";
        display: inline-block;
        width: 100%;
      }
  >>>.el-form-item__label {
      text-align: justify
  }
  .font{
    font-size: 14px;
    padding-left: 1px !important;
  }
  >>>.el-input.is-disabled .el-input__inner{
      color: #000000a6 !important;
  }
  .xiaotu{
    color: #909399;
  }
  .miaoshu{
   color: #606266;
  }

</style>
