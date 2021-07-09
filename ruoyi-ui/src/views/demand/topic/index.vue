<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" >
      <el-form-item label="需求名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入需求名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="topicList" :row-class-name="tableRowClassName">
      <el-table-column label="需求名字"  prop="projectName" />
      <el-table-column label="面试题"  prop="auditedName" >
        <template slot-scope="scope">
            <el-button
              v-if="scope.row.topicPath !== '' "
              size="small"
              type="text"
              @click="handleUpdate(scope.row)"
            >预览面试题</el-button>
        </template>
      </el-table-column>
      <el-table-column label="备注"  prop="topicBz" />
      <el-table-column label="面试题状态"  prop="topicStatu" :formatter="TopicstatuFormat"/>
      <el-table-column label="通过拒绝备注"  prop="topicReason" />
      <el-table-column label="添加时间"  prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>
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
    <el-dialog :visible.sync="open4" width="70%">
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
import { listTopic, getTopic, delTopic, addTopic, updateTopic, exportTopic } from "@/api/demand/topic";
import {checkImg} from "@/utils/ruoyi.js"
export default {
  name: "Topic",
  data() {
    return {
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
      // 面试题表格数据
      topicList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      src:"",
      open4:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectName: null,
      },
      // 表单参数
      form: {},
      topicstatu:[],
      dialogImageUrl:"",
      dialogVisible:false,

      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("topic_statu").then(response => {
      this.topicstatu = response.data;
    });
  },
  methods: {
    tableRowClassName({row, rowIndex}) {
          if (row.topicStatu === 2) {
            return 'warning-row';
          } else if (row.topicStatu === 1) {
            return 'success-row';
          }
          return '';
     },
    /** 查询面试题列表 */
    getList() {
      this.loading = true;
      listTopic(this.queryParams).then(response => {
        this.topicList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    TopicstatuFormat(row, column) {
      return this.selectDictLabel(this.topicstatu, row.topicStatu);
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },

    /** 修改按钮操作 */
    handleUpdate(row,ind) {
      this.dialogImageUrl = ""
      let srcs = process.env.VUE_APP_BASE_API
        if(checkImg(row.topicPath)){
          ind = 2
        }else{
          ind = 1
        }
        if(ind==1){
          this.open4 = true
          this.title = '预览简历模板'
          this.src=`https://www.xdocin.com/xdoc?_func=form&_key=vdm5j3eitvebvmh2qgcuv4idry&_xdoc=${srcs+row.topicPath}`
        }else if(ind==2){
          this.dialogVisible=true
          this.title = "面试题图片"
          this.dialogImageUrl = srcs+row.topicPath
        }
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有面试题数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTopic(queryParams);
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
