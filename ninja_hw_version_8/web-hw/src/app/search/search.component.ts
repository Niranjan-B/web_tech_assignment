import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { FetchDataService } from '../fetch-data.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit, OnDestroy {

    private selectedTab : string = "Users";
    private searchedKeyword: string;
    private subscription;
    
    isContentCleared: boolean = false;
    
    jsonResult = new Array();

    // vars for user tab
    hideButtonsUserTab : boolean = true;
    nextPageButtonUserTab: boolean = false;
    previousPageButtonUserTab: boolean = false;
    nextPageButtonUserTabUrl: string;
    previousPageButtonUserTabUrl: string;

    constructor(private dataService: FetchDataService, private activatedRoute: ActivatedRoute) {}    

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

    private loadData(searchedKeyword: string): void {
      this.subscription = this.dataService.getSearchedUsers(searchedKeyword)
              .subscribe(
                userSearchData => {
                  //console.log(userSearchData);
                  //this.isNextPageInUserTabPresent(userSearchData['paging']['next']);
                  //this.isPreviousPageInUserTabPresent(userSearchData['paging']['previous']);
                  this.jsonResult = userSearchData;
                  //console.log(userSearchData);
                  this.hideButtonsUserTab = false;
                  this.isContentCleared = true;
                },
                error => console.log(error),
                () => console.log("Completed!"));
    }

    ngOnInit() : void {
      console.log("On init called");
      this.jsonResult = [];
      // subscribe to listen for search requests
      this.activatedRoute.params
                         .map(params => params['query'])
                         .subscribe(data => 
                            { this.loadData(data);}, 
                          error => 
                            { console.log(error); }
                          );   
    }

    ngOnDestroy(): void {
      this.subscription.unsubscribe();
      console.log("On destroy called");
    }

}
