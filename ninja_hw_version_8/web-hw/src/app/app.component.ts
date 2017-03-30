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

    private selectedTab : string = "Users";
    private searchedKeyword: string;
    jsonResult: string;

    constructor(private dataService: FetchDataService) {}

    displayData(resultJSON) : void {
      this.jsonResult = resultJSON['data'];
    }

    // calling the respective service on tab changed
    callSelectedTabService(selectedTab: string, searchedKeyword: string, lat: string, lon: string) {

      switch (selectedTab) {
        case "Users":
          this.dataService.getSearchedUsers(searchedKeyword)
              .subscribe(
                userSearchData => this.displayData(userSearchData),
                error => console.log(error),
                () => console.log("Completed!"));
          break;
        case "Pages":
          this.dataService.getSearchedPages(searchedKeyword)
              .subscribe(
                pageSearchData => this.displayData(pageSearchData),
                error => console.log(error),
                () => console.log("completed page search"));
          break;
        case "Events":
          this.dataService.getSearchedEvents(searchedKeyword)
              .subscribe(
                searchedEvents => this.displayData(searchedEvents),
                error => console.log(error),
                () => console.log("Completed event search"));
          break;
        case "Places":
          this.dataService.getSearchedPlaces(searchedKeyword, lat, lon)
              .subscribe(
                searchedPlaces => this.displayData(searchedPlaces),
                error => console.log(error),
                () => console.log("Completed places search"));
          break;
        case "Groups":
          this.dataService.getSearchedGroup(searchedKeyword)
              .subscribe(
                searchedGroups => this.displayData(searchedGroups),
                error => console.log(error),
                () => console.log("Completed group search"));
          break;
        case "Favorites":
          console.log("Favourites later");
          break;
        default:
          console.log("What the fuck? You clicked some tab which i dont know?");
          break;
      }
    }

    ngOnInit() : void {

       $('[data-toggle="tooltip"]').tooltip({trigger: 'manual'});
       $('a[data-toggle="tab"]').on('shown.bs.tab', (e) => {
          this.selectedTab = e.target['firstChild']['data'];
          
          // calling services on tab change  
          if (this.searchedKeyword !== undefined && this.searchedKeyword.length !== 0) {
              this.callSelectedTabService(this.selectedTab, this.searchedKeyword, "", "");
          }
       });

      // this.dataService.getDetailsOfId("353851465130")
      //     .subscribe(
      //       detailData => console.log(detailData),
      //       error => console.log(error),
      //       () => console.log("completef detail search")
      //     );          

      // this.dataService.getHighResImage("10158961066065131")
      //     .subscribe(
      //       picData => console.log(picData),
      //       error => console.log(error),
      //       () => console.log("completed pic data search")
      //     );       
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
        this.searchedKeyword = formValue['searchedKeyWord'];
        this.callSelectedTabService(this.selectedTab, formValue['searchedKeyWord'], "", "");
      }
    }

}
