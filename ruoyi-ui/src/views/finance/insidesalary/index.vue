<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" >
      <el-form-item prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入姓名"
          clearable
          size="small"
          style="width:140px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="small" @click="handleQuery">查询</el-button>
      </el-form-item>
      <el-form-item prop="sideMonths">
        <el-date-picker
          type="month"
          style="width:160px"
          format="yyyy 年 MM 月 "
          value-format="yyyy-MM-dd"
          size="small"
          v-model="queryParams.sideMonths"
          placeholder="选择月"
          :picker-options="pickerOptions3"
          @change="handleQuery"
          >
        </el-date-picker>
      </el-form-item>
     <el-form-item class="asd">
       <el-button type="primary" plain size="small" @click="seemuban">查看文件模板</el-button>
       <el-button type="primary" v-hasPermi="['finance:insidesalary:export']" plain  size="small" @click="handleExport">导出</el-button>
       <el-button type="primary" v-hasPermi="['finance:insidesalary:add']"  size="small" @click="handleAdd">新增考勤表</el-button>
     </el-form-item>
    </el-form>

    <el-table  v-loading="loading" :data="servicechargeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="姓名"  prop="name" />
      <el-table-column label="月份"  prop="sideMonths" >
      </el-table-column>
      <el-table-column label="基本工资"  prop="realAttence" />
      <el-table-column label="法定出勤"  prop="statutoryAttendance" />
       <el-table-column label="实际出勤"  prop="actualAttendance" />
      <el-table-column label="应发工资"  prop="actualIncome" />
      <el-table-column label="绩效"  prop="taxes" />
      <el-table-column label="全勤"  prop="totalManagement" />
      <el-table-column label="保险"  prop="insurance" />
      <el-table-column label="提成/其他"  prop="commission" />
      <el-table-column label="实发工资"  prop="realSalary" />
      <el-table-column label="操作"  class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="medium"
            type="text"
            @click="handlesele(scope.row)"
          ><svg-icon icon-class="eye-open" class="icons"/>查看</el-button>
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

    <el-dialog :title="title" :visible.sync="wenjian" width="60%">
      <el-button v-if="title=='文件模板预览'" style="margin-top: -20px;" type="primary" @click="downl">下载模板</el-button>
     <iframe
        :src="src"
        style="overflow: auto; position: absolute; top: 110px; right: 0; bottom: 0; left: 0; width: 100%; height:700px; border: none;"
      ></iframe>
    </el-dialog>
    <!-- 添加或修改服务费核算请求书对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-upload action="wqewq" ref="file" class="upload-demo" drag accept=".xlsx,.xls" :limit="1" :on-exceed="handleExceed" :auto-upload="false" :on-change="oplodad" :before-remove="upoplodad">
          <div v-if="wen">
              <i class="el-icon-upload"></i>
            <div><b>点击或拖拽上传文件</b></div>
            <div class="el-upload__text" style="width: 230px;margin: 0 auto;">简历支持.xlsx,.xls格式，大小不超过500kb，拖拽文件可直接上传</div>
          </div>
          <div v-else>
            <i class="el-icon-success" style="color:rgb(0,218,175);
              font-size: 67px;
              margin: 40px 0 16px;
              line-height: 50px;"></i>
            <div><b>上传成功 </b></div>
          </div>
        </el-upload>
        <br>
        <el-form-item prop="months" label="工资日期">
          <el-date-picker
            type="month"
            style="width:160px"
            format="yyyy 年 MM 月 "
            value-format="yyyy-MM-dd"
            size="small"
            v-model="form.months"
            placeholder="选择工资日期"
            :picker-options="pickerOptions3"
            >
          </el-date-picker>
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
import { listInsidesalary, getInsidesalary, delInsidesalary, addInsidesalary, updateInsidesalary, exportInsidesalary } from "@/api/finance/insidesalary";
import {
    corpNames,
  } from "@/api/demand/follow";
