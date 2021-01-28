<template>
  <div class='app-container'>
    <el-form :model="datalist" :rules="rules" ref="form" label-position="right" label-width="110px">
      <el-form-item label="通过简历数量" prop="adopt">
        <el-input-number v-model="datalist.adopt" :min="0"  ></el-input-number>
      </el-form-item>
      <el-form-item label="绑定简历数" prop="binding">
        <el-input-number v-model="datalist.binding" :min="0"  ></el-input-number>
      </el-form-item>
      <el-form-item label="入项数量" prop="entry">
        <el-input-number v-model="datalist.entry" :min="0"  ></el-input-number>
      </el-form-item>
      <el-form-item label="面试通过数量" prop="interview">
        <el-input-number v-model="datalist.interview" :min="0"  ></el-input-number>
      </el-form-item>
      <el-form-item>
         <el-button type="primary" @click="submitForm">确 定</el-button>
         <el-button @click="rest">重 置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

  import { getList,setdata} from "@/api/taskVolume/sitting.js"
  export default {
    name:"sitting",
    data(){
      return{
        datalist:{},
        rules:{
          adopt:[{
             required: true,
             message: "通过简历数量不能为空",
             trigger: ["blur", "change"]
            }
          ],
          binding:[{
             required: true,
             message: "绑定简历数不能为空",
             trigger: ["blur", "change"]
            }
          ],
          entry:[{
             required: true,
             message: "入项数量不能为空",
             trigger: ["blur", "change"]
            }
          ],
          interview:[{
             required: true,
             message: "面试通过数量不能为空",
             trigger: ["blur", "change"]
            }
          ],
        },
      }
    },
    created() {
        this.getlist()
    },
    methods:{
      getlist(){
        getList().then(res=>{
          this.datalist = res[0]
        })
      },
      submitForm(){
        this.$refs["form"].validate(valid => {
          if(valid){
            setdata(this.datalist).then(res=>{
              this.dialogTableVisible = false
              this.getlist()
              this.msgSuccess("修改成功")
            })
          }
        })
      },
      rest(){
        this.datalist = {
          adopt:0,
          binding:0,
          entry:0,
          interview:0,
        }
      }
    }
  }
</script>

<style lang="scss" scoped>
  .dashboard-editor-container {
    padding: 32px;
    background-color: rgb(240, 242, 245);
    position: relative;
  .panel-group {
    margin-top: 18px;
    .card-panel-col {
      margin-bottom: 32px ;
      padding: 0 16px !important;
    }
    .card-panel {
      border-radius: 2px;
      height: 110px;
      cursor: pointer;
      font-size: 12px;
      position: relative;
      overflow: hidden;
      color: #666;
      background: #fff;
      box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
      border-color: rgba(0, 0, 0, .05);

      &:hover {
        .card-panel-icon-wrapper {
          color: #fff;
        }
        .icon-bangd{
          background:#28C76F;
        }

        .icon-jianli{
          background: #33B5E5;
        }
        .icon-ruxiang {
          background: #EA5455;
        }
       .icon-mianshi {
         background: #AA66CC;
       }
      }



      .icon-bangd{
        color:#28C76F;
        background-color: rgba(40,199,111,0.1) ;
      }
      .icon-jianli{
        color: #33B5E5;
        background-color: rgba(51,181,229,0.1) ;
      }
      .icon-mianshi {
        color: #AA66CC;
        background-color:rgba(170,102,204,0.1) ;
      }

      .icon-ruxiang {
        color: #EA5455;
        background-color: rgba(234,84,85,0.1) ;
      }


      .card-panel-icon-wrapper {
        float: right;
        margin: 35px 16px 0 0;
        padding:6px 6px;
        transition: all 0.38s ease-out;
        border-radius: 10px;
      }

      .card-panel-icon {
        float: right;
        font-size: 28px;
      }

      .card-panel-description {
        float: left;
        font-weight: bold;
        margin:20px 0 26px 16px;

        .card-panel-text {
          line-height: 18px;
          color: rgba(0, 0, 0, 0.45);
          font-size: 14px;
          margin-bottom: 12px;
        }

        .card-panel-num1 {
          font-size: 20px;
          font-weight: 800;
          color: #33B5E5;
        }
        .card-panel-num2 {
          font-size: 20px;
           color: #AA66CC;
          font-weight: 800;
        }
        .card-panel-num3 {
          color: #EA5455;
          font-size: 20px;
          font-weight: 800;
        }
        .card-panel-num6 {
          font-size: 20px;
           color:#28C76F;
          font-weight: 800;
        }
      }
    }
  }
 }
</style>
