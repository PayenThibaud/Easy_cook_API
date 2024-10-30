import {HttpClient, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {catchError, Observable, of, throwError} from 'rxjs';
import {Utilisateur} from '../types/utilisateur.type';

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
        return of([]);
      })
    );
  }

  addUtilisateur(utilisateur: Utilisateur): Observable<Utilisateur> {
    return this.http.post<Utilisateur>(`${this.api_url}/register`, utilisateur).pipe(
      catchError(error => {
        alert('Erreur lors de l\'ajout de l\'utilisateur : ' + error.message);
        return of(null as unknown as Utilisateur);
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

  deleteUtilisateur(id: number): Observable<HttpResponse<void>> {
    return this.http.delete<void>(`http://localhost:8080/api/auth/${id}`, { observe: 'response' }).pipe(
      catchError(error => {
        return of(null as unknown as HttpResponse<void>);
      })
    );
  }

  updateUtilisateur(id: number, utilisateur: Utilisateur): Observable<Utilisateur> {
    return this.http.put<Utilisateur>(`${this.api_url}/${id}`, utilisateur).pipe(
      catchError(error => {
        alert('Erreur lors de la mise à jour de l\'utilisateur : ' + error.message);
        return of(null as unknown as Utilisateur);
      })
    );
  }
}

