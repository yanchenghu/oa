<template>
  <div class="app-container">
    <div style="display: flex;">
      <!-- 需求表格 -->
      <div class="left">
          <el-input prefix-icon="el-icon-search" size="medium" placeholder="搜索需求">
          </el-input>

          <el-radio-group v-model="fromdata.nood"  style="margin-top: 10px;">
             <div  v-for="nood in tabledata" >
               <el-radio-button  :label="nood.projectName"></el-radio-button>
             </div>
          </el-radio-group>
      </div>

 <!-- 筛选列表 -->

      <div  class="right">
        <el-form style="border-bottom: 1px solid black;padding-bottom: 25px;" ref="form" :model="form" label-width="80px" :inline="true">
          <el-form-item label="技术方向" prop="professionId">
            <el-select v-model="fromdata.professionId" size="small">
              <el-option v-for="dict in professionIdoptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
            </el-select>
          </el-form-item>
          <el-form-item label="工作年限" prop="workYear">
            <el-select v-model='fromdata.workYear' size="small">

              <el-option v-for="dict in professionIdoptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
            </el-select>
          </el-form-item>
          <el-form-item label="最低学历" prop="education">
            <el-select v-model='fromdata.education' size="small">

              <el-option v-for="dict in customerSpecialitiesoptions" :key="dict.dictValue" :label="dict.dictLabel"
                :value="dict.dictValue" />
            </el-select>
          </el-form-item>
          <el-form-item label="期望城市" prop="intentionArea">
            <el-select v-model='fromdata.intentionArea' size="small">

              <el-option v-for="dict in professionIdoptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
            </el-select>
          </el-form-item>
          <el-form-item label="入库时间" prop="puttime">
            <el-select v-model='fromdata.puttime' size="small">

              <el-option v-for="dict in professionIdoptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
            </el-select>
          </el-form-item>
          <el-button  type="primary" @click="next">换一批</el-button>
        </el-form>
        <!-- 数据表格 -->
        <el-table  border :data="msgtaba" tooltip-effect="light" ref="multipleTable">
          <el-table-column type="selection"  width="55" align="center"/>
          <el-table-column prop="customerName" label="姓名电话" width="100" align="center">
          </el-table-column>
          <el-table-column prop="customerBirth" label="出生日期" width="100" align="center">
          </el-table-column>
          <el-table-column prop="professionId" label="技术方向" align="center" width="100">
            <template slot-scope="scope">
              {{professionIdoptions[scope.row.professionId].dictLabel}}
            </template>
          </el-table-column>
          <el-table-column prop="workYear" label="工作年限" align="center" width="80">
            <template slot-scope="scope">
              {{scope.row.workYear}}年
            </template>
          </el-table-column>
          <el-table-column prop="education" label="学历" align="center" width="100">
            <template slot-scope="scope">
              {{customerSpecialitiesoptions[scope.row.education].dictLabel}}
            </template>
          </el-table-column>
          <el-table-column prop="customerUniversity" label="毕业院校" align="center">
          </el-table-column>
          <el-table-column prop="expectationSalary" label="期望薪资" align="center">
          </el-table-column>
          <el-table-column prop="intentionArea" align="center" label="期望城市">
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
               <el-tooltip class="item" effect="dark" content="预览" placement="top-start">
                    <el-button size="mini" type="text"  @click="handleUpdate(scope.row)" v-hasPermi="['resume:record:preview']">
                      <svg-icon icon-class="preview" style="font-size: 16px;"/>
                    </el-button>
                </el-tooltip>

              <el-tooltip class="item" effect="dark" content="抢占" placement="top">
                    <el-button size="mini" type="text"  @click="handleDelete(scope.row)" v-hasPermi="['resume:record:preempt']"><svg-icon icon-class="button" style="font-size: 16px;"/></el-button>
              </el-tooltip>

              <el-tooltip class="item" effect="dark" content="查看" placement="top-end">
                    <el-button size="mini" type="text"  @click="handleDelete(scope.row)" v-hasPermi="['resume:record:eye']"><svg-icon icon-class="eye-open" style="font-size: 16px;"/></el-button>
              </el-tooltip>

            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="fromdata.pageNum"
          :limit.sync="fromdata.pageSize"
          @pagination="onSubmit"
        />

         <!-- <el-button @click="pdf">点击</el-button> -->

         <el-dialog title="预览" :visible.sync="open" width="70%"  >
         <iframe
             src='https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=https://oa.zhuyanhr.com/shzqoa/custTemp/demand20072003485269172/%E7%8E%8B%E8%82%96%E4%B8%9C.doc'
            style="overflow: auto; position: absolute; top: 0; right: 0; bottom: 0; left: 0; width: 100%; height:1000%"
             ></iframe>
        </el-dialog>

      </div>

      </div>
  </div>

