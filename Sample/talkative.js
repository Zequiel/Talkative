function init()
{
	root = document.getElementById("talkative");

}

function send_message()
{
	var xhr = getXMLHttpRequest();
	var response = '{"message":"'+document.getElementById("message").value+'"}';

	xhr.onreadystatechange = function()
	{
		if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 0))
		{
			alert(xhr.responseText);
		}
	};

	xhr.open("POST", "http://localhost:8080/Talkative/comment_repository", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send(response);
}

//utils function
function getXMLHttpRequest()
{
	var xhr = null;

	if (window.XMLHttpRequest || window.ActiveXObject)
	{
		if (window.ActiveXObject)
		{
			try
			{
				xhr = new ActiveXObject("Msxml2.XMLHTTP");
			}
			catch (e)
			{
				xhr = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		else
		{
			xhr = new XMLHttpRequest();
		}
	}
	else
	{
		alert("Votre navigateur ne supporte pas l'objet XMLHttpRequest...");
		return null;
	}
	
	return xhr;
}
