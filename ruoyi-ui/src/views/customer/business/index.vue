  <template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item style="float: right;">
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">查询</el-button>
      </el-form-item>
      <el-form-item label="" prop="companyName" style="float: right;">
        <el-input
          @input="change"
          v-model="queryParams.companyName"
          placeholder="请输入公司名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

    </el-form>
    <div style="clear: both;"></div>



    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
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
      <el-form-item  prop="isFollowSubmit">
        <el-select v-model="queryParams.isFollowSubmit" placeholder="请选择线索状态" clearable size="small" @change="change">
          <el-option
            v-for="dict in isFollowSubmitOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item  prop="entryDays">
        <el-select v-model="queryParams.entryDays" placeholder="请选择距进入公海天数"  clearable size="small" @change="change">
          <el-option
            v-for="dict in entryDaysOptions"
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
          v-hasPermi="['resume:intention:add']"
        >新建意向客户</el-button>
    </el-form>
   <!-- <el-row :gutter="10" class="mb8">
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

<!--    展示-->
    <el-table v-loading="loading" :data="yxdemandList" >
      <el-table-column label="序列" align="center" prop="entryId" />
      <el-table-column label="公司名称" align="center" prop="companyName" />
      <el-table-column label="联系人/职位" align="center" prop="contactPosition" />
      <el-table-column
        label="公司性质"
        align="center"
        prop="companySituation"
        :formatter="companySituationFormat"
        width="100"
      />
      <el-table-column label="联系方式" align="center" prop="interviewContact" />
      <el-table-column label="录入人" align="center" prop="entryPeople" />
      <el-table-column
        label="线索状态"
        align="center"
        prop="isFollowSubmit"
        :formatter="isFollowSubmitFormat"
        width="100"
      />
      <el-table-column label="最近一次联系情况" align="center" prop="contactInformation" />
      <el-table-column label="更新时间" align="center" prop="updateDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="距进入公海天数"
        align="center"
        prop="entryDays"
        :formatter="entryDaysFormat"
        width="100"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="followUp(scope.row.entryId)"
            v-hasPermi="['customer:intention:follow']"
          >跟进</el-button>
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


    <!-- 录入线索 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司名称" prop="companyName">
          <el-input v-model="form.companyName"  placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="联系人姓名" prop="contactPeople">
          <el-input v-model="form.contactPeople"  placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="联系人职位" prop="contactPosition">
          <el-input v-model="form.contactPosition"  placeholder="请输入联系人/职位" />
        </el-form-item>
        <el-form-item label="联系人电话" prop="interviewContact">
          <el-input v-model="form.interviewContact"  placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="公司性质"  prop="companySituation">
          <el-select v-model="form.companySituation"   placeholder="请选择公司性质" >
            <el-option
              v-for="dict in companySituationOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="线索状态"  prop="isFollowSubmit">
          <el-select v-model="form.isFollowSubmit" placeholder="请选择线索状态" >
            <el-option
              v-for="dict in isFollowSubmitOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="最近一次联系情况" prop="contact">
          <el-input v-model="form.contactInformation" placeholder="请输入" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

     <el-drawer
       title="信息"
       :visible.sync="drawer"
       :with-header="false"
       size="50%"
       @close="dra"
       >
       <div style="margin:0 3% 0 3%;border-left:1px solid #E6E6E6;">
       <div style="display: flex;flex-wrap: wrap;justify-content: space-between; padding:20px 3% 30px 2%; border-bottom: 1px solid #E6E6E6;">
         <div>
           <b>
             {{yxdemandone.companyName}}
           </b>
         </div>
          <el-form :inline="true" :model="yxdemandone" class="demo-form-inline">
            <el-form-item label="公司性质">
              <el-select v-model="yxdemandone.companySituation"     size="small">
                <el-option
                  v-for="dict in companySituationOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="parseInt(dict.dictValue)"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="线索状态">
              <el-select v-model="yxdemandone.isFollowSubmit"   size="small">
                <el-option
                  v-for="dict in isFollowSubmitOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="parseInt(dict.dictValue)"
                />
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button :disabled="yxdemandone.isFollowSubmit!==4" type="primary" @click="onSubmit">移交</el-button>
            </el-form-item>
          </el-form>

        </div>
        <div style="padding:20px 3% 30px 2%; display: flex;">


           <div style="width: 50%;">
              <div class="msg">
                <b>联系人信息</b>
              </div>

              <div>
                <el-form label-position="left" label-width="80px" :model="yxdemandone">
                  <el-form-item label="姓名" >
                    <el-input v-model="yxdemandone.contactPeople" @input="see"></el-input>
                  </el-form-item>
                  <el-form-item label="职位">
                    <el-input v-model="yxdemandone.contactPosition" @input="see"></el-input>
                  </el-form-item>
                  <el-form-item label="电话">
                    <el-input v-model="yxdemandone.contactPhone" @input="see"></el-input>
                  </el-form-item>
                  <el-form-item label="邮箱">
                    <el-input v-model="yxdemandone.mailbox" @input="see"></el-input>
                  </el-form-item>
                  <el-form-item label="微信">
                    <el-input v-model="yxdemandone.wechat" @input="see"></el-input>
                  </el-form-item>
                  <el-form-item label="QQ">
                    <el-input v-model="yxdemandone.qq" @input="see"></el-input>
                  </el-form-item>
                  <div class="msg">
                    <b>外包公司信息</b>
                  </div>
                  <el-form-item label="面试名义公司">
                    <el-input v-model="yxdemandone.interviewCompany" @input="see"></el-input>
                  </el-form-item>
                  <el-form-item label="面试官">
                    <el-input v-model="yxdemandone.interviewer" @input="see"></el-input>
                  </el-form-item>
                  <el-form-item label="面试职位">
                    <el-input v-model="yxdemandone.interviewerPosition" @input="see"></el-input>
                  </el-form-item>
                  <el-form-item label="面试地点">
                    <el-input v-model="yxdemandone.interviewaddress" @input="see"></el-input>
                  </el-form-item>
                  <el-form-item label="最终甲方">
                    <el-input v-model="yxdemandone.finalparty" @input="see"></el-input>
                  </el-form-item>
                </el-form>
              </div>

           </div>
           <!-- 联系记录 -->
           <div style="padding-left:2%;width: 50%;">
             <div class="msg">
               <b>联系记录</b>
             </div>
             <el-input type="textarea" style="width: 100%;" placeholder="添加跟进信息"  v-model="putmsg" @focus="put=true" ></el-input>
             <el-button v-show="put" style="float: right;margin-top: 10px;" type="primary" @click="putmsgbut">发布</el-button>
             <div style="clear: both;margin-top: 10px;"></div>
             <ul>
               <li v-for="msg in putmsgs">
                 <span class="span">{{msg.contactTime}}</span>
                 <span class="span">{{msg.nickName}}</span>
                 <span class="span">{{}}</span>

               </li>
             </ul>



           </div>
          </div>
       </div>
     </el-drawer>

  </div>
</template>

<script>
import { getYxdemand,listYxdemand,addYxdemand,see} from "@/api/customer/business";

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
      //距进入公海天数
      entryDaysOptions : [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        companyName: null,
        contactPosition: null,
        companySituation: null,
        interviewContact: null,
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
      },
      // 发布信息
      putmsg:null,
      putmsgs:[],
      // 发布按钮
      put:false
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
    this.getDicts("yxdemand_entry_days").then(response => {
      this.entryDaysOptions = response.data;
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
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.entryId != null) {
            this.msgSuccess("新增失败");
          } else {
            addYxdemand(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },

    // 移交
    onSubmit(){

    },

    // 发布按钮
    putmsgbut(){
      console.log(this.putmsg)
      this.followUp()
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

    /**输入框实时修改按钮  */
    see(){
      see(this.yxdemandone).then()
    }


  }
};
</script>
<style>
  .el-drawer.rtl{
      overflow: auto;
  }
  .msg{
    margin-bottom: 20px;
  }
</style>
