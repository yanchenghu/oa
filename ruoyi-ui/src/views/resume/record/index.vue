<template>
  <div class="app-container">
    <div style="margin-bottom: 30px;display: flex;justify-content: space-between;">
      <div style="width: 25%;">
        <p><b>录入解析简历</b></p>
      </div>
      <div style="display: flex;flex-wrap: wrap; width: 60%;justify-content: flex-end;">
        <div>
          <item>电话 </item>&nbsp;<input type="text" class="input" v-model="phone">
        </div>
        <div>
          <item>姓名 </item>&nbsp;<input type="text" class="input" v-model="name">
        </div>
        <button class="but" @click="open = true">查询</button>
      </div>
    </div>
    <div style="margin-bottom:40px;">
         <input type="file" ref="files" >

         <button class="but" @click="jiexii">解析</button>
    </div>
    <div>
      <p><h4><b>姓名/电话</b></h4></p>
      <div>

          <el-form label-width="68px" :inline="true">
              <el-form-item label="性别" prop="sex">
                <el-input
                  v-model="queryParams.sex"
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <el-form-item label="出生年月" prop="birthday">
                <el-input
                  v-model="queryParams.birthday"
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <el-form-item label="毕业学院" prop="school">
                <el-input
                  v-model="queryParams.school"
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <el-form-item label="学历" prop="education">
                <el-input
                  v-model="queryParams.education"
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <el-form-item label="期望薪资" prop="pay">
                <el-input
                  v-model="queryParams.pay"
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <el-form-item label="技术方向" prop="direction">
                <el-input
                  v-model="queryParams.direction"
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <br/>
              <el-form-item label="工作经验" prop="workjingyan">
                <el-input
                style="width: 300px;"
                 type="textarea"
                  v-model="queryParams.workjingyan"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <br>
              <el-form-item label="工作经历" prop="workjingli">
                <el-input
                style="width: 300px;"
                 type="textarea"
                  v-model="queryParams.workjingli"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <br/>
              <el-form-item label="项目经历" prop="xiangmu">
                <el-input
                style="width: 500px;"
                 type="textarea"
                  v-model="queryParams.xiangmu"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>
              <br>
              <el-form-item>
                  <el-button  size="medium " @click="handleQuery" class="but">取消</el-button>
                  <el-button size="medium " @click="resetQuery" class="but">保存</el-button>
                </el-form-item>
              </el-form>
         </el-form>
      </div>

    </div>
    <el-dialog :visible.sync="open" style="width: 90%;">
        <div v-if="sous" style="height: 400px;">
          <h3><p><b>简历查询-查询不到结果</b></p></h3>
          <div style="margin-top:200px" align="center">未查询到人员信息</div>
        </div>
        <div v-else >
          <h4><p><b>简历查询-可查询到结果</b></p></h4>
          <el-table
              :data="tableData"
              border
              style="width: 100%">
              <el-table-column
                prop="date"
                label="姓名电话"
                width="150">
              </el-table-column>
              <el-table-column
                prop="name"
                label="出生日期"
                width="140">
              </el-table-column>
              <el-table-column
                prop="province"
                label="技术方向"
                width="160">
              </el-table-column>
              <el-table-column
                prop="city"
                label="录入/更新简历时间"
                width="150">
              </el-table-column>
              <el-table-column
                prop="address"
                label="占有人"
                width="120">
              </el-table-column>
              <el-table-column

                label="详情"
                width="100">
                <template slot-scope="scope">
                 <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                </template>
              </el-table-column>
            </el-table>
        </div>
    </el-dialog>



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
    <el-dialog :title="title" :visible.sync="ope" width="1000px" append-to-body>
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
import {jiexi, listRecord, getRecord, delRecord, addRecord, updateRecord, exportRecord } from "@/api/resume/record/customerinfo";

export default {
  name: "Record",
  data() {
    return {
      text:"",
      // 查询电话
      phone:null,
      // 查询姓名
      name:null,
      // 是否查询到
      sous:false,
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
        sex: null,
        birthday: null,
        school: null,
        education: null,
        pay: null,
        direction: null,
        workjinli: null,
        workjingyan: null,
        xingmu: null,
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

    jiexii(){
      this.text=this.$refs.files.value
      let formData=new FormData();
      formData.append('upfile',this.text);
      jiexi(formData).then()
    },
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

<style>
  .input{
    width: 150px;
    height: 40px;
    margin-right: 10px;
  }
  .but{
    width: 90px;
    height: 40px;
    background: none;
    border: 1px solid grey;
  }
</style>
