import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Utilisateur } from '../../utils/types/utilisateur.type';
import { UtilisateurService } from '../../utils/services/utilisateur.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  utilisateurs: Utilisateur[] = [];
  utilisateurForm: FormGroup;
  loginForm: FormGroup;
  loginError: string | null = null;
  isEditMode = false;
  utilisateurIdToUpdate: number | null = null;

  constructor(private utilisateurService: UtilisateurService, private fb: FormBuilder, private router: Router) {
    this.utilisateurForm = this.fb.group({
      pseudo: [''],
      email: [''],
      password: [''],
    });

    this.loginForm = this.fb.group({
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
      alert('Utilisateur ajouté avec succès');
      this.utilisateurForm.reset();
      this.getAllUtilisateurs();
    });
  }

  login(): void {
    if (this.loginForm.invalid) {
      alert("Veuillez remplir tous les champs obligatoires !");
      return;
    }
    const utilisateur: { email: string; password: string } = this.loginForm.value;

    this.utilisateurService.login(utilisateur).subscribe({
      next: response => {
        if (response) {
          this.router.navigate(['/recettes']);
        } else {
          this.loginError = "Erreur lors de la connexion. Vérifiez vos identifiants.";
          alert(this.loginError);
        }
      },
      error: (err) => {
        this.loginError = "Erreur lors de la connexion. Vérifiez vos identifiants.";
        alert(this.loginError);
        console.error('Erreur de connexion:', err.message);
      }
    });
  }

  deleteUtilisateur(id: number): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer cet utilisateur ?")) {
      this.utilisateurService.deleteUtilisateur(id).subscribe({
        next: (response) => {
          alert('Utilisateur supprimé avec succès');
          this.getAllUtilisateurs();
        },
        error: err => {
          console.error('Erreur lors de la suppression de l\'utilisateur:', err);
        }
      });
    }
  }




  editUtilisateur(utilisateur: Utilisateur): void {
    this.isEditMode = true;
    this.utilisateurIdToUpdate = utilisateur.id_utilisateur;
    this.utilisateurForm.patchValue({
      pseudo: utilisateur.pseudo,
      email: utilisateur.email,
      password: ''
    });
  }

  updateUtilisateur(): void {
    if (this.utilisateurForm.invalid || !this.utilisateurIdToUpdate) {
      alert("Veuillez remplir tous les champs obligatoires !");
      return;
    }
    const updatedUtilisateur: Utilisateur = this.utilisateurForm.value;
    this.utilisateurService.updateUtilisateur(this.utilisateurIdToUpdate, updatedUtilisateur).subscribe({
      next: () => {
        alert('Utilisateur mis à jour avec succès');
        this.isEditMode = false;
        this.utilisateurIdToUpdate = null;
        this.utilisateurForm.reset();
        this.getAllUtilisateurs();
      },
      error: err => console.error('Erreur lors de la mise à jour de l\'utilisateur:', err)
    });
  }

}
