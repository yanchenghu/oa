<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="" prop="templateName">
        <el-input
          v-model="queryParams.templateName"
          placeholder="搜索"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery"></el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['resume:template:add']"
        >新增</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>



    <el-row>
      <el-col  :span="7" v-for="temp in templateList" style="margin-right: 10px;margin-bottom: 10px">
        <el-card shadow="hover" >
          <div style="display: flex;position: relative;font-size: 10px;line-height: 20px">
            <el-avatar style="background: rgb(1,128,255)"> {{temp.templateName}} </el-avatar>

            <div style="margin-left: 10px">
              <div>
                {{temp.templateName}}
              </div>
              <div>
                {{temp.company}}-{{temp.technicalDirection}}-{{temp.workingYears}}年-{{temp.name}}
              </div>
            </div >
            <div style="position: absolute;right: -15px;line-height: 50px;">
              <el-button
                type="primary"
                size="mini"
                icon="el-icon-preview"
                @click="preview(temp)"
                v-hasPermi="['resume:template:preview']"
              >预览</el-button>
              <el-button
                size="mini"
                type="success"
                @click="download(temp)"
                v-hasPermi="['resume:template:download']"
              >下载</el-button>
            </div>

          </div>
        </el-card>
      </el-col>
    </el-row>


    <el-dialog title="预览" :visible.sync="open" width="70%"  >
      <iframe
        :src="'https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=http://localhost/dev-api/'+drees"
        style="overflow: auto; position: absolute; top: 40px; right: 0; bottom: 0; left: 0; width: 100%; height:1000%; border: none;"
      ></iframe>
    </el-dialog>

    <!-- 添加或修改简历模板对话框 -->
    <el-dialog :title="title" :visible.sync="openn" width="500px" append-to-body>
      <el-form ref="form" :model="form"  label-width="80px">
        <el-form-item label="模板名称" prop="templateName">
          <el-input v-model="form.templateName" placeholder="请输入模板名称" />
        </el-form-item>
        <el-form-item label="模板文件">
          <el-upload
            class="upload-demo"
            ref="upload"
            action="#"
            :on-change="handleRemove"
            :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          </el-upload>
        </el-form-item>

        <el-form-item label="公司" prop="company">
          <el-input v-model="form.company" placeholder="请输入公司" />
        </el-form-item>
        <el-form-item label="技术方向" prop="technicalDirection">
          <el-input v-model="form.technicalDirection" placeholder="请输入技术方向" />
        </el-form-item>
        <el-form-item label="工作年限" prop="workingYears">
          <el-input v-model="form.workingYears" placeholder="请输入工作年限" />
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
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
        templateOrder: [
          { required: true, message: "模板顺序不能为空", trigger: "blur" }
        ],
        templateStatus: [
          { required: true, message: "模板状态不能为空", trigger: "blur" }
        ],
        addtime: [
          { required: true, message: "添加时间不能为空", trigger: "blur" }
        ],
        addpeople: [
          { required: true, message: "添加人不能为空", trigger: "blur" }
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
      this.open=true;
      this.drees = value.templateFile
      this.title="简历预览"
    },

    handleRemove(value){
      this.form.templateFile=value.raw;
    },
    // 下载简历
    download(val){
      window.open(
      `http://localhost/dev-api/${val.templateFile}`)
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
      this.open = false;
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
      let form = this.form
      let formdata = new FormData()
      formdata.append("templateFile",form.templateFile)
      formdata.append("templateName",form.templateName)
      formdata.append("company",form.company)
      formdata.append("technicalDirection",form.technicalDirection)
      formdata.append("workingYears",form.workingYears)
      formdata.append("name",form.name)
      addTemplate(formdata).then(response => {
        this.msgSuccess("新增成功");
        this.open = false;
        this.getList();
      });


      // this.$refs["form"].validate(valid => {
      //   if (valid) {
      //     if (this.form.templateId != null) {
      //       this.msgSuccess("新增失败");
      //       this.open = false;
      //       this.getList();
      //     } else {
      //       let formdata = new FormData()
      //       formdata.append("templateFile",form.templateFile)
      //
      //
      //     }
      //   }
      // });
    },

    // 模板状态修改
    handleStatusChange(row) {
      console.log(row.templateStatus)
      let text = row.templateStatus === "0" ? "启用" : "停用";
      this.$confirm('确认要"' + text + '""' + row.templateName + '"模板吗?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return changeTemplateStatus(row.templateId, row.templateStatus);
      }).then(() => {
        this.msgSuccess(text + "成功");
      }).catch(function() {
        row.templateStatus = row.templateStatus === "0" ? "1" : "0";
      });
    },

  }
};
</script>
<style>

</style>
