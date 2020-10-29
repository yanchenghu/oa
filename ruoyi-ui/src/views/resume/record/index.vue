<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="简历性别" prop="customerSex">
        <el-select v-model="queryParams.customerSex" placeholder="请选择简历性别" clearable size="small">
          <el-option
            v-for="dict in customerSexOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="联系电话" prop="customerTel">
        <el-input
          v-model="queryParams.customerTel"
          placeholder="请输入联系电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="毕业院校" prop="customerUniversity">
        <el-input
          v-model="queryParams.customerUniversity"
          placeholder="请输入毕业院校"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专业" prop="customerSpecialities">
        <el-input
          v-model="queryParams.customerSpecialities"
          placeholder="请输入专业"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工作年限" prop="workYear">
        <el-input
          v-model="queryParams.workYear"
          placeholder="请输入工作年限"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="添加时间" prop="addTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.addTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择添加时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="期望薪资" prop="expectationSalary">
        <el-input
          v-model="queryParams.expectationSalary"
          placeholder="请输入期望薪资"
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
          v-hasPermi="['resume:record:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['resume:record:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['resume:record:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['resume:record:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="简历姓名" align="center" prop="customerName" />
      <el-table-column label="联系电话" align="center" prop="customerTel" />
      <el-table-column label="出生日期" align="center" prop="customerBirth" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.customerBirth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="专业" align="center" prop="customerSpecialities" />
      <el-table-column label="入职时间" align="center" prop="entryTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.entryTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工作年限" align="center" prop="workYear" />
      <el-table-column label="简历地址" align="center" prop="resumePath" />
      <el-table-column label="与梓钦关系" align="center" prop="relationshipZq" />
      <el-table-column label="添加时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作员姓名" align="center" prop="opertName" />
      <el-table-column label="期望薪资" align="center" prop="expectationSalary" />
      <el-table-column label="意向地区" align="center" prop="intentionArea" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['resume:record:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['resume:record:remove']"
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

    <!-- 添加或修改简历对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="简历姓名" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入简历姓名" />
        </el-form-item>
        <el-form-item label="简历性别" prop="customerSex">
          <el-select v-model="form.customerSex" placeholder="请选择简历性别">
            <el-option
              v-for="dict in customerSexOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系电话" prop="customerTel">
          <el-input v-model="form.customerTel" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="出生日期" prop="customerBirth">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.customerBirth"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择出生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="毕业院校" prop="customerUniversity">
          <el-input v-model="form.customerUniversity" placeholder="请输入毕业院校" />
        </el-form-item>
        <el-form-item label="专业" prop="customerSpecialities">
          <el-input v-model="form.customerSpecialities" placeholder="请输入专业" />
        </el-form-item>
        <el-form-item label="入职时间" prop="entryTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.entryTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择入职时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="工作年限" prop="workYear">
          <el-input v-model="form.workYear" placeholder="请输入工作年限" />
        </el-form-item>
        <el-form-item label="简历来源(1、Boos直聘，2、智联招聘3、58同城)" prop="resumeSource">
          <el-input v-model="form.resumeSource" placeholder="请输入简历来源(1、Boos直聘，2、智联招聘3、58同城)" />
        </el-form-item>
        <el-form-item label="简历ID" prop="resumeId">
          <el-input v-model="form.resumeId" placeholder="请输入简历ID" />
        </el-form-item>
        <el-form-item label="简历地址" prop="resumePath">
          <el-input v-model="form.resumePath" placeholder="请输入简历地址" />
        </el-form-item>
        <el-form-item label="与梓钦关系" prop="relationshipZq">
          <el-input v-model="form.relationshipZq" placeholder="请输入与梓钦关系" />
        </el-form-item>
        <el-form-item label="添加时间" prop="addTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.addTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择添加时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="操作员编号" prop="opertCode">
          <el-input v-model="form.opertCode" placeholder="请输入操作员编号" />
        </el-form-item>
        <el-form-item label="操作员姓名" prop="opertName">
          <el-input v-model="form.opertName" placeholder="请输入操作员姓名" />
        </el-form-item>
        <el-form-item label="期望薪资" prop="expectationSalary">
          <el-input v-model="form.expectationSalary" placeholder="请输入期望薪资" />
        </el-form-item>
        <el-form-item label="技术方向(1、java 2、c++ 3、python)" prop="professionId">
          <el-input v-model="form.professionId" placeholder="请输入技术方向(1、java 2、c++ 3、python)" />
        </el-form-item>
        <el-form-item label="意向地区" prop="intentionArea">
          <el-input v-model="form.intentionArea" placeholder="请输入意向地区" />
        </el-form-item>

        <el-form-item label="入项次数" prop="entryTimes">
          <el-input v-model="form.entryTimes" placeholder="请输入入项次数" />
        </el-form-item>
        <el-form-item label="入项状态">
          <el-radio-group v-model="form.joinStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="抢占人" prop="signPeople">
          <el-input v-model="form.signPeople" placeholder="请输入抢占人" />
        </el-form-item>
        <el-form-item label="学信网是否可查" prop="chsiFlag">
          <el-input v-model="form.chsiFlag" placeholder="请输入学信网是否可查" />
        </el-form-item>
        <el-form-item label="简历方向 : 1:国内 2:对日" prop="resumeDirection">
          <el-input v-model="form.resumeDirection" placeholder="请输入简历方向 : 1:国内 2:对日" />
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
import { listRecord, getRecord, delRecord, addRecord, updateRecord, exportRecord } from "@/api/resume/record/customerinfo";

export default {
  name: "Record",
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
      // 简历表格数据
      recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 简历性别字典
      customerSexOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        customerSex: null,
        customerTel: null,
        customerUniversity: null,
        customerSpecialities: null,
        workYear: null,
        addTime: null,
        expectationSalary: null,
        professionId: null,
        education: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        customerName: [
          { required: true, message: "简历姓名不能为空", trigger: "blur" }
        ],
        customerTel: [
          { required: true, message: "联系电话不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_user_sex").then(response => {
      this.customerSexOptions = response.data;
    });
  },
  methods: {
    /** 查询简历列表 */
    getList() {
      this.loading = true;
      listRecord(this.queryParams).then(response => {
        this.recordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 简历性别字典翻译
    customerSexFormat(row, column) {
      return this.selectDictLabel(this.customerSexOptions, row.customerSex);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        customerCode: null,
        customerName: null,
        customerSex: null,
        customerTel: null,
        customerBirth: null,
        customerUniversity: null,
        customerSpecialities: null,
        entryTime: null,
        workYear: null,
        resumeSource: null,
        resumeId: null,
        resumePath: null,
        relationshipZq: null,
        addTime: null,
        updateTime: null,
        opertCode: null,
        opertName: null,
        expectationSalary: null,
        professionId: null,
        intentionArea: null,
        education: null,
        entryTimes: null,
        joinStatus: 0,
        signPeople: null,
        chsiFlag: null,
        resumeDirection: null
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
      this.ids = selection.map(item => item.customerCode)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加简历";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const customerCode = row.customerCode || this.ids
      getRecord(customerCode).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改简历";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.customerCode != null) {
            updateRecord(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecord(this.form).then(response => {
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
      const customerCodes = row.customerCode || this.ids;
      this.$confirm('是否确认删除简历编号为"' + customerCodes + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delRecord(customerCodes);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有简历数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportRecord(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
