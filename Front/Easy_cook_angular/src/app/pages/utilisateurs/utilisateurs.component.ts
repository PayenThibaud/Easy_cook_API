import {Component, OnInit} from '@angular/core';
import {Utilisateur, UtilisateurService} from '../../utils/services/utilisateur.service';

@Component({
  selector: 'app-utilisateurs',
  standalone: true,
  imports: [],
  templateUrl: './utilisateurs.component.html',
  styleUrl: './utilisateurs.component.css'
})
export class UtilisateurComponent implements OnInit{
  utilisateurs: Utilisateur[] = [];

  constructor(private utilisateurService: UtilisateurService) { }


  ngOnInit(): void {
    this.getAllUtilisateurs()
  }


  getAllUtilisateurs(): void {
    this.utilisateurService.getAllUtilisateurs().subscribe(data => {
      this.utilisateurs = data;
    });
  }
}