export default {
  name: "Servicecharge",
  data() {
    return {
      pickerOptions3:{
        disabledDate:(time) => {
            return time.getTime() >  Date.now()
        }
      },
      wenjian:false,
      src:"",
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      jiafnaggongsi:[],
      yifanggongsi:[],
      // 非单个禁用
      single: true,
      singles:true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      corpnamelists:[],
      // 总条数
      total: 0,
      // 服务费核算请求书表格数据
      servicechargeList: [],
      // 付款状态
      customerleve:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open3:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name:null,
        sideMonths:null,
      },
      dialogImageUrl:"",
      dialogVisible:false,
      open2:false,
      // 表单参数
      form: {},
      wen:true,
      partylist:[],
      // 表单校验
      rules: {
        months: [{
          required: true,
          message: "工资日期不能为空",
          trigger: ["blur","change" ]
        }, ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    seemuban(){
      this.wenjian = true
      this.title = "文件模板预览"
      this.src = "https://www.xdocin.com/xdoc?_func=form&_key=vdm5j3eitvebvmh2qgcuv4idry&_xdoc=http://115.159.35.233:8090/profile/avatar/system/%E5%86%85%E9%83%A8%E5%91%98%E5%B7%A5%E5%BD%95%E5%85%A5%E7%B3%BB%E7%BB%9F%E6%A8%A1%E6%9D%BF.xlxs"
    },
    handlesele(row){
      if(row.excelPath==null){
        this.msgError("历史数据暂无原文件")
      }else{
        let srcs = process.env.VUE_APP_BASE_API+row.excelPath
        this.src=`https://www.xdocin.com/xdoc?_func=form&_key=vdm5j3eitvebvmh2qgcuv4idry&_xdoc=${srcs}`
        this.wenjian = true
        this.title = "预览人员信息"
      }
    },

    handleExceed(){
      this.msgError(`当前限制选择 1 个文件`);
    },
    oplodad(file) {
      this.wen = false
    },
    upoplodad(file) {
      this.reset()
    },
    /** 查询服务费核算请求书列表 */
    getList() {
      this.loading = true;
      listInsidesalary(this.queryParams).then(response => {
        this.servicechargeList = response.rows;
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
      this.wen = true
      this.form = {
        id: null,
        customerName: null,
        customerTel: null,
        customerCode:null,
        overAttence:null,
        realAttence:null,
        projectSalary:null,
        buzhuSalary:null,
        baoxianSalary:null,
        realSalary:null,
        months:null,
      };
      this.resetForm("form");
      if(this.$refs.file!==undefined){
        this.$refs.file.clearFiles()
      }
    },
    OutRowClassName({row, rowIndex}) {
      let endtime = row.comBined
      let statetime = row.price/row.standard*row.attenDance+row.overPay
      if(endtime !==statetime){
        row.yuanyin = true
        return 'warning-row';
      }
      return '';
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.singles = selection.length!==1
      this.single = selection
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加考勤表";
    },

    /** 提交按钮 */
    submitForm() {
      if (this.$refs.file.uploadFiles[0] == null) {
        this.msgError("请选择文件")
      }else{
      let formData = new FormData()
      formData.append('file', this.$refs.file.uploadFiles[0].raw);
      formData.append('months', this.form.months);
      this.$refs["form"].validate(valid => {
       if (valid) {
          addInsidesalary(formData).then(response => {
            this.msgSuccess("新增成功");
            this.open = false;
            this.getList();
          });
      }
    });
      }
    },
    downl(){
      let srcs = "http://115.159.35.233:8090/profile/avatar/system/%E5%86%85%E9%83%A8%E5%91%98%E5%B7%A5%E5%BD%95%E5%85%A5%E7%B3%BB%E7%BB%9F%E6%A8%A1%E6%9D%BF.xlxs"
      window.open(srcs, '_blank');
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除服务费核算请求书编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delInsidesalary(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有服务费核算请求书数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportInsidesalary(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
<style>
  .el-table .warning-row {
      color: red;
  }
</style>
