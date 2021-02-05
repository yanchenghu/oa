<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item prop="months">
        <el-date-picker
          type="month"
          style="width:160px"
          format="yyyy 年 MM 月 "
          value-format="yyyy-MM-dd"
          size="small"
          v-model="queryParams.insertTime"
          placeholder="选择月份"
          :picker-options="pickerOptions3"
          @change="handleQuery"
          >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"  size="small" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>
    <div style=" float: right;margin-bottom: 10px;">
      <el-button  type="primary" v-hasPermi="['finance:otherexpenses:add']" size="small" @click="handleAdd">新增支出</el-button>
      <el-button type="primary" plain v-hasPermi="['finance:otherexpenses:export']"  size="small" @click="handleExport" >导出</el-button>
    </div>
    <el-table v-loading="loading" :data="otherexpensesList" @selection-change="handleSelectionChange">
      <el-table-column label="序号"  type="index" width="55"/>
      <el-table-column label="房租费"  prop="rent" />
      <el-table-column label="水电费"  prop="hydropower" />
      <el-table-column label="物业费"  prop="property" />
      <el-table-column label="材料费"  prop="materiaCost" />
      <el-table-column label="税费"  prop="taxation" />
      <el-table-column label="其他"  prop="otherCost" />
      <el-table-column label="备注"  prop="remarks" />
      <el-table-column label="添加时间">
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.insertTime,"{y}-{m}-{d}")}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作"  class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['finance:otherexpenses:edit']"
          >修改</el-button>
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
    <!-- 添加或修改每月公司其他住处费用对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" style="width: 300px;">
        <el-form-item label="房租费" prop="rent">
          <el-input v-model="form.rent" placeholder="请输入房租费" />
        </el-form-item>
        <el-form-item label="水电费" prop="hydropower">
          <el-input v-model="form.hydropower" placeholder="请输入水电费" />
        </el-form-item>
        <el-form-item label="物业费" prop="property">
          <el-input v-model="form.property" placeholder="请输入物业费" />
        </el-form-item>
        <el-form-item label="材料费" prop="materiaCost">
          <el-input v-model="form.materiaCost" placeholder="请输入材料费" />
        </el-form-item>
        <el-form-item label="税费" prop="taxation">
          <el-input v-model="form.taxation" placeholder="请输入税费" />
        </el-form-item>
        <el-form-item label="其他" prop="otherCost">
          <el-input v-model="form.otherCost" placeholder="请输入其他" />
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input type="textarea" v-model="form.remarks" placeholder="请输入备注" />
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
import { listOtherexpenses, getOtherexpenses, delOtherexpenses, addOtherexpenses, updateOtherexpenses, exportOtherexpenses } from "@/api/finance/otherexpenses";

export default {
  name: "Otherexpenses",
  data() {
    var price = (rule, value, callback) => {
          // const reg = /^\d+.?\d{0,2}$/
          const reg = /^[+-]?(0|([1-9]\d*))(\.\d{0,2})?$/g
          if (value===""){
            callback(new Error("不能为空"))
          } else {
            if (reg.test(value)){
              callback()
            } else {
              callback(new Error('请输入数字且保留两位小数'))
            }
          }
        }
    return {
      pickerOptions3:{
        disabledDate:(time) => {
            return time.getTime() >  Date.now()
        }
      },
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
      // 每月公司其他住处费用表格数据
      otherexpensesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        insertTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        rent:[{
            required: true,
            validator: price,
            trigger: "blur",
          },],
        hydropower:[{
            required: true,
            validator: price,
            trigger: ["blur","change"]
          },],
        property:[{
            required: true,
            validator: price,
            trigger: ["blur","change"]
          },],
        materiaCost:[{
            required: true,
            validator: price,
            trigger: "blur"
          },],
        taxation:[{
            required: true,
            validator: price,
            trigger: ["blur","change"]
          },],
        otherCost:[{
          required: true,
          validator: price,
          trigger: ["blur","change"]
        },],
        remarks:[{
            required: true,
            message: "备注不能为空",
            trigger: ["blur","change"]
          },],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询每月公司其他住处费用列表 */
    getList() {
      this.loading = true;
      listOtherexpenses(this.queryParams).then(response => {
        this.otherexpensesList = response.rows;
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
        rent: null,
        hydropower: null,
        property: null,
        materiaCost: null,
        taxation: null,
        otherCost: null,
        remarks: null,
        insertTime: null,
        updateTime: null,
        isDelflag: null
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
      this.title = "添加每月公司其他出处费用";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOtherexpenses(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改每月公司其他出处费用";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOtherexpenses(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOtherexpenses(this.form).then(response => {
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
      this.$confirm('是否确认删除每月公司其他住处费用编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delOtherexpenses(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有每月公司其他住处费用数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportOtherexpenses(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
