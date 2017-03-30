<?php 
    
    $access_token = "EAAX3MCLefw0BAB1n5798KZCtgFAItvgZAv0ZCaWEcLLhd8KDKAcmfeWlyZAo3ZAJjMTEJ7ZBODIUTzSJywM306n4Et4EQoFgqakBu25hXSi4JbBJ0hFDqkzVFQWHSE5S7V2xGkUUYDsgubtkmIeQQQ";

    if (isset($_GET['search_type']) && isset($_GET['searched_keyword'])) {
        
        header('Content-Type: application/json');
        $strippd_out_keyword = str_replace(' ', '', $_GET['searched_keyword']);
        
        if ($_GET['search_type'] == "users") {
            // if search type is users
            $content = file_get_contents("https://graph.facebook.com/v2.8/search?q=".$strippd_out_keyword."&type=user&fields=id,name,picture.width(700).height(700)&access_token=$access_token");
            echo $content;
        } else if ($_GET['search_type'] == "pages") {
            // if search type is pages
            $pageContent = file_get_contents("https://graph.facebook.com/v2.8/search?q=".$strippd_out_keyword."&type=page&fields=id,name,picture.width(700).height(700)&access_token=$access_token");
            echo $pageContent;
        } else if ($_GET['search_type'] == "events") {
            // if search type is events
            $eventContent = file_get_contents("https://graph.facebook.com/v2.8/search?q=".$strippd_out_keyword."&type=event&fields=id,name,picture.width(700).height(700)&access_token=$access_token");
            echo $eventContent;
        } else if ($_GET['search_type'] == "places") {
            // if search type is places
            $lat = "34.0522222";
            $lon = "-118.2427778";

            // check for latitude and longitude first as response is dependent on them
            if (isset($_GET['lat']) && isset($_GET['lon'])) {
                $lat = $_GET['lat'];
                $lon = $_GET['lon'];
            }
            $placesContent = file_get_contents("https://graph.facebook.com/v2.8/search?q=".$strippd_out_keyword."&type=place&fields=id,name,picture.width(700).height(700)&center=".$lat.",".$lon."&access_token=$access_token");
            echo $placesContent;
        } else if ($_GET['search_type'] == "groups") {
            // if search type is groups
            $groupsContent = file_get_contents("https://graph.facebook.com/v2.8/search?q=".$strippd_out_keyword."&type=group&fields=id,name,picture.width(700).height(700)&access_token=$access_token");
            echo $groupsContent;
        } else if ($_GET['search_type'] == "details") {
            // if the search type is detail
            $detailContent = file_get_contents("https://graph.facebook.com/v2.8/".$strippd_out_keyword."?fields=albums.limit(5){name,photos.limit(2){name,picture}},posts.limit(5)&access_token=$access_token");
            echo $detailContent;
        } else if ($_GET['search_type'] == "pictures") {
            // if the search type is picture
            $pictureContent = file_get_contents("https://graph.facebook.com/v2.8/".$strippd_out_keyword."?fields=images&access_token=$access_token");
            echo $pictureContent;
        } else {
            echo "What the hell have you searched for? :P";
        }
    }
?>