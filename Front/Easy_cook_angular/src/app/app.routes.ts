import { Routes } from '@angular/router';

import { AccueilComponent } from './pages/accueil/accueil.component';
import { InscriptionComponent } from './pages/inscription/inscription.component';
import { LoginComponent } from './pages/login/login.component';
import { ListeRecettesComponent } from './pages/liste-recettes/liste-recettes.component';
import { DetailRecetteComponent } from './pages/detail-recette/detail-recette.component';

export const routes: Routes = [
    {path: "", component: AccueilComponent},
    {path: "register", component: InscriptionComponent},
    {path: "login", component: LoginComponent},
    {path: "recettes", component: ListeRecettesComponent},
    // à adapter en fonction de l'ID recette
    {path: "recette", component: DetailRecetteComponent},
];
