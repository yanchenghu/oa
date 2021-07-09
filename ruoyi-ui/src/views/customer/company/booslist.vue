<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px" style="width:80% ;" @submit.native.prevent>
       <el-form-item label="" prop="corpName">
         <el-input
           v-model.trim="queryParams.corpName"
           placeholder="请输入客户名称"
           clearable
           size="small"
           @keyup.enter.native="handleQuery"
         />
       </el-form-item>
       <el-form-item>
         <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">查询</el-button>
       </el-form-item>
       <el-form-item  prop="companySituation">
         <el-select v-model.trim="queryParams.companySituation"   placeholder="请选择公司性质" clearable size="small" @change="change">
           <el-option
             v-for="dict in companySituationOptions"
             :key="dict.dictValue"
             :label="dict.dictLabel"
             :value="dict.dictValue"
           />
         </el-select>
       </el-form-item>
      <el-form-item prop="customerLevel">
        <el-select v-model.trim="queryParams.customerLevel" placeholder="请选择客户级别" clearable size="small" @change="change">
          <el-option
              v-for="dict in customerleve"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />

        </el-select>
      </el-form-item>
      <el-form-item  prop="paybackPeriod">
        <el-select v-model.trim="queryParams.paybackPeriod" placeholder="请选择回款周期" clearable size="small" @change="change">
          <el-option
              v-for="dict in companyperiod"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
        </el-select>
      </el-form-item>

    </el-form>
    <el-row :gutter="10" class="mb8">
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="companyList" @selection-change="handleSelectionChange">
      <el-table-column label="公司名称" align="left" prop="corpName" width="250">
        <template slot-scope="scope">
          <el-button
            type="text"
            @click="xuqiu(scope.row)"
          >{{scope.row.corpName}}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="公司性质"  prop="companySituation" :formatter="companySituationFormat"/>
      <el-table-column label="联系人/职位" align="left" width="130">
        <template slot-scope="scope">
          <span>{{scope.row.contactPeople}} / {{scope.row.contactPosition}}</span>
        </template>
      </el-table-column>
      <el-table-column label="联系方式" align="left" prop="contactPhone" width="110"/>
      <el-table-column label="客户级别"  prop="customerLevel" :formatter="customerleveFormat" width="100"/>
      <el-table-column label="回款周期"  prop="paybackPeriod" :formatter="companyperiodFormat"/>
      <el-table-column label="合作时间"  prop="cooperationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.cooperationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="录入人" prop="entryPeople" />
      <el-table-column label="更多"  class-name="small-padding fixed-width" >
        <template slot-scope="scope">
          <el-button
            type="text"
            icon="el-icon-more"
            @click="more(scope.row)"
          >更多信息</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <el-dialog title="我的需求" :visible.sync="open5" width="550px" append-to-body>
      <p style="font-size: 18px;">{{title}}</p>
      <el-row>
        <el-col :span="12" v-for="item,i in xuqiumingc" :key="i">
           <el-button type="text" @click="tiaozhuan(item)">{{item.projectName}}</el-button>
           <p></p>
        </el-col>
      </el-row>
    </el-dialog>

    <!-- 抽屉 -->
    <el-drawer
      title="信息"
      :visible.sync="drawer"
      :with-header="false"
      size="60%"
      @close="dra"
      >
      <div style="margin:0 3% 0 3%;border-left:1px solid #E6E6E6;">
      <div style=" padding:20px 3% 30px 3%; border-bottom: 1px solid #E6E6E6;">
        <div>
          <b>
            {{yxdemandone.corpName}}
          </b>
        </div>
        <br/>
         <el-form :inline="true" :model="yxdemandone" class="demo-form-inline">
           <el-form-item label="公司性质">
             <el-select  v-model="yxdemandone.companySituation"   size="small" >
               <el-option
                 v-for="dict in companySituationOptions"
                 :key="dict.dictValue"
                 :label="dict.dictLabel"
                 :value="parseInt(dict.dictValue)"
               />
             </el-select>
           </el-form-item>
           <el-form-item prop="customerLevel" label="客户级别">
             <el-select v-model="yxdemandone.customerLevel" placeholder="请选择客户级别"  size="small" >
               <el-option
                   v-for="dict in customerleve"
                   :key="dict.dictValue"
                   :label="dict.dictLabel"
                   :value="parseInt(dict.dictValue)"
                 />

             </el-select>
           </el-form-item>
           <el-form-item  prop="paybackPeriod" label="回款周期">
             <el-select v-model="yxdemandone.paybackPeriod" placeholder="请选择回款周期"  size="small" >
               <el-option
                   v-for="dict in companyperiod"
                   :key="dict.dictValue"
                   :label="dict.dictLabel"
                   :value="parseInt(dict.dictValue)"
                 />

             </el-select>
           </el-form-item>
         </el-form>
      </div>
      <div>
          <el-tabs v-model="activeName"  @tab-click="handleClick">
            <el-tab-pane label="联系人信息" name="popmsg">
               <div style="display: flex; justify-content: space-between;">
                 <el-form ref="formmsg" label-position="right" label-width="80px" :model="yxdemandone">
                    <b>联系人信息</b>
                    <p></p>
                    <el-form-item label="姓名" prop="contactPeople">
                      <el-input v-model.trim="yxdemandone.contactPeople" ></el-input>
                    </el-form-item>
                    <el-form-item label="职位" prop="contactPosition">
                      <el-input  v-model.trim="yxdemandone.contactPosition" ></el-input>
                    </el-form-item>
                    <el-form-item label="电话" prop="contactPhone">
                      <el-input  v-model.trim="yxdemandone.contactPhone" ></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="mailbox">
                      <el-input  v-model.trim="yxdemandone.mailbox" ></el-input>
                    </el-form-item>
                    <el-form-item label="微信">
                      <el-input v-model.trim="yxdemandone.wechat" ></el-input>
                    </el-form-item>
                    <el-form-item label="QQ">
                      <el-input  v-model.trim="yxdemandone.qq" ></el-input>
                    </el-form-item>
                 </el-form>
                 <div>
                   <p></p>
                   <el-table  :data="listlianxi">
                     <el-table-column label="姓名" align="left" prop="contactPeople" />
                     <el-table-column label="职位" align="left" prop="contactPosition" />
                     <el-table-column label="电话" align="left" prop="contactPhone" />
                     <el-table-column label="备注" align="left" prop="bz" />
                   </el-table>
                 </div>
               </div>
            </el-tab-pane>
            <el-tab-pane label="合同">

              <p></p>
              <el-table v-loading="loadings" :data="contractlist">
                <el-table-column label="甲方" align="left" prop="firstParty" width="160"/>
                <el-table-column label="乙方" align="left" prop="party"/>
                <el-table-column label="合同开始日期" align="center" prop="startTime" width="180">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="合同结束日期" align="center" prop="endTime" width="180">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="客户签约人" align="left" prop="clientSigner" width="110"/>
                <el-table-column label="公司签约人" align="center" prop="companySigner" width="100"/>
                <el-table-column label="添加人" align="center" prop="contractName"/>

                <el-table-column label="合同添加日期" align="center" prop="contractTime" width="180">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.contractTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>

                <el-table-column label="合同预览" align="center" class-name="small-padding fixed-width" fixed="right">
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      type="text"
                      @click="preview(scope.row)"
                    >预览</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="联系记录">
              <div style="padding-left:2%; float: left;width: 60%;">
                <div class="msg">
                  <b>转化记录</b>
                </div>
                <div style="clear: both;margin-top: 10px;"></div>
                <el-form label-position="right" label-width="70px">
                <el-form-item label="合作日期">
                  <span class="sp">{{yxdemandone.cooperationTime}}</span>
                </el-form-item>
                <el-form-item label="录入人">
                  <span class="sp">{{yxdemandone.entryPeople}}</span>
                </el-form-item>
                <el-form-item label="备注">
                <span class="sp">{{yxdemandone.remark}}</span>
                </el-form-item>
                </el-form>
              </div>
              <div style="padding-left:2%; float: left;">
                <div class="msg">
                  <b>联系记录</b>
                </div>
                <div style="clear: both;margin-top: 10px;"></div>
                <ul style="list-style: none;padding-left: 10px;">
                  <li v-for="msg in putmsgs">
                    <span class="sp">{{msg.contactTime}}</span>
                    <span style="display: inline-block; width: 10px;"> </span>
                    <span class="sp">{{msg.nickName}} </span>
                    <span style="display: inline-block; width: 10px;"></span>
                    <span class="sp">{{msg.contactDetail}}</span>
                  </li>
                </ul>
              </div>
              </el-tab-pane>
              <el-tab-pane label="面试题">
                  <el-table style="width: 50%;"  :data="listAuditeditor">
                    <el-table-column label="面试题名称" align="left" prop="auditedName" />
                    <el-table-column label="面试题添加日期" align="left" prop="addTime" >
                      <template slot-scope="scope">
                        <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>
                      </template>
                    </el-table-column>
                    <el-table-column label="面试题预览" align="left" class-name="small-padding fixed-width" >
                      <template slot-scope="scope">
                        <el-button
                          size="small"
                          type="text"
                          @click="handsee(scope.row.auditedPath)"
                        >预览面试题</el-button>
                      </template>
                    </el-table-column>
                  </el-table>
              </el-tab-pane>
          </el-tabs>
       </div>
      </div>
    </el-drawer>
    <!-- 简历预览 -->
    <el-dialog  :visible.sync="open3" width="70%">
     <iframe
        :src="src"
        style="overflow: auto; position: absolute; top: 40px; right: 0; bottom: 0; left: 0; width: 100%; height:1000%; border: none;"
      ></iframe>
    </el-dialog>
    <el-dialog :visible.sync="dialogVisible" width="500px" :title="title">

      <img width="100%" :src="src" alt="">
    </el-dialog>
  </div>
