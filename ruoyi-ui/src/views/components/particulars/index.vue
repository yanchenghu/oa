<template>
  <div>
      <el-dialog title="绑定" :visible.sync="bangding.open2" width="500px" append-to-body>
        <el-input v-model="searchmsg" placeholder="请输入" clearable size="small" @keyup.enter.native="search" style="width: 170px;margin-right: 10px;"/>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="search">查询</el-button>
        <p></p>
        <el-radio-group v-model="corpnamelist" v-loading="bangding.loading2">
             <el-radio v-for="city in bangding.templist2" :label="city.customer_code" :key="city.customer_code" style="width:155px;margin-bottom: 10px;">{{city.customer_name}}-{{city.customer_tel}}</el-radio>
        </el-radio-group>
        <div v-show="bangding.templist2.length==0 && bangding.forms.length!==0" style="text-align: center;
        ">暂无该简历信息</div>
        <div v-show="bangding.templist2.length==0 && bangding.forms.length==0" style="text-align: center; ">暂无简历信息,先去抢占几份去吧</div>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确定</el-button>
          <el-button @click="bangding.open2=false">取 消</el-button>
        </div>
      </el-dialog>

      <el-dialog :visible.sync="opens" width="400px" :title="titles" append-to-body>
          <el-upload action="wqewq" ref="file" class="upload-demo" drag accept=".docx,.doc,.pdf" :limit="1" :on-exceed="handleExceed" :auto-upload="false" :on-change="oplodad" :before-remove="upoplodad">
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
        searchmsg:"",
        wen:true,
        timer:null,
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
        this.bangding.loading2=true
        if(this.searchmsg==""){
        setTimeout(function(){
          that.bangding.templist2=that.bangding.forms
          that.bangding.loading2=false
        },1000)
        }else{
          clearInterval(this.timer)
          this.timer=setTimeout(function(){
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
            if(this.$refs.file!==undefined){
              this.upoplodad()
            }
            this.titles = "上传附件"
            this.opens = true
          }
        },
        submitForms:debounce(function(){this.submitFor()}),
        submitFor(){
          let zm={
            customerCode:this.corpnamelist,
            demandId:this.bangding.id
          }
          let form= new FormData()
          form.append("zm",JSON.stringify(zm))
          if(this.$refs.file.uploadFiles[0] == null){
            this.msgError("请选择简历附件")
          }else{
            form.append("resumeEnclosurepath",this.$refs.file.uploadFiles[0].raw)
            delFollow(form).then(res=>{
              this.msgSuccess("绑定成功")
              this.opens=false
              this.bangding.open2=false
              this.$emit("gettelist")
              this.$emit("getList")
            })
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
