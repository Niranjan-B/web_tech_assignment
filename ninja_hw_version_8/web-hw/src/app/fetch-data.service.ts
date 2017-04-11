import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class FetchDataService {

  private headers = new Headers();

  constructor(private http: Http) { 
    this.headers.append('Access-Control-Allow-Origin', '*');  
  }

  // call to get the user searched for
  getSearchedUsers(searchedUser: string) {
    // return this.http.get("https://php-gae-161219.appspot.com/?search_type=users&searched_keyword="+searchedUser, 
    //             {headers: this.headers})
    //             .map(response => response.json());
    return this.http.get("https://sheetsu.com/apis/v1.0/a9c02fa03319", {headers: this.headers})
                    .map(response => response.json());
  }

  // function to get pages of searched query
  getSearchedPages(searchedPage: string) {
    // return this.http.get("https://php-gae-161219.appspot.com/?search_type=pages&searched_keyword="+searchedPage,
    //             {headers: this.headers})
    //             .map(response => response.json());
    return this.http.get("https://sheetsu.com/apis/v1.0/a9c02fa03319", {headers: this.headers})
                    .map(response => response.json());
  }

  // function to get events of searched type
  getSearchedEvents(searchedEvent: string) {
    // return this.http.get("https://php-gae-161219.appspot.com/?search_type=events&searched_keyword="+searchedEvent,
    //             { headers: this.headers })
    //             .map(response => response.json());
    return this.http.get("https://sheetsu.com/apis/v1.0/a9c02fa03319", {headers: this.headers})
                    .map(response => response.json());
  }

  // function to get nearby places
  getSearchedPlaces(searchedPlace: string, lat: string, lon: string) {
    // var url = "";
    
    // if (lat.length === 0 || lon.length === 0) {
    //   url = "https://php-gae-161219.appspot.com/?search_type=places&searched_keyword="+searchedPlace;
    // } else {
    //   url = "https://php-gae-161219.appspot.com/?search_type=places&searched_keyword="+ searchedPlace +"&lat="+ lat +"&lon=" + lon;
    // }

    // return this.http.get(url, { headers: this.headers })
    //            .map( response => response.json()); 
    return this.http.get("https://sheetsu.com/apis/v1.0/a9c02fa03319", {headers: this.headers})
                    .map(response => response.json());
  }

  // method to search for specified group
  getSearchedGroup(searchedGroup: string) {
    // return this.http.get("https://php-gae-161219.appspot.com/?search_type=groups&searched_keyword="+searchedGroup, 
    //     { headers: this.headers })
    //     .map(response => response.json());
    return this.http.get("https://sheetsu.com/apis/v1.0/a9c02fa03319", {headers: this.headers})
                    .map(response => response.json());
  }

  // search details given the id
  getDetailsOfId(id: string) {
    return this.http.get("https://php-gae-161219.appspot.com/?search_type=details&searched_keyword="+id,
                { headers: this.headers})
                .map(response => response.json());
  }

  // get high resolution images
  getHighResImage(picId: string) {
    return this.http.get("https://php-gae-161219.appspot.com/?search_type=pictures&searched_keyword="+picId,
                    { headers: this.headers })
                    .map(response => response.json());
  }

  // get next page data
  getPageData(url: string) {
    return this.http.get(url, { headers: this.headers }).map(response => response.json());
  }

}
