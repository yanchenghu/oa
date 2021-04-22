<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" style="width:80% ;" label-width="68px" @submit.native.prevent>
      <el-form-item label="" prop="companyName">
        <el-input
          v-model.trim="queryParams.companyName"
          placeholder="请输入公司名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item >
        <el-button type="primary" icon="el-icon-search" size="small" @click="handleQuery">查询</el-button>
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
      <el-form-item  prop="isFollowSubmit">
        <el-select v-model.trim="queryParams.isFollowSubmit" placeholder="请选择线索状态" clearable size="small" @change="change">
          <el-option
            v-for="dict in isFollowSubmitOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-button
          type="primary"
          size="small"
          @click="handleAdd"
          style="position: absolute;right: 0;margin-right: 30px"
        >新建客户线索</el-button>
    </el-form>
   <el-row :gutter="10" class="mb8">
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

<!--    展示-->
    <el-table v-loading="loading" :data="yxdemandList" >
      <el-table-column label="公司名称" align="left" prop="companyName" width="250">
        <template slot-scope="scope">
          <el-button
            type="text"
            @click="followUp(scope.row.entryId)"
          >{{scope.row.companyName}}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="联系人/职位"  width="130">
        <template slot-scope="scope">
          <span>{{scope.row.contactPeople}} / {{scope.row.contactPosition}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="公司性质"
        prop="companySituation"
        :formatter="companySituationFormat"
        width="90"
      />
      <el-table-column label="联系方式"  prop="contactPhone" width="110"/>
      <el-table-column label="录入人"  prop="businessPeople" />
      <el-table-column
        label="线索状态"
        prop="isFollowSubmit"
        :formatter="isFollowSubmitFormat"
         width="90"
      />
      <el-table-column label="最近一次联系情况"  prop="contactInformation"  >
        <template slot-scope="scope">
          <span>{{scope.row.contactInformation}}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间"  prop="updateDate" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="距进入公海天数"
        prop="qq"
        width="80"
        align="center"
      >
      <template slot-scope="scope">
        <span v-if="scope.row.qq==1||scope.row.qq==2||scope.row.qq==3">{{scope.row.qq}}</span>
      </template>
      </el-table-column>
      <el-table-column label="操作"  class-name="small-padding fixed-width"  width="60">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.isFollowSubmit!==4"
            type="text"
            @click="followUp(scope.row.entryId)"
          ><svg-icon icon-class="genzong"/>跟进</el-button>
          <el-button
            v-else
            type="text"
            @click="followUp(scope.row.entryId)"
          ><svg-icon icon-class="eye-open"/>查看</el-button>
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
       <div style=" padding:20px 3% 30px 3%; border-bottom: 1px solid #E6E6E6;">
         <div>
           <b>
             {{yxdemandone.companyName}}
           </b>
         </div>
         <br/>
          <el-form :inline="true" :model="yxdemandone" class="demo-form-inline">
            <el-form-item label="公司性质">
              <el-select  v-model.trim="yxdemandone.companySituation"  @change="changes(yxdemandone.entryId)" size="small" :disabled="yxdemandone.isFollowSubmit==4">
                <el-option
                  v-for="dict in companySituationOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="parseInt(dict.dictValue)"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="线索状态">
              <el-select  v-model.trim="yxdemandone.isFollowSubmit" @change="changes(yxdemandone.entryId)"  size="small" :disabled="yxdemandone.isFollowSubmit==4">
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
                <el-button :disabled="yxdemandone.isFollowSubmit!==3" type="primary" @click="onSubmit">转化为合作用户</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div>
          <el-tabs>
            <el-tab-pane label="联系人信息">
              <div style="display: flex; justify-content: space-between;">
              <el-form ref="formmsg" label-position="right" label-width="80px" :model="yxdemandone" :disabled="yxdemandone.isFollowSubmit==4" :rules="rules">
                 <b>联系人信息</b>
                 <p></p>
                 <el-form-item label="姓名" prop="contactPeople">
                   <el-input  v-model.trim="yxdemandone.contactPeople" @input="sees" ></el-input>
                 </el-form-item>
                 <el-form-item label="职位" prop="contactPosition">
                   <el-input  v-model.trim="yxdemandone.contactPosition" @input="sees" ></el-input>
                 </el-form-item>
                 <el-form-item label="电话" prop="contactPhone">
                   <el-input  v-model.trim="yxdemandone.contactPhone" @input="sees" ></el-input>
                 </el-form-item>
                 <el-form-item label="邮箱" prop="mailbox">
                   <el-input  v-model.trim="yxdemandone.mailbox" @input="sees" ></el-input>
                 </el-form-item>
                 <el-form-item label="微信">
                   <el-input  v-model.trim="yxdemandone.wechat" @input="sees" ></el-input>
                 </el-form-item>
                 <el-form-item label="QQ">
                   <el-input  v-model.trim="yxdemandone.qq" @input="sees" ></el-input>
                 </el-form-item>
              </el-form>
              <el-form label-position="left" label-width="100px" :model="yxdemandone" :disabled="yxdemandone.isFollowSubmit==4">
                <b>外包公司信息</b>
                <p></p>
                 <el-form-item label="面试名义公司">
                   <el-input  v-model.trim="yxdemandone.interviewCompany" @input="sees" ></el-input>
                 </el-form-item>
                 <el-form-item label="面试官">
                   <el-input  v-model.trim="yxdemandone.interviewer" @input="sees" ></el-input>
                 </el-form-item>
                 <el-form-item label="面试职位">
                   <el-input  v-model.trim="yxdemandone.interviewerPosition" @input="sees" ></el-input>
                 </el-form-item>
                 <el-form-item label="面试地点">
                   <el-input  v-model.trim="yxdemandone.interviewAddress" @input="sees" ></el-input>
                 </el-form-item>
                 <el-form-item label="最终甲方">
                   <el-input  v-model.trim="yxdemandone.finalParty" @input="sees" ></el-input>
                 </el-form-item>
              </el-form>
            </div>
            </el-tab-pane>

            <el-tab-pane label="联系记录">
              <div style="padding-left:2%;">
                <div class="msg">
                  <b>联系记录</b>
                </div>
                <el-input type="textarea"   autosize placeholder="添加跟进信息"  v-model.trim="putmsg" @focus="put=true" @blur="bul" :disabled="yxdemandone.isFollowSubmit==4"></el-input>
                <el-button v-show="put" style="margin-top: 10px;" type="primary" @click="putmsgbut(yxdemandone.entryId)">发布</el-button>
                <div style="clear: both;margin-top: 10px;"></div>
                <ul style="list-style: none;padding-left: 10px;">
                  <li v-for="msg in putmsgs">
                    <span class="sp">{{msg.contactTime}} {{msg.nickName}} </span>
                    <span v-if="msg.status==3" class="sp">跟进</span>
                    <span v-else-if="msg.status==2" class="sp">{{msg.contactDetail}}</span>
                    <span v-else-if="msg.status==1" class="sp">录入客户</span>
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
          <el-form label-position="right"  label-width="100px" :model="yxdemandone2" :rules="rules" ref="formmsgs">
             <b>联系人信息</b>
             <p></p>
             <el-form-item label=" 公司名称" >
               <el-input size="small" disabled v-model.trim="yxdemandone2.companyName"/>
             </el-form-item>
             <el-form-item label="姓名" prop="contactPeople">
               <el-input size="small" v-model.trim="yxdemandone2.contactPeople" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label="职位" prop="contactPosition">
               <el-input size="small" v-model.trim="yxdemandone2.contactPosition" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label="电话" prop="contactPhone">
               <el-input size="small" v-model.trim="yxdemandone2.contactPhone" v-on:input="sees"></el-input>
             </el-form-item>
             <el-form-item label=" 邮箱" prop="mailbox">
               <el-input size="small" v-model.trim="yxdemandone2.mailbox" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label=" 微信">
               <el-input  size="small" v-model.trim="yxdemandone2.wechat" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label=" QQ">
               <el-input size="small"  v-model.trim="yxdemandone2.qq" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label="客户级别" prop="customerLevel">

               <el-select style="width:100%" v-model="yxdemandone2.customerLevel"  size="small">
                 <el-option
                     v-for="dict,index in customerleve"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="parseInt(dict.dictValue)"
                   />
                 </el-select>

             </el-form-item>
             <el-form-item label="回款周期" prop="paybackPeriod">
               <el-select style="width:100%"  v-model="yxdemandone2.paybackPeriod"  size="small">
                 <el-option
                     v-for="dict,index in companyperiod"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="parseInt(dict.dictValue)"
                   />
                </el-select>
             </el-form-item>
             <el-form-item label="合作日期" prop="cooperationTime">
              <el-date-picker
                 size="small"
                 v-model.trim="yxdemandone2.cooperationTime"
                 type="date"
                 placeholder="选择日期"
                 value-format="yyyy-MM-dd">
              </el-date-picker>
             </el-form-item>
             <el-form-item label="备注">
               <el-input type="textarea" autosize placeholder="请输入内容"  v-model.trim="yxdemandone2.remark"></el-input>
             </el-form-item>
          </el-form>
          <el-form label-position="right" label-width="120px" :model="yxdemandone2" v-show="yxdemandone2.companySituation==0">
            <b>外包公司信息</b>
            <p></p>
             <el-form-item label="面试名义公司">
               <el-input  v-model.trim="yxdemandone2.interviewCompany" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label="面试官">
               <el-input  v-model.trim="yxdemandone2.interviewer" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label="面试职位">
               <el-input  v-model.trim="yxdemandone2.interviewerPosition" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label="面试地点">
               <el-input  v-model.trim="yxdemandone.interviewAddress" @input="sees"></el-input>
             </el-form-item>
             <el-form-item label="最终甲方">
               <el-input  v-model.trim="yxdemandone2.finalParty" @input="sees"></el-input>
             </el-form-item>
          </el-form>
        </div>

        <p></p>
        <div>
          <el-button   @click="opens=!opens">取消</el-button>
          <el-button  type="primary" @click="onSu">确定</el-button>
        </div>
     </el-dialog>
     <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
       <el-form ref="form" :model="form" :rules="rules" label-width="125px" style="width: 620px;">
         <el-form-item label="公司名称" prop="companyName" >
           <el-input  v-model.trim="form.companyName"  placeholder="请输入公司名称"  @blur="findname(form.companyName)" style="width: 251px;"/>
           &nbsp;<span v-if="msg=='该客户不存在'" style="color: green;line-height: 40px;position: absolute;"> <i class="el-icon-success"></i></span>
         </el-form-item>
         <el-form-item label="联系人姓名" prop="contactPeople">
           <el-input v-model.trim="form.contactPeople"  placeholder="请输入联系人" style="width: 251px;"/>
         </el-form-item>
         <el-form-item label="联系人职位" prop="contactPosition">
           <el-input v-model.trim="form.contactPosition"  placeholder="请输入联系人职位"style="width: 251px;" />
         </el-form-item>
         <el-form-item label="联系人电话" prop="contactPhone">
           <el-input v-model.trim="form.contactPhone"  placeholder="请输入联系方式" style="width: 251px;"/>
         </el-form-item>
         <el-form-item label="公司性质"  prop="companySituation">
            <el-radio-group v-model.trim="form.companySituation">
               <el-radio v-for="dict in companySituationOptions" :key="dict.dictValue" :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
             </el-radio-group>
         </el-form-item>
         <el-form-item label="线索状态" prop="isFollowSubmit">
           <el-radio-group v-model.trim="form.isFollowSubmit">
              <el-radio v-for="dict in isFollowSubmitOptions" :key="dict.dictValue" :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
            </el-radio-group>
         </el-form-item>
         <el-form-item label="联系情况" prop="contactInformation">
           <el-input type="textarea" autosize placeholder="请输入最近一次联系情况" v-model.trim="form.contactInformation"></el-input>
         </el-form-item>
       </el-form>
       <div slot="footer" class="dialog-footer">
         <el-button type="primary" @click="submitForm(1)">保存并继续</el-button>
         <el-button  @click="submitForm(2)">保 存</el-button>
         <el-button @click="cancel">取 消</el-button>
       </div>
     </el-dialog>
  </div>
</template>

<script>
  import { getYxdemand,listbusiness,addYxdemand,see,exportYxdemand,release,findnames}from "@/api/customer/business";
  import {debounce} from "@/utils/ruoyi.js"
export default {
  name: "Yxdemand",
  data() {
    var checkAge = (rule, value, callback) => {
       if (!value) {
         return callback(new Error('公司名称不能为空'));
       }
       setTimeout(() => {
        if(value.length<5){
          callback(new Error("公司名称长度不能小于5"));
          this.msg="该客户"
        }else if(value.indexOf("公司") == -1){
          callback(new Error("公司名称包含公司两字"));
          this.msg="该客户"
        }else{
          if (this.msg!=="该客户不存在") {
            callback(new Error(this.msg));
          }else{
            callback()
          }
        }
       }, 1000);
      };
    var phone = (rule, value, callback) => {
        if (!value) {
         return callback(new Error('不能为空'))
        } else {
          const reg = /^1[3|4|5|6|7|8|9][0-9]\d{8}$/
          const isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/
          if (reg.test(value)||isPhone.test(value)) {
            callback()
          } else {
            return callback(new Error('请输入正确的手机号'))
          }
        }
      }
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
      msg:null,
      // 表单校验
      rules: {
        companyName: [{
          required: true,
          validator: checkAge,
          trigger: ["blur"]
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
          { required: true,validator: phone, trigger: ["blur", ] },
        ],
        mailbox:[{
          type: 'email',
          message: '请输入正确的邮箱地址',
          trigger: ['blur', 'change'] },
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
      yxdemandone2:{},
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
      this.yxdemandone2.corpName=this.yxdemandone2.companyName
      let that = this
      this.$refs["formmsgs"].validate(valid=>{
        if(valid){
          this.$confirm('是否确认把该客户转化为合作客户吗吗?', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
               exportYxdemand(that.yxdemandone2)
            }).then(() => {
              that.drawer=false
              that.opens=false
              this.msgSuccess("转化成功");
            })
        }
      })
    },
    bul:debounce(function(){this.put=false},500),
    // 下拉框选中加载
    change(){
        this.getList()
    },
    changes(value){
      see(this.yxdemandone).then(res=>{
        this.msgSuccess("修改成功")
        this.followUp(value)
      })
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
      return this.selectDictLabel(this.isFollowSubmitOptions, row.isFollowSubmit);
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
    findname(name){
      if(name==""||name==null){
        this.msg=null
      }else{
        let formData = new FormData()
        formData.append("companyName",name)
        findnames(formData).then(res=>{
          this.msg=res
        })
      }
    },
    // 表单重置
    reset() {
      this.msg=null,
      this.form = {
        entryId: null,
        companyName: null,
        recruitmentJob: null,
        contactPeople: null,
        contactPosition: null,
        contactInformation:null,
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
        isFollowSubmit: null,
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
    jixu1:debounce(function(){this.jixu()},500),
    jixu(){
      addYxdemand(this.form).then(response => {
      this.msgSuccess("新增成功");
      this.getList();
      this.reset()
      });
    },
    baocun1:debounce(function(){this.baocun()},500),
    baocun(){
      addYxdemand(this.form).then(response => {
      this.msgSuccess("新增成功");
      this.open = false;
      this.getList();
      });
    },
    submitForm(inx) {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.entryId != null) {
            this.msgSuccess("新增失败");
          } else {
            if(inx==1){
              this.jixu1()
            }else{
              this.baocun1()
            }
          }
        }else{
          this.msg=null
        }
      });
    },
    // 移交
    onSubmit(id){
      this.opens=true
      this.yxdemandone2 = {}
      this.resetForm("formmsgs");
      this.yxdemandone2 = JSON.parse(JSON.stringify(this.yxdemandone))
    },
    // 发布按钮
    putmsgbut(id){
      let formData = new FormData()
      formData.append("entryId",id)
      formData.append("contactDetail",this.putmsg)
      release(formData).then(res=>{
        this.followUp(id)
        this.putmsg=""
        this.msgSuccess("发布成功")
      })
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
    /**输入框实时修改按钮  */
    sees:debounce(function(){this.$refs["formmsg"].validate(valid => {
        if (valid) {
          this.set()
        }
        })},1000),
  }
};
</script>
<style scoped>
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
