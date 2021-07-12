<template>
  <div class="app-container">
      <el-input v-model="customerName" placeholder="请输入简历姓名或电话" clearable size="small" @keyup.enter.native="handleQuery" style="width: 190px;" />
      <el-button
      style="margin-right: 20px;"
          type="primary"
          size="small"
          @click="handleQuery"
      >搜索</el-button>

      <el-select filterable  v-model="relationshipZq"  placeholder="请选择简历状态" size="small" clearable  @change="handleQuery">
        <el-option
           v-if="dict.dictValue>1"
            v-for="dict in corpnamelist"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      <p></p>
      <el-table v-loading="loading" border :data="templists" >
        <el-table-column prop="customerName" label="姓名">
          <template slot-scope="scope">
            <el-button  type="text"  @click="handlesee(scope.row)" >{{scope.row.customerName}}</el-button>
          </template>
        </el-table-column>
        <el-table-column label="电话" align="left" prop="customerTel" key="3" >
          <template slot-scope="scope">
            <span >{{scope.row.customerTel.replace(/^(\d{3})\d{4}(\d{4})$/,"$1****$2")}}</span>
          </template>
        </el-table-column>
        <el-table-column label="期望薪资" align="left" prop="expectationSalary"  key="9">
          <template slot-scope="scope">
            <span v-if="scope.row.expectationSalary">{{scope.row.expectationSalary }}</span>
            <span v-else>未知</span>
          </template>
        </el-table-column>
        <el-table-column label="毕业时间" align="left" prop="customerUniversityTime" key="11">
           <template slot-scope="scope">
            <span >{{scope.row.customerUniversityTime?scope.row.customerUniversityTime:"未知"}}</span>
           </template>
        </el-table-column>
        <el-table-column label="学历/工作经验" align="left" prop="expectationSalary" key="10">
          <template slot-scope="scope">
            <span >{{scope.row.education?customerFormat(scope.row):"未知"}}/{{scope.row.workYear?scope.row.workYear+"年":"未知"}}</span>
           </template>
        </el-table-column>
        <el-table-column label="需求名称" align="left" prop="projectName"  />
        <el-table-column label="绑定时间" align="left" key="5">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.bindTime, '{y}-{m}-{d} {h}:{i}') }}</span>
           </template>
        </el-table-column>
        <el-table-column label="简历状态" align="left" width="400" key="6">
          <template slot-scope="scope" >
            <el-steps
            :active="scope.row.active"
            finish-status="success"
            :process-status="scope.row.process"
            :align-center="true">
              <el-step title="需求绑定"></el-step>
              <el-step title="简历" v-if="scope.row.followStatus<3"></el-step>
              <el-step title="简历通过" v-if="scope.row.followStatus==3||scope.row.followStatus>4"></el-step>
              <el-step title="简历不过" v-if="scope.row.followStatus==4"></el-step>
              <el-step title="面试" v-if="scope.row.followStatus<5"></el-step>
              <el-step title="面试通过" v-if="scope.row.followStatus==5||scope.row.followStatus>6"></el-step>
              <el-step title="面试不过" v-if="scope.row.followStatus==6"></el-step>

              <el-step title="入项" v-if="scope.row.followStatus<=7"></el-step>
              <el-step title="未入项" v-if="scope.row.followStatus==8"></el-step>
            </el-steps>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="left" key="8" width="150">
          <template slot-scope="scope">
            <p><el-button
              size="mini"
              type="text"
              @click="preview(scope.row,1)"
            >预览原版</el-button>
            <el-button
              size="mini"
              type="text"
              @click="preview(scope.row,2)"
            >预览附件</el-button>
            </p>
          </template>
        </el-table-column>
      </el-table>
      <pagination v-show="total>0" :total="total" :page.sync="pageNum" :limit.sync="pageSize"@pagination="gettelist"/>
      <el-dialog :title="title" :visible.sync="open3" width="70%">
       <iframe
          :src="src"
          style="overflow: auto; position: absolute; top: 45px; right: 0; bottom: 0; left: 0; width: 100%; height:1000%; border: none;"
        ></iframe>
      </el-dialog>
  </div>
</template>

<script>
  import { getMybindingResume } from "@/api/index.js"
  export default {
    name:"binbing",
    data(){
      return{
        templists:[],
        loading:true,
        customerName:"",
        total:0,
        src:"",
        pageNum: 1,
        open3:false,
        pageSize: 10,
        title:"",
        customerSpecialitiesoptions:[],
        relationshipZq:null,
        corpnamelist:[],
      }
    },
    created() {
      this.gettelist()
      this.getDicts("per_customerinfo_education").then(response => {
        this.customerSpecialitiesoptions = response.data;
      });
      this.getDicts("mar_demandresumefollow_status").then(response => {
        this.corpnamelist = response.data;
      });

    },
    methods:{
      customerFormat(row, column) {
        return this.selectDictLabel(this.customerSpecialitiesoptions, row.education);
      },
      gettelist(){
        this.templists=[]
        // 获取绑定简历信息
        let form =new FormData()
        form.append("customerName",this.customerName)
        form.append("pageNum",this.pageNum)
        form.append("pageSize",this.pageSize)
        if(this.relationshipZq){
          form.append("relationshipZq",this.relationshipZq)
        }
        this.loading = true;
        getMybindingResume(form).then(res=>{
          res.rows.forEach(item=>{
            if(item.followStatus=== 1||item.followStatus=== 2){
              item.active=0
              if(item.followStatus== 1){
                item.process="success"
              }else{
                item.process="error"
              }
            }
            if(item.followStatus===3||item.followStatus===4){
              item.active=1
              if(item.followStatus==3){
                item.process="success"
              }else{
                item.process="error"
              }
            }
            if(item.followStatus===5||item.followStatus===6){
               item.active=2
               if(item.followStatus==5){
                 item.process="success"
               }else{
                 item.process="error"
               }
            }
            if(item.followStatus===7||item.followStatus===8){
              item.active=3
              if(item.followStatus==7){
                item.process="success"
              }else{
                item.process="error"
              }
            }
          })
          this.templists = res.rows
          this.total = res.total;
          this.loading = false;
          })
      },
      handleQuery(){
        this.pageNum = 1
        this.gettelist()
      },
      handlesee(row){
        this.$router.push({path:"/record/particulars",query:{customerCode:row.customerCode}});
      },
      preview(adinw,ind){
       let srcs = null
       if(ind == 1){
         if(adinw.resumePath){
           srcs = process.env.VUE_APP_BASE_API+adinw.resumePath
           this.open3 = true
           this.title = ""
         }else{
           this.msgError(adinw.customerName+"暂无简历原件请上传")
         }
       }else{
         if(adinw.resumeEnclosurepath){
           srcs = process.env.VUE_APP_BASE_API+adinw.resumeEnclosurepath
           this.open3 = true
           this.title = ""
         }else{
          this.msgError(adinw.customerName+"暂无简历附件请上传")
         }
       }
       this.src=`https://www.xdocin.com/xdoc?_func=form&_key=vdm5j3eitvebvmh2qgcuv4idry&_xdoc=${srcs}`
      },
    }
  }
</script>

<style>

</style>
