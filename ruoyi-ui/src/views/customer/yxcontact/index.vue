<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公司Id" prop="entryId">
        <el-input
          v-model="queryParams.entryId"
          placeholder="请输入公司Id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="跟进人姓名" prop="contactName">
        <el-input
          v-model="queryParams.contactName"
          placeholder="请输入跟进人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="添加时间" prop="contactTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.contactTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择添加时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="邮箱" prop="mailbox">
        <el-input
          v-model="queryParams.mailbox"
          placeholder="请输入邮箱"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信" prop="wechat">
        <el-input
          v-model="queryParams.wechat"
          placeholder="请输入微信"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="QQ" prop="qq">
        <el-input
          v-model="queryParams.qq"
          placeholder="请输入QQ"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="添加人" prop="usercode">
        <el-input
          v-model="queryParams.usercode"
          placeholder="请输入添加人"
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
          v-hasPermi="['customer:yxcontact:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['customer:yxcontact:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['customer:yxcontact:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['customer:yxcontact:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="yxcontactList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="添加人" align="center" prop="contactId" />
      <el-table-column label="公司Id" align="center" prop="entryId" />
      <el-table-column label="跟进详情" align="center" prop="contactDetail" />
      <el-table-column label="跟进人姓名" align="center" prop="contactName" />
      <el-table-column label="添加时间" align="center" prop="contactTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.contactTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="邮箱" align="center" prop="mailbox" />
      <el-table-column label="微信" align="center" prop="wechat" />
      <el-table-column label="QQ" align="center" prop="qq" />
      <el-table-column label="添加人" align="center" prop="usercode" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['customer:yxcontact:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['customer:yxcontact:remove']"
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

    <!-- 添加或修改跟进联系内容对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司Id" prop="entryId">
          <el-input v-model="form.entryId" placeholder="请输入公司Id" />
        </el-form-item>
        <el-form-item label="跟进详情" prop="contactDetail">
          <el-input v-model="form.contactDetail" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="跟进人姓名" prop="contactName">
          <el-input v-model="form.contactName" placeholder="请输入跟进人姓名" />
        </el-form-item>
        <el-form-item label="添加时间" prop="contactTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.contactTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择添加时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="邮箱" prop="mailbox">
          <el-input v-model="form.mailbox" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="微信" prop="wechat">
          <el-input v-model="form.wechat" placeholder="请输入微信" />
        </el-form-item>
        <el-form-item label="QQ" prop="qq">
          <el-input v-model="form.qq" placeholder="请输入QQ" />
        </el-form-item>
        <el-form-item label="添加人" prop="usercode">
          <el-input v-model="form.usercode" placeholder="请输入添加人" />
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
import { listYxcontact, getYxcontact, delYxcontact, addYxcontact, updateYxcontact, exportYxcontact } from "@/api/customer/yxcontact";

export default {
  name: "Yxcontact",
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
      // 跟进联系内容表格数据
      yxcontactList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        entryId: null,
        contactDetail: null,
        contactName: null,
        contactTime: null,
        mailbox: null,
        wechat: null,
        qq: null,
        usercode: null
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
    /** 查询跟进联系内容列表 */
    getList() {
      this.loading = true;
      listYxcontact(this.queryParams).then(response => {
        this.yxcontactList = response.rows;
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
        contactId: null,
        entryId: null,
        contactDetail: null,
        contactName: null,
        contactTime: null,
        mailbox: null,
        wechat: null,
        qq: null,
        usercode: null
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
      this.ids = selection.map(item => item.contactId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加跟进联系内容";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const contactId = row.contactId || this.ids
      getYxcontact(contactId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改跟进联系内容";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.contactId != null) {
            updateYxcontact(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYxcontact(this.form).then(response => {
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
      const contactIds = row.contactId || this.ids;
      this.$confirm('是否确认删除跟进联系内容编号为"' + contactIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delYxcontact(contactIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有跟进联系内容数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportYxcontact(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
