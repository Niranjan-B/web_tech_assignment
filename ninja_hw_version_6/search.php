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
                            <td><input type="text" id="keyword" name="keyword" required/></td>
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
                                <input type="submit" value="Search"/>
                                <input type="reset" value="Clear" onclick="removePlaceFields()"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        <script type="text/javascript">

            var selectTag = document.getElementById("type_of_search");
            var locationDistanceRow = document.getElementById('location_distance_input_row');
            var keywordTextField = document.getElementById('keyword');
            var locationTextField = document.getElementById('location');
            var distanceTextField = document.getElementById('distance');

            function removePlaceFields() {
                // hide the row containing location and distance text fields if visible
                if (locationDistanceRow.style.visibility == "visible") {
                    locationDistanceRow.style.visibility = "hidden";
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
    // $fb->setDefaultAccessToken($accessToken);
    // $requestFB = $fb->request('GET', '/search');
    // $requestFB->setParams([
    //     'type' => 'user',
    //     'q' => 'ninja',
    // ]);

    // try {
    //     $response = $fb->getClient()->sendRequest($requestFB);
    // } catch(Facebook\Exceptions\FacebookResponseException $e) {
    //     // When Graph returns an error
    //     echo 'Graph returned an error: ' . $e->getMessage();
    //     exit;
    // } catch(Facebook\Exceptions\FacebookSDKException $e) {
    //     // When validation fails or other local issues
    //     echo 'Facebook SDK returned an error: ' . $e->getMessage();
    //     exit;
    // }
    
    // $graphNode = $response->getGraphEdge();
    // $array = json_decode($graphNode);
    // echo "<pre>";
    // echo json_encode($array, JSON_PRETTY_PRINT);
    // echo "</pre>";

    // setting the keyword if not empty


?>