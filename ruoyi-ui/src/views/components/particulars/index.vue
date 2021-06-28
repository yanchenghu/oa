<template>
  <div>
      <el-dialog title="绑定" :visible.sync="bangding.open2" width="500px" append-to-body >
        <el-input v-model="searchmsg" placeholder="请输入姓名或电话" clearable size="small" @keyup.enter.native="search" style="width: 170px;margin-right: 10px;"/>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="search">查询</el-button>
        <p></p>
        <div style="height: 300px; overflow: auto;">
          <el-radio-group v-model="corpnamelist" v-loading="bangding.loading2">
               <el-radio v-for="city in bangding.templist2" :label="city.customer_code" :key="city.customer_code" style="width:155px;margin-bottom: 10px;">{{city.customer_name}}-{{city.customer_tel}}</el-radio>
          </el-radio-group>
        </div>

        <div v-show="bangding.templist2.length==0 && bangding.forms.length!==0" style="text-align: center;
        ">暂无该简历信息</div>
        <div v-show="bangding.templist2.length==0 && bangding.forms.length==0" style="text-align: center; ">暂无简历信息,先去抢占几份去吧</div>

        <div slot="footer" class="dialog-footer" >
          <el-button type="primary" @click="submitForm">确定</el-button>
          <el-button @click="bangding.open2=false">取 消</el-button>
        </div>
      </el-dialog>

      <el-dialog :visible.sync="opens" width="600px" :title="titles" append-to-body>
          <el-upload style="padding-left: 15%;" action="wqewq" ref="file" class="upload-demo" drag accept=".docx,.doc,.pdf" :limit="1" :on-exceed="handleExceed" :auto-upload="false" :on-change="oplodad" :before-remove="upoplodad">
            <div v-if="wen">
              <i class="el-icon-circle-plus" style="color:#0081FF;
              font-size: 67px;
              margin: 40px 0 16px;
              line-height: 50px;"></i>
              <div><b>点击上传简历</b></div>
              <div class="el-upload__text" style="width: 210px;margin: 0 auto;">简历支持.docx .doc .pdf格式，大小不超过500kb，拖拽文件可直接上传</div>
            </div>
            <div v-else>
              <i class="el-icon-success" style="color:rgb(0,218,175);
                font-size: 67px;
                  margin: 40px 0 16px;
                  line-height: 50px;"></i>
              <div><b>上传成功 </b></div>
            </div>
          </el-upload>
          <p></p>
          <span style="color: #303133;">简历是否满足要求</span>
          <div style="padding: 10px 0  0 22px;">
            <div style="display: flex;" v-for="item,i in ganglist">
              <div style="width: 80%; background: #f7f9fd;margin: 10px 20px 0 0;padding:10px 25px;color: #303133;"><span style="color: red;position: relative;margin-left: -13px;" v-if="item.isNecessary==1">★</span> {{item.sort}}、{{item.jobRequirements}}</div>
              <el-radio style="margin-top: 15px;" v-model="item.xuanze" :label="1">是</el-radio>
              <el-radio style="margin-top: 15px;" v-model="item.xuanze" :label="2">否</el-radio>
            </div>
            <br>
            <br>
            <span style="color: red;" >★</span> <span>加星为岗位必须满足，简历不满足不能绑定</span>
            <br>
            <br>
            <el-tag>你最近10天的简历通过率为{{format}}%</el-tag>
          </div>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForms">确定</el-button>
            <el-button @click="opens=false">取 消</el-button>
          </div>
      </el-dialog>
  </div>
</template>

