<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item  prop="customerCode">
        <el-input
          v-model="queryParams.customerCode"
          placeholder="请输入姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">查询</el-button>
      </el-form-item>
      <el-form-item  prop="startTime">
        <el-date-picker
            v-model="value1"
            type="daterange"
            size="small"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            @change="handleQuery">
        </el-date-picker>
      </el-form-item>
      <el-form-item  prop="corpName">
        <el-select v-model="queryParams.corpName" clearable placeholder="请选择客户" size="small" filterable @change="handleQuery">
          <el-option
              v-for="dict in entryList"
              :key="dict.marCustomerprojectpay.id"
              :label="dict.marCustomerprojectpay.corpName"
              :value="dict.marCustomerprojectpay.corpName"
            />
        </el-select>
      </el-form-item>
      <el-form-item  prop="settledCycle">
        <el-select v-model="queryParams.settledCycle"  placeholder="是否在项" size="small" @change="handleQuery">
          <el-option label="在项" :value="1" />
          <el-option label="出项" :value="2" />
        </el-select>
      </el-form-item>
      <el-form-item style="float: right;">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['perentry:entry:export']"
        >导出</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table v-loading="loading" :data="entryList" @selection-change="handleSelectionChange" style="width: 100%;">
      <el-table-column type="selection" width="55"  />
      <el-table-column label="姓名"  prop="customerName">
        <template slot-scope="scope">
          <el-button
          type="text"
          @click="handleUpdate(scope.row.marCustomerprojectpay.id)"
          >{{scope.row.customerName}}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="电话"  prop="customerTel" />

      <el-table-column label="入职公司"  >
        <template slot-scope="scope">
          <span>{{scope.row.marCustomerprojectpay.corpName}}</span>
        </template>
      </el-table-column>

      <el-table-column label="入职时间"  prop="syqstartTime" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.marCustomerprojectpay.syqstartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="技术方向">
        <template slot-scope="scope">
          <span>{{ professionIdopFormat(scope.row)}}</span>
        </template>
      </el-table-column>
      <el-table-column label="出项时间" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.marCustomerprojectpay.outofProjecttime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="人员成本">
        <template slot-scope="scope">
          <span>{{ scope.row.marCustomerprojectpay.salary }}</span>
        </template>
      </el-table-column>
      <el-table-column label="服务费用">
        <template slot-scope="scope">
          <span>{{ scope.row.marCustomerprojectpay.servicePay }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否缴纳社保"  prop="socSecopt">
        <template slot-scope="scope">
          <span>{{scope.row.marCustomerprojectpay.socSecopt==1?"是":"否"}}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否签署合同" prop="socSecopt">
        <template slot-scope="scope">
          <span>{{scope.row.socSecopt==1?"是":"否"}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="120" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
          v-if="scope.row.marCustomerprojectpay.outofProjecttime"
          type="text"
          icon="el-icon-view"
          @click="handleUpdate(scope.row.marCustomerprojectpay.id)"
          >查看人员信息</el-button>
          <el-button
          v-else
          type="text"
          icon="el-icon-edit"
          @click="handleUpdate(scope.row.marCustomerprojectpay.id)"
          >添加人员信息</el-button>
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
    <!-- 抽屉 -->
    <el-drawer
      title="信息"
      :visible.sync="drawer"
      :with-header="false"
      size="60%"
      @close="dra"
      >
      <div style="margin:0 3% 0 3%;border-left:1px solid #E6E6E6;">
      <div style=" padding:20px 3% 30px 2%; border-bottom: 1px solid #E6E6E6;">
        <div>
          <b>
            {{yxdemandone.customerName}}
          </b>
        </div>
         <el-form :inline="true" :model="yxdemandone" class="demo-form-inline">
           <el-form-item label="当前状态">
             <el-input  :placeholder="!yxdemandone.outofProjecttime?'在项':'出项'"  disabled size="small"></el-input>
           </el-form-item>
           <el-form-item  label="入职公司">
             <el-input  v-model="yxdemandone.corpName" disabled size="small"></el-input>
           </el-form-item>
          <el-form-item style="margin-left: 40px;">
            <el-button
              v-if="!yxdemandone.outofProjecttime"
              type="danger"
              @click="handleDelete"
              size="medium"
            >
              出项
            </el-button>
          </el-form-item>
         </el-form>
      </div>
      <div>
          <el-tabs v-model="activeName">
            <el-tab-pane label="联系人信息" name="popmsg">
               <div style="display: flex; justify-content: space-around;">
                 <el-form label-position="left" label-width="100px" :model="yxdemandone" :rules="rules">
                    <b>基本信息</b>
                    <p></p>
                    <el-form-item label="姓名">
                      <span>{{yxdemandone.customerName}}</span>
                    </el-form-item>
                    <el-form-item label="电话">
                      <span>{{yxdemandone.customerTel}}</span>
                    </el-form-item>
                    <el-form-item label="入职时间" >
                      <span>{{yxdemandone.syqstartTime}}</span>
                    </el-form-item>
                    <el-form-item label="转正时间" >
                      <span>{{yxdemandone.syqEndtime}}</span>

                    </el-form-item>
                    <el-form-item label="人员成本">
                      <span>{{yxdemandone.salary}}</span>
                    </el-form-item>
                    <el-form-item label="服务费">
                      <span>{{yxdemandone.service_Pay}}</span>
                    </el-form-item>
                    <el-form-item label="是否缴社保">
                      <span>{{yxdemandone.socSecopt==1?"是":"否"}}</span>
                    </el-form-item>
                 </el-form>
                 <el-form label-position="left" label-width="90px" :model="yxdemandone">
                   <b>外包公司信息</b>
                   <p></p>
                    <el-form-item label="身份证正面">
                      <el-button :disabled="yxdemandone.outofProjecttime!==undefined" type="text" @click="upfile(0)" v-if="!marCertificates1.idcardPositive">
                          点击上传
                      </el-button>
                      <el-button type="text" @click="seefile(0)" v-else>
                          点击预览
                      </el-button>
                    </el-form-item>
                    <el-form-item label="身份证反面">
                      <el-button :disabled="yxdemandone.outofProjecttime!==undefined" type="text" @click="upfile(1)" v-if="!marCertificates1.idcardReverse">
                          点击上传
                      </el-button>
                      <el-button  type="text" @click="seefile(1)" v-else>
                          点击预览
                      </el-button>
                    </el-form-item>
                    <el-form-item label="毕业证">
                      <el-button :disabled="yxdemandone.outofProjecttime!==undefined" type="text" @click="upfile(2)" v-if="!marCertificates1.diploma">
                          点击上传
                      </el-button>
                      <el-button type="text" @click="seefile(2)" v-else>
                          点击预览
                      </el-button>
                    </el-form-item>
                    <el-form-item label="学位证">
                      <el-button  :disabled="yxdemandone.outofProjecttime!==undefined"type="text" @click="upfile(3)" v-if="!marCertificates1.academic">
                          点击上传
                      </el-button>
                      <el-button type="text" @click="seefile(3)" v-else>
                          点击预览
                      </el-button>
                    </el-form-item>
                    <el-form-item label="保密协议">
                      <el-button :disabled="yxdemandone.outofProjecttime!==undefined" type="text" @click="upfile(4)" v-if="!marCertificates1.confidentialityAgreement">
                          点击上传
                      </el-button>
                      <el-button type="text" @click="seefile(4)" v-else>
                          点击预览
                      </el-button>
                    </el-form-item>
                    <el-form-item label="劳动合同">
                      <el-button :disabled="yxdemandone.outofProjecttime!==undefined" type="text" @click="upfile(5)" v-if="!marCertificates1.serviceContract">
                          点击上传
                      </el-button>
                      <el-button type="text" @click="seefile(5)" v-else>
                          点击预览
                      </el-button>
                    </el-form-item>
                 </el-form>
               </div>
            </el-tab-pane>
            <el-tab-pane label="借用物品">
              <el-button  v-if="!yxdemandone.outofProjecttime" type="primary" @click="handAdd">新建借用记录</el-button>
              <p></p>
              <el-table v-loading="loadings" :data="marBorrows">
                <el-table-column label="借用时间"  prop="borrowTime" width="100">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.borrowTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="物品"  prop="borrowSth" />
                <el-table-column label="型号"  prop="borrowModel"/>

                <el-table-column label="是否签署协议"  prop="isAgreement" width="180">
                  <template slot-scope="scope">
                    <span>{{ scope.row.isAgreement==1?"是":"否"}}</span>
                  </template>
                </el-table-column>
                <el-table-column label="归还日期"  prop="returnTime" width="180">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.returnTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>

                <el-table-column label="操作"  class-name="small-padding fixed-width" fixed="right">
                  <template slot-scope="scope">
                    <el-button
                      v-if="!scope.row.returnTime"
                      size="mini"
                      type="text"
                      @click="preview(scope.row)"
                    >归还</el-button>
                  </template>
                </el-table-column>
              </el-table>

            </el-tab-pane>
            <el-tab-pane label="入项记录">

              <el-table  :data="Listmarcustomerp">
                <el-table-column label="入项公司" align="left" prop="corpName" width="100"/>
                <el-table-column label="入职项目" align="left" prop="projectName" width="100"/>
                <el-table-column label="入职日期"  prop="syqstartTime" width="100">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.syqstartTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="出项日期" prop="outofProjecttime" width="100">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.outofProjecttime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="转正日期"  prop="syqEndtime" width="100">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.syqEndtime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="是否缴社保"  prop="socSecopt"   width="100">
                  <template slot-scope="scope">
                    <span>{{ scope.row.socSecopt==1?"是":"否"}}</span>
                  </template>
                </el-table-column>

                <el-table-column label="人员成本" align="left" prop="salary" />
                <el-table-column label="服务费" align="left" prop="servicePay"/>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="人员成本调整记录">
              <el-button v-if="!yxdemandone.outofProjecttime"  type="primary" @click="handAdd(2)">新建人员成本调整</el-button>
              <p></p>

              <el-table v-loading="loadings" :data="marAdsalaries">
                <el-table-column label="所在公司"  prop="corpName" />
                <el-table-column label="所在项目"  prop="projectName" />
                <el-table-column label="入项时间"  prop="syqstartTime" width="100">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.syqstartTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="调前人员成本"  prop="beforeSalary" />
                <el-table-column label="调后人员成本"  prop="afterSalary"/>

                <el-table-column label="调整日期"  prop="adjustTime" width="180">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.adjustTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="调整原因"  prop="adjustDetail"/>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="服务费调整记录">
              <el-button v-if="!yxdemandone.outofProjecttime"  type="primary" @click="handAdd(3)">新建服务费调整</el-button>
              <p></p>
              <el-table v-loading="loadings" :data="marServicepays">
                <el-table-column label="所在公司"  prop="corpName" />
                <el-table-column label="所在项目"  prop="projectName" />
                <el-table-column label="入项时间"  prop="syqstartTime" width="100">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.syqstartTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="调前服务费"  prop="beforeServicepay" />
                <el-table-column label="调后服务费"  prop="afterServicepay"/>
                <el-table-column label="调整日期"  prop="adjustTime" width="100">
                  <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.adjustTime, '{y}-{m}-{d}') }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="调整原因"  prop="adjustDetail"/>
              </el-table>
            </el-tab-pane>
          </el-tabs>
       </div>
      </div>
    </el-drawer>
    <el-dialog :title="title2" :visible.sync="open2" width="500px">
      <el-upload
        action="#"
        list-type="picture-card"
        :auto-upload="false"
        ref="file"
        :limit="1"
        :file-list="filelist"
        :on-change="handleRemov"
        :on-exceed="handleExceed"
        accept=".png,.jpg,"
        >
        <div slot="tip" class="el-upload__tip">仅支持上传jpg/png文件</div>
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
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm2">确 定</el-button>
        <el-button @click="open2=false">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogVisible" width="500px" :title="title2">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
    <!-- 添加或修改入项对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="归还时间" prop="returnTime">
          <el-date-picker clearable size="small" style="width: 200px"
           :picker-options="pickerOptions3"
            v-model="form.returnTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择归还时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 出项信息 -->
    <el-dialog :title="title3" :visible.sync="open3" width="500px">
      <el-form ref="form3" :model="form3" :rules="rules" class="form" label-width="100px" :validate-on-rule-change="false">
        <el-form-item label="离项原因" prop="quitProreason">
          <el-select v-model="form3.quitProreason" placeholder="请选择" size="small">
            <el-option
                v-for="dict,index in customerleve"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-select>
        </el-form-item>
        <el-form-item label="离项时间" prop="outofProjecttime">
          <el-date-picker
           :picker-options="pickerOptions3"
           type="date"
          placeholder="选择离项时间"
          v-model="form3.outofProjecttime"
          size="small"
          style="width: 100%;"
          value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="quitRemark">
            <el-input v-model="form3.quitRemark" placeholder="请输入备注" type="textarea"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm3">确 定</el-button>
        <el-button @click="open3=false">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 新建借用记录 -->
    <el-dialog :title="title4" :visible.sync="open4" width="500px" >
      <el-form ref="form4" :rules="rules" :model="form4" label-width="110px" class="form" :validate-on-rule-change="false">
        <el-form-item label="借用时间" prop="borrowTime">
              <el-date-picker type="date" placeholder="选择借用时间" v-model="form4.borrowTime" value-format="yyyy-MM-dd" size="small" :picker-options="pickerOptions3" style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="物品" prop="borrowSth">
            <el-input v-model="form4.borrowSth" placeholder="请输入物品"  size="small" />
        </el-form-item>
        <el-form-item label="型号" prop="borrowModel">
            <el-input v-model="form4.borrowModel" placeholder="请输入型号"  size="small" />
        </el-form-item>
        <el-form-item label="是否签署协议" prop="isAgreement">
            <el-radio-group v-model="form4.isAgreement" size="small">
                <el-radio :label="1">是</el-radio>
                <el-radio :label="2">否</el-radio>
            </el-radio-group>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm4">确 定</el-button>
        <el-button @click="open4=false">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 新建工资调整记录 -->
    <el-dialog :title="title5" :visible.sync="open5" width="500px" >
      <el-form ref="form5" :rules="rules" :model="form5" label-width="110px" class="form">
        <el-form-item label="人员成本调整前" prop="beforeSalary" v-if="title5=='新建人员成本调整记录'">
            <el-input v-model="form5.beforeSalary" placeholder="请输入人员成本调整前"  size="small" disabled/>
        </el-form-item>
        <el-form-item label="服务费调整前" prop="beforeServicepay" v-else>
            <el-input v-model="form5.beforeServicepay" placeholder="请输入服务费调整前"  size="small" disabled/>
        </el-form-item>
        <el-form-item label="人员成本调整后" prop="afterSalary" v-if="title5=='新建人员成本调整记录'">
            <el-input v-model="form5.afterSalary" placeholder="请输入人员成本调整后"  size="small" />
        </el-form-item>
        <el-form-item label="服务费调整后" prop="afterServicepay" v-else>
            <el-input v-model="form5.afterServicepay" placeholder="请输入服务费调整后"  size="small" />
        </el-form-item>
        <el-form-item label="调整时间" prop="adjustTime">
              <el-date-picker type="date"
              :picker-options="pickerOptions3"
              placeholder="选择调整时间"
              v-model="form5.adjustTime"
              size="small"
              style="width: 100%;"
              value-format="yyyy-MM-dd">
              </el-date-picker>
        </el-form-item>
        <el-form-item label="调整原因" prop="adjustDetail">
            <el-input v-model="form5.adjustDetail" placeholder="请输入调整原因" type="textarea"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm5">确 定</el-button>
        <el-button @click="open5=false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {getwupin, listEntry, getEntry, delEntry, addEntry, updateEntry, exportEntry, addgongzi,addfuwufei,putwupin} from "@/api/perentry/entry";
