<template>
  <div class="app-container">
    <div style="margin-bottom: 25px;">
      <el-radio-group style="margin-right: 20px;margin-bottom: 2px;" v-model="radio2" size="medium" @change="getList">
        <el-radio-button label="chengyuan" >待审核的面试题</el-radio-button>
        <el-radio-button label="meiri" >整理后的面试题</el-radio-button>
      </el-radio-group>
    </div>

    <div v-if="radio2=='chengyuan'">
      <el-form :model="queryParams" ref="queryForm" :inline="true">
        <el-form-item label="公司名称" prop="corpCode">
         <el-select filterable  v-model="queryParams.corpCode"  placeholder="请选择" size="small" clearable  @change="handleQuery">
           <el-option
               v-for="dict in corpnamelist"
               :key="dict.corpCode"
               :label="dict.corpName"
               :value="dict.corpCode"
             />
           </el-select>
        </el-form-item>
        <el-form-item label="面试题状态" prop="topicstatu">
         <el-select filterable  v-model="queryParams.topicstatu"  placeholder="请选择" size="small" clearable  @change="handleQuery">
           <el-option
               v-for="dict in topicstatu"
               :key="dict.dictValue"
               :label="dict.dictLabel"
               :value="dict.dictValue"
             />
           </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="small" @click="handleQuery">搜索</el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="loading" :data="topicList" :row-class-name="tableRowClassName" key="1">
        <el-table-column label="需求名称"  prop="projectName" />
        <el-table-column label="面试题" class-name="small-padding fixed-width">
          <template slot-scope="scope">
              <el-button
                v-if="scope.row.topicPath !== '' "
                size="small"
                type="text"
                @click="handlesee(scope.row)"
              >预览面试题</el-button>
            </template>
        </el-table-column>
        <el-table-column label="备注"  prop="topicBz" />
        <el-table-column label="面试题状态"  prop="topicStatu" :formatter="TopicstatuFormat"/>
        <el-table-column label="通过拒绝备注"  prop="topicReason" />
        <el-table-column label="添加人"  prop="topicNickname" />
        <el-table-column label="添加时间"  prop="addTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作"  prop="auditedName">
          <template slot-scope="scope">
              <el-button
                size="small"
                type="text"
                @click="handleUpdate(scope.row)"
              >审批</el-button>
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
    </div>
    <div v-else>
      <el-form :model="queryParams" ref="queryForm" :inline="true">
        <el-form-item label="面试题名称" prop="auditedName">
          <el-input
            v-model="queryParams.auditedName"
            placeholder="请输入面试题名称"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="公司名称" prop="corpCode">
         <el-select filterable  v-model="queryParams.corpCode"  placeholder="请选择" size="small" clearable  @change="handleQuery">
           <el-option
               v-for="dict in corpnamelist"
               :key="dict.corpCode"
               :label="dict.corpName"
               :value="dict.corpCode"
             />
           </el-select>
        </el-form-item>
        <el-form-item label="面试题状态" prop="corpCode">
         <el-select   v-model="queryParams.corpCode"  placeholder="请选择" size="small" clearable  @change="handleQuery">
           <el-option
               v-for="dict in topicstatu"
               :key="dict.dictValue"
               :label="dict.dictLabel"
               :value="dict.dictValue"
             />
           </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="small" @click="handleQuery">搜索</el-button>
        </el-form-item>
      </el-form>
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            icon="el-icon-plus"
            size="small"
            @click="handleAdd"
          >新增面试题</el-button>
        </el-col>
      </el-row>
      <el-table v-loading="loading" :data="auditeditorList" key="2">
        <el-table-column label="公司名称"  prop="corpName" />
        <el-table-column label="面试题名称"  prop="auditedName" />
        <el-table-column label="面试题" class-name="small-padding fixed-width">
          <template slot-scope="scope">
              <el-button
                v-if="scope.row.topicPath !== '' "
                size="small"
                type="text"
                @click="handlesee(scope.row)"
              >预览面试题</el-button>
            </template>
        </el-table-column>
        <el-table-column label="面试题备注"  prop="auditedBz" />
        <el-table-column label="添加时间"  prop="addTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <!-- <el-table-column label="操作"  class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="small"
              type="text"
              @click="handlebding(scope.row)"
            >绑定</el-button>
          </template>
        </el-table-column> -->
      </el-table>
      <pagination
        v-show="total2>0"
        :total="total2"
        :page.sync="queryParam.pageNum"
        :limit.sync="queryParam.pageSize"
        @pagination="getList"
      />
    </div>
    <el-dialog :title="title" :visible.sync="open2" width="500px" append-to-body >
      <el-form ref="form" :model="form" label-width="100px">
        <el-form-item label="需求名称">
          <span>{{form.projectName}}</span>
        </el-form-item>
        <el-form-item label="简历状态">
          <el-radio-group v-model="form.topicStatu">
              <el-radio :label="1">通过</el-radio>
              <el-radio :label="2">拒绝</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审批备注">
          <el-input v-model="form.topicReason" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="open2=false">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="open3" width="500px" append-to-body>
       <el-checkbox-group v-model="bangdlist">
         <el-col :span="24" v-for="city in xuqiulist" :key="city.demandId">
           <el-checkbox  :label="city.demandId" :key="city.demandId">{{city.projectName}}</el-checkbox>
         </el-col>
       </el-checkbox-group>
      <div slot="footer" class="dialog-footer" style="padding-top: 40px;">
        <el-button type="primary" @click="submitForm3">确 定</el-button>
        <el-button @click="open3=false">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改审核编辑后的面试题对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="forms" :rules="rules" label-width="100px">
        <el-form-item label="公司名称" prop="corpCode">
         <el-select filterable  v-model="forms.corpCode"  placeholder="请选择" size="small" ref="sous">
           <el-option
               v-for="dict in corpnamelist"
               :key="dict.corpCode"
               :label="dict.corpName"
               :value="dict.corpCode"
             />
           </el-select>
        </el-form-item>
        <el-form-item label="面试题" >
          <el-upload
            action="#"
            list-type="picture-card"
            :auto-upload="false"
            ref="file"
            :limit="1"
            :on-change="handleRemov"
            :on-exceed="handleExceed"
            >
            <!-- <div slot="tip" class="el-upload__tip">仅支持上传jpg/png文件</div> -->
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
        </el-form-item>
        <el-form-item label="面试题名称" prop="auditedName">
          <el-input v-model="forms.auditedName" placeholder="请输入面试题名称" />
        </el-form-item>
        <el-form-item label="面试题备注" prop="auditedBz">
          <el-input v-model="forms.auditedBz" placeholder="请输入面试题备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm2">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog  :visible.sync="open4" width="70%">
     <iframe
        :src="src"
        style="overflow: auto; position: absolute; top: 40px; right: 0; bottom: 0; left: 0; width: 100%; height:1000%; border: none;"
      ></iframe>
    </el-dialog>
    <el-dialog :visible.sync="dialogVisible" width="500px" >
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>

  </div>
