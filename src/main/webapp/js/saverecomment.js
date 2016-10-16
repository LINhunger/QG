//验证回复内容
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
		if (checkContent(r_content,"亲，回复不能为空哦!")==false)
  			{return false;}
	}
}