import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Utilisateur } from '../../utils/types/utilisateur.type';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {




  // user1:Utilisateur = {
  //   id: 1,
  //   nom: "Guigui",
  //   prenom: "Nico",
  //   email: "nico@mail.com",
  //   password: "123456",
  //   phone: "07 56 56 54 21"
  // };
  //
  // user2:Utilisateur = {
  //   id: 2,
  //   nom: "Gogol",
  //   prenom: "Bobo",
  //   email: "bobo@mail.com",
  //   password: "123456",
  //   phone: "08 23 23 23 22"
  // };
  //
  // users: Utilisateur[] = [this.user1, this.user2];

}
