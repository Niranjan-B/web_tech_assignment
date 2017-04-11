import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { FetchDataService } from '../fetch-data.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  isEventContentCleared: boolean = false;
  jsonEventResult: string;

   // vars for events tab
    hideButtonsEventTab : boolean = true;
    nextPageButtonEventTab: boolean = false;
    previousPageButtonEventTab: boolean = false;
    nextPageButtonPageEventUrl: string;
    previousPageButtonEventTabUrl: string;

    constructor(private dataService: FetchDataService, private activatedRoute: ActivatedRoute) { }

    // function to fire next page events tab, fuck facebooks API. giving wierd JSON for places
    fireNextPageEventsTab(): void {
      this.dataService.getPageData(this.nextPageButtonPageEventUrl)
                      .subscribe(nextPageData => {

                        if (nextPageData['paging'] === undefined) {
                          this.isNextPageInEventsTabPresent(undefined);
                        }  else {
                          this.isNextPageInEventsTabPresent(nextPageData['paging']['next']);
                        }
                        this.isPreviousPageInEventsTabPresent(nextPageData['paging']['previous']);
                        this.jsonEventResult = nextPageData['data'];
                        this.hideButtonsEventTab = false;
                      },
                      error => console.log(error),
                      () => console.log("completed fetching next page data for events"));
    }
    // function to fire previous page events tab
    firePreviousPageEventsTab(): void {
      this.dataService.getPageData(this.previousPageButtonEventTabUrl)
                      .subscribe(previousPageData => {
                        this.isNextPageInEventsTabPresent(previousPageData['paging']['next']);
                        this.isPreviousPageInEventsTabPresent(previousPageData['paging']['previous']);
                        this.jsonEventResult = previousPageData['data'];
                        this.hideButtonsEventTab = false;
                      },
                      error => console.log(error),
                      () => console.log("completed fetching next page data"));
    }

    // function to check if next page of pages tab is available
    isNextPageInEventsTabPresent(nextPageUrl): void {
      nextPageUrl !== undefined ? this.nextPageButtonEventTab = true : this.nextPageButtonEventTab = false;
      this.nextPageButtonPageEventUrl = nextPageUrl;
    }
    // function to check if the previous page of page tab is available
    isPreviousPageInEventsTabPresent(previousPageUrl): void {
      previousPageUrl !== undefined ? this.previousPageButtonEventTab = true : this.previousPageButtonEventTab = false; 
      this.previousPageButtonEventTabUrl = previousPageUrl;
    }

    private loadEventData(searchedKeyword) {
      this.dataService.getSearchedEvents(searchedKeyword)
              .subscribe(
                searchedEvents => {
                  this.isNextPageInEventsTabPresent(searchedEvents['paging']['next']);
                  this.isPreviousPageInEventsTabPresent(searchedEvents['paging']['previous']);
                  this.jsonEventResult = searchedEvents['data'];
                  this.hideButtonsEventTab = false;
                  this.isEventContentCleared = true;
                },
                error => console.log(error),
                () => console.log("Completed event search"));
    }

    ngOnInit() {
      // subscribe to listen for search requests
      this.activatedRoute.params
                         .map(params => params['query'])
                         .subscribe((query) => {
                           //this.loadEventData(query);
                          });
    }

}