</template>

<script>
import { listAuditeditor, delAuditeditor,getAuditeditor, addAuditeditor, updateAuditeditor,afterbinding,exportAuditeditor,corpName, listExtopiclist,addauditeditor,addaudbindingdemand  } from "@/api/demand/auditeditor";
import {checkImg} from "@/utils/ruoyi.js"
export default {
  name: "Auditeditor",
  data() {
    return {
      radio2:"chengyuan",
      // 遮罩层
      loading: true,
      disabled:false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      corpnamelist:[],
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 审核编辑后的面试题表格数据
      auditeditorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open3:false,
      open2:false,
      open4:false,
      topicstatu:[],
      src:"",
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        corpCode: null,
        topicstatu:null,
      },
      queryParam: {
        pageNum: 1,
        pageSize: 10,
        corpCode: null,
        auditedPath: null,
        auditedBz: null,
        topicUsername: null,
        addTime: null
      },
      // 表单参数
      form: {},
      props:{multiple:true,value:"demandId",label:"projectName"},
      forms:{},
      bangdlist:[],
      total2:0,
      topicList:[],
      // 需求列表
      xuqiulist:[],
      dialogImageUrl:"",
      dialogVisible:false,
      // 表单校验
      rules: {
        corpCode:[{
          required: true,
          message: "公司名称不能为空",
          trigger: ["blur", "change"]
        }, ],
        auditedName:[{
          required: true,
          message: "公司名称不能为空",
          trigger: ["blur", "change"]
        }, ],
      }
    };
  },
  created() {
    this.getList();
    this.getcorpName()
    this.getDicts("topic_statu").then(response=> {
      this.topicstatu = response.data;
    });
  },
  methods: {
    submitForm3(){
      if(this.bangdlist.length==0){
        this.msgError("绑定需求不能为空")
      }else{
        console.log(this.bangdlist)
        let form = new FormData()
        form.append("zm",JSON.stringify({list:this.bangdlist,auditedId:this.multiple}))
        addaudbindingdemand(form).then(res=>{
          this.msgSuccess("绑定成功")
          this.open3 = false
        })
      }
    },
    handleExceed(){
      this.msgError(`当前限制选择 1 个文件`);
    },
    handleRemov(value,filelist){
      this.single=value.raw;
    },
    handleRemove(file) {
        this.$refs.file.clearFiles()
        this.single = null
    },
    getcorpName(){
      corpName().then(res=>{
        this.corpnamelist=res
      });
    },
    handlebding(row){
      let form = new FormData()
      form.append("corpCode",row.corpCode)
      form.append("auditedId",row.auditedId)
      getAuditeditor(form).then(res=>{
        this.bangdlist = []
        if(res.data[0] == null){
          this.msgError("该客户无可选用需求")
        }else{
          this.xuqiulist = res.data
          afterbinding(form).then(res=>{
            res.data.forEach(item=>{
              this.bangdlist.push(item.demandId)
            })
            this.multiple = row.auditedId
          })
          this.open3 = true
          this.title = "绑定需求"
        }
      })
    },
    tableRowClassName({row, rowIndex}) {
        if (row.topicStatu === 2) {
          return 'warning-row';
        } else if (row.topicStatu === 1) {
          return 'success-row';
        }
        return '';
     },
    TopicstatuFormat(row, column) {
      return this.selectDictLabel(this.topicstatu, row.topicStatu);
    },
    /** 查询审核编辑后的面试题列表 */
    getList() {
      this.loading = true;
      if(this.radio2=="chengyuan"){
        listExtopiclist(this.queryParams).then(res=>{
          this.topicList = res.rows;
          this.total = res.total;
          this.loading = false;
        })
      }else{
        listAuditeditor(this.queryParam).then(response => {
          this.auditeditorList = response.rows;
          this.total2 = response.total;
          this.loading = false;
        });
      }
    },
    handlesee(row,ind){
      this.dialogImageUrl = ""
      let srcs = process.env.VUE_APP_BASE_API
        if(checkImg(row.topicPath)){
          ind = 2
        }else{
          ind = 1
        }
        if(ind==1){
          this.open4 = true
          this.src=`https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=${srcs+row.topicPath}`
        }else if(ind==2){
          this.dialogVisible=true
          this.dialogImageUrl = srcs+row.topicPath
        }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.single = null
      this.forms = {
        corpCode: null,
        corpName: null,
        auditedBz: null,
      };
      this.resetForm("form");
      if(this.$refs.file){
        this.handleRemove()
      }
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
      this.ids = selection.map(item => item.auditedId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加面试题";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.open2 = true
      this.title = "审批"
      this.form = JSON.parse(JSON.stringify(row))
    },
    /** 提交按钮 */
    submitForm() {
      updateAuditeditor(this.form).then(response => {
        this.msgSuccess("操作成功");
        this.open2 = false;
        this.getList();
      });
    },
    submitForm2() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if(this.single==null){
            this.msgError("文件不能为空")
          }else{
            let formdata = new FormData()
            this.forms.corpName = this.$refs.sous.selectedLabel
            formdata.append("zm",JSON.stringify({marAuditeditor:this.forms}))
            formdata.append("multipartFile",this.single)
            addAuditeditor(formdata).then(response => {
              this.msgSuccess("操作成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });

    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const auditedIds = row.auditedId || this.ids;
      this.$confirm('是否确认删除审核编辑后的面试题编号为"' + auditedIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAuditeditor(auditedIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有审核编辑后的面试题数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAuditeditor(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
<style scoped>
  >>>.el-table .success-row{
    color: green;
  }
  >>>.el-table .warning-row{
    color: red;
  }
</style>
