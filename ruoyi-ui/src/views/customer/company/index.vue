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
      <el-button
          type="primary"
          size="mini"
          @click="handleAdd"
          style="position: absolute;right: 0;margin-right: 50px"
        >新建合作客户</el-button>
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
    <!-- 新建合同 -->
    <el-dialog :title="title" :visible.sync="opens" width="550px" append-to-body>
      <el-form ref="forms" :model="forms" :rules="rules" label-width="120px">
        <el-form-item label="上传合同附件">
          <el-upload
            class="upload-demo"
            ref="upload"
            action="#"
            accept=".docx,.doc,.pdf"
            :limit="1"
            :on-change="handleRemove"
            :auto-upload="false"
            :before-remove="upoplodad"
            >
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="甲方" prop="firstParty">
          <el-input v-model.trim="forms.firstParty" placeholder="请输入甲方公司" />
        </el-form-item>
        <el-form-item label="乙方" prop="party">
          <el-input v-model.trim="forms.party" placeholder="请输入乙方公司" />
        </el-form-item>
        <el-form-item label="合同开始日期" prop="startTime">
          <el-date-picker v-model.trim="forms.startTime" type="date" placeholder="请选择合同开始日期" :picker-options="pickerOptions1" value-format="yyyy-MM-dd" style="width:190px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="合同结束日期" prop="endTime">
          <el-date-picker v-model.trim="forms.endTime" type="date" placeholder="请选择合同结束日期"  :picker-options="pickerOptions4" value-format="yyyy-MM-dd" style="width:190px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="客户签约人" prop="clientSigner">
          <el-input v-model.trim="forms.clientSigner" placeholder="请输入客户签约" />
        </el-form-item>
        <el-form-item label="公司签约人" prop="companySigner">
          <el-input v-model.trim="forms.companySigner" placeholder="请输入公司签约人" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm2">确 定</el-button>
        <el-button @click="cancel2">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 添加客戶 -->
    <el-dialog :title="title" :visible.sync="open" width="650px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司名称" prop="corpName">
          <el-input v-model.trim="form.corpName" placeholder="请输入公司名称" @blur="findname(form.corpName)" class="indd"/>
          &nbsp;<span v-if="msg=='当前公司不存在'" style="color: green;line-height: 40px;position: absolute;"> <i class="el-icon-success"></i></span>
        </el-form-item>
        <el-form-item label="联系人" prop="contactPeople">
          <el-input v-model.trim="form.contactPeople" placeholder="请输入联系人" class="indd"/>
        </el-form-item>
        <el-form-item label="职位" prop="contactPosition">
          <el-input v-model.trim="form.contactPosition" placeholder="请输入职位" class="indd"/>
        </el-form-item>
        <el-form-item label="电话" prop="contactPhone">
          <el-input v-model.trim="form.contactPhone" placeholder="请输入电话" class="indd"/>
        </el-form-item>
        <el-form-item label="邮箱" prop="mailbox">
          <el-input v-model.trim="form.mailbox" placeholder="请输入邮箱" class="indd"/>
        </el-form-item>
        <el-form-item label="微信" prop="wechat">
          <el-input v-model.trim="form.wechat" placeholder="请输入微信" class="indd"/>
        </el-form-item>
        <el-form-item label="QQ" prop="qq">
          <el-input v-model.trim="form.qq" placeholder="请输入QQ" class="indd"/>
        </el-form-item>
        <el-form-item label="公司性质"  prop="companySituation">
           <el-radio-group v-model.trim="form.companySituation">
              <el-radio v-for="dict in companySituationOptions" :key="dict.dictValue" :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item label="客户级别"  prop="customerLevel">
           <el-radio-group v-model.trim="form.customerLevel">
              <el-radio v-for="dict in customerleve" :key="dict.dictValue" :label="parseInt(dict.dictValue)">{{dict.dictLabel}}</el-radio>
            </el-radio-group>
        </el-form-item>

        <el-form-item label="回款周期" prop="paybackPeriod">
          <el-select  v-model.trim="form.paybackPeriod"  size="small">
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
            class="indd"
            v-model.trim="form.cooperationTime"
            type="date"
            placeholder="选择日期"
            value-format="yyyy-MM-dd">
         </el-date-picker>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" autosize placeholder="请输入内容"  v-model.trim="form.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
