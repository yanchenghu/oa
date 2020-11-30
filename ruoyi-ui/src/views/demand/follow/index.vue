<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户公司编号" prop="corpCode">
        <el-input
          v-model="queryParams.corpCode"
          placeholder="请输入客户公司编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="需求名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入需求名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="技术方向" prop="technologyDirection">
        <el-input
          v-model="queryParams.technologyDirection"
          placeholder="请输入技术方向"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="级别(1、中级2高级)" prop="demandYears">
        <el-input
          v-model="queryParams.demandYears"
          placeholder="请输入级别(1、中级2高级)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="需求级别" prop="demandLevel">
        <el-input
          v-model="queryParams.demandLevel"
          placeholder="请输入需求级别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学历要求" prop="education">
        <el-input
          v-model="queryParams.education"
          placeholder="请输入学历要求"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="需求人数" prop="demandNumber">
        <el-input
          v-model="queryParams.demandNumber"
          placeholder="请输入需求人数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="具体要求" prop="specificrequiRement">
        <el-input
          v-model="queryParams.specificrequiRement"
          placeholder="请输入具体要求"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目地点" prop="projectLocation">
        <el-input
          v-model="queryParams.projectLocation"
          placeholder="请输入项目地点"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工作年限" prop="directWorklife">
        <el-input
          v-model="queryParams.directWorklife"
          placeholder="请输入工作年限"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="目标人数" prop="targetNumber">
        <el-input
          v-model="queryParams.targetNumber"
          placeholder="请输入目标人数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="重要级别" prop="importantLevel">
        <el-input
          v-model="queryParams.importantLevel"
          placeholder="请输入重要级别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最小薪资" prop="minSalary">
        <el-input
          v-model="queryParams.minSalary"
          placeholder="请输入最小薪资"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最大薪资" prop="maxSalary">
        <el-input
          v-model="queryParams.maxSalary"
          placeholder="请输入最大薪资"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="技术要求" prop="technicalRequirement">
        <el-input
          v-model="queryParams.technicalRequirement"
          placeholder="请输入技术要求"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="语言(无       1、日语 2、英语   )" prop="langue">
        <el-input
          v-model="queryParams.langue"
          placeholder="请输入语言(无       1、日语 2、英语   )"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="需求图片" prop="demandPic">
        <el-input
          v-model="queryParams.demandPic"
          placeholder="请输入需求图片"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="面试官" prop="interviewer">
        <el-input
          v-model="queryParams.interviewer"
          placeholder="请输入面试官"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="面试官联系方式" prop="contactPhone">
        <el-input
          v-model="queryParams.contactPhone"
          placeholder="请输入面试官联系方式"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="面试地点" prop="specificLocation">
        <el-input
          v-model="queryParams.specificLocation"
          placeholder="请输入面试地点"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="注意事项" prop="attention">
        <el-input
          v-model="queryParams.attention"
          placeholder="请输入注意事项"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="app是否能看到   1:是  2:否" prop="ifLook">
        <el-input
          v-model="queryParams.ifLook"
          placeholder="请输入app是否能看到   1:是  2:否"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学信网是否可查" prop="chsiFlag">
        <el-input
          v-model="queryParams.chsiFlag"
          placeholder="请输入学信网是否可查"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合作性质" prop="coopnature">
        <el-input
          v-model="queryParams.coopnature"
          placeholder="请输入合作性质"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态(0:未关闭；1：需求关闭；)" prop="state">
        <el-input
          v-model="queryParams.state"
          placeholder="请输入状态(0:未关闭；1：需求关闭；)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关闭时间" prop="closeDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.closeDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择关闭时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="模板Id" prop="tempId">
        <el-input
          v-model="queryParams.tempId"
          placeholder="请输入模板Id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="模板名称" prop="tempName">
        <el-input
          v-model="queryParams.tempName"
          placeholder="请输入模板名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="录入时间" prop="addTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.addTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择录入时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="录入人ID" prop="operationuser">
        <el-input
          v-model="queryParams.operationuser"
          placeholder="请输入录入人ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="录入人姓名" prop="operUsername">
        <el-input
          v-model="queryParams.operUsername"
          placeholder="请输入录入人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['demand:follow:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['demand:follow:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['demand:follow:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['demand:follow:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="followList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="需求ID" align="center" prop="id" />
      <el-table-column label="客户公司编号" align="center" prop="corpCode" />
      <el-table-column label="需求名称" align="center" prop="projectName" />
      <el-table-column label="技术方向" align="center" prop="technologyDirection" />
      <el-table-column label="级别(1、中级2高级)" align="center" prop="demandYears" />
      <el-table-column label="需求级别" align="center" prop="demandLevel" />
      <el-table-column label="学历要求" align="center" prop="education" />
      <el-table-column label="需求人数" align="center" prop="demandNumber" />
      <el-table-column label="具体要求" align="center" prop="specificrequiRement" />
      <el-table-column label="项目地点" align="center" prop="projectLocation" />
      <el-table-column label="详细地址" align="center" prop="address" />
      <el-table-column label="工作年限" align="center" prop="directWorklife" />
      <el-table-column label="目标人数" align="center" prop="targetNumber" />
      <el-table-column label="重要级别" align="center" prop="importantLevel" />
      <el-table-column label="最小薪资" align="center" prop="minSalary" />
      <el-table-column label="最大薪资" align="center" prop="maxSalary" />
      <el-table-column label="技术要求" align="center" prop="technicalRequirement" />
      <el-table-column label="语言(无       1、日语 2、英语   )" align="center" prop="langue" />
      <el-table-column label="需求图片" align="center" prop="demandPic" />
      <el-table-column label="面试官" align="center" prop="interviewer" />
      <el-table-column label="面试官联系方式" align="center" prop="contactPhone" />
      <el-table-column label="面试地点" align="center" prop="specificLocation" />
      <el-table-column label="注意事项" align="center" prop="attention" />
      <el-table-column label="app是否能看到   1:是  2:否" align="center" prop="ifLook" />
      <el-table-column label="学信网是否可查" align="center" prop="chsiFlag" />
      <el-table-column label="合作性质" align="center" prop="coopnature" />
      <el-table-column label="状态(0:未关闭；1：需求关闭；)" align="center" prop="state" />
      <el-table-column label="关闭时间" align="center" prop="closeDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.closeDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="模板Id" align="center" prop="tempId" />
      <el-table-column label="模板名称" align="center" prop="tempName" />
      <el-table-column label="录入时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="录入人ID" align="center" prop="operationuser" />
      <el-table-column label="录入人姓名" align="center" prop="operUsername" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['demand:follow:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['demand:follow:remove']"
          >删除</el-button>
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

    <!-- 添加或修改需求对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户公司编号" prop="corpCode">
          <el-input v-model="form.corpCode" placeholder="请输入客户公司编号" />
        </el-form-item>
        <el-form-item label="需求名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入需求名称" />
        </el-form-item>
        <el-form-item label="技术方向" prop="technologyDirection">
          <el-input v-model="form.technologyDirection" placeholder="请输入技术方向" />
        </el-form-item>
        <el-form-item label="级别(1、中级2高级)" prop="demandYears">
          <el-input v-model="form.demandYears" placeholder="请输入级别(1、中级2高级)" />
        </el-form-item>
        <el-form-item label="需求级别" prop="demandLevel">
          <el-input v-model="form.demandLevel" placeholder="请输入需求级别" />
        </el-form-item>
        <el-form-item label="学历要求" prop="education">
          <el-input v-model="form.education" placeholder="请输入学历要求" />
        </el-form-item>
        <el-form-item label="需求人数" prop="demandNumber">
          <el-input v-model="form.demandNumber" placeholder="请输入需求人数" />
        </el-form-item>
        <el-form-item label="具体要求" prop="specificrequiRement">
          <el-input v-model="form.specificrequiRement" placeholder="请输入具体要求" />
        </el-form-item>
        <el-form-item label="项目地点" prop="projectLocation">
          <el-input v-model="form.projectLocation" placeholder="请输入项目地点" />
        </el-form-item>
        <el-form-item label="详细地址" prop="address">
          <el-input v-model="form.address" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="工作年限" prop="directWorklife">
          <el-input v-model="form.directWorklife" placeholder="请输入工作年限" />
        </el-form-item>
        <el-form-item label="目标人数" prop="targetNumber">
          <el-input v-model="form.targetNumber" placeholder="请输入目标人数" />
        </el-form-item>
        <el-form-item label="重要级别" prop="importantLevel">
          <el-input v-model="form.importantLevel" placeholder="请输入重要级别" />
        </el-form-item>
        <el-form-item label="最小薪资" prop="minSalary">
          <el-input v-model="form.minSalary" placeholder="请输入最小薪资" />
        </el-form-item>
        <el-form-item label="最大薪资" prop="maxSalary">
          <el-input v-model="form.maxSalary" placeholder="请输入最大薪资" />
        </el-form-item>
        <el-form-item label="技术要求" prop="technicalRequirement">
          <el-input v-model="form.technicalRequirement" placeholder="请输入技术要求" />
        </el-form-item>
        <el-form-item label="语言(无       1、日语 2、英语   )" prop="langue">
          <el-input v-model="form.langue" placeholder="请输入语言(无       1、日语 2、英语   )" />
        </el-form-item>
        <el-form-item label="需求图片" prop="demandPic">
          <el-input v-model="form.demandPic" placeholder="请输入需求图片" />
        </el-form-item>
        <el-form-item label="面试官" prop="interviewer">
          <el-input v-model="form.interviewer" placeholder="请输入面试官" />
        </el-form-item>
        <el-form-item label="面试官联系方式" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入面试官联系方式" />
        </el-form-item>
        <el-form-item label="面试地点" prop="specificLocation">
          <el-input v-model="form.specificLocation" placeholder="请输入面试地点" />
        </el-form-item>
        <el-form-item label="注意事项" prop="attention">
          <el-input v-model="form.attention" placeholder="请输入注意事项" />
        </el-form-item>
        <el-form-item label="app是否能看到   1:是  2:否" prop="ifLook">
          <el-input v-model="form.ifLook" placeholder="请输入app是否能看到   1:是  2:否" />
        </el-form-item>
        <el-form-item label="学信网是否可查" prop="chsiFlag">
          <el-input v-model="form.chsiFlag" placeholder="请输入学信网是否可查" />
        </el-form-item>
        <el-form-item label="合作性质" prop="coopnature">
          <el-input v-model="form.coopnature" placeholder="请输入合作性质" />
        </el-form-item>
        <el-form-item label="状态(0:未关闭；1：需求关闭；)" prop="state">
          <el-input v-model="form.state" placeholder="请输入状态(0:未关闭；1：需求关闭；)" />
        </el-form-item>
        <el-form-item label="关闭时间" prop="closeDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.closeDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择关闭时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="模板Id" prop="tempId">
          <el-input v-model="form.tempId" placeholder="请输入模板Id" />
        </el-form-item>
        <el-form-item label="模板名称" prop="tempName">
          <el-input v-model="form.tempName" placeholder="请输入模板名称" />
        </el-form-item>
        <el-form-item label="录入时间" prop="addTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.addTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择录入时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="录入人ID" prop="operationuser">
          <el-input v-model="form.operationuser" placeholder="请输入录入人ID" />
        </el-form-item>
        <el-form-item label="录入人姓名" prop="operUsername">
          <el-input v-model="form.operUsername" placeholder="请输入录入人姓名" />
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
import { listFollow, getFollow, delFollow, addFollow, updateFollow, exportFollow } from "@/api/demand/follow";

export default {
  name: "Follow",
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
      // 需求表格数据
      followList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        corpCode: null,
        projectName: null,
        technologyDirection: null,
        demandYears: null,
        demandLevel: null,
        education: null,
        demandNumber: null,
        specificrequiRement: null,
        projectLocation: null,
        address: null,
        directWorklife: null,
        targetNumber: null,
        importantLevel: null,
        minSalary: null,
        maxSalary: null,
        technicalRequirement: null,
        langue: null,
        demandPic: null,
        interviewer: null,
        contactPhone: null,
        specificLocation: null,
        attention: null,
        ifLook: null,
        chsiFlag: null,
        coopnature: null,
        state: null,
        closeDate: null,
        tempId: null,
        tempName: null,
        addTime: null,
        operationuser: null,
        operUsername: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        corpCode: [
          { required: true, message: "客户公司编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询需求列表 */
    getList() {
      this.loading = true;
      listFollow(this.queryParams).then(response => {
        this.followList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        corpCode: null,
        projectName: null,
        technologyDirection: null,
        demandYears: null,
        demandLevel: null,
        education: null,
        demandNumber: null,
        specificrequiRement: null,
        projectLocation: null,
        address: null,
        directWorklife: null,
        targetNumber: null,
        importantLevel: null,
        minSalary: null,
        maxSalary: null,
        technicalRequirement: null,
        langue: null,
        demandPic: null,
        interviewer: null,
        contactPhone: null,
        specificLocation: null,
        attention: null,
        ifLook: null,
        chsiFlag: null,
        coopnature: null,
        state: null,
        closeDate: null,
        tempId: null,
        tempName: null,
        addTime: null,
        operationuser: null,
        operUsername: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加需求";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFollow(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改需求";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFollow(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFollow(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除需求编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delFollow(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有需求数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportFollow(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
