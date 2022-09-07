<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>Document</title>
</head>
<body>
    <form action="" method="POST">
            <p>Input: <input type="number" name="input" class="form-control" value=""></p> 
            <!-- <p>Lastname: <input type="text" name="lastname" class="form-control" value=""></p> -->
            <input type="submit" name="submit" value="Submit">
        </div>
    </form>

    <?php 
    class converter{
        public $input=0;

        function solve($input){
            $result = $input*1.609;
            printf($result);
        }
    }

    $converter = new converter;
    if(isset($_POST['submit'])&&!empty($_POST['input'])){
        echo "Kilometers: ";
        $result = $converter->solve($_POST['input']);
        echo $result;
    }
    ?>
</body>
</html>