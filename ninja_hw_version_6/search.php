<?php 
    session_start();
    date_default_timezone_set('America/Los_Angeles');
?>
<!DOCTYPE html>
<html>
    <head>
        <title>HW6</title>
        <style>
            div.facebook_search_container {
                border: 2px solid grey;
                width: 50%;
                padding-top: 3px;
                margin-left: auto;
                margin-right: auto;
                padding-bottom: 15px;
            }
            #facebook_search_text {
                font-size: 20px;
                font-style: italic;
                font-family: sans-serif;
            }
            .form_labels {
                font-size:15px;
            }
            th.tableHeader {
                border: 2px solid grey;
                text-align: left;
            }
            img.profilePhoto {
                width: 40px;
                height: 30px;
            }
            a {
                text-decoration: underline;
                color: blue;
            }
            a:hover {
                cursor:pointer;
            }
            a:active {
                color: red;
            }
            a:visited {
                color: red;
            }
    </style>
    </head>

    <body onload="hideLocationDistanceRow()">
            <div class="facebook_search_container">
                <center><span id="facebook_search_text">Facebook Search</span></center>
                <hr style="color: grey; margin-left: 10px; margin-right:10px;">
                <form method="post" action="search.php" style="padding-left:10px;" autocomplete="on">
                    <table>
                        <tr>
                            <td><label for="keyword" class="form_labels">Keyword:&nbsp;</label></td>
                            <td><input type="text" id="keyword" name="keyword" title="This cant be left empty" required/></td>
                        </tr>
                        <tr>
                            <td><label for="type" class="form_labels">Type:&nbsp;</label></td>
                            <td>
                                <select name="typeOfSearch" id="type_of_search" onchange="onElementSelected()">
                                    <option value="user" selected="selected">Users</option>
                                    <option value="page">Pages</option>
                                    <option value="event">Events</option>
                                    <option value="place">Places</option>
                                    <option value="group">Groups</option>
                                </select>
                            </td>
                        </tr>
                        <tr id="location_distance_input_row">
                            <td><label for="location" class="form_labels">Location:&nbsp;</label></td>
                            <td><input type="text" id="location" name="location"/></td>
                            <td><label for="distance" class="form_labels">Distance(meters):&nbsp;</label></td>
                            <td><input type="text" id="distance" name="distance"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <input type="submit" value="Search" name="submit"/>
                                <input type="reset" value="Clear" name="reset" onclick="removePlaceFields()"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <br>
        <script type="text/javascript">

            var selectTag = document.getElementById("type_of_search");
            var locationDistanceRow = document.getElementById('location_distance_input_row');
            var keywordTextField = document.getElementById('keyword');
            var locationTextField = document.getElementById('location');
            var distanceTextField = document.getElementById('distance');

            function openImageInNewTab(url) {
                // getting the url value and opening a new tab
                var imageWindow = window.open("", "_blank");
                if (imageWindow !== null) {
                    var htmlString = "";
                    htmlString += "<img src = " + url + " style = 'width: 550px; height:550px;' />";
                    imageWindow.document.write(htmlString);   
                    imageWindow.focus();
                }
                
            }

            function removePlaceFields() {
                // hide the row containing location and distance text fields if visible
                if (locationDistanceRow.style.visibility == "visible") {
                    locationDistanceRow.style.visibility = "hidden";
                }
                var resultTable = document.getElementById('resultTable');
                if (resultTable !== null) {
                    resultTable.remove();
                } 
            }

            function hideLocationDistanceRow() {
                // hide the row by default initially
                <?php if(isset($_POST['typeOfSearch'])) :?>
                    <?php 
                        if ($_POST['typeOfSearch'] == "place") {
                            echo 'locationDistanceRow.style.visibility = "visible";';
                        } else {
                            echo 'locationDistanceRow.style.visibility = "hidden";';
                        }
                    ?>
                <?php else: ?>
                    <?php echo 'locationDistanceRow.style.visibility = "hidden";'; ?>
                <?php endif; ?>

                // setting the kwyword text field after form submission if keyword exists
                <?php if(isset($_POST["keyword"])) : ?>
                    <?php 
                        $keyword = strval($_POST["keyword"]);
                        $_SESSION['searchedKeyword'] = $keyword;
                        echo 'keywordTextField.value = '."\"$keyword\";"; ?>
                <?php elseif(isset($_GET["id"])) : ?>
                    <?php
                        $searchedKeyword = $_SESSION['searchedKeyword'];
                        echo 'keywordTextField.value = '."\"$searchedKeyword\";"; ?>
                <?php endif;?>
            }

            function onElementSelected() {
                if (selectTag.value === "place") {
                    locationDistanceRow.style.visibility = "visible";
                } else {
                    locationDistanceRow.style.visibility = "hidden";
                }
                locationTextField.value = "";
                distanceTextField.value = "";
            }

            function parseDetailTable(id) {
                // reloading the page with a new URL
                window.location.href = "http://localhost/search.php?id="+id;
            }

            function hideResultTable(id) {
                var resultTable = document.getElementById('resultTable');
                if (resultTable !== null) {
                    resultTable.remove();
                    parseDetailTable(id);
                }
            }

            function showHideAlbumPhotos(albumId) {
                if (document.getElementById(albumId).style.visibility === "visible") {
                    document.getElementById(albumId).style.visibility = "collapse";
                } else {
                    document.getElementById(albumId).style.visibility = "visible";
                }
            }

            function hideShowAlbumsPosts(genericId) {
                if (genericId === "album") {
                    if (document.getElementById("albumsDisplay").style.visibility === "collapse") {
                        document.getElementById("albumsDisplay").style.visibility = "visible";   
                        document.getElementById("postsDisplay").style.visibility = "collapse"; 
                    } else {
                        document.getElementById("albumsDisplay").style.visibility = "collapse";
                        document.getElementById("postsDisplay").style.visibility = "visible";
                    }
                } else {    
                    if (document.getElementById("postsDisplay").style.visibility === "collapse") {
                        document.getElementById("postsDisplay").style.visibility = "visible";
                        document.getElementById("albumsDisplay").style.visibility = "collapse";
                    } else {
                        document.getElementById("albumsDisplay").style.visibility = "visible";
                        document.getElementById("postsDisplay").style.visibility = "collapse";
                    }
                }
            }

            // setting the selected category (select tag) as it was earlier after the form submission
            <?php if(isset($_POST["typeOfSearch"])) : ?>        
                <?php
                    $selectedTag = $_POST["typeOfSearch"];
                    $_SESSION['typeOfSearch'] = $selectedTag;
                    if ($selectedTag == "user") {
                        echo 'selectTag.value = "user";';
                    } else if ($selectedTag == "page") {
                        echo 'selectTag.value = "page";';
                    } else if ($selectedTag == "event") {
                        echo 'selectTag.value = "event";';
                    } else if ($selectedTag == "place") {
                        echo 'selectTag.value = "place";';
                        if (isset($_POST['location'])) {
                            $location = strval($_POST['location']);
                            $_SESSION['location'] = $location;
                            echo 'locationTextField.value = '."\"$location\";";
                        }
                        if (isset($_POST['distance'])) {
                            $distance = strval($_POST['distance']);
                            $_SESSION['distance'] = $distance;
                            echo 'distanceTextField.value = '."\"$distance\";";
                        }
                    } else {
                        echo 'selectTag.value = "group";';
                    }
                ?>
            <?php elseif(isset($_GET['id'])) : ?>
                <?php 
                    // duplicate code here -- bad programming :( -- not really duplicate

                    if ($_SESSION['typeOfSearch'] == "user") {
                        echo 'selectTag.value = "user";';
                    } else if ($_SESSION['typeOfSearch'] == "page") {
                        echo 'selectTag.value = "page";';
                    } else if ($_SESSION['typeOfSearch'] == "event") {
                        echo 'selectTag.value = "event";';
                    } else if ($_SESSION['typeOfSearch'] == "place") {
                        echo 'selectTag.value = "place";';
                        if (isset($_SESSION['location'])) {
                            $location = $_SESSION['location'];
                            echo 'locationTextField.value = '."\"$location\";";
                        }
                        if (isset($_SESSION['location'])) {
                            $distance = $_SESSION['location'];
                            echo 'distanceTextField.value = '."\"$distance\";";
                        }
                    } else {
                        echo 'selectTag.value = "group";';
                    }
                ?>
            <?php endif; ?>

        </script>
    </body>
