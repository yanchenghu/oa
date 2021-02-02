<template>
  <div class="app-containe ">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px" @submit.native.prevent>
      <el-form-item label="" prop="templateName">
        <el-input
          v-model="queryParams.templateName"
          placeholder="搜索"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search"  @click="handleQuery">查询</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"

          @click="handleAdd"
        >新增</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <ul class="ul">
      <li v-for="temp in templateList" class="li">
           <el-card shadow="hover">
             <el-avatar style="background: rgb(1,128,255);margin-bottom: 10px;"> {{temp.templateName}} </el-avatar>
              <div>
                <span>{{temp.templateName}}</span>
                <p class="time">{{temp.templateNominate}}</p>
              </div>
              <el-button
                type="primary"
                size="mini"
                icon="el-icon-preview"
                @click="preview(temp)"
              >预览</el-button>
              <el-button
                size="mini"
                type="success"
                @click="download(temp)"
              >下载</el-button>
           </el-card>
      </li>
    </ul>
    <el-dialog title="预览" :visible.sync="open" width="70%"  >
      <iframe
        :src="'https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc='+drees"
        style="overflow: auto; position: absolute; top: 40px; right: 0; bottom: 0; left: 0; width: 100%; height:1000%; border: none;"
      ></iframe>
    </el-dialog>
    <!-- 添加或修改简历模板对话框 -->
    <el-dialog :title="title" :visible.sync="openn" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules"  label-width="80px" >
        <el-form-item label="模板名称" prop="templateName">
          <el-input v-model="form.templateName" placeholder="请输入模板名称" />
        </el-form-item>
        <el-form-item label="模板文件">
          <el-upload
            class="upload-demo"
            ref="upload"
            action="#"
            :on-change="handleRemove"
            :auto-upload="false"
            accept=".docx,.doc,.pdf"
            :limit="1"
            :on-exceed="handleExceed"
            >
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          </el-upload>
        </el-form-item>

        <el-form-item label="命名格式" prop="templateNominate">
          <el-input v-model="form.templateNominate" placeholder="请输入模板格式" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listTemplate, changeTemplateStatus,addTemplate} from "@/api/resume/template/template";

export default {
  name: "Template",
  data() {
    return {
      // 简历地址
      drees:"",
      // 是否显示弹出层
      open: false,
      openn: false,
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
      // 状态数据字典
      statusOptions: [],
      // 总条数
      total: 0,
      // 简历模板表格数据
      templateList: [],
      // 弹出层标题
      title: "",

      // 查询参数
      queryParams: {
        templateName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        templateName: [
          { required: true, message: "模板名称不能为空", trigger: "blur" }
        ],
        templateNominate: [
          { required: true, message: "命名格式不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    // 预览简历
    preview(value){
      if(value.templateFile!==null){
        this.open=true;
        this.drees = process.env.VUE_APP_BASE_API+value.templateFile
        this.title="简历预览"
      }else{
        this.msgError("该简历暂无原版")
      }
    },

    handleRemove(value){
      this.form.templateFile=value.raw;
    },
    // 下载简历
    download(val){
      if(val.templateFile!==null){
        let srcs = process.env.VUE_APP_BASE_API+val.templateFile
        window.open(srcs, '_blank');
      }else{
        this.msgError("该简历暂无原版")
      }

    },

    /** 查询简历模板列表 */
    getList() {
      this.loading = true;
      listTemplate(this.queryParams).then(response => {
        this.templateList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.openn = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        templateId: null,
        templateName: null,
        templateFile: null,
        templatePrc: null,
        templateOrder: null,
        templateStatus: "0",
        addtime: null,
        addpeople: null,
        invalidtime: null,
        invalidpeople: null,
        namingFormat: null
      };
      this.resetForm("form");
      this.form.templateFile=null
      if(this.$refs.upload !==undefined){
        this.$refs.upload.clearFiles()
      }

    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.openn = true;
      this.title = "添加简历";
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

    /** 提交按钮 */
    submitForm() {
      if(this.form.templateFile==null){
        this.msgError("文件不能为空")
      }else{
        this.$refs["form"].validate(valid => {
        if (valid) {
         let form = this.form
         let formdata = new FormData()
         formdata.append("templateFile",form.templateFile)
         formdata.append("templateNominate",form.templateNominate)
         formdata.append("templateName",form.templateName)
         addTemplate(formdata).then(response => {
           this.msgSuccess("新增成功");
           this.openn = false;
           this.getList();
         });
        }
      });
      }
    },
    handleExceed() {
      this.msgError(`当前限制选择 1 个文件`);
    },
    // 模板状态修改
    // handleStatusChange(row) {
    //   console.log(row.templateStatus)
    //   let text = row.templateStatus === "0" ? "启用" : "停用";
    //   this.$confirm('确认要"' + text + '""' + row.templateName + '"模板吗?', "警告", {
    //     confirmButtonText: "确定",
    //     cancelButtonText: "取消",
    //     type: "warning"
    //   }).then(function() {
    //     return changeTemplateStatus(row.templateId, row.templateStatus);
    //   }).then(() => {
    //     this.msgSuccess(text + "成功");
    //   }).catch(function() {
    //     row.templateStatus = row.templateStatus === "0" ? "1" : "0";
    //   });
    // },

  }
};
</script>
<style scoped>
  .time {
    font-size: 13px;
    color: #999;
  }
  .ul{
    padding: 0;
    margin: 0;
    list-style: none;
    display: flex;
    flex-wrap: wrap;
  }
  .li{
    width: 250px;
    margin-right: 20px;
    margin-top: 20px;
    text-align: center;
  }
  .app-containe{
    padding: 15px;
  }

</style>