import { getToken } from "@/utils/auth";
import {debounce} from "@/utils/ruoyi.js"
export default {
  name: "Entry",
  data() {
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
    return {
      pickerOptions3:{
        disabledDate:(time) => {
              return time.getTime() >  Date.now()
        }
      },
      // 调整工资
      title5:"",
      form5:{},
      open5:false,
      // 借用记录
      title4:"",
      form4:{},
      open4:false,

      customerleve:[],
      disabled: false,
      name:"",
      loadings:false,
      activeName:"popmsg",
      // 服务费记录
      marServicepays:[],
      // 工资记录
      marAdsalaries:[],
      // 入项信息
      Listmarcustomerp:[],
      // 借用物品
      marBorrows:[],
      // 证件照
      marCertificates1:{},
      // 入项基本信息
      yxdemandone:{},
      filelist:[],
      dialogVisible:false,
      // 查看照片路径
      dialogImageUrl:"",
      // 抽屉
      drawer:false,
      // 时间
      value1:"",
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single:null,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 入项表格数据
      entryList: [],
      professionIdoptions:[],
      // 弹出层标题
      title: "",
      // 上传简历
      open2:false,
      title2:"",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        customerCode:null,
        endTime: null,
        startTime: null,
        corpName: null,
        settledCycle:null,
      },
      // 表单参数
      form: {},
      form3:{},
      // 表单校验
      rules: {
        borrowTime: [{
          required: true,
          message: "借用时间不能为空",
          trigger: ["blur", "change"]
        }, ],
        borrowSth: [{
          required: true,
          message: "借用物品不能为空",
          trigger: ["blur", "change"]
        }, ],
        borrowModel: [{
          required: true,
          message: "型号不能为空",
          trigger: ["blur", "change"]
        }, ],
        isAgreement: [{
          required: true,
          message: "是否签署协议不能为空",
          trigger: ["blur", "change"]
        }, ],
        afterSalary: [{
          required: true,
          validator: price,
          trigger: ["blur", "change"]
        },],
        afterServicepay: [{
          required: true,
          validator: price,
          trigger: ["blur", "change"]
        },],
         adjustTime: [{
           required: true,
           message: "调整时间不能为空",
           trigger: ["blur", "change"]
         },],
         returnTime: [{
           required: true,
           message: "归还时间不能为空",
           trigger: ["blur", "change"]
         },],
         quitProreason:[
           {required: true,
           message: "离项原因不能为空",
           trigger: ["blur", "change"]}
         ],
         outofProjecttime: [{
           required: true,
           message: "离项时间不能为空",
           trigger: ["blur", "change"]
         },],
         quitRemark: [{
           required: true,
           message: "离项备注不能为空",
           trigger: ["blur", "change"]
         },],

      },
      open3:false,
      title3:"",
    };
  },
  watch:{
    "value1.length":{
      handler(newValue, oldValue) {
        if (newValue>1) {
            this.queryParams.settledCycle=1
        }
      }
    }
  },
  created() {
    this.getDicts("per_customerinfo_professionid").then(response => {
      this.professionIdoptions = response.data;
    });
    this.getList();
    // 离项原因
    this.getDicts("outof_project_cause").then(response => {
      this.customerleve = response.data;
    });
  },
  methods: {
    professionIdopFormat(row, column) {
      return this.selectDictLabel(this.professionIdoptions, row.technologyDirection);

    },
    /** 查询入项列表 */
    getList() {
      if(this.value1==null){
        this.value1 = ""
        this.queryParams.startTime = null
        this.queryParams.endTime =null
      }else{
        this.queryParams.startTime = this.value1[0]
        this.queryParams.endTime = this.value1[1]
      }
      this.loading = true;
      listEntry(this.queryParams).then(response => {
        this.entryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
    },
    // 表单重置
    // reset() {
    //   this.form = {
    //     id: null,
    //     customerCode: null,
    //     corpCode: null,
    //     corpName: null,
    //     demandId: null,
    //     projectName: null,
    //     startTime: null,
    //     endTime: null,
    //     settledCycle: null,
    //     salary: null,
    //     servicePay: null,
    //     outofProjecttime: null,
    //     quitProreason: null,
    //     quitRemark: null,
    //     opercode: null,
    //     operTime: null,
    //     syqstartTime: null,
    //     syqEndtime: null,
    //     socSecopt: null,
    //     remark: null
    //   };
    //   this.resetForm("form");
    // },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    dra(){
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.value1 = ""
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },

    handleExceed(){
      this.msgError(`当前限制选择 1 个文件`);
    },
    handleRemov(value){
      this.single=value.raw;
    },
    handleRemove(file) {
        this.$refs.file.clearFiles()
        this.single = null
    },
    submitForm2:debounce(function(){
      this.submitForms2()
    },500),
    submitForms2(){
      if(this.single==null){
        this.msgError("上传图片不能为空")
      }else{
        let form = new FormData()
        form.append("marcusId",this.yxdemandone.id)
        form.append("file",this.single)
        form.append("id",this.marCertificates1.id)
        if(this.title2=="上传身份证正面"){
          form.append("photo",1)
        }else if(this.title2=="上传身份证反面"){
          form.append("photo",2)
        }else if(this.title2=="上传毕业证"){
          form.append("photo",3)
        }else if(this.title2=="上传学位证"){
          form.append("photo",4)
        }else if(this.title2=="上传保密协议"){
          form.append("photo",5)
        }else if(this.title2=="上传劳动合同"){
          form.append("photo",6)
        }
        addEntry(form).then(res=>{
          this.open2 = false
          this.msgSuccess("上传成功")
          this.handleUpdate(this.yxdemandone.id)
        })
      }
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加入项";
    },
    // 归还
    preview(row){
        this.open=true
        this.form = {}
        this.form.id = row.id
        this.title = "归还物品"
    },

    // 添加借用记录
    handAdd(i){
      this.reset();
      if(i==2){
        this.form5.beforeSalary = this.yxdemandone.salary
        this.open5 = true
        this.title5 = "新建人员成本调整记录"
        if (this.$refs["form5"] !== undefined) {
              this.$refs["form5"].resetFields()
           }
      }else if(i==3){
        if (this.$refs["form5"] !== undefined) {
              this.$refs["form5"].resetFields()
           }
        this.form5.beforeServicepay = this.yxdemandone.service_Pay
        this.open5 = true
        this.title5 = "新建服务费调整记录"
      }else{
        if (this.$refs["form4"] !== undefined) {
              this.$refs["form4"].resetFields()
           }
        this.open4 = true
        this.title4 = "新建借用记录"
      }
    },
    // 借用记录
    submitForm4:debounce(function(){this.submitForms4()},500),
    submitForms4(){
      this.$refs["form4"].validate(valid=>{
        if(valid){
          this.form4.marcusId = this.yxdemandone.id
          updateEntry(this.form4).then(response => {
            this.msgSuccess("添加成功");
            this.open4 = false;
            this.handleUpdate(this.form4.marcusId);
          });
        }
      })
    },
    submitForm5:debounce(function(){this.submitForms5()},500),
    submitForms5(){
      this.$refs["form5"].validate(valid=>{
        if(valid){
          this.form5.marcusId = this.yxdemandone.id
          if(this.title5=="新建人员成本调整记录"){
            addgongzi(this.form5).then(response => {
              this.msgSuccess("添加成功");
              this.open5 = false;
              this.handleUpdate(this.form5.marcusId);
            });
          }else{
            addfuwufei(this.form5).then(response => {
              this.msgSuccess("添加成功");
              this.open5 = false;
              this.handleUpdate(this.form5.marcusId);
            });
          }
        }
      })
    },

    // 上传文件
    upfile(ind){
      this.filelist=[]
      if(ind==0){
        // 上传身份证
        this.title2 = "上传身份证正面"
      }else if(ind==1){
        // 上传身份证
        this.title2 = "上传身份证反面"
      }else if(ind==2){
        // 上传毕业证
        this.title2 = "上传毕业证"
      }else if(ind==3){
        // 上传学位证
        this.title2 = "上传学位证"
      }else if(ind==4){
        // 上传保密协议
        this.title2 = "上传保密协议"
      }else if(ind==5){
        // 上传劳动合同
        this.title2 = "上传劳动合同"
      }
      this.open2 = true
    },

    // 预览文件
    seefile(ind){
       let srcs = process.env.VUE_APP_BASE_API
       this.dialogImageUrl = ""
       if(ind==0){
         // 预览身份证
         this.title2 = "预览身份证正面"
         this.dialogImageUrl = srcs+this.marCertificates1.idcardPositive
       }else if(ind==1){
         // 预览身份证
         this.title2 = "预览身份证反面"
         this.dialogImageUrl = srcs+this.marCertificates1.idcardReverse
       }else if(ind==2){
         // 预览毕业证
         this.title2 = "预览毕业证"
          this.dialogImageUrl = srcs+this.marCertificates1.diploma
       }else if(ind==3){
         // 预览学位证
         this.title2 = "预览学位证"
          this.dialogImageUrl = srcs+this.marCertificates1.academic
       }else if(ind==4){
         // 预览保密协议
         this.title2 = "预览保密协议"
          this.dialogImageUrl = srcs+this.marCertificates1.confidentialityAgreement
       }else if(ind==5){
         // 预览劳动合同
         this.title2 = "预览劳动合同"
          this.dialogImageUrl = srcs+this.marCertificates1.serviceContract
       }
       this.dialogVisible=true
    },

    /** 添加人员信息按钮操作 */
    handleUpdate(row) {
      getEntry(row).then(response => {
        this.yxdemandone = response.data.MarCustomePerinfo
        this.marAdsalaries = response.data.marAdsalaries
        this.marBorrows = response.data.marBorrows
        this.marCertificates1 = response.data.marCertificates1
        this.marServicepays = response.data.marServicepays
        this.Listmarcustomerp = response.data.Listmarcustomerp
        this.drawer=true
      });
    },
    /** 提交按钮 */
    submitForm:debounce(function(){this.submitForms()},500),
    submitForms() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.isReturn=1
          putwupin(this.form).then(res=>{
            this.msgSuccess("归还成功");
            this.open=false
            this.handleUpdate(this.yxdemandone.id);
          })
        }
      });
    },
    /** 出项按钮操作 */
    handleDelete(row) {
      getwupin(this.yxdemandone.id).then(res=>{
          this.reset()
          if(res==1){
            this.$confirm('该人员还有物品未归还是否确认继续出项?', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
            }).then(() => {
              this.open3 = true
              this.title3 = "出项信息"
            }).catch();
          }else{
            this.open3 = true
            this.title3 = "出项信息"
          }
      })
      // this.resetForm("form3");
    },
    submitForm3:debounce(function(){this.submitForms3()},500),
    submitForms3(){
      this.$refs["form3"].validate(valid => {
        if (valid) {
          let _this = this
          let name = this.yxdemandone.customerName
          this.form3.id = this.yxdemandone.id
          this.$confirm('确认出项"'+name+'"吗?', "警告", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(function() {
            delEntry(_this.form3).then(res=>{
              _this.msgInfo("出项成功")
            })
          }).then(() => {
            this.open3 = false
            this.handleUpdate(this.yxdemandone.id);
          }).catch();
        }
      });

    },
    reset() {
      this.form = {
        borrowTime: null,
        borrowSth: null,
        borrowModel: null,
        isAgreement: null,
        afterSalary: null,
        afterServicepay: null,
        returnTime: null,
        adjustTime: null,
        quitProreason: null,
        outofProjecttime: null,
        quitRemark: null,
      };
      this.form3 = {
        borrowTime: null,
        borrowSth: null,
        borrowModel: null,
        isAgreement: null,
        afterSalary: null,
        afterServicepay: null,
        returnTime: null,
        adjustTime: null,
        quitProreason: null,
        outofProjecttime: null,
        quitRemark: null,
      };
      this.form4 = {
        borrowTime: null,
        borrowSth: null,
        borrowModel: null,
        isAgreement: null,
        afterSalary: null,
        afterServicepay: null,
        returnTime: null,
        adjustTime: null,
        quitProreason: null,
        outofProjecttime: null,
        quitRemark: null,
      };
      this.form5 = {
        borrowTime: null,
        borrowSth: null,
        borrowModel: null,
        isAgreement: null,
        afterSalary: null,
        afterServicepay: null,
        returnTime: null,
        adjustTime: null,
        quitProreason: null,
        outofProjecttime: null,
        quitRemark: null,
      };
      this.resetForm("form");
      this.resetForm("form3");
      this.resetForm("form4");
      this.resetForm("form5");
     },

    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有入项数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportEntry(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
<style scoped>
  >>>.el-tabs__item:focus.is-active.is-focus:not(:active) {
        -webkit-box-shadow: none !important;
        box-shadow: none !important;
  }
    >>>.el-tabs__header{
      background: #F5F5F9;
      padding-left:5%;
    }
    >>>.el-tabs__content{
      padding:20px 3% 0 3%;
    }
     >>>.el-drawer.rtl{
          overflow: auto;
      }
      .span{
        color: #2C2C2C;
      }
      .sp{
        font-size: 11px;
        color: #8A8A8A;
      }
      >>>.el-form-item__label{
        color: #909399;
      }
    .form{
      width: 350px;
    }
</style>
