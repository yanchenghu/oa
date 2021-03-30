<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="70px" class="form">
        <el-form-item label="需求名称" prop="projectName">
          <el-input v-model="queryParams.projectName" placeholder="请输入需求名称" clearable size="small" @keyup.enter.native="handleQuery" style="width: 150px;"/>
        </el-form-item>

        <!-- <el-form-item label="公司名称" prop="corpCode">
          <el-select filterable  v-model="queryParams.corpCode"  placeholder="请选择" size="small" clearable  @change="handleQuery">
            <el-option
                v-for="dict in corpnamelists"
                :key="dict.corpCode"
                :label="dict.corpName"
                :value="dict.corpCode"
              />
            </el-select>
        </el-form-item> -->
        <el-form-item label="技术方向" prop="technologyDirection">
          <el-select v-model="queryParams.technologyDirection" clearable placeholder="请选择" filterable size="small" @change="handleQuery">
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
          <el-select v-model="queryParams.projectLocation" placeholder="请选择" filterable clearable size = "small" @change="handleQuery">
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
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" style="margin:3px 10px 0 -10px">查询</el-button>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="resetQuery"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" border :data="followList" >
      <el-table-column label="需求名称" align="left" prop="projectName" >
        <template slot-scope="scope">
          <span>{{scope.row.projectName}}</span>
          <div>
            <el-button type="text" v-hasPermi="['demand:follow:postInterview']" @click="uplodmian(scope.row)">上传面试题</el-button>
          </div>
         </template>
      </el-table-column>

      <el-table-column label="技术要求/技术方向" align="left">
        <template slot-scope="scope">
          <span>{{scope.row.demandYears==1?"中级":scope.row.demandYears==0?"初级":"高级"}} / {{professionIdopFormat(scope.row)}}</span>
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
      <el-table-column label="学历要求" align="left" prop="education" :formatter="customerFormat" width="55"/>
      <el-table-column label="年限" align="left" prop="directWorklife" width="50"/>
      <el-table-column label="具体要求" align="left" width="500">
      <template slot-scope="scope">
          <p v-html='scope.row.specificrequiRement'></p>
      </template>
      </el-table-column>
      <el-table-column label="发布时间" align="left" prop="addTime"/>
      <el-table-column label="地址" align="left" prop="demandYears">
        <template slot-scope="scope">
          <span>{{intentionareaFormat(scope.row)}}</span>
        </template>
      </el-table-column>

      <el-table-column label="录入人姓名" align="left" prop="operUsername" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="scope">
          <div>
            <el-button  type="text" @click="handleUpdate(scope.row)"><svg-icon icon-class="bangding" class="icons"/>绑定</el-button>
          </div>
          <div>
            <el-button  type="text" @click="see(scope.row)" v-hasPermi="['demand:follow:query']"><svg-icon icon-class="eye-open" class="icons"/>查看</el-button>
          </div>

        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>
    <index :bangding="bangding"></index>


    <el-dialog title="上传面试题" :visible.sync="open"  width="500px" append-to-body>
      <el-form :model="form" label-width="80px"   ref="form" style="width: 400px;">
        <el-form-item label="需求名称">
           <span>{{form.projectName}}</span>
        </el-form-item>
        <el-form-item label="选择文件">
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
        <el-form-item label="备注" prop="topicBz">
           <el-input v-model="form.topicBz" size="medium"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm2">确定</el-button>
        <el-button @click="open=false">取 消</el-button>
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
    postInterview,
    ispostInterview
  } from "@/api/demand/binding";
  import {
    corpNames
  } from "@/api/demand/follow";
import { treeselect } from "@/api/system/dept";
import index from "../../components/particulars/index"
  export default {
    name: "Follow",
    components:{
      index
    },
    data() {
      return {
        // 搜索信息
        searchmsg:"",
        corpnamelists:[],
        // 客户级别
        customerleve:[],
        // 技术方向字典
        professionIdoptions: [],
        // 简历学历字典
        customerSpecialitiesoptions: [],
        // 简历地区字典
        intentionareaOptions:[],
        bangding:{
          open2:false,
          loading2:false,
          templist2:[],
          forms: [],
          id:"",
        },
        // 遮罩层
        loading: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        disabled:false,
        // 需求表格数据
        followList: [],
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
        form: {},
        open:false,
        single:null,
      };
    },
    created() {
      this.getList();
      // this.getCorpName()
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

      uplodmian(row){
        this.form = {
          corpCode:null,
          demandId:null,
          projectName:null,
          topicBz:null,
        }
        this.resetForm("form")
        this.form = {
          corpCode:row.corpCode,
          demandId:row.demandId,
          projectName:row.projectName,
        }
        let formData = new FormData()
        formData.append("demandId",row.demandId)
        ispostInterview(formData).then(res=>{
            if(res=="你未发布过该需求的面试题"){
              this.open = true
              if(this.$refs.file!==undefined){
                this.handleRemove()
              }
            }else{
              let that = this
              this.$confirm(res+'是否继续添加', "警告", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
              }).then(function() {

              }).then(() => {
                that.open = true
                if(that.$refs.file!==undefined){
                  that.handleRemove()
                }
              }).catch(function() {
              });
            }
        })



      },
      handleExceed(){
        this.msgError(`当前限制选择 1 个文件`);
      },
      submitForm2(){
        if(this.single==null && this.form.topicBz==null){
          this.msgError("文件或备注选填一项")
        }else{
              let formData = new FormData()
              formData.append("zm",JSON.stringify({marTopic:this.form}))
              formData.append("multipartFile",this.single)
              postInterview(formData).then(res=>{
                  this.msgSuccess("上传成功")
                  this.open=false
              })
        }
      },
      handleRemov(value,filelist){
        this.single=value.raw;
      },
      handleRemove(file) {
          this.$refs.file.clearFiles()
          this.single = null
      },
      getCorpName(){
        corpNames().then(res=>{
          this.corpnamelists=res
        });
      },
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
        this.bangding.templist2=[]
        let form = new FormData()
        form.append("demandId",row.demandId)
        addFollow(form).then(res=>{
          this.bangding.forms=res.data
          this.bangding.templist2=res.data
          this.bangding.id=row.demandId
          this.bangding.open2 = true;
        })
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.corpnamelist=[]
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
