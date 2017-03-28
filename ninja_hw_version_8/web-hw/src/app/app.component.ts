import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
declare var $:any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

    ngOnInit() : void {
       $('[data-toggle="tooltip"]').tooltip({trigger: 'manual'});
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
        // form valid here.. call the backend
      }
    }

}
