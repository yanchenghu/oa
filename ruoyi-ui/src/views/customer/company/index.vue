<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="客户级别" prop="customerLevel">
        <el-select v-model="queryParams.customerLevel" placeholder="请选择客户级别" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="回款周期" prop="paybackPeriod">
        <el-select v-model="queryParams.paybackPeriod" placeholder="请选择回款周期" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
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
          v-hasPermi="['customer:company:add']"
        >新增</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="companyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序列" align="center" prop="corpCode" />
      <el-table-column label="公司名称" align="center" prop="corpName" />
      <el-table-column label="客户级别" align="center" prop="customerLevel" />
      <el-table-column label="回款周期" align="center" prop="paybackPeriod" />
      <el-table-column label="合作时间" align="center" prop="cooperationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.cooperationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="录入人" align="center" prop="enteredBy" />
      <el-table-column label="转化人" align="center" prop="transformingPeople" />
      <el-table-column label="更多" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">

          <el-button
            size="mini"
            type="text"
            icon="el-icon-more"
            @click="more(scope.row)"
            v-hasPermi="['customer:company:more']"
          ></el-button>
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

    <!-- 添加或修改合作公司对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司名称" prop="corpName">
          <el-input v-model="form.corpName" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="客户级别" prop="customerLevel">
          <el-select v-model="form.customerLevel" placeholder="请选择客户级别">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="回款周期" prop="paybackPeriod">
          <el-select v-model="form.paybackPeriod" placeholder="请选择回款周期">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="合作时间" prop="cooperationTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.cooperationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择合作时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="录入人" prop="enteredBy">
          <el-input v-model="form.enteredBy" placeholder="请输入录入人" />
        </el-form-item>
        <el-form-item label="转化人" prop="transformingPeople">
          <el-input v-model="form.transformingPeople" placeholder="请输入转化人" />
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
import { listCompany, getCompany, delCompany, addCompany, updateCompany, exportCompany } from "@/api/customer/company";

export default {
  name: "Company",
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
      // 合作公司表格数据
      companyList: [],
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
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
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
    // 表单重置
    reset() {
      this.form = {
        corpCode: null,
        corpName: null,
        customerLevel: null,
        paybackPeriod: null,
        cooperationTime: null,
        enteredBy: null,
        transformingPeople: null
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
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const corpCode = row.corpCode || this.ids
      getCompany(corpCode).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改合作公司";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.corpCode != null) {
            updateCompany(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompany(this.form).then(response => {
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
      const corpCodes = row.corpCode || this.ids;
      this.$confirm('是否确认删除合作公司编号为"' + corpCodes + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCompany(corpCodes);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有合作公司数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCompany(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
