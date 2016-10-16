//判断标题栏
function checkTitle(field,alerttxt)
{
	with (field)
	{
		var a=value;
		if (value=="") 
  			{alert(alerttxt);return false;}
		else {return true;}
	}
}
//验证文章内容
function checkContent(field,alerttxt)
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
function checkform(thisform)
{

	with (thisform)
	{
		if (checkTitle(a_title,"亲，标题栏不能为空!")==false)
  			{return false;}
		
	}
	with (thisform)
	{
		if (checkContent(a_content,"亲，内容也不能为空哦!")==false)
  			{return false;}
	}
}