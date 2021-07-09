<template>
  <div>
    <el-row :gutter="35" class="panel-group" v-if="dataList.numb!==1">
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData(0)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              录入
            </div>
            <count-to  :start-val="0" :end-val="dataList.EnterInfosize" :duration="2600" class="card-panel-num4" />

            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-luru">
            <svg-icon icon-class="luru" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData(1)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              抢占
            </div>
            <count-to :start-val="0" :end-val="dataList.entryrobnnum" :duration="2600" class="card-panel-num5" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>

          </div>
          <div class="card-panel-icon-wrapper icon-qaingzhan">
            <svg-icon icon-class="genzong" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData(2)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              绑定
            </div>
            <count-to :start-val="0" :end-val="dataList.bingdinnum" :duration="3000" class="card-panel-num6" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-bangd">
            <svg-icon icon-class="bangding" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData(3)">

          <div class="card-panel-description">
            <div class="card-panel-text">
              简历通过
            </div>
            <count-to :start-val="0" :end-val="dataList.resumeadopt" :duration="3200" class="card-panel-num1 " />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-jianli">
            <svg-icon icon-class="jianli" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData(4)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              面试通过
            </div>
            <count-to :start-val="0" :end-val="dataList.interviewadopt" :duration="3600" class="card-panel-num2" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-mianshi">
            <svg-icon icon-class="mianshi" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData(5)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              入项
            </div>
            <count-to :start-val="0" :end-val="dataList.entryPeople" :duration="3600" class="card-panel-num3" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-ruxiang">
            <svg-icon icon-class="ruxiang" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="35" class="panel-group" v-else>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartDatas(0)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              录入需求
            </div>
            <count-to  :start-val="0" :end-val="dataList.litmarD" :duration="2600" class="card-panel-num4" />

            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-luru">
            <svg-icon icon-class="luru" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartDatas(1)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              简历绑定
            </div>
            <count-to :start-val="0" :end-val="dataList.litmap" :duration="3000" class="card-panel-num6" />

            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-bangd">
            <svg-icon icon-class="bangding" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartDatas(3)">

          <div class="card-panel-description">
            <div class="card-panel-text">
              简历通过
            </div>
            <count-to :start-val="0" :end-val="dataList.litinfo" :duration="3200" class="card-panel-num1 " />

            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-jianli">
            <svg-icon icon-class="jianli" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartDatas(5)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              面试通过
            </div>
            <count-to :start-val="0" :end-val="dataList.litview" :duration="3600" class="card-panel-num2" />

            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-mianshi">
            <svg-icon icon-class="mianshi" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartDatas(7)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              人员入项
            </div>
            <count-to :start-val="0" :end-val="dataList.litentry" :duration="3600" class="card-panel-num3" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-ruxiang">
            <svg-icon icon-class="ruxiang" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartDatas(8)">
          <div class="card-panel-description">
            <div class="card-panel-text">
              人员出项
            </div>
            <count-to :start-val="0" :end-val="dataList.litout " :duration="3600" class="card-panel-num5" />
            <div>
              <el-button type="text">查看详情</el-button>
            </div>
          </div>
          <div class="card-panel-icon-wrapper icon-qaingzhan">
            <svg-icon icon-class="out" class-name="card-panel-icon" />
          </div>
        </div>
      </el-col>
    </el-row>
    <el-dialog style="text-align: center;"  :title="title2" :visible.sync="open"  width="500px">
      <el-radio-group v-if="title=='简历通过'"   v-model="caozuos">
          <el-radio :label="1">面试</el-radio>
          <el-radio :label="6">拒绝面试</el-radio>
      </el-radio-group>
      <p></p>
      <el-form label-width="120px" :model="form" style="width: 70%;" ref="ruxing" >
        <el-form-item
          v-if="caozuos==1 && title=='简历通过'"
          :rules="[{ required: true, message: '日期不能为空', trigger: 'change' },]"
          prop="interviewTime"
          label="具体面试日期">
          <el-date-picker
              v-model="form.interviewTime"
              type="datetime"
              size="small"
              :clearable="false"
              value-format="yyyy-MM-dd HH:mm:ss"
              placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item
          v-if="caozuos==6 || title=='面试通过'"
          :rules="[{ required: true, message: '原因不能为空', trigger: 'change' },]"
          prop="followDetail"
          :label="title=='简历通过'? '拒绝面试原因':'面试不通过原因'">
          <el-input
            type="textarea"
            autosize
            placeholder="请输入原因"
            v-model="form.followDetail">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="open=false">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog :title="title" :visible.sync="dialogTableVisible2"  width="500px">
      <el-table :data="gridData" v-loading="loading">
        <el-table-column property="customer_name" label="姓名" ></el-table-column>
        <el-table-column property="outof_projecttime" label="离项时间" ></el-table-column>
        <el-table-column property="quit_proreason" label="离项原因" :formatter="professionIdopFormat"></el-table-column>
      </el-table>
    </el-dialog>
    <el-dialog  :title="title" :visible.sync="dialogTableVisible3"  width="900px">
      <el-table :data="gridData" v-loading="loading">
        <el-table-column property="customerName" label="姓名" width="80"></el-table-column>
        <el-table-column property="customerTel" label="电话" width="120"></el-table-column>
        <el-table-column property="beginTime" label="面试开始时间"></el-table-column>
        <el-table-column property="endTime" label="面试结束时间"></el-table-column>
        <el-table-column property="interviewTime" label="具体面试时间"></el-table-column>
        <el-table-column label="操作" v-if="dataList.numb==2||dataList.numb==3||dataList.numb==5">
          <template slot-scope="scope">
            <el-button type="text" size="medium" :disabled="scope.row.interviewTime!==null || scope.row.followStatus!==3" @click="addmianshi(scope.row)">
              <span v-if="scope.row.followStatus==3">添加面试状态</span>
              <span v-if="scope.row.followStatus==5">面试通过</span>
              <span v-if="scope.row.followStatus==6">面试不通过</span>
              <span v-if="scope.row.followStatus==7">已入项</span>
              <span v-if="scope.row.followStatus==8">未入项</span>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
    <el-dialog  :title="title" :visible.sync="dialogTableVisible"  width="500px">
      <el-table :data="gridData" v-if="dataList.numb==2||dataList.numb==3||dataList.numb==5" v-loading="loading">
        <el-table-column property="customerName" label="姓名" ></el-table-column>
        <el-table-column property="customerTel" label="电话"  ></el-table-column>
        <el-table-column v-if="title=='录入'||title=='抢占'" property="addTime" :label="title=='录入'?'录入时间':'抢占时间'"></el-table-column>
        <el-table-column v-else-if="title=='面试通过'||title=='绑定'"  property="projectName" label="项目名称"></el-table-column>
        <el-table-column v-else-if="title=='绑定'"  property="syqstartTime" label="绑定时间"></el-table-column>
        <el-table-column v-else  property="syqstartTime" label="入项时间"></el-table-column>
        <el-table-column label="操作状态" v-if="title=='面试通过'">
          <template slot-scope="scope">
            <el-button type="text" size="medium" :disabled="scope.row.followStatus!==5" @click="addmianshi(scope.row)">
              <span v-if="scope.row.followStatus==5&&title=='面试通过'">修改面试状态</span>
              <span v-if="scope.row.followStatus==7&&title=='面试通过'">已入项</span>
              <span v-if="scope.row.followStatus==8&&title=='面试通过'">未入项</span>
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <div v-if="title == '简历绑定'">
        <el-input v-model="searchmsg" placeholder="请输入" clearable size="small" @keyup.enter.native="search" style="width: 170px;margin-right: 10px;"/>
      <el-button type="cyan" icon="el-icon-search" size="mini" @click="search">查询</el-button>
      </div>

      <el-table :data="gridData" v-if="dataList.numb==1" v-loading="loading">
        <el-table-column v-if="title!=='录入需求'" property="customerName" label="姓名" width="80">
          <template slot-scope="scope">
            <el-button @click="tiaozhuan(scope.row)" type="text" size="medium">{{scope.row.customerName}}</el-button>
          </template>
        </el-table-column>
        <el-table-column  property="projectName" label="公司名称" ></el-table-column>
        <el-table-column v-if="title=='录入需求'" property="addTime" label="添加时间" ></el-table-column>
        <el-table-column v-else property="trackingtime" label="操作时间" ></el-table-column>
      </el-table>
    </el-dialog>
  </div>