</template>



<script>
  import {
    listRecord,
    getRecord,
    delRecord,
    addRecord,
    updateRecord,
    exportRecord,
    listFollow,

  } from "@/api/resume/peopost/peopost";

  export default {
    name: "Record",

    data() {
      return {
        nale:"",
        // 全选
        checkAll:false,
        // 表单内容
        fromdata: {
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
        // 表格数据
        msgtaba: [],

        // 需求数组
        tabledata: [],
        // 表单参数
        form: {},
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
        // 技术方向字典
        professionIdoptions: [],
        // 简历学历字典
        customerSpecialitiesoptions: [],
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
        // 表单校验
        rules: {
          customerName: [{
            required: true,
            message: "简历姓名不能为空",
            trigger: "blur"
          }],
          customerTel: [{
            required: true,
            message: "联系电话不能为空",
            trigger: "blur"
          }],
        },
      };
    },
    created() {
      // 获取学历字典
      this.getDicts("per_customerinfo_education").then(response => {
        this.customerSpecialitiesoptions = response.data;
      });
      // 获取技术方向字典
      this.getDicts("per_customerinfo_professionid").then(response => {
        this.professionIdoptions = response.data;
      });
      this.getDicts("sys_user_sex").then(response => {
        this.customerSexOptions = response.data;
      });
      this.getxuqiulist()
    },
    methods: {
      getxuqiulist(){
        listFollow().then(res=>{
          this.tabledata = res.rows
        })
      },

      // 筛选更多
      onSubmit() {
        this.total = this.msgtaba.length
        // console.log(this.fromdata)
        listRecord(this.fromdata).then(res => {
          this.msgtaba= res.data
          // this.total = res.total;
        })
      },


      // 需求全选列表
     next(){

     },

      // 表格数据全选
      handleCheckAllChange(val){
        this.datalist = val ? this.msgtaba : [],
        console.log(this.datalist)
      },
      // 表格数据多选框
      handleCheckedCitiesChange(val) {
        console.log(val)
        this.checkAll= val.length === this.msgtaba.length

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
      handleSelectionChange(selection){
        this.ids = selection.map(item => item.customerCode)
        this.single = selection.length !== 1
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
        console.log(row)
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
        console.log(this.$refs["form"])
        // this.$refs["form"].perCustomerinfo(valid => {
        //   if (valid) {
        //     if (this.form.customerCode != null) {
        //       updateRecord(this.form).then(response => {
        //         this.msgSuccess("修改成功");
        //         this.open = false;
        //         this.getList();
        //       });
        //     } else {
        //       addRecord(this.form).then(response => {
        //         this.msgSuccess("新增成功");
        //         this.open = false;
        //         this.getList();
        //       });
        //     }
        //   }
        // });
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
  .left{
     padding: 10px;
     margin-right: 10px;
     width: 170px;
     text-align: left;
     height:100%;
     background: #F2F6FC;
  }
  .right{
    width: 80%;
    padding: 10px;
    background: #F2F6FC;
  }
</style>
