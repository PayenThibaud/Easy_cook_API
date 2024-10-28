import { Utilisateur } from "./utilisateur.type"

export type Recette = {
    id: number
    nom: string   
    image: string
    duree: number
    calorie: number
    regime: string[]
    ingredients: Ingredient[]
    etapes: string[]
    auteur: string

}

export type Ingredient = {
    nom: string
    gramme: number
    litre: number
}