<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="70px" class="form">
        <el-form-item label="需求名称" prop="projectName">
          <el-input v-model="queryParams.projectName" placeholder="请输入需求名称" clearable size="small" @keyup.enter.native="handleQuery" style="width: 150px;"/>
        </el-form-item>

          <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery" style="margin:3px 10px 0 -10px">查询</el-button>

        <el-form-item label="技术方向" prop="technologyDirection">
          <el-select v-model="queryParams.technologyDirection" clearable placeholder="请选择" size="small" @change="handleQuery">
            <el-option
                v-for="dict in professionIdoptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
          </el-select>
        </el-form-item>
        <el-form-item label="技术级别" prop="demandYears">
          <el-select v-model="queryParams.demandYears" clearable placeholder="请选择" size="small" @change="handleQuery">
            <el-option label="中级" value="1"/>
            <el-option label="高级" value="2"/>
          </el-select>
        </el-form-item>
        <el-form-item label="项目地点" prop="projectLocation">
          <el-select v-model="queryParams.projectLocation" placeholder="请选择" clearable size="small" @change="handleQuery">
            <el-option
                v-for="dict in intentionareaOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
          </el-select>
        </el-form-item>
        <el-form-item label="客户级别" prop="importantLevel">
          <el-select  v-model="queryParams.importantLevel"  size="small" @change="handleQuery">
            <el-option
                v-for="dict,index in customerleve"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="parseInt(dict.dictValue)"
              />
            </el-select>
        </el-form-item>
        <el-form-item label="需求状态" prop="state">
          <el-select  v-model="queryParams.state"  size="small" @change="handleQuery">
            <el-option label="启用中" :value="0" />
            <el-option label="禁用中" :value="1"/>
            </el-select>
        </el-form-item>
        <el-button
            type="cyan"
            size="mini"
            @click="handleAdd"
            style="position: absolute;right: 0;margin-right: 25px"
          >新建需求</el-button>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" border :data="followList" size="small" >
      <el-table-column label="需求名称" align="left" prop="projectName" />
      <el-table-column label="地址/技术方向" align="left">
        <template slot-scope="scope">
          <span>{{intentionareaFormat(scope.row)}} / {{professionIdopFormat(scope.row)}}</span>
        </template>
      </el-table-column>
      <el-table-column label="进度" align="left"  width="130">
        <template slot-scope="scope">
          <div>需求人数:{{scope.row.demandNumber}}</div>
          <div>目标人数:{{scope.row.targetNumber}}</div>
          <div>入项人数:{{scope.row.coopnature }}</div>
          <div>面试通过人数:{{scope.row.chsiFlag}}</div>
          <div>已提交简历数:{{scope.row.ifLook}}</div>
        </template>
      </el-table-column>
      <el-table-column label="客户级别" align="left" prop="importantLevel" :formatter="customerleveFormat" width="90"/>
      <el-table-column label="学历要求" align="left" prop="education" :formatter="customerFormat"/>
      <el-table-column label="具体要求" align="left" width="500">
      <template slot-scope="scope">
          <p v-html='scope.row.specificrequiRement'></p>
      </template>
      </el-table-column>
      <el-table-column label="备注" align="left" prop="attention" />
      <el-table-column label="技术要求" align="left" prop="demandYears">
        <template slot-scope="scope">
          <span>{{scope.row.demandYears==1?"中级":"高级"}}</span>
        </template>
      </el-table-column>
      <el-table-column label="年限" align="left" prop="directWorklife" width="50"/>
      <el-table-column label="录入人姓名" align="left" prop="operUsername" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="scope">
          <p>
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['demand:follow:edit']">修改</el-button>
          </p>
          <p>
            <el-button size="mini" type="text" @click="handleDelete(scope.row)" v-hasPermi="['demand:follow:query']">查看</el-button>
          </p>
          <p>
            <el-switch v-model="scope.row.state" :active-value="0" :inactive-value="1" inactive-color="#ff4949" @change="handleStatusChange(scope.row)"></el-switch>
          </p>

        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改需求对话框 -->
    <!-- 新建需求 -->
    <el-dialog :title="title" :visible.sync="open" width="65%" append-to-body>
      <el-form ref="forms" :inline="true" :model="form"  :rules="rules" label-width="100px" >
        <el-form-item label="选择客户" prop="corpCode">
          <el-select v-model="form.corpCode" placeholder="请选择" size="small">
            <el-option
                v-for="dict in corpnamelist"
                :key="dict.corpCode"
                :label="dict.corpName"
                :value="dict.corpCode"
              />
            </el-select>
        </el-form-item>
        <el-form-item label="客户级别" prop="importantLevel">
        <el-select  v-model="form.importantLevel"  size="small">
          <el-option
              v-for="dict,index in customerleve"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="需求名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入需求名称" size="small"  @blur="findname(form.projectName)"/>
        </el-form-item>
        <span v-if="msg==1" style="color: green;line-height: 40px;position: absolute;"><i class="el-icon-circle-check"></i></span>
        <el-form-item label="需求人数" prop="demandNumber">
          <el-input v-model.number="form.demandNumber" placeholder="请输入需求人数" size="small"/>
        </el-form-item>
        <el-form-item label="目标人数" prop="targetNumber">
          <el-input v-model.number="form.targetNumber" placeholder="请输入目标人数" size="small"/>
        </el-form-item>
        <el-form-item label="技术方向" prop="technologyDirection">
          <el-select v-model="form.technologyDirection" placeholder="请选择" size="small">
            <el-option
                v-for="dict in professionIdoptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
          </el-select>
        </el-form-item>

        <el-form-item label="技术级别" prop="demandYears">
          <el-select v-model="form.demandYears" placeholder="请选择" size="small">
            <el-option label="中级" value="1"/>
            <el-option label="高级" value="2"/>
          </el-select>
        </el-form-item>
        <el-form-item label="年限要求" prop="directWorklife">
          <el-select v-model="form.directWorklife" placeholder="请选择" size="small">
            <el-option v-for="dict in 10" :key="dict" :label="dict+'年'" :value="dict"/>
          </el-select>
        </el-form-item>
        <el-form-item label="语言要求" prop="langue">
          <el-select v-model="form.langue" placeholder="请选择" size="small">
           <el-option label="无" :value="0"/>
           <el-option label="日语" :value="1"/>
           <el-option label="英语" :value="2"/>
          </el-select>
        </el-form-item>
        <el-form-item label="学历要求" prop="education">
          <el-select v-model="form.education" placeholder="请选择" size="small">
            <el-option
                v-for="dict in customerSpecialitiesoptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
          </el-select>
        </el-form-item>
        <el-form-item label="是否可查" prop="chsiFlag">
          <el-radio-group v-model="form.chsiFlag" size="small">
              <el-radio :label="1">是</el-radio>
              <el-radio :label="2">否</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="薪资范围" required>
          <el-col :span="11">
            <el-form-item prop="minSalary">
                <el-input v-model="form.minSalary" placeholder="最小薪资"  size="small" style="width: 85px;"/>
            </el-form-item>
          </el-col>
          <el-col :span="2">-</el-col>
          <el-col :span="11">
            <el-form-item prop="maxSalary">
              <el-input v-model="form.maxSalary" placeholder="最大薪资"  size="small" style="width: 85px;"/>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="工作地点" prop="projectLocation">
          <el-select v-model="form.projectLocation" placeholder="请选择" size="small">
            <el-option
                v-for="dict in intentionareaOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
          </el-select>
        </el-form-item>
        <el-form-item label="详细地址" prop="tempName">
          <el-input v-model="form.address" placeholder="请输入" size="small"/>
        </el-form-item>
        <el-form-item label="简历模板" prop="tempId">
          <el-select v-model="form.tempId" placeholder="请选择" size="small">
            <el-option
                v-for="dict in templist"
                :key="dict.templateId"
                :label="dict.templateName"
                :value="dict.templateId"
              />
          </el-select>
        </el-form-item>
        <el-form-item label="面试官" prop="interviewer">
          <el-input v-model="form.interviewer" placeholder="请输入面试官" size="small"/>
        </el-form-item>
        <el-form-item label="联系方式" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入面试官联系方式" size="small"/>
        </el-form-item>
        <el-form-item label="面试地点" prop="specificLocation">
          <el-input v-model="form.specificLocation" placeholder="请输入面试地点" size="small"/>
        </el-form-item>
        <el-form-item label="可见下包商" prop="list">
          <el-cascader
          v-model="form.list"
          :show-all-levels="false"
          :options="deptOptions"
          :props="props"
          collapse-tags
          size="small"
          ref="cascad"
          clearable
          >
          </el-cascader>
        </el-form-item>
        <el-form-item label="兼职是否可见" prop="ifLook">
          <el-radio-group v-model="form.ifLook" size="small">
              <el-radio :label="1">是</el-radio>
              <el-radio :label="2">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <div class="div">
        <el-form-item label="需求图片" style="width: 100%;">
          <el-upload
            action="#"
            list-type="picture-card"
            :auto-upload="false"
            ref="file"
            :on-change="handleRemov"
            :limit="1"
            :file-list="filelist"
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
                    class="el-upload-list__item-preview"
                    @click="handlePictureCardPreview(file)"
                  >
                    <i class="el-icon-zoom-in"></i>
                  </span>
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
        </el-form-item>
        </div>
        <div class="div">
          <el-form-item label="岗位说明" prop="specificrequiRement" style="width:100%;">
            <editor  v-model="form.specificrequiRement" :min-height="192"/>
          </el-form-item>
        </div>
        <div class="div">
          <el-form-item label="特别备注" prop="attention" style="width:100%;">
            <el-input v-model="form.attention" type="textarea" placeholder="请输入内容" ></el-input>
          </el-form-item>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm(1)">立即保存</el-button>
        <el-button v-if="title=='添加需求'" @click="submitForm(2)">保存并继续</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>

