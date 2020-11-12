(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-11fe11b8"],{5455:function(e,t,r){"use strict";r.r(t);var l=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"app-container"},[r("el-card",{staticClass:"box-card"},[r("el-form",{ref:"listQuery",attrs:{model:e.listQuery,inline:!0}},[r("el-form-item",{attrs:{label:"工单种类"}},[r("el-input",{staticStyle:{width:"240px"},attrs:{placeholder:"请输入工单种类",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.listQuery.orderStyle,callback:function(t){e.$set(e.listQuery,"orderStyle",t)},expression:"listQuery.orderStyle"}})],1),r("el-form-item",{attrs:{label:"工单类型"}},[r("el-input",{staticStyle:{width:"240px"},attrs:{placeholder:"请输入工单类型",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.listQuery.orderType,callback:function(t){e.$set(e.listQuery,"orderType",t)},expression:"listQuery.orderType"}})],1),r("el-form-item",[r("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"small"},on:{click:e.handleQuery}},[e._v("搜索")])],1)],1),r("el-row",{staticClass:"mb8",attrs:{gutter:10}},[r("el-col",{attrs:{span:1.5}},[r("el-button",{attrs:{type:"primary",icon:"el-icon-plus",size:"mini"},on:{click:e.handleCreate}},[e._v("新增 ")])],1)],1),r("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{border:"",data:e.tplList},on:{"selection-change":e.handleSelectionChange}},[r("el-table-column",{attrs:{label:"任务号",prop:"id",width:"80"}}),r("el-table-column",{attrs:{label:"applyId",prop:"applyId"}}),r("el-table-column",{attrs:{label:"任务名称",prop:"name","show-overflow-tooltip":!0}}),r("el-table-column",{attrs:{label:"工单种类",prop:"orderStyle","show-overflow-tooltip":!0}}),r("el-table-column",{attrs:{label:"工单类型",align:"center",prop:"orderType"}}),r("el-table-column",{attrs:{label:"任务流程",align:"center",prop:"logic.name"}}),r("el-table-column",{attrs:{label:"任务模板",align:"center",prop:"model.name"}}),r("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(r){return e.handleEdit(t.row)}}},[e._v("编辑 ")]),r("el-button",{attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(r){return e.handleDelete(t.row)}}},[e._v("删除 ")])]}}])})],1),r("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageIndex,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageIndex",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}}),r("el-dialog",{staticStyle:{"margin-top":"0"},attrs:{title:1===e.dialogFormVisibleName?"新建任务":"编辑",visible:e.open,"destroy-on-close":!0},on:{"update:visible":function(t){e.open=t}}},[r("div",{staticClass:"tpl-create-content"},[r("el-form",{ref:"ruleForm",attrs:{model:e.ruleForm,rules:e.rules,"label-width":"100px"}},[r("el-form-item",{attrs:{label:"任务名称",prop:"name"}},[r("el-input",{model:{value:e.ruleForm.name,callback:function(t){e.$set(e.ruleForm,"name",t)},expression:"ruleForm.name"}})],1),r("el-form-item",{attrs:{label:"工单种类",prop:"orderStyle"}},[r("el-input",{attrs:{disabled:1!=e.dialogFormVisibleName},model:{value:e.ruleForm.orderStyle,callback:function(t){e.$set(e.ruleForm,"orderStyle",t)},expression:"ruleForm.orderStyle"}})],1),r("el-form-item",{attrs:{label:"工单类型",prop:"orderType"}},[r("el-input",{attrs:{disabled:1!=e.dialogFormVisibleName},model:{value:e.ruleForm.orderType,callback:function(t){e.$set(e.ruleForm,"orderType",t)},expression:"ruleForm.orderType"}})],1),r("el-form-item",{attrs:{label:"任务流程",prop:"logicId"}},[r("el-select",{model:{value:e.ruleForm.logicId,callback:function(t){e.$set(e.ruleForm,"logicId",t)},expression:"ruleForm.logicId"}},e._l(e.logicOpt,(function(e){return r("el-option",{attrs:{label:e.label,value:e.value}})})),1)],1),r("el-form-item",{attrs:{label:"任务模板",prop:"modelId"}},[r("el-select",{model:{value:e.ruleForm.modelId,callback:function(t){e.$set(e.ruleForm,"modelId",t)},expression:"ruleForm.modelId"}},e._l(e.modelOpt,(function(e){return r("el-option",{attrs:{label:e.label,value:e.value}})})),1)],1)],1),r("div",{staticStyle:{"text-align":"center"}},[r("el-button",{attrs:{type:"primary"},on:{click:function(t){1===e.dialogFormVisibleName?e.submitForm("ruleForm"):e.editForm("ruleForm")}}},[e._v(" 提交 ")]),r("el-button",{on:{click:function(t){e.open=!1}}},[e._v("取 消")])],1)],1)])],1)],1)},a=[],o=(r("053b"),r("b775")),i={addOrderTask:function(e){return Object(o["a"])({url:"/orderTask/addOrderTask",method:"post",data:e})},getTaskList:function(e){return o["a"].get("/orderTask/getTaskList",{params:e})},updateOrderTask:function(e){return Object(o["a"])({url:"orderTask/updateOrderTask",method:"post",data:e})},deleteOrderTask:function(e){return Object(o["a"])({url:"/orderTask/deleteOrderTask",method:"post",data:e})}},n=r("644f"),s=r("e33d"),d=(r("5f87"),{name:"task-manager",watch:{"$store.state.myApp.currentApp":{handler:function(e,t){e&&(this.getList(),this.getOptions())},deep:!0,immediate:!0}},components:{},data:function(){return{formConfig:"",formData:{},appList:[],dialogFormVisibleName:1,queryParams:{pageIndex:1,pageSize:10},loading:!1,ids:[],single:!0,multiple:!0,total:0,open:!1,tplList:[],listQuery:{applyId:"",orderStyle:"",orderType:""},logicOpt:[],modelOpt:[],ruleForm:{applyId:"",id:"",name:"",orderStyle:"",orderType:"",logicId:"",modelId:""},rules:{name:[{required:!0,message:"请输入任务名称",trigger:"blur"}],orderStyle:[{required:!0,message:"请输入工单种类",trigger:"change"}],logicId:[{required:!0,message:"请选择流程",trigger:"change"}],modelId:[{required:!0,message:"请选择模板",trigger:"change"}]}}},created:function(){},mounted:function(){},methods:{getOptions:function(){var e=this,t={},r=this.$store.getters.currentApp.id;t.applyId=r,n["a"].getAllLogicName(t).then((function(t){if(200==t.code){e.logicOpt=[];for(var r=0;r<t.data.length;r++){var l=t.data[r];e.logicOpt.push({label:l.name,value:l.id})}}}));var l={};l.applyId=r,s["a"].getAllModelName(t).then((function(t){if(200==t.code){e.modelOpt=[];for(var r=0;r<t.data.length;r++){var l=t.data[r];e.modelOpt.push({label:l.name,value:l.id})}}}))},handleQuery:function(e){this.getList()},getList:function(){var e=this;this.loading=!0;var t=this.$store.getters.currentApp.id;this.listQuery.applyId=t,i.getTaskList(this.listQuery).then((function(t){200==t.code?e.tplList=t.data:(e.tplList=[],e.$message({message:t.msg,type:"error"})),e.loading=!1}))},handleCreate:function(){this.ruleForm={applyId:"",name:"",orderStyle:"",orderType:"",logicId:"",modelId:""},this.dialogFormVisibleName=1,this.open=!0},submitForm:function(e){var t=this;this.$refs[e].validate((function(e){if(e){var r=t.$store.getters.currentApp.id;t.ruleForm.applyId=r,i.addOrderTask(t.ruleForm).then((function(e){200==e.code?(t.$message({type:"success",message:e.msg}),t.getList()):t.$message({type:"error",message:e.msg})}))}}))},handleEdit:function(e){this.ruleForm.applyId=e.applyId,this.ruleForm.id=e.id,this.ruleForm.name=e.name,this.ruleForm.orderStyle=e.orderStyle,this.ruleForm.orderType=e.orderType,this.ruleForm.logicId=e.logic.id,this.ruleForm.modelId=e.model.id,this.dialogFormVisibleName=2,this.open=!0},editForm:function(e){var t=this;this.$refs[e].validate((function(e){e&&i.updateOrderTask(t.ruleForm).then((function(e){200==e.code?(t.$message({type:"success",message:e.msg}),t.getList()):t.$message({type:"error",message:e.msg})}))}))},handleDelete:function(e){var t=this,r={};r.id=e.id,i.deleteOrderTask(r).then((function(e){200==e.code?(t.$message({type:"success",message:e.msg}),t.getList()):t.$message({type:"error",message:e.msg})}))},handleSelectionChange:function(){}}}),u=d,c=r("9ca4"),m=Object(c["a"])(u,l,a,!1,null,null,null);t["default"]=m.exports},"644f":function(e,t,r){"use strict";var l=r("b775");t["a"]={addLogic:function(e){return Object(l["a"])({url:"/logic/addLogic",method:"post",data:e})},updateLogic:function(e){return Object(l["a"])({url:"/logic/updateLogic",method:"post",data:e})},deleteLogicById:function(e){return Object(l["a"])({url:"/logic/deleteLogicById",method:"post",data:e})},getLogicByPage:function(e){return l["a"].get("/logic/getLogicByPage",{params:e})},getAllLogicName:function(e){return l["a"].get("/logic/getAllLogicName",{params:e})},getLogicByName:function(e){return l["a"].get("/logic/getLogicByName",{params:e})}}},e33d:function(e,t,r){"use strict";var l=r("b775");t["a"]={addModel:function(e){return Object(l["a"])({url:"/model/addModel",method:"post",data:e})},updateModel:function(e){return Object(l["a"])({url:"/model/updateModel",method:"post",data:e})},deleteModelById:function(e){return Object(l["a"])({url:"/model/deleteModelById",method:"post",data:e})},getModelByPage:function(e){return l["a"].get("/model/getModelByPage",{params:e})},getAllModelName:function(e){return l["a"].get("/model/getAllModelName",{params:e})},getModelByName:function(e){return l["a"].get("/model/getModelByName",{params:e})},getOrderInfo:function(e){return l["a"].get("/orderInfo/getOrderInfo",{params:e})},getWork:function(e){return l["a"].get("/work/getWork",{params:e})},delOrderInfo:function(e){return Object(l["a"])({url:"/orderInfo/delOrderInfo",method:"post",data:e})}}}}]);