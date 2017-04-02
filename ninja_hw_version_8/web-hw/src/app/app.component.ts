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

  constructor(private router: Router){}

  ngOnInit() : void {
      $('[data-toggle="tooltip"]').tooltip({trigger: 'manual'});
      $('a[data-toggle="tab"]').on('shown.bs.tab', (e) => {
        // do nothing for now
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
        // call the search component here
        this.router.navigate(['/search/'+formValue['searchedKeyWord']]);
      }
    }
}
