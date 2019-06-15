/*
* @program:  enginegraph
 * @description:  智能搜索js
 * @author: liuchenyang
 * @create: 2019-06-15 15.49

**/


var pageSize=$("#pageSize").val();
var pageNum=$("#pageNum").val();
//得到总页数
var pageCount;
var alldata;
//记录总数
var pageNumCount;
/*//初始化，加载完成后执行
window.onload=function(){
    search();
};*/
//搜索按钮绑定回车事件
document.onkeydown = function(event){
    if (event.keyCode == 13) {
        event.cancelBubble = true;
        event.returnValue = false;
    }
}

//下一步
function next(){
    //得到当前选中项的页号
    var id=$("#pageNum option:selected").val();
    //计算下一页的页号
    var nextPage=parseInt(id)+1;
    // 修改当前页码
    pageNum=parseInt(pageNum)+1;
    //得到select的option集合
    var list=document.getElementById("pageNum").options;
    //得到select中，下一页的option
    var nextOption=list[nextPage-1];
    //修改select的选中项
    nextOption.selected=true;
    //调用分頁方法
    page();
}
function pageNumage(){
    pageNum=$("#pageNum").val();
    //调用分頁方法
    page();
}


//上一步
function previous(){

    // 修改当前页码
    pageNum=parseInt(pageNum)-1;

    //得到当前选中项的页号
    var id=$("#pageNum option:selected").val();
    //计算上一页的页号
    var previousPage=parseInt(id)-1;
    //得到select的option集合
    var list=document.getElementById("pageNum").options;
    //得到select中，上一页的option
    var previousOption=list[previousPage-1];
    //修改select的选中项
    previousOption.selected=true;
    //调用查询方法
    page();
}

//修改每页显示条数时，要从第一页开始查起
function research() {
    //得到select的option集合
    var list=document.getElementById("pageNum").options;
    pageSize=$("#pageSize").val();   //重新得到页的大小
    // 修改当前页码
    pageNum=1;
    //得到select中，第一页的option
    var nextOption=list[0];
    //修改select的选中项
    nextOption.selected=true;
    //调用查询方法
    page();
}
function page(){
    //将除模板行的thead删除，即删除之前的数据重新加载
    document.getElementById("div1").style.visibility="visible";//显示标签
    $("thead").eq(0).nextAll().remove();

    //当前记录开始数
    var pageNumBegin=(pageNum-1)*pageSize;
    //当前记录结束数
    var pageNumEnd=pageNum*pageSize;
    //如果结束数大于记录总数，则等于记录总数
    if(pageNumEnd>pageNumCount){
        pageNumEnd=pageNumCount;
    }
    console.log("pageNumBegin  "+pageNumBegin);
    console.log("page");

    if(pageNumCount/pageSize==0){
        pageCount=pageNumCount/pageSize;
    }else{
        pageCount=Math.ceil(pageNumCount/pageSize);
    }

    //输出"显示第 1 至 10 项记录"
    document.getElementById("DataTables_Table_0_info").innerHTML=
        "显示第"+(parseInt(pageNumBegin)+1).toString()
        +" 至 "+pageNumEnd.toString()
        +" 项记录，共 "+pageNumCount.toString()+" 项";

    //显示所有的页码数
    var pageSelect =document.getElementById("page");
    var pageOption="";
    var flag;
    //删除select下所有的option，清除所有页码
    document.getElementById("pageNum").options.length=0;

    for(var i=0;i<pageCount;i++){
        flag=(i+1).toString();
        var option;
        //如果等于当前页码
        if(flag==pageNum){
            //实例化一个option,则当前页码为选中状态
            option=new Option(flag, flag, false, true);
        }else{
            option=new Option(flag, flag, false, false);
        }
        //将option加入select中
        document.getElementById("pageNum").options.add(option);
    }

    //如果总记录数小于5条，则不显示分页
    if((pageNumCount-5)<0){
        document.getElementById("bottomTool").style.display="none";
    }else{
        document.getElementById("bottomTool").style.display="";
    }

    //将获取到的数据动态的加载到table中
    //分页  第几页 每页多少条数据
    console.log(" (pageNum-1)*pageSize "+ (pageNum-1)*pageSize);
    console.log(" pageNum*pageSize "+ pageNum*pageSize);
    for (var i = (pageNum-1)*pageSize; i <pageNum*pageSize&&i<alldata.length; i++) {
        //获取模板行，复制一行
        var row = $("#tem").clone();

        //给每一行赋值
        row.find("#studentId").text(alldata[i].title);
        row.find("#studentName").text( alldata[i].content);
        row.find("#courseId").text( alldata[i].info_from);
        row.find("#examRoomId").text( alldata[i].path);
        row.find("#className").text( alldata[i].time);

        //将新行添加到表格中
        row.appendTo("#table");
    }

    /**给上一步下一步加颜色**/
    //判断是否只有一页
    if(pageCount==1){

        //如果只有一页，上一步，下一步都为灰色
        $("#previousPage").css("color","#AAA");//给上一步加灰色
        $("#nextPage").css("color","#AAA");//给下一步加灰色
    }else if(pageNum-1<1){
        //如果是首页,则给上一步加灰色，下一步变蓝
        $("#previousPage").css("color","#AAA");//给上一步加灰色
        $("#nextPage").css("color","#00F");//给下一步加蓝色
    }else if(pageNum==pageCount){
        //如果是尾页,则给上一步加蓝色，下一步灰色
        $("#previousPage").css("color","#00F");//给上一步标签加蓝色
        $("#nextPage").css("color","#AAA");//给下一步标签加灰色
    }else{
        //上一步为蓝色，下一步为绿色
        $("#previousPage").css("color","#00F");//给上一步加蓝色
        $("#nextPage").css("color","#00F");//给下一步加蓝色
    }
}


//搜索，模糊查询学生违纪信息
function search(){
    //得到查询条件
    var search = $("#account").val();
    var inputdata = {'search':search};
    //初始化页码
    pageNum=1;

    $.ajax({
        type:"post",
        url:"/Aiserach",
        type:'POST',
        dataType:'json',  //json 返回值类型
        data: JSON.stringify(inputdata),//  转化为json字符串
        contentType:"application/json;charset=utf-8",
        success: function (data) {
            console.log(data.code);
            console.log(data);
            // 一定是==  而不是=
            if(data.code==200){

                alldata=data.object;
                console.log(alldata);
                pageNumCount=alldata.length;
                //将除模板行的thead删除，即删除之前的数据重新加载
                $("thead").eq(0).nextAll().remove();
                page();
            }
            else if(data.code==400)
            {
                document.getElementById("div1").style.visibility="hidden";//显示标签
                alert("数据不存在");
            }
        }
    });
}
