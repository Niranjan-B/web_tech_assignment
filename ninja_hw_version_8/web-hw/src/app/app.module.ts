import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { FetchDataService } from './fetch-data.service';
import { DetailComponentComponent } from './detail-component/detail-component.component';
import { SearchComponent } from './search/search.component';
import { FbRoutingRoutes } from './fb-routing.routes';

@NgModule({
  declarations: [
    AppComponent,
    DetailComponentComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RouterModule.forRoot(FbRoutingRoutes)
  ],
  providers: [FetchDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
