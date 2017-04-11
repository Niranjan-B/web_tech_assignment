import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { FetchDataService } from '../fetch-data.service';

@Component({
  selector: 'app-place',
  templateUrl: './place.component.html',
  styleUrls: ['./place.component.css']
})
export class PlaceComponent implements OnInit {

  isPlaceContentCleared: boolean = false;
  jsonPlaceResult: string;

  // vars for places tab
  hideButtonsPlaceTab : boolean = true;
  nextPageButtonPlaceTab: boolean = false;
  previousPageButtonPlaceTab: boolean = false;
  nextPageButtonPagePlaceUrl: string;
  previousPageButtonPlaceTabUrl: string;

  constructor(private dataService: FetchDataService, private activatedRoute: ActivatedRoute) { }

  // function to fire next page event in locations tab
    fireNextPageLocationsTab(): void {
      this.dataService.getPageData(this.nextPageButtonPagePlaceUrl)
                      .subscribe(nextPageData => {
                        if (nextPageData['paging'] === undefined) {
                          this.isNextPageInPlacesTabPresent(undefined);
                        }  else {
                          this.isNextPageInPlacesTabPresent(nextPageData['paging']['next']);
                        }
                        this.isPreviousPageInPlacesTabPresent(nextPageData['paging']['previous']);
                        this.jsonPlaceResult = nextPageData['data'];
                        this.hideButtonsPlaceTab = false;
                      },
                      error => console.log(error),
                      () => console.log("completed fetching next page data location"));
    }
    // function to fire previous page event in locations tab
    firePreviousPageLocationsTab(): void {
      this.dataService.getPageData(this.previousPageButtonPlaceTabUrl)
                      .subscribe(previousPageData => {
                        this.isNextPageInPlacesTabPresent(previousPageData['paging']['next']);
                        this.isPreviousPageInPlacesTabPresent(previousPageData['paging']['previous']);
                        this.jsonPlaceResult = previousPageData['data'];
                        this.hideButtonsPlaceTab = false;
                      },
                      error => console.log(error),
                      () => console.log("completed fetching next page data location"));
    }

    // function to check if next page of pages tab is available
    isNextPageInPlacesTabPresent(nextPageUrl): void {
      nextPageUrl !== undefined ? this.nextPageButtonPlaceTab = true : this.nextPageButtonPlaceTab = false;
      this.nextPageButtonPagePlaceUrl = nextPageUrl;
    }
    // function to check if the previous page of page tab is available
    isPreviousPageInPlacesTabPresent(previousPageUrl): void {
      previousPageUrl !== undefined ? this.previousPageButtonPlaceTab = true : this.previousPageButtonPlaceTab = false; 
      this.previousPageButtonPlaceTabUrl = previousPageUrl;
    }

    private loadSearchData(searchedKeyword, lat, lon) {
      this.dataService.getSearchedPlaces(searchedKeyword, lat, lon)
              .subscribe(
                searchedPlaces => {
                  this.isNextPageInPlacesTabPresent(searchedPlaces['paging']['next']);
                  this.isPreviousPageInPlacesTabPresent(searchedPlaces['paging']['previous']);
                  this.jsonPlaceResult = searchedPlaces['data'];
                  this.hideButtonsPlaceTab = false;
                  this.isPlaceContentCleared = true;
                },
                error => console.log(error),
                () => console.log("Completed places search"));
    }

    ngOnInit() {
      this.activatedRoute.params
                         .map(params => params['query'])
                         .subscribe((query) => {
                           //this.loadSearchData(query, "", "");
                          });
    }

}
