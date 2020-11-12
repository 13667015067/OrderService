(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2255abf2"],{4274:function(e,t,a){"use strict";var l=a("a546"),r=a.n(l);r.a},"5f52":function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-card",{staticClass:"box-card"},[a("el-form",{ref:"listQuery",attrs:{model:e.listQuery,inline:!0}},[a("el-form-item",{attrs:{label:"工单状态"}},[a("el-select",{model:{value:e.listQuery.state,callback:function(t){e.$set(e.listQuery,"state",t)},expression:"listQuery.state"}},[a("el-option",{attrs:{label:"初始",value:"0"}}),a("el-option",{attrs:{label:"处理中",value:"1"}}),a("el-option",{attrs:{label:"已处理",value:"2"}}),a("el-option",{attrs:{label:"全部",value:"-1"}})],1)],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"small"},on:{click:e.handleQuery}},[e._v("搜索")])],1)],1),a("el-row",{staticClass:"mb8",attrs:{gutter:10}},[a("el-col",{attrs:{span:1.5}})],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{border:"",data:e.tplList},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{label:"工单号",prop:"id",width:"180"}}),a("el-table-column",{attrs:{label:"任务名称",prop:"taskName","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{label:"工单种类",prop:"orderStyle","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{label:"工单类型",prop:"orderType","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{prop:"orderState",label:"当前状态","show-overflow-tooltip":!0},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(e.showState(t.row))+" ")]}}])}),a("el-table-column",{attrs:{label:"预处理时间",prop:"dealTime","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{label:"当前处理人",prop:"nowDealUser","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{label:"创建者",prop:"createUser","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{label:"创建时间",align:"center",prop:"createTime"}}),a("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(a){return e.handleEdit(t.row)}}},[e._v("查看 ")]),a("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(a){return e.handleDelete(t.row)}}},[e._v("删除 ")])]}}])})],1),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageIndex,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageIndex",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}}),a("el-dialog",{staticStyle:{"margin-top":"0"},attrs:{title:"查看工单",visible:e.openEdit,"destroy-on-close":!0,fullscreen:!0},on:{"update:visible":function(t){e.openEdit=t}}},[a("gd_handle",{attrs:{gdData:e.gdData,showBtn:!1,formDisabled:!0}})],1)],1)],1)},r=[],o=(a("fe59"),a("e35a"),a("0d7a"),a("08ba"),function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"text item"},[a("el-steps",{attrs:{active:e.activeIndex,"finish-status":"success"}},[e._l(e.nodeStepList,(function(e,t){return[a("el-step",{key:t,attrs:{title:e.label}})]}))],2)],1)]),a("el-card",{staticClass:"box-card",staticStyle:{"margin-top":"15px"}},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",[e._v("工单流转历史")])]),a("div",{staticClass:"text item"},[a("el-table",{ref:"singleTable",staticStyle:{width:"100%"},attrs:{data:e.circulationHistoryList,border:""}},[a("el-table-column",{attrs:{prop:"nodeId",label:"节点id"}}),a("el-table-column",{attrs:{prop:"dealUser",label:"当前处理人"}}),a("el-table-column",{attrs:{prop:"nodeState",label:"当前状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(e.showState(t.row))+" ")]}}])}),a("el-table-column",{attrs:{prop:"nodeType",label:"节点类型"}}),a("el-table-column",{attrs:{prop:"createTime",label:"创建时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(e.showTime(t.row.createTime))+" ")]}}])})],1)],1)]),a("el-card",{staticClass:"box-card",staticStyle:{"margin-top":"15px"}},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",[e._v("表单信息")])]),a("ele-form",{ref:"form",attrs:{formDesc:e.formConfig,"request-fn":e.handleSubmit},scopedSlots:e._u([{key:"form-btn",fn:function(t){t.btns;return[e.isFinish?a("div"):a("div",{staticStyle:{display:"flex","align-items":"center","justify-items":"center"}},[e.showBtn&&"处理"==e.nodeType?a("el-button",{attrs:{type:"primary"},on:{click:e.submit}},[e._v(" 提交 ")]):e._e(),e.showBtn&&"审核"==e.nodeType?a("el-button",{attrs:{type:"primary"},on:{click:e.submit}},[e._v("通过 ")]):e._e(),e.showBtn&&"审核"==e.nodeType?a("el-button",{attrs:{type:"danger"},on:{click:function(t){return e.CheckSumbit(!1)}}},[e._v(" 回退 ")]):e._e()],1)]}}]),model:{value:e.formData,callback:function(t){e.formData=t},expression:"formData"}})],1)],1)}),n=[],i=(a("9302"),a("513c"),a("5e9f"),a("22dc")),s="",d="http://192.168.1.28:8080/",u={APPLY_ID:0x72d47a0e20f7c,APPLY_KEY:"22pyj0Rj47F8",ip:s,gd_ip:d,picSite:s+"/wurenji/profile/",problemsOpt:[{value:0,label:"围蔽情况",children:[{value:0,label:"未进行围蔽"},{value:1,label:"围蔽完整"},{value:2,label:"围蔽缺失"}]},{value:1,label:"工程进度",children:[{value:0,label:"主体结构及局部桩基础施工"},{value:1,label:"桩基础施工"},{value:2,label:"“三通一平”施工"},{value:3,label:"施工队未进场"},{value:4,label:"停工状态"},{value:5,label:"主体结构施工"},{value:6,label:"桩基础及局部主体结构施工"}]},{value:2,label:"非作业面裸土覆盖情况",children:[{value:0,label:"现场已无土方作业"},{value:1,label:"非作业面裸露土未100%覆盖"},{value:2,label:"非作业面裸露土未覆盖"},{value:3,label:"非作业面裸露土已覆盖"}]},{value:3,label:"建筑废弃物情况",children:[{value:0,label:"建筑废弃物未及时清理"}]},{value:4,label:"建筑材料情况",children:[{value:0,label:"建筑材料堆放混乱"},{value:1,label:"建筑材料堆放整洁"}]},{value:5,label:"作业面工作情况",children:[{value:0,label:"作业面已做喷淋"},{value:1,label:"作业面降尘措施不足"}]}],weiguiName:function(e){for(var t="",a=0;a<this.problemsOpt.length;a++){var l=this.problemsOpt[a];e==l.value&&(t=l.label)}return t},weiguiDesc:function(e){for(var t=[],a=0;a<e.length;a++){var l=e[a][0],r=e[a][1],o=this.problemsOpt[l].children,n=o[r].label;t.push(n)}return t.join(",")},areaOpt:[{value:"海珠区",label:"海珠区"},{value:"越秀区",label:"越秀区"},{value:"番禺区",label:"番禺区"},{value:"南沙区",label:"南沙区"},{value:"荔湾区",label:"荔湾区"},{value:"从化区",label:"从化区"},{value:"黄埔区",label:"黄埔区"},{value:"萝岗区",label:"萝岗区"},{value:"增城区",label:"增城区"},{value:"天河区",label:"天河区"},{value:"白云区",label:"白云区"},{value:"花都区",label:"花都区"}],stateOpt:[{value:"",label:"全部"},{value:0,label:"未上传"},{value:1,label:"待审批"},{value:2,label:"已审批"}],logOpt:[{value:"",label:"全部"},{value:"新增",label:"新增"},{value:"修改",label:"修改"},{value:"删除",label:"删除"}],toFixed:function(e){return e=String(e).replace(/^(.*\..{5}).*$/,"$1"),Number(e)},getUserType:function(){var e=localStorage.getItem("loginData");if(e){var t=JSON.parse(e);return t.type}},menuOpt:[{value:"menu",label:"menu"},{value:"button",label:"button"}],DANGAN_STATE:{jirudang:0,guidang:1,daizhijian:2,yizhijian:3,yishangchuan:4},USER_TYPE:{admin:0,user:1},getUserTypeDesc:function(e){var t,a=(t={},Object(i["a"])(t,this.USER_TYPE.user,"普通用户"),Object(i["a"])(t,this.USER_TYPE.admin,"管理员"),t);return a[e]},hasRight:function(e){var t,a=(t={},Object(i["a"])(t,this.USER_TYPE.user,0),Object(i["a"])(t,this.USER_TYPE.admin,1),t),l=this.getUserType(),r=a[l],o=a[e];return r>=o},getAccount:function(){var e=localStorage.getItem("loginData");if(e){var t=JSON.parse(e);return t.account}return""},GD_STATE:{NOT:0,DOING:1,FINISH:2},GD_STATE_LABEL:{0:"未处理",1:"处理中",2:"已完成"},GD_NODE_STATE:{NOT:0,FINISH:1,BACK:2},GD_NODE_STATE_LABEL:{0:"未处理",1:"已处理",2:"回退"}},c={props:["gdData","formDisabled","showBtn"],watch:{gdData:{handler:function(e,t){this.showSteps(),this.showHistory(),this.showForm()},immediate:!0}},data:function(){return{formConfig:{},formData:{},activeIndex:0,nodeStepList:[],circulationHistoryList:[],curSelRow:null}},computed:{isFinish:function(){return this.gdData.orderInfo.orderState==u.GD_STATE.FINISH},nodeType:function(){return this.curSelRow.nodeType}},created:function(){},methods:{showTime:function(e){return new Date(e).toLocaleDateString().split(" ")[0]},showState:function(e){return u.GD_NODE_STATE_LABEL[e.nodeState]},handleCurrentChange:function(e){},setCurrent:function(e){var t=this;this.curSelRow=e,this.$nextTick((function(){t.$refs.singleTable.setCurrentRow(e)}))},handleSubmit:function(e){var t=this;if(this.formData=e,"处理"==this.nodeType){var a=this.curSelRow.id,l=this.gdData.orderInfo.id,r=JSON.parse(this.gdData.orderInfo.orderContent);r.formData=this.formData,r=JSON.stringify(r);var o=new FormData;o.append("applyId",u.APPLY_ID),o.append("applyKey",u.APPLY_KEY),o.append("workId",a),o.append("orderId",l),o.append("orderContent",r),o.append("fileList",""),this.$api.dealWork(o).then((function(e){200==e.data.code?(t.$message({message:e.data.msg,type:"success"}),t.$emit("update")):t.$message({message:e.data.msg,type:"error"})}))}else this.CheckSumbit(!0)},CheckSumbit:function(e){var t=this,a=e?1:0,l=this.curSelRow.id,r=this.gdData.orderInfo.id,o=JSON.parse(this.gdData.orderInfo.orderContent);o.formData=this.formData,o=JSON.stringify(o);var n={applyId:u.APPLY_ID,applyKey:u.APPLY_KEY,workId:l,orderId:r,isPass:a,workData:o};this.$api.checkWork(n).then((function(e){200==e.code?(t.$message({message:e.msg,type:"success"}),t.$emit("update")):t.$message({message:e.msg,type:"error"})}))},submit:function(){this.$refs.form.handleSubmitForm()},showSteps:function(){var e=JSON.parse(this.gdData.orderInfo.orderLogic);this.nodeStepList=[{label:"开始"}];for(var t=0;t<e.nodes.length;t++){var a=e.nodes[t];this.nodeStepList.push({label:a.nodeType})}this.nodeStepList.push({label:"结束"});var l=this.gdData.works[this.gdData.works.length-1].work,r=e.nodes.length,o=l.nodeId;o==r?l.nodeState==u.GD_NODE_STATE.NOT?o=o:l.nodeState==u.GD_NODE_STATE.FINISH&&(o+=2):o=o,this.activeIndex=o},showHistory:function(){this.circulationHistoryList=[];for(var e=0;e<this.gdData.works.length;e++)this.circulationHistoryList.push(this.gdData.works[e].work);this.setCurrent(this.circulationHistoryList[this.circulationHistoryList.length-1])},showForm:function(){this.formConfig=JSON.parse(this.gdData.orderInfo.orderContent).formDesc;var e=!1,t=this.gdData.works[this.gdData.works.length-1].work,a=t.nodeType;for(var l in e=this.gdData.orderInfo.orderState==u.GD_STATE.FINISH||("审核"==a||this.formDisabled),this.formConfig)this.formConfig[l].disabled=function(t){return e};this.formData=JSON.parse(this.gdData.orderInfo.orderContent).formData?JSON.parse(this.gdData.orderInfo.orderContent).formData:{}}}},p=c,m=a("9ca4"),f=Object(m["a"])(p,o,n,!1,null,null,null),h=f.exports,g=a("e33d"),b={name:"AllGongdan",watch:{"$store.state.myApp.currentApp":{handler:function(e,t){e&&this.getList()},deep:!0,immediate:!0}},components:{gd_handle:h},data:function(){return{gdData:null,gdList:[],orderStyleOpt:[],taskOpt:[],taskList:[],formConfig:{key_1604628302686:{label:"单行输入框",type:"input",attrs:{}}},formData:{},appList:[],dialogFormVisibleName:1,queryParams:{pageIndex:1,pageSize:10},loading:!1,ids:[],single:!0,multiple:!0,total:0,open:!1,openEdit:!1,tplList:[],listQuery:{state:"-1"},ruleForm:{fileList:"",applyId:"",orderStyle:"",orderType:"",taskName:"",orderContent:"",dealTime:"",user:""},rules:{orderStyle:[{required:!0,message:"请选择工单种类",trigger:"blur"}],taskName:[{required:!0,message:"请选择任务名称",trigger:"blur"}],dealTime:[{required:!0,message:"请选择处理日期",trigger:"change"}]},fileList:[]}},created:function(){},mounted:function(){},methods:{showState:function(e){return u.GD_STATE_LABEL[e.orderState]},handleRequest:function(e){console.log(1111111111,e)},handleRequestSuccess:function(){},handleQuery:function(e){this.getList()},getList:function(){var e=this;this.loading=!0;var t={};t.applyId=this.$store.getters.currentApp.id,t.applyKey=this.$store.getters.currentApp.applyKey,t.state=this.listQuery.state,t.page=this.queryParams.pageIndex,t.amount=this.queryParams.pageSize,g["a"].getOrderInfo(t).then((function(t){200==t.code?(e.gdList=t.data.orders,t.data.orders.forEach((function(e){e.createTime=new Date(e.createTime).toLocaleDateString().split(" ")[0]})),t.data.orders.forEach((function(e){e.dealTime=new Date(e.dealTime).toLocaleDateString().split(" ")[0]})),e.tplList=t.data.orders,e.total=t.data.pageSize*e.queryParams.pageSize):e.tplList=[],e.loading=!1}))},handleCreate:function(){this.ruleForm={fileList:"",applyId:"",orderStyle:"",orderType:"",taskName:"",orderContent:"",dealTime:"",user:""},this.dialogFormVisibleName=1,this.open=!0},submitForm:function(e){},handleEdit:function(e){var t=this,a=this.$store.getters.currentApp.id,l=this.$store.getters.currentApp.applyKey,r={applyId:a,applyKey:l,orderId:e.id};g["a"].getWork(r).then((function(a){for(var l={orderInfo:e,works:[]},r=0;r<a.data.length;r++){var o=a.data[r];l.works.push(o)}t.gdData=l,t.dialogFormVisibleName=2,t.openEdit=!0}))},editForm:function(e){},handleDelete:function(e){var t=this,a=new FormData;a.append("orderId",e.id),g["a"].delOrderInfo(a).then((function(e){200==e.code?(t.$message({type:"success",message:e.msg}),t.getList()):t.$message({type:"error",message:e.msg})}))},handleSelectionChange:function(){},handleChange:function(e,t){console.log(e,t),this.fileList=t},handleRemove:function(e,t){console.log(e,t),this.fileList=t},handlePreview:function(e){}}},v=b,y=(a("4274"),Object(m["a"])(v,l,r,!1,null,null,null));t["default"]=y.exports},a546:function(e,t,a){},e33d:function(e,t,a){"use strict";var l=a("b775");t["a"]={addModel:function(e){return Object(l["a"])({url:"/model/addModel",method:"post",data:e})},updateModel:function(e){return Object(l["a"])({url:"/model/updateModel",method:"post",data:e})},deleteModelById:function(e){return Object(l["a"])({url:"/model/deleteModelById",method:"post",data:e})},getModelByPage:function(e){return l["a"].get("/model/getModelByPage",{params:e})},getAllModelName:function(e){return l["a"].get("/model/getAllModelName",{params:e})},getModelByName:function(e){return l["a"].get("/model/getModelByName",{params:e})},getOrderInfo:function(e){return l["a"].get("/orderInfo/getOrderInfo",{params:e})},getWork:function(e){return l["a"].get("/work/getWork",{params:e})},delOrderInfo:function(e){return Object(l["a"])({url:"/orderInfo/delOrderInfo",method:"post",data:e})}}}}]);