<el-dialog :title="title" :visible.sync="open2" width="650px" append-to-body>
      <el-form ref="form3" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="联系人" prop="contactPeople">
          <el-input v-model.trim="form.contactPeople" placeholder="请输入联系人" class="indd"/>
        </el-form-item>
        <el-form-item label="职位" prop="contactPosition">
          <el-input v-model.trim="form.contactPosition" placeholder="请输入职位" class="indd"/>
        </el-form-item>
        <el-form-item label="电话" prop="contactPhone">
          <el-input v-model.trim="form.contactPhone" placeholder="请输入电话" class="indd"/>
        </el-form-item>
        <el-form-item label="备注" prop="bz">
          <el-input v-model.trim="form.bz" placeholder="请输入备注" class="indd"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm3">确 定</el-button>
        <el-button @click="open2=false">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="选择移交对象" :visible.sync="open4" width="400px" style="text-align: center;">
       <el-select v-model="yxdemandone.transformingPeople" ref="selec" size="small">
         <el-option
           v-for="dict in username"
           :key="dict.user_name"
           :label="dict.nick_name"
           :value="dict.user_name"
         />
       </el-select>
       <p></p>
       <div>
         <el-button   @click="open4=!open4">取消</el-button>
         <el-button  type="primary" @click="changess">确定</el-button>
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
      <div style=" padding:20px 3% 30px 3%; border-bottom: 1px solid #E6E6E6;">
        <div>
          <b>
            <el-input size="small" style="width: 50%;margin-right: 20px;" v-model.trim="yxdemandone.corpName" @blur="findnames2(yxdemandone.corpName,yxdemandone.corpCode)"></el-input>
             <el-button size="small" type="primary" @click="yijiao">移交</el-button>
          </b>
        </div>
        <br/>
         <el-form :inline="true" :model="yxdemandone" class="demo-form-inline">
           <el-form-item label="公司性质">
             <el-select  v-model.trim="yxdemandone.companySituation"   size="small" @change="changes">
               <el-option
                 v-for="dict in companySituationOptions"
                 :key="dict.dictValue"
                 :label="dict.dictLabel"
                 :value="parseInt(dict.dictValue)"
               />
             </el-select>
           </el-form-item>
           <el-form-item prop="customerLevel" label="客户级别">
             <el-select v-model.trim="yxdemandone.customerLevel" placeholder="请选择客户级别"  size="small" @change="changes">
               <el-option
                   v-for="dict in customerleve"
                   :key="dict.dictValue"
                   :label="dict.dictLabel"
                   :value="parseInt(dict.dictValue)"
                 />
             </el-select>
           </el-form-item>
           <el-form-item  prop="paybackPeriod" label="回款周期">
             <el-select v-model.trim="yxdemandone.paybackPeriod" placeholder="请选择回款周期"  size="small" @change="changes">
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
          <el-tabs v-model.trim="activeName"  @tab-click="handleClick">
            <el-tab-pane label="联系人信息" name="popmsg">
               <div style="display: flex; justify-content: space-between;">
                 <el-form ref="formmsg" label-position="right" label-width="80px" :model="yxdemandone" :rules="rules" :disabled="yxdemandone.isAccept==1">
                    <b>联系人信息</b>
                    <p></p>
                    <el-form-item label="姓名" prop="contactPeople">
                      <el-input v-model.trim="yxdemandone.contactPeople" @input="sees"></el-input>
                    </el-form-item>
                    <el-form-item label="职位" prop="contactPosition">
                      <el-input  v-model.trim="yxdemandone.contactPosition" @input="sees"></el-input>
                    </el-form-item>
                    <el-form-item label="电话" prop="contactPhone">
                      <el-input  v-model.trim="yxdemandone.contactPhone" @input="sees"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="mailbox">
                      <el-input  v-model.trim="yxdemandone.mailbox" @input="sees"></el-input>
                    </el-form-item>
                    <el-form-item label="微信">
                      <el-input v-model.trim="yxdemandone.wechat" @input="sees"></el-input>
                    </el-form-item>
                    <el-form-item label="QQ">
                      <el-input  v-model.trim="yxdemandone.qq" @input="sees"></el-input>
                    </el-form-item>
                 </el-form>
                 <div>
                   <el-button type="primary" @click="handAdds">新建联系方式</el-button>
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
            <!-- <el-tab-pane label="联系记录">
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
              </el-tab-pane> -->
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
import { listCompanys, getCompany,  addCompany, updateCompany,contractCompany,addcontract,addcontracts,find,findnames} from "@/api/customer/company";
import {accordingDemand} from "@/api/perentry/entry";
import { allBusiness,}from "@/api/customer/business";
import {debounce,checkImg} from "@/utils/ruoyi.js"
export default {
  name: "Company",
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
          if (this.msg!=="当前公司不存在") {
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
      open4:false,
      opens:false,
      open3:false,
      open5:false,
      activeName:"popmsg",
      listAuditeditor:[],
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
      dialogImageUrl:"",
      dialogVisible:false,
      // 合同列表
      total2:0,
      // 合作公司表格数据
      companyList: [],
      // 合同列表
      contractlist:[],
      listlianxi:[],
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
      msg:"",
      // 表单校验
      rules: {
        corpName: [{
          required: true,
          validator: checkAge,
          trigger: ["blur", ]
        }, ],
        contactPeople: [{
          required: true,
          message: "联系人姓名不能为空",
          trigger: ["blur", ]
        }, ],
        contactPosition: [{
          required: true,
          message: "联系人职位不能为空",
          trigger: ["blur", ]
        }, ],
        contactPhone: [
          { required: true,validator: phone, trigger: ["blur", ] },
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
      open2:false,
      companyperiod:[],
      username:[],
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
      this.$router.push({ path:'/follow/particulars',query:{row:row.demandId,ident:8}})
    },
    xuqiu(row){
      accordingDemand({corpCode:row.corpCode}).then(res=>{
        this.xuqiumingc = res
        this.open5 = true
        this.title = row.corpName
      })
    },
    yijiao(){
      allBusiness().then(response => {
        this.open4=true
        this.username = response.data;
      });
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
        this.src=`https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=${srcs+file}`
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
    // 转化人
    usernameFormat(row, column) {
      return this.selectDictLabel(this.username, row.transformingPeople);
    },
    change(){
      this.getList()
    },
    set(){
        this.$refs["formmsg"].validate(valid => {
          if (valid) {
            updateCompany(this.yxdemandone)
          }
        });
    },
    sees:debounce(function(){
          this.set()
    },1000),
    changes(){
      updateCompany(this.yxdemandone).then(res=>{
        this.msgSuccess("修改成功")
        this.getone(this.yxdemandone)
      })
    },
    changess(){
      updateCompany(this.yxdemandone).then(res=>{
        this.msgSuccess("移交成功")
        this.open4 = false
         this.drawer = false
      })
    },
    dra(){
       this.getList()
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
    findnames2(name,code){
        if (name==""||name==null) {
         this.msgError('公司名称不能为空');
        }else{
          setTimeout(() => {
           if(name.length<5){
             this.msgError('公司名称长度不能小于5');
           }else if(name.indexOf("公司") == -1){
             this.msgError("公司名称包含公司两字");
           }else{
             let formData = new FormData()
             formData.append("corpName",name)
             formData.append("corpCode",code)
             find(formData).then(res=>{
               if (res=="当前公司名称已存在") {
                 this.msgError(res);
               }else if(res=="修改名称与原名称相同"){
                 this.msgError(res);
               }else{
                 this.sees()
               }
             })
           }
          }, 1000);
        }
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
      listCompanys(this.queryParams).then(response => {
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
        party:null,
        corpCode: null,
        corpName: null,
        contactPeople:null,
        contactPosition:null,
        contactPhone:null,
        mailbox:null,
        wechat:null,
        qq:null,
        customerLevel: null,
        companySituation:null,
        remark:null,
        paybackPeriod: null,
        cooperationTime: null,
        enteredBy: null,
        startTime:null,
        endTime:null,
        clientSigner:null,
        companySigner:null,
      };
      this.forms = {
        party:null,
        firstParty:null,
        startTime:null,
        endTime:null,
        clientSigner:null,
        companySigner:null,
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
      this.src=`https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=${srcs}`
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
      this.forms.firstParty = this.yxdemandone.corpName
    },
    handAdds(){
      this.reset();
      this.form.corpCode = this.yxdemandone.corpCode
      this.open2 = true;
      this.title = "新建联系人";
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
    submitForm3() {
      this.$refs["form3"].validate(valid => {
        if (valid) {
          addcontracts(this.form).then(response => {
            this.msgSuccess("新增成功");
            this.open2 = false;
            this.getone(this.yxdemandone)
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
              this.getone(this.yxdemandone)
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
