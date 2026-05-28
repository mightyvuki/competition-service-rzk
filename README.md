## Projekat za organizaciju sportskih takmičenja

Sistem omogućava:

- organizovanje sportskih događaja
- prijavu učesnika na događaj
- plaćanje kotizacije
- dodavanje rezultata

## korisnik-service

### Uloga

Upravlja korisnicima sistema.

### Baza: korisnik_db

### Tabele

- **korisnik**
    - id
    - ime
    - prezime
    - datumRodjenja
    - email
    - password
- **uloga**
    - id
    - naziv
- **korisnik_uloga**
    - korisnik_id
    - uloga_id

```
POST /register
POST /login
GET /korisnici/{id}
GET /korisnici
```

Test:

{
"datumRodjenja": "2004-02-22",
"email": "mate@gmail.com",
"ime": "Mate",
"password": "123",
"prezime": "Colic"
}

---

## event-service

### Uloga

Upravlja sportskim događajima i disciplinama.

### Baza: event_db

### Tabele

- **dogadjaj**
    - id
    - naziv
    - lokacija
    - datum
    - maxUcesnika
- **disciplina**
    - id
    - naziv
    - dogadjaj_id
- **rezultat**
    - id
    - disciplina_id
    - korisnik_id
    - rezultat
    - pozicija

```
POST /events
GET /events
GET /events/{id}
POST /events/{id}/disciplina
POST /events/rezultat
GET /events/rezultati
GET /events/rezultati/{eventId}
GET /events/disciplina/{id}/rezultati
GET /events/korisnik/{id}/rezultati

nadogradnja:
PUT /events/{id}/ranking
```

Test:

{
"datum": "2026-04-14",
"lokacija": "Novi Sad",
"maxUcesnika": 30,
"naziv": "NS Open"
}

---

## prijava-service

### Uloga

Upravlja prijavama učesnika na događaje.

### Baza: prijava_db

### Tabele

- **prijava**
    - id
    - korisnik_id
    - dogadjaj_id
    - datum
    - status
- **tim**
    - id
    - naziv
- **tim_clan**
    - id
    - tim_id
    - korisnik_id

```
POST /prijava
DELETE /prijava/{id}
GET /prijava/event/{eventId}
GET /prijava/korisnik/{userId}
PUT /prijava/{id}/status
```

Test:

{
"korisnikId":14,
"dogadjajId":2
}

---

## kotizacija-service

### Uloga

Upravlja kotizacijama za događaje.

### Baza: placanje_db

### Tabele

- **racun**
    - id
    - brojRacuna
    - datumKreiranja
    - tipRacuna
    - stanje
- **transakcija**
    - id
    - racun_id
    - iznos
    - datum
- **placanje**
    - id
    - prijava_id
    - status

```
POST /kotizacija/uplata
GET /kotizacija/{id}
GET /kotizacija/prijava/{id}
```

Test:

{
"prijavaId": 4,
"racunId":3,
"iznos":500.50
}
