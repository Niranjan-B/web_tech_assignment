import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { FetchDataService } from '../fetch-data.service';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  isGroupContentCleared: boolean = false;
  jsonGroupReult: string;

  // vars for groups tab
  hideButtonsGroupTab : boolean = true;
  nextPageButtonGroupTab: boolean = false;
  previousPageButtonGroupTab: boolean = false;
  nextPageButtonPageGroupUrl: string;
  previousPageButtonGroupTabUrl: string;


  constructor(private dataService: FetchDataService, private activatedRoute: ActivatedRoute) { }

  // function to fire next page event in groups tab
    fireNextPageGroupsTab(): void {
      this.dataService.getPageData(this.nextPageButtonPageGroupUrl)
                      .subscribe(nextPageData => {
                        if (nextPageData['paging'] === undefined) {
                          this.isNextPageInGroupsTabPresent(undefined);
                        }  else {
                          this.isNextPageInGroupsTabPresent(nextPageData['paging']['next']);
                        }
                        this.isPreviousPageInGroupsTabPresent(nextPageData['paging']['previous']);
                        this.jsonGroupReult = nextPageData['data'];
                        this.hideButtonsGroupTab = false;
                      },
                      error => console.log(error),
                      () => console.log("completed fetching next page data groups"));
    }
    // function to fire previous page event in groups tab
    firePreviousPageGroupsTab(): void {
      this.dataService.getPageData(this.previousPageButtonGroupTabUrl)
                      .subscribe(previousPageData => {
                        this.isNextPageInGroupsTabPresent(previousPageData['paging']['next']);
                        this.isPreviousPageInGroupsTabPresent(previousPageData['paging']['previous']);
                        this.jsonGroupReult = previousPageData['data'];
                        this.hideButtonsGroupTab = false;
                      },
                      error => console.log(error),
                      () => console.log("completed fetching next page data location"));
    }

     // function to check if next page of pages tab is available
    isNextPageInGroupsTabPresent(nextPageUrl): void {
      nextPageUrl !== undefined ? this.nextPageButtonGroupTab = true : this.nextPageButtonGroupTab = false;
      this.nextPageButtonPageGroupUrl = nextPageUrl;
    }
    // function to check if the previous page of page tab is available
    isPreviousPageInGroupsTabPresent(previousPageUrl): void {
      previousPageUrl !== undefined ? this.previousPageButtonGroupTab = true : this.previousPageButtonGroupTab = false; 
      this.previousPageButtonGroupTabUrl = previousPageUrl;
    }

    private loadGroupData(searchedKeyword) {
      this.dataService.getSearchedGroup(searchedKeyword)
              .subscribe(
                searchedGroups => {
                  this.isNextPageInGroupsTabPresent(searchedGroups['paging']['next']);
                  this.isPreviousPageInGroupsTabPresent(searchedGroups['paging']['previous']);
                  this.jsonGroupReult = searchedGroups['data'];
                  this.hideButtonsGroupTab = false;
                  this.isGroupContentCleared = true;
                },
                error => console.log(error),
                () => console.log("Completed group search"));
    }

    ngOnInit() {
      this.activatedRoute.params
                         .map(params => params['query'])
                         .subscribe((query) => {
                           //this.loadGroupData(query);
                          });

    }

}
