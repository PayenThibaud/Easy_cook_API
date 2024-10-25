import { Routes } from '@angular/router';

import { AccueilComponent } from './pages/accueil/accueil.component';
import { InscriptionComponent } from './pages/inscription/inscription.component';

export const routes: Routes = [
    {path: "", component: AccueilComponent},
    {path: "register", component: InscriptionComponent}
];
