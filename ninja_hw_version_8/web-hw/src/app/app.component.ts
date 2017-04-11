import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

declare var $:any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  private targetedTab: string = "user";
  private searchedQuery: string;

  constructor(private router: Router){}

  ngOnInit() : void {
      $('[data-toggle="tooltip"]').tooltip({trigger: 'manual'});
      $('a[data-toggle="tab"]').on('shown.bs.tab', (e) => {
        console.log(e.target['href'].toString().slice(23));
        this.targetedTab = e.target['href'].toString().slice(23);
        this.router.navigate(['/'+this.targetedTab+'/'+this.searchedQuery]);
      });
  }

  clearContent(): void {  
      // resetting the form
      this.searchGroup.reset();
      this.router.navigate(['/']);
    }

  searchGroup = new FormGroup({
      searchedKeyWord: new FormControl(null, Validators.required)
  });

  fireSearchEvent(formValue: Object, formValidity: boolean): void {
    
      if (!formValidity) {
        var inputField = $('#searchQueryInput').tooltip('show');
        setTimeout(function(){
          inputField.tooltip('hide');
        }, 1500);
      } else {
        // call the components here
        //this.router.navigate(['/search/'+formValue['searchedKeyWord']]);
        this.searchedQuery = formValue['searchedKeyWord'];
        this.router.navigate(['/'+this.targetedTab+'/'+formValue['searchedKeyWord']]);
      }
    }
}
