import { AppComponent } from './app.component';
import { SearchComponent } from './search/search.component';
import { PageComponent } from './page/page.component';
import { EventComponent } from './event/event.component';
import { PlaceComponent } from './place/place.component';
import { GroupComponent } from './group/group.component';
import { DetailComponentComponent } from './detail-component/detail-component.component';

export const FbRoutingRoutes = [
    { path: '', redirectTo:'/user/undefined', pathMatch: 'full' },
    { path:'user/:query', component: SearchComponent },
    { path: 'page/:query', component:PageComponent },
    { path: 'event/:query', component: EventComponent },
    { path: 'place/:query', component: PlaceComponent },
    { path: 'group/:query', component:  GroupComponent},
    { path:'detail/:id', component:DetailComponentComponent }
];