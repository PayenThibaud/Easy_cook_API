import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, map, of } from 'rxjs';

export interface Utilisateur {
  id_utilisateur: number;
  pseudo: string;
  password: string;
  email: string;
  role: string;
}

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {
  private api_url: string = "http://localhost:8085/api/auth";

  constructor(private http: HttpClient) { }

  getAllUtilisateurs(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(this.api_url).pipe(
      catchError(error => {
        alert('Erreur lors de la récupération des utilisateurs : ' + error.message);
        return of([]); // Retourne un tableau vide en cas d'erreur
      })

    );
  }
}

