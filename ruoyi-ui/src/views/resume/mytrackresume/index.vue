<template>
  <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" :inline="true"  style="width:80% ;" label-width="68px" @submit.native.prevent>
        <el-form-item label="名字" prop="companyName">
          <el-input
            v-model="queryParams.companyName"
            placeholder="请输入名字"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="电话" prop="customerTel">
          <el-input
            v-model="queryParams.customerTel"
            placeholder="请输入电话"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item >
          <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">查询</el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="loading" :data="tablelist" >
        <el-table-column label="姓名" align="center" prop="customerName" />
        <el-table-column label="电话"  prop="customerTel" width="100"/>
        <el-table-column label="添加时间" align="center" prop="addTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="释放时间" align="center" prop="editTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.editTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
            >跟踪</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getlist"
      />
      <el-dialog :title="title" :visible.sync="open" width="40%" append-to-body>
        <el-form :model="form" :rules="rules" ref="form" label-position="right" label-width="80px">
          <el-form-item label="简历状态">
            <el-select v-model="form.updateStatic" placeholder="请选择简历状态">
              <el-option label="跟进中" :value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="跟进情况" prop="memoDetail">
              <el-input v-model="form.memoDetail" type="textarea" placeholder="请输入内容" style="width: 80%;"></el-input>
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
  import{gettemplist,genzongbut}from'@/api/resume/mytrckresume.js'
  export default {
    name: "Yxdemand",
    data() {
      return {
        // 总条数
        total:0,
        // 数据列表
        tablelist:[],
        // 遮盖层
        loading:true,
        // 查询参数
        queryParams:{
          pageNum: 1,
          pageSize: 10,
          customerName:null,
          customerTel:null,
        },
        // 跟踪参数
        form:{},
        // 跟踪
        open:false,
        // 标题
        title:"",
        rules:{
          memoDetail: [
          { required: true, message: "跟踪情况不能为空", trigger: "blur" }
        ],
        },
      }
    },
    created() {
      this.getlist()
    },
    methods:{
      getlist(){
        this.loading = true;
        gettemplist(this.queryParams).then(res=>{
           this.tablelist = res.rows
           this.total = res.total;
           this.loading=false
        })
      },
      handleQuery(){
        this.queryParams.pageNum = 1;
        this.getlist()
      },
      handleUpdate(row){
        this.open=true
        this.title = "简历跟踪"
        this.form.contactCustomercode = row.customerCode
        this.form.updateStatic = 2
      },
      submitForm(){
        this.$refs["form"].validate(valid => {
          if (valid) {
            genzongbut(this.form).then(res=>{
              this.msgSuccess("操作成功");
              this.open = false;
              this.getlist();
            })
          }
        });
      },
      cancel(){
         this.open = false;
         this.form = {}
      },

    }
  }
</script>

<style>
</style>
