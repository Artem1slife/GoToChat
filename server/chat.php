<?php 
$mysql_host = "localhost"; // sql сервер
$mysql_user = "root"; // пользователь
$mysql_password = ""; // пароль
$mysql_database = "goto"; // имя базы данных chat
if($_GET){

}

if (isset($_GET["action"])) { 
    $action = $_GET['action'];
}

if (isset($_GET["author"])) { 
    $author = $_GET['author'];
}
if (isset($_GET["client"])) { 
    $client = $_GET['client'];
}
if (isset($_GET["text"])) { 
    $text = $_GET['text'];
}

if (isset($_GET["data"])) { 
    $data = $_GET['data'];
}


$link = mysqli_connect($mysql_host, $mysql_user, $mysql_password); 
mysqli_select_db($link,"chat"); // коннект к БД на сервере
mysqli_set_charset($link,'utf8'); // кодировка

if($action == 'select'){

if($data == null){

$sql=mysqli_query($link,"SELECT * FROM `chat`");

print($result);
}else{
	


$mysqli = new mysqli('127.0.0.1', 'root', '', 'goto');
if ($mysqli->connect_errno) {
    echo "Извините, возникла проблема на сайте";

    // На реальном сайте этого делать не следует, но в качестве примера мы покажем 
    // как распечатывать информацию о подробностях возникшей ошибки MySQL
    echo "Ошибка: Не удалсь создать соединение с базой MySQL и вот почему: \n";
    echo "Номер_ошибки: " . $mysqli->connect_errno . "\n";
    echo "Ошибка: " . $mysqli->connect_error . "\n";
    
    exit;
}

// Выполняем запрос SQL
$sql = "SELECT * FROM `chat` WHERE data > $data";
if (!$result = $mysqli->query($sql)) {
    echo "Извините, возникла проблема в работе сайта.";

    echo "Ошибка: Наш запрос не удался и вот почему: \n";
    echo "Запрос: " . $sql . "\n";
    echo "Номер_ошибки: " . $mysqli->errno . "\n";
    echo "Ошибка: " . $mysqli->error . "\n";
    exit;
}
//$actor = $result->fetch_assoc();
$zzz = array();
while ($actor = $result->fetch_assoc()) {
    $output[]=$actor;
}
print(json_encode($output));
if ($result->num_rows === 0) {
    echo "Мы не смогли найти совпадение для $aid, простите. Пожалуйста, попробуйте еще раз.";
    exit;
}

}

/* while($row = mysqli_fetch_array($result)){
        echo 'автор'.row[`author`];
}*/

//print($output);

}



if($action == 'insert' && $author != null && $client != null && $text != null){ 


$current_time = round(microtime(1) * 1000);
try {
    $conn = new PDO("mysql:host=$mysql_host;dbname=$mysql_database", $mysql_user, $mysql_password);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "INSERT INTO  `chat`(`author`,`client`,`data`,`text`) VALUES ('$author','$client','$current_time','$text')";
    // use exec() because no results are returned
    $conn->exec($sql);
    echo "New record created successfully";
    }
catch(PDOException $e)
    {
    echo $sql . "<br>" . $e->getMessage();
    }

// пример передачи скрипту данных:
// chat.php?action=insert&author=author&client=client&text=text
// вставим строку с переданными параметрами
mysqli_query($link,"INSERT INTO `chat`(`author`,`client`,`data`,`text`) VALUES ('$author','$client','$current_time','$text')");

}


if($action == 'delete'){ // если действие DELETE
// полностью обнулим таблицу записей
mysqli_query($link,"TRUNCATE TABLE `chat`");	
}

mysqli_close($link);
?>
