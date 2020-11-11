<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入公司名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">查询</el-button>
      </el-form-item>
    </el-form>


    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item  prop="companySituation">
        <el-select v-model="queryParams.companySituation"   placeholder="请选择公司性质" clearable size="small">
          <el-option
            v-for="dict in companySituationOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item  prop="isFollowSubmit">
        <el-select v-model="queryParams.isFollowSubmit" placeholder="请选择线索状态" clearable size="small">
          <el-option
            v-for="dict in isFollowSubmitOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item  prop="entryDays">
        <el-select v-model="queryParams.entryDays" placeholder="请选择距进入公海天数"  clearable size="small">
          <el-option
            v-for="dict in entryDaysOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
    </el-form>


    <el-row :gutter="10" class="mb8">
    <el-col :span="1.5">
      <el-button
        type="cyan"
        icon="el-icon-search"
        size="mini"
        @click="handleAdd"
        style="position: absolute;right: 0;margin-right: 50px"
        v-hasPermi="['resume:yxbemand:add']"
      >新建客户线索</el-button>
    </el-col>
    </el-row>


      <el-row :gutter="10" class="mb8">
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

<!--    展示-->
    <el-table v-loading="loading" :data="yxdemandList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
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
            @click="followUp(scope.row)"
            v-hasPermi="['customer:yxdemand:follow']"
          >跟进</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-search"
            @click="see(scope.row)"
            v-hasPermi="['customer:yxdemand:see']"
          >查看</el-button>
        </template>
      </el-table-column>
    </el-table>



  <!-- 跟进 -->
    <el-table v-loading="loading" :data="yxdemandLists">
      <el-table-column type="selection" width="55" align="center" />
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
            @click="followUp(scope.row)"
            v-hasPermi="['customer:yxdemand:follow']"
          >跟进</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-search"
            @click="see(scope.row)"
            v-hasPermi="['customer:yxdemand:see']"
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



  </div>
</template>

<script>
import { listYxdemand,  addYxdemand,see   } from "@/api/customer/yxdemand";

export default {
  name: "Yxdemand",
  data() {
    return {
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

      }
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
    /** 查询营销录入公司列表 */
    getList() {
      this.loading = true;
      listYxdemand(this.queryParams).then(response => {
        this.yxdemandList = response.rows;
        this.total = response.total;
        this.loading = false;
      });

      listsYxdemand(this.queryParams).then(response => {
        this.yxdemandLists = response.rows;
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



    /** 跟进按钮 */
    followUp(){

    },

    /** 查看按钮  */
    see(){
      this.reset();
      this.openn = true;
      this.title = "联系人信息";
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.entryId != null) {
            see(this.form).then(response => {
              this.msgSuccess("查看成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.msgSuccess("查看失败");
          }
        }
      });
    }


  }
};
</script>
