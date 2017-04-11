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
import { PageComponent } from './page/page.component';
import { EventComponent } from './event/event.component';
import { PlaceComponent } from './place/place.component';
import { GroupComponent } from './group/group.component';
import { FavouriteComponent } from './favourite/favourite.component';

@NgModule({
  declarations: [
    AppComponent,
    DetailComponentComponent,
    SearchComponent,
    PageComponent,
    EventComponent,
    PlaceComponent,
    GroupComponent,
    FavouriteComponent
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
