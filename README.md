Application de Gestion des Soins Médicaux
Cette application Spring Boot permet de gérer les soins médicaux des patients avec un modèle de données simple mais efficace.

Fonctionnalités
Gestion complète des patients (ajout, modification, suppression, recherche)
Gestion des types de soins médicaux
Suivi des séances de soins pour chaque patient
API REST complète avec documentation OpenAPI/Swagger
Structure du Projet
Le projet suit une architecture MVC avec une séparation claire des couches :

com.medical.soins
├── config         # Configuration (OpenAPI, Initialisation des données)
├── controller     # Contrôleurs REST
├── dto            # Objets de transfert de données
├── entity         # Entités JPA
├── exception      # Gestion des exceptions
├── mapper         # Conversion entre entités et DTOs
├── repository     # Repositories Spring Data JPA
└── service        # Services métier
Modèle de Données
Patient : Informations de base sur les patients (nom, prénom, téléphone)
Soin : Types de soins médicaux disponibles
Séance : Historique des soins reçus par les patients
API REST
L'API expose les endpoints suivants :

Patients
GET /api/patients : Liste tous les patients
GET /api/patients/{id} : Récupère un patient par son ID
POST /api/patients : Crée un nouveau patient
PUT /api/patients/{id} : Met à jour un patient existant
DELETE /api/patients/{id} : Supprime un patient
GET /api/patients/search?query=... : Recherche des patients par nom ou prénom
Soins
GET /api/soins : Liste tous les types de soins
GET /api/soins/{id} : Récupère un soin par son ID
POST /api/soins : Crée un nouveau type de soin
PUT /api/soins/{id} : Met à jour un type de soin existant
DELETE /api/soins/{id} : Supprime un type de soin
GET /api/soins/search?query=... : Recherche des soins par désignation
Séances
GET /api/seances : Liste toutes les séances
GET /api/seances/{patientId}/{soinId} : Récupère une séance spécifique
POST /api/seances : Crée une nouvelle séance
DELETE /api/seances/{patientId}/{soinId} : Supprime une séance
GET /api/seances/patient/{patientId} : Liste les séances d'un patient
GET /api/seances/soin/{soinId} : Liste les séances d'un type de soin
GET /api/seances/date-range?dateDebut=...&dateFin=... : Liste les séances dans une plage de dates
Technologies Utilisées
Java 17
Spring Boot 3.2.0
Spring Data JPA
MySQL
