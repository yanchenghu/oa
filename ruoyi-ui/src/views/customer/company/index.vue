<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px" style="width:80% ;">
       <el-form-item label="" prop="companyName">
         <el-input
           v-model="queryParams.companyName"
           placeholder="请输入客户名称"
           clearable
           size="small"
           @keyup.enter.native="handleQuery"
         />
       </el-form-item>
       <el-form-item>
         <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">查询</el-button>
       </el-form-item>
       <el-form-item  prop="companySituation">
         <el-select v-model="queryParams.companySituation"   placeholder="请选择公司性质" clearable size="small" @change="change">
           <el-option
             v-for="dict in companySituationOptions"
             :key="dict.dictValue"
             :label="dict.dictLabel"
             :value="dict.dictValue"
           />
         </el-select>
       </el-form-item>
      <el-form-item prop="customerLevel">
        <el-select v-model="queryParams.customerLevel" placeholder="请选择客户级别" clearable size="small" @change="change">
          <el-option
              v-for="dict in customerleve"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />

        </el-select>
      </el-form-item>
      <el-form-item  prop="paybackPeriod">
        <el-select v-model="queryParams.paybackPeriod" placeholder="请选择回款周期" clearable size="small" @change="change">
          <el-option
              v-for="dict in companyperiod"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
        </el-select>
      </el-form-item>
      <el-button
          type="cyan"
          size="mini"
          @click="handleAdd"
          style="position: absolute;right: 0;margin-right: 50px"
        >新建合作客户</el-button>
    </el-form>
    <el-row :gutter="10" class="mb8">
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="companyList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <!-- <el-table-column label="序列" align="center" prop="corpCode" /> -->
      <el-table-column label="公司名称" align="left" prop="corpName" width="160"/>
      <el-table-column label="公司性质" align="center" prop="companySituation" :formatter="companySituationFormat"/>
      <el-table-column label="联系人/职位" align="left" width="130">
        <template slot-scope="scope">
          <span>{{scope.row.contactPeople}} / {{scope.row.contactPosition}}</span>
        </template>
      </el-table-column>
      <el-table-column label="联系方式" align="left" prop="contactPhone" width="110"/>
      <el-table-column label="客户级别" align="center" prop="customerLevel" :formatter="customerleveFormat" width="100"/>
      <el-table-column label="回款周期" align="center" prop="paybackPeriod" :formatter="companyperiodFormat"/>
      <el-table-column label="合作时间" align="center" prop="cooperationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.cooperationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="录入人" align="center" prop="entryPeople" />
      <el-table-column label="转化人" align="center" prop="transformingPeople" :formatter="usernameFormat"/>
      <el-table-column label="更多" align="center" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-more"
            @click="more(scope.row)"
          >更多</el-button>
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

    <!-- 新建合同 -->
    <el-dialog :title="title" :visible.sync="opens" width="550px" append-to-body>
      <el-form ref="forms" :model="forms" :rules="rules" label-width="120px">
        <el-form-item label="上传合同附件">
          <el-upload
            class="upload-demo"
            ref="upload"
            action="#"
            :limit="1"
            :on-change="handleRemove"
            :auto-upload="false"
            :before-remove="upoplodad"
            >
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="甲方" prop="firstParty">
          <el-input v-model="forms.firstParty" placeholder="请输入甲方公司" />
        </el-form-item>
        <el-form-item label="乙方" prop="party">
          <el-input v-model="forms.party" placeholder="请输入乙方公司" />
        </el-form-item>
        <el-form-item label="合同开始日期" prop="startTime">
          <el-date-picker v-model="forms.startTime" type="date" placeholder="请选择合同开始日期" :picker-options="pickerOptions1" value-format="yyyy-MM-dd" style="width:190px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="合同结束日期" prop="endTime">
          <el-date-picker v-model="forms.endTime" type="date" placeholder="请选择合同结束日期"  :picker-options="pickerOptions4" value-format="yyyy-MM-dd" style="width:190px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="客户签约人" prop="clientSigner">
          <el-input v-model="forms.clientSigner" placeholder="请输入客户签约" />
        </el-form-item>
        <el-form-item label="公司签约人" prop="companySigner">
          <el-input v-model="forms.companySigner" placeholder="请输入公司签约人" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm2">确 定</el-button>
        <el-button @click="cancel2">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 添加客戶 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司名称" prop="corpName">
          <el-input v-model="form.corpName" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="联系人" prop="contactPeople">
          <el-input v-model="form.contactPeople" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="职位" prop="contactPosition">
          <el-input v-model="form.contactPosition" placeholder="请输入职位" />
        </el-form-item>
        <el-form-item label="电话" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="邮箱" prop="mailbox">
          <el-input v-model="form.mailbox" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="微信" prop="wechat">
          <el-input v-model="form.wechat" placeholder="请输入微信" />
        </el-form-item>
        <el-form-item label="QQ" prop="qq">
          <el-input v-model="form.qq" placeholder="请输入QQ" />
        </el-form-item>
        <el-form-item  label="公司性质"  prop="companySituation">
          <el-select v-model="form.companySituation"  size="small">
            <el-option
              v-for="dict in companySituationOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="客户级别" prop="customerLevel">
          <el-select  v-model="form.customerLevel"  size="small">
            <el-option
                v-for="dict,index in customerleve"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="parseInt(dict.dictValue)"
              />
            </el-select>
        </el-form-item>
        <el-form-item label="回款周期" prop="paybackPeriod">
          <el-select  v-model="form.paybackPeriod"  size="small">
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
            v-model="form.cooperationTime"
            type="date"
            placeholder="选择日期"
            value-format="yyyy-MM-dd">
         </el-date-picker>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" autosize placeholder="请输入内容"  v-model="form.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
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
      <div style=" padding:20px 3% 30px 2%; border-bottom: 1px solid #E6E6E6;">
        <div>
          <b>
            {{yxdemandone.corpName}}
          </b>
        </div>
         <el-form :inline="true" :model="yxdemandone" class="demo-form-inline">
           <el-form-item label="公司性质">
             <el-select  v-model="yxdemandone.companySituation"   size="small" @change="changes">
               <el-option
                 v-for="dict in companySituationOptions"
                 :key="dict.dictValue"
                 :label="dict.dictLabel"
                 :value="parseInt(dict.dictValue)"
               />
             </el-select>
           </el-form-item>
           <el-form-item prop="customerLevel" label="客户级别">
             <el-select v-model="yxdemandone.customerLevel" placeholder="请选择客户级别" clearable size="small" @change="changes">
               <el-option
                   v-for="dict in customerleve"
                   :key="dict.dictValue"
                   :label="dict.dictLabel"
                   :value="parseInt(dict.dictValue)"
                 />

             </el-select>
           </el-form-item>
           <el-form-item  prop="paybackPeriod" label="回款周期">
             <el-select v-model="yxdemandone.paybackPeriod" placeholder="请选择回款周期"  size="small" @change="changes">
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
                 <el-form label-position="right" label-width="80px" :model="yxdemandone" :rules="rules">
                    <b>联系人信息</b>
                    <p></p>
                    <el-form-item label="姓名" prop="contactPeople">
                      <el-input  v-model="yxdemandone.contactPeople" ></el-input>
                    </el-form-item>
                    <el-form-item label="职位" prop="contactPosition">
                      <el-input  v-model="yxdemandone.contactPosition" ></el-input>
                    </el-form-item>
                    <el-form-item label="电话" prop="contactPhone">
                      <el-input  v-model="yxdemandone.contactPhone"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="mailbox">
                      <el-input  v-model="yxdemandone.mailbox" ></el-input>
                    </el-form-item>
                    <el-form-item label="微信">
                      <el-input  v-model="yxdemandone.wechat" ></el-input>
                    </el-form-item>
                    <el-form-item label="QQ">
                      <el-input  v-model="yxdemandone.qq" ></el-input>
                    </el-form-item>
                 </el-form>
                 <el-form label-position="left" label-width="100px" :model="yxdemandone">
                   <b>外包公司信息</b>
                   <p></p>
                    <el-form-item label="面试名义公司">
                      <el-input  v-model="yxdemandone.interviewCompany" ></el-input>
                    </el-form-item>
                    <el-form-item label="面试官">
                      <el-input  v-model="yxdemandone.interviewer" ></el-input>
                    </el-form-item>
                    <el-form-item label="面试职位">
                      <el-input  v-model="yxdemandone.interviewerPosition" ></el-input>
                    </el-form-item>
                    <el-form-item label="面试地点">
                      <el-input  v-model="yxdemandone.interviewaddress" ></el-input>
                    </el-form-item>
                    <el-form-item label="最终甲方">
                      <el-input  v-model="yxdemandone.finalparty" ></el-input>
                    </el-form-item>
                 </el-form>
               </div>
            </el-tab-pane>
            <el-tab-pane label="合同">
              <el-button type="primary" @click="handAdd">新建合同</el-button>
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
                    <span>{{ parseTime(scope.row.cooperationTime, '{y}-{m}-{d}') }}</span>
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
                <el-form-item label="转化人">
                  <span class="sp">{{yxdemandone.transformingPeople}}</span>
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
          </el-tabs>
       </div>
      </div>
    </el-drawer>
    <!-- 简历预览 -->
    <el-dialog title="预览" :visible.sync="open3" width="70%">
     <iframe
        :src="src"
        style="overflow: auto; position: absolute; top: 40px; right: 0; bottom: 0; left: 0; width: 100%; height:1000%; border: none;"
      ></iframe>
    </el-dialog>
  </div>
