import { AppComponent } from './app.component';
import { SearchComponent } from './search/search.component';
import { DetailComponentComponent } from './detail-component/detail-component.component';

export const FbRoutingRoutes = [
    { path:'search/:query', component:SearchComponent },
    { path:'detail/:id', component:DetailComponentComponent }
];