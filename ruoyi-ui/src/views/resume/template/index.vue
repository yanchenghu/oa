<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="模板名称" prop="templateName">
        <el-input
          v-model="queryParams.templateName"
          placeholder="请输入模板名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['resume:template:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['resume:template:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['resume:template:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['resume:template:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="templateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="模板名称" align="center" prop="templateName" />
      <el-table-column label="模板文件" align="center" prop="templateFile" />
      <el-table-column label="模板图片" align="center" prop="templatePrc" />
      <el-table-column label="模板顺序" align="center" prop="templateOrder" />
      <el-table-column label="模板状态" align="center">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.templateStatus"
            active-value="0"
            inactive-value="1"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="添加时间" align="center" prop="addtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="添加人" align="center" prop="addpeople" />
      <el-table-column label="作废时间" align="center" prop="invalidtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.invalidtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="作废人" align="center" prop="invalidpeople" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-preview"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['resume:template:preview']"
          >预览</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-download"
            @click="handleDelete(scope.row)"
            v-hasPermi="['resume:template:download']"
          >下载</el-button>
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

    <!-- 添加或修改简历模板对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="模板名称" prop="templateName">
          <el-input v-model="form.templateName" placeholder="请输入模板名称" />
        </el-form-item>
        <el-form-item label="模板文件" prop="templateFile">
          <el-input v-model="form.templateFile" placeholder="请输入模板文件" />
        </el-form-item>
        <el-form-item label="模板图片" prop="templatePrc">
          <el-input v-model="form.templatePrc" placeholder="请输入模板图片" />
        </el-form-item>
        <el-form-item label="模板顺序" prop="templateOrder">
          <el-input v-model="form.templateOrder" placeholder="请输入模板顺序" />
        </el-form-item>
          <el-form-item label="模板状态">
            <el-radio-group v-model="form.templateStatus">
              <el-radio
                v-for="dict in statusOptions"
                :key="dict.dictValue"
                :label="dict.dictValue"
              >{{dict.dictLabel}}</el-radio>
            </el-radio-group>
          </el-form-item>


        <el-form-item label="添加时间" prop="addtime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.addtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择添加时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="添加人" prop="addpeople">
          <el-input v-model="form.addpeople" placeholder="请输入添加人" />
        </el-form-item>
        <el-form-item label="作废时间" prop="invalidtime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.invalidtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择作废时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="作废人" prop="invalidpeople">
          <el-input v-model="form.invalidpeople" placeholder="请输入作废人" />
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
import { listTemplate, getTemplate, delTemplate, addTemplate,changeTemplateStatus, updateTemplate, exportTemplate } from "@/api/resume/template/template";

export default {
  name: "Template",
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
      // 状态数据字典
      statusOptions: [],
      // 总条数
      total: 0,
      // 简历模板表格数据
      templateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        templateName: null,
        templateFile: null,
        templatePrc: null,
        templateOrder: null,
        templateStatus: null,
        addtime: null,
        addpeople: null,
        invalidtime: null,
        invalidpeople: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        templateOrder: [
          { required: true, message: "模板顺序不能为空", trigger: "blur" }
        ],
        templateStatus: [
          { required: true, message: "模板状态不能为空", trigger: "blur" }
        ],
        addtime: [
          { required: true, message: "添加时间不能为空", trigger: "blur" }
        ],
        addpeople: [
          { required: true, message: "添加人不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询简历模板列表 */
    getList() {
      this.loading = true;
      listTemplate(this.queryParams).then(response => {
        this.templateList = response.rows;
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
        templateId: null,
        templateName: null,
        templateFile: null,
        templatePrc: null,
        templateOrder: null,
        templateStatus: "0",
        addtime: null,
        addpeople: null,
        invalidtime: null,
        invalidpeople: null
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
      this.ids = selection.map(item => item.templateId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    // 模板状态修改
    handleStatusChange(row) {
      console.log(row.templateStatus)
      let text = row.templateStatus === "0" ? "启用" : "停用";
      this.$confirm('确认要"' + text + '""' + row.templateName + '"模板吗?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return changeTemplateStatus(row.templateId, row.templateStatus);
      }).then(() => {
        this.msgSuccess(text + "成功");
      }).catch(function() {
        row.templateStatus = row.templateStatus === "0" ? "1" : "0";
      });
    },


    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加简历模板";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const templateId = row.templateId || this.ids
      getTemplate(templateId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改简历模板";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.templateId != null) {
            updateTemplate(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTemplate(this.form).then(response => {
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
      const templateIds = row.templateId || this.ids;
      this.$confirm('是否确认删除简历模板编号为"' + templateIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTemplate(templateIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有简历模板数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTemplate(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
