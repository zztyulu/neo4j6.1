function insertNode(url) {
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
            document.getElementById("delete-right").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}

function deleteNode(url) {
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
            document.getElementById("delete-right").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}

function modifyNode(url) {
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
            document.getElementById("delete-right").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET",url,true);
    xmlhttp.send();

}

function findNode(url) {
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
            document.getElementById("delete-right").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET",url,true);
    xmlhttp.send();

}

function insertRelation(url) {
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
            document.getElementById("delete-right").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}

function deleteRelation(url) {
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
            document.getElementById("delete-right").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}

function modifyRelation(url) {
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
            document.getElementById("delete-right").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}

function findRelation(url) {
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
            document.getElementById("delete-right").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}



function refurbishIndex(){
    alert("准备添加")
    var label = $("#label").val();//jQuery获取文本框仪检名称值
    var keyl = $("#key1").val();
    console.log(keyl);
    var value1 = $("#value1").val();
    console.log(value1);
    var key2 = $("#key2").val();
    var value2 = $("#value2").val();
    var key3 = $("#key3").val();
    var value3 = $("#value3").val();
    var key4 = $("#key4").val();
    var value4 = $("#value4").val();
    var list = [];
    var a={}; a.Key= keyl;  a.Value=value1;
    var b={}; b.Key= key2;  b.Value=value2;
    var c={}; c.Key= key3;  c.Value=value3;
    var d={}; d.Key= key4;  d.Value=value4;
    if(a.Key!=""){
        list.push(a);
    }
    if(b.Key!=""){
        list.push(b);
    }
    if(c.Key!=""){
        list.push(c);
    }
    if(d.Key!=""){
        list.push(d);
    }
    //list.push(a);list.push(b); list.push(c);   list.push(d);
    var dat1={"lable":label,"property":list};
    //document.write(dat1);
    $.ajax({
        type:"post",
        url:"/person/test",
        type:'POST',
        dataType:'json',  //json 返回值类型
        data: JSON.stringify(dat1),//  转化为json字符串
        contentType:"application/json;charset=utf-8",    //告诉后台 我发送的是JSON字符串  必须有
        async: false,
        success:function (data) {
            alert("成功");
        }
    });
}

function deleteNodefuc(){
    alert("delete")
    var label = $("#label").val();//jQuery获取文本框仪检名称值
    var keyl = $("#key1").val();
    console.log(keyl);
    var value1 = $("#value1").val();
    console.log(value1);
    var key2 = $("#key2").val();
    var value2 = $("#value2").val();
    var key3 = $("#key3").val();
    var value3 = $("#value3").val();
    var list = [];
    var a={}; a.Key= keyl;  a.Value=value1;
    var b={}; b.Key= key2;  b.Value=value2;
    var c={}; c.Key= key3;  c.Value=value3;
    if(a.Key!=""){
        list.push(a);
    }
    if(b.Key!=""){
        list.push(b);
    }
    if(c.Key!=""){
        list.push(c);
    }
    //list.push(a);   //list.push(b); list.push(c);
    var dat1={"lable":label,"property":list};
    //document.write(dat1);
    $.ajax({
        type:"post",
        url:"/person/delete",
        type:'POST',
        dataType:'json',  //json 返回值类型
        data: JSON.stringify(dat1),//  转化为json字符串
        contentType:"application/json;charset=utf-8",    //告诉后台 我发送的是JSON字符串  必须有
        //async: false,
        success:function (data) {
            alert("成功");
        }
    });
}

function findNodefuc(){
    alert("find")
    var label = $("#label").val();//jQuery获取文本框仪检名称值
    var keyl = $("#key1").val();
    console.log(keyl);
    var value1 = $("#value1").val();
    console.log(value1);
    var key2 = $("#key2").val();
    var value2 = $("#value2").val();
    var key3 = $("#key3").val();
    var value3 = $("#value3").val();
    var list = [];
    var a={}; a.Key= keyl;  a.Value=value1;
    var b={}; b.Key= key2;  b.Value=value2;
    var c={}; c.Key= key3;  c.Value=value3;
    if(a.Key!=""){
        list.push(a);
    }
    if(b.Key!=""){
        list.push(b);
    }
    if(c.Key!=""){
        list.push(c);
    }
    //list.push(a);   //list.push(b); list.push(c);
    var dat1={"lable":label,"property":list};
    //document.write(dat1);
    $.ajax({
        type:"post",
        url:"/search/find",
        type:'POST',
        dataType:'json',  //json 返回值类型
        data: JSON.stringify(dat1),//  转化为json字符串
        contentType:"application/json;charset=utf-8",    //告诉后台 我发送的是JSON字符串  必须有
        //async: false,
        success:function (data) {
            alert("成功");
        }
    });
}

function updateNodefuc(){
    alert("准备更新")
    var label = $("#label").val();//jQuery获取文本框仪检名称值
    var keyl = $("#key1").val();
    console.log(keyl);
    var value1 = $("#value1").val();
    console.log(value1);
    var key2 = $("#key2").val();
    var value2 = $("#value2").val();
    var key3 = $("#key3").val();
    var value3 = $("#value3").val();
    var key4 = $("#key4").val();
    var value4 = $("#value4").val();
    var list = [];
    var list1 = [];
    var a={}; a.Key= keyl;  a.Value=value1;
    var b={}; b.Key= key2;  b.Value=value2;
    var c={}; c.Key= key3;  c.Value=value3;
    var d={}; d.Key= key4;  d.Value=value4;
    if(a.Key!=""){
        list.push(a);
    }
    if(b.Key!=""){
        list.push(b);
    }
    if(c.Key!=""){
        list1.push(c);
    }
    if(d.Key!=""){
        list1.push(d);
    }
    //list.push(a);   list.push(b); list1.push(c);   list1.push(d);
    var dat1={"lable":label,"property":list,"newPropertys":list1};
    //document.write(dat1);
    $.ajax({
        type:"post",
        url:"/person/update",
        type:'POST',
        dataType:'json',  //json 返回值类型
        data: JSON.stringify(dat1),//  转化为json字符串
        contentType:"application/json;charset=utf-8",    //告诉后台 我发送的是JSON字符串  必须有
        async: false,
        success:function (data) {
            alert("成功");
        }
    });
}