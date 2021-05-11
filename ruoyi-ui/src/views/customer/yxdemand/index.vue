<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch"  label-width="68px" @submit.native.prevent>
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
        <el-select v-model="queryParams.companySituation"   placeholder="请选择公司性质" clearable size="small" @change="getList">
          <el-option
            v-for="dict in companySituationOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item  prop="isFollowSubmit">
        <el-select v-model="queryParams.isFollowSubmit" placeholder="请选择线索状态" clearable size="small" @change="getList">
          <el-option
            v-for="dict in isFollowSubmitOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item  prop="robPeopleId">
        <el-select v-model="queryParams.robPeopleId" placeholder="请选择人员"  size="small" @change="getList">
          <el-option
            v-for="dict in businesslist"
            :key="dict.user_name"
            :label="dict.nick_name"
            :value="dict.user_name"
          />
        </el-select>
      </el-form-item>
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
      <el-table-column label="当前占有人"  prop="robPeople" />
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
      <el-table-column label="操作"  class-name="small-padding fixed-width"  width="60">
        <template slot-scope="scope">
          <el-button
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
          <el-form :inline="true" :model="yxdemandone" disabled class="demo-form-inline">
            <el-form-item label="公司性质">
              <el-select  v-model="yxdemandone.companySituation" size="small" >
                <el-option
                  v-for="dict in companySituationOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="parseInt(dict.dictValue)"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="线索状态">
              <el-select  v-model="yxdemandone.isFollowSubmit"  size="small" >
                <el-option
                    v-for="dict,index in isFollowSubmitOptions"
                    :disabled="index==4"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="parseInt(dict.dictValue)"
                  />
                </el-select>
            </el-form-item>
          </el-form>
        </div>
        <div>
          <el-tabs>
            <el-tab-pane label="联系人信息">
              <div style="display: flex; justify-content: space-between;">
              <el-form ref="formmsg" label-position="right" label-width="80px" :model="yxdemandone" disabled>
                 <b>联系人信息</b>
                 <p></p>
                 <el-form-item label="姓名" prop="contactPeople">
                   <el-input  v-model="yxdemandone.contactPeople"  ></el-input>
                 </el-form-item>
                 <el-form-item label="职位" prop="contactPosition">
                   <el-input  v-model="yxdemandone.contactPosition"  ></el-input>
                 </el-form-item>
                 <el-form-item label="电话" prop="contactPhone">
                   <el-input  v-model="yxdemandone.contactPhone"  ></el-input>
                 </el-form-item>
                 <el-form-item label="邮箱" prop="mailbox">
                   <el-input  v-model="yxdemandone.mailbox"  ></el-input>
                 </el-form-item>
                 <el-form-item label="微信">
                   <el-input  v-model="yxdemandone.wechat"  ></el-input>
                 </el-form-item>
                 <el-form-item label="QQ">
                   <el-input  v-model="yxdemandone.qq"  ></el-input>
                 </el-form-item>
              </el-form>
              <el-form label-position="left" label-width="100px" :model="yxdemandone" disabled>
                <b>外包公司信息</b>
                <p></p>
                 <el-form-item label="面试名义公司">
                   <el-input  v-model.trim="yxdemandone.interviewCompany"></el-input>
                 </el-form-item>
                 <el-form-item label="面试官">
                   <el-input  v-model.trim="yxdemandone.interviewer"  ></el-input>
                 </el-form-item>
                 <el-form-item label="面试职位">
                   <el-input  v-model.trim="yxdemandone.interviewerPosition"  ></el-input>
                 </el-form-item>
                 <el-form-item label="面试地点">
                   <el-input  v-model.trim="yxdemandone.interviewAddress"  ></el-input>
                 </el-form-item>
                 <el-form-item label="最终甲方">
                   <el-input  v-model.trim="yxdemandone.finalParty"  ></el-input>
                 </el-form-item>
              </el-form>
            </div>
            </el-tab-pane>

            <el-tab-pane label="联系记录">
              <div style="padding-left:2%;">
                <div class="msg">
                  <b>联系记录</b>
                </div>
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
  </div>
</template>

<script>
  import { allBusiness,alllist,getYxdemand}from "@/api/customer/business";
  import {debounce} from "@/utils/ruoyi.js"
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
      businesslist:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        companyName: null,
        companySituation: null,
        isFollowSubmit:null,
        robPeopleId:null,
      },
      // 表单参数
      form: {},
      msg:null,
      // 表单校验
      rules: {

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
    this.handle()
    this.getDicts("yxdemand_company_situation").then(response => {
      this.companySituationOptions = response.data;
    });
    this.getDicts("business_isfollow_submin").then(response => {
      this.isFollowSubmitOptions = response.data;
    });
    this.getDicts("yxdemand_entry_days").then(response => {
      this.entryDaysOptions = response.data;
    });
  },
  methods: {
    /** 查询营销录入公司列表 */
    getList() {
      this.loading = true;
      alllist(this.queryParams).then(response => {
        this.yxdemandList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handle() {
      allBusiness().then(response => {
        this.businesslist = response.data
        this.queryParams.robPeopleId = this.businesslist[0].user_name
        this.getList();
      });
    },
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 简历线索字典翻译
    companySituationFormat(row, column) {
      return this.selectDictLabel(this.companySituationOptions, row.companySituation);
    },
    // 线索状态字典翻译
    isFollowSubmitFormat(row, column) {
      return this.selectDictLabel(this.isFollowSubmitOptions, row.isFollowSubmit);
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
