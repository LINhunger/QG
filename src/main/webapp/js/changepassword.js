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
//判断表单格式
function checkform(thisform)
{
	with (thisform)
	{		
		if (checkPassword(password,"亲，密码至少不少于6个字符，至多不多于15个字符，并且只能由字母数字组成!")==false)
  			{return false;}
	}
}