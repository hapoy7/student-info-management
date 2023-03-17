<template>

  <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
    <el-tab-pane label="帐号密码" name="first">
      <div :style="bodyHeight" class="body">
        <div class="center">
          <el-row>
            <el-col :span="14"> &nbsp; </el-col>
            <el-col :span="10">
              <el-form :model="form" ref="form" :rules="rules" label-width="100px">
                <el-row style="height: 50px;font-size: larger;font-weight: 900;">
                  <el-col>用户登录</el-col>
                </el-row>
                <el-row>
                  <el-form-item label="用户名" prop="name">
                    <el-input placeholder="请输入用户名" v-model="form.name" style="width: 200px" size="small" @keyup.enter.native="doLogin" />
                  </el-form-item>
                  <el-form-item label="密码" prop="password">
                    <el-input placeholder="请输入密码" v-model="form.password" show-password style="width: 200px" size="small" @keyup.enter.native="doLogin" />
                  </el-form-item>
                </el-row>
                <el-row>
                  <el-col :span="8"> &nbsp; </el-col>
                  <el-col :span="12">
                    <el-button @click="doLogin" type="primary" style="width:120px" size="small">登录</el-button>
                  </el-col>
                </el-row>
              </el-form>
            </el-col>
          </el-row>
        </div>
      </div>
    </el-tab-pane>
    <el-tab-pane label="指纹登录" name="second">
      <div :style="fingerWidth"> <img class="box" src="../../assets/finger.gif" /> </div>
    </el-tab-pane>
    <el-tab-pane label="人脸登录" name="third">
      <br>
      <div style="padding:10px;text-align: center;">
        <video id="video" width="375px" height="500px" autoplay="autoplay" ></video>
        <canvas id="canvas" width="375px" height="500px" ></canvas>
      </div>
      <div style="text-align:center;padding:20px;">
        <button @click="takePhoto()" style="padding:10px;font-size:50px" >拍照</button>
        &emsp; &emsp;
      </div>
    </el-tab-pane>
  </el-tabs>
</template>
<script>
import Vue from "vue";
import { USER, TOKEN, DICTS, MENUS, BUTTONS , serverUrl } from "@/common/Constant";
import JSONbig from 'json-bigint'; //解决超过 16 位数字精度丢失问题
import axios from 'axios';
export default {
  created() { this.initWebSocket(); },
  beforeRouteLeave(to, from, next) {  //路由跳转时关闭链接
    this.websock.close();
    next();
  },
  data() {
    return {
      height: null, width: null, activeName: 'first',
      form: {
        name: "admin", /*用户名*/
        password: "12345" /*密码*/
      },
      rules: {
        name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      },
      track: null
    };
  },
  computed: {
    bodyHeight() {
      return this.resize();
    },
    fingerWidth(){return "height:"+ (this.height-100) +"px; display: grid;"}
  },
  mounted() {
    window.onresize = () => (() => this.resize())()
  },
  methods: {
    handleClick(tab, event) {
      /*console.log(tab.name, event);
      console.log(tab, event);*/
      if ("second" == tab.name ) {
        this.rq.post("/sys/fingerLogin").then((res) => {
          if (res.code == 200) {
            this.$message.success("请开始验证指纹!");
          } else
            this.$message.error(res.msg);
        });
      };
      if ("third" == tab.name ) {
          //打开摄像头
        navigator.mediaDevices.getUserMedia({ video : { width : 375, height : 500 }}).then((stream)=> {
          video.srcObject = stream
          this.track = stream.getTracks()[0];
        }).catch(error => {
          console.log("没有找到摄像头" + error);
        });        
      } else if (this.track != null){
        //关闭摄像头
        this.track.stop();
      }
    },
    resize() {
      this.height = document.documentElement.clientHeight;
      this.width = document.documentElement.clientWidth;
      return "width: " + this.width + "px; height: " + this.height + "px; position: relative;";
    },
    /**登录*/
    doLogin() {
      this.$refs["form"].validate((valid) => {
        if (!valid) return;
        this.rq.post("/sys/login", this.form).then((res) => {
          // console.log(res)
          if (res.code == 200) {
            this.$message.success("登录成功!");
            Vue.ls.set(USER, res.data.user);
            Vue.ls.set(TOKEN, res.data.token);
            Vue.ls.set(DICTS, res.data.dicts);
            Vue.ls.set(MENUS, res.data.menus.filter(i => i.type == 1));
            let button = res.data.menus.filter(i => i.type == 2);
            Vue.ls.set(BUTTONS, button.length > 0 ? button[0].children : []);
            this.$emit("changeLogin", true);
          } else
            this.$message.error(res.msg);
        });
      });
    },
    initWebSocket() {
      this.websock = new WebSocket(serverUrl + "login/point");
      this.websock.onopen = () => console.log("login/point连接成功");
      this.websock.onerror = () => console.log("login/point 连接发生错误");
      this.websock.onmessage = (msg) => {
        console.log(JSONbig.parse(msg.data));
        let data = JSONbig.parse(msg.data);
        if (data.code == 200) {
          Vue.ls.set(USER, data.data.user);
          Vue.ls.set(TOKEN, data.data.token);
          Vue.ls.set(DICTS, data.data.dicts);
          Vue.ls.set(MENUS, data.data.menus);
          this.$emit("changeLogin", true);
          this.websock.close();
        } else {
          this.$message.success(data.msg);
        }
      };
      this.websock.onclose = () => console.log("login/point closed ");
    },
    //拍照
    takePhoto() {
      canvas.getContext('2d').drawImage(video, 0, 0, 500, 375);
      this.fileUp();
    },
    //上传
    fileUp() {
      canvas.toBlob((data)=>{
        let formData = new FormData();
        formData.append("file",data,"face.jpg");//指定文件名
        axios.post('/api/face/upload',formData,{'Content-type' : 'multipart/form-data' }).then(res=>{
          console.log("上传成功!",res.data);
          if (res.data.code == 200) {
            Vue.ls.set(USER, res.data.data.user);
            Vue.ls.set(TOKEN, res.data.data.token);
            Vue.ls.set(DICTS, res.data.data.dicts);
            Vue.ls.set(MENUS, res.data.data.menus);
            this.$emit("changeLogin", true);
            // console.log(res.data.data.token)
            // 关闭摄像头
            this.track.stop();
          } else if (res.data.code == 9) {
            //重新进行拍照，进行人脸比较
            // this.takePhoto();
            // console.log(res.data.msg)
          } else {
            this.$message.error(res.data.msg);
          }
          //关闭摄像头
          //this.track.stop();
        },err=>{
          console.log("上传失败!"+err);
          //关闭摄像头
          this.track.stop();
        });
      },"image/jpeg",1);//指定文件类型,压缩比
    },
  },
};
</script>
<style scoped lang="scss">
.el-col {
  border: 1px solid transparent;
}

.center {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  width: 1200px;
  height: 180px;
  position: absolute;
  margin: auto;
  padding: 20px;
}

.body {
  background-image: url("../../assets/back.jpg");
  margin-top: 0px;
  margin-right: 0px;
  margin-bottom: 0px;
  margin-left: 0px;
  background-position: top;
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-position: center center;
}
.box { width: 620px; height: 620px; align-self: center; justify-self: center;}
</style>
