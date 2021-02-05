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
      <el-form-item prop="customerTel">
        <el-date-picker
          v-model="value1"
          type="monthrange"
          size="small"
          format="yyyy 年 MM 月 "
          value-format="yyyy-MM-dd"
          range-separator="至"
          start-placeholder="开始月份"
          end-placeholder="结束月份"
          style="width:260px"
          @change="handleQuery">
        </el-date-picker>
      </el-form-item>
      <el-form-item  prop="corpCode">
        <el-select v-model="queryParams.corpCode" placeholder="选择甲方" filterable  clearable @change="handleQuery" size="small" >
          <el-option
              v-for="dict in corpnamelists"
              :key="dict.corpCode"
              :label="dict.corpName"
              :value="dict.corpCode"
           />
        </el-select>
      </el-form-item>
      <el-form-item  prop="party">
        <el-select v-model="queryParams.party" placeholder="选择已方" filterable  clearable @change="handleQuery" size="small" style="width:160px">
          <el-option
            v-for="dict in partylist"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item  prop="status">
        <el-select v-model="queryParams.status" @change="handleQuery" placeholder="选择付款状态" clearable size="small" style="width:140px">
          <el-option
            v-for="dict in customerleve"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
     <el-form-item class="asd">
       <el-button type="primary" v-hasPermi="['finance:servicecharge:batchBilling']" :disabled="multiple" plain  size="small" @click="batchkaipiao">批量开票</el-button>
       <el-button type="primary" v-hasPermi="['finance:servicecharge:batchPayment']" :disabled="multiple" plain  size="small" @click="batchfukuan">批量付款</el-button>
       <el-button type="primary" v-hasPermi="['finance:servicecharge:add']"  size="small" @click="handleAdd">新增核算单</el-button>
       <el-button type="primary" plain  size="small" @click="seemuban">查看文件模板</el-button>
       <el-button type="success" :disabled="singles" size="small" @click="handlesele">修改</el-button>
     </el-form-item>
    </el-form>
    <el-button type="primary" plain v-hasPermi="['finance:servicecharge:export']" size="small" @click="handleExport" style=" float: right;margin-bottom: 10px;">导出</el-button>
    <el-table :row-class-name="OutRowClassName"  :data="servicechargeList" @selection-change="handleSelectionChange" v-loading="loading">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="姓名"  prop="customerName">
        <template slot-scope="scope">
          <el-button
            type="text"
            @click="handsee(scope.row)"
          >{{scope.row.customerName}}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="结算单开始时间"  prop="time" width="180">
      </el-table-column>
      <el-table-column label="服务费"  prop="price" />
      <el-table-column label="系统服务费"  prop="overTime">
        <template slot-scope="scope">
          <span>{{scope.row.overTime==null||scope.row.overTime==""?'服务费异常':scope.row.overTime}}</span>
        </template>
      </el-table-column>
      <el-table-column label="标准工时"  prop="standard" />
      <el-table-column label="出勤工时"  prop="attenDance" />
      <el-table-column label="加班费"  prop="overPay" />
      <el-table-column label="合计"  prop="comBined" />
      <el-table-column label="系统合计">
        <template slot-scope="scope">
          <span>{{(scope.row.price/scope.row.standard*scope.row.attenDance+scope.row.overPay).toFixed(2)}}</span>
        </template>
      </el-table-column>
      <el-table-column label="付款状态"  prop="status" :formatter="customerleveFormat"/>
      <el-table-column label="原因"  prop="reason" />

      <el-table-column label="操作"  class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.status==1"
            type="text"
            @click="batchkaipiao(scope.row)"
            v-hasPermi="['finance:servicecharge:batchBilling']"
          ><svg-icon icon-class="kp" class="icons"/>开票</el-button>
          <el-button
            v-if="scope.row.status==2"
            type="text"
            @click="batchfukuan(scope.row)"
            v-hasPermi="['finance:servicecharge:batchPayment']"
          ><svg-icon icon-class="fk" class="icons"/>付款</el-button>
          <el-button
            v-if="scope.row.status==3"
            type="text"
            @click="handlesele(scope.row,1)"
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
    <el-dialog :title="title" :visible.sync="open2" width="500px" append-to-body>
      <el-upload
        action="#"
        list-type="picture-card"
        :auto-upload="false"
        ref="files"
        :limit="1"
        :on-exceed="handleExceed"
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
                class="el-upload-list__it
                em-preview"
                @click="handlePictureCardPreview(file)"
              >
                <i class="el-icon-zoom-in"></i>
              </span>
              <span
                class="el-upload-list__item-delete"
                @click="handleRemove(file)"
              >
                <i class="el-icon-delete"></i>
              </span>
            </span>
          </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitkaipiao">确 定</el-button>
        <el-button @click="open2=false">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogVisible" width="500px" title="预览图片">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>

    <el-dialog title="查看" :visible.sync="open4" width="500px" append-to-body>
      <el-form  style="width: 200px;"  label-width="80px">

        <el-form-item label="付款时间" prop="paymentTime">
          <span class="span">{{form.paymentTime}}</span>
        </el-form-item>
        <el-form-item label="付款图片">
          <li class="li-li" v-if="form.paymentTime">
              <img  width="100%"  :src="srcs+form.paymentTime">
                <span class="imgs">
                  <span
                   class="el-upload-list__item-preview"
                    @click="filesee(form.paymentTime)"
                  >
                    <i class="el-icon-zoom-in"></i>
                  </span>
                  <span
                    @click="filedow(form.paymentTime)"
                  >
                    <i class="el-icon-download"></i>
                  </span>
                </span>
          </li>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!-- 修改 -->
    <el-dialog title="修改核算单" :visible.sync="open3" width="500px" append-to-body>
      <el-alert
        v-if="form.reasons"
        title="仅限修改一次,请考虑"
        type="error">
      </el-alert>
      <el-alert
        v-else
        title="仅限修改一次,已修改"
        type="error">
      </el-alert>
      <br>
      <el-form ref="form" :disabled="!form.reasons" style="width: 230px;" :model="form" :rules="rules"  label-width="80px">
        <el-form-item label="姓名" prop="customerName">
          <span>{{form.customerName}}</span>
        </el-form-item>
        <el-form-item label="服务费" prop="price">
          <el-input v-model="form.price"/>
        </el-form-item>
        <el-form-item label="标准工时" prop="standard">
          <el-input v-model="form.standard"/>
        </el-form-item>
        <el-form-item label="出勤工时" prop="attenDance">
          <el-input v-model="form.attenDance"/>
        </el-form-item>
        <el-form-item label="加班费" prop="overPay">
          <el-input v-model="form.overPay"/>
        </el-form-item>
        <el-form-item label="合计" prop="comBined">
          <el-input v-model="form.comBined"/>
        </el-form-item>
        <el-form-item label="修改原因" prop="reason">
          <el-input v-model="form.reason"/>
        </el-form-item>
      </el-form>
      <div v-if="form.reasons"  slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submithetong">确 定</el-button>
        <el-button @click="open3=false">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="title" :visible.sync="wenjian" width="70%">
      <el-button v-if="title=='文件模板预览'" type="primary" @click="downl">下载模板</el-button>
     <iframe
        :src="src"
        style="overflow: auto; position: absolute; top: 140px; right: 0; bottom: 0; left: 0; width: 100%; height:500px; border: none;"
      ></iframe>
    </el-dialog>
     <!-- 付款时间 -->
    <el-dialog title="选择付款日期" :visible.sync="fukuan" width="500px">
      <el-form ref="form1" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="付款时间" prop="paymentTime">
              <el-date-picker type="date" placeholder="选择借用时间" v-model="form.paymentTime" value-format="yyyy-MM-dd" size="small" :picker-options="pickerOptions3"></el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitfukuan">确 定</el-button>
        <el-button @click="fukuan=false">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改服务费核算请求书对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form2" :model="form" :rules="rules" label-width="80px">
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
        <el-form-item label="甲方公司" prop="corpCode">
          <el-select filterable  v-model="form.corpCode"  placeholder="请选择" size="small" >
            <el-option
                v-for="dict in corpnamelists"
                :key="dict.corpCode"
                :label="dict.corpName"
                :value="dict.corpCode"
              />
            </el-select>
        </el-form-item>
        <el-form-item label="乙方公司" prop="party">
          <el-select v-model="form.party" placeholder="选择已方" filterable  size="small">
            <el-option
              v-for="dict in partylist"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
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
import { listServicecharge, getServicecharge, delServicecharge, addServicecharge, updateServicecharge, exportServicecharge, kaipServicecharge } from "@/api/finance/servicecharge";
import {
    corpNames,
  } from "@/api/demand/follow";
