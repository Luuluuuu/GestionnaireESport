package modele;

import modele.Etat;
import vue.HeaderAdmin;
import vue.VueCalendrier;

public class EtatFactory {
	
    public static Etat creerEtat(String texteBouton) {
        switch (texteBouton) {
            case "Créer un nouveau tournoi":
                return Etat.CREER;
            case "Créer une nouvelle équipe":
                return Etat.CREER;
            case "Créer un nouveau joueur":
                return Etat.CREER;
            case "Annuler":
                return Etat.ANNULER;
            case "Se déconnecter":
                return Etat.DECONNECTER;
            case "Supprimer le tournoi sélectionné":
                return Etat.SUPPRIMER;
            case "Supprimer l'équipe sélectionnée":
                return Etat.SUPPRIMER;
            case "Supprimer le joueur sélectionné":
                return Etat.SUPPRIMER;
            case "Ecuries / Responsables / Arbitres":
                return Etat.ECURIE;
            case "Valider":
                return Etat.VALIDER;
            case "Classement":
            	//désactivation puis réactivation du bouton classement après que la page soit ouverte lié au problème d'ouverture de fenêtre multiple
            	VueCalendrier.desactiverBouton(VueCalendrier.getBtnClassement());
                return Etat.CLASSEMENT;
            case "Equipes":
                return Etat.EQUIPES;
            case "Joueurs":
                return Etat.JOUEURS;
            case "Calendrier":
                return Etat.CALENDRIER;
            case "Tournois":
                return Etat.TOURNOIS;
            case "Rechercher":
                return Etat.RECHERCHER;
            case "Mon profil":
                return Etat.PROFIL;
            case "POULE 1":
                return Etat.POULE1;
            case "POULE 2":
                return Etat.POULE2;
            case "POULE 3":
                return Etat.POULE3;
            case "POULE 4":
                return Etat.POULE4;
            case "POULE FINALE":
            	return Etat.POULEF;
            case "Choisir une photo":
            	return Etat.PHOTO;
            default:
                return null;
        }
    }
    
}
