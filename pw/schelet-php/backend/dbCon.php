<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET,POST,PUT");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
// Connecting, selecting database
$dbconn = pg_connect("host=localhost dbname=practic user=postgres password=postgres")
    or die('Could not connect: ' . pg_last_error());
?>