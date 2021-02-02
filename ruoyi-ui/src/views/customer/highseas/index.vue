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
        <el-button type="primary" icon="el-icon-search" size="mini"@click="handleQuery">查询</el-button>
      </el-form-item>
      <el-button
          type="primary"
          size="mini"
          @click="next"
          style="position: absolute;right: 120px;"
        >换一批</el-button>
       <!-- <el-button
            type="warning"
            size="mini"
            @click="handleAdd"
            style="position: absolute;right: 30px;"
          >批量抢占</el-button> -->
    </el-form>
   <el-row :gutter="10" class="mb8">
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

<!--    展示-->
    <el-table v-loading="loading" :data="yxdemandList" >
      <el-table-column
        type="selection"
        width="55"
        align="center"
        >
      </el-table-column>
      <el-table-column label="公司名称"  prop="companyName" width="250">
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
      <el-table-column label="录入人"  prop="entryPeople" width="80"/>
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
          <span>{{ parseTime(scope.row.updateDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="抢占人"  prop="robPeople" width="80">
        <template slot-scope="scope">
          <span v-if="scope.row.isAccept==0">
            {{scope.row.robPeople}}
          </span>
          <span v-else>
            {{scope.row.businessPeople}}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="操作"  class-name="small-padding fixed-width"  width="120">
        <template slot-scope="scope">
          <span  v-if="scope.row.isAccept==0">
            <el-button
                v-if="scope.row.robPeopleId==null || scope.row.robPeopleId==''"
                type="text"
                @click="handleClick(scope.row)"
              ><svg-icon icon-class="button"/>抢占</el-button>
             <el-button
                type="text"
                @click="followUp(scope.row.entryId)"
              ><svg-icon icon-class="eye-open"/>查看</el-button>
          </span>

          <span v-else>
            <el-button
               v-if="scope.row.businessId==null || scope.row.businessId==''"
               type="text"
               @click="handleClick(scope.row)"
             ><svg-icon icon-class="button"/>抢占</el-button>
            <el-button
               type="text"
               @click="followUp(scope.row.entryId)"
             ><svg-icon icon-class="eye-open"/>查看</el-button>
          </span>
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
              <el-select disabled v-model.trim="yxdemandone.companySituation"   size="small">
                <el-option
                  v-for="dict in companySituationOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="parseInt(dict.dictValue)"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="线索状态">
              <el-select disabled v-model.trim="yxdemandone.isFollowSubmit"  size="small">
                <el-option
                  v-for="dict in isFollowSubmitOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="parseInt(dict.dictValue)"
                />
              </el-select>
            </el-form-item>
            <el-form-item v-if="yxdemandone.isAccept==0">
                  <el-button v-show="yxdemandone.robPeopleId==null || yxdemandone.robPeopleId=='' "   type="warning" @click="handleClick(yxdemandone)">抢占</el-button>
            </el-form-item>
            <el-form-item v-else>
                  <el-button v-show="yxdemandone.businessId==null || yxdemandone.businessId=='' "   type="warning" @click="handleClick(yxdemandone)">抢占</el-button>
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
                   <el-input disabled v-model.trim="yxdemandone.contactPeople" ></el-input>
                 </el-form-item>
                 <el-form-item label="职位">
                   <el-input disabled v-model.trim="yxdemandone.contactPosition" ></el-input>
                 </el-form-item>
                 <el-form-item label="电话">
                   <el-input disabled v-model.trim="yxdemandone.contactPhone"></el-input>
                 </el-form-item>
                 <el-form-item label="邮箱">
                   <el-input disabled v-model.trim="yxdemandone.mailbox" ></el-input>
                 </el-form-item>
                 <el-form-item label="微信">
                   <el-input disabled v-model.trim="yxdemandone.wechat" ></el-input>
                 </el-form-item>
                 <el-form-item label="QQ">
                   <el-input disabled v-model.trim="yxdemandone.qq" ></el-input>
                 </el-form-item>
              </el-form>
              <el-form label-position="left" label-width="100px" :model="yxdemandone">
                <b>外包公司信息</b>
                <p></p>
                 <el-form-item label="面试名义公司">
                   <el-input disabled v-model.trim="yxdemandone.interviewCompany" ></el-input>
                 </el-form-item>
                 <el-form-item label="面试官">
                   <el-input disabled v-model.trim="yxdemandone.interviewer" ></el-input>
                 </el-form-item>
                 <el-form-item label="面试职位">
                   <el-input disabled v-model.trim="yxdemandone.interviewerPosition" ></el-input>
                 </el-form-item>
                 <el-form-item label="面试地点">
                   <el-input disabled v-model.trim="yxdemandone.interviewaddress" ></el-input>
                 </el-form-item>
                 <el-form-item label="最终甲方">
                   <el-input disabled v-model.trim="yxdemandone.finalparty" ></el-input>
                 </el-form-item>
              </el-form>
            </div>
            </el-tab-pane>

            <el-tab-pane label="联系记录">
              <div style="padding-left:2%;">
                <div class="msg">
                  <b>联系记录</b>
                </div>
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
  </div>
</template>

<script>

import { getYxdemand,listYxdemand,addYxdemand, see ,yxhandrob,turnover,swhandrob} from "@/api/customer/highseas.js";
export default {
  name: "Yxdemand",
  data() {
    return {
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
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        companyName: null,
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
        contactInformation: [{
          required: true,
          message: "联系情况不能为空",
          trigger: ["blur", "change"]
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
    this.getDicts("yxdemand_isfollow_submit").then(response => {
      this.isFollowSubmitOptions = response.data;
    });
  },
  methods: {
    // 下拉框选中加载
    change(){
        this.getList()
    },
    /** 查询营销录入公司列表 */
    getList() {
      this.loading = true;
      listYxdemand(this.queryParams).then(response => {
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
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
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
    // 移交
    onSubmit(id){
      this.opens=true
    },
    // 换一批
    next(){
      this.queryParams.pageNum += 1
      if(this.queryParams.pageNum-1>=this.total/this.queryParams.pageSize){
        this.queryParams.pageNum = 1
        this.getList()
      }else{
        this.getList()
      }
    },
    // 抢占客户
    handleClick(value){
      var formData = new FormData()
      formData.append("entryId",value.entryId)
      if(value.isAccept==0){
        // 营销抢占
        yxhandrob(formData).then(res=>{
          this.msgSuccess("抢占成功")
          this.getList()
        })
      }else{
        // 商务抢占
        swhandrob(formData).then(res=>{
          this.msgSuccess("抢占成功")
          this.getList()
        })
      }
    },
    /** 查看 */
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
    padding-left:3%;
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
