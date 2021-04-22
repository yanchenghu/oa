<template>
  <div class="app-containe">
    <div style="display: flex;">
       <!-- 左边信息栏 -->
      <div style="width:calc(100% - 360px); padding:0 15px;border-radius: 2px; background-color: #fff;">
          <!-- top -->
          <div style="display: flex; position: fixed;width: calc(100% - 605px);background-color: #fff; z-index: 10;padding-top: 35px;padding-bottom: 20px; height: 200px;" >
            <div>
              <el-avatar class="avatar" size="medium">
                <img v-if="perCustomerinfo.customerSex==1" :src="name1" alt="">
                <img v-else :src="name2" alt="">
              </el-avatar>
            </div>
            <div class="top-right">
              <div class="top-top">
                <span v-if="perro.addPeople===name">{{perCustomerinfo.customerName}}</span>
                <span v-else>
                <span >{{perCustomerinfo.customerName.substring(0,1)+ new Array(perCustomerinfo.customerName.length).join('*')}}</span>
                </span>
              </div>
              <div class="font">
                    <el-col :sm="8" class="padding">
                        <svg-icon icon-class="dianhua" class-name="card-panel-icon" />{{perCustomerinfo.customerTel}}
                    </el-col>
                    <el-col :sm="8" class="padding" >
                      <svg-icon icon-class="sex" class-name="card-panel-icon" />{{customerSexOptionsFormat(perCustomerinfo)}}
                    </el-col>
                    <el-col :sm="8" class="padding" >
                      <svg-icon icon-class="chusheng" class-name="card-panel-icon" />{{perCustomerinfo.customerBirth?perCustomerinfo.customerBirth:"生日未知"}}
                    </el-col>
                    <el-col :sm="8" class="padding">
                      <svg-icon icon-class="jishu" class-name="card-panel-icon" />{{perCustomerinfo.professionId?professionIdoptionsFormat(perCustomerinfo):"专业未知"}}
                    </el-col>
                    <el-col :sm="8" class="padding">
                      <svg-icon icon-class="jiaoyu" class-name="card-panel-icon" />{{perCustomerinfo.education?customerSpecialitiesoptionsFormat(perCustomerinfo):"学历未知"}}
                    </el-col>
                    <el-col :sm="8" class="padding">
                      <svg-icon icon-class="work" class-name="card-panel-icon" />{{perCustomerinfo.workYear?perCustomerinfo.workYear+'年':'工作经验未知'}}
                    </el-col>
                    <el-col :sm="8" class="padding">
                      <svg-icon icon-class="xuexiao" class-name="card-panel-icon" />{{perCustomerinfo.customerUniversity?perCustomerinfo.customerUniversity:"毕业学校未知"}}
                    </el-col>
                    <el-col :sm="8" class="padding">
                      <svg-icon icon-class="chengshi" class-name="card-panel-icon" />{{perCustomerinfo.intentionArea?intentionareaOptionsFormat(perCustomerinfo):"意向地区未知"}}
                    </el-col>
                    <el-col :sm="8" class="padding" >
                      <svg-icon icon-class="xinzi" class-name="card-panel-icon" />{{perCustomerinfo.expectationSalary?perCustomerinfo.expectationSalary:"薪资未知"}}
                    </el-col>
                    <el-col :sm="8" class="padding"  v-if="perCustomerinfo.email">
                      <svg-icon icon-class="youxiang" class-name="card-panel-icon"/>
                      <el-tooltip v-if="perCustomerinfo.email.length>=10"  class="item" effect="dark" :content="perCustomerinfo.email" placement="top-end">
                        <span>{{`${perCustomerinfo.email.slice(0,10)}...`}}</span>
                      </el-tooltip>
                      <span v-else>
                        {{perCustomerinfo.email}}
                      </span>
                    </el-col>
                    <el-col :sm="8" class="padding" v-else>
                      <svg-icon icon-class="youxiang" class-name="card-panel-icon"/>
                      <span >
                        邮箱未知
                      </span>
                    </el-col>
                    <el-col :sm="8" class="padding"  v-if="perCustomerinfo.addTime">
                      <svg-icon icon-class="luru" class-name="card-panel-icon" />
                       <el-tooltip  class="item" effect="dark" :content="perCustomerinfo.addTime" placement="top-end">
                        <span>{{`${perCustomerinfo.addTime.slice(0,11)}...`}}</span>
                      </el-tooltip>
                    </el-col>
              </div>
            </div>
          </div>
          <el-tabs v-model="activeName" v-if="perro.addPeople == name">
              <el-tab-pane label="标准简历" name="second">
                 <span><svg-icon icon-class="jiaoyu" class-name="card-panel-icon" /></span><span class="tit">教育经历</span>
                 <ul style="list-style: none;" class="font">
                   <li v-for="work,i in perEducList"
                    :key="i"
                    style="margin: 10px 10px 10px 0; ">
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
                    <li v-for="work in work_experienceListArr" style="margin: 10px 0 10px 0;">
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
                    <li v-for="project in project_experience" style="margin: 10px 15spx 10px 0; ">
                      <p class="biaoti">{{project.projectName}}</p>
                      <p><span class="xiaotu">项目时间：</span>&nbsp<span class="miaoshu"  v-if="project.quitprojectTime">{{project.joinprojectTime?project.joinprojectTime+" 至 "+project.quitprojectTime : "未知"+" 至 "+project.quitprojectTime}}</span>
                    <span class="miaoshu"  v-else-if="project.joinprojectTime">{{project.quitprojectTime?project.joinprojectTime+" 至 "+project.quitprojectTime:project.joinprojectTime+" 至 "+"未知"}}</span>
                    <span class="miaoshu"  v-else>{{"未知"+" 至 "+"未知"}}</span></p>
                      <p><span class="miaoshu"  style="line-height: 1.5;">{{project.duty}}</span></p>
                    </li>
                  </ul>
              </el-tab-pane>

              <el-tab-pane label="跟进信息" name="first">
                <span><svg-icon icon-class="ruxiang" class-name="card-panel-icon" /></span><span class="tit">入项记录</span>
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
                <span><svg-icon icon-class="genzong" class-name="card-panel-icon" /></span><span class="tit">跟踪记录</span>
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
              </el-tab-pane>
            </el-tabs>
      </div>

      <!-- 右边操作栏 -->
      <div class="right-but">
        <div style="font-weight: 400;font-size: 18px;margin-bottom: 20px;">简历操作</div>
        <div v-if="perro.addTime" style="display: flex;">
          <div>
            <el-avatar class="avatar" size="medium">
              <img v-if="sex==1" :src="name1" alt="">
              <img v-else :src="name2" alt="">
            </el-avatar>
          </div>
          <div style="padding: 5px 0 0 10px;">
            <div class="font zhanyou">占有人：{{perro.addName}}</div>
            <div style="font-size: 12px;color: #909399;">
                 {{perro.addTime}} - {{perro.editTime}}
            </div>
          </div>
        </div>
        <div style="padding: 20px 0;">
          <el-row :gutter="15">
            <el-col :span="12" v-show="!button[0].disabled">
              <el-button class="button" type="warning" @click="buttoncli(0)">{{button[0].name}}</el-button>
            </el-col>
            <el-col :span="12" v-show="!button[1].disabled">
               <el-button class="button" type="warning" @click="buttoncli(1)">{{button[1].name}}</el-button>
            </el-col>
            <el-col :span="12" v-show="!button[5].disabled">
                <el-button plain class="button" type="danger" @click="buttoncli(5)">{{button[5].name}}</el-button>
            </el-col>
          </el-row>
        </div>
        <div style="display: flex;justify-content: space-between;">
          <div class="caozuo" v-show="!button[3].disabled" @click="buttoncli(3)"> <svg-icon icon-class="xiazai" class-name="el-col icon-but" /><br><span class="zhanyou font">下载简历</span> </div>
          <div class="caozuo" @click="buttoncli(4)" v-show="!button[4].disabled"> <svg-icon icon-class="gengxin" class-name="el-col icon-but" /><br><span class="zhanyou font">更新简历</span> </div>
          <div class="caozuo" @click="buttoncli(2)" v-show="!button[2].disabled"> <svg-icon icon-class="preview" class-name="el-col icon-but" /><br><span class="zhanyou font">预览简历</span></div>
        </div>
      </div>
      <el-dialog  :visible.sync="open2" width="70%">
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
  import {debounce,} from "@/utils/ruoyi.js"
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
        sex:store.getters.sex,
        // 简历预览
        open2:false,
        // 入项信息
        mapList:[],
        avart1:[
          require('../../../assets/avat/avta2.png'),
          require('../../../assets/avat/头像 4.png'),
          require('../../../assets/avat/头像 8.png'),
          require('../../../assets/avat/头像 12.png'),
          require('../../../assets/avat/头像 10.png'),
          require('../../../assets/avat/头像 6.png'),
        ],
        avart2:[
          require('../../../assets/avat/avat1.png'),
          require('../../../assets/avat/头像 3.png'),
          require('../../../assets/avat/头像 5.png'),
          require('../../../assets/avat/头像 9.png'),
          require('../../../assets/avat/头像 7.png'),
        ],
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
        activeName: 'second',
        name1:"",
        name2:"",
        // 表格数据
        tableData: [],
        // 教育经验
        perEducList:[],
        src:null,
        // 项目经验
        project_experience: [],
        // 工作经历
        work_experienceListArr: {},
        style:{
          height:"400px",
        },
        // 列表数据请求
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        // 接收基础信息
         perCustomerinfo:{
           addTime:"",
           email:"",
           customerName:"",
         },
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
    mounted() {
      this.name1 = this.index1
      this.name2 = this.index2
    },
    computed:{
      index1:{
         cache:false,
         get:function(){
             let index = Math.floor((Math.random()*this.avart1.length))
              return this.avart1[index]
            }
      },
      index2:{
        cache:false,
        get:function(){
          let index = Math.floor((Math.random()*this.avart2.length))
          return this.avart2[index]
        }
      },
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
      // 技术方向翻译
      professionIdoptionsFormat(row, column) {
        return this.selectDictLabel(this.professionIdoptions, row.professionId);
      },

      customerSexOptionsFormat(row, column) {
        return this.selectDictLabel(this.customerSexOptions, row.customerSex);
      },

      customerSpecialitiesoptionsFormat(row, column) {
        return this.selectDictLabel(this.customerSpecialitiesoptions, row.education);
      },
      intentionareaOptionsFormat(row, column) {
        return this.selectDictLabel(this.intentionareaOptions, row.intentionArea);
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
  >>>.el-tabs__header{
    position: fixed;
    background-color: #fff;
    z-index: 10;
    width:calc(100% - 605px);
    top: 277px;
  }
  >>>.el-tabs__content{
    padding-top: 245px;
    height: calc(100vh - 100px);
    overflow: auto;
  }
  >>>.el-tabs__content::-webkit-scrollbar {
    width: 5px;
    height: 1px;
  }
  >>>.el-tabs__content::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: #909399;
  }
  >>>.el-tabs__content::-webkit-scrollbar-track {
    box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    background: #ededed;
    border-radius: 5px;
  }

  /* updata css */
  .app-containe{
    margin: 15px 10px 0 10px;
  }
  .top-right{
     padding: 9px 15px;
     width: 100%;
  }
  .avatar{
    width: 44px;
    height: 44px;
  }
  .top-right .top-top{
    font-size: 20px;
    font-weight: 700;
    margin-bottom:9px;
  }
  .card-panel-icon{
    margin-right:5px;
    color: #606266;
  }
  .el-col{
    margin-bottom:7px ;
  }
  .zhanyou{
    font-weight: 400;
    color: #303133;
    margin-bottom: 5px;
  }
  @media only screen and (min-width: 1200px){
    .el-col-lg-5-8 {
        width: 20%;
    }
  }
  @media only screen and (min-width: 1350px){
    .padding {
        width: 16.66667%;
    }
  }
  .right-but{
    width:360px;
    height: 300px;
    margin-left: 10px;
    border-radius: 2px;
    background-color: #fff;
    padding: 30px 30px;
  }
  .button{
    width: 100%;
  }
  .caozuo{
    text-align: center;
  }
  .caozuo:hover{
    cursor:pointer;
  }
  .icon-but{
    color: #303133;
    width: 25px;
    height: 25px;
  }
  .biaoti{
    font-weight: 400;
    color: #303133;
    font-size: 16px;
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
    font-weight: 700;
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
    /* padding-left: 1px !important; */
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
