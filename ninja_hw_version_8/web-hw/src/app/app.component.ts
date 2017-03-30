import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

declare var $:any;
import { FetchDataService } from './fetch-data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

    constructor(private dataService: FetchDataService) {
    }

    ngOnInit() : void {
       $('[data-toggle="tooltip"]').tooltip({trigger: 'manual'});
       $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
          console.log(e.target['firstChild']);
      });

      this.dataService.getSearchedUsers("niranjan")
          .subscribe(
            userSearchData => console.log(userSearchData),
            error => console.log(error),
            () => console.log("Completed!")
          );

      this.dataService.getSearchedPages("spacex")
          .subscribe(
            pageSearchData => console.log(pageSearchData),
            error => console.log(error),
            () => console.log("completed page search")
          );

      this.dataService.getSearchedEvents("usc")
          .subscribe(
            searchedEvents => console.log(searchedEvents),
            error => console.log(error),
            () => console.log("Completed event search")
          );

      this.dataService.getSearchedPlaces("usc", "", "")
          .subscribe(
            searchedPlaces => console.log(searchedPlaces),
            error => console.log(error),
            () => console.log("Completed places search")
          );

      this.dataService.getSearchedGroup("usc")
          .subscribe(
            searchedGroups => console.log(searchedGroups),
            error => console.log(error),
            () => console.log("Completed group search")
          );
      
      this.dataService.getDetailsOfId("353851465130")
          .subscribe(
            detailData => console.log(detailData),
            error => console.log(error),
            () => console.log("completef detail search")
          );          

      this.dataService.getHighResImage("10158961066065131")
          .subscribe(
            picData => console.log(picData),
            error => console.log(error),
            () => console.log("completed pic data search")
          );       
    }
  
    searchGroup = new FormGroup({
      searchedKeyWord: new FormControl(null, Validators.required)
    });
    
    // fire search if it's valid
    fireSearchEvent(formValue: Object, formValidity: boolean): void {
      if (!formValidity) {
        var inputField = $('#searchQueryInput').tooltip('show');
        setTimeout(function(){
          inputField.tooltip('hide');
        }, 1500);
      } else {
        // form valid here.. call the backend
      }
    }

}