</template>

<script>
import CountTo from 'vue-count-to'
import { getmsg, getbusmsg,put,noInterviewEntry} from "@/api/index.js"

export default {
  name:"PanelGroup",
  data(){
    return{
      caozuos:1,
      loading:false,
      gridData:[],
      gridDatas:[],
      dialogTableVisible:false,
      dialogTableVisible2:false,
      dialogTableVisible3:false,
      title:"",
      customerleve:[],
      searchmsg:"",
      form:{},
      open:false,
      title2:"",
      times:{}
    }
  },
  created() {
    // this.download("https://image.baidu.com/search/albumsdetail?tn=albumsdetail&word=%E5%9F%8E%E5%B8%82%E5%BB%BA%E7%AD%91%E6%91%84%E5%BD%B1%E4%B8%93%E9%A2%98&fr=searchindex_album%20&album_tab=%E5%BB%BA%E7%AD%91&album_id=7&rn=30")
    this.getDicts("outof_project_cause").then(response => {
      this.customerleve = response.data;
    });
  },
  props:{
    dataList:{
      type:Object,
      required: true
    }
  },
  components: {
    CountTo
  },
  methods: {
    addmianshi(row){
      // this.times = {
      //    sta:row.beginTime.split(" ")[0],
      //    sta1:row.beginTime.split(" ")[1],
      //    end:row.endTime.split(" ")[0],
      //    end1:row.endTime.split(" ")[1],
      // }
      this.form = {
        customerCode:row.customerCode,
        demandId:row.demandId,
        interviewTime:"",
        customerName:row.customerName,
        followDetail:""
      }
      this.open = true
      this.title2 = "面试操作"
      this.caozuos=1
    },
    submit(){
      this.$refs["ruxing"].validate((valid) => {
          if(valid){
            if(this.title=="面试通过"){this.caozuos=8}
            if(this.caozuos==1){
                put(this.form).then(res=>{
                this.msgSuccess("添加成功")
                this.open = false
                this.dialogTableVisible3 = false
              })
            }else{
              this.butongg()
            }
          }
        })
    },
    butongg(){
      let form = new FormData()
      form.append("demandId",this.form.demandId)
      form.append("customerCode",this.form.customerCode)
      form.append("type",this.caozuos)
      form.append("followDetail",this.form.followDetail)
      noInterviewEntry(form).then(res=>{
        this.msgSuccess("修改成功")
        this.open = false
        this.dialogTableVisible3 = false
        this.dialogTableVisible = false
      })
    },
    search(){
      let that = this
      that.loading=true
      if(that.searchmsg==""){
        setTimeout(function(){
          that.gridData=that.gridDatas
          that.loading=false
        },1000)
      }else{
        clearInterval(that.timer)
        that.timer=setTimeout(function(){
          var data= that.gridDatas.filter(item=>{
            return item.customerName == that.searchmsg
           })
          that.gridData = data
          that.loading=false
        },1000)
      }
    },

    tiaozhuan(list){
      this.$router.push({path:'/follow/particulars',query:{row:list.demandId,ident:8,name:list.customerName}})
    },
    professionIdopFormat(row, column) {
      return this.selectDictLabel(this.customerleve, row.quit_proreason);},

    handleSetLineChartData(type) {
      // this.$emit('handleSetLineChartData', type)
      let form = new FormData()
      form.append("followStatus",type)
      this.loading=true
      getmsg(form).then(res=>{
        this.gridData = res.data

        this.loading = false
      })
      if(type==0){
        this.title = "录入"
        this.dialogTableVisible = true
      }else if(type == 1){
        this.title = "抢占"
        this.dialogTableVisible = true
      }else if(type == 2){
        this.title = "绑定"
        this.dialogTableVisible = true
      }else if(type == 3){
        this.title = "简历通过"
        this.dialogTableVisible3 = true
      }else if(type == 4){
        this.title = "面试通过"
        this.dialogTableVisible = true
      }else if(type == 5){
        this.title = "入项"
        this.dialogTableVisible = true
      }
    },
    handleSetLineChartDatas(type) {
      let form = new FormData()
      form.append("followStatus",type)
      this.loading=true
      getbusmsg(form).then(res=>{
        this.gridData = res.data
        this.gridDatas = res.data
        this.loading=false
      })
      if(type==0){
        this.title = "录入需求"
        this.dialogTableVisible = true
      }else if(type == 1){
        this.title = "简历绑定"
        this.dialogTableVisible = true
      }else if(type == 3){
        this.title = "简历通过"
        this.dialogTableVisible = true
      }else if(type == 5){
        this.title = "面试通过"
        this.dialogTableVisible = true
      }else if(type == 7){
        this.title = "人员入项"
        this.dialogTableVisible = true
      }else if(type == 8){
        this.title = "人员出项"
        this.dialogTableVisible2 = true
      }
    },

  }
}
</script>

