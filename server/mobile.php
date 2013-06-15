<?php
/**
 * Mobile interacting script
 * Pass in a command, it returns XML
 */
require_once("class.auth.php");


if(!isset($_POST['command']) || $_POST['command'] == "") {
	die("Didn't pass in a required command.");
}

$command = $_POST['command'];

$writer = new XMLWriter();
//lets store our XML into the memory so we can output it later
$writer->openMemory();
//lets also set the indent so its a very clean and formatted XML
$writer->setIndent(true);
//now we need to define our Indent string,which is basically how many blank spaces we want to have for the indent
$writer->setIndentString("    ");
//Lets start our document,setting the version of the XML and also the encoding we gonna use
//XMLWriter->startDocument($string version, $string encoding);
$writer->startDocument("1.0", "UTF-8");
//lets start our main element,lets call it "XML"
$writer->startElement('ScavengerScram');



switch($command) {
	case "login":
		$user = $_POST['username'];
		$pass = $_POST['password'];
		
		$auth = new Auth();
		$result = $auth->login($user, $pass);
		
		$writer->startElement("LoginResult");
		if($result == -1) {
			$writer->writeElement("LoginAuth","fail");
			$writer->writeElement("player_id", $result);
		}
		else {
			$writer->writeElement("LoginAuth","pass");
		}
		$writer->endElement();

		break;
	default:
		$result = "error";	
}

$writer->endDocument();


// $writer->startElement("gallery");
// /* We gonna add an Attribute to the gallery called "name" with the respective name of the gallery */
// //XMLWriter->writeAttribute($string atribute,$string value);
// $writer->writeAttribute("name", $name);

// /* Ok now we have our gallery node open,we need to put the images inside it that belong to this gallery,so lets make another query inside this loop */
// $query_images = "SELECT * FROM images WHERE gallery_id = $id";
// $images = $mysqli->query($query_images);
// while($row_image = $images->fetch_array()){
// $url = $row_image['url'];
// //now lets create all our image nodes here
// $writer->startElement("image");
// //write our URL attribute with the respective image URL
// $writer->writeAttribute("url", $url);
// //Close our image node
// $writer->endElement();
// }
// //lets close our gallery node
// $writer->endElement();
// }
// //Now lets close our main element

// $writer->endElement();
// //close our document
// $writer->endDocument();

/*Lets output what we have so far,first we need to set a header so we can display the XML in the
  browser,otherwise you will need to look at the source output. */
header('Content-type: text/xml');

//lets then echo our XML;

echo $writer->outputMemory();


?>