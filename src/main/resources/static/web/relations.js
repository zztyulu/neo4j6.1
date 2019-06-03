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

function addRelationfuc(){
    console.log(11);
    //起始节点
    var label1 = $("#label1").val();//jQuery获取文本框仪检名称值
    var label1key1 = $("#label1key1").val();
    //console.log(keyl);
    var label1value1 = $("#label1value1").val();
    var label1key2 = $("#label1key2").val();
    var label1value2 = $("#label1value2").val();
    var label1key3 = $("#label1key3").val();
    var label1value3 = $("#label1value3").val();
    var list1 = [];
    var a1={}; a1.Key= label1key1;  a1.Value=label1value1;
    var b1={}; b1.Key= label1key2;  b1.Value=label1value2;
    var c1={}; c1.Key= label1key3;  c1.Value=label1value3;
    if(a1.Key!=""){list1.push(a1);}
    if(b1.Key!=""){list1.push(b1);}
    if(c1.Key!=""){list1.push(c1);}
    var startNode={"lable":label1,"propertys":list1};

    //终止节点
    var label2 = $("#label2").val();//jQuery获取文本框仪检名称值
    var label2key1 = $("#label2key1").val();
    var label2value1 = $("#label2value1").val();
    var label2key2 = $("#label2key2").val();
    var label2value2 = $("#label2value2").val();
    var label2key3 = $("#label2key3").val();
    var label2value3 = $("#label2value3").val();
    var a2={}; a2.Key= label2key1;  a2.Value=label2value1;
    var b2={}; b2.Key= label2key2;  b2.Value=label2value2;
    var c2={}; c2.Key= label2key3;  c2.Value=label2value3;
    var list2 = [];
    if(a2.Key!=""){list2.push(a2);}
    if(b2.Key!=""){list2.push(b2);}
    if(c2.Key!=""){list2.push(c2);}
    var EndNode={"lable":label2,"propertys":list2};

    //关系
    var label3 = $("#label3").val();//jQuery获取文本框仪检名称值
    var label3key1 = $("#label3key1").val();
    var label3value1 = $("#label3value1").val();
    var label3key2 = $("#label3key2").val();
    var label3value2 = $("#label3value2").val();
    var label3key3 = $("#label3key3").val();
    var label3value3 = $("#label3value3").val();
    var a3={};a3.Key= label3key1;  a3.Value=label3value1;
    var b3={}; b3.Key= label3key2;  b3.Value=label3value2;
    var c3={};  c3.Key= label3key3;  c3.Value=label3value3;
    var list = [];
    if(a3.Key!=""){list.push(a3);}
    if(b3.Key!=""){list.push(b3);}
    if(c3.Key!=""){list.push(c3);}
    console.log(list);

    var dat1={"label":label3,"mypro":list,"startNode":startNode,"endNode":EndNode};
    //document.write(dat1);
    $.ajax({
        type:"post",
        url:"/addRe",
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
function deleteRelationfuc(){
    console.log(11);
    //起始节点
    var label1 = $("#label1").val();//jQuery获取文本框仪检名称值
    var label1key1 = $("#label1key1").val();
    //console.log(keyl);
    var label1value1 = $("#label1value1").val();
    var label1key2 = $("#label1key2").val();
    var label1value2 = $("#label1value2").val();
    var label1key3 = $("#label1key3").val();
    var label1value3 = $("#label1value3").val();
    var list1 = [];
    var a1={}; a1.Key= label1key1;  a1.Value=label1value1;
    var b1={}; b1.Key= label1key2;  b1.Value=label1value2;
    var c1={}; c1.Key= label1key3;  c1.Value=label1value3;
    if(a1.Key!=""){list1.push(a1);}
    if(b1.Key!=""){list1.push(b1);}
    if(c1.Key!=""){list1.push(c1);}
    var startNode={"lable":label1,"propertys":list1};

    //终止节点
    var label2 = $("#label2").val();//jQuery获取文本框仪检名称值
    var label2key1 = $("#label2key1").val();
    var label2value1 = $("#label2value1").val();
    var label2key2 = $("#label2key2").val();
    var label2value2 = $("#label2value2").val();
    var label2key3 = $("#label2key3").val();
    var label2value3 = $("#label2value3").val();
    var a2={}; a2.Key= label2key1;  a2.Value=label2value1;
    var b2={}; b2.Key= label2key2;  b2.Value=label2value2;
    var c2={}; c2.Key= label2key3;  c2.Value=label2value3;
    var list2 = [];
    if(a2.Key!=""){list2.push(a2);}
    if(b2.Key!=""){list2.push(b2);}
    if(c2.Key!=""){list2.push(c2);}
    var EndNode={"lable":label2,"propertys":list2};

    //关系
    var label3 = $("#label3").val();//jQuery获取文本框仪检名称值
    var label3key1 = $("#label3key1").val();
    var label3value1 = $("#label3value1").val();
    var label3key2 = $("#label3key2").val();
    var label3value2 = $("#label3value2").val();
    var label3key3 = $("#label3key3").val();
    var label3value3 = $("#label3value3").val();
    var a3={};a3.Key= label3key1;  a3.Value=label3value1;
    var b3={}; b3.Key= label3key2;  b3.Value=label3value2;
    var c3={};  c3.Key= label3key3;  c3.Value=label3value3;
    var list = [];
    if(a3.Key!=""){list.push(a3);}
    if(b3.Key!=""){list.push(b3);}
    if(c3.Key!=""){list.push(c3);}
    console.log(list);

    var dat1={"label":label3,"mypro":list,"startNode":startNode,"endNode":EndNode};
    //document.write(dat1);
    $.ajax({
        type:"post",
        url:"/deleteRe",
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
function modifyRelationfuc(){
    console.log(11);
    //起始节点
    var label1 = $("#label1").val();//jQuery获取文本框仪检名称值
    var label1key1 = $("#label1key1").val();
    //console.log(keyl);
    var label1value1 = $("#label1value1").val();
    var label1key2 = $("#label1key2").val();
    var label1value2 = $("#label1value2").val();
    var label1key3 = $("#label1key3").val();
    var label1value3 = $("#label1value3").val();
    var list1 = [];
    var a1={}; a1.Key= label1key1;  a1.Value=label1value1;
    var b1={}; b1.Key= label1key2;  b1.Value=label1value2;
    var c1={}; c1.Key= label1key3;  c1.Value=label1value3;
    if(a1.Key!=""){list1.push(a1);}
    if(b1.Key!=""){list1.push(b1);}
    if(c1.Key!=""){list1.push(c1);}
    var startNode={"lable":label1,"propertys":list1};

    //终止节点
    var label2 = $("#label2").val();//jQuery获取文本框仪检名称值
    var label2key1 = $("#label2key1").val();
    var label2value1 = $("#label2value1").val();
    var label2key2 = $("#label2key2").val();
    var label2value2 = $("#label2value2").val();
    var label2key3 = $("#label2key3").val();
    var label2value3 = $("#label2value3").val();
    var a2={}; a2.Key= label2key1;  a2.Value=label2value1;
    var b2={}; b2.Key= label2key2;  b2.Value=label2value2;
    var c2={}; c2.Key= label2key3;  c2.Value=label2value3;
    var list2 = [];
    if(a2.Key!=""){list2.push(a2);}
    if(b2.Key!=""){list2.push(b2);}
    if(c2.Key!=""){list2.push(c2);}
    var EndNode={"lable":label2,"propertys":list2};

    //关系
    var label3 = $("#label3").val();//jQuery获取文本框仪检名称值
    var label3key1 = $("#label3key1").val();
    var label3value1 = $("#label3value1").val();
    var label3key2 = $("#label3key2").val();
    var label3value2 = $("#label3value2").val();
    var label3key3 = $("#label3key3").val();
    var label3value3 = $("#label3value3").val();
    var a3={};a3.Key= label3key1;  a3.Value=label3value1;
    var b3={}; b3.Key= label3key2;  b3.Value=label3value2;
    var c3={};  c3.Key= label3key3;  c3.Value=label3value3;
    var list = [];
    if(a3.Key!=""){list.push(a3);}
    if(b3.Key!=""){list.push(b3);}
    if(c3.Key!=""){list.push(c3);}
    console.log(list);

    var dat1={"label":label3,"mypro":list,"startNode":startNode,"endNode":EndNode};
    //document.write(dat1);
    $.ajax({
        type:"post",
        url:"/modifyRe",
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




