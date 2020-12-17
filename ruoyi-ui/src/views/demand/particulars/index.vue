<template>
  <div class="app-container">
      <div class="centart">
          <b>{{form.projectName}}</b>
          <span class="name"></span>
          <el-button type="primary" @click="handleUpdate">绑定简历</el-button>
          <table class="table">
            <tr>
              <td><span class="name">需求名称</span>{{form.projectName}}</td>
              <td><span class="name">岗位需求</span>{{professionIdopFormat(form)}}</td>
              <td><span class="name">技术级别</span>{{form.demandYears==1?"中级":"高级"}}</td>
              <td><span class="name">需求人数</span>{{form.demandNumber}}</td>
              <td><span class="name">薪资范围</span>{{form.minSalary}}-{{form.maxSalary}}</td>
            </tr>
            <tr>
              <td><span class="name">经验要求</span>{{form.directWorklife}}年</td>
              <td><span class="name">学历要求</span>{{customerFormat(form)}}</td>
              <td><span class="name">语言要求</span>{{form.langue == 0?"无":form.langue==2?"日语":"英语"}}</td>
              <td><span class="name">简历格式</span>{{form.demandNumber}}</td>
              <td><span class="name">简历模板</span>{{templateFormat(templist,form.tempId)}}</td>
            </tr>
            <tr>
              <td><span class="name">客户级别</span>{{customerleveFormat(form)}}</td>
              <td><span class="name">工作地点</span>{{intentionareaFormat(form)}}</td>
              <td><span class="name">面试官</span>{{form.interviewer}}</td>
              <td><span class="name">面试地点</span>{{form.specificLocation}}</td>
              <td><span class="name">发布时间</span>{{form.addTime}}</td>
            </tr>
          </table>
          <div class="div"> <span style="width: 70px; color:#909399;float: left;">需求图片</span><el-button @click="see" type="text" style="height: 20px;margin-top: -11px;">点击预览</el-button></div>
          <div class="div">
            <span style="width: 70px; color:#909399;float: left;">技术要求</span>
            <span v-html='form.specificrequiRement'></span>
          </div>
          <div class="div">
            <div style="width: 70px; color:#909399;">特别备注</div><p v-html='form.attention'></p>
          </div>
          <p></p>
          <b>绑定的简历</b>
          <p></p>
          <el-form  ref="queryForm" :inline="true">
            <el-input v-model="customerName" placeholder="请输入简历姓名" clearable size="small" @keyup.enter.native="handleQuery" style="width: 190px;"/>
            <el-button
                type="cyan"
                size="mini"
                @click="handleQuery"
              >搜索</el-button>
            <!-- <el-button
                v-if="ident==1"
                type="warning"
                size="mini"
                @click=""
                style="position: absolute;right: 25px;"
            >批量跟踪</el-button> -->
            <el-button
                v-if="ident==1"
                type="primary"
                size="mini"
                @click="downloadlist"
                :disabled="multiple"
                style="position: absolute;right: 110px;"
              >批量下载</el-button>
          </el-form>
          <p></p>
          <el-table v-loading="loading" border :data="templists" size="small" @selection-change="handleSelectionChange">
            <el-table-column type="selection" key="1"/>
            <el-table-column label="姓名" align="left" prop="customerName" key="2"/>
            <el-table-column label="电话" align="left" prop="customerTel" key="3"/>
            <el-table-column label="绑定人" align="left" prop="trackzPeoname" key="4"/>
            <el-table-column label="绑定时间" align="left" key="5">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.bindTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
               </template>
            </el-table-column>
            <el-table-column label="简历状态" align="left" width="500" key="6">
              <template slot-scope="scope" >
                <el-steps
                v-if="ident==2"
                :active="scope.row.active"
                finish-status="success"
                :process-status="scope.row.process"
                :align-center="true">
                  <el-step title="需求绑定"></el-step>
                  <el-step title="简历" v-if="scope.row.followStatus<3"></el-step>
                  <el-step title="简历通过" v-if="scope.row.followStatus==3||scope.row.followStatus>4"></el-step>
                  <el-step title="简历不过" v-if="scope.row.followStatus==4"></el-step>

                  <el-step title="面试" v-if="scope.row.followStatus<5"></el-step>
                  <el-step title="面试通过" v-if="scope.row.followStatus==5||scope.row.followStatus>6"></el-step>
                  <el-step title="面试不过" v-if="scope.row.followStatus==6"></el-step>

                  <el-step title="入项" v-if="scope.row.followStatus<=7"></el-step>
                  <el-step title="未入项" v-if="scope.row.followStatus==8"></el-step>
                </el-steps>

                <el-steps
                v-if="ident==1"
                :active="scope.row.active"
                finish-status="success"
                :process-status="scope.row.process"
                :align-center="true">
                  <el-step title="需求绑定"></el-step>
                  <el-step title="简历" v-if="scope.row.followStatus<3" @click.native="resume(scope.row,1)"></el-step>
                  <el-step title="简历通过" v-if="scope.row.followStatus==3||scope.row.followStatus>4"></el-step>
                  <el-step title="简历不过" v-if="scope.row.followStatus==4"></el-step>

                  <el-step title="面试" v-if="scope.row.followStatus==3" @click.native="resume(scope.row,2)"></el-step>
                  <el-step title="面试" v-if="scope.row.followStatus<5&&scope.row.followStatus!==3"></el-step>
                  <el-step title="面试通过" v-if="scope.row.followStatus==5||scope.row.followStatus>6"></el-step>
                  <el-step title="面试不过" v-if="scope.row.followStatus==6"></el-step>

                  <el-step title="入项" v-if="scope.row.followStatus<=7&&scope.row.followStatus!==5"></el-step>
                  <el-step title="入项" v-if="scope.row.followStatus==5" @click.native="resume(scope.row,3)"></el-step>
                  <el-step title="未入项" v-if="scope.row.followStatus==8"></el-step>
                </el-steps>
              </template>
            </el-table-column>
            <el-table-column label="跟踪时间" align="left" key="7">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.newfollowtime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="left" key="8">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="preview(scope.row)"
                >预览</el-button>
                <el-button
                  v-if="ident==1"
                  size="mini"
                  type="text"
                  @click="download(scope.row)"
                >下载</el-button>
              </template>
            </el-table-column>
          </el-table>
          <pagination v-show="total>0" :total="total" :page.sync="pageNum" :limit.sync="pageSize"@pagination="getlist"/>
          <el-dialog
            :title="title"
            :visible.sync="open"
             width="30%"
             style="text-align: center;"
             >
            <p></p>

            <el-radio-group v-model="followstart" v-if="title=='简历'">
                <el-radio :label="3">简历通过</el-radio>
                <el-radio :label="4">简历未通过</el-radio>
            </el-radio-group>

            <el-radio-group v-model="followstart" v-else-if="title=='面试'">
                <el-radio :label="5">面试通过</el-radio>
                <el-radio :label="6">面试未通过</el-radio>
            </el-radio-group>
            <el-radio-group v-model="followstart" v-else>
                <el-radio :label="7">入项</el-radio>
                <el-radio :label="8">未入项</el-radio>
            </el-radio-group>
            <p></p>

            <div v-if="followstart==4||followstart==6||followstart==8">


                <el-form label-width="100px" :model="qqq" ref="ruxing">
                  <el-form-item v-if="followstart==8"
                    :rules="[{ required: true, message: '请输入原因', trigger: 'blur' },]"
                    prop="textarea1" label="未通过原因">
                    <el-input
                      type="textarea"
                      autosize
                      placeholder="请输入原因"
                      v-model="qqq.textarea1">
                    </el-input>
                  </el-form-item>
                  <el-form-item v-else prop="textarea1" label="未通过原因">
                    <el-input
                      type="textarea"
                      autosize
                      placeholder="请输入原因"
                      v-model="qqq.textarea1">
                    </el-input>
                  </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
              <el-button @click="open=false">取 消</el-button>
              <el-button type="primary" @click="submit">确 定</el-button>
            </span>
          </el-dialog>
          <!-- 简历预览 -->
          <el-dialog title="预览" :visible.sync="open3" width="70%">
           <iframe
              :src="src"
              style="overflow: auto; position: absolute; top: 40px; right: 0; bottom: 0; left: 0; width: 100%; height:1000%; border: none;"
            ></iframe>
          </el-dialog>
          <!-- 绑定简历 -->
          <el-dialog :title="title" :visible.sync="open2" width="40%" append-to-body>
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
                 <el-checkbox v-for="city in templist2" :label="city.customer_code" :key="city.customer_code" style="width:155px;">{{city.customer_name}}-{{city.customer_tel}}</el-checkbox>
            </el-checkbox-group>
            <div v-show="templist2.length==0 && forms.length!==0" style="text-align: center;
            ">暂无该信息</div>
            <div v-show="templist2.length==0 && forms.length==0" style="text-align: center; ">暂无简历信息,先去抢占几份去吧</div>
            <div slot="footer" class="dialog-footer">
              <el-button type="primary" @click="submitForm">确定</el-button>
              <el-button @click="open2=false">取 消</el-button>
            </div>
          </el-dialog>
          <!-- 入项信息 -->
          <el-dialog :title="'确认并补全'+title+'信息'" :visible.sync="open4"  width="40%" append-to-body>
            <el-form :model="comform" label-width="80px" :rules="rules" ref="comform">
              <el-form-item label="公司名称">
                <span>{{comform.corpName}}</span>
              </el-form-item>
              <el-form-item label="需求名称">
                 <span>{{comform.projectName}}</span>
              </el-form-item>
              <el-form-item label="项目时间">
                  <el-col :span="11">
                    <el-form-item prop="startTime">
                    <el-date-picker type="date" placeholder="选择开始日期" v-model="comform.startTime" size="small" style="width: 100%;"></el-date-picker>
                    </el-form-item>
                  </el-col>
                  <el-col style="text-align: center;" :span="2">—</el-col>
                  <el-col :span="11">
                    <el-form-item prop="endTime">
                    <el-date-picker placeholder="选择结束时间" v-model="comform.endTime" size="small" style="width: 100%;"></el-date-picker>
                    </el-form-item>
                  </el-col>
                </el-form-item>
              <el-form-item label="结算周期" prop="settledCycle">
                 <el-select v-model="comform.settledCycle" placeholder="请选择结算周期" clearable size="small" >
                   <el-option
                       v-for="dict in companyperiod"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"
                     />
                 </el-select>
              </el-form-item>
              <el-form-item label="工资" prop="salary">
                  <el-input v-model="comform.salary" placeholder="请输入工资"  size="small" />
              </el-form-item>
              <el-form-item label="服务费用" prop="servicePay">
                  <el-input v-model="comform.servicePay" placeholder="请输入服务费用"  size="small" />
              </el-form-item>
              <el-form-item label="入职时间" prop="syqstartTime">
                  <el-date-picker type="date" placeholder="选择入职时间" v-model="comform.syqstartTime" size="small" style="width: 100%;"></el-date-picker>
              </el-form-item>
              <el-form-item label="转正时间" prop="syqEndtime">
                  <el-date-picker type="date" placeholder="选择转正时间" v-model="comform.syqEndtime" size="small" style="width: 100%;"></el-date-picker>
              </el-form-item>
              <el-form-item label="社保" prop="socSecopt">
                  <el-radio-group v-model="comform.socSecopt" size="small">
                      <el-radio :label="1">是</el-radio>
                      <el-radio :label="2">否</el-radio>
                  </el-radio-group>
              </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button type="primary" @click="submitForm2">确定</el-button>
              <el-button @click="open4=false">取 消</el-button>
            </div>
          </el-dialog>
          <el-dialog :visible.sync="dialogVisible" width="500px" :title="title2">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
      </div>
  </div>
