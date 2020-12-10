<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户编号" prop="customerCode">
        <el-input
          v-model="queryParams.customerCode"
          placeholder="请输入客户编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="外派公司编号" prop="corpCode">
        <el-input
          v-model="queryParams.corpCode"
          placeholder="请输入外派公司编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="外派公司名称" prop="corpName">
        <el-input
          v-model="queryParams.corpName"
          placeholder="请输入外派公司名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="外派公司项目编号" prop="demandId">
        <el-input
          v-model="queryParams.demandId"
          placeholder="请输入外派公司项目编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="外派公司项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入外派公司项目名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始日期" prop="startTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束日期" prop="endTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结算周期" prop="settledCycle">
        <el-input
          v-model="queryParams.settledCycle"
          placeholder="请输入结算周期"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工资" prop="salary">
        <el-input
          v-model="queryParams.salary"
          placeholder="请输入工资"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务费用" prop="servicePay">
        <el-input
          v-model="queryParams.servicePay"
          placeholder="请输入服务费用"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出项时间" prop="outofProjecttime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.outofProjecttime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择出项时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="离项原因" prop="quitProreason">
        <el-input
          v-model="queryParams.quitProreason"
          placeholder="请输入离项原因"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="离项备注" prop="quitRemark">
        <el-input
          v-model="queryParams.quitRemark"
          placeholder="请输入离项备注"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作人编号" prop="opercode">
        <el-input
          v-model="queryParams.opercode"
          placeholder="请输入操作人编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作时间" prop="operTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.operTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择操作时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="入职时间" prop="syqstartTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.syqstartTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择入职时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="转正时间" prop="syqEndtime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.syqEndtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择转正时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="社保选项(1、交2、否)" prop="socSecopt">
        <el-input
          v-model="queryParams.socSecopt"
          placeholder="请输入社保选项(1、交2、否)"
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
          v-hasPermi="['perentry:entry:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['perentry:entry:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['perentry:entry:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['perentry:entry:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="entryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="社保选项(1、交2、否)" align="center" prop="id" />
      <el-table-column label="客户编号" align="center" prop="customerCode" />
      <el-table-column label="外派公司编号" align="center" prop="corpCode" />
      <el-table-column label="外派公司名称" align="center" prop="corpName" />
      <el-table-column label="外派公司项目编号" align="center" prop="demandId" />
      <el-table-column label="外派公司项目名称" align="center" prop="projectName" />
      <el-table-column label="开始日期" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束日期" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结算周期" align="center" prop="settledCycle" />
      <el-table-column label="工资" align="center" prop="salary" />
      <el-table-column label="服务费用" align="center" prop="servicePay" />
      <el-table-column label="出项时间" align="center" prop="outofProjecttime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.outofProjecttime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="离项原因" align="center" prop="quitProreason" />
      <el-table-column label="离项备注" align="center" prop="quitRemark" />
      <el-table-column label="操作人编号" align="center" prop="opercode" />
      <el-table-column label="操作时间" align="center" prop="operTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="入职时间" align="center" prop="syqstartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.syqstartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="转正时间" align="center" prop="syqEndtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.syqEndtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="社保选项(1、交2、否)" align="center" prop="socSecopt" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['perentry:entry:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['perentry:entry:remove']"
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

    <!-- 添加或修改入项对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户编号" prop="customerCode">
          <el-input v-model="form.customerCode" placeholder="请输入客户编号" />
        </el-form-item>
        <el-form-item label="外派公司编号" prop="corpCode">
          <el-input v-model="form.corpCode" placeholder="请输入外派公司编号" />
        </el-form-item>
        <el-form-item label="外派公司名称" prop="corpName">
          <el-input v-model="form.corpName" placeholder="请输入外派公司名称" />
        </el-form-item>
        <el-form-item label="外派公司项目编号" prop="demandId">
          <el-input v-model="form.demandId" placeholder="请输入外派公司项目编号" />
        </el-form-item>
        <el-form-item label="外派公司项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入外派公司项目名称" />
        </el-form-item>
        <el-form-item label="开始日期" prop="startTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束日期" prop="endTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结算周期" prop="settledCycle">
          <el-input v-model="form.settledCycle" placeholder="请输入结算周期" />
        </el-form-item>
        <el-form-item label="工资" prop="salary">
          <el-input v-model="form.salary" placeholder="请输入工资" />
        </el-form-item>
        <el-form-item label="服务费用" prop="servicePay">
          <el-input v-model="form.servicePay" placeholder="请输入服务费用" />
        </el-form-item>
        <el-form-item label="出项时间" prop="outofProjecttime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.outofProjecttime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择出项时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="离项原因" prop="quitProreason">
          <el-input v-model="form.quitProreason" placeholder="请输入离项原因" />
        </el-form-item>
        <el-form-item label="离项备注" prop="quitRemark">
          <el-input v-model="form.quitRemark" placeholder="请输入离项备注" />
        </el-form-item>
        <el-form-item label="操作人编号" prop="opercode">
          <el-input v-model="form.opercode" placeholder="请输入操作人编号" />
        </el-form-item>
        <el-form-item label="操作时间" prop="operTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.operTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择操作时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="入职时间" prop="syqstartTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.syqstartTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择入职时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="转正时间" prop="syqEndtime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.syqEndtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择转正时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="社保选项(1、交2、否)" prop="socSecopt">
          <el-input v-model="form.socSecopt" placeholder="请输入社保选项(1、交2、否)" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listEntry, getEntry, delEntry, addEntry, updateEntry, exportEntry } from "@/api/perentry/entry";

export default {
  name: "Entry",
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
      // 入项表格数据
      entryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        customerCode: null,
        corpCode: null,
        corpName: null,
        demandId: null,
        projectName: null,
        startTime: null,
        endTime: null,
        settledCycle: null,
        salary: null,
        servicePay: null,
        outofProjecttime: null,
        quitProreason: null,
        quitRemark: null,
        opercode: null,
        operTime: null,
        syqstartTime: null,
        syqEndtime: null,
        socSecopt: null,
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
    /** 查询入项列表 */
    getList() {
      this.loading = true;
      listEntry(this.queryParams).then(response => {
        this.entryList = response.rows;
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
        customerCode: null,
        corpCode: null,
        corpName: null,
        demandId: null,
        projectName: null,
        startTime: null,
        endTime: null,
        settledCycle: null,
        salary: null,
        servicePay: null,
        outofProjecttime: null,
        quitProreason: null,
        quitRemark: null,
        opercode: null,
        operTime: null,
        syqstartTime: null,
        syqEndtime: null,
        socSecopt: null,
        remark: null
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
      this.title = "添加入项";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getEntry(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改入项";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateEntry(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEntry(this.form).then(response => {
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
      this.$confirm('是否确认删除入项编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delEntry(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有入项数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportEntry(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
