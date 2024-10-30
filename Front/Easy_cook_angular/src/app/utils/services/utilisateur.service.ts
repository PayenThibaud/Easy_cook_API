import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {catchError, Observable, of, throwError} from 'rxjs';
import {Utilisateur} from '../types/utilisateur.type';
import {LoginRequestDto} from '../types/LoginRequestDto';
import {LoginResponseDto} from '../types/LoginResponseDto';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {
  private api_url: string = "http://localhost:8080/api/auth";

  constructor(private http: HttpClient) {
  }

  getAllUtilisateurs(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(this.api_url).pipe(
      catchError(error => {
        alert('Erreur lors de la récupération des utilisateurs : ' + error.message);
        return of([]); // Retourne un tableau vide en cas d'erreur
      })
    );
  }

  addUtilisateur(utilisateur: Utilisateur): Observable<Utilisateur> {
    return this.http.post<Utilisateur>(`${this.api_url}/register`, utilisateur).pipe(
      catchError(error => {
        alert('Erreur lors de l\'ajout de l\'utilisateur : ' + error.message);
        return of(null as unknown as Utilisateur); // Retourne null en cas d'erreur
      })
    );
  }

  login(utilisateur: { email: string; password: string }) {
    return this.http.post(`${this.api_url}/login`, utilisateur).pipe(
      catchError((error: HttpErrorResponse) => {
        return throwError(() => new Error('Erreur lors de la connexion'));
      })
    );
  }
}