</template>

<script>
  import {gettemplist,template,getFollow,submitstart,addFollow,delFollow,getInputInformation,entryPersonnel} from '@/api/demand/praticulars'
  import {debounce} from "@/utils/ruoyi.js"
  export default {
    name:'particulars',
    data(){
      var price = (rule, value, callback) => {
            const reg = /^\d+.?\d{0,2}$/
            if (!value) {
              callback(new Error('不能为空'))
            } else if (!Number(value)) {
              callback(new Error('请输入数字值'))
            } else {
              if (reg.test(value)) {
                callback()
              } else {
                callback(new Error('小数点后最多只能输入两位'))
              }
            }
          }
      return{
        dialogVisible:false,
        dialogImageUrl:"",
        // 入项信息
        comform:{},
        // 未通过原因
        qqq:{
          textarea1:""
        },
        // 定时器
        timer:null,
        loading2:false,
        // 搜索简历信息
        searchmsg:"",
        // 入项信息
        open4:false,
        // 预览
        open3:false,
        // 绑定简历
        open2:false,
        // 标题
        title:null,
        // 接收基础数据
        form:{},
        // 抢占简历信息
        forms:[],
        // 选中简历信息
        corpnamelist:[],
        // 抢占简历信息
        templist2:[],
        // 搜索name
        customerName:"",
        // 学历
        customerSpecialitiesoptions:[],
        // 技术方向
        professionIdoptions:[],
         // 城市
        intentionareaOptions:[],
        // 客户级别
        customerleve:[],
        // 简历模板
        templist:[],
        title2:"",
        pageNum: 1,
        pageSize: 10,
        // 总条数
        total: 0,
        // 数据列表
        templists:[],
        // 选中数组
        ids:[],
        // 非多个禁用
        multiple:true,
        // 遮盖层
        loading:true,
        // 简历路径
        src:"",
        // 跟踪
        open:false,
        // 单选
        followstart:null,
        // 单个简历id
        tempID:null,
        ident:"",
        list:[1,1,1,"简历通过","简历未通过","面试通过","面试未通过","入项","未入项"],
        // 周期字典
        companyperiod:[],
        rules:{
          startTime:[{ type: 'date', required: true, message: '请选择开始日期', trigger: 'change' }],
          endTime:[{ type: 'date', required: true, message: '请选择结束日期', trigger: 'change' }],
          syqstartTime:[{ type: 'date', required: true, message: '请选择入职时间', trigger: 'change' }],
          settledCycle:[{ required: true, message: '请选择结算周期', trigger: 'blur' },],
          salary:[{ validator: price,  trigger: 'blur' },],
          servicePay:[{ validator: price,  trigger: 'blur' },],
          syqEndtime:[{ type: 'date', required: true, message: '请选择转正时间', trigger: 'change' }],
          socSecopt:[{ required: true, message: '请选择社保', trigger: 'blur' },],
        },
      }
    },
    watch:{
        "corpnamelist.length":{
            handler(newValue, oldValue) {
              if (newValue>3) {
                  this.msgError("最多能绑定3条简历")
                  this.corpnamelist.pop()
              }
            }
        }
    },
    created() {
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
      this.getlist()
    },
    mounted(){
      this.gettemplate()
    },
    methods:{
      // 获取详情信息
      getlist(){
        // 获取基础信息
        getFollow(this.$route.query.row).then(res=>{
          this.form=res.data.marDeman
        });
        this.gettelist()
      },
      gettelist(){
        this.ident=this.$route.query.ident
        // 获取绑定简历信息
        let form =new FormData()
        form.append("demandId",this.$route.query.row)
        form.append("customerName",this.customerName)
        form.append("pageNum",this.pageNum)
        form.append("pageSize",this.pageSize)
        this.loading = true;
        gettemplist(form).then(res=>{
          res.rows.forEach(item=>{
            if(item.followStatus=== 1||item.followStatus=== 2){
              item.active=0
              if(item.followStatus== 1){
                item.process="success"
              }else{
                item.process="error"
              }
            }
            if(item.followStatus===3||item.followStatus===4){
              item.active=1
              if(item.followStatus==3){
                item.process="success"
              }else{
                item.process="error"
              }
            }
            if(item.followStatus===5||item.followStatus===6){
               item.active=2
               if(item.followStatus==5){
                 item.process="success"
               }else{
                 item.process="error"
               }
            }
            if(item.followStatus===7||item.followStatus===8){
              item.active=3
              if(item.followStatus==7){
                item.process="success"
              }else{
                item.process="error"
              }
            }
          })
          this.templists = res.rows
          this.total = res.total;
          this.loading = false;
          })
      },
      // 获取简历模板
      gettemplate(){
        template().then(res=>{
          this.templist=res.rows
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
      // 简历模板
      templateFormat(datas, value) {
        var actions = [];
        Object.keys(datas).some((key) => {
        	if (datas[key].templateId == ('' + value)) {
        		actions.push(datas[key].templateName);
        		return true;
        	}
        })
        return actions.join('');
      },
      // 搜索
      handleQuery(){
        this.getlist()
      },
      resume(row,ind){
        if(ind==1){
          // 简历点击
          this.followstart=3
          this.open=true
          this.title="简历"
          this.tempID=row.id
        }else if(ind==2){
          // 面试点击
          this.followstart=5
          this.open=true
          this.title="面试"
          this.tempID=row.id
        }else{
          // 入项点击
          this.followstart=7
          this.open=true
          this.title="入项"
          this.tempID=row.id
        }
      },
      // 提交
      submit(){
        let form =new FormData()
        form.append("followStatus",this.followstart)
        form.append("demandresumeId",this.tempID)
        form.append("followDetail",this.qqq.textarea1)
        if(this.followstart==7){
          form.append("id",this.tempID)
          this.getDicts("mar_company_period").then(response => {
            this.companyperiod = response.data;
          });
          this.comform = {}
          getInputInformation(form).then(res=>{
            this.comform=res.data
            this.open4=true
          })
        }else{
          let that = this
          if(this.followstart==8){
            this.$refs["ruxing"].validate((valid) => {
                if(valid){
                  this.$confirm('确认"'+that.title+'"结果为"'+ that.list[that.followstart] + '"吗?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                  }).then(function() {
                    return submitstart(form).then(res=>{
                    })
                  }).then(() => {
                    this.msgSuccess("操作成功");
                    this.templists=[]
                    this.gettelist()
                    this.open=false
                  }).catch(()=>{})
                }
              })
          }else{
            this.$confirm('确认"'+that.title+'"结果为"'+ that.list[that.followstart] + '"吗?', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
              return submitstart(form).then(res=>{
              })
            }).then(() => {
              this.msgSuccess("操作成功");
              this.templists=[]
              this.gettelist()
              this.open=false
            }).catch(()=>{})
          }

        }
      },
      // 确定
      submitForm2(){
        this.comform.id=this.tempID
        this.$refs["comform"].validate((valid) => {
          if (valid) {
            if(this.comform.salary>this.comform.servicePay){this.msgError("工资不能大于服务费")}else{
              let that = this
              this.$confirm('确认信息完整并入项吗?', "警告", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
              }).then(function() {
                return entryPersonnel(that.comform).then()
              }).then(() => {
                this.msgSuccess("操作成功");
                this.templists=[]
                this.gettelist()
                this.open4=false
                this.open=false
              }).catch(()=>{})
            }

          }
        });
      },
      // 绑定
      handleUpdate(){
        this.loading2=true
        addFollow().then(res=>{
          this.corpnamelist=[]
          this.forms=res.data
          this.templist2=res.data
          this.forms.id=this.$route.query.row
          this.open2 = true;
          this.title = "绑定";
          this.loading2=false
        })
      },
      // 确定按钮
      submitForm(){
        if(this.corpnamelist.length>3){
            this.msgError("最多绑定3条简历")
        }else{
          let zm={
            list:this.corpnamelist,
            demandId:this.forms.id
          }
          zm = JSON.stringify(zm)
          let form= new FormData()
          form.append("zm",zm)
          delFollow(form).then(res=>{
            this.msgSuccess("绑定成功")
            this.open2=false
            this.gettelist()
          })
        }
        },
      // 搜索简历
      search(){
        let that = this
        this.loading2=true
        if(this.searchmsg==""){
        setTimeout(function(){
          that.templist2=that.forms
          that.loading2=false
        },1000)
        }else{
          clearInterval(this.timer)
          this.timer=setTimeout(function(){
            var data= that.forms.filter(item=>{
              return item.customer_name == that.searchmsg || item.customer_tel==that.searchmsg
             })
            that.templist2 = data
            that.loading2=false
          },1000)
        }
      },
      // 预览
      preview(adinw){
        let srcs = process.env.VUE_APP_BASE_API+adinw.resumePath
        this.src=`https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=http://localhost${srcs}`
        this.open3=true
      },
      // 下载
      download(adinw){
        let that = this
        clearInterval(this.timer)
        this.timer=setTimeout(function(){
          that.dow(adinw)
          },500)
      },
      handleSelectionChange(selection){
        this.ids = selection
        this.multiple = !selection.length
      },
      dow(adinw){
        location.href=`http://localhost/dev-api${adinw.resumePath}`
      },
      downloadlist:debounce(function(){
          this.downloadlis()
      },500),
      downloadlis(){
        let that = this
        let i = 1
        this.ids.forEach(item=>{
          setTimeout(function(){
            that.dow(item)
          },1000*i)
          i+=1
        })
      },
      see(){
         let srcs = process.env.VUE_APP_BASE_API
        this.dialogVisible=true
        this.title2 = "需求图片"
        this.dialogImageUrl = srcs+this.form.demandPic
      },
    }
  }
</script>

<style>
  .centart{
    padding:20px 20px;
  }
  .div{
    border: 1.5px solid #DCDFE6;
    width: 100%;
    padding: 10px;
    font-size: 14px;
    display: flex;
  }
  .table{
    margin-top: 20px;
    width: 100%;
    border-collapse:collapse;
    font-size: 14px;
  }
  .table tr td{
    padding-left: 10px;
    line-height: 30px;
    border: 1px solid #DCDFE6;
  }
  .name{
    display: inline-block;
    width: 70px;
    color:#909399;
  }
</style>