<script>
  import {
    listFollow,
    getFollow,
    delFollow,
    addFollow,
    updateFollow,
    exportFollow,
    template,
    corpName,
    changeUserStatus,
    findnames,
    changeopenStatus
  } from "@/api/demand/follow";
import { treeselect } from "@/api/system/dept";
import Editor from '@/components/Editor';
import {debounce} from "@/utils/ruoyi.js"
  export default {
    name: "Follow",
    components: {
      Editor,
    },
    data() {
       var checkAge = (rule, value, callback) => {
          if (!value) {
            return callback(new Error('需求名称不能为空'));
          }
          setTimeout(() => {
            if (this.msg==2) {
              callback(new Error('该需求已存在'));
            } else {
              callback();
            }
          }, 1000);
         };
      return {
        dialogImageUrl: '',
        dialogVisible: false,
        disabled: false,
        // 公司列表
        corpnamelist:[],
        // 简历模板列表
        templist:[],
        // 文件
        filelist:[],
        // 下包商列表
        deptOptions:[],
        // 客户级别
        customerleve:[],
        // 级联选择设置
        props:{multiple: true,value:"id"},
        // 技术方向字典
        professionIdoptions: [],
        // 简历学历字典
        customerSpecialitiesoptions: [],
        // 简历地区字典
        intentionareaOptions:[],
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: "",
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 需求表格数据
        followList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          corpCode: null,
          projectName: null,
          technologyDirection: null,
          demandYears: null,
          demandLevel: null,
          education: null,
          demandNumber: null,
          state:0,
        },
        // 表单参数
        form: {
        },
        msg:null,
        // 表单校验
        rules: {
          corpCode: [{
            required: true,
            message: "客户不能为空",
            trigger: ["blur", "change"]
          }, ],
          projectName: [{
            validator: checkAge,
            trigger: 'blur',
          }, ],
          demandNumber: [{
            required: true,
            message: "需求人数不能为空",
            trigger: ["blur", "change"]
          }, { type: 'number',
              message: '必须为数字值'}],
          technologyDirection: [{
            required: true,
            message: "技术方向不能为空",
            trigger: ["blur", "change"]
          }, ],
          demandYears: [{
            required: true,
            message: "技术级别不能为空",
            trigger: ["blur", "change"]
          }, ],
          directWorklife: [{
            required: true,
            message: "年限不能为空",
            trigger: ["blur", "change"]
          }, ],
          langue: [{
            required: true,
            message: "语言不能为空",
            trigger: ["blur", "change"]
          }, ],
          education: [{
            required: true,
            message: "学历不能为空",
            trigger: ["blur", "change"]
          }, ],
          chsiFlag: [{
            required: true,
            message: "是否可查不能为空",
            trigger: ["blur", "change"]
          }, ],
          minSalary: [{
            required: true,
            message: "最小值不能为空",
            trigger: ["blur", "change"]
          }, ],
          maxSalary: [{
            required: true,
            message: "最大值不能为空",
            trigger: ["blur", "change"]
          }, ],
          targetNumber: [{
            required: true,
            message: "目标人数不能为空",
            trigger: ["blur", "change"]
          }, { type: 'number',
              message: '必须为数字值'}],
          projectLocation: [{
            required: true,
            message: "工作地点不能为空",
            trigger: ["blur", "change"]
          }, ],
          list: [{
            required: true,
            message: "下包商不能为空",
            trigger: ["blur", "change"]
          }, ],
          specificrequiRement: [{
            required: true,
            message: "岗位说明不能为空",
            trigger: ["blur", "change"]
          }, ],
          importantLevel:[{
            required: true,
            message: "客户级别不能为空",
            trigger: ["blur", "change"]
          }, ],
        }
      };
    },
    created() {
      this.getList();
      // 获取学历字典
      this.getDicts("per_customerinfo_education").then(response => {
        this.customerSpecialitiesoptions = response.data;
      });
      // 获取技术方向字典
      this.getDicts("per_customerinfo_professionid").then(response =>      {
        this.professionIdoptions = response.data;
      });
      // 获取城市字典
      this.getDicts("per_customerinfo_intentionarea").then(response => {
        this.intentionareaOptions = response.data;
      });
      // 客户级别
      this.getDicts("bus_customer_leve").then(response => {
        this.customerleve = response.data;
      });
    },
    methods: {
      // 客户级别
      customerleveFormat(row, column) {
        return this.selectDictLabel(this.customerleve, row.importantLevel);
      },
      // 城市
      intentionareaFormat(row, column) {
        return this.selectDictLabel(this.intentionareaOptions, row.projectLocation);
      },
      // 技术方向
      professionIdopFormat(row, column) {
        return this.selectDictLabel(this.professionIdoptions, row.technologyDirection);
      },
      // 学历
      customerFormat(row, column) {
        return this.selectDictLabel(this.customerSpecialitiesoptions, row.education);
      },

      /** 查询需求列表 */
      getList() {
        this.loading = true;
        listFollow(this.queryParams).then(response => {
          this.followList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      /** 查询部门下拉树结构 */
      getTreeselect() {
        treeselect().then(response => {
          this.deptOptions = response.data;
        });
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          corpCode: null,
          projectName: null,
          technologyDirection: null,
          demandYears: null,
          demandLevel: null,
          education: null,
          demandNumber: null,
          specificrequiRement: null,
          projectLocation: null,
          address: null,
          directWorklife: null,
          targetNumber: null,
          importantLevel: null,
          minSalary: null,
          maxSalary: null,
          technicalRequirement: null,
          langue: null,
          demandPic: null,
          interviewer: null,
          contactPhone: null,
          specificLocation: null,
          attention: null,
          ifLook: null,
          chsiFlag: null,
          coopnature: null,
          state: null,
          closeDate: null,
          tempId: null,
          tempName: null,
          addTime: null,
          operationuser: null,
          operUsername: null
        };
        this.resetForm("form");
        this.filelist=[]
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
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      gettemplate(){
        template().then(res=>{
          this.templist=res.rows
        });
      },
      getcorpName(){
        corpName().then(res=>{
          this.corpnamelist=res
        });
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.getTreeselect()
        this.getcorpName()
        this.gettemplate()
        this.form.list=[[100, 101,103],[100, 101,104],[100, 101,105],[100, 101,106],[100, 101,107]]
        this.open = true;
        this.title = "添加需求";
      },
      findname(name){
        if(name==""||null){
          this.msg=null
        }else{
          let form = new FormData()
          form.append("projectName",name)
          findnames(form).then(res=>{
            this.msg=res
          })
        }
      },
      handleRemov(value){
        this.single=value.raw;
      },
      handlePictureCardPreview(file){
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      handleExceed() {
        this.msgError(`当前限制选择 1 个文件`);
      },
      handleRemove(file) {
          this.$refs.file.clearFiles()
          this.filelist=[]
      },
      // 查找树结构父节点
      changeDetSelect(key, treeData) {
              let arr = []; // 在递归时操作的数组
              let returnArr = []; // 存放结果的数组
              let depth = 0; // 定义全局层级
              // 定义递归函数
              function childrenEach(childrenData, depthN) {
                for (var j = 0; j < childrenData.length; j++) {
                  depth = depthN; // 将执行的层级赋值 到 全局层级
                  arr[depthN] = childrenData[j].id;
                  if (childrenData[j].id== key) {
                    returnArr = arr.slice(0, depthN + 1); //将目前匹配的数组，截断并保存到结果数组，
                    break;
                  } else {
                    if (childrenData[j].children) {
                      depth++;
                      childrenEach(childrenData[j].children, depth);
                    }
                  }
                }
                return returnArr;
              }
              return childrenEach(treeData, depth);
            },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        this.getTreeselect()
        this.getcorpName()
        this.gettemplate()
        getFollow(row.demandId).then(response => {
          this.form = response.data.marDeman;
          if(this.form.demandPic){
            this.filelist=[{name:"",url:`${process.env.VUE_APP_BASE_API}${this.form.demandPic}`}]
          }
          let list =[]
          response.data.Signlis.forEach(item=>{
          list.push(this.changeDetSelect(item.deptId,this.deptOptions))
          })
          this.form.list=list
          this.open = true;
          this.title = "修改需求";
        });
      },

      // 用户状态修改
      handleStatusChange(row) {
        let text = row.state == 0 ? "启用" : "停用";
        this.$confirm('确认要"' + text + '""' + row.projectName + '"需求吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          let form = new FormData()
          form.append("demandId",row.demandId)
          if(text=="停用"){
            return changeUserStatus(form);
          }else{
            return changeopenStatus(form)
          }
        }).then(() => {
          this.msgSuccess(text + "成功");
          this.getList()
        }).catch(function() {
          row.state = row.state == 0 ? 1 : 0;
        });
      },

      /** 提交按钮 */
      submitForm(i){
        console.log(this.form.list)
        let list =[]
        this.form.list.forEach(item=>{
           list.push(item[2])
        })
        let zm = {
            marDemand:this.form,
            list:list
        }
        zm = JSON.stringify(zm)
        let formData = new FormData()
        formData.append("zm",zm)
        formData.append("demandPic",this.single)
        this.$refs["forms"].validate(valid => {
          if (valid) {
            if (this.form.demandId != null) {
              updateFollow(formData).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            }else{
              if(i==1){
                addFollow(formData).then(response => {
                  this.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                });
              }else{
                addFollow(formData).then(response => {
                  this.msgSuccess("新增成功");
                  this.handleAdd();
                });
              }

            }
          }else{
            this.msg=null
            this.msgError("请确认信息是否正确完整")
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        this.$router.push({ path:'/follow/particulars',query:{row:row.demandId,ident:1}})
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有需求数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportFollow(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(()=>{})
      }
    }
  };
</script>
<style scoped>
  >>>.el-table__row td {
    vertical-align: top;
    max-height: 500px;
    overflow: auto;
  }
  .form{
    width: 100%;
  }
  .form >>>.el-form-item__content {
    width: 170px;
  }
  .form >>>.el-input {
    width: 150px;
  }
  >>>.el-form-item__content{
    width: 199px;
  }
   .div >>>.el-form-item__content{
    width: 80%;
  }
</style>