export default {
  name: "Servicecharge",
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
      wenjian:false,
      value1:"",
      src:"",
      srcs:process.env.VUE_APP_BASE_API,
      open4:false,
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
      fukuan:false,
      // 表单校验
      rules: {
        paymentTime:[{
          required: true,
          message: "付款时间不能为空",
          trigger: ["blur","change" ]
        }],
        corpCode: [{
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
            validator: price,
            trigger: "blur",
          },],
        standard:[{
            required: true,
            validator: price,
            trigger: ["blur","change"]
          },],
        attenDance:[{
            required: true,
            validator: price,
            trigger: ["blur","change"]
          },],
        overPay:[{
            required: true,
            validator: price,
            trigger: "blur"
          },],
        comBined:[{
            required: true,
            validator: price,
            trigger: ["blur","change"]
          },],
        reason:[{
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
      this.src = "https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=http://115.159.35.233:8090/profile/avatar/system/%E8%AF%B7%E6%B1%82%E4%B9%A6%E5%BD%95%E5%85%A5%E7%B3%BB%E7%BB%9F%E6%A8%A1%E6%9D%BF.xls"
    },
    handsee(row){
      if(row.excelPath==null){
        this.msgError("暂无原文件")
      }else{
        let srcs = process.env.VUE_APP_BASE_API+row.excelPath
        this.src=`https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=${srcs}`
        this.wenjian = true
        this.title = "预览人员信息"
      }
    },
    getCorpName(){
      corpNames().then(res=>{
        this.corpnamelists=res
      });
    },
    handleRemove(file) {
      if(this.$refs.files!==undefined){
        this.$refs.files.clearFiles()
      }
    },
    handlePictureCardPreview(file){
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handlesele(row,ind){
      this.reset()
      if(ind){
        this.open4= true
      }else{
        this.open3= true
        this.title = "修改服务费核算请求书";
      }
      let ids = row.id || this.ids
      getServicecharge(ids).then(response => {
        this.form = response.data;
        if(this.form.reason==null){
          this.form.reasons = true
        }
      });
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
      if(this.value1==null){
        this.value1 = ""
        this.queryParams.startTime = null
        this.queryParams.endTime =null
      }else{
        this.queryParams.startTime = this.value1[0]
        this.queryParams.endTime = this.value1[1]
      }
      this.loading = true;
      listServicecharge(this.queryParams).then(response => {
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
        corpCode: null,
        party: null,
        price:null,
        standard:null,
        attenDance:null,
        overPay:null,
        comBined:null,
        reason:null,
      };
      this.resetForm("form");
      this.resetForm("form2");
      if(this.$refs.file!==undefined){
        this.$refs.file.clearFiles()
      }
    },
    submitfukuan(){
      this.$refs["form1"].validate(valid => {
        if (valid) {
          let formData = new FormData()
          let zm = {
            list:this.ids,
            paymentTime:this.form.paymentTime
          }
          formData.append("zm",JSON.stringify(zm))
          this.$confirm('确认开票所选数据项吗?', "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
              return kaipServicecharge(formData);
            }).then(() => {
              this.getList();
              this.fukuan = false
              this.msgSuccess("付款成功");
            }).catch(()=>{})
        }
      })
    },
    kaipiao(){
      this.fukuan = true
      this.resetForm("form1");
    },
    batchfukuan(row){
      let ids
      if(row.id){
        this.ids = [row.id]
        this.kaipiao()
      }else{
        if(this.queryParams.corpCode==null|| this.queryParams.corpCode==""){
          this.msgError("请选择甲方公司")
        }else{
          let a = 0
          this.single.forEach(item=>{
            if(item.status!==2){
              a+=1
            }
          })
          if(a!==0){
            this.msgError("有付款状态不同的人员")
          }else{
            this.kaipiao()
          }
        }
      }
    },
    batchkaipiao(row){
      if(row.id){
        this.title = "开票"
        this.ids = row.id
        this.open2 = true
        this.handleRemove()
      }else{
        if(this.queryParams.corpCode==null|| this.queryParams.corpCode==""){
          this.msgError("请选择甲方公司")
        }else{
          let a = 0
          this.single.forEach(item=>{
            if(item.status!==1){
              a+=1
            }
          })
          if(a!==0){
            this.msgError("有付款状态不同的人员")
          }else{
            this.title = "批量开票"
            this.open2 = true
            this.handleRemove()
          }
        }
      }
    },
    submithetong(){
      console.log(this.form)
      this.$refs["form"].validate(valid => {
        let form = this.form
        if (valid) {
          this.$confirm('是否确认修改"' + form.customerName + '"的服务费核算请求吗?', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
              return updateServicecharge(form);
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
      let zong = statetime - endtime
      if(Math.abs(zong)>1000){
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
      this.title = "添加服务费核算请求书";
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
    submitkaipiao(row){
      let ids = null
      if(this.title=="开票"){
        ids = [this.ids]
      }else{
        ids=this.ids;
      }
      let file = null
      if (this.$refs.files.uploadFiles[0] == null) {

      }else{
          file = this.$refs.files.uploadFiles[0].raw
      }
      let formData = new FormData()
      let zm = {
        list:ids
      }
      formData.append("zm",JSON.stringify(zm))
      formData.append("file",file)
      this.$confirm('是否确认开票所选数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delServicecharge(formData);
        }).then(() => {
          this.getList();
          this.open2 = false
          this.msgSuccess("开票成功");
        }).catch(()=>{})
    },
    filesee(){},
    filedow(){},
    /** 提交按钮 */
    submitForm() {
      if (this.$refs.file.uploadFiles[0] == null) {
        this.msgError("请选择文件")
      }else{
      let formData = new FormData()
      formData.append('file', this.$refs.file.uploadFiles[0].raw);
      formData.append('corpCode', this.form.corpCode);
      formData.append('party', this.form.party);
      this.$refs["form2"].validate(valid => {
       if (valid) {
          addServicecharge(formData).then(response => {
            this.msgSuccess("新增成功");
            this.open = false;
            this.getList();
          });
      }
    });
      }
    },
    downl(){
      let srcs = "http://115.159.35.233:8090/profile/avatar/system/%E8%AF%B7%E6%B1%82%E4%B9%A6%E5%BD%95%E5%85%A5%E7%B3%BB%E7%BB%9F%E6%A8%A1%E6%9D%BF.xls"
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
          return delServicecharge(ids);
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
          return exportServicecharge(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
<style scoped>
  >>>.el-table .warning-row {
      color: red;
  }
  .span{
    font-size: 14px;
    font-weight: 400;
    color: #909399;
  }
  .li-li{
    overflow: hidden;
    background-color: #fff;
    border: 1px solid #c0ccda;
    border-radius: 6px;
    box-sizing: border-box;
    width: 148px;
    height: 148px;
    margin: 0 8px 8px 0;
    display: inline-block;
    position: relative;
  }
  .imgs{
      position: absolute;
      width: 100%;
      height: 100%;
      left: 0;
      top: 0;
      cursor: default;
      color: #fff;
      opacity: 0;
      font-size: 20px;
      background-color: rgba(0, 0, 0, 0.5);
      transition: opacity .3s;
      display: flex;
      justify-content: space-around;
  }
  .imgs:hover{
    opacity: 1;
  }
  .imgs span{
    align-self: center;
    position: static;
    cursor: pointer;
  }
</style>
