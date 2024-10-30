import { Component, OnInit } from '@angular/core';
import { UtilisateurService } from '../../utils/services/utilisateur.service';
import { Utilisateur } from '../../utils/types/utilisateur.type';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common'; // Assurez-vous d'importer CommonModule
import { Router } from '@angular/router'; // Importez Router pour la redirection

@Component({
  selector: 'app-utilisateurs',
  standalone: true,
  imports: [
    CommonModule, // Ajoutez CommonModule ici
    ReactiveFormsModule
  ],
  templateUrl: './utilisateurs.component.html',
  styleUrls: ['./utilisateurs.component.css']
})
export class UtilisateurComponent implements OnInit {
  utilisateurs: Utilisateur[] = [];
  utilisateurForm: FormGroup; // Formulaire pour ajouter un utilisateur
  loginForm: FormGroup; // Formulaire pour se connecter
  loginError: string | null = null; // Pour stocker les erreurs de connexion

  constructor(private utilisateurService: UtilisateurService, private fb: FormBuilder, private router: Router) { // Ajoutez le Router ici
    // Initialisation du formulaire pour ajouter un utilisateur
    this.utilisateurForm = this.fb.group({
      pseudo: [''],
      email: [''],
      password: [''],
    });

    // Initialisation du formulaire pour se connecter
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
      this.utilisateurs.push(newUtilisateur);
      this.utilisateurForm.reset();
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
        // Vérifiez si la connexion est réussie (vous pouvez ajuster la condition selon votre API)
        if (response) {
          // Redirection vers la route /recettes
          this.router.navigate(['/recettes']);
        } else {
          this.loginError = "Erreur lors de la connexion. Vérifiez vos identifiants.";
          alert(this.loginError); // Optionnel : afficher une alerte
        }
      },
      error: (err) => {
        // Afficher un message d'erreur personnalisé
        this.loginError = "Erreur lors de la connexion. Vérifiez vos identifiants.";
        alert(this.loginError); // Afficher une alerte avec le message d'erreur
        console.error('Erreur de connexion:', err.message); // Affichez uniquement le message d'erreur
      }
    });
  }
}
