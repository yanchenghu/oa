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
              <td><span class="name">目标人数</span>{{form.targetNumber}}</td>
            </tr>
            <tr>
              <td><span class="name">薪资范围</span>{{form.minSalary}}-{{form.maxSalary}}</td>
              <td><span class="name">经验要求</span>{{form.directWorklife}}年</td>
              <td><span class="name">学历要求</span>{{customerFormat(form)}}</td>
              <td><span class="name">语言要求</span>{{form.langue == 0?"无":form.langue==2?"日语":"英语"}}</td>
              <td><span class="name">需求图片</span><el-button @click="see(3)" type="text" :disabled="!form.demandPic">点击预览</el-button></td>
            </tr>
            <tr>
              <td v-if="ident==8"><span class="name" >客户级别</span>{{customerleveFormat(form)}}</td>
              <td><span class="name">工作地点</span>{{intentionareaFormat(form)}}</td>
              <td><span class="name">面试官</span>{{form.interviewer}}</td>
              <td><span class="name">面试地点</span>{{form.specificLocation}}</td>
               <td><span class="name">简历模板</span><el-button v-if="form.tempId" type="text" @click="see(templateFormat(templist,form.tempId)[1],1)">{{templateFormat(templist,form.tempId)[0]}}</el-button><span v-else>无</span></td>
            </tr>
            <tr >
              <td><span class="name">发布时间</span>{{form.addTime}}</td>
              <td colspan="4"><span class="name">详细地址</span>{{form.address}}</td>
            </tr>
            <!-- <span><span class="name">详细地址</span>{{form.address}}</span> -->
          </table>
          <div class="div">
            <div style=" width: 125px; color:#909399;">技术要求</div>
              <div v-html='form.specificrequiRement'></div>
          </div>
          <div class="div">
            <div style="width: 70px; color:#909399;">特别备注</div><span>{{form.attention}}</span>
          </div>
          <p></p>
          <b>绑定的简历</b>
          <p></p>
          <el-form  ref="queryForm" :inline="true" @submit.native.prevent>
            <el-input v-model="customerName" placeholder="请输入简历姓名或电话" clearable size="small" @keyup.enter.native="handleQuery" style="width: 190px;" />
            <el-button
                type="primary"
                size="small"
                @click="handleQuery"
              >搜索</el-button>
            <el-button
                v-if="ident==8"
                type="warning"
                size="mini"
                @click="miaoshi"
                :disabled="multiple"
                style="float: right;"
            >批量操作</el-button>
            <!-- <el-button
                v-if="ident==8"
                type="primary"
                size="mini"
                @click="downloadlist"
                :disabled="multiple"
                style="float: right;"
              >批量下载简历</el-button> -->
              <!-- <el-button
                  v-if="ident==8"
                  type="primary"
                  size="mini"
                  @click="downloadlist(2)"
                  :disabled="multiple"
                  style="float: right;"
                >批量下载附件</el-button> -->
            <el-button
                v-if="ident==8"
                type="danger"
                size="mini"
                @click="chongzhi"
                :disabled="multiple"
                style="float: right;"
              >重置简历状态</el-button>
          </el-form>
          <p></p>
          <el-table v-loading="loading" border :data="templists"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" key="1"/>
            <el-table-column label="姓名" align="left" prop="customerName" key="2" width="70"/>
            <el-table-column label="电话" align="left" prop="customerTel" key="3"/>
            <el-table-column label="期望薪资" align="left" prop="expectationSalary" width="60" key="9">
              <template slot-scope="scope">
                <span v-if="scope.row.expectationSalary">{{scope.row.expectationSalary }}</span>
                <span v-else>未知</span>
               </template>
            </el-table-column>
            <el-table-column label="毕业时间" align="left" prop="customerUniversityTime" key="11">
               <template slot-scope="scope">
                <span >{{scope.row.customerUniversityTime?scope.row.customerUniversityTime:"未知"}}</span>
               </template>
            </el-table-column>
            <el-table-column label="学历/工作经验" align="left" prop="expectationSalary" key="10">
              <template slot-scope="scope">
                <span >{{scope.row.education?customerFormat(scope.row):"未知"}}/{{scope.row.workYear?scope.row.workYear+"年":"未知"}}</span>
               </template>
            </el-table-column>
            <el-table-column label="绑定人" width="60" align="left" prop="trackzPeoname" key="4"/>
            <el-table-column label="绑定时间" align="left" key="5">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.bindTime, '{y}-{m}-{d} {h}:{i}') }}</span>
               </template>
            </el-table-column>
            <el-table-column label="简历状态" align="left" width="400" key="6" >
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
                <div title="简历跟踪">
                <el-steps
                v-if="ident==8"
                :active="scope.row.active"
                finish-status="success"
                :process-status="scope.row.process"
                :align-center="true">
                  <el-step title="需求绑定"></el-step>
                  <el-step title="简历" class="stepp" v-if="scope.row.followStatus<3" @click.native="resume(scope.row,1)"></el-step>
                  <el-step title="简历通过" v-if="scope.row.followStatus==3||scope.row.followStatus>4"></el-step>
                  <el-step title="简历不过" v-if="scope.row.followStatus==4"></el-step>

                  <el-step title="面试" class="stepp" v-if="scope.row.followStatus==3" @click.native="resume(scope.row,2)"></el-step>
                  <el-step title="面试" v-if="scope.row.followStatus<5&&scope.row.followStatus!==3"></el-step>
                  <el-step title="面试通过" v-if="scope.row.followStatus==5||scope.row.followStatus>6"></el-step>
                  <el-step title="面试不过" v-if="scope.row.followStatus==6"></el-step>

                  <el-step title="入项" v-if="scope.row.followStatus<=7&&scope.row.followStatus!==5"></el-step>

                  <el-step title="入项" class="stepp" v-if="scope.row.followStatus==5" @click.native="resume(scope.row,3)"></el-step>
                  <el-step title="未入项" v-if="scope.row.followStatus==8"></el-step>
                </el-steps>
                </div>
              </template>
            </el-table-column>
            <el-table-column v-if="ident==8" label="推荐状态" align="left" key="12">
              <template slot-scope="scope">
                <el-button :disabled="scope.row.isRecommend==1" type="primary" size="mini" @click="tuijian(scope.row)">{{scope.row.isRecommend==0?"推荐":"已推荐"}}</el-button>
              </template>
            </el-table-column>
            <el-table-column label="跟踪时间" align="left" key="7">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.newfollowtime, '{y}-{m}-{d} {h}:{i}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="left" key="8" width="150">
              <template slot-scope="scope">
                <p><el-button
                  size="mini"
                  type="text"
                  @click="preview(scope.row,1)"
                >预览原版</el-button>
                <el-button
                  size="mini"
                  type="text"
                  @click="preview(scope.row,2)"
                >预览附件</el-button>
                </p>
                <p>
                  <el-button
                    v-if="ident==8"
                    size="mini"
                    type="text"
                    @click="download(scope.row,1)"
                  >下载原版</el-button>
                  <el-button
                    v-if="ident==8"
                    size="mini"
                    type="text"
                    @click="download(scope.row,2)"
                  >下载附件</el-button>
                </p>
              </template>
            </el-table-column>
          </el-table>
          <pagination v-show="total>0" :total="total" :page.sync="pageNum" :limit.sync="pageSize"@pagination="getlist"/>
          <el-dialog
            :title="title"
            :visible.sync="open"
            width="500px"
            style="text-align: center;"
            >
            <p></p>
            <el-radio-group v-model="followstart" v-if="title=='简历'||title=='批量简历跟踪'">
                <el-radio :label="3">简历通过</el-radio>
                <el-radio :label="4">简历未通过</el-radio>
            </el-radio-group>
            <el-radio-group v-model="followstart" v-else-if="title=='面试'||title=='批量面试跟踪'">
                <el-radio :label="5">面试通过</el-radio>
                <el-radio :label="6">面试未通过</el-radio>
            </el-radio-group>
            <el-radio-group v-model="followstart" v-else>
                <el-radio :label="7">入项</el-radio>
                <el-radio :label="8">未入项</el-radio>
            </el-radio-group>
            <p></p>

            <div v-if="followstart==4||followstart==6||followstart==8||followstart==5||followstart==3">
                <el-form label-width="100px" :model="qqq" style="width: 65%;" ref="ruxing" >
                  <el-form-item
                    v-if="followstart==5"
                    :rules="[{ required: true, message: '日期不能为空', trigger: 'change' },]"
                    prop="stayTime"
                    label="预入项日期">
                    <el-date-picker
                        v-if="followstart==5"
                        v-model="qqq.stayTime"
                        type="date"
                        size="small"
                        :picker-options="pickerOptions2"
                        :clearable="false"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd"
                        placeholder="选择日期">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item v-else-if="followstart==3" :rules="[{ required: true, message: '面试时间不能为空', trigger: 'change' },]" prop="value1" label="面试时间段">
                    <el-date-picker
                    style="width: 360px;"
                      v-model="qqq.value1"
                      type="datetimerange"
                      size="small"
                      value-format="yyyy-MM-dd HH:mm:ss"
                      :clearable="false"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期">
                     </el-date-picker>
                  </el-form-item>

                  <el-form-item v-else-if="followstart==8"
                    :rules="[{ required: true, message: '请输入原因', trigger: 'blur' },]"
                    prop="textarea1"
                    label="未通过原因">
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
                  <el-form-item v-if="followstart==3" :rules="[{ required: true, message: '面试方式不能为空', trigger: 'change' },]" prop="remark1" label="面试方式">
                    <el-select v-model="qqq.remark1"  size="small">
                      <el-option
                          key="视频面试"
                          label="视频面试"
                          value="视频面试"
                        />
                      <el-option
                          key="电话面试"
                          label="电话面试"
                          value="电话面试"
                        />
                      <el-option
                          key="现场面试"
                          label="现场面试"
                          value="现场面试"
                         />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="协助人" v-if="followstart==8">
                      <el-input v-model="qqq.entryAssistant" placeholder="请输入协助人"  size="small" />
                  </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
              <el-button @click="open=false">取 消</el-button>
              <el-button type="primary" @click="submit">确 定</el-button>
            </span>
          </el-dialog>
         <el-dialog style="text-align: center;" title="批量跟踪" :visible.sync="caozuo" width="500px">
              <el-radio-group v-model="caozuos">
                  <el-radio :label="1">批量简历跟踪</el-radio>
                  <el-radio :label="2">批量面试跟踪</el-radio>
              </el-radio-group>
              <span slot="footer" class="dialog-footer">
                <el-button @click="caozuo=false">取 消</el-button>
                <el-button type="primary" @click="submitqq">确 定</el-button>
              </span>
          </el-dialog>

          <el-dialog style="text-align: center;" title="批量下载" :visible.sync="caozuo2" width="500px">
               <el-radio-group v-model="caozuos">
                   <el-radio :label="1">批量下载原件</el-radio>
                   <el-radio :label="2">批量下载附件</el-radio>
               </el-radio-group>
               <span slot="footer" class="dialog-footer">
                 <el-button @click="caozuo2=false">取 消</el-button>
                 <el-button type="primary" @click="submitq">确 定</el-button>
               </span>
           </el-dialog>
          <!-- 简历预览 -->
          <el-dialog :title="title" :visible.sync="open3" width="70%">
           <iframe
              :src="src"
              style="overflow: auto; position: absolute; top: 45px; right: 0; bottom: 0; left: 0; width: 100%; height:1000%; border: none;"
            ></iframe>
          </el-dialog>
          <!-- 绑定 -->
         <index :bangding="bangding" @gettelist="gettelist"></index>

          <!-- 入项信息   -->
          <el-dialog :title="'确认并补全'+title+'信息'" :visible.sync="open4"  width="500px" append-to-body>
            <el-form :model="comform" label-width="80px" :rules="rules"  ref="comform" style="width: 400px;">
              <el-form-item label="公司名称">
                <span>{{comform.corpName}}</span>
              </el-form-item>
              <el-form-item label="需求名称">
                 <span>{{comform.projectName}}</span>
              </el-form-item>
              <el-form-item label="项目时间" required>
                  <el-col :span="11">
                    <el-form-item prop="startTime" >
                    <el-date-picker type="date" placeholder="选择开始日期" v-model="comform.startTime" size="small" style="width: 100%;" :picker-options="pickerOptions1" value-format="yyyy-MM-dd"></el-date-picker>
                    </el-form-item>
                  </el-col>
                  <el-col style="text-align: center;" :span="2">—</el-col>
                  <el-col :span="11">
                    <el-form-item prop="endTime">
                    <el-date-picker placeholder="选择结束时间" v-model="comform.endTime" size="small" style="width: 100%;" :picker-options="pickerOptions3" value-format="yyyy-MM-dd"></el-date-picker>
                    </el-form-item>
                  </el-col>
                </el-form-item>
              <el-form-item label="结算周期" prop="settledCycle">
                 <el-select v-model="comform.settledCycle" placeholder="请选择结算周期" clearable size="small" :change="change">
                   <el-option
                       v-for="dict in companyperiod"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="parseInt(dict.dictValue)"
                     />
                 </el-select>
              </el-form-item>
              <el-form-item label="人员成本" prop="salary">
                  <el-input v-model="comform.salary" placeholder="请输入人员成本"  size="small"/>
              </el-form-item>
              <el-form-item label="服务费用" prop="servicePay">
                  <el-input v-model="comform.servicePay" placeholder="请输入服务费用"  size="small" />
              </el-form-item>
              <el-form-item label="入职时间" prop="syqstartTime">
                  <el-date-picker type="date" placeholder="选择入职时间" v-model="comform.syqstartTime" size="small" value-format="yyyy-MM-dd" style="width: 100%;" :picker-options="pickerOptions4"></el-date-picker>
              </el-form-item>
              <el-form-item label="转正时间" prop="syqEndtime">
                  <el-date-picker type="date" placeholder="选择转正时间" v-model="comform.syqEndtime" size="small" value-format="yyyy-MM-dd" style="width: 100%;" ></el-date-picker>
              </el-form-item>
              <el-form-item label="社保" prop="socSecopt">
                  <el-radio-group v-model="comform.socSecopt" size="small">
                      <el-radio :label="1">是</el-radio>
                      <el-radio :label="2">否</el-radio>
                  </el-radio-group>
              </el-form-item>
              <el-form-item label="协助人" >
                  <el-input v-model="comform.entryAssistant" placeholder="请输入协助人"  size="small" />
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
          <el-dialog :visible.sync="dialogVisible2" :title="title2">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
      </div>
  </div>
