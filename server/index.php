<?php
require_once("inc.header.php");

$auth = new Auth();

?>
<html>
<body><div>
<?php 

if($auth->login("geekygirlsarah", "blueberry")) 
	print "logged in!";
else
	print "failed login";

?>
</div></body></html>