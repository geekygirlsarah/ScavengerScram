<?php
require_once("inc.mysql.php");

class Auth {
	
	private $mysqlhost = "localhost";
	private $mysqluser = "scavengeruser";
	private $mysqlpass = "KBKd52wxJ7fm4N7";
	private $mysqldb = "scavengerscram";
	
	public function generateSHA1($password) {
		return sha1($password,false);
	}
	
	public function login($username, $password) {
		$mysqli = new mysqli($this->mysqlhost, $this->mysqluser, $this->mysqlpass, $this->mysqldb);
		if ($mysqli->connect_errno) {
			echo "Failed to connect to MySQL: (" . $mysqli->connect_errno . ") " . $mysqli->connect_error;
		}
		
		$password = $this->generateSHA1($password);
		$res = $mysqli->query("SELECT username FROM players WHERE username = '" 
				. $username .
				"' AND password = '"
				. $password . 
				"'");
		
		/*
		echo "Reverse order...\n";
		for ($row_no = $res->num_rows - 1; $row_no >= 0; $row_no--) {
			$res->data_seek($row_no);
			$row = $res->fetch_assoc();
			echo " id = " . $row['id'] . "\n";
		}
		
		echo "Result set order...\n";
		$res->data_seek(0);
		while ($row = $res->fetch_assoc()) {
			echo " id = " . $row['id'] . "\n";
		}
		*/
		
		if($res->num_rows == 1)
			return true;
		else
			return false;
		
	}
	
	public function createAccount($username, $password, $name) {
		
	} 
}

?>