</template>

<script>
import { listCompany, getCompany,  addCompany, updateCompany,contractCompany,addcontract } from "@/api/customer/company";

export default {
  name: "Company",
  data() {
    return {
      pickerOptions1:{
        disabledDate:(time) => {
          if (this.forms.endTime) {
              return time.getTime() > new Date(this.forms.endTime).getTime();
          }
        }
      },
      pickerOptions4:{
        disabledDate:(time) => {
          if (this.forms.startTime){
              return time.getTime() < new Date(this.forms.startTime).getTime()
          }
        }
      },
      opens:false,
      open3:false,
      activeName:"popmsg",
      // 抽屉
      drawer:false,
      // 遮罩层
      loading: true,
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
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
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
      // 表单校验
      rules: {
        corpName: [{
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
        mailbox:[{ type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }],
        customerLevel: [{
          required: true,
          message: "客户级别不能为空",
          trigger: ["blur", "change"]
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
        firstParty: [{
          required: true,
          message: "甲方公司不能为空",
          trigger: ["blur", ]
        }, ],
        party: [{
          required: true,
          message: "乙方公司不能为空",
          trigger: ["blur", ]
        }, ],
        startTime: [{
          required: true,
          message: "合作开始日期不能为空",
          trigger: ["blur", ]
        }, ],
        endTime: [{
          required: true,
          message: "合作结束日期不能为空",
          trigger: ["blur", ]
        }, ],
        clientSigner: [{
          required: true,
          message: "合作结束日期不能为空",
          trigger: ["blur", ]
        }, ],
        companySigner: [{
          required: true,
          message: "合作结束日期不能为空",
          trigger: ["blur", ]
        }, ],
      },
      companySituationOptions:[],
      customerleve:[],

      companyperiod:[],
      username:[],
      // 单个数据
      yxdemandone:{},
      putmsgs:[],
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
    this.getDicts("sys_user_name").then(response => {
      this.username = response.data;
    });
  },
  methods: {
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
    // 转化人
    usernameFormat(row, column) {
      return this.selectDictLabel(this.username, row.transformingPeople);
    },
    change(){
      this.getList()
    },
    changes(){
      updateCompany(this.yxdemandone).then(res=>{
        this.msgSuccess("修改成功")
        this.more(this.yxdemandone)
      })
    },
    dra(){
       this.getList()
    },
    upoplodad(){
      this.$refs.upload.clearFiles()
      this.forms.contractPreview=null
    },
    // 上传文件
    handleRemove(value){
      this.forms.contractPreview=value.raw;
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
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    cancel2() {
      this.opens = false;
      this.reset();
      this.upoplodad()
    },
    // 表单重置
    reset() {
      this.form = {
        corpCode: null,
        corpName: null,
        customerLevel: null,
        paybackPeriod: null,
        cooperationTime: null,
        enteredBy: null,
        transformingPeople: null,
      };
      this.resetForm("form");
      this.resetForm("forms");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 预览文件
    preview(adinw){
      let srcs = process.env.VUE_APP_BASE_API+adinw.contractPreview
      this.src=`https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=http://localhost${srcs}`
      this.open3=true
    },
    // 更多
    more(value){
      getCompany(value.corpCode).then(res=>{
        this.drawer = true
        this.yxdemandone=res.data.data.marCompany
        this.putmsgs = res.data.data.mar
        this.activeName="popmsg"
      })
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加合作公司";
    },
    // 添加合同
    handAdd(){
      this.reset();
      this.opens = true;
      this.title = "新建合同";
      
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          addcontract(this.form).then(response => {
            this.msgSuccess("新增成功");
            this.open = false;
            this.getList();
          });
        }
      });
    },
    submitForm2(){
      if(this.forms.contractPreview){
        this.$refs["forms"].validate(valid => {
          let formData = new FormData()
          formData.append("corpCode",this.yxdemandone.corpCode)
          formData.append("contractPreview",this.forms.contractPreview)
          formData.append("firstParty",this.forms.firstParty)
          formData.append("party",this.forms.party)
          formData.append("startTime",this.forms.startTime)
          formData.append("endTime",this.forms.endTime)
          formData.append("clientSigner",this.forms.clientSigner)
          formData.append("companySigner",this.forms.companySigner)
          if (valid) {
            addCompany(formData).then(response => {
              this.msgSuccess("新增成功");
              this.opens = false;
              this.get();
              this.upoplodad()
              this.more(this.yxdemandone)
            });
          }
        });
      }else{
        this.msgError("请选择文件")
      }

    },

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
      .span{
        color: #2C2C2C;
      }
      .sp{
        font-size: 11px;
        color: #8A8A8A;
      }
</style>
