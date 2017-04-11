import { Component, OnInit  } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { FetchDataService } from '../fetch-data.service';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.css']
})
export class PageComponent implements OnInit {

  public jsonPageResult = [];
  public isLoaded: boolean = false;

  // vars for page tab
  public hideButtonsPageTab : boolean = true;
  public nextPageButtonPageTab: boolean = false;
  public previousPageButtonPageTab: boolean = false;
  public nextPageButtonPageTabUrl: string;
  public previousPageButtonPageTabUrl: string;

  constructor(private dataService: FetchDataService, private activatedRoute: ActivatedRoute) { }

      // function to fire next page event in pages tab
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

    private loadSearchData(searchedKeyword) {
      //  this.dataService.getSearchedPages(searchedKeyword)
      //           .subscribe(
      //           pageSearchData => {
      //             //this.isNextPageInPagesTabPresent(pageSearchData['paging']['next']);
      //             //this.isPreviousPageInPageTabPresent(pageSearchData['paging']['previous']);
      //             //this.jsonPageResult = pageSearchData['data'];
      //             this.hideButtonsPageTab = false;
      //           },
      //           error => console.log(error),
      //           () => {
      //             this.isLoaded = true;
      //             console.log("completed page search");
      //           });
    }

    ngOnInit() {
      // subscribe to listen for search requests
      this.activatedRoute.params
                         .map(params => params['query'])
                         .subscribe((query) => this.loadSearchData(query));
    }

}