</template>

<script>
  import {gettemplist,template,getFollow,submitstart,addFollow,delFollow,getInputInformation,entryPersonnel,submitstarts,chongzhizhuang,resource,updateMarDemandresume} from '@/api/demand/praticulars'
  import {
    findmubiao
  } from "@/api/demand/binding";
  import {debounce,checkImg} from "@/utils/ruoyi.js"
  import index from "../../components/particulars/index"
  export default {
    name:'Particulars',
    components:{
      index
    },
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
        pickerOptions1:{
          disabledDate:(time) => {
            if (this.comform.endTime) {
                return time.getTime() > new Date(this.comform.endTime).getTime();
            }
          }
        },
        pickerOptions2:{
          disabledDate:(time) => {
              return time.getTime() < Date.now()-86400000;
          }
        },
        pickerOptions4:{
          disabledDate:(time) => {
             return time.getTime() > Date.now();
          }
        },
        pickerOptions3:{
          disabledDate:(time) => {
            if (this.comform.startTime) {
                return time.getTime() < new Date(this.comform.startTime).getTime();
            }
          }
        },
        dialogVisible:false,
        dialogVisible2:false,
        dialogImageUrl:"",
        // 入项信息
        caozuo:false,
        caozuos:null,
        caozuo2:false,
        comform:{},
        // 未通过原因
        qqq:{
          textarea1:"",
          stayTime:"",
          qqq:[],
          remark1:"",
        },
        opens:false,
        titles:"",
        // 搜索简历信息
        searchmsg:"",
        // 入项信息
        open4:false,
        // 预览
        open3:false,
        // 接收基础数据
        form:{},
        // 选中简历信息
        corpnamelist:null,
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
        title:"",
        bangding:{
          open2:false,
          loading2:false,
          templist2:[],
          forms: [],
          id:"",
        },
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
          startTime:[{  required: true, message: '请选择开始日期', trigger: 'change' }],
          endTime:[{  required: true, message: '请选择结束日期', trigger: 'change' }],
          syqstartTime:[{  required: true, message: '请选择入职时间', trigger: 'change' }],
          settledCycle:[{ required: true, message: '请选择结算周期', trigger: 'blur' },],
          salary:[{ required: true, validator: price,  trigger: 'blur' },],
          servicePay:[{ required: true, validator: price,  trigger: 'blur' },],
          syqEndtime:[{  required: true, message: '请选择转正时间', trigger: 'change' }],
          socSecopt:[{ required: true, message: '请选择社保', trigger: 'blur' },],
        },
        wen:true,
      }
    },
    created() {
      // 获取学历字典
      this.getDicts("per_customerinfo_education").then(response => {
        this.customerSpecialitiesoptions = response.data;
      });
      // 获取技术方向字典
      this.getDicts("per_customerinfo_professionid").then(response => {
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
      if(this.$route.query.name){this.customerName = this.$route.query.name}
      this.getlist()
    },
    mounted(){
      this.gettemplate()
    },
    methods:{
      tuijian(row){
        updateMarDemandresume({id:row.id,isRecommend:1}).then(res=>{
          this.msgSuccess("推荐成功")
          this.getlist()
        })
      },
      // 重置简历状态
      chongzhi(){
        let array = []
        let aa = 0
        this.ids.forEach(item=>{
          if(item.followStatus==7){
            aa=1
          }else{
            array.push(item.id)
          }
        })
        if(aa==1){
          this.msgError("有已入项简历请重新选择")
        }else{
          let form =new FormData()
          form.append("zm",JSON.stringify({list:array}))
          let that = this
          this.$confirm('是否重置所选简历的状态吗?', "警告", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(function(){
            return chongzhizhuang(form).then(res=>{
              that.msgSuccess("重置成功");
            })
          }).then(() => {
            this.gettelist()
          }).catch(()=>{})
        }
      },
      // 获取详情信息
      getlist(){
        // 获取基础信息
        getFollow(this.$route.query.row).then(res=>{
          this.form=res.data.marDeman
        });
        this.gettelist()
      },
      gettelist(){
        this.templists=[]
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
        		actions.push(datas[key].templateNominate);
            actions.push(datas[key].templateFile);
        		return true;
        	}
        })
        return actions;
      },
      submitqq(){
        let arr = 0
        let array = []
        let a = 0 ;
        if(this.caozuos!==1){
          a = 3
          this.followstart=5
          this.title="批量面试跟踪"
        }else{
          a = 1
          this.followstart=3
          this.title="批量简历跟踪"
          }
        this.ids.forEach(item=>{
          if(item.followStatus !== a){
            arr+=1
          }else{
            array.push(item.id)
          }
        })
        if(arr!==0){
          this.msgError("有状态不同的简历")
        }else{
          this.tempID=array
          this.open=true
          this.qqq={
            textarea1 :"",
            stayTime:"",
            qqq:[],
            beginTime:"",
            endTime:"",
            remark1:"现场面试",
          }
          this.resetForm("ruxing")
          }
      },
      miaoshi(){
        this.caozuos = 1
        this.caozuo = true
      },
      // 搜索
      handleQuery(){
        this.getlist()
      },
      resume(row,ind){
        this.qqq={
          textarea1 :"",
          stayTime:"",
          qqq:[],
          remark1:"现场面试",
        }
        this.resetForm("ruxing")
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
      change(){
        this.$forceUpdate()
      },
      // 提交
      submit(){
        var {beginTime,endTime} = ""
        if(this.followstart==3){
          beginTime = this.qqq.value1[0]
          endTime = this.qqq.value1[1]
        }
        let form =new FormData()
        let zm ={
          list:this.tempID,
          type:this.followstart,
          followDetail:this.qqq.textarea1,
          trackingtime:this.qqq.stayTime,
          beginTime:beginTime,
          endTime:endTime,
          remark1:this.qqq.remark1,
        }
        form.append("followStatus",this.followstart)
        form.append("demandresumeId",this.tempID)
        form.append("followDetail",this.qqq.textarea1)
        form.append("zm",JSON.stringify(zm))
        if(this.followstart==7){
          form.append("id",this.tempID)
          this.getDicts("mar_company_period").then(response => {
            this.companyperiod = response.data;
          });
          this.comform = {
              settledCycle:null
          }
          this.resetForm("comform")
          getInputInformation(form).then(res=>{
            this.comform=res.data
            this.open4=true
            this.comform.settledCycle = res.data.paybackPeriod
          })
        }else{
          let that = this
          if(this.followstart==8||this.followstart==5||this.followstart==3){
            form.append("remark1",this.qqq.remark1)
            form.append("trackingtime",this.qqq.stayTime)
            form.append("beginTime ",beginTime)
            form.append("endTime",endTime)
            this.$refs["ruxing"].validate((valid) => {
                if(valid){
                  this.$confirm('确认"'+that.title+'"结果为"'+ that.list[that.followstart] + '"吗?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                  }).then(function() {
                    if(that.followstart==8){
                      return submitstart(form).then(res=>{
                      })
                    }else{
                      if(typeof that.tempID =="string"){
                        return submitstart(form)
                      }else{
                        return submitstarts(form)
                      }
                    }
                  }).then(() => {
                    this.msgSuccess("操作成功");
                    this.templists=[]
                    this.gettelist()
                    this.open=false
                    this.caozuo = false
                  }).catch(()=>{})
                }
              })
          }else{
            this.$confirm('确认"'+that.title+'"结果为"'+ that.list[that.followstart] + '"吗?', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function(){
              if(typeof that.tempID =="string"){
                return submitstart(form)
              }else{
                return submitstarts(form)
              }
            }).then(() => {
              this.caozuo = false
              this.open=false
              this.msgSuccess("操作成功");
              this.templists=[]
              this.gettelist()
            }).catch(()=>{})
          }

        }
      },

      // 确定
      submitForm2(){
        this.comform.id=this.tempID
        this.$refs["comform"].validate((valid) => {
          if (valid) {
            if(this.comform.salary - this.comform.servicePay >0){
              this.msgError("人员成本不能大于服务费")}else{
              let that = this
              this.$confirm('确认信息完整并入项吗?', "警告", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
              }).then(function() {
                return entryPersonnel(that.comform).then()
              }).then(() => {
                this.msgSuccess("操作成功");
                this.gettelist()
                this.open4=false
                this.open=false
              }).catch(()=>{})
            }
          }
        });
      },
      // 绑定
      handleUpdate(row){
          let form = new FormData()
          form.append("demandId",this.$route.query.row)
          findmubiao(form).then(res=>{
            if(res.code==200){
              this.bangding.templist2=[]
              addFollow(form).then(res=>{
                this.bangding.forms=res.data
                this.bangding.templist2=res.data
                this.bangding.id=this.$route.query.row
                this.bangding.open2 = true;
              })
            }
          })
      },
      // 预览
      preview(adinw,ind){
       let srcs = null
       if(ind == 1){
         if(adinw.resumePath){
           srcs = process.env.VUE_APP_BASE_API+adinw.resumePath
           this.open3 = true
           this.title = ""
         }else{
           this.msgError(adinw.customerName+"暂无简历原件请上传")
         }
       }else{
         if(adinw.resumeEnclosurepath){
           srcs = process.env.VUE_APP_BASE_API+adinw.resumeEnclosurepath
           this.open3 = true
           this.title = ""
         }else{
          this.msgError(adinw.customerName+"暂无简历附件请上传")
         }
       }
       this.src=`https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=${srcs}`
      },
      // 下载
      download(adinw,ind){
        let that = this
        clearInterval(this.timer)
        this.timer=setTimeout(function(){
          that.dow(adinw,ind)
          },500)
      },
      handleSelectionChange(selection){
        this.ids = selection
        this.multiple = !selection.length
      },

      dow(adinw,ind){
        // const baseURL = process.env.VUE_APP_BASE_API
        // window.location.href = baseURL + "/common/download/resource?name=" + encodeURI(adinw.resumeEnclosurepath)
        if(ind == 1){
          if(adinw.resumePath){
			this.downloads(adinw.resumePath)
          }else{
            this.msgError(adinw.customerName+"暂无简历原件请上传")
          }
        }else{
          if(adinw.resumeEnclosurepath){
			this.downloads(adinw.resumeEnclosurepath)
          }else{
           this.msgError(adinw.customerName+"暂无简历附件请上传")
          }
        }
      },
      submitq(){
        let that = this
        let i = 1
        this.ids.forEach(item=>{
          setTimeout(function(){
            that.dow(item,that.caozuos)
          },1500*i)
          i+=1

        })
        this.caozuo2 = false
      },
      downloadlist(){
        this.caozuo2 = true
        this.caozuos = 1
      },

      see(file,ind){
        this.dialogImageUrl = ""
        let srcs = process.env.VUE_APP_BASE_API
        if(file!==3){
          if(checkImg(file)){
            ind = 2
          }else{
            ind = 1
          }
        }
        if(ind==1){
          this.open3 = true
          this.title = '预览简历模板'
          this.src=`https://www.xdocin.com/xdoc?_func=form&_key=2iue7a6unfco3kaba2nayfib6i&_xdoc=${srcs+file}`
        }else if(ind==2){
          this.dialogVisible=true
          this.title2 = "面试题图片"
          this.dialogImageUrl = srcs+file
        }else{
          if(this.form.demandPic){
            this.dialogVisible=true
            this.title2 = "需求图片"
            this.dialogImageUrl = srcs+this.form.demandPic
          }else{
            this.msgError("暂无需求图片请上传")
          }
        }
      },
    }
  }
</script>

<style scoped>
  >>>.stepp:hover{
    cursor:pointer;
  }
  >>>.is-success{
    font-size: 14px;
  }
  >>>.is-wait{
    font-size: 14px;
  }
  >>>.is-error{
    font-size: 14px;
  }
  .centart{
    padding:20px 20px;
  }
  .div{
    border-left: 1px solid #DCDFE6;
    border-right: 1px solid #DCDFE6;
    border-bottom: 1px solid #DCDFE6;
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
