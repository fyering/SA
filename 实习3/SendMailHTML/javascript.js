var number="";
function fnregist() {
    var oUname = document.getElementById("uname").value;
    var oUpass = document.getElementById("upass").value;
    var oError = document.getElementById("error_box")
    var   yzm=  String(document.getElementById("uyanz").value);
   if(yzm==number){

       var objdbConn = new ActiveXObject("ADODB.Connection");
       var conn = "Driver={SQL Server};SERVER=(local);UID=sa;PWD=bigbang66;DATABASE=fy";
       objdbConn.Open(conn);
       var sql="insert into user_info (username,password) values('" + oUname + "','" + oUpass + "')";

      objdbConn.Execute(sql);
       window.alert("注册成功");

        rs.Close();
       objdbConn.Close();
   }
   else{

       window.alert("验证码错误");
   }

}
function sendyzm(){

    var url = String(document.getElementById("uname").value);

    number=randomnumbers();
    var URL ="http://localhost:8080/ws/rest/email/sendemail?_url="+url+"&_payload="+number;
    var xmlHttp=GetXmlHttpObject();
    xmlHttp.open("GET",URL,true); //submit the URL request
    xmlHttp.send(null);
    xmlHttp.onreadystatechange= function()
    {
        // Here we process the response
        var responseText=String(xmlHttp.responseText);



    }




}
function GetXmlHttpObject() {
    var xmlHttp=null;
    try {
        // Firefox, Opera 8.0+, Safari
        xmlHttp=new XMLHttpRequest();
    } catch (e) {
        // Internet Explorer
        try {
            xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    if (xmlHttp==null) {
        alert ("Your browser does not support AJAX!");
        return;
    }
    return xmlHttp;
}
function randomnumbers(){
    var Num="";
    for(var i=0;i<4;i++) {
        Num += Math.floor(Math.random() * 10);
    }

    return Num;
}
function fnLogin() {
    var oUname = document.getElementById("uname").value;
    var oUpass = document.getElementById("upass").value;
    var objdbConn = new ActiveXObject("ADODB.Connection");
    var conn = "Driver={SQL Server};SERVER=(local);UID=sa;PWD=bigbang66;DATABASE=fy";
    objdbConn.Open(conn);
    var rs = new ActiveXObject("ADODB.Recordset");
    var sql="select * from user_info";
     rs.open(sql, conn);
      var table= "";
      var flag=false;
      while(!rs.EOF) {

          var u= rs.Fields("username");
          var p= rs.Fields("password");
          if(oUname==u&&oUpass==p){
              window.alert("登陆成功");
              window.location.href="sendemail.html";
              flag=true;
              break;
          }

          rs.moveNext();
      }
    if(!flag){

        window.alert("请先注册");
    }
    rs.Close();
    objdbConn.Close();


}
function sendbatchemail(){

    var objdbConn = new ActiveXObject("ADODB.Connection");
    var conn = "Driver={SQL Server};SERVER=(local);UID=sa;PWD=bigbang66;DATABASE=fy";
    objdbConn.Open(conn);
    var rs = new ActiveXObject("ADODB.Recordset");
    var sql="select * from user_info";
    rs.open(sql, conn);
    var list=[];
    while(!rs.EOF) {

        var u= rs.Fields("username");
        var str="_url="+u;
        list.push(str);
        rs.moveNext();
    }

    rs.Close();
    objdbConn.Close();
    var req="";
    for(var i=0;i<list.length;i++){

        req+=list[i]+"&";


    }


    var url="http://localhost:8080/ws/rest/email/sendemailbatch?%20"+req+"_payload=hello";
   var xmlHttp=GetXmlHttpObject();
    xmlHttp.open("GET",url,true); //submit the URL request
    xmlHttp.send(null);
    xmlHttp.onreadystatechange= function()
    {
        // Here we process the response
        var responseText=String(xmlHttp.responseText);

    }


}
