<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item prop="months">
        <el-date-picker
          type="month"
          format="yyyy年 MM 月 "
          value-format="yyyy-MM-dd"
          size="small"
          v-model="queryParams.addTime"
          :clearable="false"
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
    <div style="display: flex;justify-content: space-between;margin-bottom: 10px;">
      <div style="font-weight: 400;font-size: 25px;">{{ parseTime(this.queryParams.addTime, '{y}年{m}月')}}份预计回款明细</div>
      <div>
        <el-button  type="primary" v-hasPermi="['finance:ancecompany:add']" size="small" @click="handleAdd">新增回款公司</el-button>
        <!-- <el-button type="primary" plain v-hasPermi="['finance:ancecompany:export']"  size="small" @click="handleExport" >导出</el-button> -->
        <el-button
        v-if="kaiguan"
          :disabled="single"
          type="danger"
          @click="handleDelete"
          v-hasPermi="['finance:ancecompany:remove']"
        >删除</el-button>
      </div>
    </div>

    <el-table v-loading="loading" :data="AncecompanyList" @selection-change="handleSelectionChange" show-summary :summary-method="getSummaries" :default-sort = "{prop: 'collectionStatus', order: 'ascending'}" :row-class-name="OutRowClassName">
      <el-table-column type="selection" width="60" align="center" />
      <el-table-column label="序号"  type="index" width="55"/>
      <el-table-column label="客户名称" prop="corpName" width="240">
        <template slot-scope="scope">
          <span class="xiugai"  @click="handleSee(scope.row)">{{scope.row.corpName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="人/月"  prop="manMonth" />
      <el-table-column label="回款月份"  prop="actualMonth" />
      <el-table-column label="应回款金额"  prop="actualMoney" />
      <el-table-column label="未回款金额" prop="weihuikuan">
        <template slot-scope="scope">
          <span v-if="scope.row.actualMoney!==null&&scope.row.receivedPayment!==null">{{scope.row.actualMoney-scope.row.receivedPayment}}</span>
        </template>
      </el-table-column>
      <el-table-column label="上个月工资"  prop="lastmonthWages" />
      <el-table-column label="当月营收" prop="dengyue">
        <template slot-scope="scope">
          <span v-if="scope.row.actualMoney!==null">{{scope.row.actualMoney-scope.row.lastmonthWages}}</span>
        </template>
      </el-table-column>
      <el-table-column label="已回款金额"  prop="receivedPayment" />
      <el-table-column label="沟通情况"  prop="contactSituation" />
      <el-table-column label="备注"  prop="remarks"/>
      <el-table-column label="预回款时间"  prop="collectionTime" />
      <el-table-column label="回款状态"  prop="collectionStatus" :formatter="customerSexFormat" sortable  width="100"/>
      <el-table-column label="操作" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <span class="xiugai" v-hasPermi="['finance:ancecompany:edit']" @click="handleUpdate(scope.row)"><i class="el-icon-edit" />修改</span>
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

    <div style="display: flex;justify-content: space-between;margin-bottom: 10px;margin-top: 50px;">
      <div style="font-weight: 400;font-size: 25px;">重点监测公司</div>
      <div>
        <el-button  type="primary" v-hasPermi="['finance:ancecompany:add']" size="small" @click="handleAdd2">新增监测公司</el-button>
        <!-- <el-button type="primary" plain v-hasPermi="['expenditure:ancecompany:export']"  size="small" @click="handleExport" >导出</el-button> -->
        <el-button
          :disabled="single2"
          type="danger"
          @click="handleDelete2"
          v-hasPermi="['finance:ancecompany:remove']"
        >删除</el-button>
      </div>
    </div>

    <el-table v-loading="loading2" :data="Monitoringlist" @selection-change="handleSelectionChange2">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号"  type="index" width="55"/>
      <el-table-column label="客户名称" prop="corpName" />
      <el-table-column label="预回款月份"  prop="backMonth" />
      <el-table-column label="目前欠款"  prop="currentArrears" />
      <!-- <el-table-column label="总欠款"  prop="accumulatedCollection" />
      <el-table-column label="总成本"  prop="allExpenditure" /> -->
      <el-table-column label="沟通情况"  prop="contactSituation" />
      <el-table-column label="操作"  class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-if="kaiguan"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate2(scope.row)"
            v-hasPermi="['finance:ancecompany:edit']"
          >修改</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total2>0"
      :total="total2"
      :page.sync="queryParams2.pageNum"
      :limit.sync="queryParams2.pageSize"
      @pagination="getList"
    />
    <!-- 添加或修改每月公司其他住处费用对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px" style="width: 300px;">
        <el-form-item label="客户名称">
          <span>{{form.corpName}}</span>
        </el-form-item>
        <el-form-item label="人/月" prop="manMonth">
          <el-input v-model="form.manMonth" placeholder="请输入应回款人数" />
        </el-form-item>
        <el-form-item label="回款月份" prop="actualMonth">
          <el-input v-model="form.actualMonth" placeholder="请输入回款月份" />
        </el-form-item>
        <el-form-item label="应回款金额" prop="actualMoney">
          <el-input v-model="form.actualMoney" placeholder="请输入应回款" />
        </el-form-item>
        <el-form-item label="已回款金额" >
          <el-input v-model="form.receivedPayment" placeholder="请输入已回款金额" />
        </el-form-item>
        <el-form-item label="上个月工资">
          <el-input v-model="form.lastmonthWages" placeholder="请输入其他" />
        </el-form-item>
        <el-form-item label="沟通情况" prop="contactSituation">
          <el-input type="textarea" v-model="form.contactSituation" placeholder="请输入沟通情况" />
        </el-form-item>
        <el-form-item label="回款状态" prop="collectionStatus">
            <el-select
                v-model="form.collectionStatus"
                placeholder="请选择回款状态">
                <el-option
                  v-for="item in contactSituationlist"
                  :key="parseInt(item.dictValue)"
                  :label="item.dictLabel"
                  :value="parseInt(item.dictValue)">
                </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="预回款时间" prop="collectionTime">
          <el-date-picker
            type="date"
            style="width:200px;"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd"
            v-model="form.collectionTime"
            placeholder="请选择预回款时间"
            >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" v-model="form.remarks" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="open2" :title="title" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" style="width: 300px;">
        <el-form-item label="客户名称" prop="corpName">
          <el-select
              v-model="form.corpName"
              multiple
              collapse-tags
              filterable
              placeholder="请选择客户">
              <el-option
                v-for="item in corpnamelists"
                :key="item.corpCode"
                :label="item.corpName"
                :value="item.corpCode">
              </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="open2=false">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="open3" :title="title" width="98s0px" append-to-body>
        <el-table v-loading="loading" :data="historyList" >
          <el-table-column label="序号"  type="index" width="55"/>
          <el-table-column label="人/月"  prop="manMonth" />
          <el-table-column label="回款月份"  prop="actualMonth" />
          <el-table-column label="应回款金额"  prop="actualMoney" />
          <el-table-column label="未回款金额" >
            <template slot-scope="scope">
              <span v-if="scope.row.actualMoney!==null&&scope.row.receivedPayment!==null">{{scope.row.actualMoney-scope.row.receivedPayment}}</span>
            </template>
          </el-table-column>
          <el-table-column label="上个月工资"  prop="lastmonthWages" />
          <el-table-column label="当月营收" >
            <template slot-scope="scope">
              <span v-if="scope.row.actualMoney!==null">{{scope.row.actualMoney-scope.row.lastmonthWages}}</span>
            </template>
          </el-table-column>
          <el-table-column label="已回款金额"  prop="receivedPayment" />
          <el-table-column label="沟通情况"  prop="contactSituation" />
          <el-table-column label="备注"  prop="remarks"/>
          <el-table-column label="预回款时间"  prop="collectionTime" />
          <el-table-column label="回款状态" :formatter="customerSexFormat" prop="collectionStatus" />
        </el-table>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="open4" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px" style="width: 300px;">
        <el-form-item label="客户名称" prop="corpCode">
          <span v-if="title=='修改重点监测公司'">{{form.corpName}}</span>
          <el-select
              v-else
              v-model="form.corpCode"
              filterable
              placeholder="请选择客户名称">
              <el-option
                v-for="item in corpnamelists"
                :key="item.corpCode"
                :label="item.corpName"
                :value="item.corpCode">
              </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="预回款月份" prop="backMonth">
          <el-date-picker
            type="month"
            style="width:200px;"
            format="yyyy 年 MM 月 "
            value-format="yyyy-MM"
            v-model="form.backMonth"
            placeholder="请选择预回款月份"
            >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="目前欠款" prop="currentArrears">
          <el-input v-model="form.currentArrears" placeholder="请输入应回款" />
        </el-form-item>
        <el-form-item label="沟通情况" prop="contactSituation">
          <el-input type="textarea" v-model="form.contactSituation" placeholder="请输入沟通情况" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm1">确 定</el-button>
        <el-button @click="open4=false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAncecompany, getAncecompany, delAncecompany, addAncecompany, updateAncecompany, exportAncecompany,gethistory,listMonitoring,addMonitoring,updateMonitoring,getMonitoring,delMonitorid } from "@/api/finance/ancecompany";
import {
    corpNames,
  } from "@/api/demand/follow";
import {debounce} from "@/utils/ruoyi.js"
export default {
  name: "Ancecompany",
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
      contactSituationlist:[],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      open2:false,
      open3:false,
      // 非单个禁用
      single: true,
      single2:true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      corpnamelists:[],
      // 总条数
      total: 0,
      // 每月公司其他住处费用表格数据
      AncecompanyList: [],
      Monitoringlist:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      kaiguan:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        addTime: null,
      },
      queryParams2: {
        pageNum: 1,
        pageSize: 10,
      },
      historyList:[],
      total2:0,
      // 表单参数
      form: {},
      open4:false,
      loading2:true,
      // 表单校验
      rules: {
        corpName:[{
            required: true,
            message: "客户不能为空",
            trigger: "blur",
          },],
        manMonth:[{
            required: true,
            validator: price,
            trigger: ["blur","change"]
          },],
        actualMonth:[{
            required: true,
            message: "回款月份不能为空",
            trigger: ["blur","change"]
          },],
        actualMoney:[{
            required: true,
            validator: price,
            trigger: "blur"
          },],
        receivedPayment:[{
            required: true,
            validator: price,
            trigger: ["blur","change"]
          },],
        lastmonthWages:[{
            required: true,
            validator: price,
            trigger: ["blur","change"]
        },],
        remarks:[{
            required: true,
            message: "备注不能为空",
            trigger: ["blur","change"]
          },],
        contactSituation:[{
            required: true,
            message: "沟通情况不能为空",
            trigger: ["blur","change"]
          },],
        collectionStatus:[{
            required: true,
            message: "回款状态不能为空",
            trigger: ["blur","change"]
          },],
        collectionTime:[{
            required: true,
            message: "预回款时间不能为空",
            trigger: ["blur","change"]
          },],
        corpCode:[{
            required: true,
            message: "客户名称不能为空",
            trigger: ["blur","change"]
          },],
        backMonth:[{
            required: true,
            message: "预回款月份不能为空",
            trigger: ["blur","change"]
          },],
        currentArrears:[{
            required: true,
            validator: price,
            trigger: ["blur","change"]
          },],
      }
    };
  },
  created() {
    this.queryParams.addTime = this.time()
    this.getList();
    this.getList2();
    this.getDicts("collectionStatus").then(response => {
      this.contactSituationlist = response.data;
    });
  },
  methods: {
    getSummaries(param) {
      const { columns, data } = param
      const len = columns.length
      const sums = []
      let values = []
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = '总计'
          return
          //如果是最后一列，索引为列数-1，则显示计算总和
        }
        if(column.property === "weihuikuan"){
          values = data.map(item => Number(item.actualMoney-item.receivedPayment))
        }else if(column.property === "dengyue"){
          values = data.map(item => Number(item.actualMoney-item.lastmonthWages))
        }else{
          values = data.map(item => Number(item[column.property]))
        }
        if(column.property === "weihuikuan" || column.property === "dengyue" || column.property === "actualMoney" || column.property === "lastmonthWages"|| column.property === "receivedPayment"){
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr)
            if (!isNaN(value)) {
              return prev + curr
            } else {
              return prev
            }
          }, 0)
          sums[index] = sums[index].toFixed(2)
        }else{
          sums[index] = ''
        }
      })
      return sums
    },

    customerSexFormat(row, column) {
      return this.selectDictLabel(this.contactSituationlist, row.collectionStatus);
    },
    time(){
      var nowDate = new Date();
      var cloneNowDate = new Date();
      var fullYear = nowDate.getFullYear();
      var month = nowDate.getMonth() + 1; // getMonth 方法返回 0-11，代表1-12月
      var endOfMonth = new Date(fullYear, month, 0).getDate(); // 获取本月最后一天
      function getFullDate(targetDate) {
              var D, y, m, d;
              if (targetDate) {
                  D = new Date(targetDate);
                  y = D.getFullYear();
                  m = D.getMonth() + 1;
                  d = D.getDate();
              } else {
                  y = fullYear;
                  m = month;
                  d = date;
              }
              m = m > 9 ? m : '0' + m;
              d = d > 9 ? d : '0' + d;
              return y + '-' + m + '-' + d;
          };
          //当月第一天
          return getFullDate(cloneNowDate.setDate(1))
    },
    /** 查询每月公司其他住处费用列表 */
    getList() {
      if(this.queryParams.addTime!==this.time()){
        this.kaiguan=false
      }else{
        this.kaiguan=true
      }
      this.loading = true;
      listAncecompany(this.queryParams).then(response => {
        this.AncecompanyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    getList2() {
      this.loading2 = true;
      listMonitoring(this.queryParams2).then(response => {
        this.Monitoringlist = response.rows;
        this.total2 = response.total;
        this.loading2 = false;
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
        contactId:null,
        corpName:null,
        corpCode:null,
        actualMoney:null,
        actualMonth:null,
        collectionStatus:null,
        manMonth:null,
        materiaCost:null,
        receivedPayment:null,
        lastmonthWages:null,
        contactSituation:null,
        collectionTime:null,
        remarks:null,
        backMonth:null,
        currentArrears:null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    handleAdd2(){
      this.reset();
      this.getCorpName()
      this.open4 = true;
      this.title = "添加重点监测客户";
    },
    handleSee(row){
      gethistory({corpCode:row.corpCode,addTime:this.queryParams.addTime}).then(res=>{
        this.open3=true
        this.historyList = res.data
        this.title = "查看历史修改"
      })
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
    handleSelectionChange2(selection){
      this.ids = selection.map(item => item.id)
      this.single2 = selection.length!==1
    },
    getCorpName(){
      corpNames().then(res=>{
        this.corpnamelists=res
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getCorpName()
      this.open2 = true;
      this.title = "添加跟踪客户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      getAncecompany(row.contactId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改每月公司其他出处费用";
      });
    },
    handleUpdate2(row) {
      this.reset();
      getMonitoring(row.id).then(response => {
        this.form = response.data;
        this.form.corpName = row.corpName
        this.open4 = true;
        this.title = "修改重点监测公司";
      });
    },
    submitForm1:debounce(function(){
      this.submitForm1s()
    },500),
    submitForm1s(){
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMonitoring(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open4 = false;
              this.getList2();
            });
          } else {
            addMonitoring(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open4 = false;
              this.getList2();
            });
          }
        }
      });
    },
    /** 提交按钮 */
    submitForm:debounce(function(){
      this.submitForm2()
    },500),
    submitForm2() {
      let formdata = new FormData()
      formdata.append("zm",JSON.stringify({corpCodelist:this.form.corpName}))
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.title=="修改每月公司其他出处费用"){
            updateAncecompany(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAncecompany(formdata).then(response => {
              this.msgSuccess("新增成功");
              this.open2 = false;
              this.getList();
            });
          }
        }
      });
    },
    handleDelete2() {
      const ids = this.ids
      this.$confirm('是否确认删除重点监测公司编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return  delMonitorid(ids);
        }).then(() => {
          this.getList2();
          this.msgSuccess("删除成功");
        })
    },
    /** 删除按钮操作 */
    handleDelete() {
      const ids = this.ids
      this.$confirm('是否确认删除每月公司其他出处费用编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAncecompany(ids);
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
          return exportAncecompany(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    OutRowClassName({row, rowIndex}) {
      if(row.collectionStatus==1){
        return 'warning-row';
      }else if(row.collectionStatus==2){
        return 'blue-row';
      }else if(row.collectionStatus==3){
        return 'suir-row';
      }else if(row.collectionStatus==4){
        return 'red-row';
      }
      return '';
    },
  }
};
</script>
<style scoped>
  >>>.el-table .warning-row {
      color: green;
  }
  >>>.el-table .blue-row {
      color: blue;
  }
  >>>.el-table .suir-row {
      color: #ff000070;
  }
  >>>.el-table .red-row {
      color: red;
  }
  .xiugai:hover{
    cursor:pointer
  }
</style>
