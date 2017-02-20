<!DOCTYPE html>
<html>
    <head>
        <title>HW6</title>
    </head>
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

    <script type="text/javascript">

        function hideLocationDistanceRow() {
            var locationDistanceRow = document.getElementById('location_distance_input_row');
            // hide the row by default
            locationDistanceRow.style.visibility = "hidden";
        }

        function onElementSelected() {
            var selectTag = document.getElementById("type_of_search");
            var locationDistanceRow = document.getElementById('location_distance_input_row');

            if (selectTag.value === "places") {
                locationDistanceRow.style.visibility = "visible";
            } else {
                locationDistanceRow.style.visibility = "hidden";
            }
        }        

    </script>

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
                                    <option value="users" selected="selected">Users</option>
                                    <option value="pages">Pages</option>
                                    <option value="events">Events</option>
                                    <option value="places">Places</option>
                                    <option value="groups">Groups</option>
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
                            <td><input type="submit" value="Search"/><input type="reset" value="Clear"/></td>
                        </tr>
                    </table>
                </form>
            </div>
    </body>
</html>