<script>
  import {
    delFollow,
    queryResumeSalary,
    acquisitionList
  } from "@/api/demand/binding";
  import { debounce } from "@/utils/ruoyi"
  export default {
    props:{
      bangding:{
        type:Object,
      },
    },
    data(){
      return {
        corpnamelist:null,
        opens:false,
        titles:"",
        radio:null,
        searchmsg:"",
        wen:true,
        timer:null,
        ganglist:[],
        idlist:[],
        format:"",
      }
    },
    watch:{
      "bangding.open2"(newValue, oldValue){
        if(newValue==true){
          this.searchmsg =""
        }
      }
    },
    methods:{
      // 搜索简历
      search(){
        this.corpnamelist=null
        let that = this
        that.bangding.loading2=true
        if(that.searchmsg==""){
        setTimeout(function(){
          that.bangding.templist2=that.bangding.forms
          that.bangding.loading2=false
        },1000)
        }else{
          clearInterval(that.timer)
          that.timer=setTimeout(function(){
            var data= that.bangding.forms.filter(item=>{
              return item.customer_name == that.searchmsg || item.customer_tel==that.searchmsg
             })
            that.bangding.templist2 = data
            that.bangding.loading2=false
          },1000)
        }
      },
      // 确定按钮
      submitForm(){
          if(this.corpnamelist==null){
            this.msgError("请选择简历")
          }else{
            let form= new FormData()
            form.append("customerCode",this.corpnamelist)
            queryResumeSalary(form).then(res=>{
              if(res=="可以绑定"){
                acquisitionList(this.bangding.id).then(res=>{
                  this.ganglist = res.data.marDemandRequirements
                   this.format = res.data.format
                  if(this.$refs.file!==undefined){
                    this.upoplodad()
                  }
                  this.titles = "上传附件与要求"
                  this.opens = true
                  this.idlist = []
                })
              }else{
                let that = this
                this.$confirm('该简历与需求不符"'+ res+'"是否去修改', "警告", {
                  confirmButtonText: "去修改",
                  cancelButtonText: "取消",
                  type: "warning"
                }).then(function(){
                    that.$router.push({path:"/record/manually",query:{customerCode:that.corpnamelist}});
                }).then(() => {
                  this.bangding.open2=false
                }).catch(()=>{})
              }
            })
          }
        },

        bixuan(){
          var a;
          this.ganglist.forEach(item=>{
            if(item.isNecessary == 1){
              if(item.xuanze !== 1){
                a = 1
              }
            }
          })
          return a
        },
        submitForms:debounce(function(){this.submitFor()}),

        submitFor(){
          if(this.bixuan()==1){
            this.msgError("必须满足项没有选择")
          }else{




              if(this.$refs.file.uploadFiles[0] == null){
                this.msgError("请选择简历附件")
              }else{
                  if(this.$refs.file.uploadFiles[0].raw.size>10*1000*1000){
                  this.msgError("文件过大，请重新上传")
                  this.upoplodad()
                  }else{
                    this.ganglist.forEach(item=>{
                      if(item.xuanze == 1){
                        this.idlist.push({requirementsId:item.id,sort:item.sort})
                      }
                    })
                    let zm={
                      customerCode:this.corpnamelist,
                      demandId:this.bangding.id,
                      marDemandresumeRequirement:this.idlist
                    }
                    let form= new FormData()
                    form.append("zm",JSON.stringify(zm))
                    form.append("resumeEnclosurepath",this.$refs.file.uploadFiles[0].raw)
                    if(this.ganglist.length ==0){
                      delFollow(form).then(res=>{
                        this.msgSuccess("绑定成功")
                        this.opens=false
                        this.bangding.open2=false
                        this.$emit("gettelist")
                        this.$emit("getList")
                      })
                    }else{
                      if(this.idlist.length==0){
                        this.msgError("请选择要求")
                      }else{
                        delFollow(form).then(res=>{
                          this.msgSuccess("绑定成功")
                          this.opens=false
                          this.bangding.open2=false
                          this.$emit("gettelist")
                          this.$emit("getList")
                        })
                      }
                    }
                  }
              }

          }

        },
        oplodad(){
            this.wen = false
        },
        handleExceed(){
          this.msgError(`当前限制选择 1 个文件`);
        },
        upoplodad(){
          this.$refs.file.clearFiles()
          this.wen = true
        },
    }

  }
</script>

<style>
</style>
