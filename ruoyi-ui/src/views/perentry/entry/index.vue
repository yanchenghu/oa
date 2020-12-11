<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item  prop="customerCode">
        <el-input
          v-model="queryParams.customerCode"
          placeholder="请输入姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">查询</el-button>
      </el-form-item>
      <el-form-item  prop="startTime">
        <el-date-picker
            v-model="value1"
            type="daterange"
            size="small"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            @change="handleQuery">
        </el-date-picker>
      </el-form-item>
      <el-form-item  prop="corpName">
        <el-select v-model="queryParams.corpName" clearable placeholder="请选择客户" size="small" @change="handleQuery">
          <el-option
              v-for="dict in entryList"
              :key="dict.marCustomerprojectpay.id"
              :label="dict.marCustomerprojectpay.corpName"
              :value="dict.marCustomerprojectpay.corpName"
            />
        </el-select>
      </el-form-item>
      <el-form-item  prop="settledCycle">
        <el-select v-model="queryParams.settledCycle"  placeholder="是否在项" size="small" @change="handleQuery">
          <el-option label="入项" :value="1" />
          <el-option label="出项" :value="2" />
        </el-select>
      </el-form-item>
      <el-form-item style="float: right;">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['perentry:entry:export']"
        >导出</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table v-loading="loading" :data="entryList" @selection-change="handleSelectionChange" style="width: 100%;">
      <el-table-column type="selection" width="55"  />
      <el-table-column label="姓名"  prop="customerName" />
      <el-table-column label="电话"  prop="customerTel" />

      <el-table-column label="入职公司"  >
        <template slot-scope="scope">
          <span>{{ scope.row.marCustomerprojectpay.corpName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="入职时间"  prop="syqstartTime" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.marCustomerprojectpay.syqstartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="出项时间"  prop="outofProjecttime" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.marCustomerprojectpay.outofProjecttime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="工资">
        <template slot-scope="scope">
          <span>{{ scope.row.marCustomerprojectpay.salary }}</span>
        </template>
      </el-table-column>
      <el-table-column label="服务费用">
        <template slot-scope="scope">
          <span>{{ scope.row.marCustomerprojectpay.servicePay }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否缴纳社保"  prop="socSecopt">
        <template slot-scope="scope">
          <span>{{scope.row.marCustomerprojectpay.socSecopt==1?"是":"否"}}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否签署合同"  prop="socSecopt">
        <template slot-scope="scope">
          <span>{{scope.row.socSecopt==1?"是":"否"}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="100" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >添加人员信息</el-button>
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
    <!-- 抽屉 -->
    <el-drawer
      title="信息"
      :visible.sync="drawer"
      :with-header="false"
      size="60%"
      @close="dra"
      >
      <div style="margin:0 3% 0 3%;border-left:1px solid #E6E6E6;">
      <div style=" padding:20px 3% 30px 2%; border-bottom: 1px solid #E6E6E6;">
        <div>
          <b>
            {{yxdemandone.customerName}}
          </b>
        </div>
         <el-form :inline="true" :model="yxdemandone" class="demo-form-inline">
           <el-form-item label="当前状态">
             <el-input  :placeholder="yxdemandone.outofProjecttime==null?'出项':'在项'"  disabled size="small"></el-input>
           </el-form-item>
           <el-form-item  label="入职公司">
             <el-input  v-model="yxdemandone.corpName" disabled size="small"></el-input>
           </el-form-item>
          <el-form-item style="margin-left: 40px;">
            <el-button
            type="danger"
            @click="handleDelete"
            size="medium"
            >
              出项
            </el-button>
          </el-form-item>
         </el-form>
      </div>
      <div>
          <el-tabs v-model="activeName">
            <el-tab-pane label="联系人信息" name="popmsg">
               <div style="display: flex; justify-content: space-around;">
                 <el-form label-position="right" label-width="80px" :model="yxdemandone" :rules="rules">
                    <b>联系人信息</b>
                    <p></p>
                    <el-form-item label="姓名" prop="contactPeople">
                      <span>{{yxdemandone.customerName}}</span>
                    </el-form-item>
                    <el-form-item label="电话" prop="contactPosition">
                      <el-input  v-model="yxdemandone.contactPosition" ></el-input>
                    </el-form-item>
                    <el-form-item label="电话" prop="contactPhone">
                      <el-input  v-model="yxdemandone.contactPhone"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="mailbox">
                      <el-input  v-model="yxdemandone.mailbox" ></el-input>
                    </el-form-item>
                    <el-form-item label="微信">
                      <el-input  v-model="yxdemandone.wechat" ></el-input>
                    </el-form-item>
                    <el-form-item label="QQ">
                      <el-input  v-model="yxdemandone.qq" ></el-input>
                    </el-form-item>
                 </el-form>
                 <el-form label-position="right" label-width="100px" :model="yxdemandone">
                   <b>外包公司信息</b>
                   <p></p>
                    <el-form-item label="身份证">
                      <el-button type="text" @click="upfile(1)">
                          点击上传
                      </el-button>
                      <el-button type="text" @click="seefile(1)">
                          点击预览
                      </el-button>
                    </el-form-item>
                    <el-form-item label="毕业证">
                      <el-button type="text" @click="upfile(2)">
                          点击上传
                      </el-button>
                      <el-button type="text" @click="seefile(2)">
                          点击预览
                      </el-button>
                    </el-form-item>
                    <el-form-item label="学位证">
                      <el-button type="text" @click="upfile(3)">
                          点击上传
                      </el-button>
                      <el-button type="text" @click="seefile(3)">
                          点击预览
                      </el-button>
                    </el-form-item>
                    <el-form-item label="保密协议">
                      <el-button type="text" @click="upfile(4)">
                          点击上传
                      </el-button>
                      <el-button type="text" @click="seefile(4)">
                          点击预览
                      </el-button>
                    </el-form-item>
                    <el-form-item label="劳动合同">
                      <el-button type="text" @click="upfile(5)">
                          点击上传
                      </el-button>
                      <el-button type="text" @click="seefile(5)">
                          点击预览
                      </el-button>
                    </el-form-item>
                 </el-form>
               </div>
            </el-tab-pane>
            <el-tab-pane label="借用合同">
              <el-button type="primary" @click="handAdd">新建借用记录</el-button>
              <p></p>
              <el-table v-loading="loadings" :data="contractlist">
                <el-table-column label="时间" align="center" prop="startTime" width="180">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="物品" align="left" prop="firstParty" width="160"/>
                <el-table-column label="型号" align="left" prop="party"/>

                <el-table-column label="是否签署协议" align="center" prop="endTime" width="180">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="归还日期" align="center" prop="cooperationTime" width="180">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.cooperationTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>

                <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      type="text"
                      @click="preview(scope.row)"
                    >归还</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="入项记录">

              <el-table v-loading="loadings" :data="contractlist">
                <el-table-column label="入项公司" align="left" prop="firstParty" width="100"/>
                <el-table-column label="入职项目" align="left" prop="firstParty" width="100"/>
                <el-table-column label="入职日期" align="center" prop="startTime" width="180">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="出项日期" align="center" prop="startTime" width="180">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="物品" align="left" prop="firstParty" width="160"/>
                <el-table-column label="型号" align="left" prop="party"/>

                <el-table-column label="是否签署协议" align="center" prop="endTime" width="180">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="归还日期" align="center" prop="cooperationTime" width="180">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.cooperationTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>

                <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      type="text"
                      @click="preview(scope.row)"
                    >归还</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
          </el-tabs>
       </div>
      </div>
    </el-drawer>
    <el-dialog :title="title2" :visible.sync="open2" width="500px">
      <el-upload
        :action="url"
        list-type="picture-card"
        :auto-upload="false"
        ref="file"
        :headers="headers"
        :limit="1"
        :on-exceed="handleExceed"
        :on-success="handleFileSuccess"
        accept=".png,.jpg,"
        >
        <div slot="tip" class="el-upload__tip">仅支持上传jpg/png文件</div>
          <i slot="default" class="el-icon-plus"></i>
          <div slot="file" slot-scope="{file}">
            <img
              class="el-upload-list__item-thumbnail"
              :src="file.url" alt=""
            >
            <span class="el-upload-list__item-actions">
              <span
                v-if="!disabled"
                class="el-upload-list__item-delete"
                @click="handleRemove(file)"
              >
                <i class="el-icon-delete"></i>
              </span>
            </span>
          </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm2">确 定</el-button>
        <el-button @click="open2=false">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
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
import { getToken } from "@/utils/auth";
export default {
  name: "Entry",
  data() {
    return {
      disabled: false,
      name:"",
      loadings:false,
      activeName:"popmsg",
      // 服务费记录
      marServicepays:[],
      // 工资记录
      marAdsalaries:[],
      // 入项信息
      Listmarcustomerp:[],
      // 借用物品
      contractlist:[],
      // 证件照
      marCertificates1:{},
      // 入项基本信息
      yxdemandone:{},
      url: process.env.VUE_APP_BASE_API + "/system/user/importData",
      headers: { Authorization: "Bearer " + getToken()},
      dialogVisible:false,
      // 查看照片路径
      dialogImageUrl:"",
      // 抽屉
      drawer:false,
      // 时间
      value1:"",
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
      // 上传简历
      open2:false,
      title2:"",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        customerCode:null,
        endTime: null,
        startTime: null,
        corpName: null,
        settledCycle:null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {


      }
    };
  },
  watch:{
    "value1.length":{
      handler(newValue, oldValue) {
        if (newValue>1) {
            this.queryParams.settledCycle=1
        }
      }
    }
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询入项列表 */
    getList() {
      this.queryParams.startTime = this.value1[0]
      this.queryParams.endTime = this.value1[1]
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
    dra(){
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.value1 = ""
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.open2 = false;
      this.$refs.file.clearFiles();
    },
    handleExceed(){
      this.msgError(`当前限制选择 1 个文件`);
    },
    handleRemove(file) {
        this.$refs.file.clearFiles()
    },
    submitForm2(){
      this.$refs.file.submit();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加入项";
    },
    handAdd(){},
    // 上传文件
    upfile(ind){
      if(ind==1){
        // 上传身份证
        this.title2 = "上传身份证"
      }else if(ind==2){
        // 上传毕业证
        this.title2 = "上传毕业证"
      }else if(ind==3){
        // 上传学位证
        this.title2 = "上传学位证"
      }else if(ind==4){
        // 上传保密协议
        this.title2 = "上传保密协议"
      }else if(ind==5){
        // 上传劳动合同
        this.title2 = "上传劳动合同"
      }
      this.open2 = true
    },

    // 预览文件
    seefile(){

    },

    /** 添加人员信息按钮操作 */
    handleUpdate(row) {
      const id = row.marCustomerprojectpay.id
      getEntry(id).then(response => {
        this.yxdemandone = response.data.marprojectpay
        this.marAdsalaries = response.data.marAdsalaries
        this.marBorrows = response.data.marBorrows
        this.marCertificates1 = response.data.marCertificates1
        this.marServicepays = response.data.marServicepays
        this.Listmarcustomerp = response.data.Listmarcustomerp
        this.drawer=true


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
    /** 出项按钮操作 */
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
<style>
  .el-tabs__item:focus.is-active.is-focus:not(:active) {
        -webkit-box-shadow: none !important;
        box-shadow: none !important;
  }
    .el-tabs__header{
      background: #F5F5F9;
      padding-left:5%;
    }
    .el-tabs__content{
      padding:20px 3% 0 3%;
    }
     .el-drawer.rtl{
          overflow: auto;
      }
      .span{
        color: #2C2C2C;
      }
      .sp{
        font-size: 11px;
        color: #8A8A8A;
      }
</style>
