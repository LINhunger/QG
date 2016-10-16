//验证评论内容
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
function checkformC(thisform)
{

	with (thisform)
	{
		if (checkContent(c_content,"亲，内容不能为空哦!")==false)
  			{return false;}
	}
}