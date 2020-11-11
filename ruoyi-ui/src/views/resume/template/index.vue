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
                {{temp.namingFormat}}
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
                @click="download"
                v-hasPermi="['resume:template:download']"
              >下载</el-button>
            </div>

          </div>


        </el-card>
      </el-col>
    </el-row>


    <el-dialog title="预览" :visible.sync="open" width="70%"  :before-close='closeDialog'>
      <iframe
        src='https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=https://oa.zhuyanhr.com/shzqoa/custTemp/demand20072003485269172/%E7%8E%8B%E8%82%96%E4%B8%9C.doc'
        style="overflow: auto; position: absolute; top: 0; right: 0; bottom: 0; left: 0; width: 100%; height:1000%"
      ></iframe>
    </el-dialog>





  </div>
</template>

<script>
import { listTemplate, changeTemplateStatus,  } from "@/api/resume/template/template";

export default {
  name: "Template",
  data() {
    return {
      // 是否显示弹出层
      open: false,
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
      this.title=value.templateFile
    },

    // 下载简历
    download(){
      this.open=true;
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
