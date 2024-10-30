import {Component, OnInit} from '@angular/core';
import {UtilisateurService} from '../../utils/services/utilisateur.service';
import {Utilisateur} from '../../utils/types/utilisateur.type';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common'; // Assurez-vous d'importer CommonModule

@Component({
  selector: 'app-utilisateurs',
  standalone: true,
  imports: [
    CommonModule,         // Ajoutez CommonModule ici
    ReactiveFormsModule
  ],
  templateUrl: './utilisateurs.component.html',
  styleUrls: ['./utilisateurs.component.css']
})
export class UtilisateurComponent implements OnInit {
  utilisateurs: Utilisateur[] = [];
  utilisateurForm: FormGroup;

  constructor(private utilisateurService: UtilisateurService, private fb: FormBuilder) {
    this.utilisateurForm = this.fb.group({
      pseudo: [''],
      email: [''],
      password: [''],
    });
  }

  ngOnInit(): void {
    this.getAllUtilisateurs();
  }

  getAllUtilisateurs(): void {
    this.utilisateurService.getAllUtilisateurs().subscribe(data => {
      this.utilisateurs = data;
    });
  }

  addUtilisateur(): void {
    if (this.utilisateurForm.invalid) {
      alert("Veuillez remplir tous les champs obligatoires !");
      return;
    }
    const newUtilisateur: Utilisateur = this.utilisateurForm.value;
    this.utilisateurService.addUtilisateur(newUtilisateur).subscribe(() => {
      this.utilisateurs.push(newUtilisateur);
      this.utilisateurForm.reset();
    });
  }
}
