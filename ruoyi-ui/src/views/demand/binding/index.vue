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
    </el-form>

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="resetQuery"></right-toolbar>
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
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">绑定</el-button>
          </p>
          <p>
            <el-button size="mini" type="text" @click="see(scope.row)" v-hasPermi="['demand:follow:query']">查看</el-button>
          </p>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />


    <el-dialog :title="title" :visible.sync="open" width="40%" append-to-body>
      <el-input v-model="searchmsg" placeholder="请输入" clearable size="small" @keyup.enter.native="search" style="width: 170px;"/>
      <el-button type="cyan" icon="el-icon-search" size="mini" @click="search">查询</el-button>
      <p></p>
      <el-alert
          title="最多可绑定3条简历"
          type="warning"
          :closable="false">
      </el-alert>
      <p></p>
      <el-checkbox-group v-model="corpnamelist" v-loading="loading2">
           <el-checkbox v-for="city in templist" :label="city.customer_code" :key="city.customer_code" style="width:155px;">{{city.customer_name}}-{{city.customer_tel}}</el-checkbox>
      </el-checkbox-group>
      <div v-show="templist.length==0 && form.length!==0" style="text-align: center;
      ">暂无该信息</div>
      <div v-show="templist.length==0 && form.length==0" style="text-align: center; ">暂无简历信息,先去抢占几份去吧</div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
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
  } from "@/api/demand/binding";
import { treeselect } from "@/api/system/dept";
  export default {
    name: "Follow",
    data() {
      return {
        // 搜索信息
        searchmsg:"",
        // 简历模板列表
        corpnamelist:[],
        // 简历模板列表
        templist:[],
        // 下包商列表
        deptOptions:[],
        // 客户级别
        customerleve:[],
        // 技术方向字典
        professionIdoptions: [],
        // 简历学历字典
        customerSpecialitiesoptions: [],
        // 简历地区字典
        intentionareaOptions:[],
        // 遮罩层
        loading: true,
        loading2:false,
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
        },
        // 表单参数
        form: [],
        timer:null,
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
    watch:{
        "corpnamelist.length":{
            handler(newValue, oldValue) {
              if (newValue>3) {
                  this.msgError("最多能绑定3条简历")
                  this.corpnamelist.pop()
                  console.log(this.corpnamelist)
              }
            }
        }
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
      // 查看
      see(row){
        this.$router.push({ path:'/follow/particulars',query:{row:row.demandId,ident:2}})
      },
      // 绑定
      handleUpdate(row){
        addFollow().then(res=>{
          this.corpnamelist=[]
          this.form=res.data
          this.templist=res.data
          this.form.id=row.demandId
          this.open = true;
          this.title = "绑定";
        })
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.corpnamelist=[]
      },
      // 搜索简历
      search(){
        let that = this
        this.loading2=true
        if(this.searchmsg==""){
        setTimeout(function(){
          that.templist=that.form
          that.loading2=false
        },1000)
        }else{
          clearInterval(this.timer)
          this.timer=setTimeout(function(){
            var data= that.form.filter(item=>{
              return item.customer_name == that.searchmsg || item.customer_tel==that.searchmsg
             })
            that.templist = data
            that.loading2=false
          },1000)
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
    // 确定按钮
    submitForm(){
      if(this.corpnamelist.length>3){
          this.msgError("最多绑定3条简历")
      }else{
        let zm={
          list:this.corpnamelist,
          demandId:this.form.id
        }
        zm = JSON.stringify(zm)
        let form= new FormData()
        form.append("zm",zm)
        delFollow(form).then(res=>{
          this.msgSuccess("绑定成功")
          this.open=false
        })
      }
      },
    }
  };
</script>
<style scoped>
  >>>.el-table__row  td {
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
