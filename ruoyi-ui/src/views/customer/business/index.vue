<template>
  <div class="app-container">

    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" style="width:80% ;" label-width="68px" @submit.native.prevent>
      <el-form-item label="" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入公司名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item >
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">查询</el-button>
      </el-form-item>
    </el-form>
   <el-row :gutter="10" class="mb8">
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

<!--    展示-->
    <el-table v-loading="loading" :data="yxdemandList" >
      <el-table-column label="公司名称" align="center" prop="companyName" width="160"/>
      <el-table-column label="联系人/职位" align="center" width="130">
        <template slot-scope="scope">
          <span>{{scope.row.contactPeople}} / {{scope.row.contactPosition}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="公司性质"
        align="center"
        prop="companySituation"
        :formatter="companySituationFormat"
        width="90"
      />
      <el-table-column label="联系方式" align="center" prop="contactPhone" width="110"/>
      <el-table-column label="录入人" align="center" prop="entryPeople" width="80"/>
      <el-table-column
        label="线索状态"
        align="center"
        prop="isBusiness"
        :formatter="isFollowSubmitFormat"
         width="90"
      />
      <el-table-column label="最近一次联系情况" align="center" prop="contactInformation"  :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span>{{scope.row.contactInformation}}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateDate" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="距进入公海天数"
        align="center"
        prop="qq"
        width="80"
      >
      <template slot-scope="scope">
        <span v-if="scope.row.qq==1||scope.row.qq==2||scope.row.qq==3">{{scope.row.qq}}</span>
      </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="60">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.isBusiness!==4"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="followUp(scope.row.entryId)"
          >跟进</el-button>
          <el-button
            v-else
            size="mini"
            type="text"
            @click="followUp(scope.row.entryId)"
          >查看</el-button>
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
     <el-drawer
       title="信息"
       :visible.sync="drawer"
       :with-header="false"
       size="60%"
       @close="dra"
       >
       <div style="margin:0 3% 0 3%;border-left:1px solid #E6E6E6;">
       <div style=" padding:20px 3% 30px 2%; border-bottom: 1px solid #E6E6E6;">
         <div>
           <b>
             {{yxdemandone.companyName}}
           </b>
         </div>
          <el-form :inline="true" :model="yxdemandone" class="demo-form-inline">
            <el-form-item label="公司性质">
              <el-select  v-model="yxdemandone.companySituation"  @change="changes(yxdemandone.companySituation)" size="small" :disabled="yxdemandone.isBusiness==4">
                <el-option
                  v-for="dict in companySituationOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="parseInt(dict.dictValue)"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="线索状态">
              <el-select  v-model="yxdemandone.isBusiness" @change="changes(yxdemandone.isBusiness)"  size="small" :disabled="yxdemandone.isBusiness==4">
                <el-option
                    v-for="dict,index in isFollowSubmitOptions"
                    :disabled="index==4"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="parseInt(dict.dictValue)"
                  />
                </el-select>
            </el-form-item>
            <el-form-item>
                  <el-button :disabled="yxdemandone.isBusiness!==2" type="primary" @click="onSubmit">转化为合作用户</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div>
          <el-tabs>
            <el-tab-pane label="联系人信息">
              <div style="display: flex; justify-content: space-between;">
              <el-form label-position="left" label-width="80px" :model="yxdemandone">
                 <b>联系人信息</b>
                 <p></p>
                 <el-form-item label="姓名" >
                   <el-input  v-model="yxdemandone.contactPeople" @input="sees" :disabled="yxdemandone.isBusiness==4"></el-input>
                 </el-form-item>
                 <el-form-item label="职位">
                   <el-input  v-model="yxdemandone.contactPosition" @input="sees" :disabled="yxdemandone.isBusiness==4"></el-input>
                 </el-form-item>
                 <el-form-item label="电话">
                   <el-input  v-model="yxdemandone.contactPhone" v-on:input="sees" :disabled="yxdemandone.isBusiness==4"></el-input>
                 </el-form-item>
                 <el-form-item label="邮箱">
                   <el-input  v-model="yxdemandone.mailbox" @input="sees" :disabled="yxdemandone.isBusiness==4"></el-input>
                 </el-form-item>
                 <el-form-item label="微信">
                   <el-input  v-model="yxdemandone.wechat" @input="sees" :disabled="yxdemandone.isBusiness==4"></el-input>
                 </el-form-item>
                 <el-form-item label="QQ">
                   <el-input  v-model="yxdemandone.qq" @input="sees" :disabled="yxdemandone.isBusiness==4"></el-input>
                 </el-form-item>
              </el-form>
              <el-form label-position="left" label-width="100px" :model="yxdemandone">
                <b>外包公司信息</b>
                <p></p>
                 <el-form-item label="面试名义公司">
                   <el-input  v-model="yxdemandone.interviewCompany" @input="sees" :disabled="yxdemandone.isBusiness==4"></el-input>
                 </el-form-item>
                 <el-form-item label="面试官">
                   <el-input  v-model="yxdemandone.interviewer" @input="sees" :disabled="yxdemandone.isBusiness==4"></el-input>
                 </el-form-item>
                 <el-form-item label="面试职位">
                   <el-input  v-model="yxdemandone.interviewerPosition" @input="sees" :disabled="yxdemandone.isBusiness==4"></el-input>
                 </el-form-item>
                 <el-form-item label="面试地点">
                   <el-input  v-model="yxdemandone.interviewaddress" @input="sees" :disabled="yxdemandone.isBusiness==4"></el-input>
                 </el-form-item>
                 <el-form-item label="最终甲方">
                   <el-input  v-model="yxdemandone.finalparty" @input="sees" :disabled="yxdemandone.isBusiness==4"></el-input>
                 </el-form-item>
              </el-form>
            </div>
            </el-tab-pane>

            <el-tab-pane label="联系记录">
              <div style="padding-left:2%;">
                <div class="msg">
                  <b>联系记录</b>
                </div>
                <el-input type="textarea"   autosize placeholder="添加跟进信息"  v-model="putmsg" @focus="put=true" @blur="bul" :disabled="yxdemandone.isBusiness==4"></el-input>
                <el-button v-show="put" style="margin-top: 10px;" type="primary" @click="putmsgbut(yxdemandone.entryId)">发布</el-button>
                <div style="clear: both;margin-top: 10px;"></div>
                <ul style="list-style: none;padding-left: 10px;">
                  <li v-for="msg in putmsgs">
                    <span class="sp">{{msg.contactTime}} {{msg.nickName}} </span>
                    <span v-if="msg.status==1" class="sp">跟进</span>
                    <span v-else-if="msg.status==2" class="sp">{{msg.contactDetail}}</span>
                    <span v-else-if="msg.status==3" class="sp">录入客户</span>
                    <p><span class="span" v-show="msg.status!==2">{{msg.contactDetail}}</span></p>
                  </li>
                </ul>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>
       </div>
     </el-drawer>
     <el-dialog title="确认信息并转化为合作用户" :visible.sync="opens"  style="text-align: center;">
        <div style="display: flex; justify-content: center;">
          <el-form label-position="right" label-width="80px" :model="yxdemandone" :rules="rules" ref="formmsg">
             <b>联系人信息</b>
             <p></p>
             <el-form-item label=" 公司名称" >
               <el-input disabled v-model="yxdemandone.companyName"/>
             </el-form-item>
             <el-form-item label="姓名" prop="contactPeople">
               <el-input  v-model="yxdemandone.contactPeople" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label="职位" prop="contactPosition">
               <el-input  v-model="yxdemandone.contactPosition" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label="电话" prop="contactPhone">
               <el-input  v-model="yxdemandone.contactPhone" v-on:input="sees"></el-input>
             </el-form-item>
             <el-form-item label=" 邮箱">
               <el-input  v-model="yxdemandone.mailbox" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label=" 微信">
               <el-input  v-model="yxdemandone.wechat" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label=" QQ">
               <el-input  v-model="yxdemandone.qq" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label="客户级别" prop="customerLevel">
               <el-select  v-model="yxdemandone.customerLevel"  size="small">
                 <el-option
                     v-for="dict,index in customerleve"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="parseInt(dict.dictValue)"
                   />
                 </el-select>
             </el-form-item>
             <el-form-item label="回款周期" prop="paybackPeriod">
               <el-select  v-model="yxdemandone.paybackPeriod"  size="small">
                 <el-option
                     v-for="dict,index in companyperiod"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"
                   />
                 </el-select>
             </el-form-item>
             <el-form-item label="合作日期" prop="cooperationTime">
              <el-date-picker
                 v-model="yxdemandone.cooperationTime"
                 type="date"
                 placeholder="选择日期"
                 value-format="yyyy-MM-dd">
              </el-date-picker>
             </el-form-item>
             <el-form-item label="备注">
               <el-input type="textarea" autosize placeholder="请输入内容"  v-model="yxdemandone.remark"></el-input>
             </el-form-item>
          </el-form>
          <el-form label-position="right" label-width="120px" :model="yxdemandone" v-show="yxdemandone.companySituation==0">
            <b>外包公司信息</b>
            <p></p>
             <el-form-item label="面试名义公司">
               <el-input  v-model="yxdemandone.interviewCompany" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label="面试官">
               <el-input  v-model="yxdemandone.interviewer" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label="面试职位">
               <el-input  v-model="yxdemandone.interviewerPosition" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label="面试地点">
               <el-input  v-model="yxdemandone.interviewaddress" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label="最终甲方">
               <el-input  v-model="yxdemandone.finalparty" @input="sees"></el-input>
             </el-form-item>
          </el-form>
        </div>

        <p></p>
        <div>
          <el-button   @click="opens=!opens">取消</el-button>
          <el-button  type="primary" @click="onSu">确定</el-button>
        </div>
     </el-dialog>
  </div>
</template>

<script>
  import { getYxdemand,listbusiness,addYxdemand,see,exportYxdemand}from "@/api/customer/business";
export default {
  name: "Yxdemand",
  data() {
    return {
      user:"rxg2016",
      opens:false,
      // 跟进抽屉
      drawer :false,
      // 单个数据
      yxdemandone:{},
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 营销录入公司表格数据
      yxdemandList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //公司性质
      companySituationOptions : [],
      //线索状态
      isFollowSubmitOptions : [],
      //距进入公海天数
      entryDaysOptions : [],
      // 回款周期
      companyperiod:[],
      // 客户级别字典
      customerleve:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        companyName: null,
        contactPosition: null,
        companySituation: null,
        contactPhone: null,
        entryPeople: null,
        isFollowSubmit: null,
        contactInformation: null,
        updateDate: null,
        entryDays: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        companyName: [{
          required: true,
          message: "公司名称不能为空",
          trigger: ["blur", "change"]
        }, ],
        contactPeople: [{
          required: true,
          message: "联系人姓名不能为空",
          trigger: ["blur", "change"]
        }, ],
        contactPosition: [{
          required: true,
          message: "联系人职位不能为空",
          trigger: ["blur", "change"]
        }, ],
        contactPhone: [
          { required: true, message: "手机号码不能为空", trigger: "blur" },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: ["blur", "change"]
          }
        ],
        companySituation: [{
          required: true,
          message: "公司性质不能为空",
          trigger: ["blur", "change"]
        }, ],
        isFollowSubmit: [{
          required: true,
          message: "线索状态不能为空",
          trigger: ["blur", "change"]
        }, ],
        contact: [{
          required: true,
          message: "联系情况不能为空",
          trigger: ["blur", "change"]
        }, ],
        customerLevel: [{
          required: true,
          message: "客户级别不能为空",
          trigger: ["blur", ]
        }, ],
        paybackPeriod: [{
          required: true,
          message: "回款周期不能为空",
          trigger: ["blur", ]
        }, ],
        cooperationTime: [{
          required: true,
          message: "合作日期不能为空",
          trigger: ["blur", ]
        }, ],
      },
      // 发布信息
      putmsg:null,
      putmsgs:[],
      // 发布按钮
      put:false,
      username:[],
      timer:-1,
    };
  },
  created() {
    this.getList();
    this.getDicts("yxdemand_company_situation").then(response => {
      this.companySituationOptions = response.data;
    });
    this.getDicts("business_isfollow_submin").then(response => {
      this.isFollowSubmitOptions = response.data;
    });
    this.getDicts("yxdemand_entry_days").then(response => {
      this.entryDaysOptions = response.data;
    });
    this.getDicts("sys_user_name").then(response => {
      this.username = response.data;
    });
    this.getDicts("bus_customer_leve").then(response => {
      this.customerleve = response.data;
    });
    this.getDicts("mar_company_period").then(response => {
      this.companyperiod = response.data;
    });

  },
  methods: {
    // 确认
    onSu(){
      this.yxdemandone.corpName=this.yxdemandone.companyName

      let that = this
      this.$refs["formmsg"].validate(valid=>{
        if(valid){
          console.log(that.yxdemandone)
          this.$confirm('是否确认把该客户转化为合作客户吗吗?', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
               exportYxdemand(that.yxdemandone)
            }).then(() => {
              that.drawer=false
              that.opens=false
              this.msgSuccess("转化成功");
            })
        }
      })
    },
    bul(){
      // debounce(function(){
      //   this.put=false
      // },500)
    },
    // 下拉框选中加载
    change(){
        this.getList()
    },
    changes(value){
      this.sees()
      this.followUp(this.yxdemandone.entryId)
      this.$forceUpdate()
    },
    /** 查询营销录入公司列表 */
    getList() {
      this.loading = true;
      listbusiness(this.queryParams).then(response => {
        this.yxdemandList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 简历线索字典翻译
    companySituationFormat(row, column) {
      return this.selectDictLabel(this.companySituationOptions, row.companySituation);
    },
    // 线索状态字典翻译
    isFollowSubmitFormat(row, column) {
      return this.selectDictLabel(this.isFollowSubmitOptions, row.isBusiness);
    },
    // 距进入公海天数翻译
    entryDaysFormat(row, column) {
      return this.selectDictLabel(this.entryDaysOptions, row.entryDays);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    // 表单重置
    reset() {
      this.form = {
        putmsg:null,
        entryId: null,
        companyName: null,
        recruitmentJob: null,
        contactPeople: null,
        contactPosition: null,
        contactPhone: null,
        infoSourse: null,
        companySituation: null,
        isSendResume: null,
        interviewCompany: null,
        interviewer: null,
        interviewerPosition: null,
        interviewContact: null,
        interviewAddress: null,
        finalParty: null,
        entryPeople: null,
        entryPeopleId: null,
        insertTime: null,
        isFollowSubmit: null,
        robPeopleId: null,
        robPeople: null,
        robTime: null,
        isAccept: null,
        businessId: null,
        businessPeople: null,
        submitTime: null,
        isBusiness: null,
        cooperationProjects: null,
        isSigning: null,
        singTime: null,
        personnelInto: null,
        isReturnMoney: null,
        updateDate: null,
        entryDays: null,
        contact: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.entryId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },

    /** 新建客户线索 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "新建客户线索";
    },
    /** 提交按钮 */
    submitForm(inx) {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.entryId != null) {
            this.msgSuccess("新增失败");
          } else {
            if(inx==1){
              addYxdemand(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.getList();
                this.reset()
              });
            }else{
              addYxdemand(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        }
      });
    },
    // 移交
    onSubmit(id){
      this.opens=true

    },
    // 发布按钮
    putmsgbut(id){
      let formData = new FormData()
      formData.append("entryId",id)
      formData.append("contactDetail",this.putmsg)
      release(formData).then(
      this.followUp(id)
      )
      // putmsgs(this.putmsg).then(msg=>{
      // })
    },
    /** 跟进按钮 */
    followUp(value){
      getYxdemand(value).then(res=>{
        this.drawer = true
        this.yxdemandone=res.data.data.Yxdemand
        this.putmsgs = res.data.data.listYxcon
      })
    },
    // 抽屉关闭回调
    dra(){
      this.getList()
   },
    set(){
      see(this.yxdemandone)
    },
    sees(){
      let that = this
      this.debounce(that.set())
    },
    /**输入框实时修改按钮  */

  }
};
</script>
<style>
  .el-tabs__item:focus.is-active.is-focus:not(:active) {
      -webkit-box-shadow: none !important;
      box-shadow: none !important;
}
  .el-tabs__header{
    background: #F5F5F9;
    padding-left:5%;
  }
  .el-tabs__content{
    padding:20px 3% 0 3%;
  }
   .el-drawer.rtl{
        overflow: auto;
    }
  .msg{
    margin-bottom: 20px;
  }
  .span{
    color: #2C2C2C;
  }
  .sp{
    font-size: 11px;
    color: #8A8A8A;
  }
</style>