</template>

<script>
import { listCompany, getCompany,   updateCompany,contractCompany,} from "@/api/customer/company";
import {accordingDemand} from "@/api/perentry/entry";
import {debounce,checkImg} from "@/utils/ruoyi.js"
export default {
  name: "Company",
  data() {
    return {
      open3:false,
      open5:false,
      activeName:"popmsg",
      // 抽屉
      drawer:false,
      // 遮罩层
      loading: true,
      listAuditeditor:[],
      loadings: false,
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
      // 合同列表
      total2:0,
      // 合作公司表格数据
      companyList: [],
      // 合同列表
      contractlist:[],
      listlianxi:[],
      dialogVisible:false,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dialogImageUrl:"",
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        corpName: null,
        customerLevel: null,
        paybackPeriod: null,
        cooperationTime: null,
        enteredBy: null,
        transformingPeople: null
      },
      // 表单参数
      form: {},
      forms:{},

      companySituationOptions:[],
      customerleve:[],
      companyperiod:[],
      // 单个数据
      yxdemandone:{},
      putmsgs:[],
      xuqiumingc:[],
    };
  },
  created() {
    this.getList();
    this.getDicts("yxdemand_company_situation").then(response => {
      this.companySituationOptions = response.data;
    });
    this.getDicts("bus_customer_leve").then(response => {
      this.customerleve = response.data;
    });
    this.getDicts("mar_company_period").then(response => {
      this.companyperiod = response.data;
    });
  },
  methods: {
    tiaozhuan(row){
      this.$router.push({ path:'/follow/particulars',query:{row:row.demandId,ident:2}})
    },
    xuqiu(row){
      accordingDemand({corpCode:row.corpCode}).then(res=>{
        this.xuqiumingc = res
        this.open5 = true
        this.title = row.corpName
      })
    },
    handsee(file,ind){
        this.src = ""
        this.dialogImageUrl = ""
        let srcs = process.env.VUE_APP_BASE_API
        if(checkImg(file)){
          ind = 2
        }else{
          ind = 1
        }
        if(ind==1){
          this.open3 = true
          this.title = '面试题'
          this.src=`https://www.xdocin.com/xdoc?_func=form&_key=vdm5j3eitvebvmh2qgcuv4idry&_xdoc=${srcs+file}`
        }else if(ind==2){
          this.dialogVisible=true
          this.title = "面试题图片"
          this.src = srcs+file
        }
        this.dialogImageUrl = srcs+file
      },

    // 公司性质
    companySituationFormat(row, column) {
      return this.selectDictLabel(this.companySituationOptions, row.companySituation);
    },
    // 客户级别
    customerleveFormat(row, column) {
      return this.selectDictLabel(this.customerleve, row.customerLevel);
    },
    // 回款周期
    companyperiodFormat(row, column) {
      return this.selectDictLabel(this.companyperiod, row.paybackPeriod);
    },
    change(){
      this.getList()
    },
    dra(){
       this.getList()
    },
    /** 查询合作公司列表 */
    getList() {
      this.loading = true;
      listCompany(this.queryParams).then(response => {
        this.companyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 预览文件
    preview(adinw){
      let srcs = process.env.VUE_APP_BASE_API+adinw.contractPreview
      this.src=`https://www.xdocin.com/xdoc?_func=form&_key=vdm5j3eitvebvmh2qgcuv4idry&_xdoc=${srcs}`
      this.open3=true
    },
    getone(value){
      getCompany(value.corpCode).then(res=>{
        this.drawer = true
        this.yxdemandone=res.data.data.marCompany
        this.listlianxi = res.data.data.listMarCompanyC
        this.putmsgs = res.data.data.mar
        this.listAuditeditor=res.data.data.listAuditeditors
      })
    },
    // 更多
    more(value){
      this.activeName="popmsg"
      this.getone(value)
    },
    handleClick(tab){
      if(tab.label=="合同"){
        this.get()
      }
    },
    get(){
      this.loadings = true;
      contractCompany({corpCode:this.yxdemandone.corpCode}).then(res=>{
      this.contractlist = res.rows
      this.total2=res.total
      this.loadings = false;
      })
    },

    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.corpCode)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },

  }
};
</script>
<style scoped>
  .indd{width: 240px;}
  >>>.el-tabs__item:focus.is-active.is-focus:not(:active) {
        -webkit-box-shadow: none !important;
        box-shadow: none !important;
  }
    >>>.el-tabs__header{
      background: #F5F5F9;
      padding-left:3%;
    }
    >>>.el-tabs__content{
      padding:20px 3% 0 3%;
    }
     >>>.el-drawer.rtl{
          overflow: auto;
      }
      .span{
        color: #2C2C2C;
      }
      .sp{
        font-size: 11px;
        color: #8A8A8A;
      }
</style>
