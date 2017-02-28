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
                <form method="post" action="" style="padding-left:10px;" autocomplete="on">
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
                    <?php $keyword = strval($_POST["keyword"]);
                    echo 'keywordTextField.value = '."\"$keyword\";"; ?>
                <?php endif; ?>
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
                console.log(id);

                // 
                //     global $fb, $requestFB;

                //     $requestFB = $fb->request('GET', '/'.$id);
                //     $requestFB -> setParams([
                //         "fields" => "id,name,albums.limit(5){name,  photos.limit(2){name, picture}}, picture.width(700).height(700), posts.limit(5)",
                //     ]);

                //     try {
                //         $response = $fb->getClient()->sendRequest($requestFB);
                //     } catch(Facebook\Exceptions\FacebookResponseException $e) {
                //         // When Graph returns an error
                //         echo 'Graph returned an error: ' . $e->getMessage();
                //         exit;
                //     } catch(Facebook\Exceptions\FacebookSDKException $e) {
                //         // When validation fails or other local issues
                //         echo 'Facebook SDK returned an error: ' . $e->getMessage();
                //         exit;
                //     }
    
                //     $graphEdge = $response->getGraphEdge();
                //     $array = json_decode($graphEdge, true);
                // ?>
            }

            function hideResultTable(id) {
                var resultTable = document.getElementById('resultTable');
                if (resultTable !== null) {
                    resultTable.remove();
                    parseDetailTable(id);
                }
            }

            // setting the selected category (select tag) as it was earlier after the form submission
            <?php if(isset($_POST["typeOfSearch"])) : ?>        
                <?php
                    $selectedTag = $_POST["typeOfSearch"];
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
                            echo 'locationTextField.value = '."\"$location\";";
                        }
                        if (isset($_POST['distance'])) {
                            $distance = strval($_POST['distance']);
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

?>