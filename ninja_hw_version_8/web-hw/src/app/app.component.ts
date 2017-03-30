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
    jsonPageResult: string;
    jsonEventResult: string;
    jsonPlaceResult: string;
    jsonGroupReult: string;

    constructor(private dataService: FetchDataService) {}

    private loadData(searchedKeyword: string, lat: string, lon: string): void {
      this.dataService.getSearchedUsers(searchedKeyword)
              .subscribe(
                userSearchData => this.jsonResult = userSearchData['data'],
                error => console.log(error),
                () => console.log("Completed!"));
        
      this.dataService.getSearchedPages(searchedKeyword)
              .subscribe(
                pageSearchData => this.jsonPageResult = pageSearchData['data'],
                error => console.log(error),
                () => console.log("completed page search"));

      this.dataService.getSearchedEvents(searchedKeyword)
              .subscribe(
                searchedEvents => this.jsonEventResult = searchedEvents['data'],
                error => console.log(error),
                () => console.log("Completed event search"));

      this.dataService.getSearchedPlaces(searchedKeyword, lat, lon)
              .subscribe(
                searchedPlaces => this.jsonPlaceResult = searchedPlaces['data'],
                error => console.log(error),
                () => console.log("Completed places search"));

       this.dataService.getSearchedGroup(searchedKeyword)
              .subscribe(
                searchedGroups => this.jsonGroupReult = searchedGroups['data'],
                error => console.log(error),
                () => console.log("Completed group search")); 
    }

    ngOnInit() : void {

       $('[data-toggle="tooltip"]').tooltip({trigger: 'manual'});
       $('a[data-toggle="tab"]').on('shown.bs.tab', (e) => {
          //this.selectedTab = e.target['firstChild']['data'];
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
        // calling all services and storing in cache 
        this.loadData(formValue['searchedKeyWord'], "", "");
      }
    }

}