<style lang="scss" scoped>

.panel-group {
  margin-top: 18px;
  .card-panel-col {
    margin-bottom: 32px ;
    padding: 0 16px !important;
  }
  .card-panel {
    border-radius: 2px;
    height: 110px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }
      .icon-luru {
        background: #0081FF;
      }
      .icon-qaingzhan{
       background: #FF9F43;
      }
      .icon-bangd{
        background:#28C76F;
      }

      .icon-jianli{
        background: #33B5E5;
      }
      .icon-ruxiang {
        background: #EA5455;
      }
     .icon-mianshi {
       background: #AA66CC;
     }
    }

    .icon-luru {
      color: #0081FF;
      background-color: rgba(51,181,229,0.1) ;
    }
    .icon-qaingzhan{
     color: #FF9F43;
     background-color: rgba(255,159,67,0.1);
    }
    .icon-bangd{
      color:#28C76F;
      background-color: rgba(40,199,111,0.1) ;
    }
    .icon-jianli{
      color: #33B5E5;
      background-color: rgba(51,181,229,0.1) ;
    }
    .icon-mianshi {
      color: #AA66CC;
      background-color:rgba(170,102,204,0.1) ;
    }

    .icon-ruxiang {
      color: #EA5455;
      background-color: rgba(234,84,85,0.1) ;
    }


    .card-panel-icon-wrapper {
      float: right;
      margin: 35px 16px 0 0;
      padding:6px 6px;
      transition: all 0.38s ease-out;
      border-radius: 10px;
    }

    .card-panel-icon {
      float: right;
      font-size: 28px;
    }

    .card-panel-description {
      float: left;
      font-weight: bold;
      margin:20px 0 26px 16px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 14px;
        margin-bottom: 12px;
      }
      .card-panel-num{
        margin-left: 20px;
        font-size: 20px;
        font-weight: 800;
        color: #c0c4cc;
      }

      .card-panel-num1 {
        font-size: 20px;
        font-weight: 800;
        color: #33B5E5;
      }
      .card-panel-num2 {
        font-size: 20px;
         color: #AA66CC;
        font-weight: 800;
      }
      .card-panel-num3 {
        color: #EA5455;
        font-size: 20px;
        font-weight: 800;
      }
      .card-panel-num4 {
        color: #0081FF;
        font-size: 20px;
        font-weight: 800;
      }
      .card-panel-num5 {
        font-size: 20px;
        color: #FF9F43;
        font-weight: 800;
      }
      .card-panel-num6 {
        font-size: 20px;
         color:#28C76F;
        font-weight: 800;
      }
    }
  }
}
@media only screen and (min-width: 1200px){
  .el-col-lg-4-8 {
  		width: 20%;
  	}
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;
    border-radius: 2px !important;
    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