</html>

<?php 
    require_once __DIR__.'/libs/php-graph-sdk-5.0.0/src/Facebook/autoload.php';

    $accessToken = 'EAAX3MCLefw0BAOf9gnHAbJCYUpuKNiVJ02QdZCqAlPZCbHXfLiq785YiCwXyHKVorn5AEbOmHLQchbNYdsTCekSZC8NP3ZCOqZAe581VQ83YhV7ZB98lrwBulRoqJhSlDjDfCsOTXJ6NwS9TZB5XMGWM2ojLSKoDde3jonUYzF33wZDZD';
    $fb = new Facebook\Facebook([
        'app_id' => '1679160999051021',
        'app_secret' => 'ba0af0d2a6e5701eaaed56bf48c0be98',
        'default_graph_version' => 'v2.8',
    ]);
    $fb->setDefaultAccessToken($accessToken);
    $requestFB = $fb->request('GET', '/search');

    function processOutput($jsonArray) {
        
        echo "<table id='resultTable' align='center' style='border: 2px solid grey; border-collapse:collapse;'>";
        echo "<tr>";
        echo "<th class='tableHeader' style='width:240px;'>Profile Photo</th>";
        echo "<th class='tableHeader' style='width:380px;'>Name</th>";
        echo "<th class='tableHeader' style='width:180px;'>Details</th>";
        echo "</tr>";
        
        foreach ($jsonArray as $graphNode) {
            $imgUrl = $graphNode['picture']['url'];
            $imgUrl = strval($imgUrl);
            $id = $graphNode['id'];
            $id = strval($id);
            echo "<tr>";
            echo "<td style='width:240px; border:2px solid grey;'><img src='".$graphNode['picture']['url']."' class='profilePhoto' onclick='openImageInNewTab(\"$imgUrl\")'/></td>";
            echo "<td style='width:380px; border:2px solid grey;'>".$graphNode['name']."</td>";
            echo "<td style='width:180px; border:2px solid grey;'><a onclick='hideResultTable(\"$id\")'>Details</a></td>";
            echo "</tr>";
        }

        echo "</table>";

    }

    function executeNonPlaceRequest() {
        try {
            global $fb, $requestFB;
            $response = $fb->getClient()->sendRequest($requestFB);
        } catch(Facebook\Exceptions\FacebookResponseException $e) {
             // When Graph returns an error
            echo 'Graph returned an error: ' . $e->getMessage();
            exit;
        } catch(Facebook\Exceptions\FacebookSDKException $e) {
             // When validation fails or other local issues
            echo 'Facebook SDK returned an error: ' . $e->getMessage();
            exit;
        }
    
        $graphEdge = $response->getGraphEdge();
        $array = json_decode($graphEdge, true);
        
        if (sizeof($array) == 0) {
            // display empty search result 
            echo "<div style='width:60%; height:23px; background:silver; border:2px solid grey; margin-left:auto; margin-right:auto; padding-top:3px;'><center>";
            echo "No Records found";
            echo "</center></div>";
        } else {
            // function to print the table
            processOutput($array);
        }
    }

    // check if the form is submitted
    if (isset($_POST['submit'])) {
        if ($selectedTag == "place") {
            // first call Google API to get the cooridantes and then construct the request array
        } else {
            $requestFB -> setParams([
                "type" => "$selectedTag",
                "q" => "$keyword",
                "fields" => "picture.width(700).height(700),name,id",
            ]);
            executeNonPlaceRequest();
        }
    }

    // function to return URL for the given ID 
    function getUrlForId($id) {
        global $fb;

        $fbRequest = $fb->request('GET', '/'.$id);
        $fbRequest -> setParams([
            "fields" => "images",
        ]);

        try {
            $picResponse = $fb->getClient()->sendRequest($fbRequest);
        } catch(Facebook\Exceptions\FacebookResponseException $e) {
             // When Graph returns an error
            echo 'Graph returned an error: ' . $e->getMessage();
            exit;
        } catch(Facebook\Exceptions\FacebookSDKException $e) {
             // When validation fails or other local issues
            echo 'Facebook SDK returned an error: ' . $e->getMessage();
            exit;
        }
        $picJSON = $picResponse->getGraphNode();
        $picUrl = json_decode($picJSON, true);
        return $picUrl['images'][0]['source'];
    }

    // process the detail page and display it when clicked on detail
    function displayDetailPage($jsonData) {

        echo "<table style='width:60%; margin-left:auto; margin-right:auto;>";

        // check if albums are empty
        if (sizeof($jsonData['albums']) == 0) {
            // display empty albums here
            echo "<tr>";
                echo "<td style='height:1px;'>";
                echo "</td>";
            echo "</tr>";

            echo "<tr>";
            echo "<td style='border:2px solid grey; background:silver;'>";
            echo "<center>";
            echo "No albums found";
            echo "</center>";
            echo "</td>";
            echo "</tr>";
        } else {

            // hack code to generate proper output
            echo "<tr>";
                echo "<td style='height:1px;'>";
                echo "</td>";
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo "<div style='width:100%; height:23px; background:silver; padding-top:3px;'>";
                    echo "<center>";
                    $albumClick = "album";
                    echo "<a onclick='hideShowAlbumsPosts(\"$albumClick\")'>Albums</a>";
                    echo "<center>";
                    echo "</div>";
                echo "</td>";
            echo "</tr>";

            echo "<tr><td><br></td></tr>";

            echo "<tr id='albumsDisplay'>";
                echo "<td>";
                    echo "<table style='width:100%; border:1px solid silver; border-collapse:collapse'>";
                    for ($i=0; $i<sizeof($jsonData['albums']); $i++) {
                        echo "<tr style='padding-left:5px;'>";
                        echo "<td style='padding-left:5px;'><a style='text-align:left;' onclick='showHideAlbumPhotos(\"albumId$i\")'>".$jsonData['albums'][$i]['name']."</a></td>";
                        echo "</tr>";
                        echo "<tr id=albumId".$i." style='width:100%; padding-left:5px; border:1px solid silver; padding-left:5px; padding-top:5px; visibility:collapse'>";
                        $imageIdOne = $jsonData['albums'][$i]['photos'][0]['id'];
                        $imageURLOne = getUrlForId($imageIdOne);
                        $imageIdTwo = $jsonData['albums'][$i]['photos'][1]['id'];
                        $imageURLTwo = getUrlForId($imageIdTwo);
                        if ($imageURLOne != null) {
                            echo "<td style='padding-left:5px;'><img src='".$jsonData['albums'][$i]['photos'][0]['picture']."' style='width:80px; height:80px; margin-right:5px;' onclick='openImageInNewTab(\"$imageURLOne\")'/>";
                        }
                        if ($imageURLTwo != null) {
                            echo "<img src='".$jsonData['albums'][$i]['photos'][1]['picture']."' style='width:80px; height:80px;' onclick='openImageInNewTab(\"$imageURLTwo\")'/></td>";
                        }
                        echo "</tr>";
                    }
                    echo "</table>";
                echo "</td>";
            echo "</tr>";
        }

        echo "<tr><td><br></td></tr>";        

        // check if posts are empty
        if (sizeof($jsonData['posts']) == 0) {
            echo "<tr>";
                echo "<td style='border:2px solid grey; background:silver;'>";
                echo "<center>";
                echo "No Posts found";
                echo "</center>";
                echo "</td>";
            echo "</tr>";
        } else {
            echo "<tr>";
                echo "<td>";
                    echo "<center>";
                    $albumClick = "posts";
                    echo "<div style='width:100%; height:23px; background:silver; padding-top:3px;'>";
                    echo "<a onclick='hideShowAlbumsPosts(\"$albumClick\")'>Posts</a>";
                    echo "</div>";
                    echo "</center>";
                echo "</td>";
            echo "</tr>";

            echo "<tr><td><br></td></tr>";

            echo "<tr id='postsDisplay' style='visibility:collapse;'>";
                echo "<td>";
                    echo "<table style='width:100%; border:1px solid silver; border-collapse:collapse'>";
                    echo "<tr>";
                    echo "<td style='padding-left:5px;'><b>Message</b></td>";
                    echo "</tr>";

                    for ($i=0; $i<sizeof($jsonData['albums']); $i++) {
                        echo "<tr style='padding-left:5px;'>";
                        if ($jsonData['posts'][$i]['message'] != null) {
                            echo "<td style='padding-left:5px; border:1px solid silver;'>".$jsonData['posts'][$i]['message']."</td>";
                        }
                        echo "</tr>";
                    }
                    echo "</table>";
                echo "</td>";
            echo "</tr>";

        }

        echo "</table>";
    }

    // checking if the id field is set
    if (isset($_GET['id'])) {
        
        global $fb;
        $id = $_GET['id'];
        $id = strval($id);

        $requestFB = $fb->request('GET', '/'.$id);
        $requestFB -> setParams([
        "fields" => "id,name,albums.limit(5){name,  photos.limit(2){name, picture}}, picture.width(700).height(700), posts.limit(5)",
         ]);

         try {
                 $response = $fb->getClient()->sendRequest($requestFB);
             } catch(Facebook\Exceptions\FacebookResponseException $e) {
                 // When Graph returns an error
                 echo 'Graph returned an error: ' . $e->getMessage();
                 exit;
             } catch(Facebook\Exceptions\FacebookSDKException $e) {
                 // When validation fails or other local issues
                 echo 'Facebook SDK returned an error: ' . $e->getMessage();
                 exit;
             }
    
             $graphNode = $response->getGraphNode();
             $array = json_decode($graphNode, true);
             displayDetailPage($array);
    }
?>