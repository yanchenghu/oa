<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" >
      <el-form-item prop="customerName">
        <el-input
          v-model="queryParams.customerName"
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
      <el-form-item prop="months">
        <el-date-picker
          type="month"
          style="width:160px"
          format="yyyy 年 MM 月 "
          value-format="yyyy-MM-dd"
          size="small"
          v-model="queryParams.months"
          placeholder="选择月"
          :picker-options="pickerOptions3"
          @change="handleQuery"
          >
        </el-date-picker>
      </el-form-item>
     <el-form-item>
       <el-button type="primary"  size="small" @click="seemuban">查看文件模板</el-button>
       <el-button type="primary" plain  size="small" @click="handleExport">导出</el-button>
       <el-button type="primary" v-hasPermi="['finance:servicecharge:add']"  size="small" @click="handleAdd">新增考勤表</el-button>
     </el-form-item>
    </el-form>

    <el-table  :data="servicechargeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="姓名"  prop="customerName" />
      <el-table-column label="电话"  prop="customerTel" />
      <el-table-column label="月份"  prop="months" >
      </el-table-column>
      <el-table-column label="综合工资"  prop="basicSalary" />
      <el-table-column label="满勤/天"  prop="overAttence" />
      <el-table-column label="实际出勤/天"  prop="realAttence" />
      <el-table-column label="应发工资"  prop="projectSalary" />
      <el-table-column label="补助/其他"  prop="buzhuSalary" />
      <el-table-column label="保险/其他"  prop="baoxianSalary" />
      <el-table-column label="实发金额"  prop="realSalary" />
      <el-table-column label="操作"  class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="medium"
            type="text"
            @click="handlesele(scope.row)"
            v-hasPermi="['finance:servicecharge:batchBilling']"
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
    <!-- 修改 -->
    <el-dialog title="修改核算单" :visible.sync="open3" width="500px" append-to-body>
      <el-alert
        v-if="form.reason==null"
        title="仅限修改一次,请考虑"
        type="error">
      </el-alert>
      <el-alert
        v-else
        title="仅限修改一次,已修改"
        type="error">
      </el-alert>
      <br>
      <el-form ref="form" :disabled="form.reason!==null" style="width: 200px;" :model="form" :rules="rules"  label-width="80px">
        <el-form-item label="姓名" prop="customerName">
          <span>{{form.customerName}}</span>
        </el-form-item>
        <el-form-item label="服务费" prop="price">
          <el-input v-model.number="form.price"/>
        </el-form-item>
        <el-form-item label="标准工时" prop="standard">
          <el-input v-model.number="form.standard"/>
        </el-form-item>
        <el-form-item label="出勤工时" prop="attenDance">
          <el-input v-model.number="form.attenDance"/>
        </el-form-item>
        <el-form-item label="加班费" prop="overPay">
          <el-input v-model.number="form.overPay"/>
        </el-form-item>
        <el-form-item label="合计" prop="comBined">
          <el-input v-model.number="form.comBined"/>
        </el-form-item>
        <el-form-item label="修改原因" prop="reasons">
          <el-input v-model.number="form.reasons"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submithetong">确 定</el-button>
        <el-button @click="open3=false">取 消</el-button>
      </div>
    </el-dialog>

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
import { listExpatriatesalary, getExpatriatesalary, delExpatriatesalary, addExpatriatesalary, updateExpatriatesalary, exportExpatriatesalary } from "@/api/finance/expatriatesalary";
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
        customerName:null,
        startTime:null,
        endTime:null,
        corpCode:null,
        party:null,
        status:null,
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
          message: "甲方公司不能为空",
          trigger: ["blur","change" ]
        }, ],
        party: [{
          required: true,
          message: "乙方公司不能为空",
          trigger: ["blur","change" ]
        }, ],
        price:[{
            required: true,
            message: "服务费不能为空",
            trigger: ["blur","change"]
          },],
        standard:[{
            required: true,
            message: "标准工时不能为空",
            trigger: ["blur","change"]
          },],
        attenDance:[{
            required: true,
            message: "出勤工时不能为空",
            trigger: ["blur","change"]
          },],
        overPay:[{
            required: true,
            message: "加班费不能为空",
            trigger: ["blur","change"]
          },],
        comBined:[{
            required: true,
            message: "合计不能为空",
            trigger: ["blur","change"]
          },],
        reasons:[{
            required: true,
            message: "修改原因不能为空",
            trigger: ["blur","change"]
          },],

      }
    };
  },
  created() {
    this.getList();
    this.getCorpName();
    // 付款状态
    this.getDicts("serv_payment_status").then(response => {
      this.customerleve = response.data;
    });
    this.getDicts("party").then(response => {
      this.partylist = response.data;
    });
  },
  methods: {
    seemuban(){
      this.wenjian = true
      this.title = "文件模板预览"
      this.src = "https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=http://115.159.35.233:8090/profile/avatar/system/%E5%A4%96%E6%B4%BE%E5%91%98%E5%B7%A5%E5%BD%95%E5%85%A5%E7%B3%BB%E7%BB%9F%E6%A8%A1%E6%9D%BF.xls"
    },
    getCorpName(){
      corpNames().then(res=>{
        this.corpnamelists=res
      });
    },
    handlesele(row){
      let srcs = process.env.VUE_APP_BASE_API+row.excelPath
      this.src=`https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=${srcs}`
      this.wenjian = true
      this.title = "预览人员信息"

    },
    // 付款状态
    customerleveFormat(row, column) {
      return this.selectDictLabel(this.customerleve, row.status);
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
      listExpatriatesalary(this.queryParams).then(response => {
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
    kaipiao(id){
      let formData = new FormData()
      let zm = {
        list:id
      }
      formData.append("zm",JSON.stringify(zm))
      this.$confirm('是否确认开票所选数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return kaipServicecharge(formData);
        }).then(() => {
          this.getList();
          this.msgSuccess("开票成功");
        }).catch(()=>{})
    },


    submithetong(){
      this.$refs["form"].validate(valid => {
        let form = this.form
        form.reason = form.reasons
        if (valid) {
          this.$confirm('是否确认修改"' + form.customerName + '"的服务费核算请求吗?', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
              return updateExpatriatesalary(form);
            }).then(() => {
              this.msgSuccess("修改成功");
              this.open3 = false;
              this.getList();
            })
        }
      });
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
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
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
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getServicecharge(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改服务费核算请求书";
      });
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
          addExpatriatesalary(formData).then(response => {
            this.msgSuccess("新增成功");
            this.open = false;
            this.getList();
          });
      }
    });
      }
    },
    downl(){
      let srcs = "http://115.159.35.233:8090/profile/avatar/system/%E5%A4%96%E6%B4%BE%E5%91%98%E5%B7%A5%E5%BD%95%E5%85%A5%E7%B3%BB%E7%BB%9F%E6%A8%A1%E6%9D%BF.xls"
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
          return delExpatriatesalary(ids);
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
          return exportExpatriatesalary(queryParams);
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
