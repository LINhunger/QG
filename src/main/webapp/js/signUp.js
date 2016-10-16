// JavaScript Document
var label =/<(.*)>.*<\/\1>/;
var space =/^[\s ]*$/;
//判断姓名格式
function checkName(field,alerttxt)
{
	with (field)
	{
		var s=value;
		var regex= /[a-zA-Z0-9\u4E00-\u9FA5\uf900-\ufa2d]{6,15}$/;
		if (!regex.exec(s)) 
  			{alert(alerttxt);return false;}
		else {return true;}
	}
}
//判断密码格式
function checkPassword(field,alerttxt)
{
	with (field)
	{
		var s=value;
		var regex=/^[0-9A-Za-z]{6,15}$/;
		if (!regex.exec(s)) 
  			{alert(alerttxt);return false;}
		else {return true;}
	}
}
//判断邮箱格式
function checkEmail(field,alerttxt)
{
	with (field)
	{
		var apos=value.indexOf("@");
		var dotpos=value.lastIndexOf(".");
		if (apos<1||dotpos-apos<2) 
  			{alert(alerttxt);return false;}
		else {return true;}
	}
}
//判断验证码
function checkVerification(field,alerttxt)
{
	with (field)
	{
		var a=value;
		if (value=="") 
  			{alert(alerttxt);return false;}
		else {return true;}
	}
}



//判断表单格式
function validate_form(thisform)
{


	with (thisform)
	{		
		if (checkName(name,"亲，用户名至少不少于6个字符，至多不多于15个字符，并且只能由汉字字母数字组成!")==false)
  			{return false;}
	}
	with (thisform)
	{		
		if (checkPassword(password,"亲，密码至少不少于6个字符，至多不多于15个字符，并且只能由字母数字组成!")==false)
  			{return false;}
	}
	with (thisform)
	{
		if (checkEmail(email,"亲，邮件格式不对喔!")==false)
  			{return false;}
		
	}
	with (thisform)
	{
		if (checkVerification(num,"亲，要输入验证码喔!")==false)
  			{return false;}
		
	}
}
//判断表单格式
function checkform(thisform)
{


	with (thisform)
	{		
		if (checkName(name,"亲，用户名至少不少于6个字符，至多不多于15个字符，并且只能由汉字字母数字组成!")==false)
  			{return false;}
	}
	with (thisform)
	{		
		if (checkPassword(password,"亲，密码至少不少于6个字符，至多不多于15个字符，并且只能由字母数字组成!")==false)
  			{return false;}
	}
	with (thisform)
	{
		if (checkVerification(num,"亲，要输入验证码喔!")==false)
  			{return false;}
		
	}
}



