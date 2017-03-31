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

    // vars for user tab
    hideButtonsUserTab : boolean = true;
    nextPageButtonUserTab: boolean = false;
    previousPageButtonUserTab: boolean = false;
    nextPageButtonUserTabUrl: string;
    previousPageButtonUserTabUrl: string;

    // vars for page tab
    hideButtonsPageTab : boolean = true;
    nextPageButtonPageTab: boolean = false;
    previousPageButtonPageTab: boolean = false;
    nextPageButtonPageTabUrl: string;
    previousPageButtonPageTabUrl: string;

    // vars for events tab
    hideButtonsEventTab : boolean = true;
    nextPageButtonEventTab: boolean = false;
    previousPageButtonEventTab: boolean = false;
    nextPageButtonPageEventUrl: string;
    previousPageButtonEventTabUrl: string;

    // vars for places tab
    hideButtonsPlaceTab : boolean = true;
    nextPageButtonPlaceTab: boolean = false;
    previousPageButtonPlaceTab: boolean = false;
    nextPageButtonPagePlaceUrl: string;
    previousPageButtonPlaceTabUrl: string;

    // vars for groups tab
    hideButtonsGroupTab : boolean = true;
    nextPageButtonGroupTab: boolean = false;
    previousPageButtonGroupTab: boolean = false;
    nextPageButtonPageGroupUrl: string;
    previousPageButtonGroupTabUrl: string;


    constructor(private dataService: FetchDataService) {}

    // function to fire nex page event in pages tab
    fireNextPagePagesTab(): void {
      this.dataService.getPageData(this.nextPageButtonPageTabUrl)
                      .subscribe(nextPageData => {
                        this.isNextPageInPagesTabPresent(nextPageData['paging']['next']);
                        this.isPreviousPageInPageTabPresent(nextPageData['paging']['previous']);
                        this.jsonPageResult = nextPageData['data'];
                        this.hideButtonsPageTab = false;
                      },
                      error => console.log(error),
                      () => console.log("completed fetching next page data"));
    }
    // function to fire previous page event in pages tab
    firePreviousPagePagesTab(): void {
      this.dataService.getPageData(this.previousPageButtonPageTabUrl)
                      .subscribe(previousPageData => {
                        this.isNextPageInPagesTabPresent(previousPageData['paging']['next']);
                        this.isPreviousPageInPageTabPresent(previousPageData['paging']['previous']);
                        this.jsonPageResult = previousPageData['data'];
                        this.hideButtonsPageTab = false;
                      },
                      error => console.log(error),
                      () => console.log("completed fetching next page data"));
    }

    // function to fire next page event and get fresh data
    fireNextPageUserTab(): void {
      this.dataService.getPageData(this.nextPageButtonUserTabUrl)
                      .subscribe(nextPageData => {
                        this.isNextPageInUserTabPresent(nextPageData['paging']['next']);
                        this.isPreviousPageInUserTabPresent(nextPageData['paging']['previous']);
                        this.jsonResult = nextPageData['data'];
                        this.hideButtonsUserTab = false;
                      },
                      error => console.log(error),
                      () => console.log("completed fetching next page data"));
    }
    // function to fire previous page event and get fresh data
    firePreviousPageUsersTab(): void {
      this.dataService.getPageData(this.previousPageButtonUserTabUrl)
                      .subscribe(previousPageData => {
                        this.isNextPageInUserTabPresent(previousPageData['paging']['next']);
                        this.isPreviousPageInUserTabPresent(previousPageData['paging']['previous']);
                        this.jsonResult = previousPageData['data'];
                        this.hideButtonsUserTab = false;
                      },
                      error => console.log(error),
                      () => console.log("completed fetching next page data"));
    }

    // function to check if next page of pages tab is available
    isNextPageInPagesTabPresent(nextPageUrl): void {
      nextPageUrl !== undefined ? this.nextPageButtonPageTab = true : this.nextPageButtonPageTab = false;
      this.nextPageButtonPageTabUrl = nextPageUrl;
    }
    // function to check if the previous page of page tab is available
    isPreviousPageInPageTabPresent(previousPageUrl): void {
      previousPageUrl !== undefined ? this.previousPageButtonPageTab = true : this.previousPageButtonPageTab = false; 
      this.previousPageButtonPageTabUrl = previousPageUrl;
    }

    // function to check if the next page of users tab is available
    isNextPageInUserTabPresent(nextPageUrl): void {
      nextPageUrl !== undefined ? this.nextPageButtonUserTab = true : this.nextPageButtonUserTab = false;
      this.nextPageButtonUserTabUrl = nextPageUrl;
    }
    // function to check if the previous page of users tab is available
    isPreviousPageInUserTabPresent(previousPageUrl): void {
      previousPageUrl !== undefined ? this.previousPageButtonUserTab = true : this.previousPageButtonUserTab = false; 
      this.previousPageButtonUserTabUrl = previousPageUrl;
    }

    private loadData(searchedKeyword: string, lat: string, lon: string): void {
      this.dataService.getSearchedUsers(searchedKeyword)
              .subscribe(
                userSearchData => {
                  this.isNextPageInUserTabPresent(userSearchData['paging']['next']);
                  this.isPreviousPageInUserTabPresent(userSearchData['paging']['previous']);
                  this.jsonResult = userSearchData['data'];
                  this.hideButtonsUserTab = false;
                },
                error => console.log(error),
                () => console.log("Completed!"));
        
      this.dataService.getSearchedPages(searchedKeyword)
              .subscribe(
                pageSearchData => {
                  this.isNextPageInPagesTabPresent(pageSearchData['paging']['next']);
                  this.isPreviousPageInPageTabPresent(pageSearchData['paging']['previous']);
                  this.jsonPageResult = pageSearchData['data'];
                  this.hideButtonsPageTab = false;
                },
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
